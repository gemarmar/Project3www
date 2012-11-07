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

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;






public class main extends HttpServlet {
   
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
         	out.println("<title>Servlet change</title>");  
         	out.println("</head>");
         	out.println("<body>");
         	out.println("<p>No file changed</p>"); 
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


			String[] nameof=new String[20];
			String nameka="";
			String height="";
			String width="";
			String details="";
			String rot="";
			String categ="";
			int kar=0;
			BufferedImage image = null;

			while ( i.hasNext () ) {

					FileItem fi = (FileItem)i.next();

					nameof[kar] = fi.getString();

					kar++;

			}
      
			nameka=nameof[0].substring(0,nameof[0].length()-4);
			height=nameof[1];
			width=nameof[2];
			rot=nameof[3];
			categ=nameof[4];
			details=nameof[5];



			File fXmlFile = new File("/usr/share/apache-tomcat-7.0.32/webapps/test/data/"+nameka+".xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList k = doc.getElementsByTagName("IMAGE");
			Node m = k.item(0);
			Element element = (Element) m;
			NodeList nodelist;
			Element element1;
			NodeList fstNm;

			if(!height.equals("")){

				nodelist = element.getElementsByTagName("HEIGHT");
				element1 = (Element) nodelist.item(0);
				fstNm = element1.getChildNodes();
				(fstNm.item(0)).setNodeValue(height);
			}

			if(!width.equals("")){
			
				out.write("karara");
				nodelist = element.getElementsByTagName("WIDTH");
				element1 = (Element) nodelist.item(0);
				fstNm = element1.getChildNodes();
				(fstNm.item(0)).setNodeValue(width);
			}

			if(!rot.equals("")){

				nodelist = element.getElementsByTagName("ROT");
				element1 = (Element) nodelist.item(0);
				fstNm = element1.getChildNodes();
				(fstNm.item(0)).setNodeValue(rot);
			}

			if(!details.equals("")){

				nodelist = element.getElementsByTagName("DETAILS");
				element1 = (Element) nodelist.item(0);
				fstNm = element1.getChildNodes();
				(fstNm.item(0)).setNodeValue(details);
			}

			if(!categ.equals("")){

				nodelist = element.getElementsByTagName("CATEGORY");
				element1 = (Element) nodelist.item(0);
				fstNm = element1.getChildNodes();
				(fstNm.item(0)).setNodeValue(categ);
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result =  new StreamResult("/usr/share/apache-tomcat-7.0.32/webapps/test/data/"+nameka+".xml");
			transformer.transform(source, result);

			response.sendRedirect("image.jsp?name="+nameof[0]);



		}catch(Exception ex) {
       
			System.out.println(ex);
		}
   
	}
   
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, java.io.IOException {
        
        throw new ServletException("GET method used with " + getClass( ).getName( )+": POST method required.");
   } 
}