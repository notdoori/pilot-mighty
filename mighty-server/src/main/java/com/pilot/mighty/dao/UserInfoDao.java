package com.pilot.mighty.dao;

import java.util.HashMap;
import java.util.Map;

import com.pilot.mighty.model.UserInfo;

public interface UserInfoDao {
	
	UserInfo[] selectUserInfoAll();
	
	HashMap<String, Object> checkRegistUser(Map<String, Object> map);
	
	void registUser(Map<String, String> map);
	
	void modifyUser(Map<String, String> map);
	
	void deleteUser(Map<String, String> map);
	
	HashMap<String, Object> selectUserInfo(Map<String, Object> map);
	
	void updateUserToken(Map<String, String> map);
	
	HashMap<String, Object> selectUserAndRefreshToken(String accessToken);
	
	String checkAvailableUser();
}
