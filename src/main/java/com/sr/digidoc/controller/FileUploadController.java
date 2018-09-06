
package com.sr.digidoc.controller;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sr.digidoc.model.FileUpload;
import com.sr.digidoc.service.ClinicService;
import com.sr.digidoc.service.DoctorService;
import com.sr.digidoc.util.FileProperties;
import com.sr.digidoc.validator.FileValidator;

@Controller
public class FileUploadController {

	private final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	ClinicService clinicService;

	@Autowired
	DoctorService doctorService;
	
	@Autowired
	FileValidator fileValidator;
	
	@Autowired
	FileProperties fileProperties;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(fileValidator);
	}

	//show upload clinic images form 
	@RequestMapping(value = "/clinic/{id}/upload/form", method = RequestMethod.GET)
	public String viewUploadForm(@PathVariable("id") Integer id,Model model) {
		FileUpload f = new FileUpload();
		f.setClinicId(id);
		model.addAttribute("fileUpload", f);
		return "clinic/uploadImages";
	}
	
	//show upload doctor images form 
	@RequestMapping(value = "/doctor/{id}/upload/form", method = RequestMethod.GET)
	public String viewDoctorUploadForm(@PathVariable("id") Integer id,Model model) {
		FileUpload f = new FileUpload();
		f.setDoctorId(id);
		model.addAttribute("fileUpload", f);
		return "doctor/uploadImages";
	}
	
	//clinic upload image API
	@RequestMapping(value = "/clinic/upload/image",method = RequestMethod.POST)
	public String uploadClinicImage( Model model,
			@ModelAttribute("fileUpload") @Validated FileUpload file, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		List<MultipartFile> multipartFiles = file.getImages();
		MultipartFile multipartFile = multipartFiles.get(0);
		if (!multipartFile.isEmpty()) {
			try {
				// Creating the directory to store file
				File dir = new File(fileProperties.getImageuploaddir());
				if (!dir.exists())
					dir.mkdirs();
				// Create the file on server
				File serverFile = new File(
						dir.getAbsolutePath() + File.separator + multipartFile.getOriginalFilename());
				multipartFile.transferTo(serverFile);
				logger.info(serverFile.getPath());
				clinicService.updateClinicImage(fileProperties.getImagedownloadpath() + "/" + multipartFile.getOriginalFilename(), file.getClinicId());
				logger.info("Server File Location=" + serverFile.getAbsolutePath());
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("css", "danger");
				redirectAttributes.addFlashAttribute("msg", "Image not uploaded!");
			}
		}
		return "redirect:/clinic/" + file.getClinicId() + "/view";
	}
	//doctor upload image API
	@RequestMapping(value = "/doctor/upload/image",method = RequestMethod.POST)
	public String uploadDoctorImage( Model model,
			@ModelAttribute("fileUpload") @Validated FileUpload file, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		List<MultipartFile> multipartFiles = file.getImages();
		MultipartFile multipartFile = multipartFiles.get(0);
		if (!multipartFile.isEmpty()) {
			try {
				// Creating the directory to store file
				File dir = new File(fileProperties.getImageuploaddir());
				if (!dir.exists())
					dir.mkdirs();
				// Create the file on server
				File serverFile = new File(
						dir.getAbsolutePath() + File.separator + multipartFile.getOriginalFilename());
				multipartFile.transferTo(serverFile);
				doctorService.updateDoctorImage(fileProperties.getImagedownloadpath() + "/" + multipartFile.getOriginalFilename()  , file.getDoctorId());
				logger.info("Server File Location=" + serverFile.getAbsolutePath());
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("css", "danger");
				redirectAttributes.addFlashAttribute("msg", "Image not uploaded!");
			}
		}
		return "redirect:/doctor/" + file.getDoctorId() + "/view";
	}
}
