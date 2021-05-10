package com.pilot.mighty.dao;

import java.util.HashMap;
import java.util.Map;

import com.pilot.mighty.model.SysCode;

public interface SysCodeDao {
	
	SysCode[] selectSysCodeAll(String tableName, String use);
}
