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
import com.pilot.mighty.model.GroupInfo;
import com.pilot.mighty.query.QueryExecutor;
import com.pilot.mighty.service.GroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"1. Group"})
@RestController
@RequestMapping("/api/group")
public class GroupController {
	
	@Autowired
	GroupService groupService;
	
	@Autowired
	QueryExecutor qe;
	
	private final Logger logger = LogManager.getLogger(GroupController.class);
	
	/**
	 * 전체 사용자 그룹 조회 요청
	 * @author thkim
	 * @return GroupInfo
	 */
	@ApiOperation(value="전체 사용자 그룹 조회", notes = "모든 사용자 그룹을 조회합니다.")
	@RequestMapping(value= "/all", method=RequestMethod.GET)
	public GroupInfo[] getGroupAll() {
		
		GroupInfo[] groupInfo = groupService.selectGroupInfoAll();
		
		return groupInfo;
	}
	
	/**
	 * 사용자 그룹 추가 요청 (INSERT)
	 * @author thkim
	 * @return GroupInfo
	 */
	@PostMapping(value = "/add"
			,consumes = {MediaType.APPLICATION_JSON_VALUE}
			,produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<Object> groupAdd(@RequestBody String body) throws JsonParseException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = mapper.readValue(body, Map.class);
		
		logger.debug("groupId: " + map.get("groupId").toString());
		logger.debug("groupDesc: " + map.get("groupDesc").toString());
		logger.debug("roleId: " + map.get("roleId").toString());
		
		HashMap<String, Object> retMap = groupService.selectGroupInfo(map);
		
		// Database 에 리스트 존재 여부 확인
		if (retMap != null) {
			retMap = new HashMap<String, Object>();
			retMap.put("reason", map.get("groupId").toString() + " is already existed.");
			return new ResponseEntity<Object>(retMap, HttpStatus.FOUND);
		}
		
		Map<String, String> insertMap = new HashMap<String, String>();
		
		insertMap.put("groupId", map.get("groupId").toString());
		insertMap.put("groupDesc", map.get("groupDesc").toString());
		insertMap.put("roleId", map.get("roleId").toString());
		
		groupService.insertGroupInfo(insertMap);
		
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}

	/**
	 * 권한 그룹 수정 요청 (UPDATE)
	 * @author thkim
	 * @return GroupInfo
	 */
	@PostMapping(value = "/modify"
			,consumes = {MediaType.APPLICATION_JSON_VALUE}
			,produces = {MediaType.APPLICATION_JSON_VALUE} )
	@ResponseBody
	public ResponseEntity<Object> groupModify(@RequestBody String body) throws JsonParseException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = mapper.readValue(body, Map.class);
		
		logger.debug("groupId: " + map.get("groupId").toString());
		logger.debug("groupDesc: " + map.get("groupDesc").toString());
		logger.debug("roleId: " + map.get("roleId").toString());
		
		HashMap<String, Object> retMap = groupService.selectGroupInfo(map);
		
		// Database 에 리스트 존재 여부 확인
		if (retMap == null) {
			retMap = new HashMap<String, Object>();
			retMap.put("reason", map.get("groupId").toString() + " is not found.");
			return new ResponseEntity<Object>(retMap, HttpStatus.NOT_FOUND);
		}
		
		Map<String, String> updateMap = new HashMap<String, String>();
		
		updateMap.put("groupId", map.get("groupId").toString());
		updateMap.put("groupDesc", map.get("groupDesc").toString());
		updateMap.put("roleId", map.get("roleId").toString());
		
		groupService.updateGroupInfo(updateMap);
		
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}
	
	/**
	 * 사용자 그룹 삭제 요청 (DELETE)
	 * @author thkim
	 * @return GroupInfo
	 */
	@PostMapping(value = "/delete"
			,consumes = {MediaType.APPLICATION_JSON_VALUE}
			,produces = {MediaType.APPLICATION_JSON_VALUE} )
	@ResponseBody
	public ResponseEntity<Object> groupDelete(@RequestBody String body) throws JsonParseException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = mapper.readValue(body, Map.class);
		
		logger.debug("groupId: " + map.get("groupId").toString());
		logger.debug("groupDesc: " + map.get("groupDesc").toString());
		logger.debug("roleId: " + map.get("roleId").toString());
		
		HashMap<String, Object> retMap = groupService.selectGroupInfo(map);
		
		// Database 에 리스트 존재 여부 확인
		if (retMap == null) {
			retMap = new HashMap<String, Object>();
			retMap.put("reason", map.get("roleId").toString() + " is not found.");
			return new ResponseEntity<Object>(retMap, HttpStatus.NOT_FOUND);
		}
		
		Map<String, String> deleteMap = new HashMap<String, String>();
		
		deleteMap.put("groupId", map.get("groupId").toString());
		deleteMap.put("groupDesc", map.get("groupDesc").toString());
		deleteMap.put("roleId", map.get("roleId").toString());
		
		groupService.deleteGroupInfo(deleteMap);
		
		return new ResponseEntity<Object>(retMap, HttpStatus.OK);
	}
}