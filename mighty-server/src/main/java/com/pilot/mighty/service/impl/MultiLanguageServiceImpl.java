package com.pilot.mighty.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pilot.mighty.dao.MultiLanguageInfoDao;
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
	public HashMap<String, Object> selectLanguageInfoSearch(Map<String, String> map) {
		
		// 권한 그룹 정보 조회 요청
		return multiLanguageInfoDao.selectLanguageInfoSearch(map);
	}
}
