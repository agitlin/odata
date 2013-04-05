<%@ page import="org.grails.plugin.odata.Column" %>



			<div class="control-group fieldcontain ${hasErrors(bean: columnInstance, field: 'index', 'error')} ">
				<label for="index" class="control-label"><g:message code="column.index.label" default="Index" /></label>
				<div class="controls">
					<g:field type="number" name="index" value="${columnInstance.index}" />
					<span class="help-inline">${hasErrors(bean: columnInstance, field: 'index', 'error')}</span>
				</div>
			</div>

			<div class="control-group fieldcontain ${hasErrors(bean: columnInstance, field: 'name', 'error')} ">
				<label for="name" class="control-label"><g:message code="column.name.label" default="Name" /></label>
				<div class="controls">
					<g:textField name="name" value="${columnInstance?.name}" />
					<span class="help-inline">${hasErrors(bean: columnInstance, field: 'name', 'error')}</span>
				</div>
			</div>

			<div class="control-group fieldcontain ${hasErrors(bean: columnInstance, field: 'table', 'error')} ">
				<label for="table" class="control-label"><g:message code="column.table.label" default="Table" /></label>
				<div class="controls">
					<g:select id="table" name="table.id" from="${org.grails.plugin.odata.Table.list()}" optionKey="id" required="" value="${columnInstance?.table?.id}" class="many-to-one"/>
					<span class="help-inline">${hasErrors(bean: columnInstance, field: 'table', 'error')}</span>
				</div>
			</div>

			<div class="control-group fieldcontain ${hasErrors(bean: columnInstance, field: 'title', 'error')} ">
				<label for="title" class="control-label"><g:message code="column.title.label" default="Title" /></label>
				<div class="controls">
					<g:textField name="title" value="${columnInstance?.title}" />
					<span class="help-inline">${hasErrors(bean: columnInstance, field: 'title', 'error')}</span>
				</div>
			</div>

