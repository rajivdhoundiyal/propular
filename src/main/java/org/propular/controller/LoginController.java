package org.propular.controller;

import org.propular.constants.ViewName;
import org.propular.service.dao.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	private final static String PROJECTS = "projects";
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@GetMapping("/")
	@Secured("USER")
	public ModelAndView home() {
		ModelAndView home = new ModelAndView(ViewName.HOME);
		home.addObject(PROJECTS, projectRepository.findAll());
		return home;
	}
}
