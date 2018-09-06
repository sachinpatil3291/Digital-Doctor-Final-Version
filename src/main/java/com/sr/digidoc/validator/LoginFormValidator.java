package com.sr.digidoc.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sr.digidoc.model.Clinic;
import com.sr.digidoc.model.Login;

@Component
public class LoginFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Clinic.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Login login = (Login) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.AddClinicForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.AddClinicForm.address");
	}
}