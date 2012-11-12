package com.accuity.des.web;

import org.springframework.validation.*;
import com.accuity.des.domain.*;

public class InstDetailValidator implements Validator {

   public void validate(Object obj, Errors errors) {
      InstDetail instDetail = (InstDetail)obj;

      if (instDetail.getId() == null || instDetail.getId().length() == 0) {
        errors.rejectValue("id",   "error.empty.field", "field missing");
      }
      else if (instDetail.getTitle() == null || instDetail.getTitle().length() == 0) {
        errors.rejectValue("title",   "error.empty.field", "field missing");
      }
   }

   public boolean supports(Class clazz) {
      return clazz.equals(InstDetail.class);
   }
}

