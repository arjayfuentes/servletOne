package com.exercise.servlet1;

import com.exercise.servlet1.core.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
        if(sort==null)
            sort="";
        try{
            List<Person> persons = new ArrayList<>();
            switch(sort){
                case "sortByDateHired":
                	System.out.println("Action: List Person Sort by Date Hired");
                    persons= personService.getPersons("dateHired");
                    break;
                case "sortByLastName":
                	System.out.println("Action = List Person Sort by Last Name");
                	persons= personService.getPersons("lastName");
                    break;
                case "sortByGwa":
                	System.out.println("Action = List Person Sort by Gwa");
                    persons= personService.getPersonsGwa();
                    break;
                default:
                	System.out.println("Action = List Person Sort by id. Default");
                    persons= personService.getPersons("id");
                    break;
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
    	System.out.println("MainPage doPost() method called");
    	request.setAttribute("roles", roleService.getRoles());
    	request.getRequestDispatcher("/WEB-INF/Person.jsp").forward(request, response);
    	
   
	}
    
    public void init(ServletConfig config) throws ServletException {
        System.out.println("MainPage init() method called");
    }

    public void destroy() {
        System.out.println("MainPage destroy() method called");
    }

}
