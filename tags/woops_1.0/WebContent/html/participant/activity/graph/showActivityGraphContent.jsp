<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/cc-forms"    prefix="forms" %>

<forms:message formid="frmError" caption="msg.error" severity="error" width="350"/>
<forms:message formid="frmInfo" caption="msg.info" severity="information" width="350"/>
<br>

<logic:notEmpty name="showActivityGraphForm" property="imageFilePath">
	<a href="${showActivityGraphForm.imageFilePath}" target="_blank">
		<html:img 
			src="${showActivityGraphForm.imageFilePath}"
			width="600"
			/>
	</a>
</logic:notEmpty>

 

  
