package com.pilot.mighty.service;

import java.util.HashMap;
import java.util.Map;

import com.pilot.mighty.model.AuthInfo;

public interface AuthService {
	
	// 모든 권한 그룹 조회 요청
	AuthInfo[] selectAuthInfoAll();
	
	// 권한 그룹 정보 조회 요청
	HashMap<String, Object> selectAuthInfoSearch(Map<String, Object> map);
	
	// 권한 그룹 정보 추가 요청
	void insertAuthInfo(Map<String, String> map);
	
	// 권한 그룹 정보 수정 요청
	void updateAuthInfo(Map<String, String> map);
	
	// 권한 그룹 정보 삭제 요청
	void deleteAuthInfo(Map<String, String> map);
	
	// 특정 권한 그룹 정보 조회 요청
	HashMap<String, Object> selectAuthInfoCheck(Map<String, Object> map);
	
	// (Swagger 프로그램) 사용자 그룹 정보 추가 요청
	void insertAuthInfoSwagger(String roleId, String roleDesc);
		
	// (Swagger 프로그램) 사용자 그룹 정보 수정 요청
	void updateAuthInfoSwagger(String roleId, String roleDesc);
	
	// (Swagger 프로그램) 사용자 그룹 정보 삭제 요청
	void deleteAuthInfoSwagger(String roleId);
}
