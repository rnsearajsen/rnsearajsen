//***********************************************************************
//Call servlet to get Emp. Wages Details
function fothincome(params) {
	var accessval = document.getElementById("useraccess");
	var uname = document.getElementById("userfirstname")
	var tabcontent = $("#inctable"), hide_flag = false, 
	save_disable = false;
//	Enable 'ADD NEW ROW' Button
	tabcontent[0].children[0].children[0].children[0].children[0].disabled = false;	
//	Declared 'othincomes' as global	
	othincomes = new Array();
	var tbodys = $("<tbody>"), tbodye = $("</tbody>");    
	tbodys.appendTo(tabcontent);
//	Call Servlet to get Emp. Wages Data
	$.get("othincome_ajax", ($.param(params)), function(responseJson) {  
		$("#inctable").find("tr:gt(1)").remove();
		$.each(responseJson, function(key, value) {
			var row = $("<tr><td><input/></td><td type='number'><input/></td><td><input/></td><td type='number'><input/></td><td><input/></td><td><input/></td></tr>");
			row.eq(0)[0].id = "tableview";
//			Update Amount in Hand
			tabcontent[0].children[0].children[0].children[3].children[0].value = (value['amtinhand']);				
			tabcontent[0].children[0].children[0].children[3].children[0].textContent = (value['amtinhand']).toLocaleString('en-US', {minimumFractionDigits: 2});				
//			If Income Type is not null, then it means 
//			Income Details on entered date is already been stored in 'oth_incomes' table. So Disable 'Save' option
			if (value['inctype'] != null ){
				save_disable = true;}
			else{
//				For New Entry collect all Expense Type				
				var othincome = {};
				othincome.txndate = document.getElementById("txndate").value;
				othincome.labour_shift = document.getElementById("shift").value;				
				othincome.inctype = value['inctype'];	
				othincome.dbamount = value['dbamount']
				othincome.mop = value['mop']
				othincome.comments = value['comments']
				othincome.changedby = uname.textContent;
				othincome.changeddate = fngettoday();
				othincome.changedtime = fngetcurrenttime();
				othincomes.push(othincome);				
				return ;}
//			Column 1 - Income Type
			//row.children().eq(0).text(value['inctype']);
			row.children().eq(0)[0].children[0].value = value['inctype'];
			(row.children().eq(0)[0]).children[0].disabled = true;
//			Column 2 - Dbamount
			//row.children().eq(1).text(value['dbamount']);
			row.children().eq(1)[0].children[0].value = value['dbamount'];
			(row.children().eq(1)[0]).children[0].disabled = true;
//			Column 3 - Mop
			row.children().eq(2)[0].children[0].value = value['mop'];
			(row.children().eq(2)[0]).children[0].disabled = true;
			hide_flag = true;
//			Cash Handle Debit Amount
			row.children().eq(3)[0].children[0].value = value['ch_dbamount'];
			row.children()[3].children[0].setAttribute("id", "ch_dbamount");
			row.children()[3].children[0].setAttribute("onfocus", "othincomerowfocus(this)");
			row.children()[3].children[0].setAttribute("onchange", "othincomerowupd(this)");	
			(row.children().eq(3)[0]).children[0].disabled = true;
//			Column 4 - Comments					
			row.children().eq(4)[0].children[0].value = value['comments'];
			(row.children().eq(4)[0]).children[0].disabled = true;
//			Column 5 - Remove
			var col5 = row.children().eq(5);
			col5[0].style.display = "none";

			if (value['inctype'] != null){
//				Hide 'ADD NEW ROW' Button
				tabcontent[0].children[0].children[0].children[0].children[0].hidden = true;					
			}
			row.appendTo(tabcontent);
		});
		if (save_disable == false){			
//			New Dynamic row
			var dynrow = $("<tr><td><input/></td><td><input/></td><td><select><select/></td><td><input/></td><td><input/></td>" +
			"<td><button type='button' id='btndelothincome' class='btn btn-danger'>DELETE</button></td></tr>");
			dynrow.eq(0)[0].id = "tableview";
			//dynrow[0].children[0].children[0].setAttribute("id", "othincome1");
			//dynrow[0].children[0].children[0].setAttribute("onchange", "othincomenameupd(this)");			
			dynrow[0].children[1].children[0].setAttribute("type", "number");
			dynrow[0].children[2].children[0].setAttribute("id", "omop1");

			dynrow[0].children[3].children[0].setAttribute("id", "ch_dbamount");
			dynrow[0].children[3].children[0].setAttribute("type", "number");
			dynrow[0].children[3].children[0].setAttribute("onfocus", "othincomerowfocus(this)");
			dynrow[0].children[3].children[0].setAttribute("onchange", "othincomerowupd(this)");
			dynrow.appendTo(tabcontent);	
//			Drop-Down for 'Emp. Wages'			
			var rowscnt = 0;
			omop_dropdown(rowscnt)
		}
//		Footer
		var footer = $("<tr><td><input/></td><td></td><td></td><td></td><td></td><td></td></tr>");	
		var skip = $("<tr><td><br></td><td></td><td></td><td></td><td></td><td></td></tr>");
		var savebutton = $("<tr><td><input/></td><td></td><td></td><td></td><td><input/></td><td><input/></td></tr>");
		footer.eq(0)[0].id = "tableview";
		//skip.eq(0)[0].id = "tableview";
		//savebutton.eq(0)[0].id = "tableview";
//		Button - Total Check
		(footer.children().eq(0)[0]).children[0].setAttribute("type", "button");
		(footer.children().eq(0)[0]).children[0].setAttribute("value", "Total Check");
		(footer.children().eq(0)[0]).children[0].setAttribute("id", "btnothincometotalcheck");	
//		Hide Column
		var footer5 = footer.children().eq(5);					

		var th = tabcontent.get()[0].getElementsByTagName("th");				
		if (hide_flag == true){
			footer5[0].style.display = "none";
//			Hide Header Column		
			th[5].style.display = "none";
		}else{
//			Display header/Footer Column 	
			footer5[0].style.display = "";
			th[5].style.display = "";
//			Display 'ADD NEW ROW' Button
			tabcontent[0].children[0].children[0].children[0].children[0].hidden = false;					
		};		
		footer.appendTo(tabcontent);	//Append Footer
		skip[0].setAttribute("class", "noBorder");
		skip.appendTo(tabcontent);   
//		Save Button Footer
		savebutton[0].setAttribute("class", "noBorder");
		(savebutton.children().eq(4)[0]).children[0].setAttribute("type", "button");
		(savebutton.children().eq(4)[0]).children[0].setAttribute("value", "SAVE");	
		(savebutton.children().eq(4)[0]).children[0].setAttribute("id", "btnothincomesave");
		(savebutton.children().eq(4)[0]).children[0].setAttribute("class", "btn btn-primary");		
//		ADD New Row Button
		(savebutton.children().eq(0)[0]).children[0].setAttribute("type", "button");
		(savebutton.children().eq(0)[0]).children[0].setAttribute("value", "ADD ROW");	
		(savebutton.children().eq(0)[0]).children[0].setAttribute("id", "btnaddinc");
		(savebutton.children().eq(0)[0]).children[0].setAttribute("class", "btn btn-success");

		if (hide_flag == true){
			(savebutton.children().eq(4)[0]).children[0].setAttribute("disabled", "true");
//			ADD ROW Hide					
			(savebutton.children().eq(0)[0]).children[0].setAttribute("hidden", "true");
			//Edit Button				
			// Show Edit option only for 'Super' access user
			if (accessval.innerText == "full") {				
				(savebutton.children().eq(5)[0]).children[0].setAttribute("type", "button");
				(savebutton.children().eq(5)[0]).children[0].setAttribute("value", "EDIT");	
				(savebutton.children().eq(5)[0]).children[0].setAttribute("id", "btnothincomeedit");
				(savebutton.children().eq(5)[0]).children[0].setAttribute("class", "btn btn-primary");
			}				 			
		}
		else{(savebutton.children().eq(5)[0]).children[0].setAttribute("hidden", "true");}
		savebutton.appendTo(tabcontent);

		tbodye.appendTo(tabcontent);				
	});
};
//*********************************************************************************************************
function omop_dropdown(rowscnt){ 
	var idval = "#omop"+(rowscnt+1)
	var $mopselect = $(idval);
	$mopselect.find("option").remove();
	$("<option>").val(" ").text(" ").appendTo($mopselect);
	listMops.forEach(function(mop, index){
		$("<option>").val(mop.mop).text(mop.mop).appendTo($mopselect);
	});
};
//************************************************************************************************************************
//Button Click - Total check
$(document).on("click", "#btnothincometotalcheck", function(){
	$("#message").text("");
	var tabcontent = $("#inctable"), i, netdbamount = parseFloat(0), netch_dbamount = parseFloat(0), dbamount, ch_dbamount;	
	var colcnt = tabcontent[0].children[0].children[1].children.length;
	var rowscnt = tabcontent[0].rows.length - 3, mop;
	var amount, error = false, error_inctype = false, error_mop = false, inctype;
	for (i=2; i<=rowscnt; i++){
		if (i == rowscnt){
			//Display aggregate Amt with Comma Notation		
			tabcontent[0].rows[i].children[1].textContent = netdbamount.toLocaleString('en-US', {minimumFractionDigits: 2});
			tabcontent[0].rows[i].children[1].style.color = "#000000";
			tabcontent[0].rows[i].children[3].textContent = netch_dbamount.toLocaleString('en-US', {minimumFractionDigits: 2});
			tabcontent[0].rows[i].children[3].style.color = "#000000";
		}else{		
			if (tabcontent[0].rows[i].children[0].children[0] == undefined){
				inctype = tabcontent[0].rows[i].children[0].textContent;
				dbamount = tabcontent[0].rows[i].children[1].textContent;
				mop = tabcontent[0].rows[i].children[2].textContent;
				ch_dbamount = tabcontent[0].rows[i].children[3].textContent;
			}else{ 
				inctype = tabcontent[0].rows[i].children[0].children[0].value;
				dbamount = tabcontent[0].rows[i].children[1].children[0].value.trim().replaceAll(",","");
				mop = tabcontent[0].rows[i].children[2].children[0].value; 	  
				ch_dbamount = tabcontent[0].rows[i].children[3].children[0].value.trim().replaceAll(",","");
			}     	    		

			if (dbamount.trim() == ""){dbamount = 0};
			dbamount = parseFloat(dbamount);
			if (ch_dbamount.trim() == ""){ch_dbamount = 0};
			ch_dbamount = parseFloat(ch_dbamount);

			if (inctype.trim() == ""){		
				tabcontent[0].rows[i].children[1].focus();
				error_inctype = true;
				break;		}
			if (dbamount == 0 && ch_dbamount == 0){
				tabcontent[0].rows[i].children[1].focus();
				error = true;
				break;		}			
			if (mop.trim() == ""){
				tabcontent[0].rows[i].children[2].focus();
				error_mop = true;
				break;
			}

			//parseFloat(tabcontent[0].rows[i].children[1].textContent.(",",""));
			netdbamount = netdbamount + dbamount;
			netch_dbamount = netch_dbamount + ch_dbamount;
		}
	}
	if (error_inctype == true){
		$("#message").text("Please Maintain 'Income Type' for all!!!");
		document.getElementById("message").style.color = "#98052e";				
	}else if(error == true){
		$("#message").text("Please Maintain 'Amount' for respective row maintained!!!");
		document.getElementById("message").style.color = "#98052e";
	}
	else if (error_mop == true){
		$("#message").text("Please Maintain 'Mode of Pay' for respective row maintained!!!");
		document.getElementById("message").style.color = "#98052e";
	}
});
//*********************************************************************************************************

