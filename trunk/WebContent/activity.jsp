<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>


<html>
<body>


<f:view>
	<h:form id="form">
		<h2>Woops2 - activities list</h2>
		<h:outputText value="Table :" />
		<h:dataTable value="#{ActivityViewer.activitiesList}" border="1"
			var="activity">
			<h:column>
				<h:outputText value="#{activity.id}" />
			</h:column>
			<h:column>
				<h:outputText value="#{activity.prefix}" />
			</h:column>
			<h:column>
				<h:selectBooleanCheckbox value="#{activity.isPlanned}" disabled="true"/>
			</h:column>
			<h:column>
				<h:selectBooleanCheckbox value="#{activity.isOptional}" disabled="true"/>
			</h:column>
		</h:dataTable>
		<br>
		<h:commandButton value="new activity" action="createactivity"/>
	</h:form>
</f:view>
</body>

</html>
