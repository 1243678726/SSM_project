package cn.yq.oa.service;

import java.util.List;

public interface CustomPermissionService {
	List<Integer> selectPermissionIdsByRoleId(Integer roleId);
}
