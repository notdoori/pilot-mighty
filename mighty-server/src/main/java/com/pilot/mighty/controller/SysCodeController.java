package com.pilot.mighty.controller;

import java.io.IOException;
import java.text.ParseException;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pilot.mighty.model.SysCode;
import com.pilot.mighty.query.QueryExecutor;
import com.pilot.mighty.service.SysCodeService;
import com.pilot.mighty.util.TokenUtil;

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
	@RequestMapping(value= "/all", method=RequestMethod.GET)
	public SysCode[] getAll(@RequestParam String tableName, String use) {	
		SysCode[] sysCode = sysCodeService.selectSysCodeAll(tableName, use);
		
		return sysCode;
	}
}
