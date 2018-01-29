

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class Registration extends HttpServlet {
	

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter pw=response.getWriter();
		pw.println("<script type=\"text/javascript\">");
		pw.println("window.location.href='index.jsp'");
		pw.println("</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter pw=response.getWriter();
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String pass=request.getParameter("password1");
		
		Connection con=DBInfo.getConn();
		String query="Select uid from registration where email=?;";
		try
		{
			java.sql.PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, email);
			
			ResultSet rs=ps.executeQuery();
			
			if(!rs.next())
			{
				
				
				String uid=userid();
				
				String username=email.replaceAll("@.*", "")+uid.substring(0, 3);
				
				
				String query1="Insert into registration values(?,?,?,?,?);";
				String query2="Insert into users() values(?,?,?,?,?,?);";
				try
				{
					java.sql.PreparedStatement ps1=con.prepareStatement(query1);
					ps1.setString(1, uid);
					ps1.setString(2, email);
					ps1.setString(3, username);
					ps1.setString(4, pass);
					ps1.setString(5, "User");
					
					ps1.executeUpdate();
					
					
					java.sql.PreparedStatement ps2=con.prepareStatement(query2);
					ps2.setString(1, uid);
					ps2.setString(2, name);
					ps2.setString(3, username);
					ps2.setInt(4, 0);
					ps2.setInt(5, 0);
					ps2.setString(6, "default.JPG");
					
					
					ps2.executeUpdate();
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('Registration done')");
					pw.println("window.location.href='index.jsp'");
					pw.println("</script>");
					
					
					
				}
				catch(Exception f)
				{
					f.printStackTrace();
				}
				

					
			}
			else
			{
				pw.println("<script type=\"text/javascript\">");
				pw.println("alert('Registration failed.Email already exists')");
				pw.println("window.location.href='index.jsp'");
				pw.println("</script>");
			
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
	}

	public String userid()
	{
		String id="";
		for(int i=0;i<=6;i++)
		{
			id+=(int)((Math.random()*9)+1);
		}
		return id;
	}
	
	
}
