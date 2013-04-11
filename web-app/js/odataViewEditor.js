$(document).ready(function() {
	var sourceId=$("#source").val()
	var entityURL='/omni360/source/getEntityTypes/';
	var propertyURL='/omni360/source/getProperties/';
	$.getJSON(entityURL+sourceId, function(data) {
		  var items = [];
		 
		  $.each(data, function( key,val) {
		    items.push('<option value="' + val + '">' + val + '</option>');
		  });
		 
		  $('#entitySelector').append(items.join(''));
		});
	$("#entitySelector").change( function () {
		var eType=$("#entitySelector").val();
		$.getJSON(propertyURL+sourceId+'?entityType='+eType, function(data){
			  var items = [];
				 
			  $.each(data, function( key,val) {
			    items.push('<option value="' + val + '">' + val + '</option>');
			  });
			 
			  $('#propertyNames').prepend("<select name='propertyName' id='propertySelector' class='many-to-one'>"+items.join('')+"</select>");
			
		});
	}
	
	)
})