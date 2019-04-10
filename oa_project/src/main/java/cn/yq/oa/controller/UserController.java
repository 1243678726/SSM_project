package cn.yq.oa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping("/user_role.do")
	public String userRole() {
		
		return "admin-role";
	}
}
