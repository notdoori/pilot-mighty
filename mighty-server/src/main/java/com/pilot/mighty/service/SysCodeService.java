package com.pilot.mighty.service;

import java.util.HashMap;
import java.util.Map;

import com.pilot.mighty.model.SysCode;
import com.pilot.mighty.model.SysCodeData;

public interface SysCodeService {

	SysCode[] selectSysCodeAll();
	SysCodeData[] selectSysCodeDataAll(String tableName);
	SysCode[] selectSysCode(String tableName, String use);
	SysCodeData[] selectSysCodeData(String tableName, String use);
	
	HashMap<String, Object> checkRegistSysCode(Map<String, Object> map);
	HashMap<String, Object> checkRegistSysCodeData(Map<String, Object> map);
	
	void registSysCode(Map<String, String> map);
	void modifySysCode(Map<String, String> map);
	void deleteSysCode(Map<String, String> map);
	void registSysCodeData(Map<String, String> map);
	void modifySysCodeData(Map<String, String> map);
	void deleteSysCodeData(Map<String, String> map);
	void deleteRelatedSysCodeData(Map<String, String> map);
}
