package com.exercise.servlet1;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exercise.servlet1.core.*;

public class ListRoles extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private RoleService roleService = new RoleService();
	
	public ListRoles(){
		System.out.println("ListRoles constructor called");
	}
	
	public void init(ServletConfig config) throws ServletException{
		System.out.println("ListRoles init() method called");
	}
	
	public void destroy(){
		System.out.println("ListRoles destroy() method called");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("ListRoles doGet() method called");
			
		List<Role> roles = roleService.getRoles();
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		request.setAttribute("roles", roles);
		request.getRequestDispatcher("/WEB-INF/ViewRoles.jsp").forward(request, response);
	}
}