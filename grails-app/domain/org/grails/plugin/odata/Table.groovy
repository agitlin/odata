package org.grails.plugin.odata

import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.Method.GET
import groovyx.net.http.HTTPBuilder

/**
 * EntityDescriptor
 * A domain class describes the data object and it's mapping to the database
 */
class Table {
	String name
	String uri

	static hasMany = [columns:Column]

	/*
	 * Methods of the Domain Class
	 */
	@Override	// Override toString for a nicer / more descriptive UI
	String toString() {
		name
	}

	def getColumnsSorted() {
		return columns.sort{it.index}
	}

	static void addSamples() {
		addNew("Movies", "http://odata.netflix.com/Catalog/Titles", [
			[name: "Name"],
			[name:"Synopsis"],
			[name:"AverageRating", label:"Average Rating"]
		])
		addNew("Customers", "http://services.odata.org/Northwind/Northwind.svc/Customers", [
			[name:"ContactName", label:"Contact Name"],
			[name:"Address"],
			[name:"City"],
			[name:"Phone"],
			[name:"CompanyName", label:"Company Name"]
		])
	}
	
	static void addNew(tableName, sourceUri, columns) {
		Table table = new Table(name: tableName, uri: sourceUri)
		columns.eachWithIndex { map, i ->
			Column col = new Column(index: i, name: map.name, title: map.label?map.label:map.name)
			col.save()
			table.addToColumns(col)
		}
		table.save()
	}
	
	def getData(max, offset, orderBy, filterQuery) {
		def http = new HTTPBuilder(this.uri)
		http.request( GET, JSON ) {
			// construct comma separated list of column names for select
			def propsSorted = getColumnsSorted()
			StringBuilder select = new StringBuilder()
			propsSorted.each {
				select << it.name
				if (it != propsSorted.last()) {
					select << ','
				}
			}
			uri.query = [
				$top:max, 
				$skip:offset, 
				$select:select.toString(), 
				$inlinecount: 'allpages', 
				$format:'json', 
				$orderby:orderBy,
				$filter:filterQuery
			]
			
			// response handler for a success response code:
			response.success = { resp, json ->
				println resp.statusLine

				// parse the JSON response object:
				return json.d
			}

			// handler for any failure status code:
			response.failure = { resp ->
				throw new Exception("Unexpected error: ${resp.statusLine.statusCode} : ${resp.statusLine.reasonPhrase}")
			}
		}
	}
}
