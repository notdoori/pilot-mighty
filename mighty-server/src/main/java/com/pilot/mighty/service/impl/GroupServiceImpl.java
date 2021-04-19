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
		
		// 모든 사용자 그룹 조회 요청
		GroupInfo[] authInfo = groupInfoDao.selectGroupInfoAll();
		
		return authInfo;
	}
	
	@Override
	public AuthInfo[] selectAuthInfoAll() {
		
		// 모든 권한 그룹 ID 조회 요청
		AuthInfo[] authInfo = groupInfoDao.selectAuthInfoAll();
		
		return authInfo;
	}
	
	@Override
	public HashMap<String, Object> selectGroupInfoSearch(Map<String, Object> map) {
		
		// 사용자 그룹 정보 조회 요청
		return groupInfoDao.selectGroupInfoSearch(map);
	}
	
	@Override
	public void insertGroupInfo(Map<String, String> map) {
		
		// 사용자 그룹 정보 추가 요청
		groupInfoDao.insertGroupInfo(map);
	}
	
	@Override
	public void updateGroupInfo(Map<String, String> map) {
		
		// 사용자 그룹 정보 수정 요청
		groupInfoDao.updateGroupInfo(map);
	}
	
	@Override
	public void deleteGroupInfo(Map<String, String> map) {
		
		// 사용자 그룹 정보 삭제 요청
		groupInfoDao.deleteGroupInfo(map);
	}
	
	@Override
	public HashMap<String, Object> selectGroupInfoCheck(Map<String, Object> map) {
		
		// 특정 사용자 그룹 리스트 조회
		return groupInfoDao.selectGroupInfoCheck(map);
	}
	
	@Override
	public void insertGroupInfoSwagger(String groupId, String groupDesc, String roleId) {
		
		// (Swagger 프로그램) 사용자 그룹 정보 추가 요청
		groupInfoDao.insertGroupInfoSwagger(groupId, groupDesc, roleId);
	}
	
	@Override
	public void updateGroupInfoSwagger(String groupId, String groupDesc, String roleId) {
		
		// (Swagger 프로그램) 사용자 그룹 정보 수정 요청
		groupInfoDao.updateGroupInfoSwagger(groupId, groupDesc, roleId);
	}
	
	@Override
	public void deleteGroupInfoSwagger(String groupId) {
		
		// (Swagger 프로그램) 사용자 그룹 정보 삭제 요청
		groupInfoDao.deleteGroupInfoSwagger(groupId);
	}
}
