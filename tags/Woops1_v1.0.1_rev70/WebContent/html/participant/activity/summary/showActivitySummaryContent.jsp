<%@ taglib uri="/cc-controls" prefix="ctrl" %> 
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-html" prefix="html" %>


<html:form action="showActivitySummary.do">	
	
		
	
	<a onClick="window.open('showActivitySummaryToPrint.do?activityId=${ requestScope["activityId"]}','' , 'status=yes,scrollbars=yes,resizable=yes,width=500,height=300')">
	<img border="0" src="images/printer.gif">
	</a>
	
	<table>
	
	<tr>
	<td colspan="3">	
	<forms:form 
		type="display" 
	    name="showActivitySummaryForm" 
	    caption="form.title.showActivitySummary" 
	    formid="frmShowActivitySummary" 
	    width="550"
	    >
    
    
		<forms:text        
			label="form.field.activity.name"         
		 	property="name"        
		 	/>
 
       	<forms:text
			label="form.field.activity.details"
            property="details"          
          	/>
          
        <forms:html
            label="form.field.activity.state"
			>
    		<bean:message 
    			name="showActivitySummaryForm"
    			property="state"
    			/>
    	</forms:html>
          
        <forms:text
			label="form.field.activity.startDate"
            property="startDate"          
          	/>
        
        <forms:text
			label="form.field.activity.endDate"
            property="endDate"          
          	/> 
          	
        <forms:text
			label="form.field.activity.onGoing"
            property="onGoing"          
          	/>    
          	
   	</forms:form>
   
   	</td>
   	</tr>
   
   	<tr height="20">
   	<td colspan="3"></td>
   	</tr>
   
	<tr>
		<td valign="top" width="48%">
		<ctrl:list 
			id="predecessorsList" 
			property="predecessorsList" 
			title="form.table.predecessors.title"  
			rows="5"
			width="100%"
			>
			
			<ctrl:columntext
				title="form.table.predecessors.field.predecessor"
				property="predecessor"
				/>
			
			<ctrl:columnhtml id="activitySequence"
				title="form.table.predecessors.field.predecessorState"
				>
					<bean:message
						name="activitySequence"
						property="predecessorState"/>
			</ctrl:columnhtml> 
			
			<ctrl:columnhtml id="activitySequence"
				title="form.table.succ_pred.field.linkType"
				>
					<bean:message
						name="activitySequence"
						property="linkType"/>
			</ctrl:columnhtml> 
		</ctrl:list>
		</td>
	
		<td width="4%"></td>
	
		<td valign="top" align="right"  width="48%">
		<ctrl:list 
			id="successorsList" 
			property="successorsList" 
			title="form.table.successors.title"  
			rows="5"
			width="100%"
			>
			
			<ctrl:columntext
				title="form.table.successors.field.successor"
				property="successor"
				/>
			
			<ctrl:columnhtml id="activitySequence"
				title="form.table.successors.field.successorState"
				>
					<bean:message
						name="activitySequence"
						property="successorState"/>
			</ctrl:columnhtml> 
			
			<ctrl:columnhtml id="activitySequence"
				title="form.table.succ_pred.field.linkType"
				>
					<bean:message
						name="activitySequence"
						property="linkType"/>
			</ctrl:columnhtml> 	
		</ctrl:list>
		</td>
	
	</tr>
	
	</table>
		
</html:form>
 

  
