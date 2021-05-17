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
	public HashMap<String, Object> selectLanguageInfoSearch1(Map<String, String> map) {
		
		// 권한 그룹 정보 조회 요청
		return multiLanguageInfoDao.selectLanguageInfoSearch1(map);
	}
	
	@Override
	public HashMap<String, Object> selectLanguageInfoSearch2(Map<String, Object> map) {
		
		// 권한 그룹 정보 조회 요청
		return multiLanguageInfoDao.selectLanguageInfoSearch2(map);
	}
	
	@Override
	public void insertLanguageInfo(Map<String, String> map) {
		
		// 다국어 정보 추가 요청
		multiLanguageInfoDao.insertLanguageInfo(map);
	}
	
	@Override
	public void updateLanguageInfo(Map<String, String> map) {
		
		// 다국어 정보 수정 요청
		multiLanguageInfoDao.updateLanguageInfo(map);
	}
	
	@Override
	public void deleteLanguageInfo(Map<String, String> map) {
		
		// 다국어 정보 삭제 요청
		multiLanguageInfoDao.deleteLanguageInfo(map);
	}
}
