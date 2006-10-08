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

<html:form action="showTeamActivitiesSummary.do">	


	<ctrl:list 
		id="list" 
		property="listActivities" 
		title="table.title.showTeamActivitiesSummary" 
		width="650" 
		rows="15" 
		refreshButton="true" 
		>
				
       
   		<util:designrule
   		   	rule="@{bean.state == 'inProgress'}"
       		styleId="inProgressTask"/>
       		
       	<util:designrule
   		   	rule="@{bean.state == 'finished'}"
       		styleId="finishedTask"/>

       	<util:designrule
   		   	rule="@{bean.userLastName == null}"
       		styleId="freeTask"/>


		<ctrl:columnhtml 
			title="table.field.showTeamActivitiesSummary.name" 
			property="name" 
			width="250"
			sortable="true"
			tooltip="table.tooltip.showTeamActivitiesSummary.drillDown"
			/>
		
				
		<ctrl:columnhtml id="activity"
			title="table.field.showTeamActivitiesSummary.state"
			width="150"
			>
				<bean:message
					name="activity"
					property="state"/>
		</ctrl:columnhtml> 
		
		
		<ctrl:columnhtml 
			title="table.field.showTeamActivitiesSummary.userLastName" 
			property="userName" 
			width="250"
			sortable="true"
			tooltip=""
			/>
		
		<ctrl:columnhtml 
			title="table.field.showTeamActivitiesSummary.startDate" 
			property="startDate" 
			width="250"
			sortable="true"
			tooltip=""
			/>
			
		<ctrl:columnhtml 
			title="table.field.showTeamActivitiesSummary.endDate" 
			property="endDate" 
			width="250"
			sortable="true"
			tooltip=""
			/>
	

				
	</ctrl:list> 


</html:form>


<jsp:include page="../../../caption.jsp" />