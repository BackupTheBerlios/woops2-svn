<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<html>
<body>

<f:view>
	<h:form id="form">
		<h2>Woops2 - activity form</h2>
		<h:outputText value="- New activity -" style="h2" />
		<h:panelGrid columns="2">
		<h:outputText value="prefix : "/>
		<h:inputText value="#{ActivityViewer.activity.prefix}"/>
		<h:outputText value="isPlanned : "/>
		<h:selectBooleanCheckbox value="#{ActivityViewer.activity.isPlanned}"/>
		<h:outputText value="isOptional : "/>
		<h:selectBooleanCheckbox value="#{ActivityViewer.activity.isOptional}"/>
		<h:commandButton value="save" action="#{ActivityViewer.saveActivityAction}"/><h:commandButton value="back" action="activity"/>
		<h:messages>
		</h:messages>
		</h:panelGrid>
	</h:form>
</f:view>
</body>

</html>
