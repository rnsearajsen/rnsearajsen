//***********************************************************************
//Call servlet to get Collection Details
function fcollect(params){
	var accessval = document.getElementById("useraccess");
	var uname = document.getElementById("userfirstname")
	var tabcontent = $("#collecttable"), hide_flag = false, 
	save_disable = false;
//	Enable 'ADD NEW ROW' Button
	tabcontent[0].children[0].children[0].children[0].children[0].disabled = false;	
//	Declared 'collect' as global	
	collects = new Array();
	var tbodys = $("<tbody>"), tbodye = $("</tbody>");    
	tbodys.appendTo(tabcontent);
//	Call Servlet to get Engine Oil Sales Data
	$.get("collect_ajax", ($.param(params)), function(responseJson) {  
		$("#collecttable").find("tr:gt(1)").remove();
		$.each(responseJson, function(key, value) {
			var row = $("<tr><td></td><td type='number'><input/></td><td type='number'><input/></td><td></td><td></td></tr>");
			row.eq(0)[0].id = "tableview";
//			Update Amount in Hand
			tabcontent[0].children[0].children[0].children[3].children[0].value = (value['amtinhand']);				
			tabcontent[0].children[0].children[0].children[3].children[0].textContent = (value['amtinhand']).toLocaleString('en-US', {minimumFractionDigits: 2});
//			If Collection Amt value equals 'exists', then it means 
//			Collection on entered date is already been stored in 'collect' table. So Disable 'Save' option
			if (value['collect_amt'] != null){				
				save_disable = true;}
			else{
//				For New Entry collect all Collection Type				
				var collect = {};
				collect.txndate = document.getElementById("txndate").value;
				collect.labour_shift = document.getElementById("shift").value;
				collect.collect_type = value['collect_type'];					
				collect.collect_amt = value['collect_amt'];
				collect.amtinhand = value['amtinhand'];
				collect.comments = value['comments'];
				collect.changedby = uname.textContent;
				collect.changeddate = fngettoday();
				collect.changedtime = fngetcurrenttime();
				collects.push(collect);				
				return ;}
//			Column 1 - Collection_Type
			row.children().eq(0).text(value['collect_type']);
//			Column 2 - Collected Amt				
			(row.children().eq(1)[0]).children[0].value = value['collect_amt'];		
			row.children()[1].children[0].setAttribute("onchange", "coltrowupd(this)");				
//			Disable Edit Mode
			(row.children().eq(1)[0]).children[0].disabled = true;
//			Column 3 - Cash Handle Collection 
//          (row.children().eq(2)[0]).children[0].value = value['chcolt_amt'];		
			row.children()[2].children[0].setAttribute("id", "chcolt_amt");
			row.children()[2].children[0].setAttribute("onfocus", "coltrowfocus(this)");
			row.children()[2].children[0].setAttribute("onchange", "coltrowupd(this)");				
//			Disable Edit Mode
			(row.children().eq(2)[0]).children[0].disabled = false;				
			hide_flag = true;
//			Column 4 - Comments				
			row.children().eq(3).text(value['comments']);

			row.appendTo(tabcontent);
		});
		if (save_disable == false){			
//			Populate all Collection Type as F4 help for 'Collection Type' Input in new Dynamic row
			var dynrow = $("<tr><td><select><select/></td><td><input/></td><td><input/></td><td><input/></td><td>" +
			"<button type='button' id='btndelcollect' class='btn btn-danger'>DELETE</button></td></tr>");
			dynrow.eq(0)[0].id = "tableview";
			dynrow[0].children[0].children[0].setAttribute("id", "collect1");
			dynrow[0].children[1].children[0].setAttribute("id", "collectamt");
			dynrow[0].children[1].children[0].setAttribute("type", "number");
			dynrow[0].children[1].children[0].setAttribute("disabled", "true");

			dynrow[0].children[2].children[0].setAttribute("id", "chcolt_amt");
			dynrow[0].children[2].children[0].setAttribute("type", "number");
			dynrow[0].children[2].children[0].setAttribute("onfocus", "coltrowfocus(this)");
			dynrow[0].children[2].children[0].setAttribute("onchange", "coltrowupd(this)");

			dynrow.appendTo(tabcontent);	
//			Drop-Down for Engine Oil Product			
			var rowscnt = 0;
			collect_dropdown(collects, rowscnt);	
		}
//		Footer
		var footer = $("<tr><td><input/></td><td></td><td></td><td></td><td></td></tr>");	
		var skip = $("<tr><td><br></td><td></td><td></td><td></td><td></td></tr>");
		var savebutton = $("<tr><td></td><td></td><td><input/></td><td><input/></td><td></td></tr>");
		footer.eq(0)[0].id = "tableview";
		//skip.eq(0)[0].id = "tableview";
		//savebutton.eq(0)[0].id = "tableview";
//		Button - Total Check
		(footer.children().eq(0)[0]).children[0].setAttribute("type", "button");
		(footer.children().eq(0)[0]).children[0].setAttribute("value", "Total Check");
		(footer.children().eq(0)[0]).children[0].setAttribute("id", "btncollecttotalcheck");	
//		Hide Column
		var footer4 = footer.children().eq(4);					

		var th = tabcontent.get()[0].getElementsByTagName("th");				
		if (hide_flag == true){
			footer4[0].style.display = "none";
//			Hide Header Column					
			th[4].style.display = "none";
		}else{
//			Display header/Footer Column 					
			footer4[0].style.display = "";
			th[4].style.display = "";
//			Display 'ADD NEW ROW' Button
			tabcontent[0].children[0].children[0].children[0].children[0].hidden = false;					
		};		
		footer.appendTo(tabcontent);	//Append Footer
		skip[0].setAttribute("class", "noBorder");
		skip.appendTo(tabcontent);   
//		Save Button Footer
		savebutton[0].setAttribute("class", "noBorder");
		(savebutton.children().eq(3)[0]).children[0].setAttribute("type", "button");
		(savebutton.children().eq(3)[0]).children[0].setAttribute("value", "SAVE");	
		(savebutton.children().eq(3)[0]).children[0].setAttribute("id", "btncoltsave");
		(savebutton.children().eq(3)[0]).children[0].setAttribute("class", "btn btn-primary");		

		if (hide_flag == true){
			//(savebutton.children().eq(3)[0]).children[0].setAttribute("disabled", "true");
			//Edit Button				
			// Show Edit option only for 'Super' access user
			if (accessval.innerText == "full") {			
				//(savebutton.children().eq(2)[0]).children[0].setAttribute("disabled", "false");	
				(savebutton.children().eq(2)[0]).children[0].setAttribute("type", "button");
				(savebutton.children().eq(2)[0]).children[0].setAttribute("value", "EDIT");	
				(savebutton.children().eq(2)[0]).children[0].setAttribute("id", "btncoltedit");
				(savebutton.children().eq(2)[0]).children[0].setAttribute("class", "btn btn-primary");
			}				 			
		}
		else{(savebutton.children().eq(2)[0]).children[0].setAttribute("hidden", "true");}
		savebutton.appendTo(tabcontent);

		tbodye.appendTo(tabcontent);				
	});
};
//*********************************************************************************************************
//*********************************************************************************************************
function collect_dropdown(collects, rowscnt){ 
	var idval = "#collect"+(rowscnt+1)
	var $collectselect = $(idval);
	$collectselect.find("option").remove();
	$("<option>").val(" ").text(" ").appendTo($collectselect);
	collects.forEach(function(collect, index){
		if (collect.comments != "X"){
			$("<option>").val(collect.collect_type).text(collect.collect_type).appendTo($collectselect);
		}
	});
};
//************************************************************************************************************************
//Button Click - Total check
$(document).on("click", "#btncollecttotalcheck", function(){
	$("#message").text("");
	var tabcontent = $("#collecttable"), i, sumcollect = 0, sumdcollect = 0, ccomments;	
	var colcnt = tabcontent[0].children[0].children[1].children.length;
	var rowscnt = tabcontent[0].rows.length - 3;
	var collect_amt, dcollect_amt, error = false, error_collect = false, collect_type, netcollect = 0;
	for (i=2; i<=rowscnt; i++){
		if (i == rowscnt){
			//Display aggregate Collect Amt with Comma Notation
			tabcontent[0].rows[i].children[1].textContent = sumdcollect.toLocaleString('en-US', {minimumFractionDigits: 2});
			tabcontent[0].rows[i].children[1].style.color = "#000000";
			tabcontent[0].rows[i].children[2].textContent = sumcollect.toLocaleString('en-US', {minimumFractionDigits: 2});
			tabcontent[0].rows[i].children[2].style.color = "#000000";		
		}else{		
			if (tabcontent[0].rows[i].children[0].children[0] == undefined){
				collect_type = tabcontent[0].rows[i].children[0].textContent;
				ccomments = tabcontent[0].rows[i].children[3].textContent;
			}else{
				collect_type = tabcontent[0].rows[i].children[0].children[0].value;
				ccomments = tabcontent[0].rows[i].children[3].children[0].value;}

			dcollect_amt = tabcontent[0].rows[i].children[1].children[0].value;
			collect_amt = tabcontent[0].rows[i].children[2].children[0].value;
			if (collect_amt == ""){collect_amt = 0};
			if (dcollect_amt == ""){dcollect_amt = 0};
			collect_amt = parseFloat(collect_amt );
			dcollect_amt  = parseFloat(dcollect_amt );

			if (collect_type.trim() == ""){		
				tabcontent[0].rows[i].children[0].focus();
				error_collect = true;
				break;		}
			if (dcollect_amt == 0 && collect_amt == 0){
				tabcontent[0].rows[i].children[1].focus();
				error = true;
				break;		}
			sumcollect = sumcollect + collect_amt;
			sumdcollect = sumdcollect +  dcollect_amt;
		}
	}
	if (error_collect == true){
		$("#message").text("Please Maintain 'Collection Type' for all!!!");
		document.getElementById("message").style.color = "#98052e";		
	}else if (error == true){
		$("#message").text("Please Maintain 'Collect Amt' for all!!!");
		document.getElementById("message").style.color = "#98052e";		
	}
});
//*********************************************************************************************************
//Get Date & Time
function getdatetime() {
//	Date
	var today = fngettoday();   
//	Time
	var currentTime = fngetcurrenttime();
//	Update Today's date
	document.getElementById("changeddate").value = today;
	document.getElementById("changedtime").value = currentTime;
}
//*********************************************************************************************************
//*********************************************************************************************************
$(document).on("click", "#btnaddcollect", function(){
	//Clear Message
	$("#message").text("");
	var tabcontent = $("#collecttable"), idnum;
	var uname = document.getElementById("userfirstname");
	var rowscnt = tabcontent[0].children[1].rows.length - 4;		
	if (rowscnt >= 0){
		var row = tabcontent[0].children[1].children[rowscnt];
//		Validation - Collect Type
		if (row.children[0].children[0] != undefined){
			if (row.children[0].children[0].value.trim() == ""){	
				$("#message").text("Maintain Collect Type before Adding New Row!!!");
				document.getElementById("message").style.color = "#98052e";
				return;	
			}}
//		Validation - Collect Amt
		if ((row.children[2].children[0].value.trim() == "" || row.children[2].children[0].value.trim() == "0") && 
			row.children[1].children[0].value.trim() == "" || row.children[1].children[0].value.trim() == "0"){
			$("#message").text("Enter Collect Amt before Adding New Row!!!");
			document.getElementById("message").style.color = "#98052e";
			return;
		}				
//		Check if all Collect Type been added
		if (collects.length > 0){
			var index_avbl = collects.findIndex(collect => (collect.comments == null || collect.comments.trim() == ""));
			if (index_avbl < 0){
				$("#message").text("All Collect Type Added!!!");
				document.getElementById("message").style.color = "#98052e";
				return;
			}}
	}
	//rowscnt = rowscnt + 1;
	var dynrow = $("<tr><td><select><select/></td><td><input/></td><td><input/></td><td><input/></td><td>" +
	"<button type='button' id='btndelcollect' class='btn btn-danger'>DELETE</button></td></tr>");
	dynrow.eq(0)[0].id = "tableview";
//	Get Last rows maintained 'ID' of Collect & the number series, to maintain with Consecutive Series of Next row
	if (rowscnt >= 0 && collects.length > 0 && row.children[0].children[0] != undefined){
		idnum = row.children[0].children[0].id.substr(7,10);
	}else{	idnum = 0;
	}
	var idnum1 = new Number(idnum);
	var idval = "collect" + (idnum1+1);
	dynrow[0].children[0].children[0].setAttribute("id", idval);
	dynrow.children().eq(1)[0].align = 'center'; //Align Checkbox centrally - Horizontal
	dynrow[0].children[1].children[0].setAttribute("id", "collectamt");
	dynrow[0].children[1].children[0].setAttribute("type", "number");
	dynrow[0].children[1].children[0].setAttribute("disabled", "true");
	//dynrow[0].children[6].style.display = "none"
	dynrow[0].children[2].children[0].setAttribute("id", "chcolt_amt");
	dynrow[0].children[2].children[0].setAttribute("type", "number");
	dynrow[0].children[2].children[0].setAttribute("onfocus", "coltrowfocus(this)");	
	dynrow[0].children[2].children[0].setAttribute("onchange", "coltrowupd(this)");		

//	Check if 'ADD NEW ROW' Clicked after clicking 'EDIT' Button by referring last row Column 'Comments' disabled
	if (rowscnt >= 0) {
		if (row.children[3].children[0] == undefined ){
//			if so then get list of Products which are of not Saved earlier in given Date & Shift
			if (collects.length <= 0){
				var params = {subgroup_name: $("#subgroup_name").val(),
						shift: $("#shift option:selected").text().trim(),
						date: $("#txndate").val()};
				$.post("dailytxn_collectpendajax", ($.param(params)), function(responseJson) {
//					Collect all the Collection Type			
					$.each(responseJson, function(key, value) {
						var collect = {};
						collect.collect_type = value['collect_type'];
						collect.txndate = document.getElementById("txndate").value;
						collect.labour_shift = document.getElementById("shift").value; 
						collect.collect_amt = value['collect_amt'];	
						collect.comments = null;
						collect.changedby = uname.textContent;
						collect.changeddate = fngettoday();
						collect.changedtime = fngetcurrenttime();
						collects.push(collect);	
					});
//					Drop-Down for Collect Type		
					if (collects.length > 0){
						collect_dropdown(collects, idnum1);
					}else{
						$("#message").text("All Collection Type Added!!!");
						document.getElementById("message").style.color = "#98052e";
						return;					
					}					
				});
			}
		}
	}
	//Insert Row at Last 
	if (rowscnt >= 0){
		dynrow.insertAfter(row);
	}else{
//		New First Entry	
		var row = tabcontent[0].children[1].children[0];
		dynrow.insertBefore(row);};				
//		Drop-Down for Engine Oil Product	
		if (collects.length > 0){
			collect_dropdown(collects, idnum1);}				
});
//*********************************************************************************************************
function coltrowfocus(that){
	var amt = parseFloat(0);
	if ($(that).val() != ""){
		amt = parseFloat($(that).val())};
		$(that).data('prev_chamt', amt);
}
//*********************************************************************************************************
function coltrowupd(that){
	var selrow = that.parentElement, collect_amt = 0, lv_amtinhand = 0;
	var collectseltyp;

//	Disable Dropdown of Collection Type
	if (selrow.parentElement.children[0].children[0] != undefined){
		selrow.parentElement.children[0].children[0].disabled = true;
	}
	//selrow.parentElement.children[2].children[0].disabled = true;
//	Update Notation for Collect Amt
	collect_amt = selrow.parentElement.children[2].children[0].value.trim().replaceAll(",","");

	if (collect_amt == ""){collect_amt = 0}
	collect_amt = parseFloat(collect_amt);
	
	selrow.parentElement.children[2].children[0].textContent = collect_amt.toLocaleString('en-US', {minimumFractionDigits: 2});
//	Global Array 'COLLECTS' holds all required details
	//Chosen 'Collect Type' should not list in other Drop-Down 'Collect Type' So setting with Flag in 'Comments' 
	if (selrow.parentElement.children[0].children[0] != undefined){
		collectseltyp = "#" + selrow.parentElement.children[0].children[0].id ;
		collects.find(collect => collect.collect_type == $(collectseltyp).val()).comments = "X";
	}
//	Get Previous Value
	var lv_chcolamt = parseFloat($(that).data('prev_chamt'));	
//	Check & Update Amount for Collect Amount
	lv_amtinhand = parseFloat(that.parentElement.parentNode.parentNode.parentElement.children[0].children[0].children[3].children[0].value);

		lv_amtinhand = (lv_amtinhand - lv_chcolamt) + collect_amt;
//	Amount In Hand	
	that.parentElement.parentNode.parentNode.parentElement.children[0].children[0].children[3].children[0].value = lv_amtinhand;	
}

