<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="p1.DBInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html lang="zxx">
<head>
<title>Welcome - mp3Cloud</title>
<!-- Meta-Tags -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } </script>
		
		
		
		
		<script type="text/javascript">

function check() {
 
	 if(document.getElementById('password1').value!="" && document.getElementById('password2').value!="")
	 {
	 	
	 
	 
	 
	 
	 if (document.getElementById('password1').value ==
    document.getElementById('password2').value)
	 {
    document.getElementById('message').style.color = 'green';
    document.getElementById('message').innerHTML = 'Passwords matching';
	document.getElementById('registerbutton').removeAttribute('disabled');
  } 
  else {
    document.getElementById('message').style.color = 'red';
    document.getElementById('message').innerHTML = 'Passwords not matching';
	document.getElementById('registerbutton').setAttribute('disabled','disabled');
  }
	
	}
	
	
}

</script> 
		
		
		
		
		
		
<!-- //Meta-Tags -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" /><!--bootstrap-css-->
<link href="css/font-awesome.css" rel="stylesheet"> <!--font-awesome-css-->
<link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen" /><!--flexslider-css-->
<link href="css/circles.css" rel="stylesheet" type="text/css" media="all" /><!--skill-circles-->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" /><!--style-sheet-->
<link href='css/aos.css' rel='stylesheet prefetch' type="text/css" media="all" /><!--Animation-effects-css-->
<!--fonts-->
<link href="//fonts.googleapis.com/css?family=Cabin:400,500,600,700" rel="stylesheet">
<link href="//fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
<!--//fonts-->

</head>
<body>
<!--banner start here-->
<div class="banner" id="home">
<div class="agileinfo-dot">
   <div class="header">
		   <div class="header-main">
			 <div class="header-top-agileits" >
		   	 
			 <div style="padding-left:50px;padding-right:50px">
				<div class="w3l-social" data-aos="fade-right">
					<h1 style=""><a href="index.jsp">mp3Cloud</a></h1>
				</div>
				
				<ul class="agile_forms" data-aos="fade-left">
				<%
				HttpSession sess=request.getSession();
				String ss=(String)sess.getAttribute("userid");
				if(ss!=null)
				{
				 %>
					<li><a class="active" href="MyProfile" ><i class="fa fa-sign-in" aria-hidden="true"></i><span style='font-size:16px'>Profile</span></a> </li>
					<li><a class="active" href="Logout" ><i class="fa fa-pencil-square-o" aria-hidden="true"></i><span style='font-size:16px'>Logout</span></a> </li>
				<%
				}
				else
				{
				%>
					<li><a class="active" href="#" data-toggle="modal" data-target="#myModal2"><i class="fa fa-sign-in" aria-hidden="true"></i><span style='font-size:16px'>Login</span></a> </li>
					<li><a class="active" href="#" data-toggle="modal" data-target="#myModal3"><i class="fa fa-pencil-square-o" aria-hidden="true"></i><span style='font-size:16px'>Register</span></a> </li>
				<%
				}
				%>
				</ul>
				<div class="clearfix"> </div>
				</div>
			</div>
		   	 <div class="container">
			<nav class="navbar navbar-default">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>S
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
					
					</div>
					<!-- navbar-header -->
					<br>
					<br>
					<br>
					<div class="collapse navbar-collapse cl-effect-13" id="bs-example-navbar-collapse-1">

						<ul class="nav navbar-nav navbar-right">
							<li><a href="index.jsp" class="active scroll">Home</a></li>
							<li><a href="#about" class="scroll">About</a></li>
							<li><a href="#tracks" class="scroll">Top tracks</a></li>
							
							
							
						</ul>

					</div>

					<div class="w3ls_search">
													<div class="cd-main-header">
														<ul class="cd-header-buttons">
															<li><a class="cd-search-trigger" href="#cd-search"> <span></span></a></li>
														</ul> <!-- cd-header-buttons -->
													</div>
													<div id="cd-search" class="cd-search">
														<form action="search" method="post">
															<input name="Search" type="search" placeholder="Click enter after typing">
														</form>
													</div>
												</div>
					

					<div class="clearfix"> </div>	
				</nav>
		   </div>
		   </div>
