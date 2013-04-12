$(document).ready(function() {
	var sourceId=$("#source").val()
	var entityURL='/omni360/source/getEntityTypes/';
	var propertyURL='/omni360/source/getProperties/';
	$("#columns").hide()
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
			    items.push('<li class="ui-state-default" ><input type="hidden" name="prop" value="'+val+'"/>'  + val + '<div class="icon-remove deleter" id='+val+'></div></li>');
			  });
			  $('#propertyNames').prepend("<ul id='sortable' >"+items.join('')+"</ul>");
			  $(".deleter").click(function (e) {
				  alert (" Deleting "+ $(this).attr("id") + " parent "+ $(this).parent().remove());
			  });
			  $(function() {
					$( "#sortable" ).sortable({
						revert: true
					});
					
					$( "ul, li" ).disableSelection();
					$("#columns").show()
				});
		});
		
	}
	
	)
})