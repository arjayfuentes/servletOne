package com.exercise.servlet1;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exercise.servlet1.core.*;

public class ManageContacts extends HttpServlet{
	
	private PersonService personService = new PersonService(); 
	
	private static final long serialVersionUID = 1L;
	private ContactsService contactsService = new ContactsService();
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List <Person> persons = personService.getPersons("id");
		request.setAttribute("persons", persons);
		request.getRequestDispatcher("/WEB-INF/ViewContacts.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{	
		String action = request.getParameter("action");	
		if(action.equals("deleteContact")){
			long deleteIdContact = Long.parseLong(request.getParameter("deleteIdContact"));
			contactsService.deleteContact(deleteIdContact);
			response.sendRedirect(request.getContextPath()+"/ManageRoles");
		}
		if(action.equals("addContact")){
			String addContactType = request.getParameter("addContactType");
			String addContactValue = request.getParameter("addContactValue");
			String personId = request.getParameter("personId");
			Contact newContact = new Contact();
			contactsService.addContact(personId, newContact);
			request.getRequestDispatcher("/WEB-INF/Person.jsp").forward(request, response);
		}

		response.sendRedirect(request.getContextPath()+"/ManageRoles");
		
	}
	

	public ManageContacts(){
		System.out.println("ManageContacts constructor called");
	}
	
	public void init(ServletConfig config) throws ServletException{
		System.out.println("ManageContacts init() method called");
	}
	
	public void destroy(){
		System.out.println("ManageContacts destroy() method called");
	}
	
	
}