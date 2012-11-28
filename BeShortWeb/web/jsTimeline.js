/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function DesapareceDiv(){
    var div = document.getElementById("retorno");
    div.innerHTML = "";
    var atuTime = document.getElementById("atuTime");
    atuTime.innerHTML = "";
//div.value() = "none"; // a div desaparecerá
}

function DesapareceAtu(){
    var atuTime = document.getElementById("atuTime");
    atuTime.innerHTML = "";
}

function DesapareceRetorno(){
    var  semCont = document.getElementById("retorno");
    semCont.innerHTML = "";
}

function getXMLObject()  //XML OBJECT
{
    var xmlHttp = false;
    try {
        xmlHttp = new ActiveXObject("Msxml2.XMLHTTP")  // For Old Microsoft Browsers
    }
    catch (e) {
        try {
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP")  // For Microsoft IE 6.0+
        }
        catch (e2) {
            xmlHttp = false   // No Browser accepts the XMLHTTP Object then false
        }
    }
    if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
        xmlHttp = new XMLHttpRequest();        //For Mozilla, Opera Browsers
    }
    return xmlHttp;  // Mandatory Statement returning the ajax object created
}
            
var xmlhttp = new getXMLObject(); //xmlhttp holds the ajax object
 
function timelineUser() {
    if(xmlhttp) { 
        var user = document.getElementById("user");
        atuTime = document.getElementById("atuTime");
        atuTime.innerHTML = "Atualizando...";
        xmlhttp.open("POST","ServletGetTimeline",true); //getname will be the servlet name
        xmlhttp.onreadystatechange  = showTU;
        xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xmlhttp.send("user=" + user.value); //Posting txtname to Servlet
        setTimeout('DesapareceAtu();', 3000);
    }
}
 
function showTU() {
    if (xmlhttp.readyState == 4) {
        if(xmlhttp.status == 200) {
            document.getElementById("message").innerHTML = xmlhttp.responseText;
        }
        else {
            alert("Error during AJAX call. Please try again");
        }
    }
}
            
var xmlhttp2 = new getXMLObject(); //xmlhttp holds the ajax object
 
function enviaTweet() {
    if(xmlhttp2) {
        var tweet = document.getElementById("tweet").value;
        var user = document.getElementById("user").value;
        text = document.getElementById("tweet");
        
        if (tweet == "") {
            semCont = document.getElementById("retorno");
            semCont.style.color = "#FF0000";
            semCont.innerHTML = "Tweet sem conteúdo!";
            setTimeout('DesapareceRetorno();', 2000);
            //out.print("<FONT COLOR=\"#FF0000\"><b>Tweet sem conteúdo!</b></FONT>");
            return;
        }
        
        xmlhttp2.open("POST","ServletEnviaTweet",true); //getname will be the servlet name
        xmlhttp2.onreadystatechange  = showEnvia;
        xmlhttp2.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xmlhttp2.send("tweet="+tweet+"&user="+user); //Posting txtname to Servlet
        text.value = "";
    }
}
 
function showEnvia() {
    if (xmlhttp2.readyState == 4) {
        if(xmlhttp2.status == 200) {
            document.getElementById("retorno").innerHTML = xmlhttp2.responseText;
            //setTimeout('DesapareceDiv();', 5000);
            atuTime = document.getElementById("atuTime");
            atuTime.innerHTML = "Enviando..";
            setTimeout('timelineUser();', 4000);
            //setTimeout('DesapareceDiv();', 3000);
        }
        else {
            alert("Error during AJAX call. Please try again");
        }
    }
}

            //            var request; 
            //            function getName(){
            //                var tweet = document.getElementById("tweet").value;
            //                var user = document.getElementById("user").value;
            //                text = document.getElementById("tweet");
            //                if(window.ActiveXObject){ 
            //                    request = new ActiveXObject("Microsoft.XMLHTTP");
            //                }
            //                else if(window.XMLHttpRequest){ 
            //                    request = new XMLHttpRequest();
            //                }
            //                request.onreadystatechange = showResult;
            //                var url = "http://localhost:8080/WebApplication1/ServletEnviaTweet?tweet="+tweet+"&user="+user;
            //                request.open("POST",url,true);
            //                request.send();
            //                text.value = "";
            //            }
            //            
            //            function showResult(){
            //                if (request.readyState == 1){
            //                    
            //                    
            //                }
            //                if(request.readyState == 4){
            //                    //var response = request.responseXML;
            //                    //var students = response.getElementsByTagName("tw");
            //                    //var student = students[0];
            //                    //document.getElementById("tweet").innerHTML = "teste";
            //                    //text = document.getElementById("tweet");
            //                    //text.value = "";
            //                    //document.getElementById("status").innerHTML = "teste";///student.getElementsByTagName("St")[0].text;      
            //                }
            //                //else{
            //                //    alert("Error during AJAX call. Please try again");
            //                //}
            //            }

            
      