//*********************************************************
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
$(document).on("click", "#btnaddinc", function(){
	//Clear Message
	$("#message").text("");
	var tabcontent = $("#inctable"), idnum;
	var uname = document.getElementById("userfirstname");
	var rowscnt = tabcontent[0].children[1].rows.length - 4;		
	if (rowscnt >= 0){
		var row = tabcontent[0].children[1].children[rowscnt];
//		Validation - Income Type
		if (row.children[0].children[0] != undefined){
			if (row.children[0].children[0].value.trim() == ""){	
				$("#message").text("Maintain 'Income Type' before Adding New Row!!!");
				document.getElementById("message").style.color = "#98052e";
				return;			
			}}
//		Validation - Amount
		if (row.children[1].children[0] != undefined){
			if (row.children[1].children[0].value.trim() == "" && row.children[3].children[0].value.trim() == ""){	
				$("#message").text("Maintain 'Amount' before Adding New Row!!!");
				document.getElementById("message").style.color = "#98052e";
				return;			
			}}		
//		Validation - Mop
		if (row.children[2].children[0] != undefined){
			if (row.children[2].children[0].value.trim() == ""){	
				$("#message").text("Maintain 'Mode of Pay' for respective row maintained!!!");
				document.getElementById("message").style.color = "#98052e";
				return;	
			}}
	}
	//rowscnt = rowscnt + 1;
	var dynrow = $("<tr><td><input/></td><td><input/></td><td><select><select/></td>" +
			"<td><input/></td><td><input/></td><td>" +
	"<button type='button' id='btndelothincome' class='btn btn-danger'>DELETE</button></td></tr>");
	dynrow.eq(0)[0].id = "tableview";
//	Get Last rows maintained 'ID' & the number series, to maintain with Consecutive Series of Next row
	if (rowscnt >= 0 && row.children[2].children[0] != undefined){
		idnum = row.children[2].children[0].id.substr(4,10);
	}else{	idnum = 0;
	}
	var idnum1 = new Number(idnum);
	var idmop = "omop" + (idnum1+1);
	//dynrow.eq(0)[0].id = "tableview";				    
	dynrow[0].children[1].children[0].setAttribute("id", "dbamount");
	dynrow[0].children[1].children[0].setAttribute("type", "number");
	dynrow[0].children[2].children[0].setAttribute("id", idmop);

	dynrow[0].children[3].children[0].setAttribute("id", "ch_dbamount");
	dynrow[0].children[3].children[0].setAttribute("type", "number");
	dynrow[0].children[3].children[0].setAttribute("onfocus", "othincomerowfocus(this)");	
	dynrow[0].children[3].children[0].setAttribute("onchange", "othincomerowupd(this)");
	//Insert Row at Last 
	if (rowscnt >= 0){
		dynrow.insertAfter(row);
	}else{
//		New First Entry	
		var row = tabcontent[0].children[1].children[0];
		dynrow.insertBefore(row);};				
//		Drop-Down for Mop
		omop_dropdown(idnum1);
});
//*********************************************************************************************************
function othincomerowfocus(that){
	var amt = parseFloat(0);
	if ($(that).val() != ""){
		amt = parseFloat($(that).val())};
		$(that).data('prev_chamt', amt);
}
//*********************************************************************************************************
function othincomerowupd(that){
	var selrow = that.parentElement;
//	Disable Income Type
	selrow.parentElement.children[0].children[0].disabled = true;
	var ch_dbamount = selrow.parentElement.children[3].children[0].value.trim().replaceAll(",","");
	if (ch_dbamount == ''){ch_dbamount = 0};
	ch_dbamount = parseFloat(ch_dbamount);
//	Get Previous Value
	var lv_chdbamt = parseFloat($(that).data('prev_chamt'));	
//	Check & Update Amount for 'Cash_Handle Income'
	var lv_amtinhand = parseFloat(that.parentElement.parentNode.parentNode.parentElement.children[0].children[0].children[3].children[0].value);
	if (that.id == "ch_dbamount"){
		lv_amtinhand = (lv_amtinhand - lv_chdbamt) + ch_dbamount;}
//	Amount In Hand	
	that.parentElement.parentNode.parentNode.parentElement.children[0].children[0].children[3].children[0].value = lv_amtinhand;	
}

