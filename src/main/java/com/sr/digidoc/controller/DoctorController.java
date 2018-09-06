package com.sr.digidoc.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sr.digidoc.model.Doctor;
import com.sr.digidoc.service.ClinicService;
import com.sr.digidoc.service.DoctorService;
import com.sr.digidoc.util.FileProperties;

@Controller
public class DoctorController {

	private final Logger logger = LoggerFactory
			.getLogger(DoctorController.class);

	@Autowired
	DoctorService docService;

	@Autowired
	ClinicService clinicService;
	
	@Autowired
	FileProperties fileProperties;

	// doctor list page
	@RequestMapping(value = "/doctors", method = RequestMethod.GET)
	public String showAllDoctors(Model model) {
		List<Doctor> docList = docService.findAll();
		logger.debug("showAllDoctors()");
		model.addAttribute("doctor", docList);
		return "doctor/list";
	}

	// view Doctor by Id
	@RequestMapping(value = "/doctor/{id}/view", method = RequestMethod.GET)
	public String viewDoctor(@PathVariable("id") int id, Model model) {

		logger.debug("showDoctor() id: {}", id);
		Optional<Doctor> doctor = docService.findById(id);
		if (!doctor.isPresent()) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "User not found");
		}
		model.addAttribute("doctor", doctor.get());
		return "doctor/view";
	}

	// add doctor form
	@RequestMapping(value = "/doctor/add/form", method = RequestMethod.GET)
	public String addDoctorForm(Model model) {
		Doctor dr = new Doctor();
		model.addAttribute("addDrForm", dr);
		model.addAttribute("clinicList", clinicService.findAll());
		return "doctor/add";
	}

	// add doctor
		@RequestMapping(value = "/doctor/add", method = RequestMethod.POST)
		public String saveDoctor(
				@ModelAttribute("addDrForm") @Validated Doctor doctor,
				BindingResult result, Model model,
				final RedirectAttributes redirectAttributes,
				ServletRequest servletRequest) throws IOException {
			
			List<MultipartFile> multipartFiles = doctor.getImages();
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
					doctor.setPhoto(fileProperties.getImagedownloadpath() + "/" + multipartFile.getOriginalFilename());
					doctor.setClinicName(clinicService.findById(doctor.getClinicId()).get().getName());
					docService.save(doctor);
					logger.info("Server File Location=" + serverFile.getAbsolutePath());
					redirectAttributes.addFlashAttribute("css", "success");
					redirectAttributes.addFlashAttribute("msg","Doctor added successfully!");
				} catch (Exception e) {
					redirectAttributes.addFlashAttribute("css", "danger");
					redirectAttributes.addFlashAttribute("msg", "Error in adding Doctor!");
				}
			}
			return "redirect:/doctor/" + doctor.getId() + "/view"; 
		}

	// view Doctor update form
	@RequestMapping(value = "/doctor/{id}/update/form", method = RequestMethod.GET)
	public String viewDoctorUpdateForm(@PathVariable("id") int id, Model model) {

		logger.debug("showDoctor() id: {}", id);
		Optional<Doctor> doctor = docService.findById(id);
		if (doctor.isPresent()) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Doctor not found");
		}
		model.addAttribute("doctor", doctor);
		model.addAttribute("clinicList", clinicService.findAll());
		return "doctor/update";
	}

	// add doctor
	@RequestMapping(value = "/doctor/{id}/update", method = RequestMethod.POST)
	public String updateDoctor(
			@ModelAttribute("addDrForm") @Validated Doctor doctor,
			BindingResult result, Model model,
			final RedirectAttributes redirectAttributes,
			ServletRequest servletRequest) throws IOException {
		logger.debug("Update() : {}", doctor);
		doctor.setClinicId(clinicService.getClinicId(doctor.getClinicName()));
		docService.update(doctor);
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg",
				"Doctor updated successfully!");
		// POST/REDIRECT/GET
		return "redirect:/doctor/" + doctor.getId() + "/view";
	}

	// delete clinic
	@RequestMapping(value = "/doctor/{id}/delete", method = RequestMethod.POST)
	public String deleteUser(@PathVariable("id") int id,
			final RedirectAttributes redirectAttributes) {
		logger.debug("deleteClinic() : {}", id);
		docService.delete(id);
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Doctor is deleted!");
		return "redirect:/doctors";
	}

	@RequestMapping(value = "/display/doctor/image", method = RequestMethod.GET)
	public void displayImage(@RequestParam("id") Integer doctorId,
			HttpServletResponse response,
			final RedirectAttributes redirectAttributes)
			throws ServletException, IOException {
		Optional<Doctor> d = docService.findById(doctorId);
		File source = new File(d.get().getPhoto());
		InputStream image = new FileInputStream(source);
		if (image != null) {
			BufferedInputStream bin = null;
			BufferedOutputStream bout = null;
			ServletOutputStream sos = response.getOutputStream();
			try {
				bin = new BufferedInputStream(image);
				bout = new BufferedOutputStream(sos);
				int ch = 0;
				;
				while ((ch = bin.read()) != -1) {
					bout.write(ch);
				}
			} catch (IOException ioException) {
				redirectAttributes.addFlashAttribute("css", "Danger");
				redirectAttributes.addFlashAttribute("msg",
						"Image display error!");
			} finally {
				bin.close();
				image.close();
				bout.close();
				sos.close();
			}
		}
	}
}
