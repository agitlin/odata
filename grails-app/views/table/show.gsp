
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="kickstart">
		<title>Preview</title>

		<r:require module="datatables"/>
		<g:javascript>
			var asInitVals = new Array();
			
			$(document).ready(function() {
				var oTable = $('#items').dataTable({
					"sDom": "<'row'<'span6'l><'span6'f><'span6'i><'span6'p>r>t<'row'<'span6'i><'span6'p>>",
					"sPaginationType": "bootstrap",
					bProcessing: true,
					bServerSide: true,
					sAjaxSource: '${request.contextPath + '/table/getItems?table=' + table}' ,
					"fnServerData": function ( sSource, aoData, fnCallback ) {
						$.ajax( {
							"dataType": 'json', 
							"type": "POST", 
							"url": sSource, 
							"data": aoData, 
							"success": fnCallback
						} );
					},
					aLengthMenu: [[20, 50, 100, 500, -1], [20, 50, 100, 500, "All"]],
					iDisplayLength: 50,
					"aaSorting": [[ 0, "asc" ]],
					"aoColumns": [
						<% headers.each { %>{ "mData": "${it.name}" }, <% } %>
					]
				});
				
				$("thead input").keyup( function () {
					/* Filter on the column (the index) of this element */
					oTable.fnFilter( this.value, $("thead input").index(this) );
				} );
		
				/*
				 * Support functions to provide a little bit of 'user friendliness' to the textboxes in 
				 * the header
				 */
				$("thead input").each( function (i) {
					asInitVals[i] = this.value;
				} );
			
				$("thead input").focus( function () {
					if ( this.className == "search_init" )
					{
						this.className = "";
						this.value = "";
					}
				} );
	
				$("thead input").blur( function (i) {
					if ( this.value == "" )
					{
						this.className = "search_init";
						this.value = asInitVals[$("thead input").index(this)];
					}
				} );
			});
		</g:javascript>
		
		
	</head>
	<body>
		<h1><% boo %></h1>
		<table id="items" class="table table-striped table-bordered">
			<thead>
            	<tr>
                	<% headers.each { %><td><input type="text" name="search_${it.name}" value="Filter by ${it.title}" class="search_init"></td><% } %>
			  	</tr>
            	<tr>
                	<% headers.each { %><th>${it.title}</th><% } %>
			  	</tr>
         	</thead>
         	<tbody>
         	</tbody>
      	</table>
	</body>
</html>