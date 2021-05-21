<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="causes">
    <h2>Causas</h2>

    <table id="causesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Suma donaciones</th>
            <th>Total</th>
            <th>Organización</th>
            <th>Actions</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${causes}" var="cause">
            <tr>
                <td>
                   	<c:out value="${cause.name}"/>
                </td>
                <td>
                    <c:out value="${cause.description} "/>
                </td>
                <td>
                    <c:out value="${cause.budget}"/>
                </td>
                <td>
                    <c:out value="${cause.totalBudget} "/>
                </td>
                <td>
                    <c:out value="${cause.organization} "/>
                </td>
                <td>
                	<spring:url value="/causes/{causeId}" var="causeUrl">
                	<spring:param name="causeId" value="${cause.id}" />
					</spring:url> <a class="btn btn-default" href="${fn:escapeXml(causeUrl)}">Detalles</a>
                </td>

                <td>
                	<spring:url value="/donation/{donationId}" var="donateUrl">
                	<spring:param name="donationId" value="${cause.id}" />
					</spring:url> <a class="btn btn-default" href="${fn:escapeXml(donateUrl)}">Donar</a>
                </td>
				

            </tr>
        </c:forEach>
        </tbody>
    </table>

    <table class="table-buttons">
        <tr>
            <td>
            	<a class="btn btn-default" href='<spring:url value="/causes/new" htmlEscape="true"/>'>Crear causa</a>

               
            </td>     
        </tr>
    </table>
</petclinic:layout>
