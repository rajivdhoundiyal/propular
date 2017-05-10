package org.propular.controller;

import java.util.List;

import javax.validation.Valid;

import org.propular.dto.Environment;
import org.propular.dto.Project;
import org.propular.service.dao.EnvironmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvironmentController {

	@Autowired
	private EnvironmentRepository environmentRepositroy;
	
	@RequestMapping(value="/environment", method=RequestMethod.POST)
	public @ResponseBody List<Environment> saveProject(@RequestBody @Valid Environment environment) {
		environmentRepositroy.save(environment);
		return environmentRepositroy.findAll();
	}
	
	@RequestMapping(value="/environment", method=RequestMethod.GET)
	public @ResponseBody List<Environment> getProjects() {
		return environmentRepositroy.findAll();
	}
	
	@RequestMapping(value="/environment/{id}", method=RequestMethod.GET)
	public @ResponseBody Environment getProjectDetail(@PathVariable("id") String id) {
		return environmentRepositroy.findById(id);
	}
	
	@RequestMapping(value="/environment", method=RequestMethod.PATCH)
	public @ResponseBody List<Environment> deleteProjects(@RequestBody List<Environment> environments) {
		environmentRepositroy.delete(environments);
		return environmentRepositroy.findAll();
	}

}
