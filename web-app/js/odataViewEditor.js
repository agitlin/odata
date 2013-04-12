$(document).ready(function() {
	var sourceId;
	var entityURL='/omni360/source/getEntityTypes/';
	var propertyURL='/omni360/source/getProperties/';
	$("#entityTypes").hide()
	$("#columns").hide()
	
	$("#source").change( function () {
		sourceId=$("#source").val()
		$.getJSON(entityURL+sourceId, function(data) {
			var items = [];
		 
			items.push('<option>Select one...</option>');
			$.each(data, function( key,val) {
				items.push('<option value="' + val + '">' + val + '</option>');
			});
		 
			$('#entitySelector').empty();
			$('#entitySelector').append(items.join(''));
			$("#entityTypes").show()
		});
	});
	
	$("#entitySelector").change( function () {
		var eType=$("#entitySelector").val();
		$.getJSON(propertyURL+sourceId+'?entityType='+eType, function(data) {
			var items = [];
				 
			$.each(data, function( key,val) {
				items.push('<li class="ui-state-default" ><input type="hidden" name="prop" value="'+val+'"/>'  + val + '<div class="icon-remove deleter" id='+val+'></div></li>');
			});
			$('#propertyNames').empty();
			$('#propertyNames').prepend("<ul id='sortable' >"+items.join('')+"</ul>");
			$(".deleter").click(function (e) {
				$(this).parent().remove();
			});
			$(function() {
				$( "#sortable" ).sortable({
					revert: true
				});
					
				$( "ul, li" ).disableSelection();
				$("#columns").show()
			});
		});
	});
})