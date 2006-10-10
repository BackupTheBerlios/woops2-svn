<%@ taglib uri="/cc-controls" prefix="ctrl" %> 
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-html" prefix="html" %>


<forms:message formid="frmError" caption="msg.error" severity="error" width="350"/>
<forms:message formid="frmInfo" caption="msg.info" severity="information" width="350"/>
<br>

<html:form action="listEvents.do">	

	<ctrl:list 
		id="listEventsNotOccured" 
		property="listEventsNotOccured" 
		title="table.title.listEventsNotOccured" 
		width="650" 
		rows="10" 
		refreshButton="true"
		>
	
		<ctrl:columntext
			title="table.field.listEvents.name"
			property="name"
			/>
		
		<ctrl:columntext
			title="table.field.listEvents.details"
			property="details"
			/>
			
		<ctrl:columntext
			title="table.field.listEvents.activityName"
			property="activityName"
			/>
			
		<ctrl:columnedit 
			title="table.field.listEventsNotOccured.edit"
			tooltip="table.tooltip.listEventsNotOccured.edit"
			/> 

		
		<ctrl:columnbutton 
			title="table.field.listEventsNotOccured.signalOccurence" 
			text="table.field.listEventsNotOccured.signalOccurence" 
			align="center"
			command="signalOccurence"
			/>
	
	</ctrl:list>

	<br/>

	<ctrl:list 
		id="listEventsOccured" 
		property="listEventsOccured" 
		title="table.title.listEventsOccured" 
		width="650" 
		rows="10" 
		refreshButton="true"
		>
	
		<ctrl:columntext
			title="table.field.listEvents.name"
			property="name"
			/>
		
		<ctrl:columntext
			title="table.field.listEvents.details"
			property="details"
			/>
			
		<ctrl:columntext
			title="table.field.listEvents.activityName"
			property="activityName"
			/>
			
	</ctrl:list>
</html:form>