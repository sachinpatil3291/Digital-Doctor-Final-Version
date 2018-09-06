package com.sr.digidoc.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sr.digidoc.model.Clinic;

@Component
public class ClinicFormValidator implements Validator {

	/*@Autowired
	@Qualifier("emailValidator")
	EmailValidator emailValidator;
	*/
	@Override
	public boolean supports(Class<?> clazz) {
		return Clinic.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Clinic clinic = (Clinic) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.AddClinicForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.AddClinicForm.address");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contact", "NotEmpty.AddClinicForm.contact");

		/*if(!emailValidator.valid(user.getEmail())){
			errors.rejectValue("email", "Pattern.userForm.email");
		}*/
		
		/*if(clinic.getName()==null || StringUtils.isEmpty(clinic.getName())){
			errors.rejectValue("name", "NotEmpty.AddClinicForm.name");
		}*/
	}

}