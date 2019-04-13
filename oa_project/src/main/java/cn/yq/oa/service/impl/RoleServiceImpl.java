package cn.yq.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yq.oa.mapper.CustomPermissionMapper;
import cn.yq.oa.mapper.SysRoleMapper;
import cn.yq.oa.pojo.SysRole;
import cn.yq.oa.pojo.SysRoleExample;
import cn.yq.oa.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private SysRoleMapper roleMapper;
	@Autowired
	private CustomPermissionMapper  customPermissionMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer roeId) {
	
		return roleMapper.deleteByPrimaryKey(roeId);
	}

	@Override
	public SysRole selectByPrimaryKey(Integer roeId) {
		return roleMapper.selectByPrimaryKey(roeId);
	}

	@Override
	public List<SysRole> selectByExample(SysRoleExample example) {
		// TODO Auto-generated method stub
		return roleMapper.selectByExample(example);
	}

	@Override
	public int updateByPrimaryKey(SysRole record) {
		int row = roleMapper.updateByPrimaryKeySelective(record);
		if(row==1) {
			customPermissionMapper.deleteRolePermissionByRoleId(record.getRoeId());
			//2.将用户提交的权限数据添加到角色权限表中
			Integer[] permissionIds = record.getPermissionIds();
			for (Integer permissions : permissionIds) {
				customPermissionMapper.insertRolePermissionByRoleId(record.getRoeId(), permissions);
			}
		}
		
		return row;
	}

	@Override
	public int updateByPrimaryKeySelective(SysRole record) {
		return roleMapper.updateByPrimaryKeySelective(record);
	}

}
