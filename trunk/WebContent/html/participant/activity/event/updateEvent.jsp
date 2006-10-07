<%@ taglib uri="/cc-template" prefix="template" %>
<%@ taglib uri="/cc-utility" prefix="util" %>

<template:insert base="/html" template="$/template.jsp">

    <template:put  name="title"  direct="true">
    	<util:resource key="page.title.updateEvent"/>
    </template:put>
    
    <template:put  name="menuHaut"  content="$/participant/subview/identification.jsp" />
    <template:put  name="menu"  	content="$/participant/subview/menu.jsp" />
    <template:put  name="contents"  content="$/participant/activity/event/updateEventContent.jsp"/>
    
</template:insert>