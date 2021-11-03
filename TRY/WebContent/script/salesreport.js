//*****************************************************
//Report Initialization
function rptinitial(){
	$("#ddyear").hide();
	$("#salesrptable").hide();
}
//*****************************************************
//Report Change Update of Input Selection
function rptypechg(that){
	if (that.value == "monthly"){
		$("#ddmonth").show();	
		$("#ddyear").hide();
	}else if(that.value == "yearly"){
		$("#ddyear").show();
		$("#ddmonth").hide();
	}
}
//*****************************************************
//sales Report
$(document).on("click", "#btnsalesrpt", function(){
	$("#salesrptable").show();
	var mthyear, rptype, year, salesprd = null, coltab = parseFloat(0), rowcnt = parseFloat(0);
	var tabcontent = $("#salesrptable"), col = parseFloat(0), salesid, csalesid = "salesprd";
	var prmonth = '00', pryear = '0001', mthdaterng, period; 
	var sgname = $("#subgroup_name").val(); var i, inc;
	rptype = document.getElementById("report_type");
	if (rptype.value == "monthly" || rptype.value == "daily"){
		mthyear = document.getElementById("ddmonth");
//		Get first & Last date of month			
		mthdaterng = fngetmthdtrange(mthyear.value);
		if (rptype.value == "monthly"){
			//Get Month & Year
			let prmthyear = fngetmthyear(mthyear.value)
			prmonth = prmthyear[0]; pryear = prmthyear[1];
		}
	}else if (rptype.value == "yearly"){
		year = document.getElementById("ddyear");	
		pryear = year.selectedOptions[0].text;
		var today = fngettoday();
//		Dummy		
		mthdaterng = fngetmthdtrange(today);
	}

	var params = {sdate: mthdaterng[0], edate: mthdaterng[1],
			subgroup_name: $("#subgroup_name").val(), rptype: rptype.value,
			month: prmonth, year: pryear};
	//Call Servlet to get Sales Report
	$.get("Salesrpt_ajax", ($.param(params)), function(responseJson) {
		$("#salesrptable").find("tr:gt(1)").remove(); //Remove Rows
//		Hide Header Columns
		if (sgname.toLocaleUpperCase() == 'FUEL'){
			$("#salesrptable").find("th:gt(7)").hide(); //Remove Header Columns
		}else if (sgname.toLocaleUpperCase() == 'PACKET_OIL'){
			$("#salesrptable").find("th:gt(7)").show();
			$("#salesrptable").find("th:gt(11)").hide();
		}else if (sgname.toLocaleUpperCase() == 'ENGINE_OIL'){
			$("#salesrptable").find("th:gt(7)").show();			
		}
		$.each(responseJson, function(key, value) {
			var dynrow = $("<tr><td></td><td></td><td></td><td hidden=true></td>" +
					"<td hidden=true></td><td hidden=true></td><td hidden=true></td><td hidden=true></td>" +
			"<td hidden=true></td><td hidden=true></td><td hidden=true></td><td hidden=true></td></tr>");
			dynrow.eq(0)[0].id = "tableview";
			var rowupd = tabcontent[0].children;
			if (salesprd == null){ //First Entry
				document.getElementById("salesprd").textContent = value['product'];
				salesprd = value['product'];
			}
			if (salesprd != value['product']){	
				salesprd = value['product'];
				coltab = coltab + 1; col = col + 4;
				rowcnt = parseFloat(0);
				salesid = csalesid.concat(coltab);
				document.getElementById(salesid).textContent = value['product'];
//				tabcontent[0].children[0].children[1].children[col].textContent = value['product']; 
			}
//			Get Period display based on report Type			
			period = fngetperiod(value['salesdate'],rptype.value)
			if (coltab == 0){ 
//				First Product Table						
				dynrow.children().eq(col).text(period);
				dynrow.children().eq(col)[0].style.backgroundColor = '#ffffbf';
				dynrow.children().eq(col)[0].style.color = 'black';
				dynrow.children().eq(col+1).text(value['sales_qty']);
				dynrow.children().eq(col+2).text(value['total_sales_price'].toLocaleString('en-US', {maximumFractionDigits: 2}));
//				Only for fuel - Show 'Test Qty'						
				if (sgname.toLocaleUpperCase() == 'FUEL'){
//					Header							
					rowupd[0].children[1].children[col+3].hidden = false;
//					Item							
					dynrow.children().eq(col+3).text(value['testqty']);
					dynrow.children().eq(col+3)[0].hidden = false;
				}else{
					//Header							
					rowupd[0].children[1].children[col+3].hidden = true;
					//Item							
					dynrow.children().eq(col+3).text('');
					dynrow.children().eq(col+3)[0].hidden = true;
				}
				dynrow.appendTo(tabcontent);
			}else {
//				Row Update for next successive Product Table						
				//Header							
				rowupd[0].children[1].children[col].hidden = false;
				rowupd[0].children[1].children[col+1].hidden = false;
				rowupd[0].children[1].children[col+2].hidden = false;
//				Only for fuel - Show 'Test Qty'						
				if (sgname.toLocaleUpperCase() == 'FUEL'){						
					rowupd[0].children[1].children[col+3].hidden = false;
				}else{
					rowupd[0].children[1].children[col+3].hidden = true;	
				}						
				rowcnt = rowcnt + 1;
				if (rowupd[rowcnt] != undefined){
//					Item							
					rowupd[rowcnt].children[col].textContent = period;							
					rowupd[rowcnt].children[col].style.backgroundColor = '#ffffbf';
					rowupd[rowcnt].children[col].style.color = 'black';
					rowupd[rowcnt].children[col].hidden = false;
					rowupd[rowcnt].children[col+1].textContent = value['sales_qty'];
					rowupd[rowcnt].children[col+1].hidden = false;
					rowupd[rowcnt].children[col+2].textContent = value['total_sales_price'].toLocaleString('en-US', {maximumFractionDigits: 2});
					rowupd[rowcnt].children[col+2].hidden = false;
					//Only for fuel - Show 'Test Qty'			
					if (sgname.toLocaleUpperCase() == 'FUEL'){							
						rowupd[rowcnt].children[col+3].textContent = value['testqty'];
						rowupd[rowcnt].children[col+3].hidden = false;
					}else{
						rowupd[rowcnt].children[col+3].hidden = true;	
					}
				}else {

					dynrow.children().eq(col).text(period);
					dynrow.children().eq(col)[0].style.backgroundColor = '#ffffbf';
					dynrow.children().eq(col)[0].style.color = 'black';
					dynrow.children().eq(col)[0].hidden = false;
					dynrow.children().eq(col+1).text(value['sales_qty']);
					dynrow.children().eq(col+1)[0].hidden = false;
					dynrow.children().eq(col+2).text(value['total_sales_price'].toLocaleString('en-US', {maximumFractionDigits: 2}));
					dynrow.children().eq(col+2)[0].hidden = false;
					//Only for fuel - Show 'Test Qty'			
					if (sgname.toLocaleUpperCase() == 'FUEL'){
						//Item								
						dynrow.children().eq(col+3).text(value['testqty']);
						dynrow.children().eq(col+3)[0].hidden = false;
//						Header								
						rowupd[0].children[1].children[col+3].hidden = false;
					}else{
						//Item
						dynrow.children().eq(col+3).text('');
						dynrow.children().eq(col+3)[0].hidden = true;
						//Header
						rowupd[0].children[1].children[col+3].hidden = true;
					}
					inc = 0;
					for (i = 0; i < col; i++) {// Previous Columns are made Visible
						
						if (inc == 3){// Third Column(Testqty) is hidden for Engine Oil
							inc = -1;
							dynrow.children().eq(i)[0].hidden = true;
						}else{
							dynrow.children().eq(i)[0].hidden = false;}
						inc = inc + 1;
					}
					dynrow.appendTo(tabcontent);
				}
			}
		});
	});	
})
//*****************************************************************************
function fngetperiod(fpdate, fprptype){
	if	(fprptype == 'daily'){
		return fpdate;
	}else{
		var date = new Date(fpdate);
		var month = date.getMonth() + 1;
		var year = date.getFullYear();	
		month = date.toLocaleString('default', { month: 'long' });		
		var retmonth = month+','+year;
		return retmonth;
	}
}
