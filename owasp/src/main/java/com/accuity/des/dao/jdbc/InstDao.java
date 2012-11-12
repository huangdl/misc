package com.accuity.des.dao.jdbc;

import java.util.*;
import java.util.Date;
import java.sql.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.accuity.des.domain.Inst;
import com.accuity.des.domain.InstDetail;

public class InstDao {

	private JdbcTemplate jdbcTemplate;;

	public List<Inst> search(Inst inst) {

		// build query
		Vector<Object> params = new Vector<Object>();
		String sql = "SELECT i.institution_id_sequence, l.location_status,"
				+ "general_category_decription, legal_title,"
				+ "geo_printed_city_name, geo_state_postal_abbrev_code, geo_country_code"
				+ " FROM institution i, location l, location_address la,"
				+ " institution_title it, geographical_city gc, val_general_category vc "
				+ " WHERE (i.institution_id_sequence = l.institution_id_sequence)"
				+ " AND (l.institution_id_sequence = la.institution_id_sequence)"
				+ " AND (l.location_id_sequence = la.location_id_sequence)"
				+ " AND ((location_type_code = '2200')"
				+ " AND (address_type_code = '2000')"
				+ " AND (location_department = 'MAIN'))"
				+ " AND (it.institution_id_sequence = i.institution_id_sequence)"
				+ " AND (name_type_code = '1700')"
				+ " AND (vc.general_category_code = i.general_category_code)"
				+ " AND (gc.geographical_id_sequence = la.geographical_id_sequence)";

		if (inst.getId() != null
				&& inst.getId().replaceAll(" ", "").length() > 0) {
			sql += " AND (i.institution_id_sequence = ?) ";
			params.add(inst.getId());
		} else if (inst.getTitle() != null && inst.getTitle().length() > 0) {
			sql += " AND (legal_title LIKE INITCAP (?) || '%'"
					+ " OR legal_title LIKE UPPER (?) || '%'"
					+ " OR it.name_value LIKE UPPER (?) || '%')";
			params.add(inst.getTitle());
			params.add(inst.getTitle());
			params.add(inst.getTitle());
		} else if (inst.getStatus() != null && inst.getStatus().length() > 0) {
			sql += " AND location_status = ?";
			params.add(inst.getStatus());
		} else if (inst.getCategory() != null
				&& inst.getCategory().length() > 0) {
			sql += " AND general_category_code = ?";
			params.add(inst.getCategory());
		} else if (inst.getCountry() != null && inst.getCountry().length() > 0) {
			sql += " AND geo_country_code = ?";
			params.add(inst.getCountry());
		} else if (inst.getState() != null && inst.getState().length() > 0) {
			sql += " AND geo_state_postal_abbrev_code = ?";
			params.add(inst.getState());
		} else if (inst.getCity() != null && inst.getCity().length() > 0) {
			sql += " AND geo_printed_city_name = ?";
			params.add(inst.getCity());
		} else if (inst.getZip() != null && inst.getZip().length() > 0) {
			sql += " AND location_us_zip_code like ? || '%' or location_intl_postal = ? || '%'";
			params.add(inst.getZip());
			params.add(inst.getZip());
		} else if (inst.getBorough() != null && inst.getBorough().length() > 0) {
			sql += " AND geo_borough_code = ?";
			params.add(inst.getBorough());
		} else if (inst.getIsland() != null && inst.getIsland().length() > 0) {
			sql += " AND geo_island_code = ?";
			params.add(inst.getIsland());
		} else if (inst.getVillage() != null && inst.getVillage().length() > 0) {
			sql += " AND geo_village_name = ?";
			params.add(inst.getVillage());
		}

		List<Inst> instList = new ArrayList<Inst>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,
				params.toArray());

