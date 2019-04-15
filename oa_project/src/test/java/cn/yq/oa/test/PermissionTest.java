package cn.yq.oa.test;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.yq.oa.service.SysPermissionService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PermissionTest {

	@Autowired
	private SysPermissionService sysPermissionService;
	@Test
	public void testName() throws Exception {
		List<String> list = sysPermissionService.selectPermissionsByRoleid(1);
		for (String string : list) {
			System.out.println(string);
		}
	}
}
