package cn.yq.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yq.oa.mapper.SysUserMapper;
import cn.yq.oa.pojo.SysUser;
import cn.yq.oa.pojo.SysUserExample;
import cn.yq.oa.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private SysUserMapper mapper;
	@Override
	public List<SysUser> selectByExample(SysUserExample example) {
		return mapper.selectByExample(example);
	}
	@Override
	public int insert(SysUser user) {
		return mapper.insert(user);
	}
	@Override
	public SysUser selectByPrimaryKey(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}
	@Override
	public int updateByPrimaryKeySelective(SysUser record) {
		return mapper.updateByPrimaryKeySelective(record);
		
	}
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}
	@Override
	public int insertSelective(SysUser record) {
		return mapper.insertSelective(record);
	}
	@Override
	public SysUser selectByUserCode(String usercode) {
		return mapper.selectByUserCode(usercode);
	}

}
