import java.io.*;
import java.util.*;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Image.*;
 
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;






public class FileUp extends HttpServlet {
   
   private boolean isMultipart;
   private String filePath;
   private int maxFileSize = 500 * 1024;
   private int maxMemSize = 400 * 1024;
   private File file ;
   private String fileName1;




	public void init( ){
      
	  // Get the file location where it would be stored.
      filePath = getServletContext().getInitParameter("file-upload"); 

	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, java.io.IOException {

				java.io.PrintWriter out = response.getWriter( );
				out.println("<html>");

				// Check that we have a file upload request

				isMultipart = ServletFileUpload.isMultipartContent(request);
				response.setContentType("text/html");
     

				if( !isMultipart ){

						out.println("<html>");
						out.println("<head>");
						out.println("<title>Servlet upload</title>");  
						out.println("</head>");
						out.println("<body>");
						out.println("<p>No file uploaded</p>"); 
						out.println("</body>");
						out.println("</html>");
						
						return;
      		}

			DiskFileItemFactory factory = new DiskFileItemFactory();
      
			// maximum size that will be stored in memory
			factory.setSizeThreshold(maxMemSize);
			
			// Location to save data that is larger than maxMemSize.
			factory.setRepository(new File("/usr/share/apache-tomcat-7.0.32/webapps/test/data/"));

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
      
			// maximum file size to be uploaded.
			upload.setSizeMax( maxFileSize );

			try{
	  
				// Parse the request to get file items.
				List fileItems = upload.parseRequest(request);
	
				// Process the uploaded file items
				Iterator i = fileItems.iterator();
				//String NameOfIm = request.getParameter("text1");

				out.println("<html>");
				out.println("<head>");
				out.println("<title>Servlet upload</title>"); 
				out.println("</head>");
				out.println("<body>");

				String[] nameof=new String[4];
				String Nameget="";
				String Nameim="";
				String details="";
				String categ="";
				int kar=0;
				BufferedImage image = null;
				
				while ( i.hasNext () ) {
				
						FileItem fi = (FileItem)i.next();
						
						if (fi.isFormField ()){ nameof[kar] = fi.getString(); kar++; }
						
						if ( !fi.isFormField () ) {

							// Get the uploaded file parameters
							String fieldName = fi.getFieldName();
							String fileName = fi.getName();
							String contentType = fi.getContentType();
							boolean isInMemory = fi.isInMemory();
							long sizeInBytes = fi.getSize();
							

							// Write the file
							
							if (nameof.equals("")){
               
									file = new File( filePath + fileName);
									fi.write( file ) ;
									out.println("Uploaded Filename: " + fileName + "<br>");
									out.println("<img src='data/" + fileName + "' height='200' width='200' />");
									Nameget = fileName.substring(fileName.length()-4,fileName.length());
									Nameim = fileName;
							}
							else{

									file = new File( filePath + nameof[0]+ fileName.substring(fileName.lastIndexOf(".")));
									fi.write( file ) ;
									out.println("Uploaded Filename: " + nameof[0]+ fileName.substring(fileName.lastIndexOf(".")) + "<br>");
									out.println("<img src='data/" +nameof[0]+ fileName.substring(fileName.lastIndexOf(".")) + "' height='200' width='200' />");
									Nameget = nameof[0];
									Nameim = nameof[0]+ fileName.substring(fileName.lastIndexOf("."));
									
							}

						}
					}
					
					out.println("<br/><a href='http://83.212.98.232:8080/test/index'>Back to first page</a>");
	  
					details=nameof[2];
					categ = nameof[1];
//==============================================================================================


					File f,fim;
					fim = new File(filePath+Nameim);
					image =  ImageIO.read(fim);
					f=new File(filePath+Nameget+".xml");
  
					if(!f.exists()){
							
							f.createNewFile();
							
							try{
   
									FileWriter fstream = new FileWriter(filePath+Nameget+".xml");
									BufferedWriter out1 = new BufferedWriter(fstream);
  
									out1.write("<?xml version='1.0' encoding='ISO-8859-1'?>\n"
												+"\n"
												+"<XML>\n"
												+"\n"
												+"<IMAGE>\n"
												+"\n"
												+"	<NAME>"+Nameget+"</NAME>\n"
												+"	<WIDTH>"+image.getWidth()+"</WIDTH>\n"
												+"	<HEIGHT>"+image.getHeight()+"</HEIGHT>\n"
												+"	<ROT>0</ROT>\n"
												+"	<IMG>"+Nameim+"</IMG>\n"
												+"	<DETAILS>"+details+"</DETAILS>\n"
												+"	<CATEGORY>"+categ+"</CATEGORY>\n"
												+"\n"
												+"</IMAGE>\n"
												+"\n"
												+"</XML>");
									out1.close();
	

							}catch (Exception e){//Catch exception if any
	
							System.err.println("Error: " + e.getMessage());
		
							}
					}		



//==============================================================================================
response.sendRedirect("/test/index");

			}catch(Exception ex) {
			
				System.out.println(ex);
				
			}
	}
   

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, java.io.IOException {
        
        
		throw new ServletException("GET method used with " + getClass( ).getName( ) + ": POST method required.");
			
	}

}