package com.pilot.mighty.query;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;

import com.pilot.mighty.controller.UserController;

@Component
public class QueryExecutor {

	DataSource dataSource;
	
	NamedParameterJdbcTemplate qe = null;
	
	private final Logger logger = LogManager.getLogger(QueryExecutor.class);
	
	public QueryExecutor(DataSource dataSource) {
		this.dataSource = dataSource;
		qe = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public void prinQueryWithBindVal(String sql, Map<String, Object> bindVal) {
		
		int len = bindVal.size();
		String printSql = new String(sql);
		if (len > 0) {
			for (Map.Entry<String, Object> entry : bindVal.entrySet()) {
				Object val = entry.getValue();
				if (val instanceof String) {
					printSql = printSql.replace(":" + entry.getKey(), "'" + entry.getValue().toString() + "'");
				} else {
					printSql = printSql.replace(":" + entry.getKey(), entry.getValue().toString());
				}
		    }
			printSql = printSql.replace("''", "'");
		}
		
		logger.debug(printSql);
	}
	
	public List<Map<String, Object>> selectList(String sql, Map<String, Object> bindVal) {
		
		prinQueryWithBindVal(sql, bindVal);
		
		List<Map<String, Object>> listRows = qe.queryForList(sql, bindVal);
		
		return listRows;
	}
	
	public Map<String, Object> selectMap(String sql, Map<String, Object> bindVal) {
		
		prinQueryWithBindVal(sql, bindVal);
		
		Map<String, Object> mapRows = qe.queryForMap(sql, bindVal);
		
		return mapRows;
	}
	
	public int seletCount(String sql, Map<String, Object> bindVal) {
		
		prinQueryWithBindVal(sql, bindVal);
		
		int count = qe.queryForObject(sql, bindVal, Integer.class);
		
		return count;
	}
	
	public int insert(String sql, Map<String, Object> bindVal) {
		
		prinQueryWithBindVal(sql, bindVal);
		
		int count = qe.update(sql, bindVal);
		
		return count;
	}
	
	public int update(String sql, Map<String, Object> bindVal) {
		
		prinQueryWithBindVal(sql, bindVal);
		
		int count = qe.update(sql, bindVal);
		
		return count;
	}
	
	public int[] batchInsert(String sql, List<Map<String, Object>> bindVal) {
		
		SqlParameterSource[] parameterSource = SqlParameterSourceUtils.createBatch(bindVal);
		
		return qe.batchUpdate(sql, parameterSource);
	}

}
