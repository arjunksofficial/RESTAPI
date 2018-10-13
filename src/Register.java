
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String  name,password, department;
	Connection con=null; 
	ServletInputStream input = null;
	ObjectMapper mapper = new ObjectMapper(); 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		input = request.getInputStream();
		try {
			Student s = mapper.readValue(input, Student.class);
			name = s.getName();
			password = s.getPassword();
			department = s.getDepartment();
			
		}
		catch (JsonParseException e) { 
			e.printStackTrace();
		} 
		catch (JsonMappingException e) { 
			e.printStackTrace(); 
		} 
		catch (IOException e) { 
			e.printStackTrace(); 
		}
		
	
		PrintWriter out=response.getWriter();
		
		try       
		{  
			
			Class.forName("com.mysql.jdbc.Driver"); 

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/STUDENT_DETAILS?useSSL=false&allowPublicKeyRetrieval=true", "qburst" , "qburstqburst" );              
			
			PreparedStatement pst = con.prepareStatement("insert into STUD values(?,?,?);");
		
			 pst.setString(1, name);
		     pst.setString(2, password);
		     pst.setString(3, department);
		    
		     
		     int x = pst.executeUpdate();
		     out.println(x);
			if(x>0)                
				out.println("Inserted Successfully");              
			else                
				out.println("Insert Unsuccessful");        
		}        
		catch(Exception e)        
		{          out.println(e);               
		}
	}
	
	

}
