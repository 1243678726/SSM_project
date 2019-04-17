package cn.yq.oa.service;

import java.util.List;

import cn.yq.oa.pojo.SysRole;
import cn.yq.oa.pojo.SysRoleExample;

public interface RoleService {
	int deleteByPrimaryKey(Integer roeId);
	SysRole selectByPrimaryKey(Integer roeId);
	List<SysRole> selectByExample(SysRoleExample example);
	int updateByPrimaryKey(SysRole record);
	int updateByPrimaryKeySelective(SysRole record);
	int insertSelective(SysRole record);
}
