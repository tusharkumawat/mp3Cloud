

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class Upload
 */
@WebServlet("/Upload")
@MultipartConfig(maxFileSize = 16177215)
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	
	   private String filePath;
	   private int maxFileSize = 16177215;
	   private int maxMemSize = 4 * 1024;
	   private File file ;

	   public void init( ){
	      // Get the file location where it would be stored.
	      filePath = getServletContext().getInitParameter("file-upload"); 
	   }
	
	
	
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
		HttpSession session=request.getSession();
        String uid=(String)session.getAttribute("userid");
        if(uid==null)
        {
        	PrintWriter pw=response.getWriter();
    		pw.println("<script type=\"text/javascript\">");
    		pw.println("window.location.href='index.jsp'");
    		pw.println("</script>");
        }
		 
		 String arr[]=new String[100];
		 DiskFileItemFactory factory = new DiskFileItemFactory();
		   
	      // maximum size that will be stored in memory
	      factory.setSizeThreshold(maxMemSize);
	   
	      // Location to save data that is larger than maxMemSize.
	      factory.setRepository(new File("D:\\rat\\J2EE\\apache-tomcat-8.0.28-windows-x86\\apache-tomcat-8.0.28\\webapps\\mp3cloud_data\\temporary"));

	      // Create a new file upload handler
	      ServletFileUpload upload = new ServletFileUpload(factory);
	   
	      // maximum file size to be uploaded.
	      upload.setSizeMax( maxFileSize );
	      String fileName[]=new String[2];
	      try { 
	         // Parse the request to get file items.
	         List fileItems = upload.parseRequest(request);
	         
	         int count=-1,count1=-1;
	         
	         // Process the uploaded file items
	         Iterator it = fileItems.iterator();
	         
	         
	         while ( it.hasNext()) 
	         {
	        	
	            FileItem fi = (FileItem)it.next();
	            if(fi.isFormField())
	            {
	            	count1+=1;
	            	arr[count1]=fi.getString();

	            }
	            
	            if ( !fi.isFormField () ) {
	            	
	            		            	
	               // Get the uploaded file parameters
	            	count+=1;
	            	fileName[count] = fi.getName();
	               
	               
	            
	               // Write the file
	               if( fileName[count].lastIndexOf("\\") >= 0 ) {
	                  file = new File( filePath + uid+ fileName[count].substring( fileName[count].lastIndexOf("\\"))) ;
	               } else {
	                  file = new File( filePath +uid+ fileName[count].substring(fileName[count].lastIndexOf("\\")+1)) ;
	               }
	               fi.write( file ) ;
	               System.out.println("Uploaded Filename: " + uid+fileName[count]);
	            }
	         }
	         
	        
	         } catch(Exception ex) {
	            System.out.println(ex);
	         }

	      
	      
	      
			
			
	      	Connection con = null; // connection to the database
	      	
        	con=DBInfo.getConn();
	        try 
	        {
	        	
	        	
	        	 String query="Insert into tracks(track_id,track_name,track_artist,date,uid,genre,track_dp,track_file)values(?,?,?,curdate(),?,?,?,?);"; 
	        	 
	        	 
	        	 PreparedStatement ps = con.prepareStatement(query);
	             ps.setString(1, trackid());
	             ps.setString(2, arr[0]);
	             if(arr[1].isEmpty())
	             {
	            	 ps.setString(3, "Unknown");
	             }
	             else
	             {
	            	 ps.setString(3, arr[1]);
	             }
	             ps.setString(4, uid);
	             ps.setString(5, arr[2]);
	             if(fileName[0].isEmpty())
	             {
	            	 System.out.println("Capture");
	            	 int n=(int)Math.ceil(Math.random()*6);
	            	 ps.setString(6, "Capture"+n+".JPG");
	             }
	             else
	             {
	            	 ps.setString(6, uid+fileName[0]); 
	             }
	             ps.setString(7, uid+fileName[1]);    
	            
	             // sends the statement to the database server
	             int row = ps.executeUpdate();
	             if (row > 0) {
	                 System.out.println("track photo song inserted");
	                 
	                 Connection conn=DBInfo.getConn();
	                 String queryUpload="Update users set uploads=uploads+1 where uid=?;";
	                 try
	                 {
	                	 PreparedStatement p=conn.prepareStatement(queryUpload);
	                	 p.setString(1, uid);
	                	 p.executeUpdate();
	                 }
	                 catch(Exception a)
	                 {
	                	 a.printStackTrace();
	                 }
	                 
	                 response.sendRedirect("MyProfile");
	             }
	         } catch (SQLException ex) {
	             ex.printStackTrace();
	         } finally {
	             if (con != null) {
	                 // closes the database connection
	                 try {
	                     con.close();
	                 } catch (SQLException ex) {
	                     ex.printStackTrace();
	                 }
	             }}
		
		
	}
	

	public String trackid()
	{
		String id="";
		for(int i=0;i<=6;i++)
		{
			id+=(int)((Math.random()*9)+1);
		}
		return id;
	}
}
