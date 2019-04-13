package cn.yq.oa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.yq.oa.pojo.SysRole;
import cn.yq.oa.pojo.SysRoleExample;
import cn.yq.oa.pojo.SysUser;
import cn.yq.oa.pojo.SysUserExample;
import cn.yq.oa.service.RoleService;
import cn.yq.oa.service.UserService;
import cn.yq.oa.vo.MessageObject;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService service;
	@Autowired
	private RoleService roleService;
	@RequestMapping("/userPage.do")
	public String userPage() {
		
		return "admin-role";
	}
	@RequestMapping("/insertPage.do")
	public String insertPage(Model model) {
		SysRoleExample example = new SysRoleExample();
		List<SysRole> roles = roleService.selectByExample(example);
		model.addAttribute("roles", roles);
		return "admin-insert";
	}
	@RequestMapping("/list.do")
	@ResponseBody
	public PageInfo<SysUser> list(Integer pageNum,Model model) {
		SysUserExample example = new SysUserExample();
		PageHelper.startPage(pageNum, 12);
		List<SysUser> users = service.selectByExample(example);
		model.addAttribute("users", users);
		PageInfo<SysUser> pageInfo = new PageInfo<>(users);
		System.out.println(pageInfo);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("pageNum", pageNum);
		return pageInfo;
	}
	
	@RequestMapping("/edit.do")
	public String edit(Integer id,Model model) {
		SysUser user = service.selectByPrimaryKey(id);
		System.out.println(user);
		SysRoleExample example = new SysRoleExample();
		List<SysRole> roles = roleService.selectByExample(example);
		System.out.println(roles);
		model.addAttribute("user", user);
		model.addAttribute("roles", roles);
		return "admin-add";
	}
	
	@RequestMapping("/savaOrupdate.do")
	@ResponseBody
	public MessageObject savaOrupdate(SysUser user) {
		int row = service.updateByPrimaryKeySelective(user);
		MessageObject mObject = null;
		if(row>1) {
			mObject = new MessageObject(1, "操作成功");
		}else {
			mObject = new MessageObject(0, "操作失败");
		}
		
		return mObject;
	}

	@RequestMapping("/delete.do")
	@ResponseBody
	public MessageObject delete(Integer id) {
		int row = service.deleteByPrimaryKey(id);
		MessageObject mObject = null;
		if(row>=1) {
			mObject = new MessageObject(1, "操作成功");
		}else{
			mObject = new MessageObject(0, "操作失败 联系管理员");
		}
		
		return mObject;
	}
	@RequestMapping("/insert.do")
	@ResponseBody
	public MessageObject insert(SysUser user) {
		int row = service.insertSelective(user);
		MessageObject mObject = null;
		if(row>=1) {
			mObject = new MessageObject(1, "操作成功");
		}else{
			mObject = new MessageObject(0, "操作失败 联系管理员");
		}
		return mObject;
	}
	
		
	
}
