//****Create Company:**** 
//Insert an entry in table on button click
$(document).on("click", "#btncompinsert", function(){
//Formal Parameters
 	var params = {company_id: $("#company_id").val(),
		      name: $("#name").val(),
		      suffix: $("#suffix").val(),
      address: $("#address").val(),
      mail: $("#mail").val(),
      contact1: $("#contact1").val(),
      contact2: $("#contact2").val()};
 	
//postajax
	that = this;
	fnpostajax(that,params,"comptable");
	});

//******************************************************************************
//Update Company
$(document).on("click", "#btncompupdate", function(){
	//Formal Parameters
 	var params = {company_id: $("#company_id").val(),
		      name: $("#name").val(),
		      suffix: $("#suffix").val(),
      address: $("#address").val(),
      mail: $("#mail").val(),
      contact1: $("#contact1").val(),
      contact2: $("#contact2").val()};

//postajax
	that = this;
	fnpostajax(that,params,"comptable");
	});

//*********************************************************
//Clear 
$(document).on("click", "#btncompclear", function() {
	// Clear Mandatory Border Color
	fntableclear("comptable");
});
//*********************************************************
//Display(List) Company Not used - but it will work
$(document).on("click", "#btncompdisplay", function(){
	//var tabcontent = $("#comptableview");
	//var tabcontent = $("#divcomptableview");
	//tabcontent = [];
	//Call Servlet    
	$.get("complistajax", function(responseJson) {
		$("#comptableview").find("tr:gt(0)").remove();
		$.each(responseJson, function(key, value) {
            var row = $("<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
            row.eq(0)[0].id = "tableview";
            //row.eq(0)[0].parentElement = "tbody";
            //row.eq(0)[0].parentNode = "tbody";
            row.children().eq(0).text(value['name']);
            row.children().eq(1).text(value['suffix']);
            row.children().eq(2).text(value['address']);
            row.children().eq(3).text(value['mail']);
            row.children().eq(4).text(value['contact1']);
            row.children().eq(5).text(value['contact2']);
            row.appendTo(tabcontent);            
        });		
	});
	//Show Content
	$("#divcompdisplay").show();
	 $("#divcompdisplay1").show();	
	}); 
//******************************************************************************************************
//Delete Company : Not used - but it will work
$(document).on("click", "#btncompdelete", function(){
	//Call Servlet    
// "this.value" holds the url along with value to be deleted
// it is maintained in button value	
	$.post(this.value, function(responseText) {   
	   $("#message").text(responseText);
	});           
	$.get("complistajax", function(responseJson) {
		var $tr = $("#listajax");
		$.each(responseJson, function(index, company) {
            $("<td>").val(company.id).text(company.name).text(company.suffix).text(company.address).text(company.mail).text(company.contact1).text(company.contact2).appendTo($tr);
        });		
	});
	}); 
//*************************************************************************
