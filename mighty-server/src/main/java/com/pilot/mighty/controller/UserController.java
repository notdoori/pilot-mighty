package com.pilot.mighty.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pilot.mighty.model.UserInfo;
import com.pilot.mighty.query.QueryExecutor;
import com.pilot.mighty.service.UserService;
import com.pilot.mighty.util.TokenUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"1. User"})
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	QueryExecutor qe;
	
	private final Logger logger = LogManager.getLogger(UserController.class);
	

	/**
	 * 전체 사용자 조회 요청
	 * @author djchoi
	 * @return UserInfo[]
	 */
	@ApiOperation(value="전체 사용자 조회", notes = "모든 사용자를 조회합니다.")
	@RequestMapping(value= "/all", method=RequestMethod.GET)
	public UserInfo[] getAll() {	
		UserInfo[] userInfo = userService.selectUserInfoAll();
		
		return userInfo;
		//return userService.selectUserInfo();
	}
	
	/**
	 * 사용자 등록
	 * @author djchoi
	 * @return ResponseEntity<Object>
	 */
	@ApiOperation(value="사용자 등록", notes = "사용자를 등록합니다.")
	@RequestMapping(value= "/regist", method=RequestMethod.POST)
	public ResponseEntity<Object> registUser(@RequestBody String body) throws JsonParseException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = mapper.readValue(body, Map.class);
		
		HashMap<String, Object> retMap = userService.checkRegistUser(map);
		if (retMap != null) {
			retMap = new HashMap<String, Object>();
			retMap.put("reason", map.get("userId").toString() + " already exists.");
			return new ResponseEntity<Object>(retMap, HttpStatus.FOUND);
		}
		
		Map<String, String> registUserMap = new HashMap<String, String>();
		registUserMap.put("userId", map.get("userId").toString().toUpperCase());
		registUserMap.put("userName", map.get("userName").toString());
		registUserMap.put("password", map.get("password").toString());
		registUserMap.put("langType", map.get("langType").toString());
		registUserMap.put("depart", map.get("depart").toString());
		registUserMap.put("userGroup", map.get("userGroup").toString());
		
		userService.registUser(registUserMap);
		
		return new ResponseEntity<Object>(retMap, HttpStatus.OK);
	}
	
	/**
	 * 사용자 수정
	 * @author djchoi
	 * @return ResponseEntity<Object>
	 */
	@ApiOperation(value="사용자 수정", notes = "사용자를 수정합니다.")
	@RequestMapping(value= "/modify", method=RequestMethod.POST)
	public ResponseEntity<Object> modifyUser(@RequestBody String body) throws JsonParseException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = mapper.readValue(body, Map.class);
		
		HashMap<String, Object> retMap = userService.checkRegistUser(map);
		if (retMap == null) {
			retMap = new HashMap<String, Object>();
			retMap.put("reason", map.get("userId").toString() + " doesn't exist.");
			return new ResponseEntity<Object>(retMap, HttpStatus.FOUND);
		}
		
		Map<String, String> modifyUserMap = new HashMap<String, String>();
		modifyUserMap.put("userId", map.get("userId").toString());
		modifyUserMap.put("userName", map.get("userName").toString());
		modifyUserMap.put("password", map.get("password").toString());
		modifyUserMap.put("langType", map.get("langType").toString());
		modifyUserMap.put("depart", map.get("depart").toString());
		modifyUserMap.put("userGroup", map.get("userGroup").toString());
		
		userService.modifyUser(modifyUserMap);
		
		return new ResponseEntity<Object>(retMap, HttpStatus.OK);
	}
	
	/**
	 * 사용자 삭제
	 * @author djchoi
	 * @return ResponseEntity<Object>
	 */
	@ApiOperation(value="사용자 삭제", notes = "사용자를 삭제합니다.")
	@RequestMapping(value= "/delete", method=RequestMethod.POST)
	public ResponseEntity<Object> deleteUser(@RequestBody String body) throws JsonParseException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = mapper.readValue(body, Map.class);
		
		HashMap<String, Object> retMap = userService.checkRegistUser(map);
		if (retMap == null) {
			retMap = new HashMap<String, Object>();
			retMap.put("reason", map.get("userId").toString() + " doesn't exist.");
			return new ResponseEntity<Object>(retMap, HttpStatus.FOUND);
		}
		
		Map<String, String> deleteUserMap = new HashMap<String, String>();
		deleteUserMap.put("userId", map.get("userId").toString());
		
		userService.deleteUser(deleteUserMap);
		
		return new ResponseEntity<Object>(retMap, HttpStatus.OK);
	}
	
	@PostMapping(value = "/login"
			,consumes = {MediaType.APPLICATION_JSON_VALUE}
			,produces = {MediaType.APPLICATION_JSON_VALUE} )
	@ResponseBody
	//public HashMap<String, Object> doLogIn(@RequestBody String body) throws JsonParseException, IOException {
	public ResponseEntity<Object> doLogIn(@RequestBody String body) throws JsonParseException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = mapper.readValue(body, Map.class);
		
		HashMap<String, Object> retMap = userService.selectUserInfo(map);
		if (retMap == null) {
			retMap = new HashMap<String, Object>();
			retMap.put("reason", map.get("userId").toString() + " is not found.");
			return new ResponseEntity<Object>(retMap, HttpStatus.NOT_FOUND);
		}
			
		Map<String, String> updateMap = new HashMap<String, String>();
		updateMap.put("userId", retMap.get("USERID").toString());
		updateMap.put("token", retMap.get("ACCESSTOKEN").toString());
		
		userService.updateUserToken(updateMap);
		
		System.out.println("login: " + retMap);
		logger.debug("login: " + retMap);
		
		
		 //QueryExecutor 사용 예제
		  //Map<String, Object> bindVal = new HashMap<String, Object>();
		  //bindVal.put("userId", "IBS"); 
		  //bindVal.put("token", "IBS:XXXXXXXXX");
