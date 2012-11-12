package com.accuity.des.action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import com.accuity.des.domain.Login;
import com.accuity.des.dao.jdbc.*;

public class LoginController extends SimpleFormController {

	private Login login;

	public LoginController() {
		setCommandClass(Login.class);
		setCommandName("login");
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		Login login = (Login) super.formBackingObject(request);
		return login;
	}

	protected ModelAndView onSubmit(Object command, BindException bindException)
			throws Exception {

		return new ModelAndView(new RedirectView(getSuccessView()));
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
}
