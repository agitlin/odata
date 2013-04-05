
<%@ page import="org.grails.plugin.odata.Table" %>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'table.label', default: 'Table')}" />
	<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>

<body>
	
<section id="list-table" class="first">

	<table class="table table-bordered">
		<thead>
			<tr>
			
				<g:sortableColumn property="name" title="${message(code: 'table.name.label', default: 'Name')}" />
			
				<g:sortableColumn property="uri" title="${message(code: 'table.uri.label', default: 'Uri')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${tableInstanceList}" status="i" var="tableInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${tableInstance.id}">${fieldValue(bean: tableInstance, field: "name")}</g:link></td>
			
				<td>${fieldValue(bean: tableInstance, field: "uri")}</td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<bs:paginate total="${tableInstanceTotal}" />
	</div>
</section>

</body>

</html>
