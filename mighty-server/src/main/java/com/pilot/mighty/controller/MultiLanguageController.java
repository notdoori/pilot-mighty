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
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pilot.mighty.query.QueryExecutor;
import com.pilot.mighty.service.MultiLanguageService;

@RestController
public class MultiLanguageController {
	
	@Autowired
	MultiLanguageService multiLanguageService;
	
	@Autowired
	QueryExecutor qe;
	
	private final Logger logger = LogManager.getLogger(MultiLanguageController.class);
	
	@PostMapping(value = "api/language"
			,consumes = {MediaType.APPLICATION_JSON_VALUE}
			,produces = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<Object> getMultiLanguage(@RequestBody String body) throws JsonParseException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = mapper.readValue(body, Map.class);
		
		logger.debug("keyword: " + map.get("keyword").toString());
		logger.debug("code: " + map.get("code").toString());
		
		// Database 에 리스트 존재 여부 확인 (추가 필요)

		Map<String, String> languageMap = new HashMap<String, String>();
		
		languageMap.put("keyword", map.get("keyword").toString());
		languageMap.put("code", map.get("code").toString());
		
		HashMap<String, Object> retMap = multiLanguageService.selectMultiLanguageInfo(languageMap);
		
		logger.debug("LANGTEXT: " + retMap.get("LANGTEXT").toString());
		
		return new ResponseEntity<Object>(retMap.get("LANGTEXT").toString(), HttpStatus.OK);
	}
}
