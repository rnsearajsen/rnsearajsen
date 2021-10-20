//****Create Product:**** 
//Insert an entry in table on button click
$(document).on("click", "#btnproductinsert", function(){
	that = this;
//get Date/Time
	getdatetime(that);
	//Formal Parameter
 	var params = {product_id: $("#product_id").val(),
		      name: $("#name").val(),
		      group_name: $("#group_name").val(),
		      subgroup_name: $("#subgroup_name").val(),
      uom: $("#uom").val(),
      comments: $("#comments").val().trim(),
      changedby:$("#changedby").val(),
      changeddate:$("#changeddate").val(),
      changedtime:$("#changedtime").val(),
      createddate:$("#createddate").val()};

//postajax
fnpostajax(that,params,"producttable");
	});

//******************************************************************************
//Update Product
$(document).on("click", "#btnproductupdate", function(){
	that = this;
//get Date/Time
	getdatetime(that);
//Formal Parameter
 	var params = {product_id: $("#product_id").val(),
		      name: $("#name").val(),
		      group_name: $("#group_name").val(),
		      subgroup_name: $("#subgroup_name").val(),
    uom: $("#uom").val(),
    comments: $("#comments").val().trim(),
    changedby:$("#changedby").val(),
    changeddate:$("#changeddate").val(),
    changedtime:$("#changedtime").val(),
    createddate:$("#createddate").val()};

//postajax
	fnpostajax(that,params,"producttable");	
	});

//*********************************************************
//Clear 
$(document).on("click", "#btnproductclear", function() {
	// Clear Mandatory Border Color
	fntableclear("producttable");
});
//*********************************************************
//Get Date & TIme
function getdatetime(that) {
	//Date
	var today = fngettoday();   
//Time
	var currentTime = fngetcurrenttime();
//Update Today's date
	document.getElementById("changeddate").value = today;
	document.getElementById("changedtime").value = currentTime;
	if (that.id == "btnproductinsert"){
		document.getElementById("createddate").value = today;
	}
}
//*********************************************************
