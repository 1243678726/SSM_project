package cn.yq.oa.mapper;

import cn.yq.oa.pojo.SysRole;
import cn.yq.oa.pojo.SysRoleExample;
import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer roeId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    List<SysRole> selectByExample(SysRoleExample example);

    SysRole selectByPrimaryKey(Integer roeId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}