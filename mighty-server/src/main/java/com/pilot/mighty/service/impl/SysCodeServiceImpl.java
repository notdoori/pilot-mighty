package com.pilot.mighty.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pilot.mighty.service.SysCodeService;
import com.pilot.mighty.dao.SysCodeDao;
import com.pilot.mighty.model.SysCode;
import com.pilot.mighty.model.SysCodeData;

@Service
public class SysCodeServiceImpl implements SysCodeService {
	
	@Autowired
	SysCodeDao sysCodeDao;

	@Override
	public SysCode[] selectSysCodeAll() {
		
		SysCode[] sysCode = sysCodeDao.selectSysCodeAll();
		return sysCode;
	}
	
	@Override
	public SysCodeData[] selectSysCodeDataAll(String tableName) {
		
		SysCodeData[] sysCodeData = sysCodeDao.selectSysCodeDataAll(tableName);
		return sysCodeData;
	}
	
	@Override
	public SysCode[] selectSysCode(String tableName, String use) {
		
		SysCode[] sysCode = sysCodeDao.selectSysCode(tableName, use);
		return sysCode;
	}
	
	@Override
	public SysCodeData[] selectSysCodeData(String tableName, String use) {
		
		SysCodeData[] sysCodeData = sysCodeDao.selectSysCodeData(tableName, use);
		return sysCodeData;
	}
	
	@Override
	public HashMap<String, Object> checkRegistSysCode(Map<String, Object> map) {
		
		return sysCodeDao.checkRegistSysCode(map);
	}
	
	@Override
	public HashMap<String, Object> checkRegistSysCodeData(Map<String, Object> map) {
		
		return sysCodeDao.checkRegistSysCodeData(map);
	}
	
	@Override
	public void registSysCode(Map<String, String> map) {
	
		sysCodeDao.registSysCode(map);
		
	}
	
	@Override
	public void modifySysCode(Map<String, String> map) {
	
		sysCodeDao.modifySysCode(map);
		
	}
	
	@Override
	public void deleteSysCode(Map<String, String> map) {
	
		sysCodeDao.deleteSysCode(map);
		
	}
	
	@Override
	public void registSysCodeData(Map<String, String> map) {
	
		sysCodeDao.registSysCodeData(map);
		
	}
	
	@Override
	public void modifySysCodeData(Map<String, String> map) {
	
		sysCodeDao.modifySysCodeData(map);
		
	}
	
	@Override
	public void deleteSysCodeData(Map<String, String> map) {
	
		sysCodeDao.deleteSysCodeData(map);
		
	}
	
	@Override
	public void deleteRelatedSysCodeData(Map<String, String> map) {
	
		sysCodeDao.deleteRelatedSysCodeData(map);
		
	}

}
