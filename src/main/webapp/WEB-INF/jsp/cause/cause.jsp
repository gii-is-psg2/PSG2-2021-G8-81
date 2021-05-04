<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="causes">
    <h2>Crear una causa benéfica</h2>
        <table class="table table-striped">
        <tr>
            <th>Nombre de la causa</th>
            <td><b><c:out value="${cause.name}"/></b></td>
        </tr>
        <tr>
            <th>Descripción</th>
            <td><c:out value="${cause.description}"/></td>
        </tr>
        <tr>
            <th>Presupuesto</th>
            <td><c:out value="${cause.budget}"/></td>
        </tr>
        <tr>
            <th>Presupuesto recaudado</th>
            <td><c:out value="${cause.totalBudget}"/></td>
        </tr>
         <tr>
            <th>ONG</th>
            <td><c:out value="${cause.organization}"/></td>
        </tr>
    </table>
    <spring:url value="/donation/{causeId}" var="donateUrl">
                	<spring:param name="causeId" value="${cause.id}" />
					</spring:url> <a class="btn btn-default" href="${fn:escapeXml(donateUrl)}">Donar</a>


</petclinic:layout>