//*********************************************************************************************************
//Save 'Other Income' Entry
$(document).on("click", "#btncoltsave", function(){
	$("#message").text("");
	var collectupds = new Array();
	var tabcontent = $("#collecttable"), i;
	//var colcnt = tabcontent[0].children[0].children[2].children.length;
	var rowscnt = tabcontent[0].rows.length - 4;
	var uname = document.getElementById("userfirstname"), error = false, 
	error_collect = false;
	for (i=2; i<=rowscnt; i++){
		var collectupd = {};
		if (tabcontent[0].rows[i].children[0].children[0] == undefined){
			collectupd.collect_type = tabcontent[0].rows[i].children[0].textContent;
		}else{
			collectupd.collect_type = tabcontent[0].rows[i].children[0].children[0].value;}
		if (collectupd.collect_type.trim() == ""){
			error_collect = true;
			tabcontent[0].rows[i].children[0].focus();
			break;		}	
		collectupd.txndate = document.getElementById("txndate").value;
		collectupd.labour_shift = document.getElementById("shift").value;
        var lv_collected_amt = tabcontent[0].rows[i].children[1].children[0].value.trim().replaceAll(",","");
		collectupd.collect_amt = tabcontent[0].rows[i].children[2].children[0].value.trim().replaceAll(",","");
		if (tabcontent[0].rows[i].children[3].children[0] == undefined ){
			collectupd.comments = tabcontent[0].rows[i].children[3].textContent;	
		}else{
			collectupd.comments = tabcontent[0].rows[i].children[3].children[0].value;}		
		if (lv_collected_amt == ""){lv_collected_amt = 0};
		lv_collected_amt = parseFloat(lv_collected_amt);
		if (collectupd.collect_amt == ""){collectupd.collect_amt = 0};
		collectupd.collect_amt = parseFloat(collectupd.collect_amt);
		collectupd.collect_amt = collectupd.collect_amt + lv_collected_amt;
//		Collect Amt Validation
		if (collectupd.collect_amt == 0 ){
			tabcontent[0].rows[i].children[2].focus();
			error = true;
			break;		}		
//		Amount in Hand
		collectupd.amtinhand = tabcontent[0].children[0].children[0].children[3].children[0].value.trim().replaceAll(",","");
//		Disable 'EDIT' Mode 
		tabcontent[0].rows[i].children[1].children[0].disabled = true;	
		tabcontent[0].rows[i].children[2].children[0].disabled = true;	
		if (tabcontent[0].rows[i].children[4].children[0] != undefined){
			tabcontent[0].rows[i].children[4].children[0].disabled = true;}

		collectupd.changedby = uname.textContent;
		collectupd.changeddate = fngettoday();
		collectupd.changedtime = fngetcurrenttime();
		collectupds.push(collectupd);
	}
	if (error == true){
		$("#message").text("Please Maintain 'Collect Amt' for all!!!");
		document.getElementById("message").style.color = "#98052e";		
	}else if(error_collect == true){
		$("#message").text("Please Maintain 'Collection Type' for all!!!");
		document.getElementById("message").style.color = "#98052e";
	}else{
//		Disable 'ADD NEW ROW' Button
		tabcontent[0].children[0].children[0].children[0].children[0].disabled = true;
//		Save Collection Amount Details		
		$.post("dailytxn_collectsave", { "collectupds":JSON.stringify(collectupds)}, function(responseText) {
			$("#message").text(responseText);
			document.getElementById("message").style.color = "#98052e";
		});	
	}
//	To get Exception error
	$(document).ajaxError(
			function (event, jqXHR, ajaxSettings, thrownError) {
				if (jqXHR.status == 500) {
					//alert('Error');
					//Get Error Text
					var errortext = jqXHR.responseText.split('<h2>')[1].split('<br />')[0];
					errortext = "Error!!! "+errortext;
					$("#message").text(errortext);
					document.getElementById("message").style.color = "#98052e";
				}
			});	
});
//********************************************************************************************************************
$(document).on("click", "#btndelcollect", function(){
	that = this;
	var i = that.parentElement.parentElement.rowIndex - 2;
	var selrow = that.parentElement, lv_collectamt = 0, lv_amtinhand = 0;
//	Remove 'X' Flag in 'Comments' when the row is deleted	
	var collectsel = "#" + selrow.parentElement.children[0].children[0].id ;
	if ($(collectsel).val() != " "){
		collects.find(collect => collect.collect_type == $(collectsel).val()).comments = "";}
//	Adjust the Amount in Hand Accordingly 
	lv_collectamt = selrow.parentElement.children[2].children[0].value.trim().replaceAll(",","");
	if (lv_collectamt == ""){lv_collectamt = 0};
	lv_collectamt  = parseFloat(lv_collectamt);
	lv_amtinhand = parseFloat(that.parentElement.parentNode.parentNode.parentElement.children[0].children[0].children[3].children[0].value);
	lv_amtinhand = lv_amtinhand - lv_collectamt;
//	Amount In Hand	
	that.parentElement.parentNode.parentNode.parentElement.children[0].children[0].children[3].children[0].value = lv_amtinhand;

	that.parentElement.parentElement.parentElement.deleteRow(i);
});
//*********************************************************************************************************
$(document).on("click", "#btncoltedit", function(){
	$("#message").text("");
	var tabcontent = $("#collecttable"), i;
	var rowscnt = tabcontent[0].rows.length - 4;
	for (i=2; i<=rowscnt; i++){
		//tabcontent[0].rows[i].children[1].children[0].disabled = false;
		tabcontent[0].rows[i].children[2].children[0].disabled = false;
	}
//	Enable Save Mode in Last row
	i = rowscnt + 3;
	tabcontent[0].rows[i].children[3].children[0].disabled = false;
//	Show 'ADD NEW ROW' Button
	tabcontent[0].children[0].children[0].children[0].children[0].hidden = false;
	tabcontent[0].children[0].children[0].children[0].children[0].disabled = false;
});