package com.pilot.mighty.dao;

import java.util.HashMap;
import java.util.Map;

import com.pilot.mighty.model.AuthInfo;

public interface MultiLanguageInfoDao {

	// 다국어
	HashMap<String, Object> selectMultiLanguageInfo(Map<String, String> map);
}
