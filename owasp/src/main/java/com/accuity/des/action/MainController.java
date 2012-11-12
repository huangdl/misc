package com.accuity.des.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class MainController extends AbstractController {

    @Override
    protected ModelAndView handleRequestInternal(
		HttpServletRequest request, 
		HttpServletResponse response) throws Exception {
        return new ModelAndView("main");
    }

}

