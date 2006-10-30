<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/cc-base"     prefix="base" %>
<%@ taglib uri="/cc-utility" prefix="util" %>
<%@ taglib uri="/cc-controls" prefix="ctrl" %>


<html:form action="/manageDependancesTypes">
	
	<forms:message formid="frmError" caption="title.error" severity="error" width="350"/>
	<forms:message formid="frmInfo" caption="msg.info" severity="information" width="350"/>
	
	<br>
	
	<table width="350">
	
	<tr>
    
    <td colspan="3">
    
    <ctrl:list
		name="KEY_DEPENDANCES_LIST"
		title="list.title.manageDependancesTypes"
		>
       
        <ctrl:columntext    title="list.manageDependancesTypes.predecessor"         property="predecessor"/>
        
        <ctrl:columntext    title="list.manageDependancesTypes.predecessorState"         property="predecessorState"/>
              
        <ctrl:columngroup title="list.manageDependancesTypes.columngroup.title" align="center">
			<ctrl:columnradio	title="finishToStart"   property="linkType"   value="finishToStart"		editableProperty="finishToStartEditable"/>
			<ctrl:columnradio	title="finishToFinish"	property="linkType"   value="finishToFinish"    editableProperty="finishToFinishEditable"/>
			<ctrl:columnradio	title="startToStart"    property="linkType"   value="startToStart"  	editableProperty="startToStartEditable"/>
			<ctrl:columnradio	title="startToFinish"   property="linkType"   value="startToFinish"   	editableProperty="startToFinishEditable"/>
		</ctrl:columngroup>      
               
    </ctrl:list>
	
	</td>
	
	</tr>
    
    <tr><td></td><td></td><td></td></tr>
    
    <tr>
    
    <td align="right" width="50%">
    	<ctrl:button
			name="btnPrevious"
			text="form.button.previous"
			title="list.tooltip.manageDependancesTypes.previous"
			/>
	</td>
	
	<td align="right" width="25%">	
		<ctrl:button
			name="btnNext"
			text="form.button.next"
			disabled="true"
			/>
	</td>
	
	<td align="right" width="25%">	
		<ctrl:button
			name="btnFinish"
			text="form.button.finish"
			title="list.tooltip.manageDependancesTypes.finish"
			/>
	</td>
	
	</tr>
	
	</table>
	
	<html:hidden property="activityId"/>
   
</html:form>
