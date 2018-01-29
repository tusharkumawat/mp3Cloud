

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
 * Servlet implementation class EditProfile
 */
@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
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
			String username=new String();
			String user_dp=new String();
			 int followers = 0,uploads=0;			
			String uid=(String)session.getAttribute("userid");
			PrintWriter pw=response.getWriter();
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
			

			
			
			

			pw.println("</head><body>");
			
			
			 pw.println("<div class='header' id='home' >");
			 pw.println("<div class='header-main'>");
			 pw.println("<div class='header-top-agileits' >");
			   	 
			 pw.println("<div style='padding-left:50px;padding-right:50px'>");
			 pw.println("<div class='w3l-social' data-aos='fade-right'>");
			 pw.println("<h1><a href='index.jsp'>mp3Cloud</a></h1>");
			 pw.println("</div>");
					
					
					
			 pw.println("<ul class='agile_forms' data-aos='fade-left'>");
			 								
			 pw.println("<li><a href='/mp3Cloud/MyProfile' data-toggle='modal' ><i class='fa fa-pencil-square-o' aria-hidden='true'></i><span style='font-size:16px'>Profile</span></a> </li>");
			 pw.println("<li><a class='active' href='/mp3Cloud/Logout' data-toggle='modal' ><i class='fa fa-sign-in' aria-hidden='true'></i><span style='font-size:16px'>Logout</span></a> </li>");
			 pw.println("</ul></div></div></div></div><br><br><br>");

			
			 
			 
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
			 pw.println("<input type='text' name='track_genre' placeholder='Genre'><br>");
			 pw.println("<b>Upload album art:</b> <input type='file' name='photo' placeholder='Photo'>");
			 pw.println("<br><b>Upload track:</b> <input type='file' name='song' placeholder='Song' required=''>");		 
			 pw.println("<div class='tp'><input type='submit' value='Upload'>");
			 pw.println("</div></form></div></div></div></div></div></div>");
			 
			 
			  
			 
			 pw.println("</div></div>");
			
			 
			 pw.println("<div style='width:81%;border-style:solid;border-color:#CCCCCC;border-width:thin;float:left;'>");
			 
			 pw.println("<h2 align='left'>	Edit photo</h2><hr>");
			 pw.println("<div align='center'>");
			 pw.println("<form action='EditPOp' method='post' enctype='multipart/form-data'>");
			 
			 pw.println("<b>Upload photo:</b><br> <input type='file' name='photo' placeholder='Photo' required=''><br>");
			 pw.println("<input type='hidden' name='operation' value='dp'");
			 pw.println("<br><input type='submit' value='Upload'>");
			 pw.println("</form>");
			 pw.println("</div>");
			 pw.println("<hr>");
			 
			 
			 pw.println("<h2 align='left'>Change password</h2><hr>");
			 pw.println("<div align='center'>");
			 pw.println("<form action='ChangePass' method='post' >");
			 
			 pw.println("Old password: &nbsp &nbsp &nbsp      <input type='password' name='old' required=''> <br><br>");
			 pw.println("New password:   &nbsp &nbsp   <input type='password' name='new' required=''><br><br>");
			 pw.println("Confirm password:  <input type='password' name='conf' required=''>");
			 pw.println("<br><br>");
			 pw.println("<input type='hidden' name='operation' value='pass'>");
			 pw.println("<input type='submit' id='updatebutton'>");
			 pw.println("<br></form>");
			 
			 
			 pw.println("</div>");
			 pw.println("<hr>");
			 
			 pw.println("<h2 align='left'>Edit Tracks</h2><hr>");
			 pw.println("<div align='center'>");
			 
			 pw.println("<table style='width:70%;background-color:#E5E5E5;border:1px solid;border-color:#CCCCCC;'>");
			 pw.println("<tr style='border-bottom:1px solid;border-color:#CCCCCC;'>");
			 pw.println("<th style='width:80%'><center><b>Track</b></center></th>");
			 pw.println("<th><center><b>Delete</b></center></th>");
			 pw.println("</tr>");
			 Connection con=DBInfo.getConn();
			 String query="Select track_name,track_id from tracks where uid=?;";
			 try
			 {
				 PreparedStatement ps=con.prepareStatement(query);
				 ps.setString(1, uid);
				 ResultSet rs=ps.executeQuery();
				 while(rs.next())
				 {
					 pw.println("<tr style='border-bottom:1px solid;border-color:#CCCCCC;'>");
					 pw.println("<td>"+rs.getString(1)+"</td>");
					 pw.println("<td><center><a href='DeleteTrack?t_id="+rs.getString(2)+"'><img src='images1\\dlt.png'></a></center></td>");
					 pw.println("</tr>");
				 }
			 }
			 catch(Exception e)
			 {
				 e.printStackTrace();
			 }
			 pw.println("</table>");
			 pw.println("</div>");
			 pw.println("</br></br>");
			 
			 pw.println("</div>");//81%div
			 
			 
			 
			 
			 
			 
			 
			 
			 pw.println("</div>");//100%div
			 
			 
			 
			 
			 
			 
			 
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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

}
