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
public class InstSearchController extends SimpleFormController {

	// private InstService instService;
	private InstDao instDao;
	private RefDao refDao;

	public InstSearchController() {
		setCommandClass(Inst.class);
		setCommandName("inst");
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		Inst inst = (Inst) super.formBackingObject(request);
		inst.setMax(300);
		return inst;
	}

	protected HashMap<String, HashMap<String, String>> referenceData(
			HttpServletRequest request) throws Exception {
		HashMap<String, HashMap<String, String>> referenceData = new HashMap<String, HashMap<String, String>>();
		referenceData.put("statusList", refDao.getStatusList());
		referenceData.put("categoryList", refDao.getGeneralCategories());
		referenceData.put("countryList", refDao.getCountries());
		referenceData.put("boroughList", refDao.getBoroughs());
		referenceData.put("islandList", refDao.getIslands());	
		return referenceData;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		Inst inst = (Inst) command;
		List<Inst> results = instDao.search(inst);
	
		request.setAttribute("statusList", refDao.getStatusList());
		request.setAttribute("categoryList", refDao.getGeneralCategories());
		request.setAttribute("countryList", refDao.getCountries());
		request.setAttribute("boroughList", refDao.getBoroughs());
		request.setAttribute("islandList", refDao.getIslands());	
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("inst", inst);
		mav.addObject("instList", results);
		mav.setViewName(getSuccessView());

		return mav;
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


