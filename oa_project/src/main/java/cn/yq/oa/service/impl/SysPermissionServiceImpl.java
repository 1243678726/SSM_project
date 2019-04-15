package cn.yq.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yq.oa.mapper.CustomPermissionMapper;
import cn.yq.oa.mapper.SysPermissionMapper;
import cn.yq.oa.pojo.SysPermission;
import cn.yq.oa.pojo.SysPermissionExample;
import cn.yq.oa.service.SysPermissionService;
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

	@Autowired
	private SysPermissionMapper sysPermissionMapper; 
	//引入自定义mapper
	@Autowired
	private CustomPermissionMapper  customPermissionMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return sysPermissionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SysPermission record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(SysPermission record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SysPermission> selectByExample(SysPermissionExample example) {
		// TODO Auto-generated method stub
		return sysPermissionMapper.selectByExample(example);
	}

	@Override
	public SysPermission selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(SysPermission record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(SysPermission record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Integer> selectPermissionIdsByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return customPermissionMapper.selectPermissionIdsByRoleId(roleId);
	}

	@Override
	public List<String> selectPermissionsByRoleid(Integer roleId) {
		// TODO Auto-generated method stub
		return customPermissionMapper.selectPermissionsByRoleid(roleId);
	}

}
