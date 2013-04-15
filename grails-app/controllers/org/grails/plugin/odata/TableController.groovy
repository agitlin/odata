package org.grails.plugin.odata
import grails.converters.JSON
import static groovyx.net.http.Method.GET
import groovyx.net.http.HTTPBuilder
import org.springframework.dao.DataIntegrityViolationException

/**
 * TableController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
class TableController {
	def datatablesService
	
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def show() {
		def table = params.id? Table.get(params.id): Table.first()
		[headers:table.getColumnsSorted(),table:params.id]
	}

	def getItems() {
		render datatablesService.getDataForTable(params.table, params) as JSON
	}
	
	def index() {
		redirect(action: "list", params: params)
	}

	def list() {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[tableInstanceList: Table.list(params), tableInstanceTotal: Table.count()]
	}

	def create() {
		[tableInstance: new Table(params)]
	}

	static humanize(s) {
		s.replaceAll(/([A-Z][a-z]*)/, '$1 ').trim()
	}
	
	def save() {
		def tableInstance = new Table(params)
		tableInstance.name=tableInstance.name?:humanize(tableInstance.entityType)

		if (!tableInstance.save(flush: true)) {
			render(view: "create", model: [tableInstance: tableInstance])
			return
		}
		params.prop.eachWithIndex {p, i ->
			Column col = new Column(index: i, name: p, title: humanize(p))
			col.save(flush:true)
			tableInstance.addToColumns(col)
		}
		if (!tableInstance.save(flush: true)) {
			render(view: "create", model: [tableInstance: tableInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [
			message(code: 'table.label', default: 'Table'),
			tableInstance.id
		])
		redirect(action: "show", id: tableInstance.id)
	}

	def show_old() {
		def tableInstance = Table.get(params.id)
		if (!tableInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'table.label', default: 'Table'),
				params.id
			])
			redirect(action: "list")
			return
		}

		[tableInstance: tableInstance]
	}

	def edit() {
		def tableInstance = Table.get(params.id)
		if (!tableInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'table.label', default: 'Table'),
				params.id
			])
			redirect(action: "list")
			return
		}

		[tableInstance: tableInstance]
	}

	def update() {
		def tableInstance = Table.get(params.id)
		if (!tableInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'table.label', default: 'Table'),
				params.id
			])
			redirect(action: "list")
			return
		}

		if (params.version) {
			def version = params.version.toLong()
			if (tableInstance.version > version) {
				tableInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[
							message(code: 'table.label', default: 'Table')] as Object[],
						"Another user has updated this Table while you were editing")
				render(view: "edit", model: [tableInstance: tableInstance])
				return
			}
		}

		tableInstance.properties = params

		if (!tableInstance.save(flush: true)) {
			render(view: "edit", model: [tableInstance: tableInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [
			message(code: 'table.label', default: 'Table'),
			tableInstance.id
		])
		redirect(action: "show", id: tableInstance.id)
	}

	def delete() {
		def tableInstance = Table.get(params.id)
		if (!tableInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'table.label', default: 'Table'),
				params.id
			])
			redirect(action: "list")
			return
		}

		try {
			tableInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [
				message(code: 'table.label', default: 'Table'),
				params.id
			])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [
				message(code: 'table.label', default: 'Table'),
				params.id
			])
			redirect(action: "show", id: params.id)
		}
	}
}
