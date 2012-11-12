package com.accuity.des.web;

import org.springframework.validation.*;
import com.accuity.des.domain.Inst;

public class InstSearchValidator implements Validator {

   public void validate(Object obj, Errors errors) {
      Inst inst = (Inst)obj;

      if ((inst.getId() == null || inst.getId().length() == 0) && 
          (inst.getTitle() == null || inst.getTitle().length() == 0)) {
        errors.rejectValue("id",   "error.empty.field", "field missing");
      }
   }

   public boolean supports(Class clazz) {
      return clazz.equals(Inst.class);
   }
}

