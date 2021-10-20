//******************************************************************************
//Compare Changed Sales Date to 'To be maintained' date
function salesdatechg(that){
	changed_date = that.value;
	prev_date = that.defaultValue;
	if (changed_date < prev_date){
		window.value = "lower";
	}else{
		window.value = "higheq";
	}
}

//******************************************************************************
$(document).on("click", "#btndtxnentry", function(){
	$("#message").text("");
	var params = {subgroup_name: $("#subgroup_name").val(),
			shift: $("#shift option:selected").text().trim(),
			date: $("#txndate").val()};
//	Expense	
	if ( $("#subgroup_name").val().toLocaleLowerCase() == "expense" ) {		
//		Call servlet to get  Expense Details
		expense(params);
//		Show Expense Rows		
		$("#expensetable").show();	
//		Hide		
		$("#customertable").hide();	
		$("#ownertable").hide();
		$("#emptable").hide();
		$("#inctable").hide();
		$("#collecttable").hide();
		$("#ovratable").hide();
		//$("#tableview").hide();
		//var tabcontent1 = $("#pumpreadtable");
		//tabcontent1[0].tBodies[0].hidden = true;
	};
//	Credit Customer
	if ( $("#subgroup_name").val().toLocaleLowerCase() == "customer" ) {		
		//Call servlet to get Credit Customer Details
		creditcust(params);
		//Show Customer Rows		
		$("#customertable").show();	
		//Hide		
		$("#expensetable").hide();	
		$("#ownertable").hide();
		$("#emptable").hide();
		$("#inctable").hide();
		$("#collecttable").hide();
		$("#ovratable").hide();
	};
//	Owner
	if ( $("#subgroup_name").val().toLocaleLowerCase() == "owner" ) {		
		//Call servlet to get Credit Owner Details
		crdowner(params);
		//Show Customer Rows		
		$("#ownertable").show();	
		//Hide		
		$("#expensetable").hide();	
		$("#customertable").hide();
		$("#emptable").hide();
		$("#inctable").hide();
		$("#collecttable").hide();
		$("#ovratable").hide();
	};
//	Employee Wages
	if ( $("#subgroup_name").val().toLocaleLowerCase() == "wages" ) {		
		//Call servlet to get Employee Wages Details
		fempwages(params);
		//Show Customer Rows		
		$("#emptable").show();	
		//Hide		
		$("#expensetable").hide();	
		$("#customertable").hide();
		$("#ownertable").hide();
		$("#inctable").hide();
		$("#collecttable").hide();
		$("#ovratable").hide();
	};			
//	Other Income
	if ( $("#subgroup_name").val().toLocaleLowerCase() == "debit" ) {		
		fothincome(params);
//		Show 'Other Income'		
		$("#inctable").show();	
//		Hide		
		$("#expensetable").hide();	
		$("#customertable").hide();
		$("#ownertable").hide();
		$("#emptable").hide();
		$("#collecttable").hide();
		$("#ovratable").hide();
	};
//	Collection
	if ( $("#subgroup_name").val().toLocaleLowerCase() == "collect" ) {		
		fcollect(params);
//		Show 'Collect'		
		$("#collecttable").show();
//		Hide		
		$("#expensetable").hide();	
		$("#customertable").hide();
		$("#ownertable").hide();
		$("#emptable").hide();
		$("#inctable").hide();	
		$("#ovratable").hide();
	};	
//	Overall Update
	if ( $("#subgroup_name").val().toLocaleLowerCase() == "overall" ) {		
		fovrallupd(params);
//		Show 'Overall Update'		
		$("#ovratable").show();	
//		Hide		
		$("#expensetable").hide();	
		$("#customertable").hide();
		$("#ownertable").hide();
		$("#emptable").hide();
		$("#inctable").hide();
		$("#collecttable").hide();
	};										

});
//******************************************************************************
//***********************************************************************
//Call servlet to get Expense Details
function expense(params){
	var accessval = document.getElementById("useraccess");
	var uname = document.getElementById("userfirstname")
	var tabcontent = $("#expensetable"), hide_flag = false, 
	save_disable = false;
//	Enable 'ADD NEW ROW' Button
	tabcontent[0].children[0].children[0].children[0].children[0].disabled = false;	
//	Declared 'expense' as global	
	expenses = new Array();
	var tbodys = $("<tbody>"), tbodye = $("</tbody>");    
	tbodys.appendTo(tabcontent);
//	Call Servlet to get Engine Oil Sales Data
	$.get("expense_ajax", ($.param(params)), function(responseJson) {  
		$("#expensetable").find("tr:gt(1)").remove();
		$.each(responseJson, function(key, value) {
			var row = $("<tr><td></td><td type='number'><input/></td><td type='number'><input/></td><td></td><td></td></tr>");
			row.eq(0)[0].id = "tableview";
//			Update Amount in Hand
			tabcontent[0].children[0].children[0].children[3].children[0].value = (value['amtinhand']);				
			tabcontent[0].children[0].children[0].children[3].children[0].textContent = (value['amtinhand']).toLocaleString('en-US', {minimumFractionDigits: 2});
//			If Expense Amt value equals 'exists', then it means 
//			Expense on entered date is already been stored in 'expense' table. So Disable 'Save' option
			if (value['expense_amt'] != null){				
				save_disable = true;}
			else{
//				For New Entry collect all Expense Type				
				var expense = {};
				expense.txndate = document.getElementById("txndate").value;
				expense.labour_shift = document.getElementById("shift").value;
				expense.expense_type = value['expense_type'];					
				expense.expense_amt = value['expense_amt'];
				expense.amtinhand = value['amtinhand'];
				expense.comments = value['comments'];
				expense.changedby = uname.textContent;
				expense.changeddate = fngettoday();
				expense.changedtime = fngetcurrenttime();
				expenses.push(expense);				
				return ;}
//			Column 1 - Expense_Type
			row.children().eq(0).text(value['expense_type']);
//			Column 2 - Expense Amt				
			(row.children().eq(1)[0]).children[0].value = value['expense_amt'];		
			row.children()[1].children[0].setAttribute("onchange", "exprowupd(this)");				
//			Disable Edit Mode
			(row.children().eq(1)[0]).children[0].disabled = true;
//			Column 3 - Cash Handle Expense 
			(row.children().eq(2)[0]).children[0].value = value['chexp_amt'];		
			row.children()[2].children[0].setAttribute("id", "chexp_amt");
			row.children()[2].children[0].setAttribute("onfocus", "exprowfocus(this)");
			row.children()[2].children[0].setAttribute("onchange", "exprowupd(this)");				
//			Disable Edit Mode
			(row.children().eq(2)[0]).children[0].disabled = true;				
			hide_flag = true;
//			Column 4 - Comments				
			row.children().eq(3).text(value['comments']);

			if (value['expense_amt'] != null){
//				Hide 'ADD NEW ROW' Button
				tabcontent[0].children[0].children[0].children[0].children[0].hidden = true;					
			}
			row.appendTo(tabcontent);
		});
		if (save_disable == false){			
//			Populate all Expense Type as F4 help for 'Expense Type' Input in new Dynamic row
			var dynrow = $("<tr><td><select><select/></td><td><input/></td><td><input/></td><td><input/></td><td>" +
			"<button type='button' id='btndelexpense' class='btn btn-danger'>DELETE</button></td></tr>");
			dynrow.eq(0)[0].id = "tableview";
			dynrow[0].children[0].children[0].setAttribute("id", "expense1");
			dynrow[0].children[1].children[0].setAttribute("id", "expenseamt");
			dynrow[0].children[1].children[0].setAttribute("type", "number");
			dynrow[0].children[1].children[0].setAttribute("onchange", "exprowupd(this)");

			dynrow[0].children[2].children[0].setAttribute("id", "chexp_amt");
			dynrow[0].children[2].children[0].setAttribute("type", "number");
			dynrow[0].children[2].children[0].setAttribute("onfocus", "exprowfocus(this)");
			dynrow[0].children[2].children[0].setAttribute("onchange", "exprowupd(this)");

			dynrow.appendTo(tabcontent);	
//			Drop-Down for Engine Oil Product			
			var rowscnt = 0;
			expense_dropdown(expenses, rowscnt);	
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
		(footer.children().eq(0)[0]).children[0].setAttribute("id", "btnexptotalcheck");	
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
		(savebutton.children().eq(3)[0]).children[0].setAttribute("id", "btnexpsave");
		(savebutton.children().eq(3)[0]).children[0].setAttribute("class", "btn btn-primary");		

		if (hide_flag == true){
			(savebutton.children().eq(3)[0]).children[0].setAttribute("disabled", "true");
			//Edit Button				
			// Show Edit option only for 'Super' access user
			if (accessval.innerText == "full") {			
				//(savebutton.children().eq(2)[0]).children[0].setAttribute("disabled", "false");	
				(savebutton.children().eq(2)[0]).children[0].setAttribute("type", "button");
				(savebutton.children().eq(2)[0]).children[0].setAttribute("value", "EDIT");	
				(savebutton.children().eq(2)[0]).children[0].setAttribute("id", "btnexpedit");
				(savebutton.children().eq(2)[0]).children[0].setAttribute("class", "btn btn-primary");
			}				 			
		}
		else{(savebutton.children().eq(2)[0]).children[0].setAttribute("hidden", "true");}
		savebutton.appendTo(tabcontent);

		tbodye.appendTo(tabcontent);				
	});
};
//*********************************************************************************************************
function expense_dropdown(expenses, rowscnt){ 
	var idval = "#expense"+(rowscnt+1)
	var $expselect = $(idval);
	$expselect.find("option").remove();
	$("<option>").val(" ").text(" ").appendTo($expselect);
	expenses.forEach(function(expense, index){
		if (expense.comments != "X"){
			$("<option>").val(expense.expense_type).text(expense.expense_type).appendTo($expselect);
		}
	});
};

//************************************************************************************************************************
//Button Click - Total check
$(document).on("click", "#btnexptotalcheck", function(){
	$("#message").text("");
	var tabcontent = $("#expensetable"), i, sumexpense = 0, sumchexp = 0, expcomments;	
	var colcnt = tabcontent[0].children[0].children[1].children.length;
	var rowscnt = tabcontent[0].rows.length - 3;
	var expense_amt, chexp_amt, error = false, error_exp = false,error_cmnts = false, exp_type, netexpense = 0;
	for (i=2; i<=rowscnt; i++){
		if (i == rowscnt){
			//Display aggregate Expense Amt with Comma Notation
			tabcontent[0].rows[i].children[1].textContent = sumexpense.toLocaleString('en-US', {minimumFractionDigits: 2});
			tabcontent[0].rows[i].children[1].style.color = "#000000";
			tabcontent[0].rows[i].children[2].textContent = sumchexp.toLocaleString('en-US', {minimumFractionDigits: 2});
			tabcontent[0].rows[i].children[2].style.color = "#000000";		
		}else{		
			if (tabcontent[0].rows[i].children[0].children[0] == undefined){
				exp_type = tabcontent[0].rows[i].children[0].textContent;
				expcomments = tabcontent[0].rows[i].children[3].textContent;
			}else{
				exp_type = tabcontent[0].rows[i].children[0].children[0].value;
				expcomments = tabcontent[0].rows[i].children[3].children[0].value;}

			expense_amt = tabcontent[0].rows[i].children[1].children[0].value;
			chexp_amt = tabcontent[0].rows[i].children[2].children[0].value;
			if (expense_amt == ""){expense_amt = 0};
			if (chexp_amt == ""){chexp_amt = 0};
			expense_amt = parseFloat(expense_amt);
			chexp_amt = parseFloat(chexp_amt);

			if (exp_type.trim() == ""){		
				tabcontent[0].rows[i].children[0].focus();
				error_exp = true;
				break;		}
			if (expense_amt == 0 && chexp_amt == 0){
				tabcontent[0].rows[i].children[1].focus();
				error = true;
				break;		}
			if (expcomments.trim() == ""){
				tabcontent[0].rows[i].children[3].focus();
				error_cmnts = true;
				break;		}

			//netexpense = parseFloat(tabcontent[0].rows[i].children[1].children[0].value.replaceAll(",",""));
			sumexpense = sumexpense + expense_amt;
			sumchexp = sumchexp +  chexp_amt;
		}
	}
	if (error_exp == true){
		$("#message").text("Please Maintain 'Expense Type' for all!!!");
		document.getElementById("message").style.color = "#98052e";		
	}else if (error == true){
		$("#message").text("Please Maintain 'Expense Amt' for all!!!");
		document.getElementById("message").style.color = "#98052e";		
	}else if (error_cmnts == true){
		$("#message").text("Please Maintain 'Comments' for all!!!");
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
//*************************************************************************************
//'Labour Shift' Change
$(document).on("change", "#shift", function(){
	//Show PumpREad Entry Table		
	$("#pumpreadtable").hide();	
});
//**********************************************************
function dailytxn(){
	//Hide Display Content
	$("#expensetable").hide();
	$("#customertable").hide();	
	$("#ownertable").hide();
	$("#emptable").hide();
	$("#inctable").hide();
	$("#collecttable").hide();
	$("#ovratable").hide();

	var today = fngettoday();
//	Get last maintained date	
	var lasttxndate = document.getElementById("txndate").value;
	lasttxndate.trim();
//	Convert String to Date
	var date = new Date(lasttxndate);
	if (lasttxndate != ""){
//		Add a day 
		date.setDate( date.getDate() + 1);
		var sdate = dateconvert(date);
		document.getElementById("txndate").value = sdate;
	}else{
		document.getElementById("txndate").value = today;
	}
	document.getElementById("txndate").setAttribute("max", today);	

}
//*************************************************************************************
function salesdisp(){
	gettodaydate() ;
	getmonthstart();
//	Hide Display Content
	$("#scrolltable").hide();
}
//*************************************************************************************

//***********************************************************************
//Display Sales table
function displaysalestable(){
	var params = {subgroup_name: $("#subgroup_name").val(),
			salesdate_from: $("#monthstart").val(),
			salesdate_to:   $("#todaydate").val()};

	var tabcontent = $("#tableview");
	$.get("saleslistajax", ($.param(params)), function(responseJson) {
		$("#tableview").find("tr:gt(0)").remove();
		$.each(responseJson, function(key, value) {
			var row = $("<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>" +
			"<td><a>Edit</a>&nbsp;&nbsp;&nbsp;&nbsp;<input/></td></tr>");
			row.eq(0)[0].id = "tableview";
			row.children().eq(0).text(value['product']);
//			Set Pop-up Click for First Column
			var modal = row.children().eq(0)[0];
			modal.id = "btnrowpopup";
			modal.setAttribute("type", "button");
//			Remainingn Columns          
			row.children().eq(1).text(value['salesdate']);
			row.children().eq(2).text(value['subgroup_name']);
			row.children().eq(3).text(value['sales_qty']);
			row.children().eq(4).text(value['stock_qty']);
			row.children().eq(5).text(value['uom']);
			row.children().eq(6).text(value['price_unit']);
			row.children().eq(7).text(value['price_total']);
			row.children().eq(8).text(value['taxpercent_total']);
			row.children().eq(9).text(value['taxamt_total']);
			row.children().eq(10).text(value['comments']);
			row.children().eq(11).text(value['changedby']);
			row.children().eq(12).text(value['changeddate']);
			row.children().eq(13).text(value['changedtime']); 
//			Get/Set Element content "a"
			var a = row.children().eq(14)[0];
			var date = dateconvert(value['salesdate']);
			var edithref = "salesedit?product=" + value['product'] + "&salesdate=" + date;
			var deletehref = "salesdeleteajax?product=" + value['product'] + "&salesdate=" + date;
			a.children[0].id = "linkedit"; a.children[1].id = "btnlinkdelete";            
//			Set Attribute for "href"
			a.children[0].setAttribute("href", edithref);
			a.children[1].setAttribute("name", deletehref);
			a.children[1].setAttribute("value", "Delete");
			a.children[1].setAttribute("type", "button");

			row.appendTo(tabcontent);
		});		
	});		
	//Show Display Content
	$("#scrolltable").show();
//	To get Exception error
	$(document).ajaxError(
			function (event, jqXHR, ajaxSettings, thrownError) {
				if (jqXHR.status == 500) {
					//alert('Error');
//					Get Error Text
					var errortext = jqXHR.responseText.split('<h2>')[1].split('<br />')[0];
					errortext = "Error!!! "+errortext;
					$("#message").text(errortext);
					document.getElementById("message").style.color = "#98052e";
				}
			});

}
//*********************************************************************************************************
$(document).on("click", "#btnaddexpense", function(){
	//Clear Message
	$("#message").text("");
	var tabcontent = $("#expensetable"), idnum;
	var uname = document.getElementById("userfirstname");
	var rowscnt = tabcontent[0].children[1].rows.length - 4;		
	if (rowscnt >= 0){
		var row = tabcontent[0].children[1].children[rowscnt];
//		Validation - Expense Type
		if (row.children[0].children[0] != undefined){
			if (row.children[0].children[0].value.trim() == ""){	
				$("#message").text("Maintain Expense Type before Adding New Row!!!");
				document.getElementById("message").style.color = "#98052e";
				return;	
			}}
//		Validation - Expense Amt
		if (row.children[1].children[0].value.trim() == "" && row.children[2].children[0].value.trim() == ""){
			$("#message").text("Enter Expense Amt before Adding New Row!!!");
			document.getElementById("message").style.color = "#98052e";
			return;
		}
		//Validation - Comments
		if (row.children[3].children[0] != undefined){
			if (row.children[3].children[0].value.trim() == ""){
				$("#message").text("Enter Comments before Adding New Row!!!");
				document.getElementById("message").style.color = "#98052e";
				return;
			}}				
//		Check if all Expense Type been added
		if (expenses.length > 0){
			var index_avbl = expenses.findIndex(expense => (expense.comments == null || expense.comments.trim() == ""));
			if (index_avbl < 0){
				$("#message").text("All Expense Type Added!!!");
				document.getElementById("message").style.color = "#98052e";
				return;
			}}
	}
	//rowscnt = rowscnt + 1;
	var dynrow = $("<tr><td><select><select/></td><td><input/></td><td><input/></td><td><input/></td><td>" +
	"<button type='button' id='btndelexpense' class='btn btn-danger'>DELETE</button></td></tr>");
	dynrow.eq(0)[0].id = "tableview";
//	Get Last rows maintained 'ID' of Expense & the number series, to maintain with Consecutive Series of Next row
	if (rowscnt >= 0 && expenses.length > 0 && row.children[0].children[0] != undefined){
		idnum = row.children[0].children[0].id.substr(7,10);
	}else{	idnum = 0;
	}
	var idnum1 = new Number(idnum);
	var idval = "expense" + (idnum1+1);
	dynrow[0].children[0].children[0].setAttribute("id", idval);
	dynrow.children().eq(1)[0].align = 'center'; //Align Checkbox centrally - Horizontal
	dynrow[0].children[1].children[0].setAttribute("id", "expenseamt");
	dynrow[0].children[1].children[0].setAttribute("type", "number");
	dynrow[0].children[1].children[0].setAttribute("onchange", "exprowupd(this)");
	//dynrow[0].children[6].style.display = "none"
	dynrow[0].children[2].children[0].setAttribute("id", "chexp_amt");
	dynrow[0].children[2].children[0].setAttribute("type", "number");
	dynrow[0].children[2].children[0].setAttribute("onfocus", "exprowfocus(this)");	
	dynrow[0].children[2].children[0].setAttribute("onchange", "exprowupd(this)");		

//	Check if 'ADD NEW ROW' Clicked after clicking 'EDIT' Button by referring last row Column 'Comments' disabled
	if (rowscnt >= 0) {
		if (row.children[3].children[0] == undefined ){
//			if so then get list of Products which are of not Saved earlier in given Date & Shift
			if (expenses.length <= 0){
				var params = {subgroup_name: $("#subgroup_name").val(),
						shift: $("#shift option:selected").text().trim(),
						date: $("#txndate").val()};
				$.post("dailytxn_exppendajax", ($.param(params)), function(responseJson) {
//					Collect all the Expense Type			
					$.each(responseJson, function(key, value) {
						var expense = {};
						expense.expense_type = value['expense_type'];
						expense.txndate = document.getElementById("txndate").value;
						expense.labour_shift = document.getElementById("shift").value; 
						expense.expense_amt = value['expense_amt'];	
						expense.comments = null;
						expense.changedby = uname.textContent;
						expense.changeddate = fngettoday();
						expense.changedtime = fngetcurrenttime();
						expenses.push(expense);	
					});
//					Drop-Down for Expense Type		
					if (expenses.length > 0){
						expense_dropdown(expenses, idnum1);
					}else{
						$("#message").text("All Expenses Added!!!");
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
		if (expenses.length > 0){
			expense_dropdown(expenses, idnum1);}				
});
//*********************************************************************************************************
function exprowfocus(that){
	var amt = parseFloat(0);
	if ($(that).val() != ""){
		amt = parseFloat($(that).val())};
		$(that).data('prev_chamt', amt);
}
//*********************************************************************************************************
function exprowupd(that){
	//var tabcontent = $("#expensetable"); 
	var selrow = that.parentElement, exp_amt = 0,chexp_amt = 0, lv_amtinhand = 0;
	var expseltyp, lv_chexpamt = parseFloat(0);

//	Disable Dropdown of Expense & Cash Handle
	if (selrow.parentElement.children[0].children[0] != undefined){
		selrow.parentElement.children[0].children[0].disabled = true;
	}
	//selrow.parentElement.children[2].children[0].disabled = true;
//	Update Notation for Expense Amt
	exp_amt = parseFloat(selrow.parentElement.children[1].children[0].value.trim().replaceAll(",",""));
	chexp_amt = selrow.parentElement.children[2].children[0].value.trim().replaceAll(",","");
	if (chexp_amt == ""){chexp_amt = 0}
	chexp_amt = parseFloat(chexp_amt);
	selrow.parentElement.children[1].children[0].textContent = exp_amt.toLocaleString('en-US', {minimumFractionDigits: 2});
	selrow.parentElement.children[2].children[0].textContent = chexp_amt.toLocaleString('en-US', {minimumFractionDigits: 2});
//	Global Array 'EXPENSES' holds all required details
	//Chosen 'Expense Type' should not list in other Drop-Down 'Expense  Type' So setting with Flag in 'Comments' 
	if (selrow.parentElement.children[0].children[0] != undefined){
		expseltyp = "#" + selrow.parentElement.children[0].children[0].id ;
		expenses.find(expense => expense.expense_type == $(expseltyp).val()).comments = "X";
	}
//	Get Previous Value
	var lv_chexpamt = parseFloat($(that).data('prev_chamt'));	
//	Check & Update Amount for 'Cash_Handle Expense'
	lv_amtinhand = parseFloat(that.parentElement.parentNode.parentNode.parentElement.children[0].children[0].children[3].children[0].value);
	if (that.id == "chexp_amt"){
		lv_amtinhand = (lv_amtinhand + lv_chexpamt) - chexp_amt;}
//	Amount In Hand	
	that.parentElement.parentNode.parentNode.parentElement.children[0].children[0].children[3].children[0].value = lv_amtinhand;
}

//*********************************************************************************************************
//Save Expense Entry
$(document).on("click", "#btnexpsave", function(){
	$("#message").text("");
	var expenseupds = new Array();
	var tabcontent = $("#expensetable"), i;
	//var colcnt = tabcontent[0].children[0].children[2].children.length;
	var rowscnt = tabcontent[0].rows.length - 4;
	var uname = document.getElementById("userfirstname"), error = false, 
	error_exp = false, error_cmnts = false;
	for (i=2; i<=rowscnt; i++){
		var expenseupd = {};
		if (tabcontent[0].rows[i].children[0].children[0] == undefined){
			expenseupd.expense_type = tabcontent[0].rows[i].children[0].textContent;
		}else{
			expenseupd.expense_type = tabcontent[0].rows[i].children[0].children[0].value;}
		if (expenseupd.expense_type.trim() == ""){
			error_exp = true;
			tabcontent[0].rows[i].children[0].focus();
			break;		}	
		expenseupd.txndate = document.getElementById("txndate").value;
		expenseupd.labour_shift = document.getElementById("shift").value;

		expenseupd.expense_amt = tabcontent[0].rows[i].children[1].children[0].value.trim().replaceAll(",","");
//		Cash hande Expense		
		expenseupd.chexp_amt = tabcontent[0].rows[i].children[2].children[0].value.trim().replaceAll(",","");
		if (tabcontent[0].rows[i].children[3].children[0] == undefined ){
			expenseupd.comments = tabcontent[0].rows[i].children[3].textContent;	
		}else{
			expenseupd.comments = tabcontent[0].rows[i].children[3].children[0].value;}		
//		Expense Amt Validation
		if (expenseupd.expense_amt.trim() == "" && expenseupd.chexp_amt.trim() == ""){
			tabcontent[0].rows[i].children[2].focus();
			error = true;
			break;		}
//		Expense Comments Validation
		if (expenseupd.comments.trim() == "" || expenseupd.comments == null){
			tabcontent[0].rows[i].children[3].focus();
			error_cmnts = true;
			break;		}		
//		Amount in Hand
		expenseupd.amtinhand = tabcontent[0].children[0].children[0].children[3].children[0].value.trim().replaceAll(",","");
//		Disable 'EDIT' Mode 
		tabcontent[0].rows[i].children[1].children[0].disabled = true;	
		tabcontent[0].rows[i].children[2].children[0].disabled = true;	
		if (tabcontent[0].rows[i].children[4].children[0] != undefined){
			tabcontent[0].rows[i].children[4].children[0].disabled = true;}
		if (expenseupd.expense_amt == ""){expenseupd.expense_amt = 0;}
		expenseupd.expense_amt = parseFloat(expenseupd.expense_amt);
		if (expenseupd.chexp_amt == ""){expenseupd.chexp_amt = 0;}
		expenseupd.chexp_amt = parseFloat(expenseupd.chexp_amt);
		expenseupd.changedby = uname.textContent;
		expenseupd.changeddate = fngettoday();
		expenseupd.changedtime = fngetcurrenttime();
		expenseupds.push(expenseupd);
	}
	if (error == true){
		$("#message").text("Please Maintain 'Expense Amt' for all!!!");
		document.getElementById("message").style.color = "#98052e";		
	}else if(error_exp == true){
		$("#message").text("Please Maintain 'Expense Type' for all!!!");
		document.getElementById("message").style.color = "#98052e";
	}else if(error_cmnts == true){
		$("#message").text("Please Maintain 'Comments' for all!!!");
		document.getElementById("message").style.color = "#98052e";
	}else{
//		Disable 'ADD NEW ROW' Button
		tabcontent[0].children[0].children[0].children[0].children[0].disabled = true;
//		Save fuel with Engine Oil Sales details		
		$.post("dailytxn_expensesave", { "expenseupds":JSON.stringify(expenseupds)}, function(responseText) {
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
$(document).on("click", "#btndelexpense", function(){
	that = this;
	var i = that.parentElement.parentElement.rowIndex - 2;
	var selrow = that.parentElement, lv_expamt = 0, lv_amtinhand = 0;
//	Remove 'X' Flag in 'Comments' when the row is deleted	
	var expsel = "#" + selrow.parentElement.children[0].children[0].id ;
	if ($(expsel).val() != " "){
		expenses.find(expense => expense.expense_type == $(expsel).val()).comments = "";}
//	Adjust the Amount in Hand Accordingly 
	lv_expamt = selrow.parentElement.children[2].children[0].value.trim().replaceAll(",","");
	if (lv_expamt == ""){lv_expamt = 0};
	lv_expamt  = parseFloat(lv_expamt);
	lv_amtinhand = parseFloat(that.parentElement.parentNode.parentNode.parentElement.children[0].children[0].children[3].children[0].value);
	lv_amtinhand = lv_amtinhand + lv_expamt;
//	Amount In Hand	
	that.parentElement.parentNode.parentNode.parentElement.children[0].children[0].children[3].children[0].value = lv_amtinhand;

	that.parentElement.parentElement.parentElement.deleteRow(i);	

});
//*********************************************************************************************************
$(document).on("click", "#btnexpedit", function(){
	$("#message").text("");
	var tabcontent = $("#expensetable"), i;
	var rowscnt = tabcontent[0].rows.length - 4;
	for (i=2; i<=rowscnt; i++){
		tabcontent[0].rows[i].children[1].children[0].disabled = false;
		tabcontent[0].rows[i].children[2].children[0].disabled = false;
	}
//	Enable Save Mode in Last row
	i = rowscnt + 3;
	tabcontent[0].rows[i].children[3].children[0].disabled = false;
//	Show 'ADD NEW ROW' Button
	tabcontent[0].children[0].children[0].children[0].children[0].hidden = false;
	tabcontent[0].children[0].children[0].children[0].children[0].disabled = false;
});
