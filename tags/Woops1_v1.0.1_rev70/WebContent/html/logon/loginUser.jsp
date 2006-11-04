<%@ taglib uri="/cc-template" prefix="template" %>
<%@ taglib uri="/cc-utility" prefix="util" %>

<template:insert base="/html" template="$/template.jsp">

    <template:put  name="title" direct="true" >
    	<util:resource key="page.title.login"/>
    </template:put>
    
    <template:put  name="contents"  content="$/logon/loginUserContent.jsp" />
    
</template:insert>