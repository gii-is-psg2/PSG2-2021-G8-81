<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<petclinic:layout pageName="adoptions"> 
<jsp:body>
<h2>Adopción</h2>	

<form:form modelAttribute="adoption" class="form-horizontal" id="add-adoption-form">
<div class="form-group has-feedback">
<div class="control-group">
<b><c:out value="Escoja una mascota"/></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <select name="pet.id" id="pet.id"> 
          <c:forEach items="${pet}" var="pet">
          <option  value="${pet.id}" ><c:out value="${pet.name}" /></option> 
    </c:forEach>
  </select>
 </div> 
 </div>
 <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                        <button class="btn btn-default" type="submit">Dar en adopcion</button>
            </div>
        </div>
  </form:form>
</jsp:body>



</petclinic:layout>