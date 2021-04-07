package com.pilot.mighty.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pilot.mighty.dao.UserInfoDao;
import com.pilot.mighty.model.UserInfo;
import com.pilot.mighty.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserInfoDao userInfoDao;

	@Override
	public UserInfo[] selectUserInfoAll() {
		// TODO Auto-generated method stub
		
		UserInfo[] userInfo = userInfoDao.selectUserInfoAll();
		return userInfo;
	}

	@Override
	public String checkAvailableUser() {
		
		return userInfoDao.checkAvailableUser();
		//return retMap;
	}

	@Override
	public HashMap<String, Object> selectUserInfo(Map<String, Object> map) {
		
		return userInfoDao.selectUserInfo(map);
	}

	@Override
	public void updateUserToken(Map<String, String> map) {
	
		userInfoDao.updateUserToken(map);
		
	}

	@Override
	public HashMap<String, Object> selectUserAndRefreshToken(String accessToken) {
		// TODO Auto-generated method stub
		return userInfoDao.selectUserAndRefreshToken(accessToken);
	}

}
