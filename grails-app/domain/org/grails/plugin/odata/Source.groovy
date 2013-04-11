package org.grails.plugin.odata

import static groovyx.net.http.ContentType.XML
import static groovyx.net.http.Method.GET
import groovyx.net.http.HTTPBuilder

/**
 * Open Data Protocol Source
 * A domain class describes the data object and it's mapping to the database
 */
class Source {
	String name
	String uri
	static hasMany = [tables: Table]

	static mapping = {
	}

	static constraints = {
	}

	def makeEntityUri(entityType) {
		return uri + '/' + entityType + 's'
	}

	/**
	 * Returns names of Entity Types available in this source 
	 * @return
	 */
	def getEntityTypes() {
		def http = new HTTPBuilder(uri + "/\$metadata")
		http.request( GET, XML ) {
			// response handler for a success response code:
			response.success = { resp, xml ->
				def entityTypeNodes = xml.'**'.findAll { it.name() == 'EntityType' }
				def entityTypes = []
				entityTypeNodes.each {
					entityTypes << it.@Name.toString()
				}
				return entityTypes
			}

			// handler for any failure status code:
			response.failure = { resp ->
				throw new Exception("Unexpected error: ${resp.statusLine.statusCode} : ${resp.statusLine.reasonPhrase}")
			}
		}
	}

	/**
	 * Returns property definitions for specified Entity Type
	 * 
	 * @return
	 */
	List<EntityProperty> getEntityProperties(entityType) {
		def http = new HTTPBuilder(uri + "/\$metadata")
		http.request( GET, XML ) {
			// response handler for a success response code:
			response.success = { resp, xml ->
				def entityTypeNode = xml.'**'.find { it.name() == 'EntityType' && it.@Name == entityType }
				def props = []
				entityTypeNode.Property.list().each {
					props <<  it.@Name.toString()//new EntityProperty(name: it.@Name, type: it.@Type)
				}
				return props
			}

			// handler for any failure status code:
			response.failure = { resp ->
				throw new Exception("Unexpected error: ${resp.statusLine.statusCode} : ${resp.statusLine.reasonPhrase}")
			}
		}
	}

	/*
	 * Methods of the Domain Class
	 */
	@Override	// Override toString for a nicer / more descriptive UI
	public String toString() {
		name
	}
	
	public class EntityProperty {
		String name
		String type
	}
}
