package org.propular.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.propular.dto.EnvironmentProperties;
import org.propular.dto.PropertyGroup;
import org.propular.service.dao.PropertyGroupRepository;
import org.propular.util.MappingUtility;
import org.propular.vo.EnvironmentPropertiesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertiesController {

	@Autowired
	MappingUtility<EnvironmentPropertiesVO, EnvironmentProperties> mappingUtility;

	@Autowired
	PropertyGroupRepository propertyGroupRepository;

	@RequestMapping(value = "/{propertyGroupId}/properties", method = RequestMethod.POST)
	public Collection<EnvironmentPropertiesVO> saveProperties(@PathVariable("propertyGroupId") String propertyGroupId,
			@RequestBody @Valid EnvironmentPropertiesVO environmentPropertiesVO) {

		PropertyGroup propertyGroup = propertyGroupRepository.findOne(propertyGroupId);
		EnvironmentProperties incoming = mappingUtility.convertToDTO(environmentPropertiesVO,
				EnvironmentProperties.class);

		if (propertyGroup.getEnvProperties() == null) {
			List<EnvironmentProperties> environmentProperties = new ArrayList<>();
			environmentProperties.add(incoming);
			propertyGroup.setEnvProperties(environmentProperties);
		} else {
			propertyGroup.getEnvProperties().add(incoming);
		}

		propertyGroup = propertyGroupRepository.save(propertyGroup);

		return mappingUtility.convertToVO(propertyGroup.getEnvProperties(), EnvironmentPropertiesVO.class);
	}

	@RequestMapping(value = "/{propertyGroupId}/properties", method = RequestMethod.GET)
	public Collection<EnvironmentPropertiesVO> getAllProperties(
			@PathVariable("propertyGroupId") String propertyGroupId) {

		PropertyGroup propertyGroup = propertyGroupRepository.findOne(propertyGroupId);

		return mappingUtility.convertToVO(propertyGroup.getEnvProperties(), EnvironmentPropertiesVO.class);
	}
}
