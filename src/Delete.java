

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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
 * Servlet implementation class Update
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String  name;
	Connection con=null; 
	ServletInputStream input = null;
	ObjectMapper mapper = new ObjectMapper(); 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name= request.getParameter("name");
		PrintWriter out=response.getWriter();
		
		out.println("delete from stud where (name='"+name+"');");
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
			
			PreparedStatement pst = con.prepareStatement("delete from STUD where (name='"+name+"');");
		     
		     int x = pst.executeUpdate();
		     out.println(x);
			if(x>0)                
				out.println("Deleted Successfully");              
			else                
				out.println("Delete Unsuccessful");        
		}        
		catch(Exception e)        
		{          out.println(e);               
		}
	}


}