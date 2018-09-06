package com.sr.digidoc.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sr.digidoc.model.FileUpload;

@Component
public class FileValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return FileUpload.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		FileUpload fileUploader = (FileUpload) target;
		if (fileUploader.getImages() != null) {
			if (fileUploader.getImages().size() == 0) {
				errors.rejectValue("file", "missing.file");
			}
		}
	}
}