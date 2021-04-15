package com.pilot.mighty.service;

import java.util.HashMap;
import java.util.Map;

import com.pilot.mighty.model.AuthInfo;

public interface AuthService {
	
	// 모든 권한 그룹 리스트 조회
	AuthInfo[] selectAuthInfoAll();
	
	// 권한 그룹 정보 조회
	HashMap<String, Object> selectAuthInfoSearch(Map<String, Object> map);
	
	// 권한 그룹 리스트 추가
	void insertAuthInfo(Map<String, String> map);
	
	// 특정 권한 그룹 리스트 설명 업데이트
	void updateAuthInfo(Map<String, String> map);
	
	// 특정 권한 권한 그룹 리스트 삭제
	void deleteAuthInfo(Map<String, String> map);
	
	// 특정 권한 그룹 정보 조회
	HashMap<String, Object> selectAuthInfoCheck(Map<String, Object> map);
}
