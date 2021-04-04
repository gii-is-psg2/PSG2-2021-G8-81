<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
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
            <c:if test="${pet['new']}">Nueva </c:if> reserva en el hotel
        </h2>
  <form:form modelAttribute="petHotel" class="form-horizontal" id="add-petHotel-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Fecha de entrada" name="dateEntry" />
            <petclinic:inputField label="Dia de salida" name="dateExit" />
           <label for="vehiculo">Rellene en el siguiente cuadro de texto el nombre de su mascuta y cualquier otro dato que sea de utilidad</label>
		<petclinic:inputField label="" name="dateExit" />
</div>
        <sec:authorize access="hasAuthority('owner')">
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                        <button class="btn btn-default" type="submit">Reservar</button>    
            </div>
        </div>
        </sec:authorize>
    </form:form>
    </jsp:body>
</petclinic:layout>
