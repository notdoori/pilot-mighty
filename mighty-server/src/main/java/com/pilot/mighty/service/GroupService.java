package com.pilot.mighty.service;

import java.util.HashMap;
import java.util.Map;

import com.pilot.mighty.model.GroupInfo;

public interface GroupService {

	GroupInfo[] selectGroupInfoAll();
	
//	HashMap<String, Object> selectUserInfo(Map<String, Object> map);
//	
//	HashMap<String, Object> selectUserAndRefreshToken(String accessToken);
//	
//	void updateUserToken(Map<String, String> map);
//	
//	String checkAvailableUser();
}
