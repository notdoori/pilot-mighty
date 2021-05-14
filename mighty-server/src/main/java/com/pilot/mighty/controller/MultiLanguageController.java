package com.pilot.mighty.controller;

import java.io.IOException;
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
import com.pilot.mighty.model.MultiLanguageInfo;
import com.pilot.mighty.query.QueryExecutor;
import com.pilot.mighty.service.MultiLanguageService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/language")
public class MultiLanguageController {
	
	@Autowired
	MultiLanguageService multiLanguageService;
	
	@Autowired
	QueryExecutor qe;
	
	private final Logger logger = LogManager.getLogger(MultiLanguageController.class);
	
	/**
	 * 모든 다국어 정보 조회 요청 (SELECT)
	 * @author thkim
	 * @return MultiLanguageInfo
	 */
	@ApiOperation(value="모든 다국어 정보 조회", notes = "모든 다국어 정보를 조회합니다.")
	@RequestMapping(value= "/all", method=RequestMethod.GET)
	public MultiLanguageInfo[] getLanguageAll() {
		
		MultiLanguageInfo[] multiLanguageInfo = multiLanguageService.selectLanguageInfoAll();
		
		return multiLanguageInfo;
	}
	
	/**
	 * 특정 다국어 정보 조회 요청 (SELECT)
	 * @author thkim
	 * @return MultiLanguageInfo
	 */
	@ApiOperation(value="특정 다국어 정보 조회", notes = "특정 다국어 정보를 조회합니다.")
	@RequestMapping(value= "/search", method=RequestMethod.GET)
	public ResponseEntity<Object> getLanguageSearch(String langCode, String langTyp) {
		
		Map<String, String> map =  new HashMap<String, String>();
		
		map.put("langCode", langCode);
		map.put("langTyp", langTyp);
		
		logger.debug("langCode: " + map.get("langCode").toString());
		logger.debug("langTyp: " + map.get("langTyp").toString());

		HashMap<String, Object> retMap = multiLanguageService.selectLanguageInfoSearch(map);
		
		// Database 에 리스트 존재 여부 확인
		if (retMap == null) {
			logger.debug(langCode + " is not found.");
			return null;
		}
		
		logger.debug("LANGCODE: " + retMap.get("LANGCODE").toString());
		logger.debug("LANGTYP: " + retMap.get("LANGTYP").toString());
		logger.debug("LANGDATA: " + retMap.get("LANGDATA").toString());
		
		MultiLanguageInfo multiLanguageInfo = new MultiLanguageInfo();
		
		multiLanguageInfo.setLangCode(retMap.get("LANGCODE").toString());
		multiLanguageInfo.setLangTyp(retMap.get("LANGTYP").toString());
		multiLanguageInfo.setLangData(retMap.get("LANGDATA").toString());
		
		return new ResponseEntity<Object>(multiLanguageInfo, HttpStatus.OK);
	}

	/**
	 * 다국어 정보 저장 요청 (INSERT)
	 * @author thkim
	 * @return HttpStatus 정보
	 */
	@ApiOperation(value="다국어 정보 저장", notes = "다국어 정보를 저장합니다.")
	@PostMapping(value = "/save"
			,consumes = {MediaType.APPLICATION_JSON_VALUE}
			,produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<Object> languageSave(@RequestBody String body) throws JsonParseException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = mapper.readValue(body, Map.class);
		
		logger.debug("langCode: " + map.get("langCode").toString());
		logger.debug("langKo: " + map.get("langKo").toString());
		logger.debug("langEn: " + map.get("langEn").toString());
		logger.debug("langCn: " + map.get("langCn").toString());
		logger.debug("langVn: " + map.get("langVn").toString());
		
//		HashMap<String, Object> retMap = authService.selectAuthInfoCheck(map);
//		
//		// Database 에 리스트 존재 여부 확인
//		if (retMap != null) {
//			retMap = new HashMap<String, Object>();
//			retMap.put("reason", map.get("roleId").toString() + " is already existed.");
//			return new ResponseEntity<Object>(retMap, HttpStatus.FOUND);
//		}
//		
//		Map<String, String> insertMap = new HashMap<String, String>();
//		
//		insertMap.put("roleId", map.get("roleId").toString());
//		insertMap.put("roleDesc", map.get("roleDesc").toString());
//		
//		authService.insertAuthInfo(insertMap);
		
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}
	
	/**
	 * 다국어 정보 수정 요청 (UPDATE)
	 * @author thkim
	 * @return HttpStatus 정보
	 */
	@ApiOperation(value="다국어 정보 수정", notes = "다국어 정보를 수정합니다.")
	@PostMapping(value = "/modify"
			,consumes = {MediaType.APPLICATION_JSON_VALUE}
			,produces = {MediaType.APPLICATION_JSON_VALUE} )
	@ResponseBody
	public ResponseEntity<Object> authModify(@RequestBody String body) throws JsonParseException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = mapper.readValue(body, Map.class);
		
		logger.debug("langCode: " + map.get("langCode").toString());
		logger.debug("langKo: " + map.get("langKo").toString());
		logger.debug("langEn: " + map.get("langEn").toString());
		logger.debug("langCn: " + map.get("langCn").toString());
		logger.debug("langVn: " + map.get("langVn").toString());
		
//		HashMap<String, Object> retMap = authService.selectAuthInfoCheck(map);
//		
//		// Database 에 리스트 존재 여부 확인
//		if (retMap == null) {
//			retMap = new HashMap<String, Object>();
//			retMap.put("reason", map.get("roleId").toString() + " is not found.");
//			return new ResponseEntity<Object>(retMap, HttpStatus.NOT_FOUND);
//		}
//		
//		Map<String, String> updateMap = new HashMap<String, String>();
//		
//		updateMap.put("roleId", map.get("roleId").toString());
//		updateMap.put("roleDesc", map.get("roleDesc").toString());
//		
//		authService.updateAuthInfo(updateMap);
		
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}
	
	/**
	 * 다국어 정보 저장 삭제 요청 (DELETE)
	 * @author thkim
	 * @return HttpStatus 정보
	 */
	@ApiOperation(value="다국어 정보 삭제", notes = "다국어 정보를 삭제합니다.")
	@PostMapping(value = "/delete"
			,consumes = {MediaType.APPLICATION_JSON_VALUE}
			,produces = {MediaType.APPLICATION_JSON_VALUE} )
	@ResponseBody
	public ResponseEntity<Object> languageDelete(@RequestBody String body) throws JsonParseException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = mapper.readValue(body, Map.class);
		
		logger.debug("langCode: " + map.get("langCode").toString());
		
//		HashMap<String, Object> retMap = authService.selectAuthInfoCheck(map);
//		
//		// Database 에 리스트 존재 여부 확인
//		if (retMap == null) {
//			retMap = new HashMap<String, Object>();
//			retMap.put("reason", map.get("roleId").toString() + " is not found.");
//			return new ResponseEntity<Object>(retMap, HttpStatus.NOT_FOUND);
//		}
//		
//		Map<String, String> deleteMap = new HashMap<String, String>();
//		
//		deleteMap.put("roleId", map.get("roleId").toString());
//		deleteMap.put("roleDesc", map.get("roleDesc").toString());
//		
//		authService.deleteAuthInfo(deleteMap);
		
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}
}