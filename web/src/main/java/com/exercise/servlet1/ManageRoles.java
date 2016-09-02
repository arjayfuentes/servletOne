package com.exercise.servlet1;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exercise.servlet1.core.*;

public class ManageRoles extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private RoleService roleService = new RoleService();
	private Validation check = new Validation();
	
	public ManageRoles(){
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
		System.out.println("Action = View Roles");
			
		List<Role> roles = roleService.getRoles();
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		request.setAttribute("roles", roles);
		request.getRequestDispatcher("/WEB-INF/ViewRoles.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String action = request.getParameter("action");
		if(action.equals("addRole")){
			String newRole = request.getParameter("newRole");
			String message=check.inputString(newRole);
			if(!message.equals("Correct")){
				request.setAttribute("errors",message);
			}
			else{
				if(roleService.getRoles().contains(newRole)){
					
				}
				else{
					roleService.addRole(newRole);
				}
			}
		}
		
		if(action.equals("updateRole")){
			long updateIdRole = Long.parseLong(request.getParameter("updateIdRole"));
			String updateRoleName = request.getParameter("updateRoleName");
			String message=check.inputString(updateRoleName);
			if(!message.equals("Correct")){
				request.setAttribute("errors",message);
			}
			else{
				roleService.updateRole(updateIdRole, updateRoleName);
			}
		}
		
		if(action.equals("deleteRole")){
			long deleteIdRole = Long.parseLong(request.getParameter("deleteIdRole"));
			roleService.deleteRole(deleteIdRole);
		}

		response.sendRedirect(request.getContextPath()+"/ManageRoles");
		
	}
	
	
}