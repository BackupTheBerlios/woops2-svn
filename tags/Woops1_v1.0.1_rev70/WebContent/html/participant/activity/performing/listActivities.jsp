<%@ taglib uri="/cc-template" prefix="template" %>
<%@ taglib uri="/cc-utility" prefix="util" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>


<template:insert base="/html" template="$/template.jsp">
    <template:put  name="menuHaut"  content="$/participant/subview/identification.jsp" />
    
    <logic:notEmpty name="listActivitiesForm" property="listBDEs" scope="session">
    	<template:put  name="title"  direct="true">
    		<util:resource key="page.title.listActivities"/>
    	</template:put>
    	<template:put  name="menu"  	content="$/participant/subview/menu.jsp"/>
    	<template:put  name="contents"  content="$/participant/activity/performing/listActivitiesContent.jsp"/>
    </logic:notEmpty>
    <logic:empty name="listActivitiesForm" property="listBDEs" scope="session">
    	<template:put  name="contents"  content="$/message.jsp"/>
    </logic:empty>
</template:insert>