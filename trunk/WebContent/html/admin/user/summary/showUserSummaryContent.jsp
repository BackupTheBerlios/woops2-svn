<%@ taglib uri="/cc-controls" prefix="ctrl" %> 
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-html" prefix="html" %>

<html:form action="showUserSummary.do">	
	
	<table>
	
	<tr>
	<td colspan="3">	
	<forms:form 
		type="display" 
	    name="showUserSummaryForm" 
	    caption="admin.showSummary.title" 
	    formid="frmShowUserSummary" 
	    width="550"
	    >

		<forms:text        
			label="admin.addUser.firstName"         
		 	property="firstName"        
		 	/>
 
       	<forms:text
			label="admin.addUser.lastName"
            property="lastName"          
          	/>
          
     	<forms:text
			label="table.field.listUsers.login"
	        property="login"          
      	/>
      	
  		<forms:html label="table.field.listUsers.role" >
      		<bean:message
      			name="showUserSummaryForm"
				property="roleCode"/>
       </forms:html>
          	
   	</forms:form>
   
   	</td>
	
	</tr>
	
	<tr height="20">
   	<td colspan="3"></td>
   	</tr>
   
	<tr>
	<td valign="top" width="48%">
		<ctrl:list 
			id="bdeList"
			property="bdeList" 
			title="table.title.listBreakDownElements"  
			rows="5"
			width="100%"
			>
			
			<ctrl:columntext
				title="table.field.listBreakDownElements.prefix"
				property="prefix" 
				width="350"
				sortable="true"/>

			<ctrl:columntext
				title="table.field.listBreakDownElements.name"
				property="name" 
				width="350"
				sortable="true"/>
				
			<ctrl:columntext 
				title="table.field.listBreakDownElements.startDate"
				property="startDate"
				width="300"
				sortable="true"/>
				
			<ctrl:columntext 
				title="table.field.listBreakDownElements.endDate"
				property="endDate"
				width="300"
				sortable="true"/>
			<ctrl:columntext 
				title="table.field.listBreakDownElements.kind"
				property="kind"
				width="150"
				sortable="true"/>
			
		</ctrl:list>
		</td>
	</tr>
	</table>
		
</html:form>
 

  
