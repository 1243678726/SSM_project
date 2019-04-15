package cn.yq.oa.mapper;

import cn.yq.oa.pojo.SysPermission;
import cn.yq.oa.pojo.SysPermissionExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    List<SysPermission> selectByExample(SysPermissionExample example);

    SysPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

}