package com.pilot.mighty.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pilot.mighty.controller.MultiLanguageController;
import com.pilot.mighty.dao.MultiLanguageInfoDao;
import com.pilot.mighty.model.AuthInfo;
import com.pilot.mighty.model.MultiLanguageInfo;
import com.pilot.mighty.service.MultiLanguageService;

@Service
public class MultiLanguageServiceImpl implements MultiLanguageService {
	
	@Autowired
	MultiLanguageInfoDao multiLanguageInfoDao;
	
	@Override
	public MultiLanguageInfo[] selectLanguageInfoAll() {
		
		// 모든 권한 그룹 조회 요청
		MultiLanguageInfo[] multiLanguageInfo = multiLanguageInfoDao.selectLanguageInfoAll();
		
		return multiLanguageInfo;
	}
	
	@Override
	public HashMap<String, Object> selectMultiLanguageInfo(Map<String, String> map) {
		
		// 특정 다국어 정보 조회
		return multiLanguageInfoDao.selectMultiLanguageInfo(map);
	}
}
