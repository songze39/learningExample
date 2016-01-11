package com.yile.learning.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.xml.ws.spi.http.HttpContext;

import org.glassfish.jersey.server.CloseableService;

public class AbstractContextResource {
	@Context
	protected UriInfo uriInfo;

	@Context
	protected Request request;

	@Context
	protected SecurityContext sc;

	@Context
	protected HttpContext hc;

	@Context
	protected CloseableService cs;

	@Context
	protected HttpServletRequest httpServletRequest;

	@Context
	protected ResourceContext resourceContext;
}
