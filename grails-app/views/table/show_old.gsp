<!-- No longer in use -->
<%@ page import="org.grails.plugin.odata.Table" %>
<!doctype html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'table.label', default: 'Table')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>

<section id="show-table" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="table.columns.label" default="Columns" /></td>
				
				<td valign="top" style="text-align: left;" class="value">
					<ul>
					<g:each in="${tableInstance.columns}" var="c">
						<li><g:link controller="column" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
					</g:each>
					</ul>
				</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="table.name.label" default="Name" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: tableInstance, field: "name")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="table.uri.label" default="Uri" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: tableInstance, field: "uri")}</td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
