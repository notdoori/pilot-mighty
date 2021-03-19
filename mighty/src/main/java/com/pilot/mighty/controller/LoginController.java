package com.pilot.mighty.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pilot.mighty.model.UserInfo;
import com.pilot.mighty.query.QueryExecutor;
import com.pilot.mighty.service.UserService;
import com.pilot.mighty.util.TokenUtil;

@RestController
@RequestMapping("/api")
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	QueryExecutor qe;
	

	@RequestMapping(value= "/all", method=RequestMethod.GET)
	public UserInfo getAll() {	
		UserInfo userInfo = userService.selectUserInfoAll();
		
		return userInfo;
		//return userService.selectUserInfo();
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
		
		
//		qe.selectList("SELECT * FROM ADM_USER_INFO", new HashMap<String, Object>());
//		Map<String, Object> bindVal = new HashMap<String, Object>();
//		bindVal.put("userId", "IBS");
//		qe.selectMap("SELECT * FROM ADM_USER_INFO WHERE USER_ID = :userId", bindVal);
//		qe.seletCount("SELECT COUNT(*) FROM ADM_USER_INFO WHERE USER_ID = :userId", bindVal);

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

		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/user"
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
