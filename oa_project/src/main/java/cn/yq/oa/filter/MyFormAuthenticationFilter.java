package cn.yq.oa.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

public class MyFormAuthenticationFilter extends FormAuthenticationFilter{

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		//清除shiro 在sesion存储的上一次访问地址 shiroSavedReques
		Session session = subject.getSession(false);
		if(session!=null) {
			session.removeAttribute(WebUtils.SAVED_REQUEST_KEY);
		}
		
		return super.onLoginSuccess(token, subject, request, response);
	}
	
	//这个方法是在表单验证过滤器之前执行的。所以重写这个onAccessDenied方法来操作验证码
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		System.out.println("MyFormAuthenticationFilter.onAccessDenied()自定义");
		HttpServletRequest req = (HttpServletRequest) request;
		//获取session的随机验证码
		String rand= (String) req.getSession().getAttribute("rand");
		System.out.println(rand+"rand");
		//在通过请求对象拿到用户提交的验证码
		String yzm = request.getParameter("yzm");
		//比对验证码
		if(rand!=null) {
			if(yzm!=null) {
				if(!rand.toLowerCase().equals(yzm.toLowerCase())) {
					request.setAttribute("erroryzm", "验证码错误");
					//返回true  shiro就不执行下一步操作！
					return true;
				}
			}
			
		}
		return super.onAccessDenied(request, response, mappedValue);
	}
}
