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
import com.pilot.mighty.model.GroupInfo;
import com.pilot.mighty.model.UserInfo;
import com.pilot.mighty.query.QueryExecutor;
import com.pilot.mighty.service.GroupService;
import com.pilot.mighty.service.UserService;
import com.pilot.mighty.util.TokenUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@Api(tags = {"1. User"})
@RestController
@RequestMapping("/api/group")
public class GroupController {
	
	@Autowired
	UserService userService;
//	GroupService groupService;
	
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
	public UserInfo[] getUserAll() {
		
		UserInfo[] userInfo = userService.selectUserInfoAll();
		
		return userInfo;
	}
//	public GroupInfo[] getGroupAll() {
//		
//		GroupInfo[] groupInfo = groupService.selectGroupInfoAll();
//		
//		return groupInfo;
//	}
	
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
		
		return null;
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
		
		return null;
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
		
		return null;
	}
}
