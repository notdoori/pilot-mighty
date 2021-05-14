package com.pilot.mighty.controller;

import java.awt.List;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.jdbc.Null;
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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pilot.mighty.query.QueryExecutor;
import com.pilot.mighty.util.TokenUtil;
import com.pilot.mighty.service.SysCodeService;
import com.pilot.mighty.model.SysCode;
import com.pilot.mighty.model.SysCodeData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"5. System Code"})
@RestController
@RequestMapping("/api/syscode")
public class SysCodeController {
	
	@Autowired
	SysCodeService sysCodeService;
	
	@Autowired
	QueryExecutor qe;
	
	private final Logger logger = LogManager.getLogger(SysCodeController.class);
	

	/**
	 * 전체 시스템 코드 조회 요청
	 * @author djchoi
	 * @return SysCode[]
	 */
	@ApiOperation(value="전체 시스템 코드 조회", notes = "모든 시스템 코드를 조회합니다.")
	@RequestMapping(value= "/syscode/all", method=RequestMethod.GET)
	public SysCode[] getSysCodeAll() {	
		SysCode[] sysCode = sysCodeService.selectSysCodeAll();
		
		return sysCode;
	}
	
	/**
	 * 전체 시스템 코드 데이터 조회 요청
	 * @author djchoi
	 * @return SysCode[]
	 */
	@ApiOperation(value="전체 시스템 코드 데이터 조회", notes = "모든 시스템 코드 데이터를 조회합니다.")
	@RequestMapping(value= "/syscodedata//all", method=RequestMethod.GET)
	public SysCodeData[] getSysCodeDataAll(@RequestParam String tableName) {	
		SysCodeData[] sysCodeData = sysCodeService.selectSysCodeDataAll(tableName);
		
		return sysCodeData;
	}
	
	/**
	 * 시스템 코드 조회 요청
	 * @author djchoi
	 * @return SysCode[]
	 */
	@ApiOperation(value="시스템 코드 조회", notes = "시스템 코드를 조회합니다.")
	@RequestMapping(value= "/syscode/list", method=RequestMethod.GET)
	public SysCode[] getSysCode(@RequestParam String tableName, String use) {	
		SysCode[] sysCode = sysCodeService.selectSysCode(tableName, use);
		
		return sysCode;
	}
	
	/**
	 * 시스템 코드 데이터 조회 요청
	 * @author djchoi
	 * @return SysCode[]
	 */
	@ApiOperation(value="시스템 코드 데이터 조회", notes = "시스템 코드 데이터를 조회합니다.")
	@RequestMapping(value= "syscodedata/list", method=RequestMethod.GET)
	public SysCodeData[] getSysCodeData(@RequestParam String tableName, String use) {	
		SysCodeData[] sysCode = sysCodeService.selectSysCodeData(tableName, use);
		
		return sysCode;
	}
	
	/**
	 * 시스템 코드 등록
	 * @author djchoi
	 * @return ResponseEntity<Object>
	 */
	@ApiOperation(value="시스템 코드 등록", notes = "시스템 코드를 등록합니다.")
	@RequestMapping(value= "/syscode/regist", method=RequestMethod.POST)
	public ResponseEntity<Object> registSysCode(@RequestBody String body) throws JsonParseException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = mapper.readValue(body, Map.class);
		
		HashMap<String, Object> retMap = sysCodeService.checkRegistSysCode(map);
		if (retMap != null) {
			retMap = new HashMap<String, Object>();
			retMap.put("reason", map.get("system code").toString() + " already exists.");
			return new ResponseEntity<Object>(retMap, HttpStatus.FOUND);
		}
		
		Map<String, String> registSysCodeMap = new HashMap<String, String>();
		registSysCodeMap.put("tableName", map.get("tableName").toString());
		registSysCodeMap.put("tableDesc", map.get("tableDesc") == null ? "" : map.get("tableDesc").toString());
		registSysCodeMap.put("use", map.get("use") == null ? "" : map.get("use").toString());
		
		sysCodeService.registSysCode(registSysCodeMap);
		
