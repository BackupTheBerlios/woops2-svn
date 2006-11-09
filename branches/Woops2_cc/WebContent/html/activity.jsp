<%@ taglib uri="/cc-template" prefix="template" %>
<%@ taglib uri="/cc-utility" prefix="util" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<template:insert base="/html" template="$/template.jsp">
<f:loadBundle var="bundle" basename="woops2.Resources" />
	<template:put  name="title" direct="true" >
    </template:put>
    
    <template:put  name="contents"  content="$/activityContent.jsp" />
    
</template:insert>