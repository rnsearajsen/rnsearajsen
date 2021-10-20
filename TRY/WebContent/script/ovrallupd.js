//***********************************************************************
//Call servlet to get 'Overall Update' Details
function fovrallupd(params){
	var accessval = document.getElementById("useraccess");
	var uname = document.getElementById("userfirstname"), lv_collection_amt;
	var tabcontent = $("#ovratable");	var qty, amount = 0, totsales = 0, totsum = 0;	
	var tbodys = $("<tbody>"), tbodye = $("</tbody>");var lv_difference = 0;
	tbodys.appendTo(tabcontent);
//	Initialize	
	tabcontent[0].children[0].children[0].children[1].children[0].value = '';
	tabcontent[0].children[0].children[0].children[1].children[0].disabled = false;
//	Call Servlet to get Overall Data
	$.get("ovraupd_ajax", ($.param(params)), function(responseJson) {  
		$("#ovratable").find("tr:gt(1)").remove();
		$.each(responseJson, function(key, value) {	
			var row = $("<tr><td></td><td type='number'></td><td type='number'></td><td></td><td></td><td type='number'></td></tr>");			
			row.eq(0)[0].id = "tableview";			
//			Update Amount in Hand										
			tabcontent[0].children[0].children[0].children[5].children[0].value = (value['amtinhand']);
//			Update Collection Amt
			lv_collection_amt = value['collection_amt'];
			lv_difference = value['difference'];
			if (lv_difference == ''){lv_difference = parseFloat(0)};
			if (lv_collection_amt == '') {lv_collection_amt = parseFloat(0)};
			if (lv_collection_amt != 0){
				tabcontent[0].children[0].children[0].children[1].children[0].setAttribute("onfocus", "ovrallrowfocus(this)");
				tabcontent[0].children[0].children[0].children[1].children[0].value = (value['collection_amt']);								
			}
//Actual Collection Amt - Disabled by default			
			tabcontent[0].children[0].children[0].children[1].children[0].disabled = true;
//			Column 1 - Products			
			row.children().eq(0).text(value['product']);
			row.children().eq(0)[0].style.color = 'black';
			row.children().eq(0)[0].style.backgroundColor = '#ffffbf'
				//row.children().eq(0)[0].bgColor = '#ffffbf';
//				Column 2 - Total Sales Qty
				qty = value['sales_qty'];
			qty = qty.toLocaleString('en-US', {maximumFractionDigits: 2});			
			row.children().eq(1).text(qty);
//			Column 3 - Total Amt		
			amount = value['totsales_price'];
			totsales = totsales + amount;
			amount = amount.toLocaleString('en-US', {maximumFractionDigits: 2});
			row.children().eq(2).text(amount);
			row.children().eq(2)[0].align = "right";
//			Column 4 
			row.children().eq(3)[0].style.border = 'none';
//			Column 5 - Total Expenses
			row.children().eq(4).text(value['txndet']);
			row.children().eq(4)[0].style.color = 'black';
			row.children().eq(4)[0].style.backgroundColor = '#ffffbf'			
//				Column 6 - Total Expense Amt
				amount = value['sumamt'];
			totsum = totsum + amount;
			amount = amount.toLocaleString('en-US', {maximumFractionDigits: 2});
			row.children().eq(5).text(amount);
			row.children().eq(5)[0].align = "right";
			row.appendTo(tabcontent);
		});			
//		*********************Last ROW**********************************************
		var row = $("<tr><td></td><td type='number'></td><td type='number'></td><td></td><td></td><td type='number'></td></tr>");
		row.eq(0)[0].id = "tableview";
//		Column 1 - Sub-Total
		row.children().eq(0).text("Sub Total");
		row.children().eq(0)[0].style.color = 'black';
		row.children().eq(0)[0].style.backgroundColor = '#bbbbbb'
//			Column 2 - Total Amount
			amount = totsales.toLocaleString('en-US', {maximumFractionDigits: 2});
		row.children().eq(2).text(amount);
		row.children().eq(2)[0].align = "right";
		row.children().eq(2)[0].style.color = 'black';
		row.children().eq(2)[0].style.backgroundColor = '#e5e5e5'
//			Column 5 -  
			row.children().eq(4).text("Aggregate Sum");
		row.children().eq(4)[0].style.color = 'black';
		row.children().eq(4)[0].style.backgroundColor = '#bbbbbb'
//			Column 6 - 
			amount = totsum.toLocaleString('en-US', {maximumFractionDigits: 2});
		row.children().eq(5).text(amount);
		row.children().eq(5)[0].align = "right";
		row.children().eq(5)[0].style.color = 'black';
		row.children().eq(5)[0].style.backgroundColor = '#e5e5e5'
			row.appendTo(tabcontent);			
//		*******************************************************************				 			 
//		Footer row
		var frow = $("<tr><td></td><td type='number'></td><td><input/></td><td></td><td></td><td type='number'></td></tr>");
		var skip = $("<tr><td><br></td><td></td><td></td><td></td><td></td><td></td></tr>");

		frow.eq(0)[0].id = "tableview";
		//skip.eq(0)[0].id = "tableview";
		skip[0].setAttribute("class", "noBorder");
		skip.appendTo(tabcontent);

		//Column 1
		frow.children().eq(0).text("Expected Collection");
		frow.children().eq(0)[0].style.color = 'black';
		frow.children().eq(0)[0].style.backgroundColor = '#bbbbbb'

			amount = totsales + totsum;
		amount = amount.toLocaleString('en-US', {maximumFractionDigits: 2});
		//Column 2 - Amount
		frow.children().eq(1).text(amount);	 
		frow.children().eq(1)[0].align = "right";
		frow.children().eq(1)[0].style.color = 'black';
		frow.children().eq(1)[0].style.backgroundColor = '#e5e5e5'
			frow.children().eq(1)[0].style.border = 'double';

//		Column 3 - Edit Button				
//		Show Edit option only for 'Super' access user
		if (accessval.innerText == "full") {	
			(frow.children().eq(2)[0]).children[0].setAttribute("type", "button");
			(frow.children().eq(2)[0]).children[0].setAttribute("value", "EDIT");
			(frow.children().eq(2)[0]).children[0].setAttribute("id", "btnovralledit");
			(frow.children().eq(2)[0]).children[0].setAttribute("class", "btn btn-primary");
		}

//		Column 5 
		frow.children().eq(4).text("Difference");
		frow.children().eq(4)[0].style.color = 'black';
		frow.children().eq(4)[0].style.backgroundColor = '#bbbbbb'
//			Column 6 - Amount	 
			lv_difference = lv_collection_amt - (totsales + totsum);
		frow.children().eq(5).text(lv_difference);
		frow.children().eq(5)[0].value = lv_difference;
		frow.children().eq(5)[0].align = "right";
		frow.children().eq(5)[0].style.color = 'black';
		frow.children().eq(5)[0].style.backgroundColor = '#e5e5e5'	 
			frow.children().eq(5)[0].style.border = 'double'; 

		frow.appendTo(tabcontent);

		tbodye.appendTo(tabcontent);
	});	
};
//*********************************************************************************************************
function ovrallrowfocus(that){
	var amt = parseFloat(0);
	if ($(that).val() != ""){
		amt = parseFloat($(that).val())};
		$(that).data('prev_chamt', amt);	
}
//*********************************************************************************************************
function ovrallrowupd(that){
	var selrow = that.parentElement, lv_difference, lv_expectamt, lv_actcollect;
	var selhrow = selrow.parentElement.parentElement.parentElement;
	lv_expectamt = selrow.parentElement.parentElement.parentElement.children[1].children[8].children[1].textContent.trim().replaceAll(",","");
	lv_difference = that.value - lv_expectamt;
	lv_actcollect = that.value;
	if (lv_actcollect == ''){lv_actcollect = parseFloat(0)}else{
		lv_actcollect = parseFloat(lv_actcollect);
	}
	
	selrow.parentElement.parentElement.parentElement.children[1].children[8].children[5].textContent = lv_difference.toLocaleString('en-US', {minimumFractionDigits: 2});
	selrow.parentElement.parentElement.parentElement.children[1].children[8].children[5].value = lv_difference;
//	Check & Update Amount in Hand 	
	var lv_amtinhand = selhrow.children[0].children[0].children[5].children[0].value.trim().replaceAll(",","");
	if (lv_amtinhand == ''){lv_amtinhand = parseFloat(0)}
	else{lv_amtinhand = parseFloat(lv_amtinhand)};
//	Get Previous Value
	var prev_chamt = parseFloat($(that).data('prev_chamt'));		
	lv_amtinhand = ( lv_amtinhand + lv_actcollect ) - prev_chamt;
	
	selhrow.children[0].children[0].children[5].children[0].value = lv_amtinhand;						
}

