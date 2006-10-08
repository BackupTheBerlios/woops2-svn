<%@ taglib uri="/cc-controls" prefix="ctrl" %> 
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/cc-template" prefix="template" %>
<%@ taglib uri="/cc-utility" prefix="util" %>

<html>

<head>
<title>WOOPS</title>
<meta http-equiv="Content-Type" content="text/html;">
<util:base/>


<style>
		
		
		body  {
			font-family:verdana, sans, serif; 
			color:#024B67;
			font-size: 12px; 
			margin: 0px 0px 0px 0px;
			text-decoration: none;
		}
		
		TABLE{
			color:#024B67;

			font-family:verdana, sans, serif; 
			font-size: 12px; 
			margin: 0px 0px 0px 0px;
			text-decoration: none;
		}
		
		TABLE.tab{	
			border: 1px black solid ;

		}
		
		TR{
		}
		
		TR.header{
			background: #CAC0D6;
		}
		    
		
		A {
			text-decoration: none;
		    color:#024B67;
			}
		A:hover {
			color:#685973;
		}	

			
</style>





<script language="javascript">
<!--
function print_js(){
if (navigator.appName == "Netscape") {
window.print() ;
}
else {
var n = '<OBJECT ID="navi1" WIDTH=0 HEIGHT=0 CLAS"CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></OBJECT>';
document.body.insertAdjacentHTML('beforeEnd', n);
navi1.ExecWB(6, 2);
}
}
// -->
</script>


</head>




	<body>	
	
	
	
<a class=i href="javascript:print_js();">
<img border="0" src="images/printer.gif">
</a>




<center>
<table>
<tr><td>

<table class="tab" >

<tr class="header">
<td width="300" colspan="2">
<bean:message key="form.field.activity.name" />
</td>

</tr>


<tr >
<td width="300">
<bean:message key="form.field.activity.name" />
</td>
<td>
<bean:write name="showActivitySummaryForm" property="name"/>
</td>
</tr>

<tr>
<td>
<bean:message key="form.field.activity.details" />
</td>
<td>
<bean:write name="showActivitySummaryForm" property="details"/>
</td>
</tr>

<tr>
<td>
<bean:message key="form.field.activity.state" />
</td>
<td>

    		<bean:message 
    			name="showActivitySummaryForm"
    			property="state"
    			/>
   
</td>
</tr>

<tr>
<td>
<bean:message key="form.field.activity.startDate" />
</td>
<td>

<bean:write name="showActivitySummaryForm" property="startDate"/>

</td>
</tr>

<tr>
<td>
<bean:message key="form.field.activity.endDate" />
</td>
<td>
<bean:write name="showActivitySummaryForm" property="endDate"/>
</td>
</tr>

<tr>
<td>
<bean:message key="form.field.activity.onGoing" />
</td>
<td>
<bean:write name="showActivitySummaryForm" property="onGoing"/>
</td>
</tr>

</table>

	<br>
	
<table >

<tr>
<td width="200" valign="top">

	<table  class="tab">
	
	<tr class="header">
	
	<td>
		<bean:message key="form.table.predecessors.field.predecessor" />
	</td>
	
	<td>
	<bean:message key="form.field.activity.state" />
	</td>
	
	</tr>
	
	
	<logic:iterate id="item" name="showActivitySummaryForm" property="collecPredecessorList">
	<tr>
	<td>
		<bean:write name="item" property="predecessor" filter="true"/>
	</td>
	<td>
		<bean:message name="item" property="predecessorState"/>
	</td>
	</tr>
	</logic:iterate>
	
	</table>
	
</td>

<td width="200" valign="top">

	<table  class="tab">
	
	<tr class="header">
	
	<td>
	<bean:message key="form.table.successors.field.successor" />
	</td>
	
	<td>
	<bean:message key="form.field.activity.state" />
	</td>

	
	</tr>
	
	
	<logic:iterate id="item" name="showActivitySummaryForm" property="collecSuccessorList">
		<tr>
		<td>
			<bean:write name="item" property="successor" filter="true"/>
		</td>
		<td>
			<bean:message name="item" property="successorState"/>
		</td>
		</tr>
	</logic:iterate>
	
	

	</table>

</td>
</tr>

</table>	
</td></tr>
</table>
</center>		



</body>
</html>
 

  
