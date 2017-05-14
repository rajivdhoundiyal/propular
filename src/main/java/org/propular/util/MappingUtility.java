package org.propular.util;

import java.util.Collection;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingUtility<VO, DTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	public MappingUtility<VO, DTO> addMapping(PropertyMap<VO, DTO> propertyMap) {
		modelMapper.addMappings(propertyMap);
		return this;
	}
	
	public Collection<DTO> convertToDTO(Collection<VO> projects, Class<DTO> clazz) {
		if(projects == null) {
			return null;
		}
		return projects.stream().map(project -> modelMapper.map(project, clazz))
				.collect(Collectors.toList());
	}
	
	public DTO convertToDTO(VO object, Class<DTO> clazz) {
		if(object == null) {
			return null;
		}
		return modelMapper.map(object, clazz);
	}
	
	public Collection<VO> convertToVO(Collection<DTO> projects, Class<VO> clazz) {
		if(projects == null) {
			return null;
		}
		return projects.stream().map(project -> modelMapper.map(project, clazz))
				.collect(Collectors.toList());
	}
	
	public VO convertToVO(DTO object, Class<VO> clazz) {
		if(object == null) {
			return null;
		}
		return modelMapper.map(object, clazz);
	}
}
