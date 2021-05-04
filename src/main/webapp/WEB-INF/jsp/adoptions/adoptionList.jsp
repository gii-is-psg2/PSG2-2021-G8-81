<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="adoptions">
	<h2>Adopciones</h2>


	<table id ="adoptionTable"class="table table-striped"> 
		<thead>
        	<tr>
        	<th>Mascota</th>
            <th>Anterior dueño</th>
          	<th></th>
           
        	</tr>
        	</thead>
        	
        <tbody>
       		<c:forEach items="${pets}" var="pet">
       		<tr>
       			<td><c:out value="${pet.name}"></c:out>
       			<td><c:out value="${pet.owner.firstName} ${ pet.owner.lastName}"></c:out>
       			 <td>
                	<spring:url value="/applyAdoption/{adoptId}" var="adoptUrl">
                	<spring:param name="adoptId" value="${pet.id}" />
					</spring:url> <a class="btn btn-default" href="${fn:escapeXml(adoptUrl)}">Adoptar</a>
                </td>
       			<td>
       		</tr>
       		</c:forEach>
        </tbody>
        
        	
	</table>
	
</petclinic:layout>
