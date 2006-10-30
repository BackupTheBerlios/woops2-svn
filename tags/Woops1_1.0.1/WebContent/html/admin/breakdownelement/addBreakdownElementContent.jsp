<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/cc-base"    prefix="base" %>
<%@ taglib uri="/cc-utility" prefix="util" %>

<html:form action="addBreakdownElement.do" >
	<forms:message formid="frmError" caption="msg.error" severity="error" width="350"/>
	<forms:message formid="frmInfo" caption="msg.info" severity="information" width="350"/>
    <forms:form 
    	formid="addBreakdownElementForm"
    	type="edit"
    	caption="admin.addBreakdownElement.title"
    	formid="frmUserParticipation"
    	width="600">
    	
    	<forms:text 
        	label="admin.addBreakdownElement.prefix"
        	property="prefix"
        	required="true"/>
        	
        <forms:text 
        	label="admin.addBreakdownElement.name"
        	property="name"
        	required="true"/>        	
        	
        <forms:calendar 
        	label="admin.addBreakdownElement.startDate"
        	property="startDate"
        	required="true"
        	maxlength="10" size="8"  format="dd/mm/yyyy"  showformat="right"/>    
        	
        <forms:textarea 
        	label="admin.addBreakdownElement.details"
        	property="details"
        	required="false"
        	cols="64"
            rows="3"
            />
        	
        <forms:select
        	label="admin.addBreakdownElement.kind"
        	property="kindId"
        	>
        	<base:options property="kindOptions" keyProperty="id" labelProperty="labelName"/>
		    </forms:select>
       
       <logic:notEqual name="addBreakdownElementForm" property="mode" value="copy_mode">
	       <forms:swapselect
	            property="usersParticipation"
	            label="admin.addBreakdownElement.manageUserParticipation"
	            orientation="horizontal"
	            labelLeft="admin.addBreakdownElement.manageUserParticipation.toAdd"
	            labelRight="admin.addBreakdownElement.manageUserParticipation.added"
	            valign="top"
	            size="10"
	            style="width: 250;"
	            align="center"
	            filter="false"
	            required="false"
	            disabled="false">
	            
	            <base:options 
	            	property="userParticipationOptions"  
	            	keyProperty="id" 
	            	labelProperty="name"/>
	        
			</forms:swapselect>
		</logic:notEqual>

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
	<html:hidden property="bkId"/>
	    
</html:form>
