

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MyProfile
 */
@WebServlet("/MyProfile")
public class MyProfile extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		HttpSession session=request.getSession();
		if(session.getAttribute("userid")==null)
		{	
			PrintWriter pw=response.getWriter();
			pw.println("<script type=\"text/javascript\">");
			
			pw.println("window.location.href='index.jsp'");
			pw.println("</script>");
		}
		else
		{
			
			String uid=(String)session.getAttribute("userid");
			
			MyProfHTML(request, response, uid);

			
		}
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		
		String email=request.getParameter("email");
		System.out.println(email);
		String password=request.getParameter("password");
		PrintWriter pw=response.getWriter();
		
		
		
		Connection con=DBInfo.getConn();
		String query="Select uid from registration where email=? && password=?;";
		try
		{
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(!rs.next())
			{
				pw.println("<script type=\"text/javascript\">");
				pw.println("alert('Wrong email or password')");
				pw.println("window.location.href='index.jsp'");
				pw.println("</script>");
			}
			else
			{
				HttpSession session=request.getSession();
				String uid=rs.getString(1);
				session.setAttribute("userid", uid);
				
				
				
				//HTML page starts
				
				
				MyProfHTML(request,response,uid);

				
				
				
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	void MyProfHTML(HttpServletRequest request,HttpServletResponse response,String uid)throws ServletException,IOException
	{
		String username=new String();
		String user_dp=new String();
		 int followers = 0,uploads=0;
		PrintWriter pw=response.getWriter();
		pw.println("<!DOCTYPE html><html lang='zxx'><head><title>Profile</title>");
		
		pw.println("<script type='application/x-javascript'> addEventListener('load', function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>");
		
		pw.println("<script src='js1/jquery-1.11.1.min.js'></script>");
		pw.println("<script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js'></script>");

		pw.println("<script src='js1/searchbox.js'></script>");
		pw.println("<link href='css1/style.css' rel='stylesheet' type='text/css' media='all' />");
		pw.println("<link rel='stylesheet' type='text/css' media='all' href='css1/audio.css'>");
		pw.println("<script type='text/javascript' src='js1/mediaelement-and-player.min.js'></script>");
		pw.println("<link href='css/bootstrap.css' rel='stylesheet' type='text/css' media='all' /><!--bootstrap-css-->");
		pw.println("<link href='css/font-awesome.css' rel='stylesheet'> <!--font-awesome-css-->");
		pw.println("<link rel='stylesheet' href='css/flexslider.css' type='text/css' media='screen' /><!--flexslider-css-->");
		pw.println("<link href='css/circles.css' rel='stylesheet' type='text/css' media='all' /><!--skill-circles-->");
		pw.println("<link href='css/style.css' rel='stylesheet' type='text/css' media='all' /><!--style-sheet-->");
		pw.println("<link href='css/aos.css' rel='stylesheet prefetch' type='text/css' media='all' /><!--Animation-effects-css-->");
		pw.println("<link href='//fonts.googleapis.com/css?family=Cabin:400,500,600,700' rel='stylesheet'>");
		pw.println("<link href='//fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700' rel='stylesheet'>");

		pw.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
		
		pw.println("<script type='text/javascript'>");
		pw.println("function StarRating1(tid){window.location.href='Rate?id='+tid}");
		pw.println("function StarRating2(tid){window.location.href='Rate2?id='+tid}");
		pw.println("function StarRating3(tid){window.location.href='Rate3?id='+tid}");
		pw.println("function StarRating4(tid){window.location.href='Rate4?id='+tid}");
		pw.println("function StarRating5(tid){window.location.href='Rate5?id='+tid}");
		
		pw.println("</script>");
		
		

		pw.println("</head><body>");
		
		
		 pw.println("<div class='header' id='home' >");
		 pw.println("<div class='header-main'>");
		 pw.println("<div class='header-top-agileits' >");
		   	 
		 pw.println("<div style='padding-left:50px;padding-right:50px'>");
		 pw.println("<div class='w3l-social' data-aos='fade-right'>");
		 pw.println("<h1><a href='index.jsp'><span style='font-size:50px'>mp3Cloud</span></a></h1>");
		 pw.println("</div>");
				
				
		 		
		 pw.println("<ul class='agile_forms' data-aos='fade-left'>");
		 
		 //search box
		 
		 
		 pw.println("<li><form action='search' method='post'>");
		 pw.println("<div class='search-box'><input name='Search' type='search' class='search-input' placeholder='Search: '>");
		 
		 pw.println("<div class='search-filters'>");
		 pw.println("<input type='radio' name='filter' value='Tracks' checked /> <span style='color:#D6D6D6'>Tracks</span>");
		 pw.println("<input type='radio' name='filter' value='People' /> <span style='color:#D6D6D6'>People</span>");
		 pw.println("<input type='radio' name='filter' value='Genre' /> <span style='color:#D6D6D6'>Genre</span>");
		 pw.println("</div>");
		 pw.println("</div></form></li>");	
		 

		 
/////////////////////////////////////////////
		 
		 pw.println("<li><a class='active' href='/mp3Cloud/EditProfile' data-toggle='modal' ><i class='fa fa-sign-in' aria-hidden='true'></i><span style='font-size:16px'>Edit Profile</span></a> </li>");
		 pw.println("<li><a class='active' href='/mp3Cloud/Logout' data-toggle='modal' ><i class='fa fa-sign-in' aria-hidden='true'></i><span style='font-size:16px'>Logout</span></a> </li>");
		 pw.println("</ul>");
		 pw.println("</div></div></div></div><br><br><br>");
		 
		
		 
		 Connection con1=DBInfo.getConn();
		 String query1="Select username,followers,uploads,user_dp from users where uid=?;";
		 
		 try
		 {
			 PreparedStatement ps1=con1.prepareStatement(query1);
			 ps1.setString(1, uid);
			 ResultSet rs1=ps1.executeQuery();
			 while(rs1.next())
			 {
				username= rs1.getString("username");
				followers=rs1.getInt("followers");
				uploads=rs1.getInt("uploads");
				user_dp=rs1.getString("user_dp");
			 }
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 pw.println("<div style='width:100%'>");		
		 pw.println("<div align='center' style='width:19%;border-style:solid;border-color:#CCCCCC;border-width:thin;float:left;'>");
		 pw.println("<div ><br><img src='..\\mp3cloud_data\\dp\\"+user_dp+"' style='border-radius:50%;width:200px;height:200px;border-style:solid;border-width:5px;border-color:#CCCCCC'>");
		 pw.println("</div><br>");
		 pw.println("<div id='data'>");
		 pw.println("<ul>");
		 
		 pw.println("<li><b>Username :</b><i>"+username+"</i></li>");
		 pw.println("<li><b>Followers:</b><i>"+followers+"</i></li>");
		 pw.println("<li><b>Uploads:</b><i>"+uploads+"</i></li>");
		 pw.println("</ul></div>");
		 
		 //upload
		 pw.println("<div>");
		 pw.println("<br><a class='active' href='#' data-toggle='modal' data-target='#uploadtrack'><b>Upload new track</b></a><br>");
		 pw.println("<div class='modal fade' id='uploadtrack' tabindex='-1' role='dialog'>");
		 pw.println("<div class='modal-dialog'>");
		
		 pw.println("<div class='modal-content'><div class='modal-header'>");
		 pw.println("<button type='button' class='close' data-dismiss='modal'>&times;</button>");
						
		 pw.println("<div class='signin-form profile'><h3 class='agileinfo_sign'>New track</h3>");	
		 pw.println("<div class='login-form'>");
		 pw.println("<form action='Upload' method='post' enctype='multipart/form-data'>");
		 pw.println("<input type='text' name='track_name' placeholder='Track name' required=''>");
		 pw.println("<input type='text' name='track_artist' placeholder='Artist' >");
		 pw.println("<input type='text' name='track_genre' placeholder='Genre' ><br>");
		 pw.println("<b>Upload album art:</b> <input type='file' name='photo' placeholder='Photo'>");
		 pw.println("<br><b>Upload track:</b> <input type='file' name='song' placeholder='Song' required=''>");		 
		 pw.println("<div class='tp'><input type='submit' value='Upload'>");
		 pw.println("</div></form></div></div></div></div></div></div>");
		 
		 
		 // con1.close();
		 
		 pw.println("</div></div>");
		
		 
		 pw.println("<div align='justify' style='padding-top:50px;width:81%;border-style:solid;border-color:#CCCCCC;border-width:thin;float:right;'>");
		 
		//songs----------------------------------------------------------------------
		 
		 
		 
		 
		 
		 String query_fetch_tracks="Select track_id from tracks where uid='"+uid+"'";
		 
		 String query_fetch_followers="Select follower_id from user_followers where user_id=?;";
		 Connection con2=DBInfo.getConn();
		 try
		 {
			 PreparedStatement ps2=con2.prepareStatement(query_fetch_followers);
			 ps2.setString(1, uid);
			 ResultSet rs2=ps2.executeQuery();
			 while(rs2.next())
			 {
				 query_fetch_tracks=query_fetch_tracks.concat(" or uid='"+rs2.getString(1)+"'");
				 
				 
			 }
			 query_fetch_tracks=query_fetch_tracks.concat(" order by Sno desc;");
			 con2.close();
		 }
		 catch(Exception ex)
		 {
			 ex.printStackTrace();
		 }
		 Connection con3=DBInfo.getConn();
		 try
		 {
			 PreparedStatement ps3=con3.prepareStatement(query_fetch_tracks);
			 ResultSet rs3=ps3.executeQuery();
			 while(rs3.next())
			 {
				 Connection con4=DBInfo.getConn();
				 String fetch_track_details="Select track_name,track_artist,date,uid,genre,(5*rate_5+4*rate_4+3*rate_3+2*rate_2+rate_1)/(rate_5+rate_4+rate_3+rate_2+rate_1),track_dp,track_file from tracks where track_id=?;";
				 PreparedStatement ps4=con4.prepareStatement(fetch_track_details);
				 ps4.setString(1, rs3.getString(1));
				 ResultSet rs4=ps4.executeQuery();
				 while(rs4.next())
				 {
				
						
						
				 
					 pw.println("<div class='player-bottom' style='position:relative'>");//
					 pw.println("<div style='position:relative;float:left' ><img src='..\\mp3cloud_data\\"+rs4.getString(7)+"' style='width:150px;height:150px;border-style:solid;border-width:1px;border-color:#CCCCCC'>");
					 pw.println("</div>");
					
					 pw.println("<!-- ratings-->");
					 HttpSession session=request.getSession();
					 session.setAttribute("page", "2");

					 pw.println("<div class='rating' style='background-color:#F9F9F9;position:relative;float:right;color:#FF0000;font-size:18px;border-style:solid;border-width:1px;border-color:#CCCCCC'>");								
			    	 pw.println("RATING: <span id='rate'>"+Math.round(rs4.getDouble(6)*10)/10.0+"</span>");
			    	 pw.println("<br><br>Rate here: ");
			    	 pw.println("<form action='Rate' method='post'>");
			    	 pw.println("<div class='starRating' style='position:relative' id='rating"+rs3.getString(1)+"'>");
			    	 	 
			    	 Connection c=DBInfo.getConn();
			    	 String queryrating="Select rating from rating where u_id=? and t_id=?;";
			    	 try
			    	 {
			    		PreparedStatement p=c.prepareStatement(queryrating);
			    		p.setString(1, uid);
			    		p.setString(2, rs3.getString(1));
			    		ResultSet r=p.executeQuery();
			    		if(!r.next())
			    		{
			    			
			    			 pw.println("<input id='rating5"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='5' onClick='StarRating5("+rs3.getString(1)+")'>");
					    	 pw.println("<label for='rating5"+rs3.getString(1)+"'>5</label>");
					    	 pw.println("<input id='rating4"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='4' onClick='StarRating4("+rs3.getString(1)+")'>");
					    	 pw.println("<label for='rating4"+rs3.getString(1)+"'>4</label>");
					    	 pw.println("<input id='rating3"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='3' onClick='StarRating3("+rs3.getString(1)+")'>");
					    	 pw.println("<label for='rating3"+rs3.getString(1)+"'>3</label>");
					    	 pw.println("<input id='rating2"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='2' onClick='StarRating2("+rs3.getString(1)+")'>");
					    	 pw.println("<label for='rating2"+rs3.getString(1)+"'>2</label>");
					    	 pw.println("<input id='rating1"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='1' onClick='StarRating1("+rs3.getString(1)+")'>");
					    	 pw.println("<label for='rating1"+rs3.getString(1)+"'>1</label>");
					    	 
			    		}
			    		else
			    		{
			    			if(r.getInt(1)==1)
			    			{
			    				
			    				pw.println("<input id='rating5"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='5' onClick='StarRating5("+rs3.getString(1)+")'>");
						    	pw.println("<label for='rating5"+rs3.getString(1)+"'>5</label>");
					    		pw.println("<input id='rating4"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='4' onClick='StarRating4("+rs3.getString(1)+")'>");
						    	 pw.println("<label for='rating4"+rs3.getString(1)+"'>4</label>");
						    	 pw.println("<input id='rating3"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='3' onClick='StarRating3("+rs3.getString(1)+")'>");
						    	 pw.println("<label for='rating3"+rs3.getString(1)+"'>3</label>");
						    	 pw.println("<input id='rating2"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='2' onClick='StarRating2("+rs3.getString(1)+")'>");
						    	 pw.println("<label for='rating2"+rs3.getString(1)+"'>2</label>");
						    	 pw.println("<input id='rating1"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='1' onClick='StarRating1("+rs3.getString(1)+")' checked>");
						    	 pw.println("<label for='rating1"+rs3.getString(1)+"'>1</label>");
						
					    
			    			}
			    			else if(r.getInt(1)==2)
			    			{
			    				
			    				pw.println("<input id='rating5"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='5' onClick='StarRating5("+rs3.getString(1)+")'>");
						    	 pw.println("<label for='rating5"+rs3.getString(1)+"'>5</label>");
						    	 pw.println("<input id='rating4"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='4' onClick='StarRating4("+rs3.getString(1)+")'>");
						    	 pw.println("<label for='rating4"+rs3.getString(1)+"'>4</label>");
						    	 pw.println("<input id='rating3"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='3' onClick='StarRating3("+rs3.getString(1)+")'>");
						    	 pw.println("<label for='rating3"+rs3.getString(1)+"'>3</label>");
						    	 pw.println("<input id='rating2"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='2' onClick='StarRating2("+rs3.getString(1)+")'checked>");
						    	 pw.println("<label for='rating2"+rs3.getString(1)+"'>2</label>");
						    	 pw.println("<input id='rating1"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='1' onClick='StarRating1("+rs3.getString(1)+")'>");
						    	 pw.println("<label for='rating1"+rs3.getString(1)+"'>1</label>");
							}
			    			else if(r.getInt(1)==3)
			    			{
			    				
			    				pw.println("<input id='rating5"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='5' onClick='StarRating5("+rs3.getString(1)+")'>");
						    	 pw.println("<label for='rating5"+rs3.getString(1)+"'>5</label>");
						    	 pw.println("<input id='rating4"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='4' onClick='StarRating4("+rs3.getString(1)+")'>");
						    	 pw.println("<label for='rating4"+rs3.getString(1)+"'>4</label>");
						    	 pw.println("<input id='rating3"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='3' onClick='StarRating3("+rs3.getString(1)+")' checked>");
						    	 pw.println("<label for='rating3"+rs3.getString(1)+"'>3</label>");
						    	 pw.println("<input id='rating2"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='2' onClick='StarRating2("+rs3.getString(1)+")'>");
						    	 pw.println("<label for='rating2"+rs3.getString(1)+"'>2</label>");
						    	 pw.println("<input id='rating1"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='1' onClick='StarRating1("+rs3.getString(1)+")'>");
						    	 pw.println("<label for='rating1"+rs3.getString(1)+"'>1</label>");
							}
			    			else if(r.getInt(1)==4)
			    			{
			    				pw.println("<input id='rating5"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='5' onClick='StarRating5("+rs3.getString(1)+")'>");
						    	 pw.println("<label for='rating5"+rs3.getString(1)+"'>5</label>");
						    	 pw.println("<input id='rating4"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='4' onClick='StarRating4("+rs3.getString(1)+")' checked>");
						    	 pw.println("<label for='rating4"+rs3.getString(1)+"'>4</label>");
						    	 pw.println("<input id='rating3"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='3' onClick='StarRating3("+rs3.getString(1)+")'>");
						    	 pw.println("<label for='rating3"+rs3.getString(1)+"'>3</label>");
						    	 pw.println("<input id='rating2"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='2' onClick='StarRating2("+rs3.getString(1)+")'>");
						    	 pw.println("<label for='rating2"+rs3.getString(1)+"'>2</label>");
						    	 pw.println("<input id='rating1"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='1' onClick='StarRating1("+rs3.getString(1)+")'>");
						    	 pw.println("<label for='rating1"+rs3.getString(1)+"'>1</label>");
							}
			    			else if(r.getInt(1)==5)
			    			{
			    				pw.println("<input id='rating5"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='5' onClick='StarRating5("+rs3.getString(1)+")' checked>");
						    	 pw.println("<label for='rating5"+rs3.getString(1)+"'>5</label>");
						    	 pw.println("<input id='rating4"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='4' onClick='StarRating4("+rs3.getString(1)+")'>");
						    	 pw.println("<label for='rating4"+rs3.getString(1)+"'>4</label>");
						    	 pw.println("<input id='rating3"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='3' onClick='StarRating3("+rs3.getString(1)+")'>");
						    	 pw.println("<label for='rating3"+rs3.getString(1)+"'>3</label>");
						    	 pw.println("<input id='rating2"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='2' onClick='StarRating2("+rs3.getString(1)+")'>");
						    	 pw.println("<label for='rating2"+rs3.getString(1)+"'>2</label>");
						    	 pw.println("<input id='rating1"+rs3.getString(1)+"' type='radio' name='rating"+rs3.getString(1)+"' value='1' onClick='StarRating1("+rs3.getString(1)+")'>");
						    	 pw.println("<label for='rating1"+rs3.getString(1)+"'>1</label>");
						}
			    						
			    		}
			    	 }
			    	 catch(Exception a)
			    	 {
			    		 a.printStackTrace();
			    	 }
			    	pw.println("</div></form>");
			    	 pw.println("</div>");
				
			    	 pw.println("<!--rating end-->");
					
					
					
			    	 pw.println("<div class='music-right' style='position:relative;float:none;padding-left:20%;width:80%;'>");
			    	 pw.println("<h2>"+rs4.getString(1)+"</h2>");
			    	 pw.println("<p>"+rs4.getString(2)+"</p>");
			    	 pw.println("<div class='audio-player' style='position:relative'>");
			    	 pw.println("<audio id='audio-player'  controls='controls'>");
			    	 pw.println("<source src='..\\mp3cloud_data\\"+rs4.getString(8)+"' type='audio/ogg'></source>");
			    	 
			    	 pw.println("</div></div></div></hr>");
			    	 
				 }
			 }
			 
		 }
		 catch(Exception exc)
		 {
			 exc.printStackTrace();
		 }
		 pw.println("</div></div>");
		 
		 
		 
		 
		 
		 
		 
		 pw.println("<script type='text/javascript' src='js/jquery-2.1.4.min.js'></script>");
		 //<!--search-bar-->
		 pw.println("<script src='js/main.js'></script>");	
		 //<!--//search-bar-->
		 //<!-- FlexSlider -->
		 pw.println("<script defer src='js/jquery.flexslider.js'></script>");
		 pw.println("<script type='text/javascript'>");
		 pw.println("$(function(){");				 
		 pw.println("});");
		 pw.println("$(window).load(function(){");
		 pw.println("$('.flexslider').flexslider({");
		 pw.println("animation: 'slide',");
		 pw.println("start: function(slider){");
		 pw.println("$('body').removeClass('loading');");
		 pw.println("}");
		 pw.println(" });");
		 pw.println("});");
		 pw.println("</script>");
		 //<!-- FlexSlider -->
		 //<!-- clients-slider-script -->
		 pw.println("<script src='js/slideshow.min.js'></script>");
		 pw.println("<script src='js/launcher.js'></script>");
		 //<!-- //clients-slider-script -->
		 //<!-- /circle-->
		 pw.println("<script type='text/javascript' src='js/circles.js'></script>");
		 pw.println("<script>");
		 pw.println("var colors = [");
		 pw.println("['#ffffff', '#fd9426'], ['#ffffff', '#fc3158'],['#ffffff', '#53d769'], ['#ffffff', '#147efb']");
		 pw.println("];");
		 pw.println("for (var i = 1; i <= 7; i++) {");
		 pw.println("var child = document.getElementById('circles-' + i),");
		 pw.println("percentage = 30 + (i * 10);");
		 pw.println("Circles.create({");
		 pw.println("id:         child.id,");
		 pw.println("percentage: percentage,");
		 pw.println("radius:     80,");
		 pw.println("width:      10,");
		 pw.println("number:   	percentage / 1,");
		 pw.println("text:       '%',");
		 pw.println("colors:     colors[i - 1]");
		 pw.println("});");
		 pw.println("}");
		 pw.println("</script>");
		 	//<!-- //circle -->
		 	//<!-- for-Map -->
		 pw.println("<script type='text/javascript'>");
		 pw.println("$(function() {");
				
		 pw.println("var menu_ul = $('.faq > li > ul'),");
		 pw.println("menu_a  = $('.faq > li > a');");
				
		 pw.println("menu_ul.hide();");
				
		 pw.println("menu_a.click(function(e) {");
		 pw.println("e.preventDefault();");
		 pw.println("if(!$(this).hasClass('active')) {");
		 pw.println("menu_a.removeClass('active');");
		 pw.println("menu_ul.filter(':visible').slideUp('normal');");
		 pw.println("$(this).addClass('active').next().stop(true,true).slideDown('normal');");
		 pw.println("} else {");
		 pw.println("$(this).removeClass('active');");
		 pw.println("$(this).next().stop(true,true).slideUp('normal');");
		 pw.println("}");
		 pw.println("});");
		 pw.println("});");
		 pw.println("</script>");
		 //<!-- //for-Map -->
		 pw.println("<script src='js/jzBox.js'></script>");
		 //<!-- start-smoth-scrolling -->
		 pw.println("<script type='text/javascript' src='js/move-top.js'></script>");
		 pw.println("<script type='text/javascript' src='js/easing.js'></script>");
		 pw.println("<script type='text/javascript'>");
		 pw.println("jQuery(document).ready(function($) {");
		 pw.println("$('.scroll').click(function(event){");		
		 pw.println("event.preventDefault();");
	     pw.println("$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);");
		 pw.println("});");
		 pw.println("});");
		 pw.println("</script>");
	     //<!-- start-smoth-scrolling -->
		 //<!-- smooth scrolling -->
		 pw.println("<script type='text/javascript'>");
		 pw.println("$(document).ready(function() {");
	     pw.println("$().UItoTop({ easingType: 'easeOutQuart' });");
		 pw.println("});");
		 pw.println("</script>");
	     //<!-- Animation-effect -->
		 pw.println("<script src='js/aos.js'></script>");
		 pw.println("<script src='js/aosindex.js'></script>");
	     //<!-- //Animation-effect -->
         pw.println("<a href='#home' class='scroll' id='toTop' style='display: block;'> <span id='toTopHover' style='opacity: 1;'> </span></a>");
		 //<!-- //smooth scrolling -->
	     pw.println("<script src='js/bootstrap.js'></script>");
		 pw.println("<br>");
		 pw.println("<br>");
		 pw.println("<div style='clear:both'>");
		 pw.println("</div>");
		 pw.println("<p style='font-size:12px'>Language: English</p>"); 
		 pw.println("<p style='font-size:12px'>Copyright © 2017 mp3Cloud. All rights reserved. Powered by mp3Cloud.</p>");
		 pw.println("</body></html>");

	}


}
