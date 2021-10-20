//****Create Bankacct:**** 
//Insert an entry in table on button click
$(document).on("click", "#btnbankacctinsert", function(){
	that = this;
//get Date/Time
getdatetime(that);
var sg_name = {account : $("#name option:selected").val()};
	var params = {bankacct_id: $("#bankacct_id").val(),
	      name: {account : $("#name option:selected").text()}.account,
	      group_name: $("#group_name").val(),
	      subgroup_name: sg_name.account,
    bank: $("#bank").val(),
    acctype: $("#acctype").val(),
    ifsc: $("#ifsc").val(),
    accountno: $("#accountno").val(),
    comments: $("#comments").val().trim(),
    changedby:$("#changedby").val(),
    changeddate:$("#changeddate").val(),
    changedtime:$("#changedtime").val(),
    createddate:$("#createddate").val(),
    };
//postajax
	fnpostajax(that,params,"bankaccttable");
	});

//******************************************************************************
//Update Bankacct
$(document).on("click", "#btnbankacctupdate", function(){
	that = this;
//get Date/Time
	getdatetime(that);
	var params = {bankacct_id: $("#bankacct_id").val(),
		      name: $("#name").val(),
		      group_name: $("#group_name").val(),
		      subgroup_name: $("#subgroup_name").val(),
	    bank: $("#bank").val(),
	    acctype: $("#acctype").val(),
	    ifsc: $("#ifsc").val(),
	    accountno: $("#accountno").val(),
	    comments: $("#comments").val().trim(),
	    changedby:$("#changedby").val(),
	    changeddate:$("#changeddate").val(),
	    changedtime:$("#changedtime").val(),
	    createddate:$("#createddate").val(),
	    };
//postajax
	fnpostajax(that,params,"bankaccttable");	
	});

//*********************************************************
//Clear 
$(document).on("click", "#btnbankacctclear", function() {
	// Clear Mandatory Border Color
	fntableclear("bankaccttable");
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
	if (that.id="btnbankacctinsert"){
		document.getElementById("createddate").value = today;
	}
}
//*********************************************************
//Successive Drop-down value update based on Predecessor Drop-down value change
$(document).on("change", "#group_name", function(){
	var me = $(this);
	var params = {group_name: $("#group_name").val()};
	if ( me.data('requestRunning') == params.group_name) {
	    return;
	}
mydropdown(params,me);
		});	

function mydropdown(params,me){
//Set repeat process indicator false
	me.data('requestRunning', params.group_name)
	//Call Servlet    
	$.get("bankacct_dropdown",  ($.param(params)), function(responseJson) {   
		var $select = $("#name");
		$select.find("option").remove();
		 $.each(responseJson, function(index, account) {
             $("<option>").val(account.subgroup_name).text(account.name).appendTo($select);
         });                   	
	});           
	
}
//***********************************************************