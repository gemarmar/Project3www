<%
   String name = request.getParameter( "name" );
%>

<?xml version="1.0" encoding="utf-8" ?>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Config Your Photos</title>
<link href="style3.css" rel="stylesheet" type="text/css" />
</head>

<body>
 <div id="content">    
	<br/><a href='http://83.212.98.232:8080/test/index'><h3 class="h31">Back to first page</h3></a>
	<form action="/test/main" method="post" enctype="multipart/form-data">

	<br><h3>Name of Image:</h3></br>
	<input class="text" type="text" id="name"  name="name" readonly="readonly" value="<%=name%>" size="50"/>

	<br><h3>Change Height:</h3></br>
	<input class="text" type="text" id="heig"  name="heig"/>
	<input class="text" type="button" onclick="SizeHeight()" value="Change"/>

	<br><h3>Change Width:</h3></br>
	<input class="text" type="text"id="wid"  name="wid"/>
	<input class="text" type="button" onclick="SizeWidth()" value="Change"/>

	<br><h3>Rotate Image:</h3></br>
	<input class="text" type="text" id="rot"  name="rot"/>
	<input class="text" type="button" onclick="rotate()" value="Rotate  "/>

	<br/><br/><input class="text" type="button" onclick="reset1()" id="reset"   value="ReaL Size" style="height: 25px; width: 170px"/>
	<br/><input class="text" type="button" onclick="reset2()" id="reset"   value="Small" style="height: 25px; width: 170px"/>
	<br/><input class="text" type="button" onclick="reset3()" id="reset"   value="Medium" style="height: 25px; width: 170px"/>
	<br/><input class="text" type="button" onclick="reset4()" id="reset"   value="Large" style="height: 25px; width: 170px"/>



	<p><br/><input class="text3" id="1" type="radio" name="categ" value="general">General
	<input class="text3" id="2" type="radio" name="categ" value="travel">Travel
	<input class="text3" id="3" type="radio" name="categ" value="car">Cars
	<input class="text3" id="4" type="radio" name="categ" value="cartoon">Cartoon
	<input class="text3" id="5" type="radio" name="categ" value="sport">Sports
	<input class="text3" id="6" type="radio" name="categ" value="tech">Technology</p>
	
<h3>Discription:</h3>
<input class="text2" type="button" onclick="editable()" value="Edit"/>
<br/><textarea class="text2" type="input" style = "resize:none;" id="area"  name="details" cols=25 rows=10 readonly/></textarea>


<br/><br/><input class="text" type="submit" id="save"   value="Save Your Work!!!"/>
<ul>
<li id="li2"><img  id="imag"  src="data/<%=name%>"  width="300" height="300"/></li>
</ul>
</form>




<script type="text/javascript">



            d=document.URL;
            d=d.substring(d.lastIndexOf("=")+1);
            a =d.length;
			d=d.substring(0,a-4);

			xmlhttp=new XMLHttpRequest();
			xmlhttp.open("GET","data/" + d+".xml",false);
			xmlhttp.send();
			xmlDoc=xmlhttp.responseXML; 
			k=xmlDoc.getElementsByTagName("IMAGE");
			name=(k[0].getElementsByTagName("NAME")[0].childNodes[0].nodeValue);
			height =(k[0].getElementsByTagName("HEIGHT")[0].childNodes[0].nodeValue);
			width =(k[0].getElementsByTagName("WIDTH")[0].childNodes[0].nodeValue);
			rot =(k[0].getElementsByTagName("ROT")[0].childNodes[0].nodeValue);
			det =(k[0].getElementsByTagName("DETAILS")[0].childNodes[0].nodeValue);
			categ =(k[0].getElementsByTagName("CATEGORY")[0].childNodes[0].nodeValue);
		
			document.getElementById("area").value=det; 
			document.getElementById("imag").style.MozTransform = "rotate("+rot+"deg)";  
			document.getElementById("li2").style.height = 300;
			document.getElementById("li2").style.width = 300;
			
              temp=height;
		temp2=width;
		temp3=rot;
temp4=0;

if(categ == "general"){ document.getElementById("1").checked=true;}
if(categ == "travel"){ document.getElementById("2").checked=true;}
if(categ == "car"){ document.getElementById("3").checked=true;}
if(categ == "cartoon"){ document.getElementById("4").checked=true;}
if(categ == "sport"){ document.getElementById("5").checked=true;}
if(categ == "tech"){ document.getElementById("6").checked=true;}


function SizeHeight(){
    var x=document.getElementById('imag');
    var h=document.getElementById("heig").value
    x.height= h;
   			document.getElementById("li2").style.height = h;
			
    
}
function SizeWidth(){

    var x=document.getElementById('imag');
    var h=document.getElementById("wid").value
    x.width= h;
    			
			document.getElementById("li2").style.width = h;

}

function rotate(){

 var x=document.getElementById('imag');
 var y=document.getElementById('rot').value

 
document.getElementById("li2").style.MozTransform = "rotate("+y+"deg)";
			
}
function editable(){


document.getElementById("area").readOnly=false;


}

function reset1(){


document.getElementById("imag").height = height;
document.getElementById("imag").width = width;
document.getElementById("heig").value = height;
document.getElementById("wid").value = width;
document.getElementById("li2").style.height = height;
document.getElementById("li2").style.width = width;

}

function reset2(){


document.getElementById("imag").height = 300;
document.getElementById("imag").width = 300;
document.getElementById("heig").value = "300";
document.getElementById("wid").value = "300";
			document.getElementById("li2").style.height = 300;
			document.getElementById("li2").style.width = 300;

}

function reset3(){


document.getElementById("imag").height = 600;
document.getElementById("imag").width = 600;
document.getElementById("heig").value = "600";
document.getElementById("wid").value = "600";
			document.getElementById("li2").style.height = 600;
			document.getElementById("li2").style.width = 600;
}

function reset4(){


document.getElementById("imag").height = 900;
document.getElementById("imag").width = 900;
document.getElementById("heig").value = "900";
document.getElementById("wid").value = "900";
			document.getElementById("li2").style.height = 900;
			document.getElementById("li2").style.width = 900;
}

    </script>

</div>
</body>
</html>