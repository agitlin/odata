package org.grails.plugin.odata
import grails.converters.JSON
class SourceController {
    static scaffold = true
	
	def getEntityTypes() {
		def source = Source.get(params.id)
		def l= source.getEntityTypes() 
		render l as JSON
	}
	
	def getProperties() {
		def source = Source.get(params.id)
		def l= source.getEntityProperties(params.entityType)
		render l as JSON
	}

}
