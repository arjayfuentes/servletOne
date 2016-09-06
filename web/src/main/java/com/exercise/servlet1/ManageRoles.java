package com.exercise.servlet1;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.exception.ConstraintViolationException;

import com.exercise.servlet1.core.*;

public class ManageRoles extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private RoleService roleService = new RoleService();
	private PersonService personService = new PersonService();
	private Validation check = new Validation();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<Role> roles = roleService.getRoles();
		request.setAttribute("roles", roles);
		request.getRequestDispatcher("/WEB-INF/ViewRoles.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String action = request.getParameter("action");
		if(action.equals("addRole")){
			String newRole = request.getParameter("newRole");
				newRole = newRole.trim();
				if(newRole.isEmpty() || newRole.equals(null) || newRole.equals("") || newRole.length()==0){
					String addError = "Invalid. Empty input";
					request.setAttribute("addError", addError);
				}
				else if(roleService.checkRoleNameIfExist(newRole)==true){
					String addError = " Role already exist. Try another role";
					request.setAttribute("addError", addError);
				}
				else{
					roleService.addRole(newRole);
				}
		}
	
		if(action.equals("updateRole")){
			long updateIdRole = Long.parseLong(request.getParameter("updateIdRole"));
			String updateRoleName = request.getParameter("updateRoleName").trim();
			if(updateRoleName.isEmpty() || updateRoleName.equals(null) || updateRoleName.equals("") || updateRoleName.length()==0){
				String updateError = "Invalid. Empty input";
				request.setAttribute("updateError", updateError);
			}
			else if(roleService.checkRoleNameIfExist(updateRoleName)==true){
				String updateError = " Role already exist. Try another role";
				request.setAttribute("updateError", updateError);
			}
			else{
				try{
					roleService.updateRole(updateIdRole, updateRoleName);
				}
				catch (ConstraintViolationException e){
					String updateError = " Role in use. Cannot delete";
					request.setAttribute("updateError", updateError);
				}
			}
			
		}
		
		if(action.equals("deleteRole")){
			long deleteIdRole = Long.parseLong(request.getParameter("deleteIdRole"));
			Role role = roleService.getRole(deleteIdRole);
			List<Person> persons = personService.getPersons("id");
			boolean exist = false;
			for(Person p : persons){
				if(p.getRoles().contains(role)==true){
					String deleteError = " Role in use. Cannot delete";
					request.setAttribute("deleteError", deleteError);
					exist = true;
					break;
				}
			}
			if(exist==false){
				roleService.deleteRole(deleteIdRole);
			}
		}
		request.setAttribute("roles", roleService.getRoles());
		request.getRequestDispatcher("/WEB-INF/ViewRoles.jsp").forward(request, response);
	}
}