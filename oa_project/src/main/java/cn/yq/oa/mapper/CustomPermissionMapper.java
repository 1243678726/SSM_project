package cn.yq.oa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CustomPermissionMapper {

	/**
	 * 通过角色id去查权限表 一个角色有多个权限
	 * @param roleId
	 * @return
	 */
	List<Integer> selectPermissionIdsByRoleId(Integer roleId);
	/**
	 * 删除角色权限表（sys_role_permission）中对应角色id的所有权限数据
	 * @param roleId
	 * @return
	 */
	int deleteRolePermissionByRoleId(Integer roleId);
	
	/**
	 *插入角色权限表（sys_role_permission）中对应角色id的所有权限数据
	 * @param roleId
	 * @return
	 */
	int insertRolePermissionByRoleId(@Param("roleId")Integer roleId,@Param("permissionId")Integer PermissionId);
}
