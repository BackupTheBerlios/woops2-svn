<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/cc-forms"    prefix="forms" %>

<html:form action="loginUser.do" >
	<forms:message formid="frmError" caption="msg.error" severity="error" width="350"/>
	<br>

    <forms:form 
    	formid="loginForm"
    	type="edit"
    	caption="form.title.login"
    	width="350">
    
        <forms:text 
        	label="form.field.login"
        	property="login"
        	required="true"
        	value=""/>
        
        <forms:password    	
        	label="form.field.password"	
        	property="password"   	
        	required="true"
        	value=""/>

       	<forms:buttonsection default="btnLogin">  
        	<forms:button
            	name="btnLogin"
            	default="true"
                title="button.title.login"
                text="button.title.login"
                width="100"/>     
        </forms:buttonsection>
    
	</forms:form>
    
</html:form>
