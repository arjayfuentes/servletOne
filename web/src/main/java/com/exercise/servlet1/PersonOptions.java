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
import java.util.List;

public class PersonOptions extends HttpServlet{

  private static final long serialVersionUID = 1L;

  PersonService personService = new PersonService();

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    
	System.out.println("Action = Delete Person");
    System.out.println("PersonOptions DoPost method called");
	  
	String id = request.getParameter("id");
    PersonService personService = new PersonService();
    personService.deletePerson(id);
    response.sendRedirect(request.getContextPath()+"/MainPage?delete=SUCCESS");
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{


	request.getRequestDispatcher("/WEB-INF/AddPerson.jsp").forward(request, response);
    Person person = new Person();
    request.setAttribute("person",person);
    personService.addPerson(person);

	}

  public void init(ServletConfig config) throws ServletException {
      System.out.println("PersonOptions init() method called");
  }

  public void destroy() {
      System.out.println("PersonOptions destroy() method called");
  }




}
