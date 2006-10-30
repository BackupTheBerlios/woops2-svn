<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/cc-menu" prefix="menu" %>
<%@ taglib uri="/cc-controls" prefix="ctrl" %>
<%@ taglib uri="/cc-base" prefix="base" %>

<bean:write name="USER" property="firstName"/> <bean:write name="USER" property="lastName"/>

<menu:menu  id="identification"  type="sidebar"  width="150">

        <menu:menuitem   	
        	id="1"  
        	text="menu.deconnection"      
        	action="logoutUser.do"/>
        	
</menu:menu>

<br>

<logic:notEmpty name="listActivitiesForm" property="listBDEs" scope="session">
	<html:form action="changeBDE.do">
	<ctrl:select
        	name="listActivitiesForm"
        	property="bdeId"
        	scope="session"
        	onchange="submit();"
        	>
    	    	<base:options name="listActivitiesForm" property="listBDEs" keyProperty="id" labelProperty="label" />
	</ctrl:select>
	</html:form>
</logic:notEmpty>	    