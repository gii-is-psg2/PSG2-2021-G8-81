<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="petsHotel">
	<h2>Mascotas en el hotel</h2>

	<table id="tablePetsHotel" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 100px;">Fecha de entrada</th>
				<th style="width: 100px;">Fecha de salida</th>
				<th style="width: 100px;">Datos</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${petsHotel}" var="petsHotel">
				<tr>
					<td><c:out value="${petHotel.dateEntry}" />
					<td><c:out value="${petHotel.dateExit}" />
					<td><c:out value="${petHotel.data}" />

				</tr>
			</c:forEach>
		</tbody>
	</table>
<a class="btn btn-default" href='<spring:url value="/petHotel/new" htmlEscape="true"/>'>Solicitar estancia para mi mascota</a>
	
	<br />

</petclinic:layout>
