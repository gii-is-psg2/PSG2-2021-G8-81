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
            <c:if test="${pet['new']}"> </c:if> <fmt:message key="booking"/>
        </h2>
  <form:form modelAttribute="petHotel" class="form-horizontal" id="add-petHotel-form">
        <div class="form-group has-feedback">
        	<fmt:message var="adate" key="adate"/>
        	<fmt:message var="ddate" key="ddate"/>
            <petclinic:inputField label="${adate}" name="dateEntry" />
            <petclinic:inputField label="${ddate}" name="dateExit" />
           <label for="vehiculo"><fmt:message key="btext"/></label>
		<petclinic:inputField label="" name="dateExit" />
</div>
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
