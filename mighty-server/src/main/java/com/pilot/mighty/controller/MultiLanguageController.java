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

		HashMap<String, Object> retMap = multiLanguageService.selectLanguageInfoSearch1(map);
		
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
	@ApiOperation(value="다국어 정보 추가", notes = "다국어 정보를 추가합니다.")
	@PostMapping(value = "/add"
			,consumes = {MediaType.APPLICATION_JSON_VALUE}
			,produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<Object> languageAdd(@RequestBody String body) throws JsonParseException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = mapper.readValue(body, Map.class);
		
		String info[] = new String[] {
				map.get("langTypKo").toString(), map.get("langKo").toString(), map.get("langTypEn").toString(), map.get("langEn").toString(),
				map.get("langTypCn").toString(), map.get("langCn").toString(), map.get("langTypVn").toString(), map.get("langVn").toString()
		};
		
		logger.debug("langCode: " + map.get("langCode").toString());
		logger.debug("langTypKo: " + info[0]);
		logger.debug("langKo: " + info[1]);
		logger.debug("langTypEn: " + info[2]);
		logger.debug("langEn: " + info[3]);
		logger.debug("langTypCn: " + info[4]);
		logger.debug("langCn: " + info[5]);
		logger.debug("langTypVn: " + info[6]);
		logger.debug("langVn: " + info[7]);
		
		HashMap<String, Object> retMap = multiLanguageService.selectLanguageInfoSearch2(map);
		
		// Database 에 리스트 존재 여부 확인
		if (retMap != null) {
			retMap = new HashMap<String, Object>();
			retMap.put("reason", map.get("langCode").toString() + " is already existed.");
			return new ResponseEntity<Object>(retMap, HttpStatus.FOUND);
		}
		
		for (int i = 0; i < info.length; i+=2) {
			Map<String, String> insertMap = new HashMap<String, String>();
			
			insertMap.put("langCode", map.get("langCode").toString());
			insertMap.put("langTyp", info[i]);
			insertMap.put("langData", info[i+1]);
			
			multiLanguageService.insertLanguageInfo(insertMap);	
		}
		
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
	public ResponseEntity<Object> languageModify(@RequestBody String body) throws JsonParseException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = mapper.readValue(body, Map.class);
		
		String info[] = new String[] {
				map.get("langTypKo").toString(), map.get("langKo").toString(), map.get("langTypEn").toString(), map.get("langEn").toString(),
				map.get("langTypCn").toString(), map.get("langCn").toString(), map.get("langTypVn").toString(), map.get("langVn").toString()
		};
		
		logger.debug("langCode: " + map.get("langCode").toString());
		logger.debug("langTypKo: " + info[0]);
		logger.debug("langKo: " + info[1]);
		logger.debug("langTypEn: " + info[2]);
		logger.debug("langEn: " + info[3]);
		logger.debug("langTypCn: " + info[4]);
		logger.debug("langCn: " + info[5]);
		logger.debug("langTypVn: " + info[6]);
		logger.debug("langVn: " + info[7]);
		
//		for (int i = 0; i < info.length; i+=2) {
//			Map<String, String> updatetMap = new HashMap<String, String>();
//			
//			updatetMap.put("langCode", map.get("langCode").toString());
//			updatetMap.put("langTyp", info[i]);
//			updatetMap.put("langData", info[i+1]);
//			
//			multiLanguageService.updateLanguageInfo(updatetMap);	
//		}
		
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
		
		Map<String, String> deleteMap = new HashMap<String, String>();
		
		deleteMap.put("langCode", map.get("langCode").toString());
		
		multiLanguageService.deleteLanguageInfo(deleteMap);
		
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}
}
