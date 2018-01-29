

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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JComboBox;

/**
 * Servlet implementation class MyProfile
 */
@WebServlet("/search")
public class search extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session=request.getSession();
		String searchvalue=request.getParameter("searchval");
		if(searchvalue!=null)
		{
			
			session.setAttribute("searchval", searchvalue);
			session.setAttribute("filterval", null);
		}
				
		SearchHTML(request,response);	
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String filtervalue=request.getParameter("filter");
		String searchvalue=request.getParameter("Search");  //from text box
		HttpSession session=request.getSession();
		session.setAttribute("searchval", searchvalue);
		session.setAttribute("filterval", filtervalue);	
		SearchHTML(request,response);	
	}
	void SearchHTML(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
	{
		
		HttpSession session=request.getSession();
        String uid=(String)session.getAttribute("userid");
        PrintWriter pw=response.getWriter();
        String name=new String();
		String username=new String();
		String user_dp=new String();
		 int followers = 0,uploads=0;
		
		
		String searchvalue=(String)session.getAttribute("searchval");
		String filtervalue=(String)session.getAttribute("filterval");
		System.out.println(searchvalue);
				
				
				//HTML page starts
				
				
				pw.println("<!DOCTYPE html><html lang='zxx'><head><title>Profile</title>");
				
				pw.println("<script type='application/x-javascript'> addEventListener('load', function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>");
				
				pw.println("<script src='js1/jquery-1.11.1.min.js'></script>");
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
				
				pw.println("<script type='text/javascript'>");

				pw.println("function check() {");
				 
				pw.println("if(document.getElementById('password1').value!='' && document.getElementById('password2').value!='')");
				pw.println("{if (document.getElementById('password1').value==document.getElementById('password2').value)");
				pw.println("{document.getElementById('message').style.color = 'green';");
				pw.println("document.getElementById('message').innerHTML = 'Passwords matching';");
				pw.println("document.getElementById('registerbutton').removeAttribute('disabled');");
				pw.println("}else {");
				pw.println("document.getElementById('message').style.color = 'red';");
				pw.println("document.getElementById('message').innerHTML = 'Passwords not matching';");
				pw.println("document.getElementById('registerbutton').setAttribute('disabled','disabled');");
				pw.println("}}}");

				pw.println("</script>"); 
						


				pw.println("</head><body>");
				
				
				 pw.println("<div class='header' id='home'>");
				 pw.println("<div class='header-main'>");
				 pw.println("<div class='header-top-agileits' >");
				   	 
				 pw.println("<div style='padding-left:50px;padding-right:50px' >");
				 pw.println("<div class='w3l-social' data-aos='fade-right' > ");
				 pw.println("<h1><a href='index.jsp'><span style='font-size:50px'>mp3Cloud</span></a></h1>");
				 pw.println("</div>");
						
						
						
				 pw.println("<ul style='verticle-align:middle' class='agile_forms' data-aos='fade-left'>");
				 pw.println("<li><form action='search' method='post'>");
				 pw.println("<div class='search-box'><input name='Search' type='search' class='search-input' placeholder='Search: '>");
				 pw.println("<div class='search-filters'>");
				 pw.println("<input type='radio' name='filter' value='Tracks'  checked /> <span style='color:#D6D6D6'>Tracks</span>");
				 if(uid!=null)
				 {
				 pw.println("<input type='radio' name='filter' value='People' /> <span style='color:#D6D6D6'>People</span>");
				 }
				 pw.println("<input type='radio' name='filter' value='Genre' /> <span style='color:#D6D6D6'>Genre</span>");
				 pw.println("</div>");
				 pw.println("</div></form></li>");
				 if(uid!=null)
				 {
					 pw.println("<li><a class='active' href='/mp3Cloud/MyProfile' data-toggle='modal' ><i class='fa fa-sign-in' aria-hidden='true'></i><span style='font-size:16px'>Profile</span></a> </li>");
					 pw.println("<li><a class='active' href='/mp3Cloud/Logout' data-toggle='modal' ><i class='fa fa-sign-in' aria-hidden='true'></i><span style='font-size:16px'>Logout</span></a> </li>");
				 }
				 else
				 {
					 pw.println("<li><a class='active' href='#' data-toggle='modal' data-target='#myModal2'><i class='fa fa-sign-in' aria-hidden='true'></i><span style='font-size:16px'>Login</span></a> </li>");
					 pw.println("<li><a href='#' data-toggle='modal' data-target='#myModal3'><i class='fa fa-pencil-square-o' aria-hidden='true'></i><span style='font-size:16px'>Register</span></a> </li>");
				 }
				 pw.println("</ul></div></div></div></div><br><br><br>");

				 pw.println("<!-- Modal2 -->");
				 pw.println("<div class='modal fade' id='myModal2' tabindex='-1' role='dialog'>");
				 pw.println("<div class='modal-dialog'>");
				 pw.println("<!-- Modal content-->");
				 pw.println("<div class='modal-content'>");
				 pw.println("<div class='modal-header'>");
				 pw.println("<button type='button' class='close' data-dismiss='modal'>&times;</button>");
				 pw.println("<div class='signin-form profile'>");
				 pw.println("<h3 class='agileinfo_sign'>Login</h3>	");
				 pw.println("<div class='login-form'>");
				 pw.println("<form action='MyProfile' method='post'>");
				 pw.println("<input type='email' name='email' placeholder='E-mail' required=''>");
				 pw.println("<input type='password' name='password' placeholder='Password' required=''>");
				 pw.println("<div class='tp'>");
				 pw.println("<input type='submit' value='Login'>");
				 pw.println("</div>");
				 pw.println("</form>");
				 pw.println("</div>");
				 pw.println("<p><a href='#' data-toggle='modal' data-target='#myModal3' > Don't have an account?</a></p>");
				 pw.println("</div>");
				 pw.println("</div>");
				 pw.println("</div>");
				 pw.println("</div>");
				 pw.println("</div>");
				 pw.println("<!-- //Modal2 -->");	
				 pw.println("<!-- Modal3 -->");
				 pw.println("<div class='modal fade' id='myModal3' tabindex='-1' role='dialog'>");
				 pw.println("<div class='modal-dialog'>");
				 pw.println("<!-- Modal content-->");
			     pw.println("<div class='modal-content'>");
				 pw.println("<div class='modal-header'>");
				 pw.println("<button type='button' class='close' data-dismiss='modal'>&times;</button>");
				 pw.println("<div class='signin-form profile'>");
				 pw.println("<h3 class='agileinfo_sign'>Register</h3>");	
				 pw.println("<div class='login-form'>");
				 pw.println("<form action='Registration' method='post'  >");
				 pw.println("<input type='text'  name='name' placeholder='Full name' required=''>");
				 pw.println("<input type='email'  name='email' placeholder='Email' required=''>");
				 pw.println("<input type='password' id='password1' name='password1' placeholder='Password' required='' onKeyUp='check()'>");
				 pw.println("<input type='password' id='password2' name='password2' placeholder='Confirm Password' required='' onKeyUp='check()'>");
				 pw.println("<span id='message'></span>");
				 pw.println("<input type='submit' value='Register' id='registerbutton'>");
				 pw.println("</form>");
				 pw.println("</div><p>By clicking register, I agree to your terms</p></div></div></div></div></div>");
				 pw.println("<!-- //Modal3 -->");
				 
				
				 if(uid!=null)
				 {
				 Connection con1=DBInfo.getConn();
				 String query1="Select name,username,followers,uploads,user_dp from users where uid=?;";
				 
				 try
				 {
					 PreparedStatement ps1=con1.prepareStatement(query1);
					 ps1.setString(1, uid);
					 ResultSet rs1=ps1.executeQuery();
					 while(rs1.next())
					 {
						name=rs1.getString("name"); 
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
				 pw.println("<li><b><i>"+name+"</i></b></li>");
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
				 
				 
				  
				 
				 pw.println("</div></div>");
				
				 }
				 else
				 {
					 pw.println("<div style='width:100%'>");		
					 pw.println("<div align='center' style='width:19%;border-style:solid;border-color:#CCCCCC;border-width:thin;float:left;'>");
				
					 pw.println("<div ><br><img src='..\\mp3cloud_data\\dp\\default.JPG' style='border-radius:50%;width:200px;height:200px;border-style:solid;border-width:5px;border-color:#CCCCCC'>");
					 pw.println("</div><br>");
					 pw.println("<div id='data'>");
					 pw.println("<ul>");
					 pw.println("<li><b><i>New User</i></b></li>");
					 pw.println("</ul></div>");
					 pw.println("<br><a class='active' href='#' data-toggle='modal' data-target='#myModal2'><i class='fa fa-sign-in' aria-hidden='true'></i><b>Upload new track</b></a><br>");
					 pw.println("</div></div>");
				 }
				 
				 
				 
				 
				 pw.println("<div align='justify' style='padding-top:50px;width:81%;border-style:solid;border-color:#CCCCCC;border-width:thin;float:right;'>");
				 
				//search items----------------------------------------------------------------------
				 String query_fetch_tracks=new String();
				 //
				 if(uid!=null)
				 {
					 if(filtervalue==null)
					 {
						 System.out.println("filter value=null");
						 query_fetch_tracks="(Select track_id from tracks where track_name='"+searchvalue+"' or track_artist='"+searchvalue+"' or genre='"+searchvalue+"' order by sno desc) union (select track_id from tracks where track_name like '%"+searchvalue+"%' or genre like '%"+searchvalue+"%' or track_artist like '%"+searchvalue+"%' order by sno desc)";
						 FuncQueryFetchTracks(uid,query_fetch_tracks,request,response);
					 }
					 
				 
					 if("Tracks".equals(filtervalue) || "Genre".equals(filtervalue))
					 {
						 System.out.println("filter value=tracks/genre");
						 if("Tracks".equals(filtervalue))
						 {
							 System.out.println("filter value=tracks");
							 query_fetch_tracks="(Select track_id from tracks where track_name='"+searchvalue+"' order by sno desc) union (select track_id from tracks where track_name like '%"+searchvalue+"%' order by sno desc) ";
							 FuncQueryFetchTracks(uid,query_fetch_tracks,request,response);
						 }
						 else
						 {
							 System.out.println("filter value=genre");
							 query_fetch_tracks="(Select track_id from tracks where genre='"+searchvalue+"' order by sno desc) union (select track_id from tracks where genre like '%"+searchvalue+"%' order by sno desc) ";
							 FuncQueryFetchTracks(uid,query_fetch_tracks,request,response);
						 }
					 }
				 }
				 else
				 {
					
					 query_fetch_tracks="(Select track_id from tracks where track_name='"+searchvalue+"' or track_artist='"+searchvalue+"' or genre='"+searchvalue+"' order by sno desc) union (select track_id from tracks where track_name like '%"+searchvalue+"%' or genre like '%"+searchvalue+"%' or track_artist like '%"+searchvalue+"%' order by sno desc)";
					 FuncQueryFetchTracks(uid,query_fetch_tracks,request,response);
				 }
				 
				 
				 
	             if(uid!=null)
	             {
	             if("People".equals(filtervalue))
				 {
	            	 System.out.println("filter value=people");
					String query_fetch_people="Select uid from users where name='"+searchvalue+"' and not(uid='"+uid+"') union select uid from users where name like '%"+searchvalue+"%' and not(uid='"+uid+"') ";
					Connection conn=DBInfo.getConn();
					try
					 {
						 PreparedStatement ps=conn.prepareStatement(query_fetch_people);
						 ResultSet rs=ps.executeQuery();
						 while(rs.next())
						 {
							 Connection con2=DBInfo.getConn();
							 String fetch_people_details="Select name,username,followers,uploads,user_dp from users where uid=?;";
							 PreparedStatement ps2=con2.prepareStatement(fetch_people_details);
							 ps2.setString(1, rs.getString(1));
							 ResultSet rs2=ps2.executeQuery();
							 while(rs2.next())
							 {
							
							    	
									
							 
								 pw.println("<div class='player-bottom' style='position:relative'id='follower"+rs.getString(1)+"'>");
								 pw.println("<div style='position:relative;float:left' ><img src='..\\mp3cloud_data\\dp\\"+rs2.getString(5)+"' style='width:150px;height:150px;border-style:solid;border-width:1px;border-color:#CCCCCC'>");
								 pw.println("</div>");
								
								 pw.println("<!-- upload/followers-->");
								

								 pw.println("<div style='background-color:#F9F9F9;position:relative;float:right;color:#FF0000;font-size:18px;border-style:solid;border-width:1px;border-color:#CCCCCC'>");								
						    	 
						    	 
						    	 pw.println("<div style='position:relative' id='follower"+rs.getString(1)+"'>");
						    	 
						    	 Connection c=DBInfo.getConn();
						    	 String queryfollower="Select uf_id from user_followers where user_id=? and follower_id=?;";
						    	 try
						    	 {
						    		 PreparedStatement p=c.prepareStatement(queryfollower);
						    		 p.setString(1, uid);
						    		 p.setString(2, rs.getString(1));
						    		 ResultSet r=p.executeQuery();
						    		 if(!r.next())
						    		 {
						    			 System.out.println("1");
						    			 pw.println("<form action='FollowUnfollow' method='post'>");
						    			 pw.println("<input type='hidden' value='"+rs.getString(1)+"' name='follower_id'>");
							    		 pw.println("<input type='submit' id='button"+rs2.getString(1)+"' value='Follow' name='operation'>");
							    		 pw.println("</form>");
						    		 }
						    		 else
						    		 {
						    			 System.out.println("2");
						    			 pw.println("<form action='FollowUnfollow' method='post'>");
						    			 pw.println("<input type='hidden' value='"+rs.getString(1)+"' name='follower_id'>");
							    		 pw.println("<input type='submit' id='button"+rs2.getString(1)+"' value='Unfollow' name='operation'>");
						    			 pw.println("</form>");
						    		 }
						    	 }
						    	 catch(Exception a)
						    	 {
						    		 a.printStackTrace();
						    	 }
						    	
			
						    	 pw.println("</div>");
						    	 pw.println("</div>");
							
						    	 pw.println("<!--rating end-->");
								
								
								
						    	 pw.println("<div class='music-right' style='position:relative;float:none;padding-left:20%;width:80%;'>");
						    	 pw.println("<h2>"+rs2.getString(1)+"</h2>");
						    	 pw.println("<p>"+rs2.getString(2)+"</p>");
						    	 pw.println("<div style='position:relative'>");
						    	 pw.println("<b>Uploads:</b> <span id='uploads'>"+rs2.getInt(4)+"</span>		<b>Followers:</b><span id='followers'>"+rs2.getInt(3)+"</span>");
						    	 pw.println("<br><br><br><br><br></div></div></div></hr>");
						    	 
							 }
						 }
						 
					 }
					 catch(Exception exc)
					 {
						 exc.printStackTrace();
					 }
					 

					
					
					
				 }
				 
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
	
void FuncQueryFetchTracks(String uid,String query_fetch_tracks,HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
{
	HttpSession session=request.getSession();
	
	Connection con=DBInfo.getConn();
	PrintWriter pw=response.getWriter();
	 try
	 {
		 PreparedStatement ps=con.prepareStatement(query_fetch_tracks);
		 ResultSet rs=ps.executeQuery();
		 while(rs.next())
		 {
			 Connection con2=DBInfo.getConn();
			 String fetch_track_details="Select track_name,track_artist,date,uid,genre,(5*rate_5+4*rate_4+3*rate_3+2*rate_2+rate_1)/(rate_5+rate_4+rate_3+rate_2+rate_1),track_dp,track_file from tracks where track_id=?;";
			 PreparedStatement ps2=con2.prepareStatement(fetch_track_details);
			 ps2.setString(1, rs.getString(1));
			 ResultSet rs2=ps2.executeQuery();
			 while(rs2.next())
			 {
			
					
					
			 
				 pw.println("<div class='player-bottom' style='position:relative' id='rating"+rs.getString(1)+"'>");
				 pw.println("<div style='position:relative;float:left' ><img src='..\\mp3cloud_data\\"+rs2.getString(7)+"' style='width:150px;height:150px;border-style:solid;border-width:1px;border-color:#CCCCCC'>");
				 pw.println("</div>");
				
				
				 
				 
				 pw.println("<!-- ratings-->");
				
				 pw.println("<div class='rating' style='background-color:#F9F9F9;position:relative;float:right;color:#FF0000;font-size:18px;border-style:solid;border-width:1px;border-color:#CCCCCC'>");								
		    	 pw.println("RATING: <span id='rate'>"+Math.round(rs2.getDouble(6)*10)/10.0+"</span>");
		    	 pw.println("<br><br>Rate here: ");
		    	 pw.println("<form action='Rate' method='post'>");
		    	 pw.println("<div class='starRating' style='position:relative' >");
		    	 if(uid!=null)
		    	 {
		    	 session.setAttribute("page", "1");	 
		    	 Connection c=DBInfo.getConn();
		    	 String queryrating="Select rating from rating where u_id=? and t_id=?;";
		    	 try
		    	 {
		    		PreparedStatement p=c.prepareStatement(queryrating);
		    		p.setString(1, uid);
		    		p.setString(2, rs.getString(1));
		    		ResultSet r=p.executeQuery();
		    		if(!r.next())
		    		{
		    			System.out.println("out");
		    			 pw.println("<input id='rating5"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='5' onClick='StarRating5("+rs.getString(1)+")'>");
				    	 pw.println("<label for='rating5"+rs.getString(1)+"'>5</label>");
				    	 pw.println("<input id='rating4"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='4' onClick='StarRating4("+rs.getString(1)+")'>");
				    	 pw.println("<label for='rating4"+rs.getString(1)+"'>4</label>");
				    	 pw.println("<input id='rating3"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='3' onClick='StarRating3("+rs.getString(1)+")'>");
				    	 pw.println("<label for='rating3"+rs.getString(1)+"'>3</label>");
				    	 pw.println("<input id='rating2"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='2' onClick='StarRating2("+rs.getString(1)+")'>");
				    	 pw.println("<label for='rating2"+rs.getString(1)+"'>2</label>");
				    	 pw.println("<input id='rating1"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='1' onClick='StarRating1("+rs.getString(1)+")'>");
				    	 pw.println("<label for='rating1"+rs.getString(1)+"'>1</label>");
				    	 
		    		}
		    		else
		    		{
		    			if(r.getInt(1)==1)
		    			{
		    				
		    				pw.println("<input id='rating5"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='5' onClick='StarRating5("+rs.getString(1)+")'>");
					    	pw.println("<label for='rating5"+rs.getString(1)+"'>5</label>");
				    		pw.println("<input id='rating4"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='4' onClick='StarRating4("+rs.getString(1)+")'>");
					    	 pw.println("<label for='rating4"+rs.getString(1)+"'>4</label>");
					    	 pw.println("<input id='rating3"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='3' onClick='StarRating3("+rs.getString(1)+")'>");
					    	 pw.println("<label for='rating3"+rs.getString(1)+"'>3</label>");
					    	 pw.println("<input id='rating2"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='2' onClick='StarRating2("+rs.getString(1)+")'>");
					    	 pw.println("<label for='rating2"+rs.getString(1)+"'>2</label>");
					    	 pw.println("<input id='rating1"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='1' onClick='StarRating1("+rs.getString(1)+")' checked>");
					    	 pw.println("<label for='rating1"+rs.getString(1)+"'>1</label>");
					
				    
		    			}
		    			else if(r.getInt(1)==2)
		    			{
		    				pw.println("<input id='rating5"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='5' onClick='StarRating5("+rs.getString(1)+")'>");
					    	 pw.println("<label for='rating5"+rs.getString(1)+"'>5</label>");
					    	 pw.println("<input id='rating4"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='4' onClick='StarRating4("+rs.getString(1)+")'>");
					    	 pw.println("<label for='rating4"+rs.getString(1)+"'>4</label>");
					    	 pw.println("<input id='rating3"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='3' onClick='StarRating3("+rs.getString(1)+")'>");
					    	 pw.println("<label for='rating3"+rs.getString(1)+"'>3</label>");
					    	 pw.println("<input id='rating2"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='2' onClick='StarRating2("+rs.getString(1)+")'checked>");
					    	 pw.println("<label for='rating2"+rs.getString(1)+"'>2</label>");
					    	 pw.println("<input id='rating1"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='1' onClick='StarRating1("+rs.getString(1)+")'>");
					    	 pw.println("<label for='rating1"+rs.getString(1)+"'>1</label>");
						}
		    			else if(r.getInt(1)==3)
		    			{
		    				pw.println("<input id='rating5"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='5' onClick='StarRating5("+rs.getString(1)+")'>");
					    	 pw.println("<label for='rating5"+rs.getString(1)+"'>5</label>");
					    	 pw.println("<input id='rating4"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='4' onClick='StarRating4("+rs.getString(1)+")'>");
					    	 pw.println("<label for='rating4"+rs.getString(1)+"'>4</label>");
					    	 pw.println("<input id='rating3"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='3' onClick='StarRating3("+rs.getString(1)+")' checked>");
					    	 pw.println("<label for='rating3"+rs.getString(1)+"'>3</label>");
					    	 pw.println("<input id='rating2"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='2' onClick='StarRating2("+rs.getString(1)+")'>");
					    	 pw.println("<label for='rating2"+rs.getString(1)+"'>2</label>");
					    	 pw.println("<input id='rating1"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='1' onClick='StarRating1("+rs.getString(1)+")'>");
					    	 pw.println("<label for='rating1"+rs.getString(1)+"'>1</label>");
						}
		    			else if(r.getInt(1)==4)
		    			{
		    				pw.println("<input id='rating5"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='5' onClick='StarRating5("+rs.getString(1)+")'>");
					    	 pw.println("<label for='rating5"+rs.getString(1)+"'>5</label>");
					    	 pw.println("<input id='rating4"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='4' onClick='StarRating4("+rs.getString(1)+")' checked>");
					    	 pw.println("<label for='rating4"+rs.getString(1)+"'>4</label>");
					    	 pw.println("<input id='rating3"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='3' onClick='StarRating3("+rs.getString(1)+")'>");
					    	 pw.println("<label for='rating3"+rs.getString(1)+"'>3</label>");
					    	 pw.println("<input id='rating2"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='2' onClick='StarRating2("+rs.getString(1)+")'>");
					    	 pw.println("<label for='rating2"+rs.getString(1)+"'>2</label>");
					    	 pw.println("<input id='rating1"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='1' onClick='StarRating1("+rs.getString(1)+")'>");
					    	 pw.println("<label for='rating1"+rs.getString(1)+"'>1</label>");
						}
		    			else if(r.getInt(1)==5)
		    			{
		    				pw.println("<input id='rating5"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='5' onClick='StarRating5("+rs.getString(1)+")' checked>");
					    	 pw.println("<label for='rating5"+rs.getString(1)+"'>5</label>");
					    	 pw.println("<input id='rating4"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='4' onClick='StarRating4("+rs.getString(1)+")'>");
					    	 pw.println("<label for='rating4"+rs.getString(1)+"'>4</label>");
					    	 pw.println("<input id='rating3"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='3' onClick='StarRating3("+rs.getString(1)+")'>");
					    	 pw.println("<label for='rating3"+rs.getString(1)+"'>3</label>");
					    	 pw.println("<input id='rating2"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='2' onClick='StarRating2("+rs.getString(1)+")'>");
					    	 pw.println("<label for='rating2"+rs.getString(1)+"'>2</label>");
					    	 pw.println("<input id='rating1"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='1' onClick='StarRating1("+rs.getString(1)+")'>");
					    	 pw.println("<label for='rating1"+rs.getString(1)+"'>1</label>");
					}
		    						
		    		}
		    	 }
		    	 catch(Exception a)
		    	 {
		    		 a.printStackTrace();
		    	 }
		    	 }
		    	 else
		    	 {
		    		 System.out.println("uid=null");
		    		 pw.println("<input id='rating5"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='5' data-toggle='modal' data-target='#myModal2'>");
			    	 pw.println("<label for='rating5"+rs.getString(1)+"'>5</label>");
			    	 pw.println("<input id='rating4"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='4' data-toggle='modal' data-target='#myModal2'>");
			    	 pw.println("<label for='rating4"+rs.getString(1)+"'>4</label>");
			    	 pw.println("<input id='rating3"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='3' data-toggle='modal' data-target='#myModal2'>");
			    	 pw.println("<label for='rating3"+rs.getString(1)+"'>3</label>");
			    	 pw.println("<input id='rating2"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='2' data-toggle='modal' data-target='#myModal2'>");
			    	 pw.println("<label for='rating2"+rs.getString(1)+"'>2</label>");
			    	 pw.println("<input id='rating1"+rs.getString(1)+"' type='radio' name='rating"+rs.getString(1)+"' value='1' data-toggle='modal' data-target='#myModal2'>");
			    	 pw.println("<label for='rating1"+rs.getString(1)+"'>1</label>");
			    	 
		    	 }

		    	 pw.println("</div></form>");
		    	 pw.println("</div>");
			
		    	 pw.println("<!--rating end-->");
				
				
				
		    	 pw.println("<div class='music-right' style='position:relative;float:none;padding-left:20%;width:80%;'>");
		    	 pw.println("<h2>"+rs2.getString(1)+"</h2>");
		    	 pw.println("<p>"+rs2.getString(2)+"</p>");
		    	 pw.println("<div class='audio-player' style='position:relative'>");
		    	 pw.println("<audio id='audio-player'  controls='controls'>");
		    	 pw.println("<source src='..\\mp3cloud_data\\"+rs2.getString(8)+"' type='audio/ogg'></source>");
		    	 pw.println("</div></div>");
		    	 
		    	 
		    	 
		    	 pw.println("</div></hr>");
		    	 
			 }
		 }
		 
	 }
	 catch(Exception exc)
	 {
		 exc.printStackTrace();
	 }
}
	


}
