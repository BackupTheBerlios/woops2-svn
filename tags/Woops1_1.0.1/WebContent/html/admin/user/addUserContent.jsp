<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/cc-base"    prefix="base" %>

<html:form action="addUser.do" >
	<forms:message formid="frmError" caption="msg.error" severity="error" width="350"/>
	<forms:message formid="frmInfo" caption="msg.info" severity="information" width="350"/>
    <forms:form 
    	formid="addUserForm"
    	type="edit"
    	caption="admin.addUser.title"
    	width="600">
    	
    	<forms:text 
        	label="admin.addUser.firstName"
        	property="firstName"
        	required="true"/>
        
        <forms:text    	
        	label="admin.addUser.lastName"	
        	property="lastName"   	
        	required="true"/>
		    
		<forms:select
        	label="admin.addUser.role"
        	property="roleId"
        	>
        		<base:options property="roleOptions" keyProperty="id" labelProperty="labelCode"/>
		</forms:select>
		    
		    
        <forms:text 
        	label="form.field.login"
        	property="login"
        	required="true"/>
        
        <forms:password    	
        	label="form.field.password"	
        	property="password"   	
        	required="true"/>
        	
        <forms:password    	
        	label="admin.field.checkPassword"	
        	property="password2"   	
        	required="true"/>

       	<forms:buttonsection default="btnAdd">  
        	<forms:button
            	name="btnAdd"
            	default="true"
                title="admin.button.validate"
                text="admin.button.validate"
                width="100"/>     
        </forms:buttonsection>
    
	</forms:form>
	
	<html:hidden property="mode"/>
	<html:hidden property="userId"/>
    
</html:form>
