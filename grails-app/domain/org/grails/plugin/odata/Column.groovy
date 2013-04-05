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
	
    static mapping = {
    }
    
	static constraints = {
    }
	
	/*
	 * Methods of the Domain Class
	 */
	@Override	// Override toString for a nicer / more descriptive UI 
	public String toString() {
		return "${name}";
	}
}
