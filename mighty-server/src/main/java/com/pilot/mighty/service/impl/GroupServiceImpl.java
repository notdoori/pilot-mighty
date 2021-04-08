package com.pilot.mighty.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pilot.mighty.dao.GroupInfoDao;
import com.pilot.mighty.model.GroupInfo;
import com.pilot.mighty.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
	
	@Autowired
	GroupInfoDao groupInfoDao;

	@Override
	public GroupInfo[] selectGroupInfoAll() {
		// TODO Auto-generated method stub
		
		GroupInfo[] groupInfo = groupInfoDao.selectGroupInfoAll();
		return groupInfo;
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
