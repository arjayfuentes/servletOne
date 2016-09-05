package com.exercise.servlet1;

import com.exercise.servlet1.core.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PersonOptions extends HttpServlet{

  private static final long serialVersionUID = 1L;

  PersonService personService = new PersonService();
  RoleService roleService = new RoleService();
  
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	  String id = request.getParameter("id");
	  request.setAttribute("roles", roleService.getRoles());
	  if(id==null){
		  id="";
		  request.getRequestDispatcher("/WEB-INF/AddPerson.jsp").forward(request, response);
	  }
	  else{
		  Person person= personService.getPerson(id);
		  request.setAttribute("person", person);
		  request.setAttribute("id", id);
		  request.getRequestDispatcher("/WEB-INF/EditPerson.jsp").forward(request, response);
	  }
  }

  
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	String action= request.getParameter("action");
    if(action.equals("deletePerson")){
		String id = request.getParameter("id");
	    PersonService personService = new PersonService();
	    personService.deletePerson(id);
	    response.sendRedirect(request.getContextPath()+"/MainPage?delete=SUCCESS");
    }
    if(action.equals("addPerson")){
    	
    	String firstName = request.getParameter("firstName");
	   	String middleName = request.getParameter("middleName");
	   	String lastName = request.getParameter("lastName");
	   	String suffix = request.getParameter("suffix");
	   	String title = request.getParameter("title");
	   	  
	   	String gwa = request.getParameter("gwa");
	   	String birthDate = request.getParameter("birthDate");
	   	String employed = request.getParameter("employed");
	   	String dateHired = request.getParameter("dateHired");
	   	 
	   	String [] roleId = request.getParameterValues("roleId");
	   	 
	   	String houseNo = request.getParameter("houseNo");
	   	String street = request.getParameter("street");
	   	String barangay = request.getParameter("barangay");
	   	String city = request.getParameter("city");
	   	String zipCode = request.getParameter("zipCode");
	   	
	   	  
	   	String contactMobile =  request.getParameter("mobile");
	   	String contactLandline =  request.getParameter("landline");
	   	String contactEmail =  request.getParameter("email");
	   	  
	   	List<String> errors = new Validation().checkAddPersonValid(firstName, middleName, lastName, gwa, birthDate, employed, dateHired, roleId, houseNo, street, barangay, city, zipCode, contactMobile, contactLandline, contactEmail);
	   	  	
	   	if(errors.isEmpty()){
	   		  
	   		float convertGwa = Float.valueOf(request.getParameter("gwa"));
	   		Date convertBirthDate = convertDate(request.getParameter("birthDate"));
	   		boolean convertEmployed = Boolean.parseBoolean(request.getParameter("employed"));
	   		Date convertDateHired =  convertDate(request.getParameter("dateHired"));
	   		Set<Role> personRoles = new HashSet<Role>();
	   		for(int i=0;i<roleId.length;i++){
	   			 personRoles.add(roleService.getRole(Long.parseLong(roleId[i])));
	   		}	 
	   		int convertHouseNo = Integer.parseInt(request.getParameter("houseNo"));
	   		int convertZipCode = Integer.parseInt(request.getParameter("zipCode"));
	   		Address address = new Address(convertHouseNo, street, barangay, city, convertZipCode);
	   		Set<Contact> personContacts = new HashSet<Contact>();
	   		  
	   		if(!contactMobile.isEmpty()){
	   			personContacts.add(new Contact(ContactType.MOBILE,contactMobile));
	   		}
	   		if(!contactLandline.isEmpty()){
	   			 personContacts.add(new Contact(ContactType.LANDLINE,contactLandline));
	   		}
	   		if(!contactEmail.isEmpty()){
	   			 personContacts.add(new Contact(ContactType.EMAIL,contactEmail));
	   		 }
	   		  
	   		  Person person = new Person(firstName, middleName, lastName, suffix, title,
	   		  convertBirthDate,convertEmployed,convertGwa, convertDateHired, address , personContacts, personRoles);
	   		  personService.addPerson(person);
	   		  response.sendRedirect(request.getContextPath() + "/MainPage?message=SUCCESS");
	   	  }
	   	  if(!errors.isEmpty()){
	   		  request.setAttribute("firstName", firstName);
	   		  request.setAttribute("middleName", middleName);
	   		  request.setAttribute("lastName", lastName);
	   		  request.setAttribute("suffix", suffix);
	   		  request.setAttribute("title", title);
	   		  	  
	   		  request.setAttribute("gwa", gwa);
	   		  request.setAttribute("birthDate", birthDate);
	   		  request.setAttribute("employed", employed);
	   		  request.setAttribute("dateHired", dateHired);
	   		  
	   		  List<Role> personRoles = new ArrayList<Role>();
	   		  for(int i=0;i<roleId.length;i++){
	   			  personRoles.add(roleService.getRole(Long.valueOf(roleId[i])));
	   		  }
	   		  
	   		  request.setAttribute("personRoles", personRoles);
	   		  request.setAttribute("roles", roleService.getRoles());	 
	   		  request.setAttribute("houseNo", houseNo);
	   		  request.setAttribute("street", street);
	   		  request.setAttribute("barangay", barangay);
	   		  request.setAttribute("city", city);
	   		  request.setAttribute("zipCode", zipCode);
	   		  	
	   		  	  
	   		  request.setAttribute("mobile", contactMobile);
	   		  request.setAttribute("landline", contactLandline);
	   		  request.setAttribute("email", contactEmail);
	   		  
	   		  new Validation().clearErrors();
	   		  request.setAttribute("errors", errors);
	   		  request.getRequestDispatcher("/WEB-INF/AddPerson.jsp").forward(request, response);
	   	  }
    }	

    if(action.equals("editPerson")){
    	String personId = request.getParameter("id");
    	String firstName = request.getParameter("firstName");
	   	String middleName = request.getParameter("middleName");
	   	String lastName = request.getParameter("lastName");
	   	String suffix = request.getParameter("suffix");
	   	String title = request.getParameter("title");
	   	  
	   	String gwa = request.getParameter("gwa");
	   	String birthDate = request.getParameter("birthDate");
	   	String employed = request.getParameter("employed");
	   	String dateHired = request.getParameter("dateHired");
	   	 
	   	String [] roleId = request.getParameterValues("roleId");
	   	 
	   	String houseNo = request.getParameter("houseNo");
	   	String street = request.getParameter("street");
	   	String barangay = request.getParameter("barangay");
	   	String city = request.getParameter("city");
	   	String zipCode = request.getParameter("zipCode");
	   	
	   	List<String> errors = new Validation().checkEditPersonValid(firstName, middleName, lastName, gwa, birthDate, employed, dateHired, roleId, houseNo, street, barangay, city, zipCode);
	   	  	
	   	if(errors.isEmpty()){
	   		float convertGwa = Float.valueOf(request.getParameter("gwa"));
	   		Date convertBirthDate = convertDate(request.getParameter("birthDate"));
	   		boolean convertEmployed = Boolean.parseBoolean(request.getParameter("employed"));
	   		Date convertDateHired =  convertDate(request.getParameter("dateHired"));
	   		Set<Role> personRoles = new HashSet<Role>();
	   		for(int i=0;i<roleId.length;i++){
	   			 personRoles.add(roleService.getRole(Long.parseLong(roleId[i])));
	   		}	 
	   		int convertHouseNo = Integer.parseInt(request.getParameter("houseNo"));
	   		int convertZipCode = Integer.parseInt(request.getParameter("zipCode"));
	   		Address address = new Address(convertHouseNo, street, barangay, city, convertZipCode);
	   	
	   		
	   		Person updatedPerson = new Person(firstName, middleName, lastName, suffix, title,
		   		  convertBirthDate,convertEmployed,convertGwa, convertDateHired, address , null , personRoles);
		   		  	
		   	personService.updatePerson(personId, updatedPerson);	  
		   	response.sendRedirect(request.getContextPath() + "/MainPage?message=SUCCESS");
	   	}
	   	if(!errors.isEmpty()){
	   		  request.setAttribute("firstName", firstName);
	   		  request.setAttribute("middleName", middleName);
	   		  request.setAttribute("lastName", lastName);
	   		  request.setAttribute("suffix", suffix);
	   		  request.setAttribute("title", title);
	   		  	  
	   		  request.setAttribute("gwa", gwa);
	   		  request.setAttribute("birthDate", birthDate);
	   		  request.setAttribute("employed", employed);
	   		  request.setAttribute("dateHired", dateHired);
	   		  
	   		  List<Role> personRoles = new ArrayList<Role>();
	   		  for(int i=0;i<roleId.length;i++){
	   			  personRoles.add(roleService.getRole(Long.valueOf(roleId[i])));
	   		  }
	   		  
	   		  request.setAttribute("personRoles", personRoles);
	   		  request.setAttribute("roles", roleService.getRoles());	 
	   		  request.setAttribute("houseNo", houseNo);
	   		  request.setAttribute("street", street);
	   		  request.setAttribute("barangay", barangay);
	   		  request.setAttribute("city", city);
	   		  request.setAttribute("zipCode", zipCode);
	   		  
	   		  new Validation().clearErrors();
	   		  request.setAttribute("errors", errors);
	   		  request.getRequestDispatcher("/WEB-INF/EditPerson.jsp").forward(request, response);
	   	  }
    }
  }
  
  public Date convertDate(String date){
		Date d = null;
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		try {
			d = df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
  }

}
