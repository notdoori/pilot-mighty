package com.pilot.mighty.controller;

import java.util.ArrayList;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pilot.mighty.model.UserInfo;
import com.pilot.mighty.query.QueryExecutor;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"1. Menu"})
@RestController
@RequestMapping("/api/menu")
public class MenuController {
	
	@Autowired
	QueryExecutor qe;
	
	private final Logger logger = LogManager.getLogger(MenuController.class);
	
	private final String groupTemplate = "{\"id\" : \"\", \"name\" : \"#name\", \"children\" : [#children]}";
	private final String menuTemplate = "{\"id\" : \"#id\", \"name\" : \"#name\", \"children\" : []}";
	
	/**
	 * 사용자 권한 별 메뉴 구성 정보 조회
	 * @author djchoi
	 * @return UserInfo[]
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 */
	@ApiOperation(value="메뉴 구성 정보 조회", notes = "사용자 권한 별 메뉴 구성 정보 조회")
	@RequestMapping(value= "/all", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> get(@RequestParam String userId) throws JsonMappingException, JsonProcessingException {	
		
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
		
		List<Map<String, Object>> menuGroupList = qe.selectList(sb.toString(), bindVal);
		
		List<Map<String, Object>> groupMapList = new ArrayList<Map<String, Object>>();
		List<String> childList = new ArrayList<String>();
		for (Map<String, Object> menu : menuGroupList) {
			String parent = menu.get("PARENT").toString();
			String name = menu.get("NAME").toString();
			String child = menu.get("CHILD").toString();
					
			StringTokenizer stok = new StringTokenizer(child, ":");
			while (stok.hasMoreTokens()) {
				childList.add("#" + stok.nextToken() + "#");
			}
					
			Map<String, Object> groupMap = new HashMap<String, Object>();
			groupMap.put(parent, groupTemplate.replace("#name", name).replace("#children", String.join(",", childList)));
			childList.clear();
			
			groupMapList.add(groupMap);
		}
		
		List<String> groupList = new ArrayList<String>();
		for (Map<String, Object> group : groupMapList) {
			String key = group.keySet().toArray()[0].toString();
			String value = group.values().toArray()[0].toString();
			
			if (key.indexOf("-") < 0) {
				groupList.add(value);
			} else {
				String target = "#" + key + "#";
				for (int i = 0; i < groupList.size(); i++) {
					String gs = groupList.get(i);
					if (gs.indexOf(target) >= 0) {
						groupList.set(i, gs.replace(target, value));
					}
				}
			}
		}
		
		if (groupList.size() <= 0) {
			return new ResponseEntity<Object>(null, HttpStatus.OK);
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
		List<Map<String, Object>> menuMapList = new ArrayList<Map<String, Object>>();
		for( Map<String, Object> menu : menuList) {
			String structureId = "#" + menu.get("STRUCTURE_ID").toString() +"#";
			String menuId = menu.get("MENU_ID").toString();
			String menuName = menu.get("MENU_NAME").toString();
			
			Map<String, Object> menuMap = new HashMap<String, Object>();
			menuMap.put(structureId, menuTemplate.replace("#id", menuId).replace("#name", menuName));
			menuMapList.add(menuMap);
		}
		
		String resGroup = "[\n" + String.join(",", groupList) + "\n]";
		
		for( Map<String, Object> menuMap : menuMapList) {
			String convKey = menuMap.keySet().toArray()[0].toString();
			String convVal = menuMap.values().toArray()[0].toString();
			
			if (resGroup.indexOf(convKey) >= 0) {
				resGroup = resGroup.replace(convKey, convVal);
			}
		}
		
		ObjectMapper mapper = new ObjectMapper();
	    JsonNode menuObj = mapper.readTree(resGroup);
		
		return new ResponseEntity<Object>(menuObj, HttpStatus.OK);
	}

}
