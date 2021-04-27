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
	 * 모든 권한 그룹 조회 요청 (SELECT)
	 * @author thkim
	 * @return AuthInfo 리스트 정보
	 */
	@ApiOperation(value="모든 권한 그룹 조회", notes = "모든 권한 그룹을 조회합니다.")
	@RequestMapping(value= "/all", method=RequestMethod.GET)
	public AuthInfo[] getAuthAll() {
		
		AuthInfo[] authInfo = authService.selectAuthInfoAll();
		
		return authInfo;
	}
	
	/**
	 * 권한 그룹 정보 조회 요청 (SELECT)
	 * @author thkim
	 * @return AuthInfo 정보
	 */
	@ApiOperation(value="권한 그룹 정보 조회", notes = "권한 그룹 정보를 조회합니다.")
	@PostMapping(value = "/search"
			,consumes = {MediaType.APPLICATION_JSON_VALUE}
			,produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<Object> getAuthSearch(@RequestBody String body) throws JsonParseException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = mapper.readValue(body, Map.class);
		
		logger.debug("roleId: " + map.get("roleId").toString());
		logger.debug("roleDesc: " + map.get("roleDesc").toString());
		
		HashMap<String, Object> retMap = authService.selectAuthInfoSearch(map);
		
		// Database 에 리스트 존재 여부 확인
		if (retMap == null) {
			logger.debug(map.get("roleId").toString() + " is not found.");
			return null;
		}
		
		logger.debug("ROLEID: " + retMap.get("ROLEID").toString());
		logger.debug("ROLEDESC: " + retMap.get("ROLEDESC").toString());
		
		AuthInfo authInfo = new AuthInfo();
		
		authInfo.setRoleId(retMap.get("ROLEID").toString());
		authInfo.setRoleDesc(retMap.get("ROLEDESC").toString());
		
		return new ResponseEntity<Object>(authInfo, HttpStatus.OK);
}
	
	/**
	 * 권한 그룹 정보 추가 요청 (INSERT)
	 * @author thkim
	 * @return HttpStatus 정보
	 */
	@ApiOperation(value="권한 그룹 정보 추가", notes = "권한 그룹 정보를 추가합니다.")
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
		
		HashMap<String, Object> retMap = authService.selectAuthInfoCheck(map);
		
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
	 * 권한 그룹 정보 수정 요청 (UPDATE)
	 * @author thkim
	 * @return HttpStatus 정보
	 */
	@ApiOperation(value="권한 그룹 정보 수정", notes = "권한 그룹 정보를 수정합니다.")
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
		
		HashMap<String, Object> retMap = authService.selectAuthInfoCheck(map);
		
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
	 * 권한 그룹 정보 삭제 요청 (DELETE)
	 * @author thkim
	 * @return HttpStatus 정보
	 */
	@ApiOperation(value="권한 그룹 정보 삭제", notes = "권한 그룹 정보를 삭제합니다.")
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
		
		HashMap<String, Object> retMap = authService.selectAuthInfoCheck(map);
		
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
	
	/**
	 * (Swagger 전용) 권한 그룹 정보 추가 요청 (INSERT)
	 * @author thkim
	 * @return HttpStatus 정보
	 */
	@ApiOperation(value="권한 그룹 정보 추가", notes = "권한 그룹 정보를 추가합니다.")
	@RequestMapping(value= "/addSwagger", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> authAddSwagger(@RequestParam String roleId, String roleDesc) throws JsonParseException, IOException {
		// Swagger 프로그램
		
		logger.debug("roleId : " + roleId);
		logger.debug("roleDesc : " + roleDesc);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("roleId",  roleId);
		
		HashMap<String, Object> retMap = authService.selectAuthInfoCheck(map);
		
		// Database 에 리스트 존재 여부 확인
		if (retMap != null) {
			retMap = new HashMap<String, Object>();
			retMap.put("reason", map.get("roleId").toString() + " is already existed.");
			return new ResponseEntity<Object>(retMap, HttpStatus.FOUND);
		}
		
		authService.insertAuthInfoSwagger(roleId, roleDesc);
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	/**
	 * (Swagger 전용) 권한 그룹 정보 수정 요청 (UPDATE)
	 * @author thkim
	 * @return HttpStatus 정보
	 */
	@ApiOperation(value="권한 그룹 정보 수정", notes = "권한 그룹 정보를 수정합니다.")
	@RequestMapping(value= "/modifySwagger", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> groupModify(@RequestParam String roleId, String roleDesc) throws JsonParseException, IOException {
		// Swagger 프로그램
		
		logger.debug("roleId : " + roleId);
		logger.debug("roleDesc : " + roleDesc);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("roleId",  roleId);
		
		HashMap<String, Object> retMap = authService.selectAuthInfoCheck(map);
		
		// Database 에 리스트 존재 여부 확인
		if (retMap == null) {
			retMap = new HashMap<String, Object>();
			retMap.put("reason", map.get("roleId").toString() + " is not found.");
			return new ResponseEntity<Object>(retMap, HttpStatus.NOT_FOUND);
		}
		
		authService.updateAuthInfoSwagger(roleId, roleDesc);
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	/**
	 * (Swagger 전용) 권한 그룹 정보 삭제 요청 (DELETE)
	 * @author thkim
	 * @return HttpStatus 정보
	 */
	@ApiOperation(value="권한 그룹 정보 삭제", notes = "권한 그룹 정보를 삭제합니다.")
	@RequestMapping(value= "/deleteSwagger", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> groupDeleteSwagger(@RequestParam String roleId) throws JsonParseException, IOException {
		// Swagger 프로그램
		
		logger.debug("roleId : " + roleId);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("roleId",  roleId);
		
		HashMap<String, Object> retMap = authService.selectAuthInfoCheck(map);
		
		// Database 에 리스트 존재 여부 확인
		if (retMap == null) {
			retMap = new HashMap<String, Object>();
			retMap.put("reason", map.get("roleId").toString() + " is not found.");
			return new ResponseEntity<Object>(retMap, HttpStatus.NOT_FOUND);
		}
		
		authService.deleteAuthInfoSwagger(roleId);
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
