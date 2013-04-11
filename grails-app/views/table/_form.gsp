<%@ page import="org.grails.plugin.odata.Table" %>

			<div class="control-group fieldcontain ${hasErrors(bean: tableInstance, field: 'source', 'error')} ">
				<label for="source" class="control-label"><g:message code="table.source.label" default="Source" /></label>
				<div class="controls">
					<g:select id="source" name="source.id" from="${org.grails.plugin.odata.Source.list()}" optionKey="id" required="" value="${tableInstance?.source?.id}" class="many-to-one"/>
					<span class="help-inline">${hasErrors(bean: tableInstance, field: 'source', 'error')}</span>
				</div>
			</div>


			<div class="control-group fieldcontain ${hasErrors(bean: tableInstance, field: 'entityType', 'error')} ">
				<label for="entityType" class="control-label"><g:message code="table.entityType.label" default="Entity Type" /></label>
				<div class="controls" >
					<span id="entityTypes" />
					<span class="help-inline">${hasErrors(bean: tableInstance, field: 'entityType', 'error')}</span>
				</div>
			</div>


			<div class="control-group fieldcontain ${hasErrors(bean: tableInstance, field: 'propertyName', 'error')} ">
				<label for="propertyName" class="control-label"><g:message code="table.propertyName.label" default="Property Name" /></label>
				<div class="controls" >
					<span id="propertyNames" />
					<span class="help-inline">${hasErrors(bean: tableInstance, field: 'propertyName', 'error')}</span>
				</div>
			</div>


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


