package com.pilot.mighty.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pilot.mighty.controller.MultiLanguageController;
import com.pilot.mighty.dao.MultiLanguageInfoDao;
import com.pilot.mighty.model.MultiLanguageInfo;
import com.pilot.mighty.service.MultiLanguageService;

@Service
public class MultiLanguageServiceImpl implements MultiLanguageService {
	
	@Autowired
	MultiLanguageInfoDao multiLanguageInfoDao;
	
	@Override
	public HashMap<String, Object> selectMultiLanguageInfo(Map<String, String> map) {
		
		// 권한 그룹 정보 수정 요청
		return multiLanguageInfoDao.selectMultiLanguageInfo(map);
	}
}
