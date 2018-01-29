

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class EditPOp
 */
@WebServlet("/EditPOp")
@MultipartConfig(maxFileSize = 16177215)
public class EditPOp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String filePath;
	   private int maxFileSize = 16177215;
	   private int maxMemSize = 4 * 1024;
	   private File file ;

	   public void init( ){
	      // Get the file location where it would be stored.
	      filePath = getServletContext().getInitParameter("file-upload-dp"); 
	   }
	
	
	
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
		
				
			 
			 DiskFileItemFactory factory = new DiskFileItemFactory();
			   
		      // maximum size that will be stored in memory
		      factory.setSizeThreshold(maxMemSize);
		   
		      // Location to save data that is larger than maxMemSize.
		      factory.setRepository(new File("D:\\rat\\J2EE\\apache-tomcat-8.0.28-windows-x86\\apache-tomcat-8.0.28\\webapps\\mp3cloud_data\\temporary"));

		      // Create a new file upload handler
		      ServletFileUpload upload = new ServletFileUpload(factory);
		   
		      // maximum file size to be uploaded.
		      upload.setSizeMax( maxFileSize );
		      String fileName=new String();
		      
		      try { 
		    	 // Parse the request to get file items.
		         List fileItems = upload.parseRequest(request);
		         
		        
		         
		         // Process the uploaded file items
		         Iterator it = fileItems.iterator();
		         
		         while ( it.hasNext()) 
		         {
		            FileItem fi = (FileItem)it.next();

		            if ( !fi.isFormField () ) {
		               // Get the uploaded file parameters
		            	
		            	fileName = fi.getName();
		               
		            
		               // Write the file
		               if( fileName.lastIndexOf("\\") >= 0 ) {
		            	   System.out.println(fileName);
		                  file = new File( filePath + uid+ fileName.substring( fileName.lastIndexOf("\\"))) ;
		               } else {
		                  file = new File( filePath +uid+ fileName.substring(fileName.lastIndexOf("\\")+1)) ;
		               }
		               fi.write( file ) ;
		               System.out.println("Uploaded Filename: " + uid+fileName + "<br>");
		            }
		         }
		         
		        
		         } catch(Exception ex) {
		           ex.printStackTrace();
		         }

		      
		      
		      
				
				
		      	Connection con = null; // connection to the database
		      	
	        	con=DBInfo.getConn();
		        try 
		        {
		        	
		        	
		        	 String query="Update users set user_dp=? where uid='"+uid+"';"; 
		        	 
		        	 
		        	 PreparedStatement ps = con.prepareStatement(query);
		             ps.setString(1, uid+fileName);
		             
		             // sends the statement to the database server
		             int row = ps.executeUpdate();
		             if (row > 0) {
		                 System.out.println("dp inserted");
		                 PrintWriter pw=response.getWriter();
		                 pw.println("<script type=\"text/javascript\">");
							pw.println("alert('Display picture updated!!')");						
							pw.println("window.location.href='/mp3Cloud/EditProfile'");
							pw.println("</script>"); 
		                 
		                 
		                 
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

}
