package cn.yq.oa.test;



import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.yq.oa.pojo.SysUser;
import cn.yq.oa.pojo.SysUserExample;
import cn.yq.oa.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserTest {
	@Autowired
	private UserService service;

	@Test
	public void testName() throws Exception {
		SysUserExample example = new SysUserExample();
		PageHelper.startPage(1, 5);
		List<SysUser> users = service.selectByExample(example);
		for (SysUser sysUser : users) {
			System.out.println(sysUser);
		}
		PageInfo<SysUser> pageInfo = new PageInfo<>(users);
		System.out.println(pageInfo);
	}
	
	@Test
	public void testInsert() throws Exception {
		
		for (int i = 0; i < 50; i++) {
			SysUser user = new SysUser();
			user.setPassword("123");
			user.setUsername("安图曼"+i);
			user.setUsercode(i+"");
			user.setRoleId(2);
			service.insert(user);
		}
		
		
	}
}
