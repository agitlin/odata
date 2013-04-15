package org.grails.plugin.odata

import net.sf.json.JSONNull

/**
 * DatatablesSvcService
 * A service class encapsulates the core business logic of a Grails application
 */
class DatatablesService {
    static transactional = false

	def getDataForTable(tableId, parameters) {
		def table = Table.get(tableId)
		def columns = table.getColumnsSorted()

		def dataForDT = [:]
		dataForDT.sEcho = parameters.sEcho

		def sortProperty = columns[parameters.iSortCol_0 as Integer].name
		def sortDir = parameters.sSortDir_0?.equalsIgnoreCase('asc') ? 'asc' : 'desc'

		def data = table.getData(parameters.iDisplayLength, parameters.iDisplayStart, "${sortProperty} ${sortDir}", getFilterQuery(columns, parameters))

		dataForDT.iTotalRecords = data.__count
		dataForDT.iTotalDisplayRecords = dataForDT.iTotalRecords

		dataForDT.aaData=replaceNulls(data.results)
		
		return dataForDT
	}
	
	private getFilterQuery(columns, parameters) {
		StringBuilder filter = new StringBuilder()
		columns.eachWithIndex { descr, i ->
			def value = parameters.get("sSearch_"+i);
			if (value) {
				if (filter.length() > 0)
					filter << " and "
				filter << "substringof('" << value << "', " << descr.name << ")"
			}
		}
		return filter.toString()
	}

	/*
	 * This is a workaround for an apparent bug in JSON rendering engine. It blows up when
	 * document contains null values
	 */
	private replaceNulls(jsonArray) {
		jsonArray.each { map ->
			def nullKeys = []
			map.each() { k, v ->
				if (v instanceof JSONNull) {
					nullKeys << k
				}
			}
			nullKeys.each {
				map[it] = ""
			}
		}
		return jsonArray
	}
}
