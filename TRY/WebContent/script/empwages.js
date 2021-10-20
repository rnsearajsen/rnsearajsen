//***********************************************************************
//Call servlet to get Emp. Wages Details
function fempwages(params) {
	var accessval = document.getElementById("useraccess");
	var uname = document.getElementById("userfirstname")
	var tabcontent = $("#emptable"), hide_flag = false, 
        save_disable = false;
//Enable 'ADD NEW ROW' Button
	tabcontent[0].children[0].children[0].children[0].children[0].disabled = false;	
//Declared 'empwages' as global	
	empwages = new Array();
var tbodys = $("<tbody>"), tbodye = $("</tbody>");    
tbodys.appendTo(tabcontent);
//Call Servlet to get Emp. Wages Data
		$.get("empwage_ajax", ($.param(params)), function(responseJson) {  
			$("#emptable").find("tr:gt(1)").remove();
			$.each(responseJson, function(key, value) {
				var row = $("<tr><td></td><td></td><td type='number'></td><td><input/></td><td type='number'><input/></td><td type='number'><input/></td><td type='number'><input/></td><td type='number'><input/></td><td type='number'></td><td></td></tr>");
				row.eq(0)[0].id = "tableview";
//Update Amount in Hand
				tabcontent[0].children[0].children[0].children[3].children[0].value = (value['amtinhand']);				
				tabcontent[0].children[0].children[0].children[3].children[0].textContent = (value['amtinhand']).toLocaleString('en-US', {minimumFractionDigits: 2});				
//If Monthly Default salary not been fetched, then it means 
//Emp. Wages on entered date is already been stored in 'empwages' table. So Disable 'Save' option
			if (value['mth_salary'] == 0 ){
 			save_disable = true;}
			else{
//For New Entry collect all Expense Type				
				var empwage = {};
				empwage.txndate = document.getElementById("txndate").value;
				empwage.labour_shift = document.getElementById("shift").value;				
				empwage.emp = value['name'];	
				empwage.position = value['sgname'];
				empwage.mthsalary = value['mth_salary']
				empwage.attendance = value['attendance']
				empwage.crdamount = value['crdamount'];
				empwage.dbamount = value['dbamount'];
				empwage.actsalary = value['act_salary'];
				empwage.changedby = uname.textContent;
				empwage.changeddate = fngettoday();
				empwage.changedtime = fngetcurrenttime();
				empwages.push(empwage);				
				return ;}
//Column 1 - emp
				row.children().eq(0).text(value['name']);
//Column 2 - Hide 'Position' Column
				var col2 = row.children().eq(1);
				col2[0].style.display = "none"
//Column 3 - Hide 'Monthly Salary'  Column	
				var col3 = row.children().eq(2);
				col3[0].style.display = "none"
//Column 4 - Attendance					
				row.children().eq(3)[0].children[0].value = value['attendance'];
				(row.children().eq(3)[0]).children[0].disabled = true;
//Column 5 - Credit Amount				
				row.children().eq(4)[0].children[0].value = value['crdamount'];
				(row.children().eq(4)[0]).children[0].disabled = true;
//Column 6 - Debit Amount
				(row.children().eq(5)[0]).children[0].disabled = true;
				(row.children().eq(5)[0]).children[0].value = value['dbamount'];
//Column 7 - Cash Handle Credit
				row.children().eq(6)[0].children[0].value = value['ch_crdamount'];
				(row.children().eq(6)[0]).children[0].disabled = true;
				
				row.children()[6].children[0].setAttribute("id", "chcrdamount");
				row.children()[7].children[0].setAttribute("id", "chdbamount");
				row.children()[6].children[0].setAttribute("onchange", "empwagerowupd(this)");
				row.children()[7].children[0].setAttribute("onchange", "empwagerowupd(this)");
				row.children()[6].children[0].setAttribute("onfocus", "empwagerowfocus(this)");
				row.children()[7].children[0].setAttribute("onfocus", "empwagerowfocus(this)");
//Column 8 - Cash Handle Debit
				(row.children().eq(7)[0]).children[0].disabled = true;
				(row.children().eq(7)[0]).children[0].value = value['ch_dbamount'];				
//Column 9 - Actual Salary
				row.children().eq(8).text(value['act_salary']);
				hide_flag = true;
//Column 10 - Remove
				    var col9 = row.children().eq(9);
					col9[0].style.display = "none";
					
				if (value['mth_salary'] == 0){
//Hide 'ADD NEW ROW' Button
					tabcontent[0].children[0].children[0].children[0].children[0].hidden = true;					
				}
				row.appendTo(tabcontent);
			});
			if (save_disable == false){			
//Populate all 'Emp. Wages' as F4 help for 'emp' Input in new Dynamic row
			var dynrow = $("<tr><td><select><select/></td><td></td><td></td><td><select><select/></td>" +
					"<td><input/></td><td><input/></td><td><input/></td><td><input/></td><td></td><td>" +
					"<button type='button' id='btndelempwage' class='btn btn-danger'>DELETE</button></td></tr>");
			dynrow.eq(0)[0].id = "tableview";
			dynrow[0].children[0].children[0].setAttribute("id", "empwage1");
			dynrow[0].children[0].children[0].setAttribute("onchange", "empwagenameupd(this)");
			dynrow[0].children[2].setAttribute("type", "number");
			dynrow[0].children[3].children[0].setAttribute("id", "empattd1");
			dynrow[0].children[4].children[0].setAttribute("id", "crdamount");
			dynrow[0].children[4].children[0].setAttribute("type", "number");
			dynrow[0].children[5].children[0].setAttribute("id", "dbamount");
			dynrow[0].children[5].children[0].setAttribute("type", "number");
			dynrow[0].children[6].children[0].setAttribute("id", "chcrdamount");
			dynrow[0].children[6].children[0].setAttribute("type", "number");
			dynrow[0].children[6].children[0].setAttribute("onchange", "empwagerowupd(this)");
			dynrow[0].children[6].children[0].setAttribute("onfocus", "empwagerowfocus(this)");
			dynrow[0].children[7].children[0].setAttribute("id", "chdbamount");
			dynrow[0].children[7].children[0].setAttribute("type", "number");
			dynrow[0].children[7].children[0].setAttribute("onchange", "empwagerowupd(this)");
			dynrow[0].children[7].children[0].setAttribute("onfocus", "empwagerowfocus(this)");

			dynrow[0].children[8].setAttribute("type", "number");
			dynrow[0].children[3].children[0].setAttribute("onchange", "empwagerowupd(this)");

			dynrow.appendTo(tabcontent);	
//Drop-Down for 'Emp. Wages'			
			var rowscnt = 0;
			empwage_dropdown(empwages, rowscnt);
			attd_dropdown(rowscnt)
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
				(footer.children().eq(0)[0]).children[0].setAttribute("id", "btnempwagetotalcheck");	
//Hide Column
				var footer1 = footer.children().eq(1);
				var footer2 = footer.children().eq(2);
				var footer9 = footer.children().eq(9);					

				var th = tabcontent.get()[0].getElementsByTagName("th");				
				if (hide_flag == true){
					footer1[0].style.display = "none";
					footer2[0].style.display = "none";
					footer9[0].style.display = "none";
//Hide Header Column		
					th[1].style.display = "none";
					th[2].style.display = "none";
					th[9].style.display = "none";
				}else{
//Display header/Footer Column 	
					footer1[0].style.display = ""			
					footer2[0].style.display = "";
					footer9[0].style.display = "";
					th[1].style.display = "";
					th[2].style.display = ""
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
				(savebutton.children().eq(3)[0]).children[0].setAttribute("id", "btnempwagesave");
				(savebutton.children().eq(3)[0]).children[0].setAttribute("class", "btn btn-primary");		
//ADD New Row Button
				(savebutton.children().eq(0)[0]).children[0].setAttribute("type", "button");
				(savebutton.children().eq(0)[0]).children[0].setAttribute("value", "ADD ROW");	
				(savebutton.children().eq(0)[0]).children[0].setAttribute("id", "btnaddemp");
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
				(savebutton.children().eq(4)[0]).children[0].setAttribute("id", "btnempwageedit");
				(savebutton.children().eq(4)[0]).children[0].setAttribute("class", "btn btn-primary");
					 }				 			
				}
				else{(savebutton.children().eq(4)[0]).children[0].setAttribute("hidden", "true");}
				savebutton.appendTo(tabcontent);
				
				tbodye.appendTo(tabcontent);				
		});
};
//*********************************************************************************************************
function empwage_dropdown(empwages, rowscnt){ 
	var idval = "#empwage"+(rowscnt+1)
	var $empwageselect = $(idval);
	$empwageselect.find("option").remove();
	$("<option>").val(" ").text(" ").appendTo($empwageselect);
	empwages.forEach(function(empwage, index){
		if (empwage.changedby != "X"){
		$("<option>").val(empwage.emp).text(empwage.emp).appendTo($empwageselect);
		}
	});
	};