<div class="container">
		   <div class="banner-bottom">
		   	   <section class="slider">
		   	     <div class="flexslider">
                   <ul class="slides">
                     <li>
                       <div class="banner-bottom-text">
                         <h3>Upload your sounds, music, podcasts to the world</h3>
                       </div>
                     </li>
                     <li>
                       <div class="banner-bottom-text">
                         <h3>Connect with our community and increase your audience</h3>
                       </div>
                     </li>
                     <li>
                       <div class="banner-bottom-text">
                         <h3>Explore new music, discover new people and engage with artists</h3>
                       </div>
                     </li>
                     
                   </ul>
	   	         </div>
		   	     <div class="clearfix"> </div>
		      </section>
			<div class="thim-click-to-bottom">
				<a href="#about" class="scroll">
					<i class="fa fa-chevron-down" aria-hidden="true"></i>
				</a>
			</div>
			
	    </div>
	  </div>
	</div>
</div>
</div>
<!--banner end here-->
	<!-- Modal2 -->
		<div class="modal fade" id="myModal2" tabindex="-1" role="dialog">
														<div class="modal-dialog">
														<!-- Modal content-->
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close" data-dismiss="modal">&times;</button>
																	
																	<div class="signin-form profile">
																	<h3 class="agileinfo_sign">Login</h3>	
																			<div class="login-form">
																				<form action="MyProfile" method="post">
																					<input type="email" name="email" placeholder="E-mail" required="">
																					<input type="password" name="password" placeholder="Password" required="">
																					<div class="tp">
																						<input type="submit" value="Login">
																					</div>
																				</form>
																			</div>
																			
																			<p><a href="#" data-toggle="modal" data-target="#myModal3" > Don't have an account?</a></p>
																		</div>
																</div>
															</div>
														</div>
													</div>
													<!-- //Modal2 -->	
													<!-- Modal3 -->
													<div class="modal fade" id="myModal3" tabindex="-1" role="dialog">
														<div class="modal-dialog">
														<!-- Modal content-->
														
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close" data-dismiss="modal">&times;</button>
																	
																	<div class="signin-form profile">
																	<h3 class="agileinfo_sign">Register</h3>	
																			<div class="login-form">
																				<form action="Registration" method="post"  >
																				    <input type="text"  name="name" placeholder="Full name" required="">
																					<input type="email"  name="email" placeholder="Email" required="">
																					<input type="password" id="password1" name="password1" placeholder="Password" required="" onKeyUp="check()">
																				   <input type="password" id="password2" name="password2" placeholder="Confirm Password" required="" onKeyUp="check()">
																					<span id="message"></span>
																					
																					<input type="submit" value="Register" id="registerbutton">
																				</form>
																			</div>
																			<p>By clicking register, I agree to your terms</p>
																		</div>
																</div>
															</div>
														</div>
													</div>
													
		<!-- //Modal3 -->
<!--banner bottom-->
<div class="banner-btm-w3layouts" id="about">
	<div class="container">
	<div class="tittle-agileinfo">
		<span>01</span>
		<h2>Creaters & Listeners</h2>
		
	</div>
		<div class="about-main">
		<div class="col-md-4 about-grids" data-aos="zoom-in">
			<i class="fa fa-bookmark-o" aria-hidden="true"></i>
			<h3 class="subheading-agileits-w3layouts">Upload</h3>
			<p class="para-agileits-w3layouts">Upload your sounds, music, podcasts to the world.</p>
		</div>
		<div class="col-md-4 about-grids" data-aos="zoom-in">
			<i class="fa fa-handshake-o" aria-hidden="true"></i>
			<h3 class="subheading-agileits-w3layouts">Audience</h3>
			<p class="para-agileits-w3layouts">Connect with our community and increase your audience.</p>
		</div>
		<div class="col-md-4 about-grids" data-aos="zoom-in">
			<i class="fa fa-money" aria-hidden="true"></i>
			<h3 class="subheading-agileits-w3layouts">Explore</h3>
			<p class="para-agileits-w3layouts">Explore new music, discover new people and engage with artists.</p>
		</div>
		<div class="clearfix"> </div>	
		</div>
	</div>
</div>


<!-- Top tracks -->
	<div class="gallery" id="tracks">
	<div class="container">
	<div class="tittle-agileinfo">
		<span>02</span>
		<h3>Top tracks</h3>
		
	</div>
	</div>
	 <div class="w3layouts-grids gal-wthree-agileits">
			<div id="jzBox" class="jzBox">
				<div id="jzBoxNextBig"></div>
				<div id="jzBoxPrevBig"></div>
				<img src="#" id="jzBoxTargetImg" alt=""/>
				<div id="jzBoxBottom">
					<div id="jzBoxTitle"></div>
					<div id="jzBoxMoreItems">
						<div id="jzBoxCounter"></div>
						<i class="arrow-left" id="jzBoxPrev"></i> 
						<i class="arrow-right" id="jzBoxNext"></i> 
					</div>
					<i class="close" id="jzBoxClose"></i>
				</div>
			</div>
			<!--//banner bottom-->
