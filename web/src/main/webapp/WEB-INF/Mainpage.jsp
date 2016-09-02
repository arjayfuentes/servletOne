<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.exercise.servlet1.core.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>

<html>
	<head>
		<h1><b>MAIN PAGE</b></h1>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Main Page</title>
  	</head>
	<body>
		<div>
			<div class="row">
				<div class="column column-8" align="left">
				  <a href="${pageContext.request.contextPath}/PersonOptions">ADD PERSON</a>&nbsp;&nbsp;&nbsp;&nbsp;
				  <a href="${pageContext.request.contextPath}/ManageRoles">MANAGE ROLES</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<br>
					<br>
					<form action="${pageContext.request.contextPath}/MainPage" method="get">
					   Sort Persons By <i>(Select choice and click Sort) :</i></br>
					  <input type="radio" name="sort" value="sortById" checked="checked" >by ID</br>
					  <input type="radio" name="sort" value="sortByDateHired">by Date Hired</br>
					  <input type="radio" name="sort" value="sortByGwa">by GWA</br>
					  <input type="radio" name="sort" value="sortByLastName">by Last Name</br></br>
					  <input type="submit" value="SORT" />
					</form>
					</br>
					<table border="10">
						<tr>
							<th>Person ID</th>
							<th>Full Name</th>
							<th>GWA</th>
              				<th>Birthday</th>
							<th>Address</th>
							<th>Date Hired</th>
							<th>Person Roles</th>
							<th>Contacts</th>
							<th>Options</th>
						</tr>
						<c:set var="persons" value='${persons}' />
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
									    <c:when test="${person.employed="true"}">
												<fmt:formatDate pattern="MM-dd-yyyy" value="${person.dateHired}" /></br><br/>
									    </c:when>
									    <c:otherwise>
									        Not employed
									    </c:otherwise>
									</c:choose>
								</td>
								<td>
									<c:choose>
									    <c:when test="${person.employed="true"}">
												<c:forEach var="role" items="${person.roles}" >
													<c:out value="${role.roleName}"/> </br>
												</c:forEach>
									    </c:when>
									    <c:otherwise>
									        Not employed
									    </c:otherwise>
									</c:choose>
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
								<form class = "buttons" action = "${pageContext.request.contextPath}/PersonOptions" method= "get">
									<input type = "hidden" name = "id" value = '${person.id}'>
									<input type = "submit" value = "Edit"/>
								</form>
								<form class = "buttons" action = "${pageContext.request.contextPath}/PersonOptions" method = "post">
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
	   table {border-collapse:collapse; table-layout:fixed; width:100%;}
	   table th, td {border:solid 2px ; width:120px; word-wrap:break-word;}
	   td{text-align:center;}
	   buttons{align-items: center;}
	</style>
</html>