		return new ResponseEntity<Object>(retMap, HttpStatus.OK);
	}
	
	/**
	 * 시스템 코드 수정
	 * @author djchoi
	 * @return ResponseEntity<Object>
	 */
	@ApiOperation(value="시스템 코드 수정", notes = "시스템 코드를 수정합니다.")
	@RequestMapping(value= "/syscode/modify", method=RequestMethod.POST)
	public ResponseEntity<Object> modifySysCode(@RequestBody String body) throws JsonParseException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = mapper.readValue(body, Map.class);
		
		HashMap<String, Object> retMap = sysCodeService.checkRegistSysCode(map);
		if (retMap == null) {
			retMap = new HashMap<String, Object>();
			retMap.put("reason", map.get("system code").toString() + " doesn't exist.");
			return new ResponseEntity<Object>(retMap, HttpStatus.FOUND);
		}
		
		Map<String, String> modifySysCodeMap = new HashMap<String, String>();
		modifySysCodeMap.put("tableName", map.get("tableName").toString());
		modifySysCodeMap.put("tableDesc", map.get("tableDesc") == null ? "" : map.get("tableDesc").toString());
		modifySysCodeMap.put("use", map.get("use") == null ? "" : map.get("use").toString());
		
		sysCodeService.modifySysCode(modifySysCodeMap);
		
		return new ResponseEntity<Object>(retMap, HttpStatus.OK);
	}
	
	/**
	 * 시스템 코드 삭제
	 * @author djchoi
	 * @return ResponseEntity<Object>
	 */
	@ApiOperation(value="시스템 코드 삭제", notes = "시스템 코드를 삭제합니다.")
	@RequestMapping(value= "/syscode/delete", method=RequestMethod.POST)
	public ResponseEntity<Object> deleteSysCode(@RequestBody String body) throws JsonParseException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = mapper.readValue(body, Map.class);
		
		HashMap<String, Object> retMap = sysCodeService.checkRegistSysCode(map);
		if (retMap == null) {
			retMap = new HashMap<String, Object>();
			retMap.put("reason", map.get("system code").toString() + " doesn't exist.");
			return new ResponseEntity<Object>(retMap, HttpStatus.FOUND);
		}
		
		Map<String, String> deleteSysCodeMap = new HashMap<String, String>();
		deleteSysCodeMap.put("tableName", map.get("tableName").toString());
		
		sysCodeService.deleteSysCode(deleteSysCodeMap);
		sysCodeService.deleteRelatedSysCodeData(deleteSysCodeMap);
		
		return new ResponseEntity<Object>(retMap, HttpStatus.OK);
	}
	
	/**
	 * 시스템 코드 데이터 등록
	 * @author djchoi
	 * @return ResponseEntity<Object>
	 */
	@ApiOperation(value="시스템 코드 데이터 등록", notes = "시스템 코드 데이터를 등록합니다.")
	@RequestMapping(value= "/syscodedata/regist", method=RequestMethod.POST)
	public ResponseEntity<Object> registSysCodeData(@RequestBody String body) throws JsonParseException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		list = mapper.readValue(body, new TypeReference<ArrayList<HashMap<String, Object>>>() {});
		
		for(int i=0; i<list.size(); i++)
		{
			Map<String, Object> map = list.get(i);
			HashMap<String, Object> retMap = sysCodeService.checkRegistSysCodeData(map);
		
			if (retMap != null) {
				retMap = new HashMap<String, Object>();
				retMap.put("reason", map.get("system code Data").toString() + " already exists.");
				return new ResponseEntity<Object>(retMap, HttpStatus.FOUND);
			}
			
			Map<String, String> registSysCodeDataMap = new HashMap<String, String>();
			registSysCodeDataMap.put("tableName", map.get("tableName").toString());
			registSysCodeDataMap.put("codeName", map.get("codeName").toString());
			registSysCodeDataMap.put("codeSeq", map.get("codeSeq") == null ? "" : map.get("codeSeq").toString());
			registSysCodeDataMap.put("codeDesc", map.get("codeDesc") == null ? "" : map.get("codeDesc").toString());
			
			sysCodeService.registSysCodeData(registSysCodeDataMap);
		}
		
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}
	
	/**
	 * 시스템 코드 데이터 수정
	 * @author djchoi
	 * @return ResponseEntity<Object>
	 */
	@ApiOperation(value="시스템 코드 데이터 수정", notes = "시스템 코드 데이터를 수정합니다.")
	@RequestMapping(value= "/syscodedata/modify", method=RequestMethod.POST)
	public ResponseEntity<Object> modifySysCodeData(@RequestBody String body) throws JsonParseException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = mapper.readValue(body, Map.class);
		
		HashMap<String, Object> retMap = sysCodeService.checkRegistSysCodeData(map);
		if (retMap == null) {
			retMap = new HashMap<String, Object>();
			retMap.put("reason", map.get("system code").toString() + " doesn't exist.");
			return new ResponseEntity<Object>(retMap, HttpStatus.FOUND);
		}
		
		Map<String, String> modifySysCodeDataMap = new HashMap<String, String>();
		modifySysCodeDataMap.put("tableName", map.get("tableName").toString());
		modifySysCodeDataMap.put("codeName", map.get("codeName").toString());
		modifySysCodeDataMap.put("codeSeq", map.get("codeSeq") == null ? "" : map.get("codeSeq").toString());
		modifySysCodeDataMap.put("codeDesc", map.get("codeDesc") == null ? "" : map.get("codeDesc").toString());
		
		sysCodeService.modifySysCodeData(modifySysCodeDataMap);
		
		return new ResponseEntity<Object>(retMap, HttpStatus.OK);
	}
	
	/**
	 * 시스템 코드 데이터 삭제
	 * @author djchoi
	 * @return ResponseEntity<Object>
	 */
	@ApiOperation(value="시스템 코드 데이터 삭제", notes = "시스템 코드 데이터를 삭제합니다.")
	@RequestMapping(value= "/syscodedata/delete", method=RequestMethod.POST)
	public ResponseEntity<Object> deleteSysCodeData(@RequestBody String body) throws JsonParseException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = mapper.readValue(body, Map.class);
		
		HashMap<String, Object> retMap = sysCodeService.checkRegistSysCodeData(map);
		if (retMap == null) {
			retMap = new HashMap<String, Object>();
			retMap.put("reason", map.get("system code").toString() + " doesn't exist.");
			return new ResponseEntity<Object>(retMap, HttpStatus.FOUND);
		}
		
		Map<String, String> deleteSysCodeDataMap = new HashMap<String, String>();
		deleteSysCodeDataMap.put("tableName", map.get("tableName").toString());
		deleteSysCodeDataMap.put("codeName", map.get("codeName").toString());
		
		sysCodeService.deleteSysCodeData(deleteSysCodeDataMap);
		
		return new ResponseEntity<Object>(retMap, HttpStatus.OK);
	}
}
