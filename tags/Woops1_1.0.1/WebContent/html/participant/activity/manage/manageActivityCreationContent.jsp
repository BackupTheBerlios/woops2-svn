<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/cc-base"     prefix="base" %>
<%@ taglib uri="/cc-utility" prefix="util" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/cc-controls" prefix="ctrl" %>
<%@ page import="view.PresentationConstantes" %>


<script language="Javascript">

function ManageControl(tabControleur, tabControle, Masquer) {



 for(var i = 0; i < tabControle.length; i++){
  Controle =  tabControle[i];
  
      for(var j = 0; j < tabControleur.length; j++){
		Controleur = tabControleur[j];
		var objControleur = document.getElementById(Controleur);
		
   
	var objControle = document.getElementById(Controle);
		if (Masquer=='1')
			objControle.style.visibility=(objControleur.checked==true)?'visible':'hidden';
		else
			objControle.disabled=(objControleur.checked==true)?false:true;
		}
}

return true;
}



</script>

<!-- solution sale (deuxieme body) pour resoudre un probleme (bug CC ? ): disable la description d'un evenement par defaut --> 
<body onload="document.getElementById('ta1').disabled=true;">

<html:form action="manageActivityCreation.do">

	<forms:message formid="frmError" caption="msg.error" severity="error" width="350"/>
	<forms:message formid="frmWarning" caption="msg.warning" severity="warning" width="350"/>
	<br>
	
    <forms:form 
    type="edit" 
    name="manageActivityCreationForm" 
    caption="${manageActivityCreationForm.caption}" 
    formid="frmActivityCreation" 
    width="650">
    
    
		 <forms:text        
		 label="form.field.activity.name"         
		 property="name"     
		 maxlength="50" 
		 required="true"  
		 />
 
         <forms:textarea
            label="form.field.activity.details"
            property="details"
            cols="64"
            rows="3"          
          />
            

          <forms:checkbox  
            label="form.field.activity.checkbox.activityOnGoing"
            property="activityOnGoing"
            tooltip="table.tooltip.activity.checkbox.activityOnGoing"
            disabled="${manageActivityCreationForm.disableActivityOnGoingCheckbox}"
          />




         <forms:checkbox  
         	styleId="ch2"
            label="form.field.activity.checkbox.freeActivity"
            property="freeActivity"
            tooltip="table.tooltip.activity.checkbox.freeActivity"
            disabled="${manageActivityCreationForm.disableFreeActivityCheckbox}"
            onclick="ManageControl(new Array('ch2'), new Array('ch1'), '0'); document.getElementById('ch1').checked=false;document.getElementById('inp1').disabled=true;document.getElementById('ta1').disabled=true;"
    	/>



<logic:equal name="manageActivityCreationForm" property="mode" value="<%=PresentationConstantes.INSERT_MODE%>">

         <forms:checkbox  
         	styleId="ch1"
            label="form.field.activity.checkbox.event"
            property="event"
            tooltip="table.tooltip.activity.checkbox.event"
            onclick="ManageControl(new Array('ch1'), new Array('inp1','inp2'), '0');"
            
            disabled="${manageActivityCreationForm.disableEventCheckbox}"
            onclick="ManageControl(new Array('ch1'), new Array('inp1','ta1'), '0');"
            disabled="true"
          />
          
          
		 <forms:text  
		 styleId="inp1"      
		 label="form.field.activity.checkbox.eventName"         
		 property="eventName"     
		 maxlength="50"  
		 disabled="true"
		 />
		 
		 

		 <forms:textarea  
		 styleId="ta1"     
		 label="form.field.activity.checkbox.eventDetails"         
		 property="eventDetails"     
		 cols="64"
         rows="3"  
         readonly="false"
		 />
		 

		 
</logic:equal>
        
        
        <forms:buttonsection default="btnNext">
        
            <forms:button
				name="btnPrevious"
				text="form.button.previous"
				title="form.tooltip.manageActivityCreation.previous"
				/>
				
        	<forms:button
				name="btnNext"
				text="form.button.next"
				disabled="${manageActivityCreationForm.disableNext}"
				title="${manageActivityCreationForm.tooltipNext}"
				/>
				
			<forms:button
				name="btnFinish"
				text="form.button.finish"
				title="${manageActivityCreationForm.tooltipFinish}"
				/>
 
		</forms:buttonsection>
        
    </forms:form>
    
    
    <html:hidden property="activityId"/>
    <html:hidden property="mode"/>
    <html:hidden property="disableEventCheckbox"/>
    <html:hidden property="disableActivityOnGoingCheckbox"/>
    <html:hidden property="disableFreeActivityCheckbox"/>
    
</html:form>


</body>