

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class Read
 */
@WebServlet("/Read")
public class Read extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String  name,department;
	Connection con=null; 
	ServletInputStream input = null;
	ObjectMapper mapper = new ObjectMapper(); 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Read() {
        super();
       
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	
		PrintWriter out=response.getWriter();
		String query= "select name,department from STUD;";
		try      
		{  
		Class.forName("com.mysql.jdbc.Driver");

		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/STUDENT_DETAILS?useSSL=false", "qburst" , "qburstqburst" );              

		PreparedStatement pst = con.prepareStatement(query);
		ResultSet rd=pst.executeQuery();

		out.print("<table border='1' width='50%'");
		out.print("<tr><th>Name</th><th>Department</th></tr>");
		while(rd.next())
		{ 
		                           
		   // out.println(rd.getString("name"));
		    //out.println(rd.getString("department"));
			out.print("<tr><td>"+rd.getString("name")+"</td><td>"+rd.getString("department")+"</td></tr>");
		    out.print("\n");
		}
		out.print("</table>");
		
	}
		catch(Exception a)        
		{          out.println(a);              
		}   

	
	

}}

