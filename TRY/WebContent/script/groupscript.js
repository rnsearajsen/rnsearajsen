//****Create Group:**** 
//Insert an entry in table on button click
$(document).on("click", "#btngrpinsert", function(){
//Formal Parameters
 	var params = {group_id: $("#group_id").val(),
		      name: $("#name").val().trim(),
		      comments: $("#comments").val()};
//postajax
	that = this;
	fnpostajax(that,params,"grptable");
	});

//******************************************************************************
//Update Group
$(document).on("click", "#btngrpupdate", function(){
	//Formal Parameters
 	var params = {group_id: $("#group_id").val(),
		      name: $("#name").val().trim(),
		      comments: $("#comments").val()};
//postajax
	that = this;
	fnpostajax(that,params,"grptable");
});

//*********************************************************
//Clear 
$(document).on("click", "#btngrpclear", function() {
	// Clear Mandatory Border Color
	fntableclear("grptable");
});
//**********************************************************
