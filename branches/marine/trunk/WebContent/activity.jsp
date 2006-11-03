<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<html>
<body>

<f:view>
	<h:form id="form">
		<h:outputText value="Woops2 - JSF test" style="h2" />
		<h:dataTable value="#{ActivityViewer.activitiesList}" border="1"
			var="activity">
			<h:column>
				<h:outputText value="#{activity.id}" />
			</h:column>
		</h:dataTable>
	</h:form>
</f:view>
</body>

</html>
