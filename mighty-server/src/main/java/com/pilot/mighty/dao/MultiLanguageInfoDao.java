package com.pilot.mighty.dao;

import java.util.HashMap;
import java.util.Map;

import com.pilot.mighty.model.MultiLanguageInfo;

public interface MultiLanguageInfoDao {

	// 모든 다국어 정보 조회 요청
	MultiLanguageInfo[] selectLanguageInfoAll();
	
	// 특정 다국어 정보 조회 요청
	HashMap<String, Object> selectLanguageInfoSearch1(Map<String, String> map);
	
	// 특정 다국어 정보 조회 요청
	HashMap<String, Object> selectLanguageInfoSearch2(Map<String, Object> map);
	
	// 다국어 정보 추가 요청
	void insertLanguageInfo(Map<String, String> map);
	
	// 다국어 정보 수정 요청
	void updateLanguageInfo(Map<String, String> map);
		
	// 다국어 정보 삭제 요청
	void deleteLanguageInfo(Map<String, String> map);
}
