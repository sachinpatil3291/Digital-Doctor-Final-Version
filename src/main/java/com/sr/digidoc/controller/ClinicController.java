package com.sr.digidoc.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sr.digidoc.model.Clinic;
import com.sr.digidoc.service.ClinicService;
import com.sr.digidoc.util.FileProperties;
import com.sr.digidoc.validator.ClinicFormValidator;

@Controller
public class ClinicController {

	private final Logger logger = LoggerFactory.getLogger(ClinicController.class);

	@Autowired
	ClinicService clinicService;

	@Autowired
	ClinicFormValidator clinicFormValidator;
	
	@Autowired
	FileProperties fileProperties;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(clinicFormValidator);
	}

	// view clinic
	@RequestMapping(value = "/clinic/{id}/view", method = RequestMethod.GET)
	public String showUser(@PathVariable("id") int id, Model model) throws UnsupportedEncodingException {
		logger.debug("showUser() id: {}", id);
		Optional<Clinic> clinic = clinicService.findById(id);
		if (!clinic.isPresent()) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "User not found");
		}
		model.addAttribute("clinic", clinic.get());
		return "clinic/view";
	}

	// view home page
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String showHomePage(Model model) {
		return "homePage";

	}

	// list clinic
	@RequestMapping(value = "/clinics", method = RequestMethod.GET)
	public String showAllClinics(Model model) {
		List<Clinic> clinicList = clinicService.findAll();
		logger.debug("showAllClinics()");
		model.addAttribute("clinic", clinicList);
		return "clinic/list";
	}

	// add clinic form
	@RequestMapping(value = "/clinic/add/form", method = RequestMethod.GET)
	public String showAddClinicForm(Model model) {
		logger.debug("showAddClinicForm()");
		Clinic clinic1 = new Clinic();
		model.addAttribute("AddClinicForm", clinic1);
		return "clinic/add";
	}

	// add clinic
		@RequestMapping(value = "/clinic/add", method = RequestMethod.POST)
		public String saveOrUpdateUser(@ModelAttribute("AddClinicForm") @Validated Clinic clinic, BindingResult result,
				Model model, final RedirectAttributes redirectAttributes, ServletRequest servletRequest)
				throws IOException {
			logger.debug("saveClinic() : {}", clinic);
			
			
			List<MultipartFile> multipartFiles = clinic.getImages();
			MultipartFile multipartFile = multipartFiles.get(0);
			if (!multipartFile.isEmpty()) {
				try {
					// Creating the directory to store file
					File dir = new File(fileProperties.getImageuploaddir());
					if (!dir.exists())
						dir.mkdirs();
					// Create the file on server
					File serverFile = new File(
							fileProperties.getImageuploaddir() + File.separator + multipartFile.getOriginalFilename());
					multipartFile.transferTo(serverFile.getAbsoluteFile());
					logger.info(serverFile.getPath());
					clinic.setImageURL(fileProperties.getImagedownloadpath() + "/" + multipartFile.getOriginalFilename());
					clinicService.save(clinic);
					//clinicService.updateClinicImage(fileProperties.getImagedownloadpath() + "/" + multipartFile.getOriginalFilename(), file.getClinicId());
					logger.info("Server File Location=" + serverFile.getAbsolutePath());
					redirectAttributes.addFlashAttribute("css", "success");
					redirectAttributes.addFlashAttribute("msg","Clinic added successfully!");
				} catch (Exception e) {
					redirectAttributes.addFlashAttribute("css", "danger");
					redirectAttributes.addFlashAttribute("msg", "Image not uploaded!");
				}
			}
			return "redirect:/clinic/" + clinic.getId() + "/view"; 
		}

	// update clinic form
	@RequestMapping(value = "/clinic/{id}/update/form", method = RequestMethod.GET)
	public String showUpdateClinicForm(@PathVariable("id") Integer id,Model model) {
		logger.debug("showUpdateClinicForm()");
		Optional<Clinic> clinic = clinicService.findById(id);
		model.addAttribute("clinic", clinic.get());
		return "clinic/update";
	}

	// update clinic API
	@RequestMapping(value = "/clinic/update", method = RequestMethod.POST)
	public String updateClinic(@ModelAttribute("clinic") @Validated Clinic clinic, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes, ServletRequest servletRequest, HttpServletResponse  response) {
		clinicService.update(clinic);
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Clinic updated successfully!");
		// POST/REDIRECT/GET
		return "redirect:/clinic/" + clinic.getId() + "/view";
	}

	// delete clinic
	@RequestMapping(value = "/clinic/search/form", method = RequestMethod.GET)
	public String searchClinic(Model model) {
		logger.debug("searchClinic()");
		Clinic c = new Clinic();
		model.addAttribute("clinic", c);
		return "clinic/search";
	}
	
	// search clinic
	@RequestMapping(value = "/clinic/{id}/delete", method = RequestMethod.POST)
	public String deleteUser(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
		logger.debug("deleteClinic() : {}", id);
		clinicService.delete(id);
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Clinic is deleted!");
		return "redirect:/clinics";
	}

	@RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
	public void showImage(@RequestParam("id") Integer clinicId, HttpServletResponse response,
			HttpServletRequest request,final RedirectAttributes redirectAttributes) 
					throws ServletException, IOException{
		Optional<Clinic> c = clinicService.findById(clinicId);  
		File source = new File(c.get().getImageURL());
		InputStream image = new FileInputStream(source);
		if( image != null ) {
			BufferedInputStream bin = null;
			BufferedOutputStream bout = null;
			ServletOutputStream sos = response.getOutputStream();
			try {
				bin = new BufferedInputStream( image );
				bout = new BufferedOutputStream( sos );
				int ch =0; ;
				while((ch=bin.read())!=-1) {
					bout.write(ch);
				}
			}catch(IOException ioException){
				redirectAttributes.addFlashAttribute("css", "Danger");
				redirectAttributes.addFlashAttribute("msg", "Image display error!");
			} finally {
				bin.close();
				image.close();
				bout.close();
				sos.close();
			}
		}
	}
}
