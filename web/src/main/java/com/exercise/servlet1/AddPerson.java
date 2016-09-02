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

public class AddPerson extends HttpServlet{

  private static final long serialVersionUID = 1L;

  PersonService personService = new PersonService();
  RoleService roleService = new RoleService();
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	  List<Role> roles = roleService.getRoles();
	  request.setAttribute("roles", roles);
	  request.getRequestDispatcher("/WEB-INF/Person.jsp").forward(request, response);
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	  //person name
	  String firstName = request.getParameter("firstName");
	  String middleName = request.getParameter("middleName");
	  String lastName = request.getParameter("lastName");
	  String suffix = request.getParameter("suffix");
	  String title = request.getParameter("title");
	  
	  //person other info
	  float gwa = Float.valueOf(request.getParameter("gwa"));
	  Date birthDate = convertDate(request.getParameter("birthDate"));
	  boolean employed = Boolean.parseBoolean(request.getParameter("employed"));
	  Date dateHired =  convertDate(request.getParameter("dateHired"));
	  String [] roleId = request.getParameterValues("roleId");
	  Set<Role> personRoles = new HashSet<Role>();	 
	  for(int i=0;i<roleId.length;i++){
		  long roleIdLong = Long.valueOf(roleId[i]);
		  Role role = roleService.getRole(roleIdLong);
		  personRoles.add(role);
	  }
	  
	  //person address
	  int houseNo = Integer.parseInt(request.getParameter("houseNo"));
	  String street = request.getParameter("street");
	  String barangay = request.getParameter("barangay");
	  String city = request.getParameter("city");
	  int zipCode = Integer.parseInt(request.getParameter("zipcode"));
	  Address address = new Address(houseNo, street, barangay, city, zipCode);
	  
	  //person contacts
	  Set<Contact> contactsa = new HashSet<Contact> ();
	  Contact contactNew = new Contact(ContactType.LANDLINE,"6799302");
	  contactsa.add(contactNew);
	  Person person = new Person (firstName, middleName,lastName, suffix, title, birthDate, employed, gwa, dateHired, address , contactsa, personRoles);
	  personService.addPerson(person);
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

  public void init(ServletConfig config) throws ServletException {
      System.out.println("PersonOptions init() method called");
  }

  public void destroy() {
      System.out.println("PersonOptions destroy() method called");
  }




}
