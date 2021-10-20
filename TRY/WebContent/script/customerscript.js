//****Create Customer:**** 
//Insert an entry in table on button click
$(document).on("click", "#btncustomerinsert", function(e){
	that = this;
//get Date/Time
	getdatetime(that);
//Formal Parameter
 	var params = {customer_id: $("#customer_id").val(),
		      name: $("#name").val(),
		      group_name: $("#group_name").val(),
		      subgroup_name: $("#subgroup_name").val(),
      address: $("#address").val().trim(),
      mail: $("#mail").val(),
      contact1: $("#contact1").val(),
      contact2: $("#contact2").val(),
      idtype1: $("#idtype1").val(),
      idnum1: $("#idnum1").val(),
      idtype2: $("#idtype2").val(),
      idnum2: $("#idnum2").val(),
      comments: $("#comments").val(),
      joindate: $("#joindate").val(),
      lastdate: $("#lastdate").val(),
      changedby:$("#changedby").val(),
      changeddate:$("#changeddate").val(),
      changedtime:$("#changedtime").val(),
      createddate:$("#createddate").val(),
      createdtime:$("#createdtime").val()};

//postajax
fnpostajax(that,params,"customertable");
	});

//******************************************************************************
//Update Customer
$(document).on("click", "#btncustomerupdate", function(){
	that = this;
//get Date/Time
	getdatetime(that);
//Formal Parameter
 	var params = {customer_id: $("#customer_id").val(),
		      name: $("#name").val(),
		      group_name: $("#group_name").val(),
		      subgroup_name: $("#subgroup_name").val(),
      address: $("#address").val().trim(),
      mail: $("#mail").val(),
      contact1: $("#contact1").val(),
      contact2: $("#contact2").val(),
      idtype1: $("#idtype1").val(),
      idnum1: $("#idnum1").val(),
      idtype2: $("#idtype2").val(),
      idnum2: $("#idnum2").val(),
      comments: $("#comments").val(),
      joindate: $("#joindate").val(),
      lastdate: $("#lastdate").val(),
      changedby:$("#changedby").val(),
      changeddate:$("#changeddate").val(),
      changedtime:$("#changedtime").val(),
      createddate:$("#createddate").val(),
      createdtime:$("#createdtime").val()};

//postajax
	fnpostajax(that,params,"customertable");	
	});

//*********************************************************
//Clear 
$(document).on("click", "#btncustomerclear", function() {
	// Clear Mandatory Border Color
	fntableclear("customertable");
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
	if (that.id == "btncustomerinsert"){
		document.getElementById("createddate").value = today;
		document.getElementById("createdtime").value = currentTime;
	}
}
//*********************************************************