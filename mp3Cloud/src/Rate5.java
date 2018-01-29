

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
 * Servlet implementation class Rate
 */
@WebServlet("/Rate5")
public class Rate5 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
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
			String fwd=(String)session.getAttribute("page");
			session.removeAttribute("page");
			
			String tid=request.getParameter("id");
			System.out.println(fwd);
			System.out.println(tid);
			Connection con=DBInfo.getConn();
			String query2="Update tracks set rate_5=rate_5+1 where track_id="+tid+";";
			String querycheck="Select * from rating where u_id=? and t_id=?;";
			try
			{
				
				PreparedStatement p1=con.prepareStatement(querycheck);
				p1.setString(1, uid);
				p1.setString(2, tid);
				ResultSet rs=p1.executeQuery();
				if(!rs.next())
				{
					String query1="Insert into rating(u_id,t_id,rating) values(?,?,?);";					
					PreparedStatement ps=con.prepareStatement(query1);
					ps.setString(1, uid);
					ps.setString(2,	tid);
					ps.setInt(3, 5);
					ps.executeUpdate();
					ps=con.prepareStatement(query2);
					ps.executeUpdate();
				}
				else
				{
					
					String queryupdate;
					if(rs.getString(4).equals("1"))
					{
						queryupdate="Update tracks set rate_1=rate_1-1 where track_id="+tid+" and rate_1>0;";
					}
					else if(rs.getString(4).equals("2"))
					{
						queryupdate="Update tracks set rate_2=rate_2-1 where track_id="+tid+" and rate_2>0;";
					}
					else if(rs.getString(4).equals("3"))
					{
						queryupdate="Update tracks set rate_3=rate_3-1 where track_id="+tid+" and rate_3>0;";
					}
					else if(rs.getString(4).equals("4"))
					{
						queryupdate="Update tracks set rate_4=rate_4-1 where track_id="+tid+" and rate_4>0;";
					}
					else
					{
						queryupdate="Update tracks set rate_5=rate_5-1 where track_id="+tid+" and rate_5>0;";
					}
					PreparedStatement p2=con.prepareStatement(queryupdate);
					p2.executeUpdate();
					String query1="Update rating set rating=? where u_id=? and t_id=?;";					
					PreparedStatement ps=con.prepareStatement(query1);
					ps.setString(2, uid);
					ps.setString(3,	tid);
					ps.setInt(1, 5);
					ps.executeUpdate();
					ps=con.prepareStatement(query2);
					ps.executeUpdate();
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			PrintWriter pw=response.getWriter();
			pw.println("<script type=\"text/javascript\">");
			if(fwd.equals("1"))
			{
				
				pw.println("window.location.href='search#rating"+tid+"'");
				System.out.println("yoman1");
			}
			else
			{
				pw.println("window.location.href='MyProfile#rating"+tid+"'");
				System.out.println("yoman2");
			}
			pw.println("</script>");

		}
	}

	
}
