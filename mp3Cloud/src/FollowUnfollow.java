

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
@WebServlet("/FollowUnfollow")
public class FollowUnfollow extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
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
		String uid=(String)session.getAttribute("userid");
		String operation=request.getParameter("operation");
		String follower_id=request.getParameter("follower_id");
		if(operation.equals("Follow"))
		{
			Connection con=DBInfo.getConn();
			String query1="Insert into user_followers(user_id,follower_id) values(?,?);";
			String query2="Update users set followers=followers+1 where uid=?;";
			try
			{
				PreparedStatement ps=con.prepareStatement(query1);
				ps.setString(1, uid);
				ps.setString(2, follower_id);
				ps.executeUpdate();
				ps=con.prepareStatement(query2);
				ps.setString(1, follower_id);
				ps.executeUpdate();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			Connection con=DBInfo.getConn();
			String query1="Delete from user_followers where user_id=? and follower_id=?;";
			String query2="Update users set followers=followers-1 where uid=?;";
			try
			{
				PreparedStatement ps=con.prepareStatement(query1);
				ps.setString(1, uid);
				ps.setString(2, follower_id);
				ps.executeUpdate();
				ps=con.prepareStatement(query2);
				ps.setString(1, follower_id);
				ps.executeUpdate();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		PrintWriter pw=response.getWriter();
		pw.println("<script type=\"text/javascript\">");
		pw.println("window.location.href='search#follower"+follower_id+"'");
		pw.println("</script>");
	}

}
