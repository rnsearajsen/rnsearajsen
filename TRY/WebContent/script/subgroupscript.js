//****Create Group:**** 
//Insert an entry in table on button click
$(document).on("click", "#btnsubgrpinsert", function(){
//Formal Parameters
	var params = {subgrp_id: $("#subgrp_id").val(),
		      fsub_group: $("#fsub_group").val().trim(),
		      group: $("#group").val(),
		      comments: $("#comments").val()};

	//postajax
	that = this;
	fnpostajax(that,params,"subgrptable");
	});

//******************************************************************************
//Update Group
$(document).on("click", "#btnsubgrpupdate", function(){
	//Formal Parameters
	var params = {subgrp_id: $("#subgrp_id").val(),
		      fsub_group: $("#fsub_group").val().trim(),
		      group: $("#group").val(),
		      comments: $("#comments").val()};
	//postajax
	that = this;
	fnpostajax(that,params,"subgrptable");
	});

//*********************************************************
//Clear 
$(document).on("click", "#btnsubgrpclear", function() {
	// Clear Mandatory Border Color
	fntableclear("subgrptable");
});
//***********************************************************