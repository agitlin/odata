var sourceId;
	

function registerDeleter() {
	$(".deleter").click(function (e) {
		var rm= $(this).parent().remove();
		rm.appendTo("#excluded ul");
		rm.find("div").removeClass("icon-remove");
		rm.find("input").attr("name","ex")
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
	$('#name').hide();
	$('#name input').val("");	
}

function hideAll() {
	$('#entitySelector').empty();
	$("#entityTypes").hide()
	hideColumns()
}


function showAll() {
	$("#entityTypes").show()
	$('#columns').show();
	$('#name').show();
}

var onSourceChange =function () {
		var entityURL=root+'source/getEntityTypes/';

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
	
}

var onTableChange= function () {
	var propertyURL=root+'source/getProperties/';
	hideColumns()

	var eType=$("#entitySelector").val();
	if (eType =="") {
		return
	} 
	$('#name').show();
	$('#name input').val(eType.replace(/([A-Z])/g, ' $1')+'s');	

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
		$('#columns').show();
	});
};

var connectSortableLists = function() {
	$( "ul.props" ).sortable({
		connectWith:"#sortable ul",
	    stop: function(event, ui) {
	    		 toggleExcluded()
				 if (ui.item.parent().parent().attr("id")=="sortable"){
		           ui.item.find("div").addClass("icon-remove");
		           registerDeleter();
		           ui.item.find("input").attr("name","prop")
				 }
				 
				 
		}
	}).disableSelection();
	registerDeleter()
	if (actionName=="edit") {
		$("#columns").show()
	}
	

}

$(document).ready(function() {
	
	if (actionName=="create") {
		hideAll()
	}
	else {
		showAll()
	}
	$("#source").change( onSourceChange);
	
	$("#entitySelector").change(onTableChange);
	$(connectSortableLists);
})