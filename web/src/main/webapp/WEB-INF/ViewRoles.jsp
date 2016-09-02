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
	<br>
	<div>
		<div class="row"><br>
			
			<div class="column column-6"><span style="color:red">${errors}</span></div>

			<form action="${pageContext.request.contextPath}/ManageRoles?action=addRole" method="post">	
				Role to Add:<br>
				<input name="newRole"/>
				<input type="submit" value="Add Role" />
			</form>
		
			<form action="${pageContext.request.contextPath}/ManageRoles?action=updateRole" method="post">	
				Role to Update <br>
				<select name="updateIdRole">
					<c:forEach var = "role" items = "${roles}">
						<option value="${role.id}">${role.roleName}</option>
					</c:forEach><br>
					<input type="text" name="updateRoleName">
				</select>
				<input type="submit" value="Update Role" />
			</form>
			<form action="${pageContext.request.contextPath}/ManageRoles?action=deleteRole" method="post">	
				Role to Delete <br>
				<select name="deleteIdRole">
					<c:forEach var = "role" items = "${roles}">
						<option value="${role.id}">${role.roleName}</option>
					</c:forEach><br>
				</select>
				<input type="submit" value="Delete Role" />
			</form>
			</div>	
			<div class="column column-8" align="left">
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
		</div>
	</div>
	<br>		
</body>
	<style>
	   table {border-collapse:collapse; table-layout:fixed; width:35%;}
	   table th, td {border:solid 2px ; width:100px; word-wrap:break-word;}
	   td{text-align:center;}
	   buttons{align-items: center;}
	</style>
</html>



		
		