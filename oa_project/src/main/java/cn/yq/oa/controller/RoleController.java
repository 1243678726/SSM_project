package cn.yq.oa.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.yq.oa.pojo.SysPermission;
import cn.yq.oa.pojo.SysPermissionExample;
import cn.yq.oa.pojo.SysRole;
import cn.yq.oa.pojo.SysRoleExample;
import cn.yq.oa.service.CustomPermissionService;
import cn.yq.oa.service.RoleService;
import cn.yq.oa.service.SysPermissionService;
import cn.yq.oa.vo.MessageObject;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private SysPermissionService sysPermissionService;
	@Autowired
	private CustomPermissionService customPermissionService;
	
	
	//进入到页面
	@RequestMapping("/rolePage.do")
	@RequiresPermissions("role:rolePage")
	public String list() {
		
		return "admin-permission";
	}
	//Ajax发送请求
	@RequestMapping("/list.do")
	@ResponseBody
	@RequiresPermissions("role:list")
	public PageInfo<SysRole> rolelist(Integer pageNum) {
		PageHelper.startPage(pageNum, 10);
		SysRoleExample example = new SysRoleExample();
		List<SysRole> roles = roleService.selectByExample(example);
		PageInfo<SysRole> pageInfo = new PageInfo<>(roles);
		System.out.println(pageInfo);
		return pageInfo;
	}
	
	@RequestMapping("/edit.do")
	public String updateRole(Integer id,Model model) {
		SysRole role = roleService.selectByPrimaryKey(id);
		model.addAttribute("role", role);
		
		return "admin-role-add";
		
	}
	/**
	 * 查出所有权限
	 * @return 权限的json串
	 */
	@RequestMapping("/getAllPermissions.do")
	@ResponseBody
	public List<SysPermission> getAllPermissions() {
		SysPermissionExample example = new SysPermissionExample();
		List<SysPermission> permissions = sysPermissionService.selectByExample(example );
		return permissions;

	}
	
	@RequestMapping("/getPermissionIdsByRoleId.do")
	@ResponseBody
	public List<Integer> getPermissionIdsByRoleId(Integer roleId){
		List<Integer> roles = sysPermissionService.selectPermissionIdsByRoleId(roleId);
		return roles;
	}
	@RequestMapping("/update.do")
	@ResponseBody
	@RequiresPermissions("role:update")
	public MessageObject updateRole(SysRole role) {
		
		System.out.println(role);
		int row = roleService.updateByPrimaryKey(role);
		MessageObject mObject=null;
		if(row>0) {
			mObject = new MessageObject(1, "修改成功");
		}else {
			mObject = new MessageObject(0, "修改失败");
			
		}
		return mObject;
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	@RequiresPermissions("role:delete")
	public MessageObject deleteRole(Integer id) {
		int row = roleService.deleteByPrimaryKey(id);
		customPermissionService.deleteRolePermissionByRoleId(id);
		MessageObject mObject=null;
		if(row>0) {
			mObject = new MessageObject(1, "删除成功");
		}else {
			mObject = new MessageObject(0, "删除失败");
			
		}
		return mObject;
	}
	
	
}
