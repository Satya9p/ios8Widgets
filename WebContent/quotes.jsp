<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Quote</title>

	
    
    
    <style type="text/css" >
	
	
body { 
	background: #FDFDFD; 
}
body, input, textarea { 
	font: 14px/24px Helvetica, Arial, sans-serif; 
	color: #666; 
}
input { 
	width: 60% 
}
form { 
	margin: 30px 0 0 0 
}
input, textarea { 
	background: none repeat scroll 0 0 #FFFFFF; 
	border: 1px solid #C9C9C9; 
	box-shadow: 0 1px 3px rgba(0, 0, 0, 0.15) inset, -5px -5px 0 0 #F5F5F6, 5px 5px 0 0 #F5F5F6, 5px 0 0 0 #F5F5F6, 0 5px 0 0 #F5F5F6, 5px -5px 0 0 #F5F5F6, -5px 5px 0 0 #F5F5F6; 
	color: #545658; 
	padding: 8px; 
	font-size: 14px; 
	border-radius: 2px 2px 2px 2px; 
}
#submit { 

	border: 1px solid #B7D6DF; 
	border-radius: 2px 2px 2px 2px; 
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1); 
	color: #437182; 
	cursor: pointer; 
	font-family: "Helvetica Neue",Helvetica,Arial,sans-serif; 
	font-size: 14px;
	font-weight: bold; 
	height: auto; 
	padding: 6px 10px; 
	text-shadow: 0 1px 0 #FFFFFF; 
	width: auto; 
}
#submit:hover { 
	
	border: 1px solid #9FBAC0; 
	cursor: pointer; 
}
a { 
	color: #88BBC8; 
	text-decoration: none; 
}
a:hover { 
	color: #f26525 
}
#signup-form { 
	width: 510px; 
	margin: 0 auto; 
	margin-top: 50px; 
	margin-bottom: 50px; 
	background: #fff; 
	padding: 40px; 
	border: 10px solid #f2f2f2; 
}
#signup-icon { 
	float: right; 
	width: 48px; 
	height: 48px; 
}
h1, h2, h3, h4, h5, h6 { 
	margin: 0; 
	padding: 0; 
	color: #444; 
}
h1 { 
	float: left; 
	margin: 0 0 30px; 
	font-size: 24px; 
	line-height: 34px; 
}
h2.secondary { 
	float: left; 
	width: 260px; 
	font-size: 16px; 
	font-weight: normal; 
	color: #999; 
	margin-bottom: 30px; 
	line-height: 26px; 
}
h3 { 
	margin: 30px 0 0 0 
}
.clearfix:after { 
	content: "."; 
	display: block; 
	height: 0; 
	clear: both; 
	visibility: hidden; 
}
.clearfix { 
	display: inline-block 
} /* Hide from IE Mac \*/
.clearfix { 
	display: block; 
} /* End hide from IE Mac */
.none { 
	display: none; 
} /* End Clearfix _NO__DOTCOMMA__AFTER__*/

#header { 
	margin: 0 0 30px 0; 
	border-bottom: 1px solid #efefef; 
}
#send p { 
	margin-bottom: 20px 
}
textarea { 
	width: 95%; 
	margin: 0 0 0 2px; 
}
#required p{
	font-size:10px;
}
#apply { 
	border-top: 1px solid #efefef; 
	margin-top: 30px; 
	padding: 20px 0 0 0; 
}
#apply ul { 
	margin-bottom: 50px 
}
form label { 
	display: block; 
	margin-bottom: 5px; 
	font-weight: bold; 
	font-size: 12px; 
}
	
	
	</style>
    

<script language="javascript" type="text/javascript">  

		
      function  registrationValidationform(quotepage)
            {		
    	  			var returnValue=true;
                	if(document.forms["quotepage"]["email"].value=="")
                    {
                        alert("Please Enter Author Name");
                        returnValue = false;
                        
                    }else{
                    	returnValue = true;
                       
                    }
                    if(document.forms["quotepage"]["profile"].value=="")
                    {
                            alert("please Enter Qoute");
                            returnValue = false;
                            
                    }else{
                    	returnValue = true; 
                    }
                    return returnValue;
             }
      
      
      function validation()
      {
    	  	 var res =true;
    	  	 console.log(" before validation result::"+res);
    	  	 res = registrationValidationform(quotepage);
    		 var xmlhttp;
    		 var name =  document.getElementById("email").value;
    		 var quote = document.getElementById("profile").value;
    		 
    		 console.log("validation result::"+res);
    		 console.log("request parameter::"+name);
    		 console.log("request parameter::"+quote);
    		 
    		 if(res)
    		 {  
    			console.log("ajax call"); 
    			if(window.XMLHttpRequest){
      		        xmlhttp=new XMLHttpRequest();
      		    }else{// code for IE6, IE5
      		      xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
      		    }
      		 xmlhttp.onreadystatechange=function()
      		   {
      		   if (xmlhttp.readyState==4 && xmlhttp.status==200)
				   {
      			     var count = xmlhttp.responseText;
      			 	if(count == 0){
						document.getElementById("error").innerHTML = "failure";
						document.forms["quotepage"]["profile"].value="";
						 document.forms["quotepage"]["email"].value="";
						 document.forms["quotepage"]["profile"].focus();
					 }else{
						 document.forms["quotepage"]["profile"].value="";
						 document.forms["quotepage"]["email"].value="";
						 document.forms["quotepage"]["profile"].focus();
						 document.getElementById("success").innerHTML="sucessfully saved to database";
					 }
      		     }
      		   };
      		 xmlhttp.open("GET","http://test.relay.9pstudio.com:8080/ios8Widgets/IQS?author="+name+"&quote="+quote,true);
      		 xmlhttp.send();
    		 }
    		
    	 }
      
      </script>   
</head>

<body>

    <!--BEGIN #signup-form -->
    <div id="signup-form">
        
        <!--BEGIN #subscribe-inner -->
        <div id="signup-inner">
        
        	
            
          <form id="send" action="" name = "quotepage">
         
            <p>

                <label for="email">Author</label>
                <input id="email" type="text" name="email" value="" />
                </p>
            
             
                
                
            <p>
                <label for="profile">Quote</label>
                <textarea name="profile" id="profile" cols="30" rows="10"></textarea>

                </p>
                
                <p>

                <button id="submit" type="button" onclick= "validation()" >Save</button>
                </p>
                
                <p style="text-align:center; color:red; line-height:0px;" id="error" ></p>
                <p style="text-align:center; color:green;line-height:0px;"  id="success"></p>
                
            </form>
        </div>
        
       
        </div>
        



</body>
</html>
