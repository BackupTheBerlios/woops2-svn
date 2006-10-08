<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/cc-forms"    prefix="forms" %>



<html:form action="updateEvent.do">

	<forms:message formid="frmError" caption="msg.error" severity="error" width="350"/>
	<forms:message formid="frmWarning" caption="msg.warning" severity="warning" width="350"/>
	<br>
	
    <forms:form 
    type="edit" 
    name="updateEventForm" 
    caption="form.title.updateEvent" 
    formid="frmUpdateEvent" 
    width="550">
    
    
		 <forms:text        
			label="form.field.event.name"         
			property="name"     
			maxlength="50" 
			required="true"  
		 />
 
		<forms:textarea
            label="form.field.event.details"
            property="details"
            cols="64"
            rows="3"          
          />
            
        
		<forms:buttonsection>		
			<forms:button
				name="btnFinish"
				text="form.button.finish"
				title="form.tooltip.updateEvent.finish"
				/>
		</forms:buttonsection>
        
    </forms:form>
    
    
    <html:hidden property="eventId"/>
    
</html:form>