//************************************************************************************************************************
//Button Click - Update Collection Amount
$(document).on("click", "#btnamtupd", function(){
	$("#message").text("");
	var cashupds = new Array();
	var tabcontent = $("#ovratable");
	var uname = document.getElementById("userfirstname");
	var cashupd = {};
//	Amount in Hand		
	cashupd.aggramt = tabcontent[0].children[0].children[0].children[5].children[0].value.trim().replaceAll(",","");
	if (cashupd.aggramt == ''){cashupd.aggramt = parseFloat(0)}
	else{cashupd.aggramt = parseFloat(cashupd.aggramt)};

//	Get Actual Collection Amount
	cashupd.collection_amt = tabcontent[0].rows[0].children[1].children[0].value;
	if (cashupd.collection_amt == '' || cashupd.collection_amt == 0){
		$("#message").text("Please Maintain Actual Collection amount!!!");
		document.getElementById("message").style.color = "#98052e";
	}else{
		cashupd.collection_amt = parseFloat(cashupd.collection_amt);
		tabcontent[0].rows[0].children[1].children[0].disabled = true;

		//cashupd.aggramt = ( cashupd.aggramt + cashupd.collection_amt ) - prev_chamt;
		cashupd.difference = tabcontent[0].rows[10].children[5].value;
		cashupd.txndate = document.getElementById("txndate").value;
		cashupd.labour_shift = document.getElementById("shift").value;
		cashupd.changedby = uname.textContent;
		cashupd.changeddate = fngettoday();
		cashupd.changedtime = fngetcurrenttime();
		cashupds.push(cashupd);
//		Save Cash Txn		
		$.post("dailytxn_cashsave", { "cashupds":JSON.stringify(cashupds)}, function(responseText) {
			$("#message").text(responseText);
			document.getElementById("message").style.color = "#98052e";
		});
	}
});
//************************************************************************************************************************	
$(document).on("click", "#btnovralledit", function(){
	var tabcontent = $("#ovratable");
	tabcontent[0].rows[0].children[1].children[0].disabled = false;
});

//************************************************************************************************************************