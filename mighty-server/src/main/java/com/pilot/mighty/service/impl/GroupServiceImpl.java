package com.pilot.mighty.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pilot.mighty.controller.GroupController;
import com.pilot.mighty.dao.AuthInfoDao;
import com.pilot.mighty.dao.GroupInfoDao;
import com.pilot.mighty.model.AuthInfo;
import com.pilot.mighty.model.GroupInfo;
import com.pilot.mighty.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
	
	@Autowired
	GroupInfoDao groupInfoDao;
	
	@Override
	public GroupInfo[] selectGroupInfoAll() {
		
		// 모든 사용자 그룹 리스트 조회
		GroupInfo[] authInfo = groupInfoDao.selectGroupInfoAll();
		
		return authInfo;
	}
	
	@Override
	public AuthInfo[] selectAuthInfoAll() {
		
		// 모든 사용자 그룹 리스트 조회
		AuthInfo[] authInfo = groupInfoDao.selectAuthInfoAll();
		
		return authInfo;
	}
	
	@Override
	public HashMap<String, Object> selectGroupInfo(Map<String, Object> map) {
		
		// 특정 사용자 그룹 리스트 조회
		return groupInfoDao.selectGroupInfo(map);
	}
	
	@Override
	public void insertGroupInfo(Map<String, String> map) {
		
		// 사용자 그룹 리스트 추가
		groupInfoDao.insertGroupInfo(map);
	}
	
	@Override
	public void updateGroupInfo(Map<String, String> map) {
		
		// 특정 사용자 그룹 리스트 설명 업데이트
		groupInfoDao.updateGroupInfo(map);
	}
	
	@Override
	public void deleteGroupInfo(Map<String, String> map) {
		
		// 사용자 그룹 리스트 삭제
		groupInfoDao.deleteGroupInfo(map);
	}
}
