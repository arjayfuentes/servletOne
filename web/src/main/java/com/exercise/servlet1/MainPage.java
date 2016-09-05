package com.exercise.servlet1;

import com.exercise.servlet1.core.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainPage extends HttpServlet {

    PersonService personService = new PersonService();
    RoleService roleService = new RoleService();

    private static final long serialVersionUID = 1L;

    public MainPage() {
        System.out.println("MainPage constructor called");
    }

    /* This doGet method of this Servlet areaz initially called by index.html.      *
     * This serves as the home page of the webApp.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MainPage doGet() method called");
    	String sort = request.getParameter("sort");
    	String searchId = request.getParameter("searchId");
    	
        try{
        	if(sort==null)
                 sort="";
        	
        	if(searchId==null){
        		searchId="";
        	}
        	
            List<Person> persons = new ArrayList<>();
            
            if(searchId!=""){
            	System.out.println("Action: Search Person");
            	persons=personService.getPersonsSearch("id", searchId);
            }
            else if(sort.equals("sortByDateHired") && searchId==""){
            	System.out.println("Action: List Person Sort by Date Hired");
                persons= personService.getPersons("dateHired");
            }
            else if(sort.equals("sortByLastName") && searchId==""){
            	System.out.println("Action = List Person Sort by Last Name");
            	persons= personService.getPersons("lastName");
            }
            else if(sort.equals("sortByGwa") && searchId==""){
            	System.out.println("Action = List Person Sort by Gwa");
            	persons= personService.getPersonsGwa();
            }
            else{
            	System.out.println("Action = List Person Sort by id. Default");
                persons= personService.getPersons("id");
            }
            
	        persons.forEach(System.out::print);
	        response.setContentType("text/html");
	        response.setStatus(HttpServletResponse.SC_OK);
	        request.setAttribute("persons",persons);
	        request.getRequestDispatcher("/WEB-INF/Mainpage.jsp").forward(request, response); 
	        /* go to mainpage.jsp show the home page of the webApp */
	    }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    //this method is used to add new person
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
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
	   	  
	   	List<String> errors = new Validation().checkPersonValid(firstName, middleName, lastName, gwa, birthDate, employed, dateHired, roleId, houseNo, street, barangay, city, zipCode, contactMobile, contactLandline, contactEmail);
	   	  	
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
	   		  request.setAttribute("person.firstName", firstName);
	   		  request.setAttribute("person.middleName", middleName);
	   		  request.setAttribute("person.lastName", lastName);
	   		  request.setAttribute("person.suffix", suffix);
	   		  request.setAttribute("person.title", title);
	   		  	  
	   		  request.setAttribute("person.gwa", gwa);
	   		  request.setAttribute("birthDate", birthDate);
	   		  request.setAttribute("person.employed", employed);
	   		  request.setAttribute("dateHired", dateHired);
	   		  	 
	   		  request.setAttribute("person.roleId", roleId);
	   		  	 
	   		  request.setAttribute("person.address.houseNo", houseNo);
	   		  request.setAttribute("person.address.street", street);
	   		  request.setAttribute("person.address.barangay", barangay);
	   		  request.setAttribute("person.address.city", city);
	   		  request.setAttribute("person.address.zipCode", zipCode);
	   		  	
	   		  	  
	   		  request.setAttribute("mobile", contactMobile);
	   		  request.setAttribute("landline", contactLandline);
	   		  request.setAttribute("email", contactEmail);
	   		  
	   		  new Validation().clearErrors();
	   		  request.setAttribute("errors", errors);
	   		  request.getRequestDispatcher("/WEB-INF/Person.jsp").forward(request, response);
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
  
    
    public void init(ServletConfig config) throws ServletException {
        System.out.println("MainPage init() method called");
    }

    public void destroy() {
        System.out.println("MainPage destroy() method called");
    }

}
