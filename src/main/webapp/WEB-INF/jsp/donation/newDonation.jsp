<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<petclinic:layout pageName="owners">
    <jsp:body>
<h2>
           Nueva donación
        </h2>
  <form:form modelAttribute="donation" class="form-horizontal" id="add-donation-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Cantidad a donar" name="money" />
 <div class="control-group">
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <b><c:out value="Escoja una causa benéfica"/></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <select name="cause.id" id="cause.id"> 
  		<c:forEach items="${cause}" var="cause">
  		<option  value="${cause.id}" ><c:out value="${cause.name}" /></option> 
    </c:forEach>
  </select> 
  <br>
       
               </div>	
              </div>
                 <br>

        <sec:authorize access="hasAuthority('owner')">
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                        <button class="btn btn-default" type="submit"><fmt:message key="donate"/></button>    
            </div>
        </div>
        </sec:authorize>
    </form:form>
    </jsp:body>
</petclinic:layout>