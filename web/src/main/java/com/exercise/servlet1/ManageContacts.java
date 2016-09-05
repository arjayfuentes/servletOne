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
	private ContactsService contactsService = new ContactsService();
	private Validation check = new Validation();
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String personId = request.getParameter("id");
		Person person = personService.getPerson(personId);
		request.setAttribute("person", person);
		request.setAttribute("id", personId);
		response.setContentType("text/html");
	    response.setStatus(HttpServletResponse.SC_OK);
		request.getRequestDispatcher("/WEB-INF/ViewContacts.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String action = request.getParameter("action");
		String personId = request.getParameter("id");
		Person person = personService.getPerson(personId);
		if(action.equals("updateContact")){
			  String contactId = request.getParameter("contactId");
			  String newContactValue= request.getParameter("newContactValue");
			  if(newContactValue.isEmpty() || newContactValue.equals(null) || newContactValue.equals("") || newContactValue.length()==0){
					String updateError = "Invalid. Empty input";
					request.setAttribute("updateError", updateError);
			   }
			   else if(contactsService.checkContactValueIfExist(personId,newContactValue)==true){
					String updateError = " Contact already exist. Try another contact value";
					request.setAttribute("updateError", updateError);
				}
				else{
					contactsService.updateContact(Long.valueOf(contactId),newContactValue);
					
				} 
		}
		else if(action.equals("addContact")){
			  String typeNewContact = request.getParameter("typeNewContact");
			  String valueNewContact = request.getParameter("valueNewContact").trim();
			  ContactType contactType = null;
			  String error= null;
			  switch(typeNewContact){
			  	case "LANDLINE":
			  			contactType = ContactType.LANDLINE;
			  			error = check.checkNumberContact(valueNewContact, 7);
			  			break;
			  	case "MOBILE":
			  			contactType = ContactType.MOBILE;
			  			error = check.checkNumberContact(valueNewContact,11);
			  			break;
			  	default:
			  			contactType = ContactType.EMAIL;
			  			error = check.checkEmailContact(valueNewContact);
			  			break;
			  }
			  if(!error.equals("No errors")){
				  request.setAttribute("addError", error);
			  }
			  else{
				  Contact newContact = new Contact(contactType,valueNewContact);
				  contactsService.addContact(personId, newContact);
			  }
		 }
		else if(action.equals("deleteContact")){
			  String contactId = request.getParameter("contactId");
			  contactsService.deleteContact(Long.valueOf(contactId)); 
		 }
		 
		else{}
		 request.setAttribute("person", person);
		 request.setAttribute("id", personId);
		 request.getRequestDispatcher("/WEB-INF/ViewContacts.jsp").forward(request, response);
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