<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<petclinic:layout pageName="owners">
       <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#dateEntry").datepicker({dateFormat: 'dd/mm/yy'});
            });
            $(function () {
                $("#dateExit").datepicker({dateFormat: 'dd/mm/yy'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
<h2>

           Nueva reserva en el hotel
        </h2>
  <form:form modelAttribute="petHotel" class="form-horizontal" id="add-petHotel-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Fecha de entrada" name="dateEntry" />
            <petclinic:inputField label="Dia de salida" name="dateExit" />
 <div class="control-group">
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <b><c:out value="Escoja una mascota"/></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <select name="pet.id" id="pet.id"> 
  		<c:forEach items="${pet}" var="pet">
  		<option  value="${pet.id}" ><c:out value="${pet.name}" /></option> 
    </c:forEach>
  </select> 
  <br>
       
                </div>	
                 <br>
                 
		 <petclinic:inputField label="Datos de inter�s" name="description" />

        <sec:authorize access="hasAuthority('owner')">
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                        <button class="btn btn-default" type="submit"><fmt:message key="book"/></button>    
            </div>
        </div>
        </sec:authorize>
    </form:form>
    </jsp:body>
</petclinic:layout>
