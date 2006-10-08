<%@ taglib uri="/cc-controls" prefix="ctrl" %> 
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/cc-utility"  prefix="util" %>
<%@ page import="org.apache.struts.util.MessageResources" %>
<%@ page import="view.PresentationConstantes" %>

<forms:message formid="frmError" caption="msg.error" severity="error" width="350"/>
<forms:message formid="frmInfo" caption="msg.info" severity="information" width="350"/>
<br>

<bean:define id="basename" value="<%=PresentationConstantes.BASENAME%>" scope="page" type="java.lang.String" />
<bean:define id="confirmMessage" value="<%=MessageResources.getMessageResources(basename).getMessage("table.field.listActivities.deleteConfirmation")%>"/>

<html:form action="listActivities.do">	

	<ctrl:list 
		id="list" 
		property="listActivities" 
		title="table.title.listActivities" 
		width="650" 
		rows="10" 
		refreshButton="true" 
		createButton="true"
		>
	
		<util:designrule
       		rule="@{bean.actionEnabled == 'false' && bean.state == 'created'}"
       		styleId="blockedTask"/>
       		
       	<util:designrule
       		rule="@{bean.actionEnabled == 'false' && bean.state == 'inProgress'}"
       		styleId="blockedTask"/>
       

       
   		<util:designrule
   		   	rule="@{bean.actionEnabled == 'true' && bean.state == 'inProgress'}"
       		styleId="inProgressTask"/>
       		
       	<util:designrule
       		rule="@{bean.onGoing == 'oui'}"
       		styleId="onGoingTask"/>

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
			property="deleteEnabled"
			/> 
		
		<ctrl:columnbutton 
			title="table.field.listActivities.action" 
			text="@{bean.action}" 
			align="center"
			command="change"
			width="150"
			property="actionEnabled"
			/>
	
	</ctrl:list>

</html:form>

<jsp:include page="../../../caption.jsp" />

