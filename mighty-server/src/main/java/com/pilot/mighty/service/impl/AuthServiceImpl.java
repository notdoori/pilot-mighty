package com.pilot.mighty.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pilot.mighty.dao.AuthInfoDao;
import com.pilot.mighty.model.AuthInfo;
import com.pilot.mighty.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	AuthInfoDao authInfoDao;

	@Override
	public AuthInfo[] selectAuthInfoAll() {
		// TODO Auto-generated method stub
		
		AuthInfo[] authInfo = authInfoDao.selectAuthInfoAll();
		
		return authInfo;
	}

//	@Override
//	public String checkAvailableUser() {
//		
//		return userInfoDao.checkAvailableUser();
//		//return retMap;
//	}
//
//	@Override
//	public HashMap<String, Object> selectUserInfo(Map<String, Object> map) {
//		
//		return userInfoDao.selectUserInfo(map);
//	}
//
//	@Override
//	public void updateUserToken(Map<String, String> map) {
//	
//		userInfoDao.updateUserToken(map);
//		
//	}
//
//	@Override
//	public HashMap<String, Object> selectUserAndRefreshToken(String accessToken) {
//		// TODO Auto-generated method stub
//		return userInfoDao.selectUserAndRefreshToken(accessToken);
//	}
}
