<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ page import = "com.exercise.servlet1.core.*"%>
<%@ page import = "java.io.*"%>
<%@ page import = "java.util.*"%>

<html>
<head>
	<h1><b>MANAGE ROLES</b></h1>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>List of Roles</title>
</head>
<body>
	<a href = ${pageContext.request.contextPath}/MainPage>MAIN PAGE<br/></a>
	<br><br>
	<div class="row">
		<div class="column column-6"><span style="color:red">${addError}</span><br>	
		Add Role:
		<form action="${pageContext.request.contextPath}/ManageRoles?action=addRole" method="post">	
			<input type="text" name="newRole"/>
			<input type="submit" value="Add Role" />
		</form>
	</div>
	
	<div class="row">
		<div class="column column-6"><span style="color:red">${updateError}</span><br>		
		Update Role: 	
		<form action="${pageContext.request.contextPath}/ManageRoles?action=updateRole" method="post">
			<select name="updateIdRole">
				<c:forEach var = "role" items = "${roles}">
					<option value="${role.id}">${role.roleName}</option>
				</c:forEach><br>
				<input type="text" name="updateRoleName">
			</select>
			<input type="submit" value="Update Role" />
		</form>
	</div>
	<br>
	<div class="row">
		<div class="column column-6"><span style="color:red">${deleteError}</span><br>		
		Delete Role:
			<form action="${pageContext.request.contextPath}/ManageRoles?action=deleteRole" method="post">	
				<select name="deleteIdRole">
				<c:forEach var = "role" items = "${roles}">
					<option value="${role.id}">${role.roleName}</option>
				</c:forEach><br>
			</select>
			<input type="submit" value="Delete Role" />
		</form>	 
	</div>
	
		<div>
	         LIST OF ROLES
		         <br><br>
		         <table>			
					<tr>
						<th>Role ID</th>
						<th>Role Name</th>
					</tr>
					<c:forEach var = "role" items = "${roles}">
					<tr>
						<td><c:out value = "${role.id}"/></td>
						<td><c:out value = "${role.roleName}"/></td>
					</tr>
					</c:forEach>																				
				</table>
	   </div>
	
	<br>		
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



		
		