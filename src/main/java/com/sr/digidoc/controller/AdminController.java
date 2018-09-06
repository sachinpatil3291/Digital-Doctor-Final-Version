/**
 * @author spatil58
 * @version
 * @since
 */

package com.sr.digidoc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sr.digidoc.model.Login;
import com.sr.digidoc.validator.LoginFormValidator;
		
@Controller
public class AdminController {
    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

	 @Autowired
	 LoginFormValidator loginFormValidator;

	/* authenticate user */
	@RequestMapping(value = "/authenticate/admin", method = RequestMethod.POST)
	public String authUser(@ModelAttribute("loginForm") @Validated Login l, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		logger.debug("authUser : {}", l);
		if (result.hasErrors()) {
			return "admin/login";
		} else {
			String password = l.getPassword();
			String userName = l.getUserName();
			if ((password != null && password.equals("admin@digidoc"))
					&& (userName != null && userName.equals("admin"))) {
					return "redirect:/doctors";
			} else {
				redirectAttributes.addFlashAttribute("css", "danger");
				redirectAttributes.addFlashAttribute("msg", "Invalid Credentials!");
				return "redirect:/show/loginForm";
			}
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		logger.debug("index()");
		return "redirect:/show/loginForm";
	}

	@RequestMapping(value = "/show/loginForm", method = RequestMethod.GET)
	public String showLoginForm(Model model) {
		logger.debug("showLoginForm()");
		Login l = new Login();
		model.addAttribute("loginForm", l);
		return "admin/login";
	}
}
