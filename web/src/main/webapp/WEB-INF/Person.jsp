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
					<div class="column column-6"><span style="color:red">${error}</span>
					</div>
				</c:forEach>
				<a href = "${pageContext.request.contextPath}/MainPage">Back to Main Page</a><p></p>
				<form action="${pageContext.request.contextPath}/MainPage" method="post">
					<input type="hidden" name="personId" value="${person.id}">
					<fmt:formatDate pattern="MM/dd/yyy" value="${person.birthDate}" var="birthDate"/>
					<fmt:formatDate pattern="MM/dd/yyy" value="${person.dateHired}" var="dateHired"/>
					<c:set value="${person.firstName}" var="firstName"/>
					
					<div class="column column-8">
						<fieldset style="padding:3%">
							<legend>PERSON NAME:</legend>
							<table align="center">
								<div>
									<tr>
										<td><span class="required">*</span></div>First Name:</td>
										<td><input type="text" name="firstName" required value="${person.firstName}" placeholder=""></td>
									</tr>
									<tr>
										<td><span class="required">*</span></div>Middle Name:</td>
										<td><input type="text" name="middleName" required value="${person.middleName}"></td>
									</tr>
									<tr>
										<td><span class="required">*</span></div>Last Name:</td>
										<td><input type="text" name="lastName" required value="${person.lastName}"></td>
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
										<td><input type="text" name="gwa" required value="${person.gwa}"></td>
									</tr>
									<tr>
										<td><span class="required">*</span></div>Birthday</td>
										<td><input type="text" name="birthDate" required value="${birthDate}" placeholder="12/30/1900"></td>
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
											<input type="text" name="dateHired" required value="${dateHired}" placeholder="12/30/1900">
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
										<span class="required">*(if employed)</span>
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
										<td><input type="text" name="houseNo" required value="${person.address.houseNo}"></td>
									</tr>
									<tr>
										<td><span class="required">*</span></div>Street:</td>
										<td><input type="text" name="street" required value="${person.address.street}"></td>
									</tr>
									<tr>
										<td><span class="required">*</span></div>Barangay:</td>
										<td><input type="text" name="barangay" required value="${person.address.barangay}"></td>
									</tr>
									<tr>
										<td><span class="required">*</span></div>City:</td>
										<td><input type="text" name="city" required value="${person.address.city}"></td>
									</tr>
									<tr>
										<td><span class="required">*</span></div>ZipCode:</td>
										<td><input type="text" name="zipCode" required value="${person.address.zipCode}"></td>
									</tr>
								</div>
							</table>
						</fieldset>
					</div>
					<br>
					<c:choose>
					<c:when test = "${empty id}">
						<div class="column column-8" align="center">
							<fieldset style="padding:3%">
								<legend>CONTACTS:</legend>
								<table>
								<tr>
									<td>Mobile:</td>
									<td><input type="number" name="mobile"></td>
								</tr>
								<tr>
									<td>Landline:</td>
									<td><input type="number" name="landline"></td>
								</tr>
								<tr>
									<td>Email:</td>
									<td><input type="email" name="email"></td>
								</tr>
							</table>
							</fieldset>
						</div>
					</c:when>
					<c:otherwise>
					</c:otherwise>
					</c:choose>
				
					<div align = "center">
						<c:choose>
							<c:when test = "${empty id}">
								<input type = "submit" value = "Add" align = "center"/>
							</c:when>
							<c:otherwise>
								<p></p>
								<input type = "submit" value = "Update" align = "center"/>
							</c:otherwise>
						</c:choose>
					</div>
					
				</form>
			</div>
			
			<div class="column column-8" align="center">
				<form action="${pageContext.request.contextPath}/MainPage" method="get">
					 <input type="submit" value="cancel" align= "center"/>
				</form>		
			</div>
		</div>
		<style>
			.required{
			color:red;
		}
	</body>
</html>
