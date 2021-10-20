//***********************************************************************
//Call servlet to get Owner Details
function crdowner(params){
	var accessval = document.getElementById("useraccess");
	var uname = document.getElementById("userfirstname")
	var tabcontent = $("#ownertable"), hide_flag = false, 
        save_disable = false;
//Enable 'ADD NEW ROW' Button
	tabcontent[0].children[0].children[0].children[0].children[0].disabled = false;	
//Declared 'Owner' as global	
	crdowners = new Array();
var tbodys = $("<tbody>"), tbodye = $("</tbody>");    
tbodys.appendTo(tabcontent);
//Call Servlet to get Owner Data
		$.get("crdowner_ajax", ($.param(params)), function(responseJson) {  
			$("#ownertable").find("tr:gt(1)").remove();
			$.each(responseJson, function(key, value) {
				var row = $("<tr><td></td><td type='number'></td><td type='number'><input/></td><td type='number'><input/></td><td><input/></td><td type='number'><input/></td><td type='number'><input/></td><td type='number'></td><td></td><td></td></tr>");
				row.eq(0)[0].id = "tableview";
//Update Amount in Hand
				tabcontent[0].children[0].children[0].children[3].children[0].value = (value['amtinhand']);				
				tabcontent[0].children[0].children[0].children[3].children[0].textContent = (value['amtinhand']).toLocaleString('en-US', {minimumFractionDigits: 2});				
//If Total Credit Amount value equals 'exists', then it means 
//Owner on entered date is already been stored in 'owner_credit' table. So Disable 'Save' option
			if (value['totcredit'] == null ){
 			save_disable = true;}
			else{
//For New Entry collect all Credit Details			
				var crdowner = {};
				crdowner.txndate = document.getElementById("txndate").value;
				crdowner.labour_shift = document.getElementById("shift").value;				
				crdowner.Owner = value['name'];	
				crdowner.totcredit = value['totcredit']
				crdowner.crdamount = value['crdamount'];
				crdowner.dbamount = value['dbamount'];
				crdowner.mop = value['mop'];
				crdowner.comments = value['comments'];
				crdowner.changedby = uname.textContent;
				crdowner.changeddate = fngettoday();
				crdowner.changedtime = fngetcurrenttime();
				crdowners.push(crdowner);				
				return ;}
//Column 1 - Owner
				row.children().eq(0).text(value['name']);
//Column 2 - Hide 'Current Credit' Column
				var col2 = row.children().eq(1);
				col2[0].style.display = "none"
//Column 3 - Credit amount				
				row.children().eq(2)[0].children[0].value = value['crdamount'];
				(row.children().eq(2)[0]).children[0].disabled = true;
//Column 4 - Debit Amount
				(row.children().eq(3)[0]).children[0].disabled = true;
				(row.children().eq(3)[0]).children[0].value = value['dbamount'];
//Column 5 - Mode of Pay
				(row.children().eq(4)[0]).children[0].disabled = true;
				(row.children().eq(4)[0]).children[0].value = value['mop'];
				hide_flag = true;
//Column 6 - Cash Handle Credit
				(row.children().eq(5)[0]).children[0].disabled = true;
				(row.children().eq(5)[0]).children[0].value = value['ch_crdamount'];
				row.children()[5].children[0].setAttribute("id", "chcrdamount");
				row.children()[6].children[0].setAttribute("id", "chdbamount");
				row.children()[5].children[0].setAttribute("onchange", "crdownerrowupd(this)");
				row.children()[6].children[0].setAttribute("onchange", "crdownerrowupd(this)");
				row.children()[5].children[0].setAttribute("onfocus", "crdownerrowfocus(this)");
				row.children()[6].children[0].setAttribute("onfocus", "crdownerrowfocus(this)");
//Column 7 - Cash Handle Debit
				(row.children().eq(6)[0]).children[0].disabled = true;
				(row.children().eq(6)[0]).children[0].value = value['ch_dbamount'];				
//Column 8 - Aggregate Credit
				var col7 = row.children().eq(7);
				col7[0].style.display = "none"
//Column 9 - Comments				
					row.children().eq(8).text(value['comments']);
//Column 10 - Remove
				    var col9 = row.children().eq(9);
					col9[0].style.display = "none";
					
				if (value['totcredit'] == null){
//Hide 'ADD NEW ROW' Button
					tabcontent[0].children[0].children[0].children[0].children[0].hidden = true;					
				}
				row.appendTo(tabcontent);
			});
			if (save_disable == false){			
//Populate all 'Owner' as F4 help for 'Owner' Input in new Dynamic row
			var dynrow = $("<tr><td><select><select/></td><td></td><td><input/></td><td><input/></td>" +
					"<td><select><select/></td><td><input/></td><td><input/></td><td></td><td><input/></td><td>" +
					"<button type='button' id='btndelcrdowner' class='btn btn-danger'>DELETE</button></td></tr>");
			dynrow.eq(0)[0].id = "tableview";
			dynrow[0].children[0].children[0].setAttribute("id", "crdowner1");
			dynrow[0].children[0].children[0].setAttribute("onchange", "crdownernameupd(this)");
			dynrow[0].children[1].setAttribute("type", "number");			
			dynrow[0].children[2].children[0].setAttribute("id", "crdamount");
			dynrow[0].children[2].children[0].setAttribute("type", "number");
			dynrow[0].children[3].children[0].setAttribute("id", "dbamount");
			dynrow[0].children[3].children[0].setAttribute("type", "number");
			dynrow[0].children[4].children[0].setAttribute("id", "wmop1");
			
			dynrow[0].children[5].children[0].setAttribute("id", "chcrdamount");
			dynrow[0].children[5].children[0].setAttribute("type", "number");
			dynrow[0].children[6].children[0].setAttribute("id", "chdbamount");
			dynrow[0].children[6].children[0].setAttribute("type", "number");
			dynrow[0].children[7].setAttribute("type", "number");
			
			dynrow[0].children[2].children[0].setAttribute("onchange", "crdownerrowupd(this)");
			dynrow[0].children[3].children[0].setAttribute("onchange", "crdownerrowupd(this)");
			dynrow[0].children[5].children[0].setAttribute("onchange", "crdownerrowupd(this)");
			dynrow[0].children[6].children[0].setAttribute("onchange", "crdownerrowupd(this)");
			dynrow[0].children[5].children[0].setAttribute("onfocus", "crdownerrowfocus(this)");
			dynrow[0].children[6].children[0].setAttribute("onfocus", "crdownerrowfocus(this)");
			
			dynrow.appendTo(tabcontent);	
//Drop-Down for 'Owner'			
			var rowscnt = 0;
			crdowner_dropdown(crdowners, rowscnt);
			wmop_dropdown(rowscnt)
			}
//Footer
			var footer = $("<tr><td><input/></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");	
			  var skip = $("<tr><td><br></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
			  var savebutton = $("<tr><td><input/></td><td></td><td></td><td><input/></td><td><input/></td><td></td><td></td><td></td><td></td><td></td></tr>");
			  footer.eq(0)[0].id = "tableview";
			  //skip.eq(0)[0].id = "tableview";
			  //savebutton.eq(0)[0].id = "tableview";
//Button - Total Check
				(footer.children().eq(0)[0]).children[0].setAttribute("type", "button");
				(footer.children().eq(0)[0]).children[0].setAttribute("value", "Total Check");
				(footer.children().eq(0)[0]).children[0].setAttribute("id", "btncrdownertotalcheck");	
//Hide Column
				var footer1 = footer.children().eq(1);
				var footer7 = footer.children().eq(7);
				var footer9 = footer.children().eq(9);					

				var th = tabcontent.get()[0].getElementsByTagName("th");				
				if (hide_flag == true){
					footer1[0].style.display = "none";
					footer7[0].style.display = "none";
					footer9[0].style.display = "none";
//Hide Header Column		
					th[1].style.display = "none";
					th[7].style.display = "none";
					th[9].style.display = "none";
				}else{
//Display header/Footer Column 	
					footer1[0].style.display = ""			
					footer7[0].style.display = "";
					footer9[0].style.display = "";
					th[1].style.display = "";
					th[7].style.display = ""
					th[9].style.display = "";
//Display 'ADD NEW ROW' Button
					tabcontent[0].children[0].children[0].children[0].children[0].hidden = false;					
				};		
				footer.appendTo(tabcontent);	//Append Footer
				skip[0].setAttribute("class", "noBorder");
				skip.appendTo(tabcontent);   
//Save Button Footer
				savebutton[0].setAttribute("class", "noBorder");
				(savebutton.children().eq(3)[0]).children[0].setAttribute("type", "button");
				(savebutton.children().eq(3)[0]).children[0].setAttribute("value", "SAVE");	
				(savebutton.children().eq(3)[0]).children[0].setAttribute("id", "btncrdownersave");
				(savebutton.children().eq(3)[0]).children[0].setAttribute("class", "btn btn-primary");		
//ADD New Row Button
				(savebutton.children().eq(0)[0]).children[0].setAttribute("type", "button");
				(savebutton.children().eq(0)[0]).children[0].setAttribute("value", "ADD ROW");	
				(savebutton.children().eq(0)[0]).children[0].setAttribute("id", "btnaddowner");
				(savebutton.children().eq(0)[0]).children[0].setAttribute("class", "btn btn-success");
				
				if (hide_flag == true){
					(savebutton.children().eq(3)[0]).children[0].setAttribute("disabled", "true");
//ADD ROW Hide					
					(savebutton.children().eq(0)[0]).children[0].setAttribute("hidden", "true");
	//Edit Button				
	// Show Edit option only for 'Super' access user
					 if (accessval.innerText == "full") {			
				//(savebutton.children().eq(2)[0]).children[0].setAttribute("disabled", "false");	
				(savebutton.children().eq(4)[0]).children[0].setAttribute("type", "button");
				(savebutton.children().eq(4)[0]).children[0].setAttribute("value", "EDIT");	
				(savebutton.children().eq(4)[0]).children[0].setAttribute("id", "btncrdowneredit");
				(savebutton.children().eq(4)[0]).children[0].setAttribute("class", "btn btn-primary");
					 }				 			
				}
				else{(savebutton.children().eq(4)[0]).children[0].setAttribute("hidden", "true");}
				savebutton.appendTo(tabcontent);
				
				tbodye.appendTo(tabcontent);				
		});
};
//*********************************************************************************************************
function crdowner_dropdown(crdowners, rowscnt){ 
	var idval = "#crdowner"+(rowscnt+1)
	var $ownerselect = $(idval);
	$ownerselect.find("option").remove();
	$("<option>").val(" ").text(" ").appendTo($ownerselect);
	crdowners.forEach(function(crdowner, index){
		if (crdowner.comments != "X"){
		$("<option>").val(crdowner.Owner).text(crdowner.Owner).appendTo($ownerselect);
		}
	});
	};
//*********************************************************************************************************
	function wmop_dropdown(rowscnt){ 
		var idval = "#wmop"+(rowscnt+1)
		var $mopselect = $(idval);
		$mopselect.find("option").remove();
		$("<option>").val(" ").text(" ").appendTo($mopselect);
		listMops.forEach(function(mop, index){
			$("<option>").val(mop.mop).text(mop.mop).appendTo($mopselect);
		});
		};
//************************************************************************************************************************
//Button Click - Total check
	$(document).on("click", "#btncrdownertotalcheck", function(){
		$("#message").text("");
		var tabcontent = $("#ownertable"), i, netdbamount = 0, crdamount,dbamount;	
		var colcnt = tabcontent[0].children[0].children[1].children.length;
		var rowscnt = tabcontent[0].rows.length - 3, mop;
		var amount, error = false, error_owner = false, error_mop = false, name, netcrdamount = 0;
		var netch_crdamount = 0, netch_dbamount = 0, ch_crdamount = parseFloat(0), ch_dbamount = parseFloat(0);
		for (i=2; i<=rowscnt; i++){
	if (i == rowscnt){
	//Display aggregate Expense Amt with Comma Notation
		tabcontent[0].rows[i].children[2].textContent = netcrdamount.toLocaleString('en-US', {minimumFractionDigits: 2});
		tabcontent[0].rows[i].children[3].textContent = netdbamount.toLocaleString('en-US', {minimumFractionDigits: 2});
		tabcontent[0].rows[i].children[5].textContent = netch_crdamount.toLocaleString('en-US', {minimumFractionDigits: 2});
		tabcontent[0].rows[i].children[6].textContent = netch_dbamount.toLocaleString('en-US', {minimumFractionDigits: 2});
		
		tabcontent[0].rows[i].children[2].style.color = "#000000";
		tabcontent[0].rows[i].children[3].style.color = "#000000";
		tabcontent[0].rows[i].children[5].style.color = "#000000";
		tabcontent[0].rows[i].children[6].style.color = "#000000";
	}else{		
     	if (tabcontent[0].rows[i].children[0].children[0] == undefined){
			name = tabcontent[0].rows[i].children[0].textContent;
		}else{ 
			name = tabcontent[0].rows[i].children[0].children[0].value;					
		}
     	crdamount = tabcontent[0].rows[i].children[2].children[0].value.trim().replaceAll(",","");
		dbamount = tabcontent[0].rows[i].children[3].children[0].value.trim().replaceAll(",","");
		ch_crdamount = tabcontent[0].rows[i].children[5].children[0].value.trim().replaceAll(",","");
		ch_dbamount = tabcontent[0].rows[i].children[6].children[0].value.trim().replaceAll(",","");
		mop = tabcontent[0].rows[i].children[4].children[0].value;
		
		if (crdamount.trim() == ""){crdamount = 0}; if (dbamount.trim() == ""){dbamount = 0};
		crdamount = parseFloat(crdamount); dbamount = parseFloat(dbamount);
		if (ch_crdamount.trim() == ""){ch_crdamount = 0}; if (ch_dbamount.trim() == ""){ch_dbamount = 0};
		ch_crdamount = parseFloat(ch_crdamount); ch_dbamount = parseFloat(ch_dbamount);
		
		if (name.trim() == ""){		
			tabcontent[0].rows[i].children[0].focus();
			error_owner = true;
			break;		}
		if (crdamount == 0 && dbamount == 0 && ch_crdamount == 0 && ch_dbamount == 0){
			tabcontent[0].rows[i].children[2].focus();
			error = true;
			break;		}
		if (dbamount != 0 && mop.trim() == ""){
			tabcontent[0].rows[i].children[4].focus();
			error_mop = true;
			break;
		}
		
			netcrdamount = netcrdamount + crdamount;
			netdbamount = netdbamount + dbamount;
			netch_crdamount = netch_crdamount + ch_crdamount;
			netch_dbamount = netch_dbamount + ch_dbamount;			
	}
		}
		if (error_owner == true){
			$("#message").text("Please Maintain 'Owner' for all!!!");
			document.getElementById("message").style.color = "#98052e";		
		}else if (error == true){
			$("#message").text("Please Maintain atleast any one 'Amount' for all!!!");
			document.getElementById("message").style.color = "#98052e";		
		}else if (error_mop == true){
			$("#message").text("Please Maintain 'Mode of Pay' for respective row maintained with Debit Amount!!!");
			document.getElementById("message").style.color = "#98052e";
		}
	});
//*********************************************************************************************************
	
//*********************************************************
//Get Date & Time
function getdatetime() {
//Date
	var today = fngettoday();   
//Time
	var currentTime = fngetcurrenttime();
//Update Today's date
	document.getElementById("changeddate").value = today;
	document.getElementById("changedtime").value = currentTime;
}
//*********************************************************************************************************
	$(document).on("click", "#btnaddowner", function(){
		//Clear Message
		$("#message").text("");
		var tabcontent = $("#ownertable"), idnum;
		var uname = document.getElementById("userfirstname");
		var rowscnt = tabcontent[0].children[1].rows.length - 4;		
		if (rowscnt >= 0){
		var row = tabcontent[0].children[1].children[rowscnt];
//Validation - Owner Name
		if (row.children[0].children[0] != undefined){
			if (row.children[0].children[0].value.trim() == ""){	
			$("#message").text("Maintain Owner Name before Adding New Row!!!");
			document.getElementById("message").style.color = "#98052e";
			return;	
		}}
//Validation - Credit Amount & Debit Amount
		if (row.children[2].children[0] != undefined){
			if (row.children[2].children[0].value.trim() == "" && row.children[3].children[0].value.trim() == "" && 
				row.children[5].children[0].value.trim() == "" && row.children[6].children[0].value.trim() == "" ){	
			$("#message").text("Maintain Either Credit or Debit Amount before Adding New Row!!!");
			document.getElementById("message").style.color = "#98052e";
			return;	
		}}		
//Validation Debit Amount - Mode of Pay
		if (row.children[3].children[0] != undefined){
			if (row.children[3].children[0].value != "" && row.children[3].children[0].value != "0" && row.children[4].children[0].value.trim() == ""){	
			$("#message").text("Maintain 'Mode of Pay' for entered Debit Amount!!!");
			document.getElementById("message").style.color = "#98052e";
			return;	
		}}
//Check if all Owners added
		if (crdowners.length > 0){
		var index_avbl = crdowners.findIndex(crdowner => (crdowner.comments == null || crdowner.comments.trim() == ""));
		if (index_avbl < 0){
			$("#message").text("All Owners Added!!!");
			document.getElementById("message").style.color = "#98052e";
			return;
		}}
		}
		//rowscnt = rowscnt + 1;
		var dynrow = $("<tr><td><select><select/></td><td></td><td><input/></td><td><input/></td>" +
				"<td><select><select/></td><td><input/></td><td><input/></td><td></td><td><input/></td><td>" +
				"<button type='button' id='btndelcrdowner' class='btn btn-danger'>DELETE</button></td></tr>");
		dynrow.eq(0)[0].id = "tableview";
//Get Last rows maintained 'ID' of Owner & the number series, to maintain with Consecutive Series of Next row
		if (rowscnt >= 0 && crdowners.length > 0 && row.children[0].children[0] != undefined){
		idnum = row.children[0].children[0].id.substr(8,10);
		}else{	idnum = 0;
		}
	    var idnum1 = new Number(idnum);
		var idval = "crdowner" + (idnum1+1);
		var idmop = "wmop" + (idnum1+1);
		//dynrow.eq(0)[0].id = "tableview";		
		dynrow[0].children[0].children[0].setAttribute("id", idval);	
	    dynrow[0].children[0].children[0].setAttribute("onchange", "crdownerrowupd(this)");
	    dynrow[0].children[0].children[0].setAttribute("onchange", "crdownernameupd(this)");
		//dynrow[0].children[1].style.display = "none"
		dynrow[0].children[2].children[0].setAttribute("id", "crdamount");
		dynrow[0].children[2].children[0].setAttribute("type", "number");
		dynrow[0].children[3].children[0].setAttribute("id", "dbamount");
		dynrow[0].children[3].children[0].setAttribute("type", "number");
		dynrow[0].children[4].children[0].setAttribute("id", idmop);

		dynrow[0].children[5].children[0].setAttribute("id", "chcrdamount");
		dynrow[0].children[5].children[0].setAttribute("type", "number");
		dynrow[0].children[6].children[0].setAttribute("id", "chdbamount");
		dynrow[0].children[6].children[0].setAttribute("type", "number");		

		dynrow[0].children[7].setAttribute("type", "number");
		dynrow[0].children[2].children[0].setAttribute("onchange", "crdownerrowupd(this)");
		dynrow[0].children[3].children[0].setAttribute("onchange", "crdownerrowupd(this)");
		dynrow[0].children[5].children[0].setAttribute("onchange", "crdownerrowupd(this)");
		dynrow[0].children[6].children[0].setAttribute("onchange", "crdownerrowupd(this)");
		dynrow[0].children[5].children[0].setAttribute("onfocus", "crdownerrowfocus(this)");
		dynrow[0].children[6].children[0].setAttribute("onfocus", "crdownerrowfocus(this)");		
				
//Check if 'ADD NEW ROW' Clicked after clicking 'EDIT' Button by referring last row Column 'Comments' disabled
		if (rowscnt >= 0) {
		if (row.children[8].children[0] == undefined ){
//if so then get list of Products which are of not Saved earlier in given Date & Shift
			if (crdowners.length <= 0){
			var params = {subgroup_name: $("#subgroup_name").val(),
			    	shift: $("#shift option:selected").text().trim(),
		               date: $("#txndate").val()};
			$.post("dailytxn_crdownerpendajax", ($.param(params)), function(responseJson) {
//Collect all the Owner			
				$.each(responseJson, function(key, value) {
				var crdowner = {};
				crdowner.Owner = value['name'];
				crdowner.txndate = document.getElementById("txndate").value;
				crdowner.labour_shift = document.getElementById("shift").value;
				crdowner.crdamount = value['crdamount'];
				crdowner.dbamount = value['dbamount'];
				crdowner.mop = value['mop'];
				crdowner.comments = null;
				crdowner.changedby = uname.textContent;
				crdowner.changeddate = fngettoday();
				crdowner.changedtime = fngetcurrenttime();
				crdowners.push(crdowner);	
				});
//Drop-Down for Owner		
				if (crdowners.length > 0){
					crdowner_dropdown(crdowners, idnum1);
					wmop_dropdown(idnum1);
				}else{
					$("#message").text("All Owners Added!!!");
					document.getElementById("message").style.color = "#98052e";
					return;					
				}					
			});			
			}
//Hide Not Needed Columns during Edit
			dynrow[0].children[1].style.display = "none";
			dynrow[0].children[7].style.display = "none"
		}
	}
		//Insert Row at Last 
		if (rowscnt >= 0){
			dynrow.insertAfter(row);
			}else{
//New First Entry	
			var row = tabcontent[0].children[1].children[0];
			dynrow.insertBefore(row);};				
//Drop-Down for 'Mode of Pay'	
		if (crdowners.length > 0){
			crdowner_dropdown(crdowners, idnum1);}
		    wmop_dropdown(idnum1);
	});
//*********************************************************************************************************
	function crdownernameupd(that){
		var selrow = that.parentElement;

	//Disable Dropdown of Owner
		//selrow.parentElement.children[0].children[0].disabled = true;
//Get Selected value
		var ownername = selrow.children[0].selectedOptions[0].value
//Global Array 'owners' holds all required details		
//Get Selected Owner Name row value
		var crdownerrow = crdowners.find(crdowner => crdowner.Owner == ownername);
//Update TotCredit
		selrow.parentElement.children[1].textContent = (crdownerrow.totcredit).toLocaleString('en-US', {minimumFractionDigits: 2});
	}

//*********************************************************************************************************
	function crdownerrowfocus(that){
		var amt = parseFloat(0);
		if ($(that).val() != ""){
		amt = parseFloat($(that).val())};
		$(that).data('prev_chamt', amt);
	}
//*********************************************************************************************************
function crdownerrowupd(that){
	var selrow = that.parentElement, totamount, crdamount, dbamount, aggamount, amount;
	var lv_chcrdamount = parseFloat(0), lv_chdbamount = parseFloat(0);
//Disable Dropdown of Owner
	if (selrow.parentElement.children[0].children[0] != undefined){	
	selrow.parentElement.children[0].children[0].disabled = true;}
//Update Notation for Amount
	amount = selrow.parentElement.children[1].textContent.trim().replaceAll(",","");
	if (amount.trim() == ""){ amount = 0;	}
	totamount = parseFloat(amount);
	amount = selrow.parentElement.children[2].children[0].value.trim().replaceAll(",","");
	if (amount.trim() == ""){ amount = 0;	}
	//selrow.parentElement.children[2].children[0].textContent = crdamount.toLocaleString('en-US', {minimumFractionDigits: 2});
	crdamount = parseFloat(amount);
	amount = selrow.parentElement.children[3].children[0].value.trim().replaceAll(",","");
	if (amount.trim() == ""){ amount = 0;	}
	dbamount = parseFloat(amount);	
	
//Get Cash Handle Amounts
	amount = selrow.parentElement.children[5].children[0].value.trim().replaceAll(",","");
	if (amount.trim() == ""){ amount = 0;	}
	lv_chcrdamount = parseFloat(amount);
	amount = selrow.parentElement.children[6].children[0].value.trim().replaceAll(",","");
	if (amount.trim() == ""){ amount = 0;	}
	lv_chdbamount = parseFloat(amount);	
//Check & Update Amount in Hand 
//Get Previous Value
	var prev_chamt = parseFloat($(that).data('prev_chamt'));
	
	var lv_amtinhand = parseFloat(that.parentElement.parentNode.parentNode.parentElement.children[0].children[0].children[3].children[0].value);
	if (that.id == "chcrdamount"){
		lv_amtinhand = (lv_amtinhand - lv_chcrdamount ) + ( prev_chamt)
	  	
	}else if (that.id == "chdbamount"){lv_amtinhand = (lv_amtinhand + lv_chdbamount) - ( prev_chamt)};
//Amount In Hand	
	that.parentElement.parentNode.parentNode.parentElement.children[0].children[0].children[3].children[0].value = lv_amtinhand;
//Aggregate Amount
	aggamount = ( totamount + crdamount + lv_chcrdamount ) - ( dbamount + lv_chdbamount);
	selrow.parentElement.children[7].textContent = aggamount.toLocaleString('en-US', {minimumFractionDigits: 2});	
//Global Array 'owners' holds all required details
//Chosen Owner Name should not list in other Drop-Down Product So setting with Flag in 'Comments' 
	if (selrow.parentElement.children[0].children[0] != undefined){	
	var ownerseltyp = "#" + selrow.parentElement.children[0].children[0].id ;
	crdowners.find(crdowner => crdowner.Owner == $(ownerseltyp).val()).comments = "X";}	
}

//*********************************************************************************************************
//Save Owner Entry
$(document).on("click", "#btncrdownersave", function(){
	$("#message").text("");
	var crdownerupds = new Array();
	var tabcontent = $("#ownertable"), i;
	var rowscnt = tabcontent[0].rows.length - 4;
	var uname = document.getElementById("userfirstname"), error = false, 
	error = false, error_owner = false, error_mop = false, amount;
	for (i=2; i<=rowscnt; i++){
		var crdownerupd = {};
		if (tabcontent[0].rows[i].children[0].children[0] == undefined){
			crdownerupd.name = tabcontent[0].rows[i].children[0].textContent;
			crdownerupd.comments = tabcontent[0].rows[i].children[8].textContent;
		}else{
			crdownerupd.name = tabcontent[0].rows[i].children[0].children[0].value;
			crdownerupd.comments = tabcontent[0].rows[i].children[8].children[0].value}
		if (crdownerupd.name.trim() == ""){
			error_name = true;
			tabcontent[0].rows[i].children[0].focus();
			break;		}	
		crdownerupd.txndate = document.getElementById("txndate").value;
		crdownerupd.labour_shift = document.getElementById("shift").value;

		crdownerupd.crdamount = tabcontent[0].rows[i].children[2].children[0].value.trim().replaceAll(",","");
		crdownerupd.dbamount = tabcontent[0].rows[i].children[3].children[0].value.trim().replaceAll(",","");
		crdownerupd.mop = tabcontent[0].rows[i].children[4].children[0].value;		
		crdownerupd.ch_crdamount = tabcontent[0].rows[i].children[5].children[0].value.trim().replaceAll(",","");
		crdownerupd.ch_dbamount = tabcontent[0].rows[i].children[6].children[0].value.trim().replaceAll(",","");
//Replace Null with Zero
		if (crdownerupd.crdamount.trim() == ""){amount = 0;
			crdownerupd.crdamount = parseFloat(amount);}
		if (crdownerupd.dbamount.trim() == ""){amount = 0;
			crdownerupd.dbamount = parseFloat(amount);}
		
		if (crdownerupd.ch_crdamount.trim() == ""){amount = 0;
		crdownerupd.ch_crdamount = parseFloat(amount)};
		if (crdownerupd.ch_dbamount.trim() == ""){amount = 0;
		crdownerupd.ch_dbamount = parseFloat(amount)};
			
		//crdownerupd.totcredit = tabcontent[0].rows[i].children[5].textContent.trim().replaceAll(",","");		
//Owner Name Validation
		if (crdownerupd.name.trim() == ""){		
			tabcontent[0].rows[i].children[0].focus();
			error_owner = true;
			break;		}
//Amount Validation		
		if (crdownerupd.crdamount == 0 && crdownerupd.dbamount == 0 && crdownerupd.ch_crdamount == 0 && crdownerupd.ch_dbamount == 0){
			tabcontent[0].rows[i].children[2].focus();
			error = true;
			break;	}
//MOP Validation			}
		if (crdownerupd.dbamount != 0 && crdownerupd.mop.trim() == ""){
			tabcontent[0].rows[i].children[4].focus();
			error_mop = true;
			break;		}			
		
//Disable 'EDIT' Mode 
		tabcontent[0].rows[i].children[2].children[0].disabled = true;		
		tabcontent[0].rows[i].children[3].children[0].disabled = true;	
		tabcontent[0].rows[i].children[4].children[0].disabled = true;	
		tabcontent[0].rows[i].children[5].children[0].disabled = true;		
		tabcontent[0].rows[i].children[6].children[0].disabled = true;		
		if (tabcontent[0].rows[i].children[8].children[0] != undefined){
		tabcontent[0].rows[i].children[8].children[0].disabled = true;
		tabcontent[0].rows[i].children[9].children[0].disabled = true;}	
//Check whether 'MOP' is sent with 'NX', If so then it is first entry 
//of month. Hence While saving, update Pastdue of Previous Month in Current month first day	
//Read Owner Name row value(from DB Before Saving)
		var crdownerrow = crdowners.find(crdowner => crdowner.Owner == crdownerupd.name);
				if (crdownerrow != undefined && crdownerrow.mop == 'NX'){
					crdownerupd.newentry = 'X';
		//Get & Set First & Last date of Previous Month
					var pdate = crdownerupd.txndate;
					let retdate = fngetprevdtrange(pdate);
		//Set First day of Previous Month			
					crdownerupd.psdate = retdate[0];
		//Set Last day of Previous Month
					crdownerupd.pedate = retdate[1];			
				}else{
					crdownerupd.newentry = ' ';
					crdownerupd.psdate = null;
					crdownerupd.pedate = null;
				}		
//Amount in Hand
		crdownerupd.amtinhand = tabcontent[0].children[0].children[0].children[3].children[0].value.trim().replaceAll(",","");				
		crdownerupd.changedby = uname.textContent;
		crdownerupd.changeddate = fngettoday();
		crdownerupd.changedtime = fngetcurrenttime();
		crdownerupds.push(crdownerupd);
	}
		if (error_owner == true){
			$("#message").text("Please Maintain 'Owner' for all!!!");
			document.getElementById("message").style.color = "#98052e";		
		}else if (error == true){
			$("#message").text("Please Maintain atleast any one 'Amount' for all!!!");
			document.getElementById("message").style.color = "#98052e";		
		}else if (error_mop == true){
			$("#message").text("Please Maintain 'Mode of Pay' for respective row maintained with Debit Amount!!!");
			document.getElementById("message").style.color = "#98052e";
		}	
	else{
		//Disable 'ADD NEW ROW' Button
		tabcontent[0].children[0].children[0].children[0].children[0].disabled = true;
		tabcontent[0].children[1].lastElementChild.firstElementChild.children[0].disabled = true;
//Save Owner details		
	$.post("dailytxn_crdownersave", { "crdownerupds":JSON.stringify(crdownerupds)}, function(responseText) {
		$("#message").text(responseText);
		document.getElementById("message").style.color = "#98052e";
	});	
	}
//To get Exception error
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
$(document).on("click", "#btndelcrdowner", function(){
	that = this;
	var i = that.parentElement.parentElement.rowIndex - 2;
	var selrow = that.parentElement, lv_chcrdamt = parseFloat(0), lv_chdbamt = parseFloat(0), lv_amtinhand = parseFloat(0);
//Remove 'X' Flag in 'Comments' when the row is deleted	
	var crdownersel = "#" + selrow.parentElement.children[0].children[0].id ;
	if ($(crdownersel).val() != " "){
		crdowners.find(crdowner => crdowner.Owner == $(crdownersel).val()).comments = "";}
//Adjust the Amount in Hand Accordingly 
    lv_chcrdamt = selrow.parentElement.children[5].children[0].value.trim().replaceAll(",","");
    lv_chdbamt = selrow.parentElement.children[6].children[0].value.trim().replaceAll(",","");
    if (lv_chcrdamt == ""){lv_chcrdamt = parseFloat(0)}; if(lv_chdbamt == ""){lv_chdbamt = parseFloat(0)}; 
    lv_chcrdamt = parseFloat(lv_chcrdamt); lv_chdbamt = parseFloat(lv_chdbamt);
	lv_amtinhand = parseFloat(that.parentElement.parentNode.parentNode.parentElement.children[0].children[0].children[3].children[0].value);
	lv_amtinhand = (lv_amtinhand + lv_chcrdamt) - lv_chdbamt;
//Amount In Hand	
	that.parentElement.parentNode.parentNode.parentElement.children[0].children[0].children[3].children[0].value = lv_amtinhand;	
	
	that.parentElement.parentElement.parentElement.deleteRow(i);	
});
//*********************************************************************************************************
$(document).on("click", "#btncrdowneredit", function(){
	$("#message").text("");
	var tabcontent = $("#ownertable"), i;
	var rowscnt = tabcontent[0].rows.length - 4;
	for (i=2; i<=rowscnt; i++){
		//tabcontent[0].rows[i].children[1].children[0].disabled = false;
		tabcontent[0].rows[i].children[2].children[0].disabled = false;
		tabcontent[0].rows[i].children[3].children[0].disabled = false;
		tabcontent[0].rows[i].children[4].children[0].disabled = false;
		tabcontent[0].rows[i].children[5].children[0].disabled = false;
		tabcontent[0].rows[i].children[6].children[0].disabled = false;		
	}
//Enable Save Mode in Last row
	i = rowscnt + 3;
	tabcontent[0].rows[i].children[3].children[0].disabled = false;
//Show 'ADD NEW ROW' Button
	tabcontent[0].children[0].children[0].children[0].children[0].hidden = false;
	tabcontent[0].children[0].children[0].children[0].children[0].disabled = false;
});
