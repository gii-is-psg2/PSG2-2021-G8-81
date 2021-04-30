<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<petclinic:layout pageName="adoptions"> 
<jsp:body>
<h2>Aplicar para adopción</h2>	
<form:form modelAttribute="adoption"
                   class="form-horizontal">
<div class="form-group has-feedback">
                <div class="form-group">
                    <c:out value="Adoptar a ${adoption.pet} ${adoption.previousOwner.firstName}"/>
                         <petclinic:inputField label="¿Por qué se considera un buen dueño?" name="description" />
 						</div>
                                        
                    
                    </div>

                <button class="btn btn-default" type="submit">Aplicar</button>
                  </form:form>
</jsp:body>
                
</petclinic:layout>