<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="adoptions">
	<h2>Adoptions</h2>


	<table id ="adoptionTable"class="table table-striped"> 
		<thead>
        	<tr>
        	<th>Pet</th>
            <th>Previous Owner</th>
          	
           
        	</tr>
        	</thead>
        	
        <tbody>
       		<c:forEach items="${pets}" var="adoption">
       		<tr>
       			<td><c:out value="${pet}"></c:out>
       			<td><c:out value="${pet.owner.firstName} ${ pet.owner.lastName}"></c:out>
       			<td>
       		</tr>
       		</c:forEach>
        </tbody>
        
        	
	</table>
	
</petclinic:layout>
