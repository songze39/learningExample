package com.yile.learning.security.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YilePermissionsFilter extends PermissionsAuthorizationFilter {
	private static final Logger logger = LoggerFactory
			.getLogger(YilePermissionsFilter.class);

	public boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws IOException {
		String requestUri = getPathWithinApplication(request);
		logger.info("YilePermissionsFilter:" + requestUri);
		return super.isAccessAllowed(request, response,
				new String[] { requestUri });
	}
}
