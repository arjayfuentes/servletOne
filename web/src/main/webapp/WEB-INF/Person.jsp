<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.exercise.servlet1.core.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>

<html>
	<head>
		<title>Person</title>
	</head>
	<body>
		<div>
			<div class="row">
				
				<c:forEach var = "error" items = "${errors}">
					<div class="column column-6"><span style="color:red">${error}</span></div>
				</c:forEach>
				
				<form action="${pageContext.request.contextPath}/AddPerson" method="post">
					<input type="hidden" name="personId" value="${person.id}">
					<fmt:formatDate pattern="MM/dd/yyyy" value="${person.birthDate}" var="birthDate"/>
					<fmt:formatDate pattern="MM/dd/yyyy" value="${person.dateHired}" var="dateHired"/>
					<div class="column column-8">
						<fieldset style="padding:3%">
							<legend>PERSON NAME:</legend>
							<table align="center">
								<div>
									<tr>
										<td><span class="required">*</span></div>First Name:</td>
										<td><input type="text" name="firstName" value="${person.firstName}"></td>
									</tr>
									<tr>
										<td><span class="required">*</span></div>Middle Name:</td>
										<td><input type="text" name="middleName" value="${person.middleName}"></td>
									</tr>
									<tr>
										<td><span class="required">*</span></div>Last Name:</td>
										<td><input type="text" name="lastName" value="${person.lastName}"></td>
									</tr>
									<tr>
										<td>&nbsp;&nbsp;Suffix:</td>
										<td><input type="text" name="suffix" value="${person.suffix}"></td>
									</tr>
									<tr>
										<td>&nbsp;&nbsp;Title:</td>
										<td><input type="text" name="title" value="${person.title}"></td>
									</tr>
								</div>
							</table>
						</fieldset>
					</div>
					<br>
										
					<div class="column column-8" >
					<fieldset style="padding:3%">
						<legend>OTHER INFO:</legend>
							<table align="center">
								<div>
									<tr>
										<td><span class="required">*</span></div>GWA:</td>
										<td><input type="text" name="gwa" value="${person.gwa}"></td>
									</tr>
									<tr>
										<td><span class="required">*</span></div>Birthday</td>
										<td><input type="date" name="birthday" value="${birthDate}" placeholder="12/30/1900"></td>
									</tr>
									<tr>
										<td><span class="required">*</span>Employed:</td>
										<td>
											<input type="radio" name="employed" value="yes" ${person.getEmployed() ? 'checked' : ''}> Yes
											<input type="radio" name="employed" value="no" ${person.getEmployed() ? '' : 'checked'}> Not Employed
										</td>
									</tr>
									<tr>
										<td>Date Hired:</td>
										<td>
											<fmt:formatDate value="${ojbect.date}" pattern="dd/MM/yyyy" />
											<input type="date" name="dateHired" value="${dateHired}" placeholder="12/30/1900">
											<span class="required">*(if employed)</span>
										</td>
									</tr>
									<tr>
										<td>Roles:</td>
										<td>
										<c:set var="roles" value="${roles}"/>
										<c:set var="personRoles" value="${person.roles}"/>
										<c:forEach var="role" items="${roles}">
											<c:if test="${personRoles.contains(role)}">
												<input type="checkbox" name="roleId" value="${role.id}" checked="checked"/>${role.roleName}<br/>
											</c:if>
											<c:if test="${!personRoles.contains(role)}">
												<input type="checkbox" name="roleId" value="${role.id}"/>${role.roleName}<br/>
											</c:if>
										</c:forEach>
										</td>
									</tr>
								</div>
							</table>
						</fieldset>
					</div>
					<br>
					
					<div class="column column-8" align="center">
						<fieldset style="padding:3%">
							<legend>ADDRESS:</legend>
							<table>
								<div>
									<tr>
										<td><span class="required">*</span></div>House No:</td>
										<td><input type="text" name="houseNo" value="${person.address.houseNo}"></td>
									</tr>
									<tr>
										<td><span class="required">*</span></div>Street:</td>
										<td><input type="text" name="street" value="${person.address.street}"></td>
									</tr>
									<tr>
										<td><span class="required">*</span></div>Barangay:</td>
										<td><input type="text" name="barangay" value="${person.address.barangay}"></td>
									</tr>
									<tr>
										<td><span class="required">*</span></div>City:</td>
										<td><input type="text" name="city" value="${person.address.city}"></td>
									</tr>
									<tr>
										<td><span class="required">*</span></div>ZipCode:</td>
										<td><input type="text" name="zipCode" value="${person.address.zipCode}"></td>
									</tr>
								</div>
							</table>
						</fieldset>
					</div>
					<br>
					<div class="column column-6" align="center">
						<fieldset style="padding:1%">
						<legend>CONTACTS:</legend>
							<table align="center">
								<div>
									<c:forEach var="contact" items="${person.contacts}">
									<tr>
										<td>${contact.contactType }</td>
										<td>${contact.contactValue }</td>
										<td>
											<form class = "buttons" action = "${pageContext.request.contextPath}/PersonOptions" method = "post">
												<input type = "hidden" name = "id" value = "${person.id}">
												<input type = "submit" value = "Delete"/>
											</form>
										</td>	
									</tr>	
									</c:forEach>
								</div>
							</table>
						</fieldset>
					</div>
				    <div align = "center">
						<c:choose>
							<c:when test="${empty id}">
								<input type="submit" value="add" align= "center"/>
							</c:when>
							<c:otherwise>
								<input type="submit" value="save" align= "center"/>
							</c:otherwise>
						</c:choose>
					</div>
				
				</form>
				
				<div align = "center">
					<form action="${pageContext.request.contextPath}/MainPage" method="get">
					 <input type="submit" value="cancel" align= "center"/>
					</form>
				</div>

			</div>
		</div>
		
		<style>
			.required{
			color:red;
			}
		</style>
		
	</body>
</html>