//		  List<Object> inVal = new ArrayList<Object>();
//		  inVal.add("IBS");
//		  inVal.add("IBS2");
//		  bindVal.put("userIds",inVal); 
//		  Map<String, Object> resVal = qe.selectMap("SELECT * FROM ADM_USER_INFO WHERE USER_ID IN (:userIds)", bindVal);
		
		
//		  qe.selectList("SELECT * FROM ADM_USER_INFO", new HashMap<String, Object>());
//		  Map<String, Object> bindVal = new HashMap<String, Object>();
//		  bindVal.put("userId", "IBS");
//		  qe.selectMap("SELECT * FROM ADM_USER_INFO WHERE USER_ID = :userId", bindVal);
//		  qe.seletCount("SELECT COUNT(*) FROM ADM_USER_INFO WHERE USER_ID = :userId", bindVal);
		 	 

		return new ResponseEntity<Object>(retMap, HttpStatus.OK);
	}
	
	@PostMapping(value = "/logout"
			,consumes = {MediaType.APPLICATION_JSON_VALUE}
			,produces = {MediaType.APPLICATION_JSON_VALUE} )
	@ResponseBody
	//public HashMap<String, Object> doLogIn(@RequestBody String body) throws JsonParseException, IOException {
	public ResponseEntity<Object> doLogOut(@RequestBody String body) throws JsonParseException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = mapper.readValue(body, Map.class);
			
		Map<String, String> updateMap = new HashMap<String, String>();
		updateMap.put("userId", map.get("userId").toString());
		updateMap.put("token", "");
		
		userService.updateUserToken(updateMap);
		
		System.out.println("Logout");
		
		// batch insert test
//		{
//			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
//			
//			
//			for (int i = 1; i < 11; i++) {
//				Map<String, Object> biMap = new HashMap<String, Object>();
//				
//				biMap.put("userId", "IBS" + i);
//				biMap.put("userName", "IBS" + i);
//				biMap.put("password", "IBS" + i);
//				biMap.put("langType", "ko");
//				biMap.put("depart", "SI");
//				biMap.put("token", "");
//				
//				listMap.add(biMap);
//			}
//			
//			StringBuilder sb = new StringBuilder();
//			sb.append("INSERT INTO ADM_USER_INFO \n");
//			sb.append("            ( USER_ID, USER_NAME, PASSWORD, LANGUAGE_TYPE, DEPARTMENT, TOKEN ) \n");
//			sb.append("     VALUES ( :userId, :userName, :password, :langType, :depart, :token      ) \n");
//			
//			int[] resArr = qe.batchInsert(sb.toString(), listMap);
//		}
		

		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/check"
			,consumes = {MediaType.APPLICATION_JSON_VALUE}
			,produces = {MediaType.APPLICATION_JSON_VALUE} )
	@ResponseBody
	public ResponseEntity<Object> checkAccessToken(@RequestBody String body) throws JsonParseException, IOException, ParseException {
		
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = mapper.readValue(body, Map.class);
		
		String accessToken = map.get("token").toString();
		
		boolean bAvail = TokenUtil.checkAvailToken(accessToken);
		
		if (bAvail == false) {			
			System.out.println("accessToken(" + accessToken + ") is expired.");
			HashMap<String, Object> errMap = new HashMap<String, Object>(); 
			errMap.put("reason", accessToken + " is expired.");
			return new ResponseEntity<Object>(errMap, HttpStatus.FORBIDDEN);
		}

		
		HashMap<String, Object> retMap = userService.selectUserAndRefreshToken(accessToken);
		if (retMap == null) {
			retMap = new HashMap<String, Object>();
			retMap.put("reason", accessToken + " is not valid.");
			return new ResponseEntity<Object>(retMap, HttpStatus.FORBIDDEN);
		}
		
		Map<String, String> updateMap = new HashMap<String, String>();
		updateMap.put("userId", retMap.get("USERID").toString());
		updateMap.put("token", retMap.get("ACCESSTOKEN").toString());
		
		System.out.println("checkAccessToken");
		
		userService.updateUserToken(updateMap);
		
		return new ResponseEntity<Object>(retMap, HttpStatus.OK);
	}
}
