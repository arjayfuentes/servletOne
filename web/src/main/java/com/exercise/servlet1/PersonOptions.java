package com.exercise.servlet1;

import com.exercise.servlet1.core.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PersonOptions extends HttpServlet{

  private static final long serialVersionUID = 1L;

  PersonService personService = new PersonService();
  RoleService roleService = new RoleService();
  
  /*Method is used to add or edit a person by calling Person.jsp. Id=null indicates that the method's request is for adding a person. 
   * If the requested parameter "ID" has value, method's request is for updating/editing the existing data of a person. 
   * Create new person = called from the "add person" hyperlink in the mainpage.jsp
   * Edit person = called from the edit button of the particular peson in mainpage.jsp*/
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
	  request.getRequestDispatcher("/WEB-INF/Person.jsp").forward(request, response);  
	  //go to person.jsp. getting person data info or edit info
  }

  //this method is use to delete a person by getting the parameter ID. Called from delete button of a particular person.
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	System.out.println("Action = Delete Person");
    System.out.println("PersonOptions DoPost method called");
	String id = request.getParameter("id");
    PersonService personService = new PersonService();
    personService.deletePerson(id);
    response.sendRedirect(request.getContextPath()+"/MainPage?delete=SUCCESS");
  }
  
  /* Methods below:  These are to show that the method's are being called and to display status in the console*/
  
  public void init(ServletConfig config) throws ServletException {
      System.out.println("PersonOptions init() method called");
  }

  public void destroy() {
      System.out.println("PersonOptions destroy() method called");
  }

}
