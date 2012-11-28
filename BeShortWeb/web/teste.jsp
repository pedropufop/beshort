<%-- 
    Document   : teste
    Created on : 31/08/2012, 19:14:12
    Author     : Pedro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Binod Java Solution AJAX </title>
        <script type="text/javascript">
            function IniciaAjax(){
                var HTTP_REQUEST;
                try {
                    HTTP_REQUEST = new ActiveXObject("Microsoft.XMLHTTP");
                }catch(e) {
                    try {
                        HTTP_REQUEST = new ActiveXObject("Msxml2.XMLHTTP");
                    } catch(ex) {
                        try {
                            HTTP_REQUEST = new XMLHttpRequest();
                            HTTP_REQUEST.overrideMimeType('text/html');
                        } catch(exc) {
                            alert("Esse browser não tem recursos para uso do Ajax");
                            HTTP_REQUEST = null;
                        }
                    }
                }
                return HTTP_REQUEST;
            }
        
                        
            var request; 
            function getName(){
                var roll = document.getElementById("roll").value;
                
                if(window.ActiveXObject){ 
                    request = new ActiveXObject("Microsoft.XMLHTTP");
                    if (request==null)
                        request = new ActiveXObject("Msxml2.XMLHTTP");
                }
                    
                else if(window.XMLHttpRequest){ 
                    request = new XMLHttpRequest();
                }
                var url = "http://localhost:8080/WebApplication1/ServletIndex?roll="+roll;
                request.onreadystatechange = showResult;
                request.open("POST",url,true);
                request.send();
            }
            
            function showResult(){
                if(request.readyState == 4){
                    var response = request.responseXML;
                    var students = response.getElementsByTagName("Student");
                    var student = students[0];
                    document.getElementById("NamelH1").innerHTML = student.getElementsByTagName("Name")[0].text;
                    document.getElementById("HostelH1").innerHTML = student.getElementsByTagName("Hostel")[0].text;
                    document.getElementById("ContactH1").innerHTML = student.getElementsByTagName("Contact")[0].text;
                }
            }
        </script>
    </head>
    <body><h2> GET STUDENT INFO </h2>
        <br> Enter Roll Number <input type="text" id="roll">
        <input type="button" value="Get Name" onclick="getName();"/>
        <br> Name : <span id="NamelH1"></span> <br>
        Hostel : <span id="HostelH1"></span> <br>
        Contact : <span id="ContactH1"></span> <br>
    </body>
</html>