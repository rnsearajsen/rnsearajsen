//****Create Owner:**** 
//Insert an entry in table on button click
$(document).on("click", "#btnownerinsert", function(){
//Formal Parameters
 	var params = {owner_id: $("#owner_id").val(),
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
      idnum2: $("#idnum2").val()};

//postajax
	that = this;
	fnpostajax(that,params,"ownertable");
	});

//******************************************************************************
//Update Owner
$(document).on("click", "#btnownerupdate", function(){
	//Formal Parameters
 	var params = {owner_id: $("#owner_id").val(),
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
      idnum2: $("#idnum2").val()};
//postajax
	that = this;
	fnpostajax(that,params,"ownertable");
	});

//*********************************************************
//Clear 
$(document).on("click", "#btnownerclear", function() {
	// Clear Mandatory Border Color
	fntableclear("ownertable");
});
//*********************************************************
