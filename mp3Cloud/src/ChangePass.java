

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ChangePass
 */
@WebServlet("/ChangePass")
public class ChangePass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter pw=response.getWriter();
		pw.println("<script type=\"text/javascript\">");
		
		pw.println("window.location.href='index.jsp'");
		pw.println("</script>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session=request.getSession();
		Connection con=DBInfo.getConn();
		String query="Select password from registration where uid='"+(String)session.getAttribute("userid")+"';";
		PrintWriter pw=response.getWriter();
		try
		{
			PreparedStatement ps=con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				if(request.getParameter("new").equals(request.getParameter("conf")))
				{
					if(rs.getString(1).equals(request.getParameter("old")))
					{
						query="Update registration set password=? where uid=?;";
					
						ps=con.prepareStatement(query);
						ps.setString(1, request.getParameter("new"));
						ps.setString(2, (String)session.getAttribute("userid"));
						ps.executeUpdate();
					
						pw.println("<script type=\"text/javascript\">");
						pw.println("alert('Password changed!!')");						
						pw.println("window.location.href='/mp3Cloud/EditProfile'");
						pw.println("</script>");
						
					}
					else
					{
						pw.println("<script type=\"text/javascript\">");
						pw.println("alert('Incorrect input!!')");
						pw.println("window.location.href='/mp3Cloud/EditProfile'");
						pw.println("</script>");
						
					}
				}
				else
				{
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('Incorrect input!!')");						
					pw.println("window.location.href='/mp3Cloud/EditProfile'");
					pw.println("</script>");
					
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

}
