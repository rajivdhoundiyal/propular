package org.propular.controller;

import java.util.Collection;

import org.propular.dto.ClientGrant;
import org.propular.dto.ClientRole;
import org.propular.dto.UserRole;
import org.propular.service.dao.ClientGrantRepository;
import org.propular.service.dao.ClientRoleRepository;
import org.propular.service.dao.UserRoleRepository;
import org.propular.util.MappingUtility;
import org.propular.vo.AggregateRoleVO;
import org.propular.vo.ClientGrantVO;
import org.propular.vo.ClientRoleVO;
import org.propular.vo.UserRoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

	@Autowired
	MappingUtility<ClientRoleVO, ClientRole> mappingUtilityClient;
	
	@Autowired
	MappingUtility<UserRoleVO, UserRole> mappingUtilityUser;
	
	@Autowired
	MappingUtility<ClientGrantVO, ClientGrant> mappingUtilityGrant;
	
	@Autowired
	ClientRoleRepository clientRoleRepository;
	
	@Autowired
	UserRoleRepository userRoleRepository;
	
	@Autowired
	ClientGrantRepository clientGrantRepository;
	
	@RequestMapping(value="/role/client", method=RequestMethod.GET)
	public @ResponseBody Collection<ClientRoleVO> getClientRoles() {
		return mappingUtilityClient.convertToVO(clientRoleRepository.findAll(), ClientRoleVO.class);
	}
	
	@RequestMapping(value="/role/grant", method=RequestMethod.GET)
	public @ResponseBody Collection<ClientGrantVO> getClientGrants() {
		return mappingUtilityGrant.convertToVO(clientGrantRepository.findAll(), ClientGrantVO.class);
	}
	
	@RequestMapping(value="/role/user", method=RequestMethod.GET)
	public @ResponseBody Collection<UserRoleVO> getUserRoles() {
		return mappingUtilityUser.convertToVO(userRoleRepository.findAll(), UserRoleVO.class);
	}
	
	@RequestMapping(value="/role", method=RequestMethod.GET)
	public @ResponseBody AggregateRoleVO getRoles() {
		Collection<ClientRoleVO> clientRoleVOs =  mappingUtilityClient.convertToVO(clientRoleRepository.findAll(), ClientRoleVO.class);
		Collection<ClientGrantVO> clientGrantVOs = mappingUtilityGrant.convertToVO(clientGrantRepository.findAll(), ClientGrantVO.class);
		Collection<UserRoleVO> userRoleVOs = mappingUtilityUser.convertToVO(userRoleRepository.findAll(), UserRoleVO.class);
		
		AggregateRoleVO aggregateRoleVO = new AggregateRoleVO();
		aggregateRoleVO.setClientGrants(clientGrantVOs);
		aggregateRoleVO.setClientRoles(clientRoleVOs);
		aggregateRoleVO.setUserRoles(userRoleVOs);
		
		return aggregateRoleVO;
	}
}
