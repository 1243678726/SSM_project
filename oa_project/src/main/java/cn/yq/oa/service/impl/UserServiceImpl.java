package cn.yq.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yq.oa.pojo.SysUser;
import cn.yq.oa.pojo.SysUserExample;
import cn.yq.oa.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserService service;
	@Override
	public List<SysUser> selectByExample(SysUserExample example) {
		return service.selectByExample(example);
	}

}
