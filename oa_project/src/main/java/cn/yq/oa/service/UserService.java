package cn.yq.oa.service;

import java.util.List;

import cn.yq.oa.pojo.SysUser;
import cn.yq.oa.pojo.SysUserExample;

public interface UserService {
	
	List<SysUser> selectByExample(SysUserExample example);
}
