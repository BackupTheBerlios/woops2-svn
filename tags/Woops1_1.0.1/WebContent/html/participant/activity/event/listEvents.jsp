<%@ taglib uri="/cc-template" prefix="template" %>
<%@ taglib uri="/cc-utility" prefix="util" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>


<template:insert base="/html" template="$/template.jsp">

	
    <template:put  name="title"  direct="true">
    	<util:resource key="page.title.listEvents"/>
    </template:put>
    
    <template:put  name="menuHaut"  content="$/participant/subview/identification.jsp" />
    <template:put  name="menu"  	content="$/participant/subview/menu.jsp"/>
    
    <logic:notEmpty name="listActivitiesForm" property="listBDEs" scope="session">
    	<template:put  name="contents"  content="$/participant/activity/event/listEventsContent.jsp"/>
    </logic:notEmpty>
    <logic:empty name="listActivitiesForm" property="listBDEs" scope="session">
    	<template:put  name="contents"  content="$/message.jsp"/>
    </logic:empty>
</template:insert>