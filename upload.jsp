<%            
			String[] features = (String [])request.getAttribute("attribute");
            String[] cat = (String [])request.getAttribute("categ");
%>

<?xml version="1.0" encoding="utf-8" ?>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Upload Your Photos</title>
<link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<center>



	<h1>Upload Your Photos</h1>
	<div id="content"> 

	<form action="/test/test" method="post"
                        enctype="multipart/form-data">
		<p class="login">
		<br>Choose a name:
		<input class="text" type="text" name="nameof" size="50" /></br>
		<p class="select">Select a file to upload:
		<input class="text" type="file"  name="file" size="50" />
		<br/>
		<br>
		<input type="radio" name="categ" value="general" checked="checked">General
		<input type="radio" name="categ" value="travel">Travel
		<input type="radio" name="categ" value="car">Cars
		<input type="radio" name="categ" value="cartoon">Cartoon
		<input type="radio" name="categ" value="sport">Sports
		<input type="radio" name="categ" value="tech">Technology</br>
		<p class="select">Discription:
		<textarea class="text" style = "resize:none;" type="input" name="details" cols=39 rows=2/></textarea>
		</p>
		<input class="text" type="submit" value="Upload File"/>
		</p></p>
	
	</form>

	<br/><center>
	<h3 id="tags">General</h3>
	<ul class="gallery">
<%

             for(int i = 0; i < features.length; i++) {
				if(cat[i].equals("general"))
					out.println("<li><a href='http://83.212.98.232:8080/test/image.jsp?name="+features[i]+"'><img src='data/" + features[i] + "'width='150' height='150' ></a><div>"+features[i].substring(0,(features[i].length())-4)+"</div></li>");
               
             }
%>
</ul></center>

<br/><center>
<h3 id="tags">Travel</h3>
 <ul class="gallery">
<%

             for(int i = 0; i < features.length; i++) {
			 if(cat[i].equals("travel"))
               out.println("<li><a href='http://83.212.98.232:8080/test/image.jsp?name="+features[i]+"'><img src='data/" + features[i] + "'width='150' height='150' ></a><div>"+features[i].substring(0,(features[i].length())-4)+"</div></li>");
               
             }
%>
</ul></center>

<br/><center>
<h3 id="tags">Cars</h3>
 <ul class="gallery">
<%

             for(int i = 0; i < features.length; i++) {
			 if(cat[i].equals("car"))
               out.println("<li><a href='http://83.212.98.232:8080/test/image.jsp?name="+features[i]+"'><img src='data/" + features[i] + "'width='150' height='150' ></a><div>"+features[i].substring(0,(features[i].length())-4)+"</div></li>");
               
             }
%>
</ul></center>

<br/><center>
<h3 id="tags">Cartoon</h3>
 <ul class="gallery">
<%

             for(int i = 0; i < features.length; i++) {
			 if(cat[i].equals("cartoon"))
               out.println("<li><a href='http://83.212.98.232:8080/test/image.jsp?name="+features[i]+"'><img src='data/" + features[i] + "'width='150' height='150' ></a><div>"+features[i].substring(0,(features[i].length())-4)+"</div></li>");
               
             }
%>
</ul></center>

<br/><center>
<h3 id="tags">Sports</h3>
 <ul class="gallery">
<%

             for(int i = 0; i < features.length; i++) {
			 if(cat[i].equals("sport"))
               out.println("<li><a href='http://83.212.98.232:8080/test/image.jsp?name="+features[i]+"'><img src='data/" + features[i] + "'width='150' height='150' ></a><div>"+features[i].substring(0,(features[i].length())-4)+"</div></li>");
               
             }
%>
</ul></center>

<br/><center>
<h3 id="tags">Technology</h3>
 <ul class="gallery">
<%

             for(int i = 0; i < features.length; i++) {
			 if(cat[i].equals("tech"))
               out.println("<li><a href='http://83.212.98.232:8080/test/image.jsp?name="+features[i]+"'><img src='data/" + features[i] + "'width='150' height='150' ></a><div>"+features[i].substring(0,(features[i].length())-4)+"</div></li>");
               
             }
%>
</ul></center>


</div>
</center>
</body>
</html>