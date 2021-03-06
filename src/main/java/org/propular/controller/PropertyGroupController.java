package org.propular.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.propular.dto.EnvironmentProperties;
import org.propular.dto.Project;
import org.propular.dto.PropertyGroup;
import org.propular.service.dao.EnvironmentPropertiesRepository;
import org.propular.service.dao.ProjectRepository;
import org.propular.service.dao.PropertyGroupRepository;
import org.propular.util.MappingUtility;
import org.propular.vo.EnvironmentPropertiesVO;
import org.propular.vo.PropertyGroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertyGroupController {

	@Autowired
	PropertyGroupRepository propertyGroupRepository;

	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	EnvironmentPropertiesRepository environmentPropertiesRepository;

	@Autowired
	MappingUtility<PropertyGroupVO, PropertyGroup> mappingUtility;

	@Autowired
	MappingUtility<EnvironmentPropertiesVO, EnvironmentProperties> mappingUtilityEnvProp;
	
	@RequestMapping(value = "/{projectid}/propertygroup", method = RequestMethod.POST)
	public @ResponseBody Collection<PropertyGroupVO> saveProject(@PathVariable("projectid") String projectid,
			@RequestBody @Valid PropertyGroupVO propertyGroupVO) {

		Project project = projectRepository.findOne(projectid);
		
		Collection<EnvironmentProperties> envProperties = mappingUtilityEnvProp.convertToDTO(propertyGroupVO.getEnvProperties(), EnvironmentProperties.class);
		
		envProperties = environmentPropertiesRepository.save(envProperties);
		
		PropertyGroup propertyGroup = mappingUtility.convertToDTO(propertyGroupVO, PropertyGroup.class);

		propertyGroup.setEnvProperties(envProperties);
		propertyGroup = propertyGroupRepository.save(propertyGroup);
		
		if (project.getPropertyGroup() != null) {
			project.getPropertyGroup().add(propertyGroup);
		} else {
			List<PropertyGroup> propertyGroups = new ArrayList<>();
			propertyGroups.add(propertyGroup);
		}
		projectRepository.save(project);
		return mappingUtility.convertToVO(propertyGroupRepository.findAll(), PropertyGroupVO.class);
	}

	@RequestMapping(value = "/{projectid}/propertygroup", method = RequestMethod.GET)
	public @ResponseBody Collection<PropertyGroupVO> getPropertyGroups(@PathVariable("projectid") String projectid) {

		return mappingUtility.convertToVO(projectRepository.findOne(projectid).getPropertyGroup(),
				PropertyGroupVO.class);
	}

	@RequestMapping(value = "/{projectid}/propertygroup/{id}", method = RequestMethod.GET)
	public @ResponseBody PropertyGroupVO getPropertyGroup(@PathVariable("id") String id) {
		return mappingUtility.convertToVO(propertyGroupRepository.findByPropertyGroupId(id), PropertyGroupVO.class);
	}

	@RequestMapping(value = "/{projectid}/propertygroup", method = RequestMethod.PATCH)
	public @ResponseBody Collection<PropertyGroupVO> deleteProjects(@PathVariable("projectid") String projectid,
			@RequestBody List<PropertyGroupVO> propertyGroupVos) {
		Collection<PropertyGroup> propertyGroups = mappingUtility.convertToDTO(propertyGroupVos, PropertyGroup.class);
		propertyGroupRepository.delete(propertyGroups);
		return mappingUtility.convertToVO(propertyGroupRepository.findAll(), PropertyGroupVO.class);
	}

}
