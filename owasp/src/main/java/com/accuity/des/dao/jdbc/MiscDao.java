package com.accuity.des.dao.jdbc;

import java.util.*;
import java.sql.CallableStatement;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Encoder;
import org.owasp.esapi.Validator;
import org.owasp.esapi.codecs.Codec;
import org.owasp.esapi.codecs.OracleCodec;

import com.accuity.des.domain.Feedback;

public class MiscDao {

	private JdbcTemplate jdbcTemplate;
	public static final int maxFieldLength = 25;

	public LinkedHashMap<String, String> getCountries() {

		String sql = "select * from geographical_country where status = 'A' order by country_name";
		LinkedHashMap<String, String> hm = new LinkedHashMap<String, String>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : rows) {
			hm.put(row.get("GEO_COUNTRY_CODE") + "", row.get("COUNTRY_NAME")
					+ "");
		}
		return hm;
	}

	public LinkedHashMap<String, String> getCountryStates(String countryCode) {

		String sql = "select * from val.val_province_state where geo_country_code = ? "
				+ "order by state_province_description";
		Vector<Object> params = new Vector<Object>();
		params.add(countryCode);

		LinkedHashMap<String, String> hm = new LinkedHashMap<String, String>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,
				params.toArray());

		for (Map<String, Object> row : rows) {
			hm.put(row.get("STATE_PROVINCE_CODE") + "",
					row.get("STATE_PROVINCE_DESCRIPTION") + "");
		}
		return hm;
	}

	public LinkedHashMap<String, String> getCountryCities(String countryCode) {

		String sql = "select * from geographical_city where status = 'A' "
				+ "and geo_country_code = ? order by city_sort_name";
		Vector<Object> params = new Vector<Object>();
		params.add(countryCode);

		LinkedHashMap<String, String> hm = new LinkedHashMap<String, String>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,
				params.toArray());

		for (Map<String, Object> row : rows) {
			hm.put(row.get("GEO_PRINTED_CITY_NAME") + "",
					row.get("CITY_SORT_NAME") + "");
		}
		return hm;
	}

	public LinkedHashMap<String, String> getCountryStateCities(
			String countryCode, String stateCode) {

		String sql = "select * from geographical_city where status = 'A' "
				+ "and geo_country_code = ? and geo_state_postal_code = ? "
				+ "order by city_sort_name";
		Vector<Object> params = new Vector<Object>();
		params.add(countryCode);
		params.add(stateCode);

		LinkedHashMap<String, String> hm = new LinkedHashMap<String, String>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, params.toArray());

		for (Map<String, Object> row : rows) {
			hm.put(row.get("GEO_PRINTED_CITY_NAME") + "",
					row.get("CITY_SORT_NAME") + "");
		}
		return hm;
	}

	public LinkedHashMap<String, String> getGeneralCategories() {

		String sql = "select * from val.val_general_category order by general_category_decription";
		LinkedHashMap<String, String> hm = new LinkedHashMap<String, String>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : rows) {
			hm.put(row.get("GENERAL_CATEGORY_CODE") + "",
					row.get("GENERAL_CATEGORY_DECRIPTION") + "");
		}
		return hm;
	}

	public LinkedHashMap<String, String> getSubcategories() {

		String sql = "select * from val_subcategory order by subcategory_description";
		LinkedHashMap<String, String> hm = new LinkedHashMap<String, String>();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : rows) {
			hm.put(row.get("SUBCATEGORY_CODE") + "",
					row.get("SUBCATEGORY_DESCRIPTION") + "");
		}
		return hm;
	}

	public LinkedHashMap<String, String> getYesNoList() {

		LinkedHashMap<String, String> hm = new LinkedHashMap<String, String>();
		hm.put("Y", "Yes");
		hm.put("N", "No");

		return hm;
	}

	public LinkedHashMap<String, String> getStatusList() {

		LinkedHashMap<String, String> hm = new LinkedHashMap<String, String>();
		hm.put("A", "Active");
		hm.put("I", "Inactive");
		hm.put("P", "Pending");
		hm.put("L", "Liquidation");

		return hm;
	}

	public LinkedHashMap<String, String> getTrustPowers() {

		LinkedHashMap<String, String> hm = new LinkedHashMap<String, String>();
		hm.put("Y", "Yes");
		hm.put("N", "No");
		hm.put("L", "Limited");
		hm.put("U", "Unknow");
		hm.put("G", "Granted, Not Exercised");

		return hm;
	}

	public LinkedHashMap<String, String> getAuthorityCharters() {

		LinkedHashMap<String, String> hm = new LinkedHashMap<String, String>();
		hm.put("Federal", "Federal");
		hm.put("National", "National");
		hm.put("Private", "Private");
		hm.put("State", "State");

		return hm;
	}
	
	public LinkedHashMap<String, String> getOrganizatonTypes() {

		String sql = "select * from val.val_organization_type order by organization_type_description";
		LinkedHashMap<String, String> hm = new LinkedHashMap<String, String>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : rows) {
			hm.put(row.get("ORGANIZATION_TYPE_CODE") + "",
					row.get("ORGANIZATION_TYPE_DESCRIPTION") + "");
		}
		return hm;
	}

	public LinkedHashMap<String, String> getInsuranceTypes() {

		String sql = "select * from val_insurance_type order by insurance_type_description";
		LinkedHashMap<String, String> hm = new LinkedHashMap<String, String>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : rows) {
			hm.put(row.get("INSURANCE_TYPE_CODE") + "",
					row.get("INSURANCE_TYPE_DESCRIPTION") + "");
		}
		return hm;
	}

	public LinkedHashMap<String, String> getBoroughs() {

		String sql = "select * from geo_borough order by description";
		LinkedHashMap<String, String> hm = new LinkedHashMap<String, String>();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : rows) {
			if (row.get("DESCRIPTION") == null)
				hm.put(row.get("BOROUGH_CODE") + "", "");
			else
				hm.put(row.get("BOROUGH_CODE") + "", row.get("DESCRIPTION")
						+ "");
		}
		return hm;
	}

	public LinkedHashMap<String, String> getIslands() {

		String sql = "SELECT * FROM val_geo_code WHERE val_geo_code.geo_code_type = 'ISLAN' "
				+ "order by geo_code_description";
		LinkedHashMap<String, String> hm = new LinkedHashMap<String, String>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : rows) {
			hm.put(row.get("GEO_CODE_VALUE") + "",
					row.get("GEO_CODE_DESCRIPTION") + "");
		}
		return hm;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Feedback> getFeedback() {

		String sql = "select name, value from feedback order by timestamp";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		
		List<Feedback> feedbackList = new ArrayList<Feedback>();
		
		for (Map<String, Object> row : rows) {
			if (row.get("NAME") != null ) {
				feedbackList.add((Feedback) row.get("NAME"));
				feedbackList.add((Feedback) row.get("VALUE"));
			}
		}
		
		return feedbackList;
	}
	
	public boolean login(String user, String password) {
		String sql;
		List<Map<String, String>> rows;
		List<Object> parameters = new ArrayList<Object>();
		
		
		// Vulnerable code
		sql = "SELECT * FROM users where username = '" + user + "' and password = '" + password + "'";
		rows = jdbcTemplate.queryForList(sql);
		
		// Defense Option 1 - Parameterized query
		sql = "SELECT * FROM users where username = ? and password = ?";
		parameters.add(user);
		parameters.add(password);
		rows = jdbcTemplate.queryForList(sql, parameters.toArray());
		
/*
		// Defense Option 2 - Stored procedures
	    jdbcTemplate.execute("{call sp_login(42)}", new CallableStatementCallback() {
	        public Object doInCallableStatement(CallableStatement callableStatement) throws SQLException, DataAccessException {
	          callableStatement.execute();
	          return null;
	        }
	    });
		
		
		//Defense Option 3 - Escape all input strings
		Codec ORACLE_CODEC = new OracleCodec();
		user = ESAPI.encoder().encodeForSQL( ORACLE_CODEC, user);
		password = ESAPI.encoder().encodeForSQL( ORACLE_CODEC, password);
		sql = "SELECT * FROM users where username = '" + user + "' and password = '" + password + "'";
		rows = jdbcTemplate.queryForList(sql);		
*/		
		return rows.size() > 0;
	}

}


