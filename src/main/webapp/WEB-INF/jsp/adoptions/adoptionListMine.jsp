<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="adoptions">
	<h2>Adoptions for <c:out value="${adoption.pet.name}"/> </h2>


	<table id ="adoptionTable"class="table table-striped"> 
		<thead>
        	<tr>
        	<th>New Owner</th>
            <th>Description</th>
             <th>Conceder adopción</th>
          	
           
        	</tr>
        	</thead>
        	
        <tbody>
       		<c:forEach items="${adoptions}" var="adoption">
       		<tr>
       			<td><c:out value="${adoption.newOwner.firstName} ${adoption.newOwner.lastName}"></c:out>
       			<td><c:out value="${adoption.description}"></c:out>
       			<td><spring:url value="/adoption/{adoptionId}" var="adoptionUrl"><spring:param name="adoptionId" value="${adoption.id}"/></spring:url><a href="${fn:escapeXml(adoptionUrl)}"><fmt:message key="adoption"/></a>
       		</tr>
       		</c:forEach>
        </tbody>
        
        	
	</table>
	
</petclinic:layout>
