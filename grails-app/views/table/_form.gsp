<%@ page import="org.grails.plugin.odata.Table" %>



			<div class="control-group fieldcontain ${hasErrors(bean: tableInstance, field: 'columns', 'error')} ">
				<label for="columns" class="control-label"><g:message code="table.columns.label" default="Columns" /></label>
				<div class="controls">
					
<ul class="one-to-many">
<g:each in="${tableInstance?.columns?}" var="c">
    <li><g:link controller="column" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="column" action="create" params="['table.id': tableInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'column.label', default: 'Column')])}</g:link>
</li>
</ul>

					<span class="help-inline">${hasErrors(bean: tableInstance, field: 'columns', 'error')}</span>
				</div>
			</div>

			<div class="control-group fieldcontain ${hasErrors(bean: tableInstance, field: 'name', 'error')} ">
				<label for="name" class="control-label"><g:message code="table.name.label" default="Name" /></label>
				<div class="controls">
					<g:textField name="name" value="${tableInstance?.name}" />
					<span class="help-inline">${hasErrors(bean: tableInstance, field: 'name', 'error')}</span>
				</div>
			</div>

			<div class="control-group fieldcontain ${hasErrors(bean: tableInstance, field: 'uri', 'error')} ">
				<label for="uri" class="control-label"><g:message code="table.uri.label" default="Uri" /></label>
				<div class="controls">
					<g:textField name="uri" value="${tableInstance?.uri}" />
					<span class="help-inline">${hasErrors(bean: tableInstance, field: 'uri', 'error')}</span>
				</div>
			</div>

