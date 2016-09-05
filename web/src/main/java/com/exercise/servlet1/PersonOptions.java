package com.exercise.servlet1;

import com.exercise.servlet1.core.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

public class PersonOptions extends HttpServlet{

  private static final long serialVersionUID = 1L;

  PersonService personService = new PersonService();
  RoleService roleService = new RoleService();
  
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	  String id = request.getParameter("id");
	  if(id==null){
		  id="";
	  }
	  else{
		  Person person= personService.getPerson(id);
		  request.setAttribute("person", person);
		  request.setAttribute("id", id);
	  }
	  request.setAttribute("roles", roleService.getRoles());
	  
	  HttpSession session = request.getSession(false);
	  if(session != null){
		String uname = (String)session.getAttribute("uname");
		String emailId = (String)session.getAttribute("emailId");
	  }
	  
	  request.getRequestDispatcher("/WEB-INF/Person.jsp").forward(request, response);
  }

  //this method is use to delete a person by getting the parameter ID. Called from delete button of a particular person.
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	String action= request.getParameter("action");
	System.out.println("Action = Delete Person");
    System.out.println("PersonOptions DoPost method called");
    if(action.equals("delete")){
		String id = request.getParameter("id");
	    PersonService personService = new PersonService();
	    personService.deletePerson(id);
	    response.sendRedirect(request.getContextPath()+"/MainPage?delete=SUCCESS");
    }
    
    
  }
  
  /* Methods below:  These are to show that the method's are being called and to display status in the console*/
  
  public void init(ServletConfig config) throws ServletException {
      System.out.println("PersonOptions init() method called");
  }

  public void destroy() {
      System.out.println("PersonOptions destroy() method called");
  }

}
