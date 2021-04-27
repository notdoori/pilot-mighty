package com.pilot.mighty.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	 * @return ADM_MULTI_LANGUAGE 테이블의 LANG_TEXT 정보
	 */
	@ApiOperation(value="특정 다국어 정보 조회", notes = "특정 다국어 정보를 조회합니다.")
	@RequestMapping(value = "/search", method=RequestMethod.GET)
	public ResponseEntity<Object> getMultiLanguage(String keyword, String  code) {
		
		logger.debug("[getMultiLanguage] keyword: " + keyword);
		logger.debug("[getMultiLanguage] code: " + code);
		
		// Database 에 리스트 존재 여부 확인 (추가 필요)

		Map<String, String> languageMap = new HashMap<String, String>();
		
		languageMap.put("keyword", keyword);
		languageMap.put("code", code);
		
		HashMap<String, Object> retMap = multiLanguageService.selectMultiLanguageInfo(languageMap);
		
		logger.debug("LANGTEXT: " + retMap.get("LANGTEXT").toString());
		
		return new ResponseEntity<Object>(retMap.get("LANGTEXT").toString(), HttpStatus.OK);
	}
	
//	@ApiOperation(value="특정 다국어 정보 조회", notes = "특정 다국어 정보를 조회합니다.")
//	@PostMapping(value = "/search"
//			,consumes = {MediaType.APPLICATION_JSON_VALUE}
//			,produces = {MediaType.APPLICATION_JSON_VALUE} )
//	public ResponseEntity<Object> getMultiLanguage(@RequestBody String body) throws JsonParseException, IOException {
//		
//		ObjectMapper mapper = new ObjectMapper();
//		@SuppressWarnings("unchecked")
//		Map<String, Object> map = mapper.readValue(body, Map.class);
//		
//		logger.debug("keyword: " + map.get("keyword").toString());
//		logger.debug("code: " + map.get("code").toString());
//		
//		// Database 에 리스트 존재 여부 확인 (추가 필요)
//
//		Map<String, String> languageMap = new HashMap<String, String>();
//		
//		languageMap.put("keyword", map.get("keyword").toString());
//		languageMap.put("code", map.get("code").toString());
//		
//		HashMap<String, Object> retMap = multiLanguageService.selectMultiLanguageInfo(languageMap);
//		
//		logger.debug("LANGTEXT: " + retMap.get("LANGTEXT").toString());
//		
//		return new ResponseEntity<Object>(retMap.get("LANGTEXT").toString(), HttpStatus.OK);
//	}
}
