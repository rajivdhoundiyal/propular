package org.propular.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.propular.dto.security.AppUser;
import org.propular.service.dao.AppUsersRepository;
import org.propular.util.MappingUtility;
import org.propular.vo.GetUserVO;
import org.propular.vo.SaveUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	MappingUtility<GetUserVO, AppUser> mappingUtility;
	
	@Autowired
	MappingUtility<SaveUserVO, AppUser> mappingUtilitySave;
	
	@Autowired
	AppUsersRepository appUsersRepository;
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public @ResponseBody Collection<GetUserVO> getClientRoles() {
		return mappingUtility.convertToVO(appUsersRepository.findAll(), GetUserVO.class);
	}
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public @ResponseBody Collection<GetUserVO> save(@RequestBody @Valid SaveUserVO userVO) {
		AppUser appUserUser = mappingUtilitySave.convertToDTO(userVO, AppUser.class);
		appUsersRepository.save(appUserUser);
		return mappingUtility.convertToVO(appUsersRepository.findAll(), GetUserVO.class);
	}
	
}
