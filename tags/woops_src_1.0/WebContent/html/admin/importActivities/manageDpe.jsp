<%@ taglib uri="/cc-template" prefix="template" %>
<%@ taglib uri="/cc-utility" prefix="util" %>
<%@ taglib uri="/cc-forms"    prefix="forms" %>

<template:insert base="/html" template="$/template.jsp">

     <template:put  name="title"  direct="true">
    	<util:resource key="admin.manageDpe.title" /></template:put>
    <template:put  name="menuHaut"  content="$/admin/subview/identification.jsp" />
    <template:put  name="menu"  	content="$/admin/subview/menu.jsp"/>
 	<template:put  name="contents"  content="$/admin/importActivities/manageDpeContent.jsp"/>
    
</template:insert>