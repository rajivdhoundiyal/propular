package org.propular.controller;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.propular.dto.Project;
import org.propular.service.dao.ProjectRepository;
import org.propular.service.dao.PropertyGroupRepository;
import org.propular.util.MappingUtility;
import org.propular.vo.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
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
	MappingUtility<ProjectVO, Project> mappingUtility; 
	
	
	@Autowired
	private PropertyGroupRepository propertyGroupRepository;
	
	@RequestMapping(value="/project", method=RequestMethod.POST)
	public @ResponseBody Collection<ProjectVO> saveProject(@RequestBody @Valid Project project) {
		if (project.getPropertyGroup() != null && !project.getPropertyGroup().isEmpty()) {
			project.setPropertyGroup(propertyGroupRepository.save(project.getPropertyGroup()));
		}
		projectRepository.save(project);
		return mappingUtility.convertToVO(projectRepository.findAll(), ProjectVO.class);
	}
	
	@RequestMapping(value="/project", method=RequestMethod.GET)
	public @ResponseBody Collection<ProjectVO> getProjects() {
		return mappingUtility.convertToVO(projectRepository.findAll(), ProjectVO.class);
	}
	
	@RequestMapping(value="/project/{id}", method=RequestMethod.GET)
	public @ResponseBody ProjectVO getProjectDetail(@PathVariable("id") String id) {
		return mappingUtility.convertToVO(projectRepository.findByProjectId(id), ProjectVO.class) ;
	}
	
	@RequestMapping(value="/project", method=RequestMethod.PATCH)
	public @ResponseBody Collection<ProjectVO> deleteProjects(@RequestBody List<Project> projects) {
		projectRepository.delete(projects);
		return mappingUtility.convertToVO(projectRepository.findAll(), ProjectVO.class);
	}
}
