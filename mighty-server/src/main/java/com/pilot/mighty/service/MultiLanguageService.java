package com.pilot.mighty.service;

import java.util.HashMap;
import java.util.Map;

import com.pilot.mighty.model.MultiLanguageInfo;

public interface MultiLanguageService {
	
	// 다국어
	HashMap<String, Object> selectMultiLanguageInfo(Map<String, String> map);
}
