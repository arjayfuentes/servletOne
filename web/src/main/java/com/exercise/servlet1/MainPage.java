package com.exercise.servlet1;

import com.exercise.servlet1.core.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainPage extends HttpServlet {
	
	PersonService personService = new PersonService();
    RoleService roleService = new RoleService();

    public MainPage() {
        System.out.println("MainPage constructor called");
    }

    //entry point of the webapp. Shows list of person.
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
            for(Person p: persons){
                System.out.print(p.getEmployed());
            }
            
	        persons.forEach(System.out::print);
	        response.setContentType("text/html");
	        response.setStatus(HttpServletResponse.SC_OK);
	        request.setAttribute("persons",persons);
	        request.getRequestDispatcher("/WEB-INF/Mainpage.jsp").forward(request, response);  // go to homepage --> Mainpag.jsp
	    }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
