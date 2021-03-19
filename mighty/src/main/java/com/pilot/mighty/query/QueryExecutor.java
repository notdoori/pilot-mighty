package com.pilot.mighty.query;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;

@Component
public class QueryExecutor {

	DataSource dataSource;
	
	NamedParameterJdbcTemplate qe = null;
	
	public QueryExecutor(DataSource dataSource) {
		this.dataSource = dataSource;
		qe = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<Map<String, Object>> selectList(String sql, Map<String, Object> bindVal) {
		
		List<Map<String, Object>> listRows = qe.queryForList(sql, bindVal);
		
		return listRows;
	}
	
	public Map<String, Object> selectMap(String sql, Map<String, Object> bindVal) {
		
		Map<String, Object> mapRows = qe.queryForMap(sql, bindVal);
		
		return mapRows;
	}
	
	public int seletCount(String sql, Map<String, Object> bindVal) {
		
		int count = qe.queryForObject(sql, bindVal, Integer.class);
		
		return count;
	}
	
	public int insert(String sql, Map<String, Object> bindVal) {
		
		int count = qe.update(sql, bindVal);
		
		return count;
	}
	
	public int update(String sql, Map<String, Object> bindVal) {
		
		int count = qe.update(sql, bindVal);
		
		return count;
	}
	
	public int[] batchInsert(String sql, List<Map<String, Object>> bindVal) {
		
		SqlParameterSource[] parameterSource = SqlParameterSourceUtils.createBatch(bindVal);
		
		return qe.batchUpdate(sql, parameterSource);
	}

}