		int j = 0;
		for (Map row : rows) {
			Inst i = new Inst();
			i.setId(row.get("INSTITUTION_ID_SEQUENCE").toString());
			i.setTitle((String) row.get("LEGAL_TITLE"));
			i.setStatus((String) row.get("LOCATION_STATUS"));
			i.setCategory((String) row.get("GENERAL_CATEGORY_DECRIPTION"));
			i.setCity((String) row.get("GEO_PRINTED_CITY_NAME"));
			i.setState((String) row.get("GEO_STATE_POSTAL_ABBREV_CODE"));
			i.setCountry((String) row.get("GEO_COUNTRY_CODE"));
			instList.add(i);

			if (j++ > inst.getMax())
				break;
		}

		return instList;
	}
	
	public InstDetail getDetail(String id) {

		String sql = "SELECT i.*, s.subcategory_code, "
				+ " vc.general_category_decription, vs.subcategory_description,"
				+ " vo.organization_type_description, vi.insurance_type_description,t.num_field"
				+ " FROM institution i,"
				+ " institution_subcategory s,"
				+ " inst_item t,"
				+ " val_general_category vc,"
				+ " val_subcategory vs,"
				+ " val_organization_type vo,"
				+ " val_insurance_type vi"
				+ " WHERE i.institution_id_sequence = s.institution_id_sequence"
				+ " AND i.institution_id_sequence = t.inst_id(+)"
				+ " AND t.code(+) = 'INST_COUNT'"
				+ " AND category_rank = 1"
				+ " AND vc.general_category_code = i.general_category_code"
				+ " AND vs.subcategory_code = s.subcategory_code"
				+ " AND vo.organization_type_code(+) = i.organization_type_code"
				+ " AND vi.insurance_type_code(+) = i.insurance_type_code"
				+ " AND i.institution_id_sequence = ?";

		InstDetail instDetail = (InstDetail) jdbcTemplate.queryForObject(sql,
				new Object[] { new String(id) }, new RowMapper() {
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						InstDetail i = new InstDetail();
						i.setId(rs.getInt("INSTITUTION_ID_SEQUENCE") + "");
						i.setTitle(rs.getString("LEGAL_TITLE"));
						i.setStatus(rs.getString("INSTITUTION_STATUS"));
						i.setYearEstablished(rs.getInt("YEAR_ESTABLISHED") + "");
						i.setOrganizationTypeCode(rs.getString("ORGANIZATION_TYPE_CODE"));
						i.setOrganizationTypeDesc(rs.getString("ORGANIZATION_TYPE_DESCRIPTION"));
						i.setNumberEmployees(rs.getInt("NUMBER_EMPLOYEES") + "");
						i.setEmployerId(rs.getString("EMPLOYER_ID"));
						i.setNumberMembers(rs.getInt("NUMBER_MEMBERS") + "");
						i.setAuthorityCharter(rs.getString("AUTHORITY_CHARTER"));
						i.setLeadInstitution(rs.getString("LEAD_INSTITUTION"));
						i.setMemberBanks(rs.getInt("MEMBER_BANKS") + "");
						i.setTrustPowers(rs.getString("TRUST_POWERS"));
						i.setInsuranceTypeCode(rs.getString("INSURANCE_TYPE_CODE"));
						i.setInsuranceTypeDesc(rs.getString("INSURANCE_TYPE_DESCRIPTION"));
						i.setGeneralCategoryCode(rs.getString("GENERAL_CATEGORY_CODE"));
						i.setGeneralCategoryDesc(rs.getString("GENERAL_CATEGORY_DECRIPTION"));
						i.setSubcategoryCode(rs.getString("SUBCATEGORY_CODE"));
						i.setSubcategoryDesc(rs.getString("SUBCATEGORY_DESCRIPTION"));
						i.setCuShareDraftFlag(rs.getString("CU_SHARE_DRAFT_FLAG"));
						i.setAdminEmp((rs.getInt("TOTAL_BRANCHES") == 0) ? ""
								: Integer.toString(rs.getInt("TOTAL_BRANCHES")));
						i.setAdminEmp((rs.getInt("FOREIGN_LOCATIONS") == 0) ? ""
								: Integer.toString(rs.getInt("FOREIGN_LOCATIONS")));
						i.setAdminEmp((rs.getInt("MIN_ACCT") == 0) ? ""
								: Integer.toString(rs.getInt("MIN_ACCT")));
						i.setAdminEmp((rs.getInt("PROF_EMP") == 0) ? ""
								: Integer.toString(rs.getInt("PROF_EMP")));
						i.setAdminEmp((rs.getInt("ADMIN_EMP") == 0) ? ""
								: Integer.toString(rs.getInt("ADMIN_EMP")));
						return i;
					}
				});

		return instDetail;
	}

	public boolean isNewInst(String id) {
		String sql = "SELECT 1 FROM INSTITUTION WHERE INSTITUTION_ID_SEQUENCE = ? ";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[] {id});
		return (rows.size()>0)? false : true;
	}

	public String getNewInstId() {
		Object o = jdbcTemplate.queryForObject(
				"SELECT institution_id_seq.nextval FROM dual", String.class);
		return (String) o;

	}
	
	public boolean save(InstDetail instDetail) {
		Vector<Object> params = new Vector<Object>();
		String sql = null; 
		boolean retVal = false;

		boolean isNewInst = isNewInst(instDetail.getId());
		
		if (isNewInst) {		
			// Sort title
			params.clear();
			params.add(instDetail.getId());
			params.add("1700");
			params.add(instDetail.getTitle().toUpperCase());
			
			sql = " INSERT INTO INSTITUTION_TITLE "
				+ "(INSTITUTION_ID_SEQUENCE, NAME_TYPE_CODE, NAME_VALUE) "
				+ " VALUES (?, ?, ?)";
			
			jdbcTemplate.update(sql, params.toArray());
			
			// Legal title
			params.clear();
			params.add(instDetail.getId());
			params.add("1720");
			params.add(instDetail.getTitle());
			
			sql = " INSERT INTO INSTITUTION_TITLE "
				+ "(INSTITUTION_ID_SEQUENCE, NAME_TYPE_CODE, NAME_VALUE) "
				+ " VALUES (?, ?, ?)";
			
			jdbcTemplate.update(sql, params.toArray());			
	
			// SubCategory
			params.clear();
			params.add(instDetail.getId());
			params.add("1");
			params.add(instDetail.getSubcategoryCode());
			
			sql = " INSERT INTO INSTITUTION_SUBCATEGORY "
				+ "(INSTITUTION_ID_SEQUENCE, CATEGORY_RANK, SUBCATEGORY_CODE) "
				+ " VALUES (?, ?, ?)";
			
			jdbcTemplate.update(sql, params.toArray());	

			// Institution
			params.clear();
			params.add(instDetail.getId());
			params.add(instDetail.getGeneralCategoryCode());
			params.add(instDetail.getInsuranceTypeCode());
			params.add(instDetail.getOrganizationTypeCode());
			params.add(instDetail.getTitle());
			params.add(instDetail.getYearEstablished());
			params.add(instDetail.getStatus());
			params.add(instDetail.getNumberEmployees());
			params.add(instDetail.getEmployerId());
			params.add(instDetail.getNumberMembers());
			params.add(instDetail.getAuthorityCharter());
			params.add(instDetail.getLeadInstitution());
			params.add(instDetail.getTrustPowers());
			params.add(instDetail.getCuShareDraftFlag());
			params.add(instDetail.getMemberBanks());
			params.add(instDetail.getTotalBranches());
			params.add(instDetail.getMinAcct());
			params.add(instDetail.getProfEmp());
			params.add(instDetail.getAdminEmp());
			params.add(instDetail.getForeignLocations());
			
			sql = " INSERT INTO INSTITUTION ("
				+ "INSTITUTION_ID_SEQUENCE,"
  				+ "GENERAL_CATEGORY_CODE,"
  				+ "INSURANCE_TYPE_CODE,"
  				+ "ORGANIZATION_TYPE_CODE,"
  				+ "LEGAL_TITLE,"
  				+ "YEAR_ESTABLISHED,"
  				+ "INSTITUTION_STATUS,"
  				+ "NUMBER_EMPLOYEES,"
  				+ "EMPLOYER_ID,"
  				+ "NUMBER_MEMBERS,"
  				+ "AUTHORITY_CHARTER,"
  				+ "LEAD_INSTITUTION,"
  				+ "TRUST_POWERS,"
  				+ "CU_SHARE_DRAFT_FLAG,"
  				+ "MEMBER_BANKS,"
  				+ "TOTAL_BRANCHES,"
  				+ "MIN_ACCT,"
  				+ "PROF_EMP,"
  				+ "ADMIN_EMP,"
  				+ "FOREIGN_LOCATIONS)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			jdbcTemplate.update(sql, params.toArray());	
			
			// more on triggers
			
			retVal = true;
		} 
		else {
			// Institution
			params.clear();
			params.add(instDetail.getTitle());
			params.add(instDetail.getYearEstablished());
			params.add(instDetail.getOrganizationTypeCode());
			params.add(instDetail.getNumberEmployees());
			params.add(instDetail.getEmployerId());
			params.add(instDetail.getNumberMembers());
			params.add(instDetail.getAuthorityCharter());
			params.add(instDetail.getLeadInstitution());
			params.add(instDetail.getMemberBanks());
			params.add(instDetail.getTrustPowers());
			params.add(instDetail.getInsuranceTypeCode());
			params.add(instDetail.getGeneralCategoryCode());
			params.add(instDetail.getTotalBranches());
			params.add(instDetail.getForeignLocations());
			params.add(instDetail.getCuShareDraftFlag());
			params.add(instDetail.getMinAcct());
			params.add(instDetail.getProfEmp());
			params.add(instDetail.getAdminEmp());
			params.add(instDetail.getId());
			
			sql = "UPDATE institution "
					+ " SET legal_title = ? "
					+ ",YEAR_ESTABLISHED = ? "
					+ ",ORGANIZATION_TYPE_CODE = ? "
					+ ",NUMBER_EMPLOYEES = ? "
					+ ",EMPLOYER_ID = ? "
					+ ",NUMBER_MEMBERS = ? "
					+ ",AUTHORITY_CHARTER = ? "
					+ ",LEAD_INSTITUTION = ? "
					+ ",MEMBER_BANKS = ? "
					+ ",TRUST_POWERS = ? "
					+ ",INSURANCE_TYPE_CODE = ? "
					+ ",GENERAL_CATEGORY_CODE = ? "
					+ ",TOTAL_BRANCHES = ? "
					+ ",FOREIGN_LOCATIONS = ? "
					+ ",CU_SHARE_DRAFT_FLAG = ? "
					+ ",MIN_ACCT = ? "
					+ ",PROF_EMP = ? "
					+ ",ADMIN_EMP = ? "
					+ "WHERE institution_id_sequence = ?";	
			
			jdbcTemplate.update(sql, params.toArray());

			// SubCategory
			params.clear();
			params.add(instDetail.getSubcategoryCode());
			params.add(instDetail.getId());
			
			sql = "UPDATE INSTITUTION_SUBCATEGORY "
				+ "SET SUBCATEGORY_CODE = ? "
				+ "WHERE category_rank = 1 "
				+ "AND institution_id_sequence = ?";	
			
			jdbcTemplate.update(sql, params.toArray());
	
			// more on triggers
			
			retVal = true;
		}
		return retVal;
	}

	public boolean delete(Inst inst) {

		String sql = "DELETE FROM institution WHERE institution_id_sequence = ?";

		if (inst.getId() != null)
			jdbcTemplate.update(sql, new Object[] { inst.getId() });
		else
			return false;

		return true;
	}

	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}


