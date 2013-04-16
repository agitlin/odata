function registerDeleter() {
	$(".deleter").click(function (e) {
		var rm= $(this).parent().remove();
		rm.appendTo("#excluded ul");
		rm.find("div").removeClass("icon-remove");
		toggleExcluded()
	});
}
function toggleExcluded() {
	 if ($("#excluded ul").children().length>0) {
		 $("#excluded").show()
	 }
	 else {
		 $("#excluded").hide()
	 }
}
function hideColumns() {
	$('.props').empty();
	$('#columns').hide();
}

function hideAll() {
	$('#entitySelector').empty();
	$("#entityTypes").hide()
	hideColumns()
}

$(document).ready(function() {
	
	var sourceId;
	var entityURL=root+'source/getEntityTypes/';
	var propertyURL=root+'source/getProperties/';
	if (actionName=="create") {
		hideAll()
	}
		$("#source").change( function () {
			hideAll()
			sourceId=$("#source").val()
			if (sourceId =="null") {
				return
			} 
			$.getJSON(entityURL+sourceId, function(data) {
				var items = [];
		 
				items.push('<option value="">Select one...</option>');
				$.each(data, function( key,val) {
					items.push('<option value="' + val + '">' + val + '</option>');
				});
		 
				
				$('#entitySelector').append(items.join(''));
				$("#entityTypes").show()
			});
		});
	
	$("#entitySelector").change( function () {
		hideColumns()

		var eType=$("#entitySelector").val();
		if (eType =="") {
			return
		} 
		$.getJSON(propertyURL+sourceId+'?entityType='+eType, function(data) {
			var items = [];
			var excludedItems=[];
				 
			$.each(data, function( key,val) {
				items.push('<li class="ui-state-default" ><input type="hidden" name="prop" value="'+val+'"/>'  + val + '<div class="icon-remove deleter" id='+val+'></div></li>');
				
			});
			$('#sortable ul').prepend(items.join(''));
			$('#excluded ul').prepend(excludedItems.join(''));
			$("#excluded").hide()
			registerDeleter();
			$(function() {
				$( "ul.props" ).sortable({
					connectWith:"#sortable ul",
				    stop: function(event, ui) {
				    		 toggleExcluded()
							 if (ui.item.parent().parent().attr("id")=="sortable"){
					           ui.item.find("div").addClass("icon-remove");
					           registerDeleter();
							 }
							 
							 
					}
				}).disableSelection();
				
				$("#columns").show()
			});
		});
	});
})