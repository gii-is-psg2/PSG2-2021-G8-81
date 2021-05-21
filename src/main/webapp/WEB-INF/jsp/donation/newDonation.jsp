<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<petclinic:layout pageName="donation">
    <jsp:body>
	<h2>
           <c:out value="Nueva donación a ${donation.cause.name}"/>
        </h2>
  <form:form modelAttribute="donation" class="form-horizontal" id="add-donation-form">
        <div class="form-group has-feedback">

            <petclinic:inputField label="Cantidad a donar" name="money" />
        </div>


        <sec:authorize access="isAuthenticated()">
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                        <button class="btn btn-default" type="submit">Donar</button>    
            </div>
        </div>
        </sec:authorize>
    </form:form>
    </jsp:body>
</petclinic:layout>