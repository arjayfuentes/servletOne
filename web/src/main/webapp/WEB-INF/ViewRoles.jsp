<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ page import = "com.exercise.servlet1.core.*"%>
<%@ page import = "java.io.*"%>
<%@ page import = "java.util.*"%>

<html>
<head>
	<h1><b>LIST OF ROLES</b></h1>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>List of Roles</title>
</head>
<body>
	<a href = ${pageContext.request.contextPath}/MainPage>MAIN PAGE<br/></a>
	<div>
		<div class="row"><br>
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
</body>
	<style>
	   table {border-collapse:collapse; table-layout:fixed; width:35%;}
	   table th, td {border:solid 2px ; width:100px; word-wrap:break-word;}
	   td{text-align:center;}
	   buttons{align-items: center;}
	</style>
</html>



		
		