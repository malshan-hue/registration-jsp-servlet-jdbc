package net.java.registration.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java.registration.dao.EmployeeDao;
import net.java.registration.model.Employee;
/**
 * Servlet implementation class EmployeeServelet
 */
@WebServlet("/register")
public class EmployeeServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmployeeDao employeeDao = new EmployeeDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/EmployeeRegister.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String contact = request.getParameter("contact");
		
		Employee employee = new Employee();
		
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setPassword(password);
		employee.setAddress(address);
		employee.setContact(contact);
		
		try {
			employeeDao.registerEmployee(employee);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/EmployeeDetails.jsp");
		dispatcher.forward(request, response);
	}

}
