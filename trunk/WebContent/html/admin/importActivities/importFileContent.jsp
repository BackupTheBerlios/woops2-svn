<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/cc-base"    prefix="base" %>
<%@ taglib uri="/cc-utility" prefix="util" %>

<html:form action="importFile.do" method="post" enctype="multipart/form-data">

<!-- method="post" enctype="multipart/form-data" -->
	<forms:message formid="frmError" caption="msg.error" severity="error" width="350"/>
	<forms:message formid="frmInfo" caption="msg.info" severity="information" width="350"/>
    <forms:form 
    	formid="importFileForm"
    	type="edit"
    	caption="admin.import.title"
    	formid="frmImportFile"
    	width="600">
   <br>
   <forms:form  type="info"  caption="admin.importFile.info"  formid="frmInfo"  width="600">
    <forms:msg  severity="information"  message="admin.importFile.message"/>
</forms:form>
    <br>
    <forms:file label="admin.import.path"  property="pathFile" size="50" />
    
    
    <forms:buttonsection default="btnImport">  
        	<forms:button
            	name="btnImport"
            	default="true"
                title="admin.button.import"
                text="admin.button.import"
                width="100"/>     
        </forms:buttonsection>
    
	</forms:form>
	
	    
</html:form>
