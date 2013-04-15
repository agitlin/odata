<%@ page import="org.grails.plugin.odata.Table" %>
			<div class="control-group fieldcontain ${hasErrors(bean: tableInstance, field: 'source', 'error')} ">
				<label for="source" class="control-label"><g:message code="table.source.label" default="Source" /></label>
					
				<div class="controls">
					<g:if test="${actionName=='create' }">
						<g:select id="source" name="source.id" from="${org.grails.plugin.odata.Source.list()}" optionKey="id" required="" value="${tableInstance?.source?.id}" class="many-to-one" noSelection="${['null': 'Select One...']}"/>
						<span class="help-inline">${hasErrors(bean: tableInstance, field: 'source', 'error')}</span>
					</g:if>
					<g:else>
						<label class="value-label control-label "> ${tableInstance?.source.name}</label>
					</g:else>
				</div>
			</div>


			<div id="entityTypes" class="control-group fieldcontain ${hasErrors(bean: tableInstance, field: 'entityType', 'error')} ">
				<label for="entityType" class="control-label"><g:message code="table.entityType.label" default="Table" /></label>
				<div class="controls" >
					<g:if test="${actionName=='create' }">
						<select name='entityType' id='entitySelector' class='many-to-one'>
						</select>
						<span class="help-inline">${hasErrors(bean: tableInstance, field: 'entityType', 'error')}</span>
					</g:if>
					<g:else>
						<label class="value-label control-label "> ${tableInstance?.entityType}</label>
					</g:else>
				</div>
			</div>


			<div id="columns" class="control-group fieldcontain ${hasErrors(bean: tableInstance, field: 'propertyName', 'error')} ">
				<label for="propertyName" class="control-label"><g:message code="table.propertyName.label" default="Columns" /></label>
				<div class="controls" >
					<div id="propertyNames" />
					
					<span class="help-inline">${hasErrors(bean: tableInstance, field: 'propertyName', 'error')}</span>
				</div>
			</div>



