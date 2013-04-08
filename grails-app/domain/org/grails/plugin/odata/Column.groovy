package org.grails.plugin.odata

/**
 * PropertyDescriptor
 * A domain class describes the data object and it's mapping to the database
 */
class Column {
	int index
	String name
	String title
	
	static belongsTo = [table:Table]
	
	/*
	 * Methods of the Domain Class
	 */
	@Override
	String toString() {
		name
	}
}
