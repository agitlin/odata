
<%@ page import="org.grails.plugin.odata.Column" %>
<!doctype html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'column.label', default: 'Column')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>

<section id="show-column" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="column.index.label" default="Index" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: columnInstance, field: "index")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="column.name.label" default="Name" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: columnInstance, field: "name")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="column.table.label" default="Table" /></td>
				
				<td valign="top" class="value"><g:link controller="table" action="show_old" id="${columnInstance?.table?.id}">${columnInstance?.table?.encodeAsHTML()}</g:link></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="column.title.label" default="Title" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: columnInstance, field: "title")}</td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
