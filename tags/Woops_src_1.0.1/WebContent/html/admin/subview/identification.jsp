<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/cc-menu" prefix="menu" %>
<%@ taglib uri="/cc-utility" prefix="util" %>

<bean:write name="USER" property="firstName"/> <bean:write name="USER" property="lastName"/>

<menu:menu  id="identification"  type="sidebar"  width="150">

        <menu:menuitem   	
        	id="1"  
        	text="menu.deconnection"      
        	action="logoutUser.do"/>
        	
</menu:menu>