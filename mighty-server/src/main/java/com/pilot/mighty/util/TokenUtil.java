package com.pilot.mighty.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TokenUtil {
	
	public static boolean checkAvailToken(String accessToken) throws ParseException {
		
		String[] token = accessToken.split(":");
		
		Date oldDateTime = new SimpleDateFormat("yyyyMMddHHmmss").parse(token[1]);
		Date curDateTime = new Date();
		
		long diff = curDateTime.getTime() - oldDateTime.getTime();
		
		TimeUnit tu = TimeUnit.SECONDS;
		long diffTu = tu.convert(diff, TimeUnit.MILLISECONDS);
			
		if (diffTu >= 600) {
			return false;
		}
		
		return true;
	}

}
