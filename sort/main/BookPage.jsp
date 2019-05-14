<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
	<jsp:useBean id="book" scope="page" class="beans.BookBean"/>
--%>

<%
	String title = (String)request.getAttribute("title");
	String author = (String)request.getAttribute("author");
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Hi! I'm BookPage!</title>
</head>
<body>
	<ol>
		<li><h5>BookTitle is : <%= title %></h5></li>
		<li><h5>BookTitle is : <%= title %></h5></li>
		<li><h5>BookTitle is : <%= title %></h5></li>
	</ol>
	<h1>BookAuthor is:</h1>
	<ul>
		<li><h1><%= author %></h1></li>
		<li><h2><%= author %></h2></li>
		<li><h3><%= author %></h3></li>
	</ul>
</body>
