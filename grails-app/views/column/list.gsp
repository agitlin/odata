
<%@ page import="org.grails.plugin.odata.Column" %>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'column.label', default: 'Column')}" />
	<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>

<body>
	
<section id="list-column" class="first">

	<table class="table table-bordered">
		<thead>
			<tr>
			
				<g:sortableColumn property="index" title="${message(code: 'column.index.label', default: 'Index')}" />
			
				<g:sortableColumn property="name" title="${message(code: 'column.name.label', default: 'Name')}" />
			
				<th><g:message code="column.table.label" default="Table" /></th>
			
				<g:sortableColumn property="title" title="${message(code: 'column.title.label', default: 'Title')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${columnInstanceList}" status="i" var="columnInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${columnInstance.id}">${fieldValue(bean: columnInstance, field: "index")}</g:link></td>
			
				<td>${fieldValue(bean: columnInstance, field: "name")}</td>
			
				<td>${fieldValue(bean: columnInstance, field: "table")}</td>
			
				<td>${fieldValue(bean: columnInstance, field: "title")}</td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<bs:paginate total="${columnInstanceTotal}" />
	</div>
</section>

</body>

</html>
