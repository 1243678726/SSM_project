package cn.yq.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yq.oa.mapper.CustomPermissionMapper;
@Service
public class CustomPermissionService implements cn.yq.oa.service.CustomPermissionService {

	@Autowired
	private CustomPermissionMapper customPermissionMapper; 
	
	@Override
	public List<Integer> selectPermissionIdsByRoleId(Integer roleId) {
		
		return customPermissionMapper.selectPermissionIdsByRoleId(roleId);
	}

}
