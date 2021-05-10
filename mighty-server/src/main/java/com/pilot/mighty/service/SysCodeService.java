package com.pilot.mighty.service;

import com.pilot.mighty.model.SysCode;

public interface SysCodeService {

	SysCode[] selectSysCodeAll(String tableName, String use);
}
