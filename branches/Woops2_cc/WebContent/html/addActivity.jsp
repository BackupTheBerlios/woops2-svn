<%@ taglib uri="/cc-template" prefix="template" %>
<%@ taglib uri="/cc-utility" prefix="util" %>

<template:insert base="/html" template="$/template.jsp">

	<template:put  name="title" direct="true" >
    	<util:resource key="page.title.listActivities"/>
    </template:put>
    
    <template:put  name="contents"  content="$/addActivityContent.jsp" />
    
</template:insert>