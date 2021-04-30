<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="cause">

    <h2>Detalles de la causa benéfica</h2>


    <table class="table table-striped">
        <tr>
            <th>Nombre de la causa benéfica</th>
            <td><b><c:out value="${cause.name}"/></b></td>
        </tr>
        <tr>
            <th>Presupuesto objetivo</th>
            <td><c:out value="${cause.budget}"/></td>
        </tr>
        <tr>
            <th>Presupuesto doando</th>
            <td><c:out value="${cause.totalBudget}"/></td>
        </tr>
        <tr>
            <th>ONG</th>
            <td><c:out value="${cause.organization}"/></td>
        </tr>
        <tr>
            <th>Descripción de la causa</th>
            <td><c:out value="${cause.description}"/></td>
        </tr>
    </table>


</petclinic:layout>