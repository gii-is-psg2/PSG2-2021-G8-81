<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="adoptions">
	<h2>Mascotas que ha puesto en adopción</h2>
	
                 
         	 <c:forEach items="${pets}" var="pets">
          					 <spring:url value="/adoptionApplied/{adoptId}" var="adoptUrl">
                        <spring:param name="adoptId" value="${pets.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(adoptUrl)}">
          					<c:out value="${pets.name}" /></a>
    					</c:forEach>
  					
	
	
</petclinic:layout>
	