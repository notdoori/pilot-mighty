package com.pilot.mighty.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pilot.mighty.query.QueryExecutor;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"1. Menu"})
@RestController
@RequestMapping("/api/menu")
public class MenuController {
	
	@Autowired
	QueryExecutor qe;
	
	@SuppressWarnings("unused")
	private final Logger logger = LogManager.getLogger(MenuController.class);
	
	private String groupTemplate = "{ \"id\": \"\", \"name\": \"#name\", \"children\": [#children] }";
	private String menuTemplate = "{ \"id\": \"#id\", \"name\": \"#name\", \"children\": [] }";
	
	/**
	 * 메뉴 구성 정보 조회
	 * @author islee
	 * @return menu 구성 정보
	 */
	@ApiOperation(value="메뉴 구성 정보 조회", notes = "메뉴 구성을 위한 정보 조회")
	@RequestMapping(value= "/all", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getMenuByRoleGroup(@RequestParam String userId) throws JsonParseException, IOException {
		
//		ObjectMapper mapper = new ObjectMapper();
//		@SuppressWarnings("unchecked")
//		Map<String, Object> map = mapper.readValue(body, Map.class);
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(" SELECT A.PARENT_KEY  AS PARENT                                                          \n");
		sb.append("       ,B.MENU_NAME  AS NAME                                                             \n");
		sb.append("       ,A.CHILD                                                                          \n");
		sb.append("   FROM ( SELECT PARENT_KEY                                                              \n");
		sb.append("                ,LISTAGG(STRUCTURE_ID, ':') WITHIN GROUP(ORDER BY STRUCTURE_ID) AS CHILD \n");
		sb.append("            FROM SYS_MENU_STRUCTURE                                                      \n");
		sb.append("           GROUP BY PARENT_KEY  ) A                                                      \n");
		sb.append("       ,SYS_MENU_STRUCTURE B                                                             \n");
		sb.append("       ,( SELECT B.ROLE_ID                                                               \n");
		sb.append("            FROM ADM_USER_INFO A                                                         \n");
		sb.append("                ,ADM_GROUP_INFO B                                                        \n");
		sb.append("           WHERE A.USER_ID = :userId                                                     \n");
		sb.append("             AND A.USER_GROUP_ID = B.GROUP_ID) C                                         \n");
		sb.append("  WHERE A.PARENT_KEY = B.STRUCTURE_ID                                                    \n");
		sb.append("    AND C.ROLE_ID = B.ROLE_GROUP_ID                                                      \n");
		sb.append("  ORDER BY A.PARENT_KEY                                                                  \n");
		
		Map<String, Object> bindVal = new HashMap<String, Object>();
		bindVal.put("userId", userId);		
		List<Map<String, Object>> relList = qe.selectList(sb.toString(), bindVal);
		
		if (relList.size() <= 0) {
			return new ResponseEntity<Object>(null, HttpStatus.OK);
		}
		
		List<Map<String, Object>> groupTemplateMapList = new ArrayList<Map<String, Object>>();
		
		for( Map<String, Object> rel : relList) {
					
			String parent = rel.get("PARENT").toString();		
			String name =  rel.get("NAME").toString();
			String child = rel.get("CHILD").toString();
			StringTokenizer stok = new StringTokenizer(child, ":");
			
			List<String> childList = new ArrayList<String>();
			while (stok.hasMoreTokens()){
				childList.add("#" + stok.nextToken() + "#");
			}
			
			//String res = groupTemplate.replace("#children", String.join(",", childList));
			
			//logger.debug(groupTemplate.replace("#name", name).replace("#children", String.join(",", childList)));
			Map<String, Object> groupTemplateMap = new HashMap<String, Object>();
			groupTemplateMap.put(parent, groupTemplate.replace("#name", name).replace("#children", String.join(",", childList)));
			
			groupTemplateMapList.add(groupTemplateMap);
		}
		
		//List<Map<String, Object>> groupResultMapList = new ArrayList<Map<String, Object>>();
		List<String> groupResultList = new ArrayList<String>();
		
		for( Map<String, Object> g : groupTemplateMapList) {
			String key = g.keySet().toArray()[0].toString();
			Collection<Object> gcv = g.values();
			if (key.length() <= 2) {
				groupResultList.add(gcv.toArray()[0].toString());
			} else {
				for (int i = 0; i < groupResultList.size(); i++) {
					String value = groupResultList.get(i);
					
					if (value.indexOf("#" + key + "#") > 0) {
						groupResultList.set(i, value.replace("#" + key + "#", gcv.toArray()[0].toString()));
					}
				}
			}
		}		
		
		sb.setLength(0);
		sb.append(" SELECT A.STRUCTURE_ID                           \n");
		sb.append("       ,A.MENU_ID                                \n");
		sb.append("       ,A.MENU_NAME                              \n");		
		sb.append("   FROM SYS_MENU_STRUCTURE A                     \n");
		sb.append("       ,( SELECT B.ROLE_ID                       \n");
		sb.append("            FROM ADM_USER_INFO A                 \n");
		sb.append("                ,ADM_GROUP_INFO B                \n");
		sb.append("           WHERE A.USER_ID = :userId             \n");
		sb.append("             AND A.USER_GROUP_ID = B.GROUP_ID) C \n");
		sb.append("  WHERE A.ROLE_GROUP_ID = C.ROLE_ID              \n");
		sb.append("    AND A.HAS_CHILD = 'N'                        \n");
		sb.append("  ORDER BY A.STRUCTURE_ID                        \n");
		
		bindVal.clear();
		bindVal.put("userId", userId);	
		List<Map<String, Object>> menuList = qe.selectList(sb.toString(), bindVal);
		
		for (Map<String, Object> menu : menuList) {
			String replaceID ="#" + menu.get("STRUCTURE_ID").toString() + "#";
			String menuID = menu.get("MENU_ID").toString();
			String menuName = menu.get("MENU_NAME").toString();
			
			//menuTemplate.replace("#id", menuID).replace("#name", menuName);
			
			for (int i = 0; i < groupResultList.size(); i++) {
				String value = groupResultList.get(i);
				
				if (value.indexOf(replaceID) > 0) {
					groupResultList.set(i, value.replace(replaceID, menuTemplate.replace("#id", menuID).replace("#name", menuName)));
				}
			}
		}
		
		String result = "[\n";
		result += String.join(",", groupResultList);
		result += "\n]\n";
		
		ObjectMapper mapper = new ObjectMapper();
	    JsonNode actualObj = mapper.readTree(result);
		
		return new ResponseEntity<Object>(actualObj, HttpStatus.OK);
	}
	
	

}
