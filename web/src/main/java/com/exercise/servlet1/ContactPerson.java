package com.exercise.servlet1;

import com.exercise.servlet1.core.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactPerson extends HttpServlet{

  private static final long serialVersionUID = 1L;

  PersonService personService = new PersonService();
  RoleService roleService = new RoleService();
  ContactsService contactsService= new ContactsService();
  Validation check = new Validation();
  

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	  String personId = request.getParameter("personId");
	  List<Contact> personContacts = contactsService.getContacts(personId);
	  List <Person> persons = personService.getPersons("id");
	  request.setAttribute("persons", persons);
	  request.setAttribute("contacts", personContacts);
	  request.getRequestDispatcher("/WEB-INF/ViewContacts.jsp").forward(request, response);	
	}
	 
  
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	  String action = request.getParameter("action");
	  String personId = request.getParameter("personId");
	  System.out.println(personId+"person idfgdfhghmj,");
	  List<Contact> personContacts = contactsService.getContacts(personId);
	  System.out.println(personContacts.size());
	  request.getParameter("contacts");
	  if(action.equals("update")){
		  String newContactValue = request.getParameter("contactValue");
		  String contactId= request.getParameter("newContactId");
		  System.out.println(newContactValue+" "+contactId);
		  contactsService.updateContact(Long.valueOf(contactId),newContactValue);
		  
	  }
	  request.getRequestDispatcher("/WEB-INF/Person.jsp").forward(request, response);
  }
  
  
  
  public void init(ServletConfig config) throws ServletException {
      System.out.println("ContactPerson init() method called");
  }

  public void destroy() {
      System.out.println("ContactPerson destroy() method called");
  }




}
