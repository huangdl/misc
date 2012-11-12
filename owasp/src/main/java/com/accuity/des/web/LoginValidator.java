package com.accuity.des.web;

import org.springframework.validation.*;

import com.accuity.des.dao.jdbc.RefDao;
import com.accuity.des.domain.Login;

public class LoginValidator implements Validator {

	private RefDao refDao;

	public void validate(Object obj, Errors errors) {
		Login login = (Login) obj;

		if (login.getUsername() == null || login.getUsername().length() == 0) {
			errors.rejectValue("username", "error.empty.field", "Empty login");
		} else if (login.getPassword() == null
				|| login.getPassword().length() == 0) {
			errors.rejectValue("password", "error.empty.field",
					"Empty password");
		} else {
			boolean loginValid = refDao.login(login.getUsername(), login.getPassword());

			if (!loginValid) {
				errors.rejectValue("username", "error.username.incorrect", "Invalid login, please try again.");
			}
		}
	}

	public boolean supports(Class clazz) {
		return clazz.equals(Login.class);
	}

	public RefDao getRefDao() {
		return this.refDao;
	}

	public void setRefDao(RefDao refDao) {
		this.refDao = refDao;
	}

}
