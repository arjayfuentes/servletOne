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

public class MainPage extends HttpServlet {

    PersonService personService = new PersonService();

    private static final long serialVersionUID = 1L;

    public MainPage() {
        System.out.println("MainPage constructor called");
    }

    public void init(ServletConfig config) throws ServletException {
        System.out.println("MainPage \"Init\" method called");
    }

    public void destroy() {
        System.out.println("MainPage \"Destroy\" method called");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sort = request.getParameter("sort");
        if(sort==null)
            sort="";
        String cancel = request.getParameter("cancel");
        
        System.out.println("Action! : "+sort);
        System.out.println("MainPage - DoGet method called");

        try{
            List<Person> persons = new ArrayList<>();
            switch(sort){
                case "sortByDateHired":
                    persons= personService.getPersons("dateHired");
                    break;
                case "sortByLastName":
                    persons= personService.getPersons("lastName");
                    break;
                case "sortByGwa":
                    persons= personService.getPersonsGwa();
                    break;
                default:
                    persons= personService.getPersons("id");
                    break;
            }
            persons.forEach(System.out::print);
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            request.setAttribute("persons",persons);

            request.getRequestDispatcher("/WEB-INF/Mainpage.jsp").forward(request, response);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }







}