//*********************************************************************************************************
//Save 'Other Income' Entry
$(document).on("click", "#btnothincomesave", function(){
	$("#message").text("");
	var othincomeupds = new Array();
	var tabcontent = $("#inctable"), i;
	var rowscnt = tabcontent[0].rows.length - 4;
	var uname = document.getElementById("userfirstname"), 
	error = false, error_inctype = false, error_mop = false, amount;
	for (i=2; i<=rowscnt; i++){
		var othincomeupd = {};
		if (tabcontent[0].rows[i].children[0].children[0] == undefined){
			othincomeupd.inctype = tabcontent[0].rows[i].children[0].textContent;
		}else{
			othincomeupd.inctype = tabcontent[0].rows[i].children[0].children[0].value;}
		if (othincomeupd.inctype.trim() == ""){
			error_inctype = true;
			tabcontent[0].rows[i].children[0].focus();
			break;		}	
		othincomeupd.txndate = document.getElementById("txndate").value;
		othincomeupd.labour_shift = document.getElementById("shift").value;
		othincomeupd.dbamount = tabcontent[0].rows[i].children[1].children[0].value.trim().replaceAll(",","");
		othincomeupd.mop = tabcontent[0].rows[i].children[2].children[0].value;
		othincomeupd.ch_dbamount = tabcontent[0].rows[i].children[3].children[0].value.trim().replaceAll(",","");
		othincomeupd.comments = tabcontent[0].rows[i].children[4].children[0].value;
//		Replace Null with Zero
		if (othincomeupd.dbamount.trim() == ""){
			amount = 0;
			othincomeupd.dbamount = parseFloat(amount);
		}
		if (othincomeupd.ch_dbamount.trim() == ""){
			amount = 0;
			othincomeupd.ch_dbamount = parseFloat(amount);
		}
//		Amount Validation
		if (othincomeupd.ch_dbamount == 0 && othincomeupd.dbamount == 0){
			error = true;
			break;		}
//		Mop Validation			}
		if (othincomeupd.mop.trim() == ""){
			tabcontent[0].rows[i].children[2].focus();
			error_mop = true;
			break;		}			
//		Amount in Hand
		othincomeupd.amtinhand = tabcontent[0].children[0].children[0].children[3].children[0].value.trim().replaceAll(",","");		
//		Disable 'EDIT' Mode 
		tabcontent[0].rows[i].children[0].children[0].disabled = true;		
		tabcontent[0].rows[i].children[1].children[0].disabled = true;	
		tabcontent[0].rows[i].children[2].children[0].disabled = true;
		tabcontent[0].rows[i].children[3].children[0].disabled = true;
		tabcontent[0].rows[i].children[4].children[0].disabled = true;

		othincomeupd.changedby = uname.textContent;
		othincomeupd.changeddate = fngettoday();
		othincomeupd.changedtime = fngetcurrenttime();
		othincomeupds.push(othincomeupd);
	}
	if (error_inctype == true){
		$("#message").text("Please Maintain 'Income Type' for all!!!");
		document.getElementById("message").style.color = "#98052e";		
	}else if ( error == true ){
		$("#message").text("Please Maintain 'Income Amt' for all!!!");
		document.getElementById("message").style.color = "#98052e";
	}else if (error_mop == true){
		$("#message").text("Please Maintain 'Mode of Pay' for respective row maintained!!!");
		document.getElementById("message").style.color = "#98052e";
	}	
	else{
		//Disable 'ADD NEW ROW' Button
		tabcontent[0].children[0].children[0].children[0].children[0].disabled = true;
		tabcontent[0].children[1].lastElementChild.firstElementChild.children[0].disabled = true;
//		Save Emp. Wages details		
		$.post("dailytxn_othincomessave", { "othincomeupds":JSON.stringify(othincomeupds)}, function(responseText) {
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
$(document).on("click", "#btndelothincome", function(){
	that = this;
	var i = that.parentElement.parentElement.rowIndex - 2;
	var selrow = that.parentElement, lv_incamt, lv_amtinhand;
//	Adjust the Amount in Hand Accordingly 
	lv_incamt = selrow.parentElement.children[3].children[0].value.trim().replaceAll(",","");
	if (lv_incamt == ""){lv_incamt = 0};
	lv_incamt  = parseFloat(lv_incamt);
	lv_amtinhand = parseFloat(that.parentElement.parentNode.parentNode.parentElement.children[0].children[0].children[3].children[0].value);
	lv_amtinhand = lv_amtinhand - lv_incamt;
//	Amount In Hand	
	that.parentElement.parentNode.parentNode.parentElement.children[0].children[0].children[3].children[0].value = lv_amtinhand;
	
	that.parentElement.parentElement.parentElement.deleteRow(i);	
});
//*********************************************************************************************************
$(document).on("click", "#btnothincomeedit", function(){
	$("#message").text("");
	var tabcontent = $("#inctable"), i;
	var rowscnt = tabcontent[0].rows.length - 4;
	for (i=2; i<=rowscnt; i++){
		tabcontent[0].rows[i].children[1].children[0].disabled = false;
		tabcontent[0].rows[i].children[2].children[0].disabled = false;
		tabcontent[0].rows[i].children[3].children[0].disabled = false;
		tabcontent[0].rows[i].children[4].children[0].disabled = false;
	}
//	Enable Save Mode in Last row
	i = rowscnt + 3;
	tabcontent[0].rows[i].children[4].children[0].disabled = false;
//	Show 'ADD NEW ROW' Button
	tabcontent[0].children[0].children[0].children[0].children[0].hidden = false;
	tabcontent[0].children[0].children[0].children[0].children[0].disabled = false;
});