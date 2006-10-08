<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/cc-base"    prefix="base" %>
<%@ taglib uri="/cc-utility" prefix="util" %>
<%@ taglib uri="/cc-controls" prefix="ctrl" %> 
<html:form action="manageDpe.do">
<ctrl:list 
		id="list"
		property="listActivities" 
		title="admin.manageDpe.listActivities"
		width="650" 
		rows="10" 
		refreshButton="false" 
		createButton="false"
		>
		
		 <ctrl:columncheckbox title="admin.manageDpe.selectionne" property="selectionne" editable="true" select="multiple"/>
		
		<ctrl:columntext 
				title="admin.manageDpe.activities"
				property="name"
				width="300"
				sortable="true" />
				
				
		
                
           
    
</ctrl:list>

<br>

<ctrl:button
    name="btnConfirm"
    title="form.button.finish"
    text="form.button.finish"
    width="100"/>
</html:form>     	


