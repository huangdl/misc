package com.accuity.des.action;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import com.accuity.des.domain.*;
import com.accuity.des.dao.jdbc.*;

@SuppressWarnings("deprecation")
public class InstDetailController extends SimpleFormController {

	private InstDao instDao;
	private RefDao refDao;

	public InstDetailController() {
		setCommandClass(InstDetail.class);
		setCommandName("instDetail");
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		InstDetail instDetail = (InstDetail) super.formBackingObject(request);
		return instDetail;
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {

		HashMap<String, HashMap<String, String>> referenceData = new HashMap<String, HashMap<String, String>>();
		referenceData.put("statusList", refDao.getStatusList());
		referenceData.put("organizationTypeList", refDao.getOrganizatonTypes());
		referenceData.put("authorityCharterList", refDao.getAuthorityCharters());
		referenceData.put("trustPowerList", refDao.getTrustPowers());
		referenceData.put("insuranceTypeList", refDao.getInsuranceTypes());
		referenceData.put("generalCategoryList", refDao.getGeneralCategories());
		referenceData.put("subcategoryList", refDao.getSubcategories());
		referenceData.put("yesNoList", refDao.getYesNoList());

		return referenceData;
	}

	protected HttpServletRequest setFormList(HttpServletRequest request) {
	
		request.setAttribute("statusList", refDao.getStatusList());
		request.setAttribute("organizationTypeList", refDao.getOrganizatonTypes());
		request.setAttribute("authorityCharterList", refDao.getAuthorityCharters());
		request.setAttribute("trustPowerList", refDao.getTrustPowers());
		request.setAttribute("insuranceTypeList", refDao.getInsuranceTypes());
		request.setAttribute("generalCategoryList", refDao.getGeneralCategories());
		request.setAttribute("subcategoryList", refDao.getSubcategories());
		request.setAttribute("yesNoList", refDao.getYesNoList());
		
		return request;
	}
	
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		request = setFormList(request);
		
		InstDetail instDetail = (InstDetail) command;
		boolean success = instDao.save(instDetail);
		String message = (success == true) ? "Data saved successfully."
				: "Unable to save.";
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("instDetail", instDetail);
		mav.addObject("message", message);
		mav.setViewName(getSuccessView());
		
		return mav;
	}

	protected ModelAndView showForm(HttpServletRequest request,
			HttpServletResponse response, BindException errors) {
		String id = request.getParameter("instid");
		InstDetail instDetail = new InstDetail();

		if (id != null && id.length() != 0) {
			instDetail = instDao.getDetail(id);
			instDetail.setReadonly("true");
		}
		else {
			id = instDao.getNewInstId();
			instDetail.setId(id);
			instDetail.setReadonly("false");
		}

		request.setAttribute("statusList", refDao.getStatusList());
		request.setAttribute("organizationTypeList", refDao.getOrganizatonTypes());
		request.setAttribute("authorityCharterList", refDao.getAuthorityCharters());
		request.setAttribute("trustPowerList", refDao.getTrustPowers());
		request.setAttribute("insuranceTypeList", refDao.getInsuranceTypes());
		request.setAttribute("generalCategoryList", refDao.getGeneralCategories());
		request.setAttribute("subcategoryList", refDao.getSubcategories());
		request.setAttribute("yesNoList", refDao.getYesNoList());

		return new ModelAndView(getFormView(), "instDetail", instDetail);
	}

	public InstDao getInstDao() {
		return this.instDao;
	}

	public void setInstDao(InstDao instDao) {
		this.instDao = instDao;
	}

	public RefDao getRefDao() {
		return this.refDao;
	}

	public void setRefDao(RefDao refDao) {
		this.refDao = refDao;
	}
}


