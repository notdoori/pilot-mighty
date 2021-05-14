package com.pilot.mighty.service;

import java.util.HashMap;
import java.util.Map;

import com.pilot.mighty.model.MultiLanguageInfo;

public interface MultiLanguageService {
	
	// 모든 다국어 정보 조회 요청
	MultiLanguageInfo[] selectLanguageInfoAll();
	
	// 특정 다국어 정보 조회 요청
	HashMap<String, Object> selectLanguageInfoSearch(Map<String, String> map);
}
