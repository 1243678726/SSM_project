package cn.yq.oa.mapper;

import cn.yq.oa.pojo.SysUser;
import cn.yq.oa.pojo.SysUserExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

	SysUser selectByUserCode(@Param("usercode")String usercode);
}