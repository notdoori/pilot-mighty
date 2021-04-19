package com.pilot.mighty.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pilot.mighty.controller.AuthController;
import com.pilot.mighty.dao.AuthInfoDao;
import com.pilot.mighty.model.AuthInfo;
import com.pilot.mighty.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	AuthInfoDao authInfoDao;
	
//	private final Logger logger = LogManager.getLogger(AuthController.class);

	@Override
	public AuthInfo[] selectAuthInfoAll() {
		
		// 모든 권한 그룹 조회 요청
		AuthInfo[] authInfo = authInfoDao.selectAuthInfoAll();
		
		return authInfo;
	}
	
	@Override
	public HashMap<String, Object> selectAuthInfoSearch(Map<String, Object> map) {
		
		// 권한 그룹 정보 조회 요청
		return authInfoDao.selectAuthInfoSearch(map);
	}
	
	@Override
	public void insertAuthInfo(Map<String, String> map) {
		
		// 권한 그룹 정보 추가 요청
		authInfoDao.insertAuthInfo(map);
	}
	
	@Override
	public void updateAuthInfo(Map<String, String> map) {
		
		// 권한 그룹 정보 수정 요청
		authInfoDao.updateAuthInfo(map);
	}
	
	@Override
	public void deleteAuthInfo(Map<String, String> map) {
		
		// 권한 그룹 정보 삭제 요청
		authInfoDao.deleteAuthInfo(map);
	}
	
	@Override
	public HashMap<String, Object> selectAuthInfoCheck(Map<String, Object> map) {
		
		// 특정 권한 그룹 정보 조회 요청
		return authInfoDao.selectAuthInfoCheck(map);
	}
	
	@Override
	public void insertAuthInfoSwagger(String roleId, String roleDesc) {
		
		// 권한 그룹 정보 추가 요청
		authInfoDao.insertAuthInfoSwagger(roleId, roleDesc);
	}
	
	@Override
	public void updateAuthInfoSwagger(String roleId, String roleDesc) {
		
		// 권한 그룹 정보 수정 요청
		authInfoDao.updateAuthInfoSwagger(roleId, roleDesc);
	}
	
	@Override
	public void deleteAuthInfoSwagger(String roleId) {
		
		// 권한 그룹 정보 삭제 요청
		authInfoDao.deleteAuthInfoSwagger(roleId);
	}
}
