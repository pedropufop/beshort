<%-- 
    Document   : index
    Created on : 28/08/2012, 16:04:38
    Author     : Pedro
--%>



<%@page import="javax.swing.JOptionPane"%>
<!DOCTYPE html>
<%@page language="java" %>
<%@page import="principal.*" %>
<%@page import="java.util.*" %>

<html>
    <head>
         <title>BeShort</title>
        <link rel="stylesheet" type="text/css" href="estiloIndex.css" media="screen" />
    </head>
    <body bgcolor="#FFFFFF">
        <h1> Bem vindo ao </h1>
        <img id="bs" src="logo.png">

        <h2> Escolha Usuário </h2>
        <form id="radio" name="myform" action="timeline.jsp" method="Get">
            <input class="user" type="radio" name="group" value="BeShort2012_1" checked> BeShort2012_1
            <input class="user" type="radio" name="group" value="BeShort2012_2" >BeShort2012_2<br><br>
            <input id="buttonOK" type="submit" value="OK">
        </form>

    </body>
</html>