<% 	Connection con=DBInfo.getConn();
	String query="Select track_id,track_name,track_dp from tracks order by (rate_1*1+rate_2*2+rate_3*3+rate_4*4+rate_5*5)/(rate_1+rate_2+rate_3+rate_4+rate_5) desc limit 8";
	try
	{
		
		PreparedStatement ps=con.prepareStatement(query);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
		%>
		
			<div class="gallery-grids-row" >
				<div class="col-md-3 gallery-grid">
					<div class="wpf-demo-4">  
					
						<a href="search?searchval=<%=rs.getString(2) %>"  >  
							<img src="..\\mp3cloud_data\\<%=rs.getString(3) %>" alt=" " class="img-responsive" />
							<div class="view-caption">
								<p><%=rs.getString(2) %></p>
							</div> 
						</a>    		
					</div>
				</div>
	<%	}	
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	
%>
			
				  
				
				<div class="clearfix"> </div>
			</div>
			</div>
	</div>
	<!-- //Top tracks --> 





<!-- js -->
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<!--search-bar-->
		<script src="js/main.js"></script>	
<!--//search-bar-->
<!-- FlexSlider -->
				  <script defer src="js/jquery.flexslider.js"></script>
				  <script type="text/javascript">
					$(function(){				 
					});
					$(window).load(function(){
					  $('.flexslider').flexslider({
						animation: "slide",
						start: function(slider){
						  $('body').removeClass('loading');
						}
					  });
					});
				  </script>
<!-- FlexSlider -->
<!-- clients-slider-script -->
				<script src="js/slideshow.min.js"></script>
				<script src="js/launcher.js"></script>
<!-- //clients-slider-script -->
<!-- /circle-->
	 <script type="text/javascript" src="js/circles.js"></script>
					         <script>
								var colors = [
										['#ffffff', '#fd9426'], ['#ffffff', '#fc3158'],['#ffffff', '#53d769'], ['#ffffff', '#147efb']
									];
								for (var i = 1; i <= 7; i++) {
									var child = document.getElementById('circles-' + i),
										percentage = 30 + (i * 10);
										
									Circles.create({
										id:         child.id,
										percentage: percentage,
										radius:     80,
										width:      10,
										number:   	percentage / 1,
										text:       '%',
										colors:     colors[i - 1]
									});
								}
						
				</script>
	<!-- //circle -->
	<!-- for-Map -->
		<script type="text/javascript">
			$(function() {
			
				var menu_ul = $('.faq > li > ul'),
					   menu_a  = $('.faq > li > a');
				
				menu_ul.hide();
			
				menu_a.click(function(e) {
					e.preventDefault();
					if(!$(this).hasClass('active')) {
						menu_a.removeClass('active');
						menu_ul.filter(':visible').slideUp('normal');
						$(this).addClass('active').next().stop(true,true).slideDown('normal');
					} else {
						$(this).removeClass('active');
						$(this).next().stop(true,true).slideUp('normal');
					}
				});
			
			});
		</script>
<!-- //for-Map -->
<script src="js/jzBox.js"></script>
<!-- start-smoth-scrolling -->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event){		
			event.preventDefault();
			$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
		});
	});
</script>
<!-- start-smoth-scrolling -->
<!-- smooth scrolling -->
	<script type="text/javascript">
		$(document).ready(function() {
		/*
			var defaults = {
			containerID: 'toTop', // fading element id
			containerHoverID: 'toTopHover', // fading element hover id
			scrollSpeed: 1200,
			easingType: 'linear' 
			};
		*/								
		$().UItoTop({ easingType: 'easeOutQuart' });
		});
	</script>
<!-- Animation-effect -->
<script src='js/aos.js'></script>
<script src="js/aosindex.js"></script>
<!-- //Animation-effect -->
	<a href="#home" class="scroll" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
<!-- //smooth scrolling -->
<script src="js/bootstrap.js"></script>
<br>
<br>
<p style="font-size:12px">Language: English</p> 

<p style="font-size:12px">Copyright � 2017 mp3Cloud. All rights reserved. Powered by mp3Cloud.</p>
</body>
</html>>
