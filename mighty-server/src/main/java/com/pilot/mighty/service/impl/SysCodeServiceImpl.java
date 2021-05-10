package com.pilot.mighty.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pilot.mighty.dao.SysCodeDao;
import com.pilot.mighty.model.SysCode;
import com.pilot.mighty.service.SysCodeService;

@Service
public class SysCodeServiceImpl implements SysCodeService {
	
	@Autowired
	SysCodeDao sysCodeDao;

	@Override
	public SysCode[] selectSysCodeAll(String tableName, String use) {
		// TODO Auto-generated method stub
		
		SysCode[] sysCode = sysCodeDao.selectSysCodeAll(tableName, use);
		return sysCode;
	}

}
