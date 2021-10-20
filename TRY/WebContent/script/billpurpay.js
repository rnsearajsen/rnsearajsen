//***********************************************************************
//Call servlet to get Bill Details
function fbillpurpay(params) {
	var accessval = document.getElementById("useraccess");
	var uname = document.getElementById("userfirstname")
	var tabcontent = $("#billpurpaytable"), hide_flag = false, 
	save_disable = false;
//	Enable 'ADD NEW ROW' Button
	tabcontent[0].children[0].children[0].children[0].children[0].disabled = false;	
//	Update Amount in Hand
	tabcontent[0].children[0].children[0].children[3].children[0].value = g_amtinhand;				
	tabcontent[0].children[0].children[0].children[3].children[0].textContent = g_amtinhand.toLocaleString('en-US', {minimumFractionDigits: 2});
	var tbodys = $("<tbody>"), tbodye = $("</tbody>");    
	tbodys.appendTo(tabcontent);
//	Call Servlet to get Purchase Bill Data
	$.get("billpurpay_ajax", ($.param(params)), function(responseJson) {  
		$("#billpurpaytable").find("tr:gt(1)").remove();
		$.each(responseJson, function(key, value) {
			var row = $("<tr><td type='number'><input/></td><td><input type='date'/></td><td><input/></td><td type='number'><input/></td><td></td><td type='number'><input/></td><td type='number'><input/></td>" +
			"<td type='number'><input/></td><td><input/></td><td></td></tr>");
			row.eq(0)[0].id = "tableview";	
			save_disable = true;
//			Column 1 - Bill No:
			row.children().eq(0)[0].children[0].value = value['bill_no'];
			(row.children().eq(0)[0]).children[0].disabled = true;
//			Column 2 - Bill Date - Convert date format

			row.children().eq(1)[0].children[0].value = dateconvert(value['bill_date']);
			//row.children().eq(1)[0].children[0].value = value['dbamount'];
			(row.children().eq(1)[0]).children[0].disabled = true;
//			Column 3 - Bill Product
			row.children().eq(2)[0].children[0].value = value['bill_prdgrp'];			
			(row.children().eq(2)[0]).children[0].disabled = true;

//			Column 4 - Bill amt
			row.children().eq(3)[0].children[0].value = value['bill_amt'];
			(row.children().eq(3)[0]).children[0].disabled = true;
//			Column 5 - Paid Amt
			row.children().eq(4).val(value['paymnt_amt']);
			row.children().eq(4).text(value['paymnt_amt']);
//			Column 6 - Discount
			row.children().eq(5)[0].children[0].value = value['discount'];
			if (value['discount'] != 0){
				(row.children().eq(5)[0]).children[0].disabled = true;}
//			Column 7 - Latepay interest			
			row.children().eq(6)[0].children[0].value = value['interest'];
			if (value['interest'] != 0){
				(row.children().eq(6)[0]).children[0].disabled = true;}
			row.children()[7].children[0].setAttribute("onfocus", "billpurpayrowfocus(this)");
			row.children()[7].children[0].setAttribute("onchange", "billpurpayrowupd(this)");
			row.appendTo(tabcontent);
		});
		if (save_disable == false){			
//			New Dynamic row
			var dynrow = $("<tr><td type='number'><input/></td><td><input type='date'/></td><td><select><select/></td><td type='number'><input/></td><td></td><td type='number'><input/></td><td type='number'><input/></td>" +
			"<td type='number'><input/></td><td><input/></td><td><button type='button' id='btndelbillpurpay' class='btn btn-danger'>DELETE</button></td></tr>");
			dynrow.eq(0)[0].id = "tableview";
			//dynrow[0].children[0].children[0].setAttribute("id", "billpurpay1");
			//dynrow[0].children[0].children[0].setAttribute("onchange", "billpurpaynameupd(this)");			
			dynrow[0].children[2].children[0].setAttribute("id", "billprd1");

			//dynrow[0].children[3].children[0].setAttribute("id", "ch_dbamount");
			dynrow[0].children[7].children[0].setAttribute("onfocus", "billpurpayrowfocus(this)");
			dynrow[0].children[7].children[0].setAttribute("onchange", "billpurpayrowupd(this)");
			dynrow.appendTo(tabcontent);	
//			Drop-Down for 'Bill Product'			
			var rowscnt = 0;
			billprd_dropdown(rowscnt)
		}
//		Footer
		var footer = $("<tr><td><input/></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");	
		var skip = $("<tr><td><br></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
		var savebutton = $("<tr><td><input/></td><td></td><td></td><td></td><td><input/></td><td><input/></td><td></td><td></td><td></td><td></td></tr>");
		footer.eq(0)[0].id = "tableview";
		//skip.eq(0)[0].id = "tableview";
		//savebutton.eq(0)[0].id = "tableview";
//		Button - Total Check
		(footer.children().eq(0)[0]).children[0].setAttribute("type", "button");
		(footer.children().eq(0)[0]).children[0].setAttribute("value", "Total Check");
		(footer.children().eq(0)[0]).children[0].setAttribute("id", "btnbillpurpaytotalcheck");	
//		Hide Column
		var footer5 = footer.children().eq(5);					

		var th = tabcontent.get()[0].getElementsByTagName("th");				

		footer.appendTo(tabcontent);	//Append Footer
		skip[0].setAttribute("class", "noBorder");
		skip.appendTo(tabcontent);   
//		Save Button Footer
		savebutton[0].setAttribute("class", "noBorder");
		(savebutton.children().eq(4)[0]).children[0].setAttribute("type", "button");
		(savebutton.children().eq(4)[0]).children[0].setAttribute("value", "SAVE");	
		(savebutton.children().eq(4)[0]).children[0].setAttribute("id", "btnbillpurpaysave");
		(savebutton.children().eq(4)[0]).children[0].setAttribute("class", "btn btn-primary");		
//		ADD New Row Button
		(savebutton.children().eq(0)[0]).children[0].setAttribute("type", "button");
		(savebutton.children().eq(0)[0]).children[0].setAttribute("value", "ADD ROW");	
		(savebutton.children().eq(0)[0]).children[0].setAttribute("id", "btnaddinc");
		(savebutton.children().eq(0)[0]).children[0].setAttribute("class", "btn btn-success");


//		(savebutton.children().eq(4)[0]).children[0].setAttribute("disabled", "true");					

		//Edit Button				
		// Show Edit option only for 'Super' access user
		if (accessval.innerText == "full") {				
			(savebutton.children().eq(5)[0]).children[0].setAttribute("type", "button");
			(savebutton.children().eq(5)[0]).children[0].setAttribute("value", "EDIT");	
			(savebutton.children().eq(5)[0]).children[0].setAttribute("id", "btnbillpurpayedit");
			(savebutton.children().eq(5)[0]).children[0].setAttribute("class", "btn btn-primary");
		}				 			

		savebutton.appendTo(tabcontent);

		tbodye.appendTo(tabcontent);				
	});
};
//*********************************************************************************************************
function billprd_dropdown(rowscnt){ 
	var idval = "#billprd"+(rowscnt+1);
	var $billprdselect = $(idval);
	$billprdselect.find("option").remove();
	$("<option>").val(" ").text(" ").appendTo($billprdselect);
	listBillprds.forEach(function(billprd, index){
		$("<option>").val(billprd.billprd).text(billprd.billprd).appendTo($billprdselect);
	});
};
//************************************************************************************************************************
//Button Click - Total check
$(document).on("click", "#btnbillpurpaytotalcheck", function(){
	$("#message").text("");
	var tabcontent = $("#billpurpaytable"), i, netbillamt = parseFloat(0), netpaidamt = parseFloat(0), 
	netdiscount = parseFloat(0), netinterest = parseFloat(0);	
	var colcnt = tabcontent[0].children[0].children[1].children.length;
	var rowscnt = tabcontent[0].rows.length - 3, billprd;
	var amount, paidamt, billamt, discount, interest, error = false;
	for (i=2; i<=rowscnt; i++){
		if (i == rowscnt){
			//Display aggregate Amt with Comma Notation		
			tabcontent[0].rows[i].children[3].textContent = netbillamt.toLocaleString('en-US', {minimumFractionDigits: 2});
			tabcontent[0].rows[i].children[3].style.color = "#000000";
			tabcontent[0].rows[i].children[4].textContent = netpaidamt.toLocaleString('en-US', {minimumFractionDigits: 2});
			tabcontent[0].rows[i].children[4].style.color = "#000000";
			tabcontent[0].rows[i].children[5].textContent = netdiscount.toLocaleString('en-US', {minimumFractionDigits: 2});
			tabcontent[0].rows[i].children[5].style.color = "#000000";
			tabcontent[0].rows[i].children[6].textContent = netinterest.toLocaleString('en-US', {minimumFractionDigits: 2});
			tabcontent[0].rows[i].children[6].style.color = "#000000";
		}else{		

			paidamt = tabcontent[0].rows[i].children[4].textContent.trim().replaceAll(",","");

			billamt = tabcontent[0].rows[i].children[3].children[0].value;
			discount = tabcontent[0].rows[i].children[5].children[0].value;
			interest = tabcontent[0].rows[i].children[6].children[0].value; 	     		

			if (paidamt.trim() == ""){paidamt = 0};
			paidamt = parseFloat(paidamt);
			if (billamt.trim() == ""){billamt = 0};
			billamt = parseFloat(billamt);
			if (discount.trim() == ""){discount = 0};
			discount = parseFloat(discount);
			if (interest.trim() == ""){interest = 0};
			interest = parseFloat(interest);

			if (tabcontent[0].rows[i].children[0].children[0].value.trim() == '' || tabcontent[0].rows[i].children[1].children[0].value.trim() == '' 
				|| tabcontent[0].rows[i].children[2].children[0].value.trim() == '' || tabcontent[0].rows[i].children[3].children[0].value.trim() == ''){		
				tabcontent[0].rows[i].children[1].focus();
				error = true;
				break;		}
		};

		netbillamt = netbillamt + billamt;
		netpaidamt = netpaidamt = paidamt;
		netdiscount = netdiscount + discount;
		netinterest = netinterest + interest;
	}	
	if (error == true){
		$("#message").text("Please Maintain all mandatory values in each row!!!");
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
	var tabcontent = $("#billpurpaytable"), idnum;
	var uname = document.getElementById("userfirstname");
	var rowscnt = tabcontent[0].children[1].rows.length - 4;		
	if (rowscnt >= 0){
		var row = tabcontent[0].children[1].children[rowscnt];
//		Validation - Mandatory
		if (row.children[0].children[0].value.trim() == "" || row.children[1].children[0].value.trim() == "" ||
				row.children[2].children[0].value.trim() == "" || row.children[3].children[0].value.trim() == "") {	
			$("#message").text("Please Maintain all mandatory values in each row!!!");
			document.getElementById("message").style.color = "#98052e";
			return;			}
	}
	//rowscnt = rowscnt + 1;
	var dynrow = $("<tr><td type='number'><input/></td><td><input type='date'/></td><td><select><select/></td><td type='number'><input/></td><td></td><td type='number'><input/></td><td type='number'><input/></td>" +
	"<td type='number'><input/></td><td><input/></td><td><button type='button' id='btndelbillpurpay' class='btn btn-danger'>DELETE</button></td></tr>");
	dynrow.eq(0)[0].id = "tableview";
//	Get Last rows maintained 'ID' & the number series, to maintain with Consecutive Series of Next row
	if (rowscnt >= 0 && row.children[2].children[0] != undefined){
		idnum = row.children[2].children[0].id.substr(6,10);
	}else{	idnum = 0;
	}
	var idnum1 = new Number(idnum);
	var idbillprd = "billprd" + (idnum1+1);

	dynrow[0].children[2].children[0].setAttribute("id", idbillprd);

	dynrow[0].children[7].children[0].setAttribute("onfocus", "billpurpayrowfocus(this)");	
	dynrow[0].children[7].children[0].setAttribute("onchange", "billpurpayrowupd(this)");
	//Insert Row at Last 
	if (rowscnt >= 0){
		dynrow.insertAfter(row);
	}else{
//		New First Entry	
		var row = tabcontent[0].children[1].children[0];
		dynrow.insertBefore(row);};				
//		Drop-Down for billprd
		billprd_dropdown(idnum1);
});
//*********************************************************************************************************
function billpurpayrowfocus(that){
	var amt = parseFloat(0);
	if ($(that).val() != ""){
		amt = parseFloat($(that).val())};
		$(that).data('prev_chamt', amt);
}
//*********************************************************************************************************
function billpurpayrowupd(that){
	$("#message").text("");
	var selrow = that.parentElement, lv_totpaid = parseFloat(0), lv_actbill = parseFloat(0);
//	Disable Payment Amount
	var paymnt_amt = selrow.parentElement.children[7].children[0].value.trim().replaceAll(",","");
	var bill_amt = selrow.parentElement.children[3].children[0].value.trim().replaceAll(",","");
	var paid_amt = selrow.parentElement.children[4].textContent.trim().replaceAll(",","");
	var discount = selrow.parentElement.children[5].children[0].value.trim().replaceAll(",","");
	var interest = selrow.parentElement.children[6].children[0].value.trim().replaceAll(",","");
	if (bill_amt == ''){bill_amt = 0}; bill_amt = parseFloat(bill_amt);
	if (paid_amt == ''){paid_amt = 0}; paid_amt = parseFloat(paid_amt);
	if (discount == ''){discount = 0}; discount = parseFloat(discount);
	if (interest == ''){interest = 0}; interest = parseFloat(interest);
	if (paymnt_amt == ''){paymnt_amt = 0};	paymnt_amt = parseFloat(paymnt_amt);
	lv_actbill = ( bill_amt + interest ) - discount;
	lv_totpaid = paid_amt + paymnt_amt;
//Validation 
	if(lv_totpaid > lv_actbill){
		$("#message").text("Paid Amount is Greater than Actual Bill!!!");
		document.getElementById("message").style.color = "#98052e";				
	}
	if(lv_totpaid == lv_actbill){
//		Disable Payment Amount
		//selrow.parentElement.children[7].children[0].disabled = true;	
	}
//	Get Previous Value
	var lv_pre_amt = parseFloat($(that).data('prev_chamt'));	
//	Check & Update Amount for 'Cash_Handle Income'
	var lv_amtinhand = parseFloat(that.parentElement.parentNode.parentNode.parentElement.children[0].children[0].children[3].children[0].value);
	//if (that.id == "ch_dbamount"){
	lv_amtinhand = (lv_amtinhand + lv_pre_amt) - paymnt_amt; //}
//	Amount In Hand	
	that.parentElement.parentNode.parentNode.parentElement.children[0].children[0].children[3].children[0].value = lv_amtinhand;	
}

//*********************************************************************************************************
//Save 'Other Income' Entry
$(document).on("click", "#btnbillpurpaysave", function(){
	$("#message").text("");
	var billpurpayupds = new Array(); var lv_paidamt = 0;
	var tabcontent = $("#billpurpaytable"), i;
	var rowscnt = tabcontent[0].rows.length - 4;
	var uname = document.getElementById("userfirstname"), 
	error = false,error_amt = false, amount;
	for (i=2; i<=rowscnt; i++){
		var billpurpayupd = {};

		billpurpayupd.bill_no = tabcontent[0].rows[i].children[0].children[0].value.trim();
		billpurpayupd.bill_date = tabcontent[0].rows[i].children[1].children[0].value;
		billpurpayupd.bill_prdgrp = tabcontent[0].rows[i].children[2].children[0].value.trim();
		billpurpayupd.bill_amt = tabcontent[0].rows[i].children[3].children[0].value.trim().replaceAll(",","");
		if (billpurpayupd.bill_amt == '') {billpurpayupd.bill_amt = parseFloat(0)}else{
			billpurpayupd.bill_amt = parseFloat(billpurpayupd.bill_amt)};
			if (tabcontent[0].rows[i].children[4].value != undefined){			
				lv_paidamt = tabcontent[0].rows[i].children[4].value.trim().replaceAll(",","");		}
			var lv_paymntamt = tabcontent[0].rows[i].children[7].children[0].value.trim().replaceAll(",","");
			if (lv_paidamt == ''){lv_paidamt = parseFloat(0)}else{ lv_paidamt = parseFloat(lv_paidamt)};
			if (lv_paymntamt == ''){lv_paymntamt = parseFloat(0)}else{ lv_paymntamt = parseFloat(lv_paymntamt)};
			billpurpayupd.paymnt_amt = lv_paidamt + lv_paymntamt;
			if (billpurpayupd.paymnt_amt == ''){billpurpayupd.paymnt_amt = 0};
			billpurpayupd.paymnt_amt = parseFloat(billpurpayupd.paymnt_amt);
			billpurpayupd.discount = tabcontent[0].rows[i].children[5].children[0].value.trim().replaceAll(",","");
			billpurpayupd.interest = tabcontent[0].rows[i].children[6].children[0].value.trim().replaceAll(",","");
			if (billpurpayupd.discount == ''){billpurpayupd.discount = parseFloat(0)}else{
				billpurpayupd.discount = parseFloat(billpurpayupd.discount)};
				if (billpurpayupd.interest == ''){billpurpayupd.interest = parseFloat(0)}else{
					billpurpayupd.interest = parseFloat(billpurpayupd.interest)};
					var lv_actbill = ( billpurpayupd.bill_amt + billpurpayupd.interest ) - billpurpayupd.discount;
					if (lv_actbill == ''){lv_actbill = parseFloat(0)}else{lv_actbill = parseFloat(lv_actbill)};
					billpurpayupd.paymnt_date =  fngettoday();
					if (billpurpayupd.paymnt_amt != 0 && billpurpayupd.paymnt_amt >= lv_actbill){
						billpurpayupd.paid = 'X'} else {billpurpayupd.paid = ''};

//						Amount Validation
						if (billpurpayupd.paymnt_amt > lv_actbill){
							error_amt = true;
							break;	
						}
						if (billpurpayupd.bill_no == '' || billpurpayupd.bill_date == '' || billpurpayupd.bill_prdgrp == '' ||
								billpurpayupd.bill_amt == 0){
							error = true;
							break;		}			
//						Amount in Hand
						billpurpayupd.amtinhand = tabcontent[0].children[0].children[0].children[3].children[0].value.trim().replaceAll(",","");
						if (billpurpayupd.amtinhand == ''){billpurpayupd.amtinhand = pareFloat(0)}else{
							billpurpayupd.amtinhand = parseFloat(billpurpayupd.amtinhand)}
//						Disable 'EDIT' Mode 
						tabcontent[0].rows[i].children[0].children[0].disabled = true;		
						tabcontent[0].rows[i].children[1].children[0].disabled = true;	
						tabcontent[0].rows[i].children[2].children[0].disabled = true;
						tabcontent[0].rows[i].children[3].children[0].disabled = true;
						if (tabcontent[0].rows[i].children[9].children[0] != undefined){
						tabcontent[0].rows[i].children[9].children[0].disabled = true;}
//						Update Old Date in Txn Date
						billpurpayupd.txndate = dateconvert('01-01-1900');
						billpurpayupd.changedby = uname.textContent;
						billpurpayupd.changeddate = fngettoday();
						billpurpayupd.changedtime = fngetcurrenttime();
						billpurpayupds.push(billpurpayupd);
	}
	if ( error == true ){
		$("#message").text("Please Maintain all mandatory values in each row!!!");
		document.getElementById("message").style.color = "#98052e";
	}else if ( error_amt == true ){
		$("#message").text("Paid Amount is Greater than Actual Bill, Correct it!!!");
		document.getElementById("message").style.color = "#98052e";	
	}else{
		//Disable 'ADD NEW ROW' Button
		tabcontent[0].children[0].children[0].children[0].children[0].disabled = true;
		tabcontent[0].children[1].lastElementChild.firstElementChild.children[0].disabled = true;
//		Save Purchase Bill details		
		$.post("billpurpayssave", { "billpurpayupds":JSON.stringify(billpurpayupds)}, function(responseText) {
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
$(document).on("click", "#btndelbillpurpay", function(){
	that = this;
	var i = that.parentElement.parentElement.rowIndex - 2;
	var selrow = that.parentElement, lv_incamt, lv_amtinhand;
//	Adjust the Amount in Hand Accordingly 
	lv_incamt = selrow.parentElement.children[7].children[0].value.trim().replaceAll(",","");
	if (lv_incamt == ""){lv_incamt = 0};
	lv_incamt  = parseFloat(lv_incamt);
	lv_amtinhand = parseFloat(that.parentElement.parentNode.parentNode.parentElement.children[0].children[0].children[3].children[0].value);
	lv_amtinhand = lv_amtinhand + lv_incamt;
//	Amount In Hand	
	that.parentElement.parentNode.parentNode.parentElement.children[0].children[0].children[3].children[0].value = lv_amtinhand;

	that.parentElement.parentElement.parentElement.deleteRow(i);	
});
//*********************************************************************************************************
$(document).on("click", "#btnbillpurpayedit", function(){
	$("#message").text("");
	var tabcontent = $("#billpurpaytable"), i;
	var rowscnt = tabcontent[0].rows.length - 4;
	for (i=2; i<=rowscnt; i++){
		tabcontent[0].rows[i].children[3].children[0].disabled = false;
		tabcontent[0].rows[i].children[5].children[0].disabled = false;
		tabcontent[0].rows[i].children[6].children[0].disabled = false;
		tabcontent[0].rows[i].children[7].children[0].disabled = false;
	}
//	Enable Save Mode in Last row
	i = rowscnt + 3;
	tabcontent[0].rows[i].children[4].children[0].disabled = false;
//	Show 'ADD NEW ROW' Button
	tabcontent[0].children[0].children[0].children[0].children[0].hidden = false;
	tabcontent[0].children[0].children[0].children[0].children[0].disabled = false;
});