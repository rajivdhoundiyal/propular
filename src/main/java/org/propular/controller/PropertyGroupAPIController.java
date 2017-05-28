package org.propular.controller;

import java.util.Collection;
import java.util.Properties;

import org.propular.dto.Project;
import org.propular.dto.Property;
import org.propular.service.dao.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api")
public class PropertyGroupAPIController {

	@Autowired
	ProjectRepository projectReposiory;
	
	//@PreAuthorize("#oauth2.hasScope('read') and isAnonyms()")
	@GetMapping("/properties/{projectId}/{groupName}/{env}")
	public @ResponseBody Collection<Property> getProperties(@PathVariable("projectId") String projectId, @PathVariable("groupName") String groupName,
			@PathVariable("env") String env) {
		
		Project project = projectReposiory.findByProjectId(projectId);
		return project.getPropertyGroup().get(0).getEnvProperties().iterator().next().getProperties();
		
	}
	
}
