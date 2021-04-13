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
import com.pilot.mighty.query.QueryExecutor;
import com.pilot.mighty.service.AuthService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"1. Auth"})
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	AuthService authService;
	
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
	public AuthInfo[] getAuthAll() {
		AuthInfo[] authInfo = authService.selectAuthInfoAll();
		
		return authInfo;
	}
	
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
		
		logger.debug("roleId: " + map.get("roleId").toString());
		logger.debug("roleDesc: " + map.get("roleDesc").toString());
		
		HashMap<String, Object> retMap = authService.selectAuthInfo(map);
		
		// Database 에 리스트 존재 여부 확인
		if (retMap != null) {
			retMap = new HashMap<String, Object>();
			retMap.put("reason", map.get("roleId").toString() + " is already existed.");
			return new ResponseEntity<Object>(retMap, HttpStatus.FOUND);
		}
		
		Map<String, String> insertMap = new HashMap<String, String>();
		
		insertMap.put("roleId", map.get("roleId").toString());
		insertMap.put("roleDesc", map.get("roleDesc").toString());
		
		authService.insertAuthInfo(insertMap);
		
		return new ResponseEntity<Object>(map, HttpStatus.OK);
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
		
		logger.debug("roleId: " + map.get("roleId").toString());
		logger.debug("roleDesc: " + map.get("roleDesc").toString());
		
		HashMap<String, Object> retMap = authService.selectAuthInfo(map);
		
		// Database 에 리스트 존재 여부 확인
		if (retMap == null) {
			retMap = new HashMap<String, Object>();
			retMap.put("reason", map.get("roleId").toString() + " is not found.");
			return new ResponseEntity<Object>(retMap, HttpStatus.NOT_FOUND);
		}
		
		Map<String, String> updateMap = new HashMap<String, String>();
		
		updateMap.put("roleId", map.get("roleId").toString());
		updateMap.put("roleDesc", map.get("roleDesc").toString());
		
		authService.updateAuthInfo(updateMap);
		
		return new ResponseEntity<Object>(map, HttpStatus.OK);
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
		
		logger.debug("roleId: " + map.get("roleId").toString());
		logger.debug("roleDesc: " + map.get("roleDesc").toString());
		
		HashMap<String, Object> retMap = authService.selectAuthInfo(map);
		
		// Database 에 리스트 존재 여부 확인
		if (retMap == null) {
			retMap = new HashMap<String, Object>();
			retMap.put("reason", map.get("roleId").toString() + " is not found.");
			return new ResponseEntity<Object>(retMap, HttpStatus.NOT_FOUND);
		}
		
		Map<String, String> deleteMap = new HashMap<String, String>();
		
		deleteMap.put("roleId", map.get("roleId").toString());
		deleteMap.put("roleDesc", map.get("roleDesc").toString());
		
		authService.deleteAuthInfo(deleteMap);
		
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}
}
