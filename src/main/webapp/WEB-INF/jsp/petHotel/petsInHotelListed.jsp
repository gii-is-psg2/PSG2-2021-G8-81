<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="petsHotel">
	<h2><fmt:message key="photel"/></h2>

	<table id="tablePetsHotel" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 100px;"><fmt:message key="adate"/></th>
				<th style="width: 100px;"><fmt:message key="ddate"/></th>
				<th style="width: 100px;"><fmt:message key="datum"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${petsHotel}" var="petHotel">
				<tr>
					<td><c:out value="${petHotel.dateEntry}" />
					<td><c:out value="${petHotel.dateExit}" />
					<td><c:out value="${petHotel.pet.name}" />

				</tr>
			</c:forEach>
		</tbody>
	</table>
<a class="btn btn-default" href='<spring:url value="/petHotel/new" htmlEscape="true"/>'><fmt:message key="request"/></a>
	
	<br />

</petclinic:layout>
