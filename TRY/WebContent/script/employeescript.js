//****Create Employee:**** 
//Insert an entry in table on button click
$(document).on("click", "#btnemployeeinsert", function(){
	//get Date/Time
	getdatetime();
//Formal Parameter
 	var params = {employee_id: $("#employee_id").val(),
		      name: $("#name").val(),
		      group_name: $("#group_name").val(),
		      gender: $("#gender").val(),
		      subgroup_name: $("#subgroup_name").val(),
		      salary: $("#salary").val(),
      address: $("#address").val().trim(),
      contact1: $("#contact1").val(),
      contact2: $("#contact2").val(),
      idtype1: $("#idtype1").val(),
      idnum1: $("#idnum1").val(),
      comments: $("#comments").val(),
      joindate: $("#joindate").val(),
      lastdate: $("#lastdate").val(),
      changedby:$("#changedby").val(),
      changeddate:$("#changeddate").val(),
      changedtime:$("#changedtime").val()};

//postajax
that = this;
fnpostajax(that,params,"employeetable");
	});

//******************************************************************************
//Update Employee
$(document).on("click", "#btnemployeeupdate", function(){
//get Date/Time
	getdatetime();
//Formal Parameter
 	var params = {employee_id: $("#employee_id").val(),
		      name: $("#name").val(),
		      group_name: $("#group_name").val(),
		      gender: $("#gender").val(),
		      subgroup_name: $("#subgroup_name").val(),
		      salary: $("#salary").val(),
    address: $("#address").val().trim(),
    contact1: $("#contact1").val(),
    contact2: $("#contact2").val(),
    idtype1: $("#idtype1").val(),
    idnum1: $("#idnum1").val(),
    comments: $("#comments").val(),
    joindate: $("#joindate").val(),
    lastdate: $("#lastdate").val(),
    changedby:$("#changedby").val(),
    changeddate:$("#changeddate").val(),
    changedtime:$("#changedtime").val()};
//postajax
	that = this;
	fnpostajax(that,params,"employeetable");
	});

//*********************************************************
//Clear 
$(document).on("click", "#btnemployeeclear", function() {
	// Clear Mandatory Border Color
	fntableclear("employeetable");
});
//*********************************************************
//Get Date & TIme
function getdatetime() {
//Date
	var today = fngettoday();   
//Time
	var currentTime = fngetcurrenttime();
//Update Today's date
	document.getElementById("changeddate").value = today;
	document.getElementById("changedtime").value = currentTime;
}
//*********************************************************
