<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ page import = "com.exercise.servlet1.core.*"%>
<%@ page import = "java.io.*"%>
<%@ page import = "java.util.*"%>

<html>
<head>
	<h1><b>MANAGE CONTACTS</b></h1>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>List of Roles</title>
</head>
<body>
	<a href = ${pageContext.request.contextPath}/MainPage>MAIN PAGE<br/></a>
	<br><br><br>

		<div class="row">
			<div class="column column-8">	
				<form action="${pageContext.request.contextPath}/ContactPerson" method="get">	
					<select name="personId">
					<c:forEach var = "person" items = "${persons}">
						<option value="${person.id}">${person.id }</option>
					</c:forEach><br>
					</select>
					<input type="submit" value="View Contacts" />
				</form>	 
			</div>
		</div>
		<div class="row">
			<div class="column column-6"><span style="color:red">${error}</span>		
			Role to Update 	
			<form action="${pageContext.request.contextPath}/ContactPerson?action=update" method="post">
				<select name="contactId">
					<c:forEach var = "contact" items = "${contacts}">
						<option value="${contact.id}">${contact.contactValue}</option>
					</c:forEach><br>
					<input type="text" name="newContactValue">
				</select>
				<input type="submit" value="Update Contact" />
			</form>
		</div>
		
		
		
		
		<div>
	         LIST ROLES
		         <br><br><br>
		         <table>			
					<tr>
						<th>Contact Type</th>
						<th>Contact Value</th>
					</tr>
					<c:forEach var = "contact" items = "${contacts }">
					<tr>
						<td><c:out value = "${contact.contactType}"/></td>
						<td><c:out value = "${contact.contactValue}"/></td>
					</tr>
					</c:forEach>																				
				</table>
	   </div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
	<style>
	  table {
	    	border-collapse: collapse;
	    	table-layout:fixed;
	    	width: 50%;
		}
		
		table, th, td {
		    border: 1px solid black;
		    word-wrap:break-word;
		    text-align:center;
		}
		th{
			background-color: #748269;
	    	color: white;
	    }
		buttons{
			align-items: center;
		}
		tr:hover{background-color:#f5f5f5}
		tr:nth-child(even) {
		  background-color: #f2f2f2
		}
		tr:nth-child(odd) {
		  background-color: white;
		}
		a:link, a:visited {
		    background-color:  #748269;
		    color: white;
		    padding: 14px 25px;
		    text-align: center;
		    text-decoration: none;
		    display: inline-block;
		}
		
		a:hover, a:active {
		    background-color: #306813;
		}
		select {
		    padding:3px;
		    margin: 0;
		    -webkit-border-radius:4px;
		    -moz-border-radius:4px;
		    border-radius:4px;
		    -webkit-box-shadow: 0 3px 0 #ccc, 0 -1px #fff inset;
		    -moz-box-shadow: 0 3px 0 #ccc, 0 -1px #fff inset;
		    box-shadow: 0 3px 0 #ccc, 0 -1px #fff inset;
		    background: #748269;
		    color:white;
		    border:none;
		    outline:none;
		    display: inline-block;
		    -webkit-appearance:none;
		    -moz-appearance:none;
		    appearance:none;
		    cursor:pointer;
		}

	</style>
</html>



		
		