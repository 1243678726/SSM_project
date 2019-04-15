package cn.yq.oa.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.yq.oa.pojo.SysUser;

@Controller
public class IndexController {

	@RequestMapping("/index.do")
	public String index(Model model) {
		SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
		model.addAttribute("user", user);
		return "index";
	}
	@RequestMapping("/welcome.do")
	public String welcome() {
		
		return "welcome";
	}
}
