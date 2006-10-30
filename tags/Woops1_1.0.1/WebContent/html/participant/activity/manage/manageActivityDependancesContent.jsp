<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/cc-base"     prefix="base" %>
<%@ taglib uri="/cc-utility" prefix="util" %>

<html:form action="manageActivityDependances.do">
	
	
	<forms:message formid="frmError" caption="msg.error" severity="error" width="350"/>
	<forms:message formid="frmInfo" caption="msg.info" severity="information" width="350"/>
	
	<br>
	
    <forms:form type="edit" name="manageActivityDependancesForm" caption="form.title.manageActivityDependances" formid="frmActivityDependances" width="650">

        <forms:swapselect
            property="realDependancesKeys"
            label="form.field.manageActivityDependances"
            orientation="horizontal"
            labelLeft="form.field.manageActivityDependances.toAdd"
            labelRight="form.field.manageActivityDependances.added"
            valign="top"
            size="10"
            style="width: 250;"
            align="center"
            filter="false"
            required="false"
            disabled="false">
            
            <base:options 
            	property="possibleDependancesOptions"  
            	keyProperty="id" 
            	labelProperty="name"/>
        
		</forms:swapselect>
        
        <forms:buttonsection default="btnNext">
        
            <forms:button
				name="btnPrevious"
				text="form.button.previous"
				title="form.tooltip.manageActivityDependances.previous"
				/>
				
        	<forms:button
				name="btnNext"
				text="form.button.next"
				title="form.tooltip.manageActivityDependances.next"
				/>
				
			<forms:button
				name="btnFinish"
				text="form.button.finish"
				title="form.tooltip.manageActivityDependances.finish"
				/>
 
		</forms:buttonsection>
        
        <html:hidden property="activityId"/>
        
    </forms:form>
    
</html:form>
