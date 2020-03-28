package com.tsing.grid.util;

import com.tsing.grid.entity.sys.Admin;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * http上下文
 *
 */
public class HttpContextUtils {

    public static Admin getAdmin() {
        Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
        return admin;
    }

	public static Integer getAid() {
		return getAdmin().getId();
	}

	public static Integer getUnionId() {
		return getAdmin().getUnionId();
	}

	public static HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public  static boolean isAjaxRequest(HttpServletRequest request){
		String accept = request.getHeader("accept");
		String xRequestedWith = request.getHeader("X-Requested-With");
		// 如果是异步请求或是手机端，则直接返回信息
		return ((accept != null && accept.indexOf("application/json") != -1
				|| (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1)
		));
	}
}
