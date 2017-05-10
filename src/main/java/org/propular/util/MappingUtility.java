package org.propular.util;

import java.util.Collection;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingUtility<VO, DTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	public Collection<DTO> convertToDTO(Collection<VO> projects, Class<DTO> clazz) {
		return projects.stream().map(project -> modelMapper.map(project, clazz))
				.collect(Collectors.toList());
	}
	
	public DTO convertToDTO(VO object, Class<DTO> clazz) {
		return modelMapper.map(object, clazz);
	}
	
	public Collection<VO> convertToVO(Collection<DTO> projects, Class<VO> clazz) {
		return projects.stream().map(project -> modelMapper.map(project, clazz))
				.collect(Collectors.toList());
	}
	
	public VO convertToVO(DTO object, Class<VO> clazz) {
		return modelMapper.map(object, clazz);
	}
}
