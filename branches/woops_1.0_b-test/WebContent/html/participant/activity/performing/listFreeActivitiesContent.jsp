<%@ taglib uri="/cc-controls" prefix="ctrl" %> 
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/cc-utility"  prefix="util" %>
<%@ page import="org.apache.struts.util.MessageResources" %>

<forms:message formid="frmError" caption="msg.error" severity="error" width="350"/>
<forms:message formid="frmInfo" caption="msg.info" severity="information" width="350"/>
<br>

<bean:define id="confirmMessage" value="<%=MessageResources.getMessageResources("ApplicationResources").getMessage("table.field.listActivities.deleteConfirmation")%>"/>

<html:form action="listFreeActivities.do">	
	
	<ctrl:list 
		id="list" 
		property="listFreeActivities" 
		title="table.title.listFreeActivities" 
		width="650" 
		rows="10" 
		refreshButton="true" 
		createButton="true"
		>
		
		    <util:designrule
       			rule="@{bean.onGoing == 'oui'}"
       			styleId="onGoingTask" />
       			
       		<util:designrule
       			rule="@{bean.affectEnabled == false}"
       			styleId="blockedTask"/>
       		
			<ctrl:columndrilldown 
				title="table.field.listActivities.name" 
				property="name" 
				width="250"
				sortable="true"
				tooltip="table.tooltip.listActivities.drillDown"
				/>
				
			<ctrl:columnhtml id="activity"
				title="table.field.listActivities.state"
				width="150"
				>
					<bean:message
						name="activity"
						property="state"/>
			</ctrl:columnhtml> 
			
			<ctrl:columnedit 
				title="table.field.listActivities.edit"
				tooltip="table.tooltip.listActivities.edit"
				/> 

			<ctrl:columndelete 
				title="table.field.listActivities.delete"
				onclick="return confirm('${confirmMessage}');"
				tooltip="table.tooltip.listActivities.delete"
				/> 
			
			<ctrl:columnbutton 
					title="table.field.listActivities.action" 
					text="@{bean.action}" 
					align="center"
					command="change"
					width="150"
					property="affectEnabled"
					>
			</ctrl:columnbutton>

			
	</ctrl:list>
	
</html:form>
 
<jsp:include page="../../../caption.jsp" />
  
