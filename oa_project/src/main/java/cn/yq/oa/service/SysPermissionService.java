package cn.yq.oa.service;

import java.util.List;

import cn.yq.oa.pojo.SysPermission;
import cn.yq.oa.pojo.SysPermissionExample;

public interface SysPermissionService {
	 int deleteByPrimaryKey(Integer id);

	    int insert(SysPermission record);

	    int insertSelective(SysPermission record);

	    List<SysPermission> selectByExample(SysPermissionExample example);

	    SysPermission selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(SysPermission record);

	    int updateByPrimaryKey(SysPermission record);
	    List<Integer> selectPermissionIdsByRoleId(Integer roleId);
}
