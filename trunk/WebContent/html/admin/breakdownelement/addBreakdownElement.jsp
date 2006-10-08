<%@ taglib uri="/cc-template" prefix="template" %>
<%@ taglib uri="/cc-utility" prefix="util" %>

<template:insert base="/html" template="$/template.jsp">

    <template:put  name="title" direct="true">
    <util:resource key="admin.addBreakdownElement.title" /></template:put>
    <template:put  name="menuHaut"  content="$/admin/subview/identification.jsp" />
    <template:put  name="menu"  	content="$/admin/subview/menu.jsp"/>
    <template:put  name="contents"  content="$/admin/breakdownelement/addBreakdownElementContent.jsp" />
    
</template:insert>