package net.java.registration.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.java.registration.model.Employee;

public class EmployeeDao {
	
	public int registerEmployee(Employee employee) throws ClassNotFoundException{
		String INSER_USER_SQL = "INSERT INTO employee" +
								"(firstName,lastName,password,address,contact) VALUES" +
								"(?,?,?,?,?);";
		
		int result = 0;
		
		Class.forName("com.mysql.jdbc.Driver");
		
		try(Connection conn = DriverManager 
			.getConnection("jdbc:mysql://localhost:3307/employees","root","245197");
			
			PreparedStatement stmt = conn.prepareStatement(INSER_USER_SQL)){
			
			stmt.setString(1, employee.getFirstName());
			stmt.setString(2, employee.getLastName());
			stmt.setString(3, employee.getPassword());
			stmt.setString(4, employee.getAddress());
			stmt.setString(5, employee.getContact());
			
			System.out.println(stmt);
			
			result = stmt.executeUpdate();
			
		} catch (SQLException e) {
			printSQLException(e);
			
		}
		return result;

	}

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex){
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("SQLCode: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + ((SQLException) e).getMessage());
                Throwable t = ex.getCause();
                while(t != null){
                    System.out.println("Caouse: " + t);
                    t = t.getCause();
                }

            }

        }
    }
	


	
	
}
