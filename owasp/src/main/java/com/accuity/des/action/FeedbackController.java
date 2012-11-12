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
public class FeedbackController extends SimpleFormController {

	private RefDao refDao;

	public FeedbackController() {
		setCommandClass(Feedback.class);
		setCommandName("feedback");
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		Feedback feedback = (Feedback) super.formBackingObject(request);
		return feedback;
	}

	protected ModelAndView showForm(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) {
		Feedback feedback = (Feedback) command;

		ModelAndView mav = new ModelAndView();
		mav.addObject("feedback", feedback);
		mav.addObject("feedbackList", refDao.getFeedback());
		mav.setViewName(getSuccessView());
		
		return mav;
	}
	
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) throws Exception {
		
		Feedback feedback = (Feedback) command;
		refDao.addFeedback(feedback);
		
		List<Feedback> results = refDao.getFeedback();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("feedback", feedback);
		mav.addObject("feedbackList", results);
		mav.setViewName(getSuccessView());

		return mav;
	}

	public RefDao getRefDao() {
		return this.refDao;
	}

	public void setRefDao(RefDao refDao) {
		this.refDao = refDao;
	}
}


