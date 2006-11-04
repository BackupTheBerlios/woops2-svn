<%@ taglib uri="/cc-menu" prefix="menu" %>
<%@ taglib uri="/cc-utility" prefix="util" %>

<menu:menu  id="user"  type="sidebar"  width="150">
     	
     	<menu:menuitem   	
        	id="1"  
        	text="admin.menu.home"      
        	action="admin.do"/>
        
         	
        <menu:menuitem   	
        	id="2"  
        	text="admin.menu.createUser"      
        	action="addUser.do?mode=insert_mode"/>
        	
        <menu:menuitem   	
        	id="3"  
        	text="admin.menu.createBreakdownElement"      
        	action="addBreakdownElement.do?mode=insert_mode"/>
     	
     	

</menu:menu>
