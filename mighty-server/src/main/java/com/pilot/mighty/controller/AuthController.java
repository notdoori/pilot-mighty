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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pilot.mighty.model.AuthInfo;
import com.pilot.mighty.model.UserInfo;
import com.pilot.mighty.query.QueryExecutor;
import com.pilot.mighty.service.AuthService;
import com.pilot.mighty.service.UserService;
import com.pilot.mighty.util.TokenUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@Api(tags = {"1. User"})
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	UserService userService;
//	AuthService authService;
	
	@Autowired
	QueryExecutor qe;
	
	private final Logger logger = LogManager.getLogger(AuthController.class);
	
	/**
	 * 전체 권한 그룹 조회 요청
	 * @author thkim
	 * @return AuthInfo
	 */
	@ApiOperation(value="전체 권한 그룹 조회", notes = "모든 권한 그룹을 조회합니다.")
	@RequestMapping(value= "/all", method=RequestMethod.GET)
	public UserInfo[] getUserAll() {
		
		logger.debug("[AuthController] TEST-001");
		
		UserInfo[] userInfo = userService.selectUserInfoAll();
		
		logger.debug("[AuthController] TEST-002");
		
		return userInfo;
	}
//	public AuthInfo[] getAuthAll() {
//		
//		logger.debug("[AuthController] TEST01");
//		
//		AuthInfo[] authInfo = authService.selectAuthInfoAll();
//		
//		logger.debug("[AuthController] TEST04");
//		
//		return authInfo;
//	}
	
	/**
	 * 권한 그룹 추가 요청 (INSERT)
	 * @author thkim
	 * @return AuthInfo
	 */
	@PostMapping(value = "/add"
			,consumes = {MediaType.APPLICATION_JSON_VALUE}
			,produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<Object> authAdd(@RequestBody String body) throws JsonParseException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = mapper.readValue(body, Map.class);
		
		logger.debug("ROLE_ID: " + map.get("ROLE_ID").toString());
		logger.debug("ROLE_DESC: " + map.get("ROLE_DESC").toString());
		
		return null;
	}

	/**
	 * 권한 그룹 수정 요청 (UPDATE)
	 * @author thkim
	 * @return AuthInfo
	 */
	@PostMapping(value = "/modify"
			,consumes = {MediaType.APPLICATION_JSON_VALUE}
			,produces = {MediaType.APPLICATION_JSON_VALUE} )
	@ResponseBody
	public ResponseEntity<Object> authModify(@RequestBody String body) throws JsonParseException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = mapper.readValue(body, Map.class);
		
		logger.debug("ROLE_ID: " + map.get("ROLE_ID").toString());
		logger.debug("ROLE_DESC: " + map.get("ROLE_DESC").toString());
		
		return null;
	}
	
	/**
	 * 권한 그룹 삭제 요청 (DELETE)
	 * @author thkim
	 * @return AuthInfo
	 */
	@PostMapping(value = "/delete"
			,consumes = {MediaType.APPLICATION_JSON_VALUE}
			,produces = {MediaType.APPLICATION_JSON_VALUE} )
	@ResponseBody
	public ResponseEntity<Object> authDelete(@RequestBody String body) throws JsonParseException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = mapper.readValue(body, Map.class);
		
		logger.debug("ROLE_ID: " + map.get("ROLE_ID").toString());
		logger.debug("ROLE_DESC: " + map.get("ROLE_DESC").toString());
		
		return null;
	}
}
