<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.exercise.servlet1.core.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>

<html>
	<head>
		<h1><b>HOME PAGE</b></h1>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  	</head>
	<body>
		<div>
			<div class="row">
				<div class="column column-8" align="left">
				  	<a href="${pageContext.request.contextPath}/PersonOptions?action=addPerson">ADD PERSON</a>
				 	<a href="${pageContext.request.contextPath}/ManageRoles">MANAGE ROLES</a>
					<br><br>
					<form action="${pageContext.request.contextPath}/MainPage" method="get">
					  <label>List Employees:</label><br>
					  <input type="radio" name="sort" value="sortById" checked="checked" >by ID</br>
					  <input type="radio" name="sort" value="sortByLastName">by Last Name</br>
					  <input type="radio" name="sort" value="sortByDateHired">by Date Hired</br>
					  <input type="radio" name="sort" value="sortByGwa">by GWA</br>
					  <input type="submit" value="SORT" />
					</form>
					<form action = "${pageContext.request.contextPath}/MainPage" method = "get">
						<input type = "text" name = "searchId" placeholder = "Enter Id here to Search"/>
						<input type = "submit" name = "search" value = "Search"/><p></p>				
					</form>
					
					<table class="table" border="10">
						<thead class="thead-inverse">
						<tr>
							<th>Person ID</th>
							<th>Full Name</th>
							<th>GWA</th>
              				<th>Birthday</th>
							<th>Address</th>
							<th>Employed?</th>
							<th>Date Hired</th>
							<th>Person Roles</th>
							<th>Contacts</th>
							<th>Options</th>
						</tr>
						 </thead>
						<c:set var="persons" value="${persons}"/>
						<c:forEach var="person" items="${persons}" >
							<tr>
								<td><c:out value="${person.id}"/></td>
								<td>
								    <c:out value="${person.title}"/>
								    <c:out value="${person.firstName}"/>
								    <c:out value="${person.middleName}"/>
                  				 	<c:out value="${person.lastName}"/>
                    				<c:out value="${person.suffix}"/>
                				</td>
              					<td><c:out value="${person.gwa}"/></td>
								<td><fmt:formatDate pattern="MM-dd-yyyy" value="${person.birthDate}" /></td>
								<td>
									<c:out value="${person.address.houseNo}"/>
									<c:out value="${person.address.street}"/>
									<c:out value="${person.address.barangay}"/>
									<c:out value="${person.address.city}"/>
									<c:out value="${person.address.zipCode}"/>
								</td>
								<td>
									<c:choose>
									    <c:when test="${person.getEmployed()==true}">
											Employed
									    </c:when>
									    <c:otherwise>
									        Not employed
									    </c:otherwise>
									</c:choose>
								</td>
								<td>
									<fmt:formatDate pattern="MM-dd-yyyy" value="${person.dateHired}" /></br>
								</td>
								<td>
									<c:forEach var="role" items="${person.roles}" >
											<c:out value="${role.roleName}"/> </br>
									</c:forEach>
								</td>
								<td>
									<c:set var="contacts" value="${person.contacts}" />
									<c:if test="${contacts.isEmpty()}">
										No contacts.
									</c:if>
									<c:forEach var = "contact" items = '${contacts}'>
										<c:out value = "${contact.contactType}"/> = <c:out value = "${contact.contactValue}"/>
										<br/>
									</c:forEach>
									<br/>
								</td>
								<td>
								<br>
								<form class = "buttons" action = "${pageContext.request.contextPath}/PersonOptions?action=editPerson">
									<input type = "hidden" name = "id" value = "${person.id}">
									<input type = "submit" value = "Edit"/>
								</form>
								<form class = "buttons" action = "${pageContext.request.contextPath}/ManageContacts" method = "get">
									<input type = "hidden" name = "id" value = "${person.id}">
									<input type = "submit" value="Manage Contact"/> 
								</form>
								<form class = "buttons" action = "${pageContext.request.contextPath}/PersonOptions?action=deletePerson" method = "post">
									<input type = "hidden" name = "id" value = "${person.id}">
									<input type = "submit" onclick = "return confirm('WARNING!!! : Do you really want to delete employee record of \n\t\t\t\t ${person.firstName} ${person.middleName} ${person.lastName}?')" value = "Delete"/>
								</form>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</body>
	<style>
	   table {
	    	border-collapse: collapse;
	    	table-layout:fixed;
	    	width: 100%;
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
		head { display: block; }
		title { display: block; font-size: 200%; font-weight: bold; }
		
	</style>
	 
	
</html>
