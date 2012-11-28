<%-- 
    Document   : cadastrar
    Created on : 29/08/2012, 14:21:12
    Author     : Pedro
--%>

<%@page import="javax.swing.JOptionPane"%>
<%@page import="java.io.Reader"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.IOException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>BeShort</title>
        <link rel="stylesheet" type="text/css" href="estiloTimeline.css" media="screen" />
        <script type="text/javascript" src="jsTimeline.js" charset="utf-8"></script>
    </head>
    <body bgcolor="#FFFFFF">

        <div id="topo">	
        </div>
        <h1> Usuário ativo: @${param.group}</h1>
        <img id="logo" src="twitter.png">
        <textarea style="display:none" id="user">${param.group}</textarea>

        <form name="form1" method="post" action="index.jsp">
            <input id="trocaUser" type="submit" name="op" value="Trocar Usuário">
        </form>

        <textarea rows="5" cols="50" id="tweet"></textarea>
        <div id="retorno"></div>
        <div id="atuTime"></div>
        <input id="buttonEnviar" type="button" value="Enviar Tweet" onclick="enviaTweet();"/>
        <input id="atualizar" type="button" value="Atualizar Timeline" onclick="timelineUser();"/>
        <div id="message"></div> 
        <!--<a href="#" class="dcontexto">CSS<span>bla bla bla bla</span></a>-->
    </body>
</html>