//*********************************************************************************************************
	function attd_dropdown(rowscnt){ 
		var idval = "#empattd"+(rowscnt+1)
		var $attdselect = $(idval);
		$attdselect.find("option").remove();
		$("<option>").val(" ").text(" ").appendTo($attdselect);
		//listMops.forEach(function(mop, index){
			//$("<option>").val(mop.mop).text(mop.mop).appendTo($attdselect);
		//$("<option>").val(" ").text(" ").appendTo($attdselect);
		$("<option>").val("S").text("Single").appendTo($attdselect);
		$("<option>").val("D").text("Double").appendTo($attdselect);
		$("<option>").val("H").text("Half").appendTo($attdselect);
		//});
		};
//************************************************************************************************************************
//Button Click - Total check
	$(document).on("click", "#btnempwagetotalcheck", function(){
		$("#message").text("");
		var tabcontent = $("#emptable"), i, netdbamount = 0, crdamount,dbamount;	
		var colcnt = tabcontent[0].children[0].children[1].children.length;
		var rowscnt = tabcontent[0].rows.length - 3, attd;
		var amount, error_emp = false, error_attd = false, name, netcrdamount = 0;
		var netch_crdamount = 0, netch_dbamount = 0, ch_crdamount = parseFloat(0), ch_dbamount = parseFloat(0);
		for (i=2; i<=rowscnt; i++){
	if (i == rowscnt){
	//Display aggregate Expense Amt with Comma Notation
		tabcontent[0].rows[i].children[4].textContent = netcrdamount.toLocaleString('en-US', {minimumFractionDigits: 2});
		tabcontent[0].rows[i].children[5].textContent = netdbamount.toLocaleString('en-US', {minimumFractionDigits: 2});
		tabcontent[0].rows[i].children[6].textContent = netch_crdamount.toLocaleString('en-US', {minimumFractionDigits: 2});
		tabcontent[0].rows[i].children[7].textContent = netch_dbamount.toLocaleString('en-US', {minimumFractionDigits: 2});
		tabcontent[0].rows[i].children[4].style.color = "#000000";
		tabcontent[0].rows[i].children[5].style.color = "#000000";
		tabcontent[0].rows[i].children[6].style.color = "#000000";
		tabcontent[0].rows[i].children[7].style.color = "#000000";
	}else{		
     	if (tabcontent[0].rows[i].children[0].children[0] == undefined){
			name = tabcontent[0].rows[i].children[0].textContent;
		}else{ 
			name = tabcontent[0].rows[i].children[0].children[0].value;					
		}
     	attd = tabcontent[0].rows[i].children[3].children[0].value;
     	crdamount = tabcontent[0].rows[i].children[4].children[0].value.trim().replaceAll(",","");
		dbamount = tabcontent[0].rows[i].children[5].children[0].value.trim().replaceAll(",","");
		ch_crdamount = tabcontent[0].rows[i].children[6].children[0].value.trim().replaceAll(",","");
		ch_dbamount = tabcontent[0].rows[i].children[7].children[0].value.trim().replaceAll(",","");
		
		if (crdamount.trim() == ""){crdamount = 0}; if (dbamount.trim() == ""){dbamount = 0};
		crdamount = parseFloat(crdamount); dbamount = parseFloat(dbamount);
		if (ch_crdamount.trim() == ""){ch_crdamount = 0}; if (ch_dbamount.trim() == ""){ch_dbamount = 0};
		ch_crdamount = parseFloat(ch_crdamount); ch_dbamount = parseFloat(ch_dbamount);
		
		if (name.trim() == ""){		
			tabcontent[0].rows[i].children[0].focus();
			error_emp = true;
			break;		}
		if (attd.trim() == ""){
			tabcontent[0].rows[i].children[3].focus();
			error_attd = true;
			break;
		}
		
			netcrdamount = netcrdamount + crdamount;
			netdbamount = netdbamount + dbamount;
			netch_crdamount = netch_crdamount + ch_crdamount;
			netch_dbamount = netch_dbamount + ch_dbamount;
	}
		}
		if (error_emp == true){
			$("#message").text("Please Maintain 'Employee' for all!!!");
			document.getElementById("message").style.color = "#98052e";				
		}else if (error_attd == true){
			$("#message").text("Please Maintain 'Attendance' for respective row maintained!!!");
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
	$(document).on("click", "#btnaddemp", function(){
		//Clear Message
		$("#message").text("");
		var tabcontent = $("#emptable"), idnum;
		var uname = document.getElementById("userfirstname");
		var rowscnt = tabcontent[0].children[1].rows.length - 4;		
		if (rowscnt >= 0){
		var row = tabcontent[0].children[1].children[rowscnt];
//Validation - emp Name
		if (row.children[0].children[0] != undefined){
			if (row.children[0].children[0].value.trim() == ""){	
			$("#message").text("Maintain Employee Name before Adding New Row!!!");
			document.getElementById("message").style.color = "#98052e";
			return;	
		}}
//Validation - Attendance
		if (row.children[3].children[0] != undefined){
			if (row.children[3].children[0].value.trim() == ""){	
			$("#message").text("Maintain 'Attendance' for respective row maintained!!!");
			document.getElementById("message").style.color = "#98052e";
			return;	
		}}
//Check if all Emp. Wages added
		if (empwages.length > 0){
		var index_avbl = empwages.findIndex(empwage => (empwage.changedby != 'X'));//== null || empwage.comments.trim() == ""));
		if (index_avbl < 0){
			$("#message").text("All Emp. Wages Added!!!");
			document.getElementById("message").style.color = "#98052e";
			return;
		}}
		}
		//rowscnt = rowscnt + 1;
		var dynrow = $("<tr><td><select><select/></td><td></td><td></td><td><select><select/></td>" +
				"<td><input/></td><td><input/></td><td><input/></td><td><input/></td><td></td><td>" +
				"<button type='button' id='btndelempwage' class='btn btn-danger'>DELETE</button></td></tr>");
		dynrow.eq(0)[0].id = "tableview";
//Get Last rows maintained 'ID' of Employee & the number series, to maintain with Consecutive Series of Next row
		if (rowscnt >= 0 && empwages.length > 0 && row.children[0].children[0] != undefined){
		idnum = row.children[0].children[0].id.substr(7,10);
		}else{	idnum = 0;
		}
	    var idnum1 = new Number(idnum);
		var idval = "empwage" + (idnum1+1);
		var idattd = "empattd" + (idnum1+1);
		//dynrow.eq(0)[0].id = "tableview";		
		dynrow[0].children[0].children[0].setAttribute("id", idval);	
	    dynrow[0].children[0].children[0].setAttribute("onchange", "empwagerowupd(this)");
	    dynrow[0].children[0].children[0].setAttribute("onchange", "empwagenameupd(this)");
		//dynrow[0].children[1].style.display = "none"
		dynrow[0].children[4].children[0].setAttribute("id", "crdamount");
		dynrow[0].children[4].children[0].setAttribute("type", "number");
		dynrow[0].children[5].children[0].setAttribute("id", "dbamount");
		dynrow[0].children[5].children[0].setAttribute("type", "number");
		dynrow[0].children[6].children[0].setAttribute("id", "chcrdamount");
		dynrow[0].children[6].children[0].setAttribute("type", "number");
		dynrow[0].children[6].children[0].setAttribute("onchange", "empwagerowupd(this)");
		dynrow[0].children[6].children[0].setAttribute("onfocus", "empwagerowfocus(this)");		
		dynrow[0].children[7].children[0].setAttribute("id", "chdbamount");
		dynrow[0].children[7].children[0].setAttribute("type", "number");
		dynrow[0].children[7].children[0].setAttribute("onchange", "empwagerowupd(this)");
		dynrow[0].children[7].children[0].setAttribute("onfocus", "empwagerowfocus(this)");
		dynrow[0].children[3].children[0].setAttribute("id", idattd);
		//dynrow[0].children[5].style.display = "none"
		dynrow[0].children[8].setAttribute("type", "number");
		//dynrow[0].children[2].children[0].setAttribute("onchange", "empwagerowupd(this)");
		dynrow[0].children[3].children[0].setAttribute("onchange", "empwagerowupd(this)");
		//dynrow[0].children[6].style.display = "none"
				
//Check if 'ADD NEW ROW' Clicked after clicking 'EDIT' Button by referring last row Column 'Comments' disabled
		if (rowscnt >= 0) {
		if (row.children[0].children[0] == undefined ){
//if so then get list of Employees which are of not Saved earlier in given Date & Shift
			if (empwages.length <= 0){
			var params = {subgroup_name: $("#subgroup_name").val(),
			    	shift: $("#shift option:selected").text().trim(),
		               date: $("#txndate").val()};
			$.post("dailytxn_empwagespendajax", ($.param(params)), function(responseJson) {
//Collect all the Emp. Wages			
				$.each(responseJson, function(key, value) {
				var empwage = {};
				empwage.emp = value['name'];
				empwage.txndate = document.getElementById("txndate").value;
				empwage.labour_shift = document.getElementById("shift").value;
				empwage.crdamount = value['crdamount'];
				empwage.dbamount = value['dbamount'];
				empwage.attendance = value['attendance'];				
				empwage.actsalary = value['act_salary'];
				empwage.mthsalary = value['mth_salary']
				empwage.changedby = uname.textContent;
				empwage.changeddate = fngettoday();
				empwage.changedtime = fngetcurrenttime();
				empwages.push(empwage);	
				});
//Drop-Down for Emp. Wages		
				if (empwages.length > 0){
					empwage_dropdown(empwages, idnum1);
					attd_dropdown(idnum1);
				}else{
					$("#message").text("All Employee Wages Added!!!");
					document.getElementById("message").style.color = "#98052e";
					return;					
				}					
			});			
			}
//Hide Not Needed Columns during Edit
			dynrow[0].children[1].style.display = "none";
			dynrow[0].children[2].style.display = "none"
		}
	}
		//Insert Row at Last 
		if (rowscnt >= 0){
			dynrow.insertAfter(row);
			}else{
//New First Entry	
			var row = tabcontent[0].children[1].children[0];
			dynrow.insertBefore(row);};				
//Drop-Down for Employee / Attendance	
		if (empwages.length > 0){
			empwage_dropdown(empwages, idnum1);}
		    attd_dropdown(idnum1);
	});
//*********************************************************************************************************
	function empwagenameupd(that){
		var selrow = that.parentElement;

	//Disable Dropdown of Employee
		//selrow.parentElement.children[0].children[0].disabled = true;
//Get Selected value
		var empname = selrow.children[0].selectedOptions[0].value
//Global Array 'empwageS' holds all required details		
//Get Selected Employee Name row value
		var empwagerow = empwages.find(empwage => empwage.emp == empname);
//Update Position
		selrow.parentElement.children[1].textContent = empwagerow.position;
//Update Monthly Salary
		selrow.parentElement.children[2].textContent = (empwagerow.mthsalary).toLocaleString('en-US', {minimumFractionDigits: 2});
//Update Actual Salary if Maintained
		selrow.parentElement.children[8].textContent = (empwagerow.actsalary).toLocaleString('en-US', {minimumFractionDigits: 2});
	}

//*********************************************************************************************************
	function empwagerowfocus(that){
		var amt = parseFloat(0);
		if ($(that).val() != ""){
		amt = parseFloat($(that).val())};
		$(that).data('prev_chamt', amt);
	}
//*********************************************************************************************************
function empwagerowupd(that){
	var selrow = that.parentElement, mthsalary, actsalary, attd, amount;
	var lv_amtinhand = parseFloat(0), lv_chcrdamount = parseFloat(0), lv_chdbamount = parseFloat(0);
	
	if (selrow.parentElement.children[0].children[0] != undefined){	
	var empseltyp = "#" + selrow.parentElement.children[0].children[0].id ;
	var empwagerow = empwages.find(empwage => empwage.emp == $(empseltyp).val());
//Disable Dropdown of Employee
	selrow.parentElement.children[0].children[0].disabled = true;
//Get Attendance
	attd = selrow.parentElement.children[3].children[0].value;
//Monthly Salary	
	amount = selrow.parentElement.children[2].textContent.trim().replaceAll(",","");
	if (amount.trim() == "")
//Get Monthly Salary from obtained DB Tables	
	{ amount = empwagerow.mthsalary};
	if (amount.trim() == ""){ amount = 0};

	mthsalary = parseFloat(amount);
//Get Actual Salary
	var date = document.getElementById("txndate").value;
	actsalary = fnget_actsalary(date,mthsalary,attd);	
	
//Global Array 'empwageS' holds all required details
	//Chosen Employee Name should not list in other Drop-Down. So setting with Flag in 'changedby' 	
	empwages.find(empwage => empwage.emp == $(empseltyp).val()).changedby = "X";	
//Actual Salary Summation		 
	actsalary = actsalary + empwagerow.actsalary;
	selrow.parentElement.children[8].textContent = actsalary.toLocaleString('en-US', {maximumFractionDigits: 2});
	}
//Get Cash Handle Amounts
	amount = selrow.parentElement.children[6].children[0].value.trim().replaceAll(",","");
	if (amount.trim() == ""){ amount = 0;	}
	lv_chcrdamount = parseFloat(amount);
	amount = selrow.parentElement.children[7].children[0].value.trim().replaceAll(",","");
	if (amount.trim() == ""){ amount = 0;	}
	lv_chdbamount = parseFloat(amount);	
	
//Check & Update Amount in Hand 
//Get Previous Value
		var prev_chamt = parseFloat($(that).data('prev_chamt'));
		
		lv_amtinhand = parseFloat(that.parentElement.parentNode.parentNode.parentElement.children[0].children[0].children[3].children[0].value);
		if (that.id == "chcrdamount"){
			lv_amtinhand = (lv_amtinhand - lv_chcrdamount ) + ( prev_chamt)
		  	
		}else if (that.id == "chdbamount"){lv_amtinhand = (lv_amtinhand + lv_chdbamount) - ( prev_chamt)};
//Amount In Hand	
		that.parentElement.parentNode.parentNode.parentElement.children[0].children[0].children[3].children[0].value = lv_amtinhand;
}

//*********************************************************************************************************
//Save Emp. Wages Entry
$(document).on("click", "#btnempwagesave", function(){
	$("#message").text("");
	var empwageupds = new Array();
	var tabcontent = $("#emptable"), i;
	var rowscnt = tabcontent[0].rows.length - 4;
	var uname = document.getElementById("userfirstname"), error = false, 
	error = false, error_emp = false, error_attd = false, amount;
	for (i=2; i<=rowscnt; i++){
		var empwageupd = {};
		if (tabcontent[0].rows[i].children[0].children[0] == undefined){
			empwageupd.name = tabcontent[0].rows[i].children[0].textContent;
		}else{
			empwageupd.name = tabcontent[0].rows[i].children[0].children[0].value;}
		if (empwageupd.name.trim() == ""){
			error_name = true;
			tabcontent[0].rows[i].children[0].focus();
			break;		}
		var empwagerow = empwages.find(empwage => empwage.emp == empwageupd.name);
		empwageupd.txndate = document.getElementById("txndate").value;
		empwageupd.labour_shift = document.getElementById("shift").value;
		empwageupd.attendance = tabcontent[0].rows[i].children[3].children[0].value;
		empwageupd.crdamount = tabcontent[0].rows[i].children[4].children[0].value.trim().replaceAll(",","");
		empwageupd.dbamount = tabcontent[0].rows[i].children[5].children[0].value.trim().replaceAll(",","");
		empwageupd.ch_crdamount = tabcontent[0].rows[i].children[6].children[0].value.trim().replaceAll(",","");
		empwageupd.ch_dbamount = tabcontent[0].rows[i].children[7].children[0].value.trim().replaceAll(",","");
		//empwageupd.act_salary = tabcontent[0].rows[i].children[6].textContent.trim().replaceAll(",","");
//Salary on that Shift		
		if (empwagerow != undefined){
		empwageupd.act_salary = fnget_actsalary(empwageupd.txndate,empwagerow.mthsalary,empwageupd.attendance);
		}else{empwageupd.act_salary = parseFloat(0)};	
//Replace Null with Zero
		if (empwageupd.crdamount.trim() == ""){
			amount = 0;
			empwageupd.crdamount = parseFloat(amount);
		}
		if (empwageupd.dbamount.trim() == ""){
			amount = 0;
			empwageupd.dbamount = parseFloat(amount);
		}
		if (empwageupd.ch_crdamount.trim() == ""){
			amount = 0;
			empwageupd.ch_crdamount = parseFloat(amount);
		}
		if (empwageupd.ch_dbamount.trim() == ""){
			amount = 0;
			empwageupd.ch_dbamount = parseFloat(amount);
		}
		//empwageupd.mthsalary = tabcontent[0].rows[i].children[5].textContent.trim().replaceAll(",","");		
//Employee Name Validation
		if (empwageupd.name.trim() == ""){		
			tabcontent[0].rows[i].children[0].focus();
			error_emp = true;
			break;		}
//Attendance Validation			}
		if (empwageupd.attendance.trim() == ""){
			tabcontent[0].rows[i].children[3].focus();
			error_attd = true;
			break;		}			
		
//Disable 'EDIT' Mode 
		tabcontent[0].rows[i].children[3].children[0].disabled = true;		
		tabcontent[0].rows[i].children[4].children[0].disabled = true;	
		tabcontent[0].rows[i].children[5].children[0].disabled = true;
		tabcontent[0].rows[i].children[6].children[0].disabled = true;	
		tabcontent[0].rows[i].children[7].children[0].disabled = true;
		if (tabcontent[0].rows[i].children[8].children[0] != undefined){
		//tabcontent[0].rows[i].children[6].children[0].disabled = true;
		tabcontent[0].rows[i].children[9].children[0].disabled = true;}	
//Check whether 'Act_salary' is initial at Database Level, If so then it is first entry 
//of month. Hence While saving, update Pastdue of Previous Month in Current month first day
		//var empwagerow = empwages.find(empwage => empwage.emp == empwageupd.name);		
		if (empwagerow != undefined && empwagerow.actsalary == 0){
			empwageupd.newentry = 'X';
//Get & Set First & Last date of Previous Month
			var pdate = empwageupd.txndate;
			let retdate = fngetprevdtrange(pdate);
//Set First day of Previous Month			
			empwageupd.psdate = retdate[0];
//Set Last day of Previous Month
			empwageupd.pedate = retdate[1];
			
		}else{
			empwageupd.newentry = ' ';
			empwageupd.psdate = null;
			empwageupd.pedate = null;
		}
//Amount in Hand		
		empwageupd.amtinhand = tabcontent[0].children[0].children[0].children[3].children[0].value.trim().replaceAll(",","");
		empwageupd.changedby = uname.textContent;
		empwageupd.changeddate = fngettoday();
		empwageupd.changedtime = fngetcurrenttime();
		empwageupds.push(empwageupd);
	}
		if (error_emp == true){
			$("#message").text("Please Maintain 'Employee' for all!!!");
			document.getElementById("message").style.color = "#98052e";					
		}else if (error_attd == true){
			$("#message").text("Please Maintain 'Attendance' for respective row maintained!!!");
			document.getElementById("message").style.color = "#98052e";
		}	
	else{
		//Disable 'ADD NEW ROW' Button
		tabcontent[0].children[0].children[0].children[0].children[0].disabled = true;
		tabcontent[0].children[1].lastElementChild.firstElementChild.children[0].disabled = true;
//Save Emp. Wages details		
	$.post("dailytxn_empwagessave", { "empwageupds":JSON.stringify(empwageupds)}, function(responseText) {
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
$(document).on("click", "#btndelempwage", function(){
	that = this;
	var i = that.parentElement.parentElement.rowIndex - 2;
	var selrow = that.parentElement, lv_chcrdamt = parseFloat(0), lv_chdbamt = parseFloat(0), lv_amtinhand = parseFloat(0);
//Remove 'X' Flag in 'Changedby' when the row is deleted	
	var empwagesel = "#" + selrow.parentElement.children[0].children[0].id ;
	if ($(empwagesel).val() != " "){
		empwages.find(empwage => empwage.emp == $(empwagesel).val()).changedby = "";}
	//Adjust the Amount in Hand Accordingly 
    lv_chcrdamt = selrow.parentElement.children[6].children[0].value.trim().replaceAll(",","");
    lv_chdbamt = selrow.parentElement.children[7].children[0].value.trim().replaceAll(",","");
    if (lv_chcrdamt == ""){lv_chcrdamt = parseFloat(0)}; if(lv_chdbamt == ""){lv_chdbamt = parseFloat(0)}; 
    lv_chcrdamt = parseFloat(lv_chcrdamt); lv_chdbamt = parseFloat(lv_chdbamt);
	lv_amtinhand = parseFloat(that.parentElement.parentNode.parentNode.parentElement.children[0].children[0].children[3].children[0].value);
	lv_amtinhand = (lv_amtinhand + lv_chcrdamt) - lv_chdbamt;
//Amount In Hand	
	that.parentElement.parentNode.parentNode.parentElement.children[0].children[0].children[3].children[0].value = lv_amtinhand;
	
	that.parentElement.parentElement.parentElement.deleteRow(i);	
});
//*********************************************************************************************************
$(document).on("click", "#btnempwageedit", function(){
	$("#message").text("");
	var tabcontent = $("#emptable"), i;
	var rowscnt = tabcontent[0].rows.length - 4;
	for (i=2; i<=rowscnt; i++){
		//tabcontent[0].rows[i].children[1].children[0].disabled = false;
		//tabcontent[0].rows[i].children[3].children[0].disabled = false;
		tabcontent[0].rows[i].children[4].children[0].disabled = false;
		tabcontent[0].rows[i].children[5].children[0].disabled = false;
		tabcontent[0].rows[i].children[6].children[0].disabled = false;
		tabcontent[0].rows[i].children[7].children[0].disabled = false;
	}
//Enable Save Mode in Last row
	i = rowscnt + 3;
	tabcontent[0].rows[i].children[3].children[0].disabled = false;
//Show 'ADD NEW ROW' Button
	tabcontent[0].children[0].children[0].children[0].children[0].hidden = false;
	tabcontent[0].children[0].children[0].children[0].children[0].disabled = false;
});
//***********************************************************************************
function fnget_actsalary(fpdate,fpmthsalary,fpattd){
//Get No. of days in the transaction Month	
	var lv_days = daysInMonth(fpdate);
	//days = days + 1;
//Salary Per Day	
	var lv_actsalary = fpmthsalary / lv_days ;
//Calculate based on Shift Day
	switch(fpattd){
	//case "S":
	case "D":
		lv_actsalary = lv_actsalary * 2;
		break;
	case "H":
		lv_actsalary = lv_actsalary / 2;
		break;
	}
	lv_actsalary = parseFloat(lv_actsalary);
return lv_actsalary;		
}