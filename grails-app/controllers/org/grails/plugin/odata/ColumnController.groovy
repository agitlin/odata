package org.grails.plugin.odata

import org.springframework.dao.DataIntegrityViolationException

/**
 * ColumnController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
class ColumnController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [columnInstanceList: Column.list(params), columnInstanceTotal: Column.count()]
    }

    def create() {
        [columnInstance: new Column(params)]
    }

    def save() {
        def columnInstance = new Column(params)
        if (!columnInstance.save(flush: true)) {
            render(view: "create", model: [columnInstance: columnInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'column.label', default: 'Column'), columnInstance.id])
        redirect(action: "show", id: columnInstance.id)
    }

    def show() {
        def columnInstance = Column.get(params.id)
        if (!columnInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'column.label', default: 'Column'), params.id])
            redirect(action: "list")
            return
        }

        [columnInstance: columnInstance]
    }

    def edit() {
        def columnInstance = Column.get(params.id)
        if (!columnInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'column.label', default: 'Column'), params.id])
            redirect(action: "list")
            return
        }

        [columnInstance: columnInstance]
    }

    def update() {
        def columnInstance = Column.get(params.id)
        if (!columnInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'column.label', default: 'Column'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (columnInstance.version > version) {
                columnInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'column.label', default: 'Column')] as Object[],
                          "Another user has updated this Column while you were editing")
                render(view: "edit", model: [columnInstance: columnInstance])
                return
            }
        }

        columnInstance.properties = params

        if (!columnInstance.save(flush: true)) {
            render(view: "edit", model: [columnInstance: columnInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'column.label', default: 'Column'), columnInstance.id])
        redirect(action: "show", id: columnInstance.id)
    }

    def delete() {
        def columnInstance = Column.get(params.id)
        if (!columnInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'column.label', default: 'Column'), params.id])
            redirect(action: "list")
            return
        }

        try {
            columnInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'column.label', default: 'Column'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'column.label', default: 'Column'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
