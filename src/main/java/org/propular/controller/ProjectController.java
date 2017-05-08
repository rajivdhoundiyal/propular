package org.propular.controller;

import java.util.List;

import javax.validation.Valid;

import org.propular.dto.Project;
import org.propular.service.dao.ProjectRepository;
import org.propular.service.dao.PropertyGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private PropertyGroupRepository propertyGroupRepository;
	
	@RequestMapping(value="/project", method=RequestMethod.POST)
	public @ResponseBody List<Project> saveProject(@RequestBody @Valid Project project) {
		if (project.getPropertyGroup() != null && !project.getPropertyGroup().isEmpty()) {
			project.setPropertyGroup(propertyGroupRepository.save(project.getPropertyGroup()));
		}
		projectRepository.save(project);
		return projectRepository.findAll();
	}
	
	@RequestMapping(value="/project", method=RequestMethod.GET)
	public @ResponseBody List<Project> getProjects() {
		return projectRepository.findAll();
	}
	
	@RequestMapping(value="/project/{id}", method=RequestMethod.GET)
	public @ResponseBody Project getProjectDetail(@PathVariable("id") String id) {
		return projectRepository.findByProjectId(id);
	}
	
	@RequestMapping(value="/project", method=RequestMethod.PATCH)
	public @ResponseBody List<Project> deleteProjects(@RequestBody List<Project> projects) {
		projectRepository.delete(projects);
		return projectRepository.findAll();
	}
}
