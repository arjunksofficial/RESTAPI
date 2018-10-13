

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String  name,password;
	ServletInputStream input = null;
	Connection con=null; 
	ObjectMapper mapper = new ObjectMapper(); 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		input = request.getInputStream();
		try {
			Student s = mapper.readValue(input, Student.class);
			name = s.getName();
			password = s.getPassword();
		
			
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
		ResultSet rs = null;
		String query="select * from STUD where name='"+name+"' and password='"+password+"';";

		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/STUDENT_DETAILS?useSSL=false&allowPublicKeyRetrieval=true", "qburst" , "qburstqburst" ); 
		
			PreparedStatement pst = con.prepareStatement(query);
			rs=pst.executeQuery(query);
			
			
			if(rs.next())
				out.println("Login Successful");
			else
				out.println("Incorrect email or password");			
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally{
			try {
				con.close();
				rs.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
