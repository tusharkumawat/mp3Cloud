

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FollowUnfollow
 */
@WebServlet("/DeleteTrack")
public class DeleteTrack extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		HttpSession session=request.getSession();
		String uid=(String)session.getAttribute("userid");
		if(uid==null)
		{
			PrintWriter pw=response.getWriter();
			pw.println("<script type=\"text/javascript\">");
			pw.println("window.location.href='index.jsp'");
			pw.println("</script>");
		}
		else
		{
		String trackid=request.getParameter("t_id");
		System.out.println(trackid);
			Connection con=DBInfo.getConn();
			String query1="Delete from tracks where track_id=?;";
			String query2="Delete from rating where t_id=?;";
			String query3="Update users set uploads=uploads-1 where uid=?";
			try
			{
				PreparedStatement ps=con.prepareStatement(query1);
				ps.setString(1, trackid);
				ps.executeUpdate();
				
				ps=con.prepareStatement(query2);
				ps.setString(1, trackid);
				ps.executeUpdate();
				
				ps=con.prepareStatement(query3);
				ps.setString(1, uid);
				ps.executeUpdate();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		PrintWriter pw=response.getWriter();
		pw.println("<script type=\"text/javascript\">");
		pw.println("alert('Track deleted!!')");
		pw.println("window.location.href='EditProfile'");
		pw.println("</script>");
	}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
				
		
	}

}
