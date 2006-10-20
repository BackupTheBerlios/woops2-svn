<%@ taglib uri="/cc-controls" prefix="ctrl" %> 
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-html" prefix="html" %>

<forms:message formid="frmError" caption="msg.error" severity="error" width="350"/>
<br>

<html:form action="historizedActivities.do">	
	
	<ctrl:list 
		id="list" 
		property="listActivities" 
		title="table.title.listHistorizedActivities" 
		width="650" 
		rows="15" 
		refreshButton="false" 
		createButton="false"
		>

			<ctrl:columndrilldown 
				title="table.field.listActivities.name" 
				property="name" 
				width="250"/>
			
			<ctrl:columntext 
				title="table.field.listActivities.startDate"
				property="startDate"
				width="150"
				sortable="true"/>
				
			<ctrl:columntext 
				title="table.field.listActivities.endDate"
				property="endDate"
				width="150"
				sortable="true"/>				
	</ctrl:list>
	
</html:form>
 

  
