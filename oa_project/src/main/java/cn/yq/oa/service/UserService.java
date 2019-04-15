package cn.yq.oa.service;

import java.util.List;

import cn.yq.oa.pojo.SysUser;
import cn.yq.oa.pojo.SysUserExample;

public interface UserService {
	
	List<SysUser> selectByExample(SysUserExample example);
	int updateByPrimaryKeySelective(SysUser record);
	int insert(SysUser user);
	 int insertSelective(SysUser record);
	SysUser selectByPrimaryKey(Integer id);
	int deleteByPrimaryKey(Integer id);
	SysUser selectByUserCode(String usercode);
	
}
