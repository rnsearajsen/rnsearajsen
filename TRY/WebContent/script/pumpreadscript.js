//Pump Reading - Insert Entry
function pumpreadentry(params){
	

//*************************************************************************************************
// "useraccess" ID maintained in 'Header.jsp' file		
	var accessval = document.getElementById("useraccess");	
	var tabcontent = $("#pumpreadtable"), hide_flag = false, sprice_init = true, save_disable = false;
    var tbodys = $("<tbody>"), tbodye = $("</tbody>");   
    tabcontent[0].tHead.children[1].hidden = false; //Show Test Qty
    tabcontent[0].children[0].children[1].children[1].children[0].disabled = false;
    tabcontent[0].children[0].children[1].children[1].children[0].value = '';
    tbodys.appendTo(tabcontent);
//Call Servlet to get Pump Read entry
		$.get("pumpread_listajax", ($.param(params)), function(responseJson) {  
			$("#pumpreadtable").find("tr:gt(2)").remove();
			$.each(responseJson, function(key, value) {
				var row = $("<tr><td></td><td><input/></td><td><input/></td><td></td><td></td><td></td></tr>");
//If Product value equals 'exists', then it means Sales on entered date is already been stored in 'sales' table
//SO Disable 'Save' option
				if (value['pump_id'] == "exists"){
					save_disable = true;
				}else{
//Update Test Qty
					if (value['testqty'] != undefined){
						tabcontent[0].children[0].children[1].children[1].children[0].value = value['testqty'];
						tabcontent[0].children[0].children[1].children[1].children[0].disabled = true;
					}
				row.eq(0)[0].id = "tableview";
				row.children().eq(0).text(value['pump_id']);

				(row.children().eq(1)[0]).children[0].setAttribute("type", "number");
			//(row.children().eq(1)[0]).children[0].setAttribute("id", "pumpopen");
//Set Row Editable, if value is empty
				if (typeof value['pump_reading_open'] != "undefined" && value['pump_reading_open'] != ""){
				(row.children().eq(1)[0]).children[0].value = value['pump_reading_open'];
				(row.children().eq(1)[0]).children[0].disabled = true;
			}
				
				(row.children().eq(2)[0]).children[0].setAttribute("type", "number");
				(row.children().eq(2)[0]).children[0].setAttribute("id", "pumpclose");
//Set Row Editable, if value is empty
				if (typeof value['pump_reading_close'] != "undefined" && value['pump_reading_close'] != ""){
				(row.children().eq(2)[0]).children[0].value = value['pump_reading_close'];
				(row.children().eq(2)[0]).children[0].disabled = true;
			}

//Sales Price		      
				if (typeof value['sales_priceunit'] != "undefined" && value['sales_priceunit'] != ""){
				row.children().eq(3).text(value['sales_priceunit']);
				sprice_init = false;
				spunit = parseFloat(value['sales_priceunit']);
				sqty = parseFloat(value['sales_qty']);
				header = $("#pumpreadtable").find("tr")[2];
				header.children[3].style.display = "";
				
//Net Price				
				netprice = (spunit * sqty).toLocaleString('en-US', {minimumFractionDigits: 2});
				row.children().eq(5).text(netprice);				
				}else if (sprice_init == true){
//Hide Sales Price Column during Entry			
			  col3 = row.children().eq(3);
		      col3[0].style.display = "none";
              hide_flag = true;
				}				
//Sales Qty
				row.children().eq(4).text(value['sales_qty']);

				row.appendTo(tabcontent);
			};
			});
//Footer			
			var footer = $("<tr><td></td><td></td><td><input/></td><td></td><td></td><td></td></tr>");
			var skip = $("<tr><td><br></td><td></td><td></td><td></td><td></td><td></td></tr>");
			var savebutton = $("<tr><td></td><td></td><td><input/></td><td><input/></td><td></td><td></td></tr>");
			footer.eq(0)[0].id = "tableview";
//Button - Total Check
			(footer.children().eq(2)[0]).children[0].setAttribute("type", "button");
			(footer.children().eq(2)[0]).children[0].setAttribute("value", "Total Check");
			//(footer.children().eq(2)[0]).children[0].setAttribute("class", "btn btn-danger");
			(footer.children().eq(2)[0]).children[0].setAttribute("id", "btntotalcheck");
//Hide Column
			if (hide_flag == true){
				footer3 = footer.children().eq(3);
				footer3[0].style.display = "none";
			//var length = tabcontent[0].children[0].children[2].children.length, i;
			var th = tabcontent.get()[0].getElementsByTagName("th");
//Hide 'Sales Price'(sales_priceunit[3]) Column
				th[3].style.display = "none";
			};
			footer.appendTo(tabcontent);	//Append Footer
			skip[0].setAttribute("class", "noBorder");
			skip.appendTo(tabcontent);   
//Save Button Footer
			savebutton[0].setAttribute("class", "noBorder");
			(savebutton.children().eq(2)[0]).children[0].setAttribute("type", "button");
			(savebutton.children().eq(2)[0]).children[0].setAttribute("value", "SAVE");	
			(savebutton.children().eq(2)[0]).children[0].setAttribute("id", "btnfuelsave");
			(savebutton.children().eq(2)[0]).children[0].setAttribute("class", "btn btn-primary");
			
						
			if (save_disable == true){
				(savebutton.children().eq(2)[0]).children[0].setAttribute("disabled", "true");
//Edit Button				
// Show Edit option only for 'Super' access user
				 if (accessval.innerText == "full") {			
			//(savebutton.children().eq(3)[0]).children[0].setAttribute("disabled", "false");	
			(savebutton.children().eq(3)[0]).children[0].setAttribute("type", "button");
			(savebutton.children().eq(3)[0]).children[0].setAttribute("value", "EDIT");	
			(savebutton.children().eq(3)[0]).children[0].setAttribute("id", "btnfueledit");
			(savebutton.children().eq(3)[0]).children[0].setAttribute("class", "btn btn-primary");
				 }				 			
			}else{(savebutton.children().eq(3)[0]).children[0].setAttribute("hidden", "true");			
			}
			savebutton.appendTo(tabcontent);
			
			tbodye.appendTo(tabcontent);
		});
}
//***********************************************************************************************************************************
//***********************************************************************************************************************************
//On value Change in 'Pump close reading', calculate Sales Qty/Price
$(document).on("change","#pumpclose", function(e){
	that = this;	
	calc_sales_price_qty(that);
//$(document).on("#pumpclose","#pumpopen").change(function(){
});
//*********************************************************************************************************
//Calculate net price/Qty based on cell value change
function calc_sales_price_qty(that){
	var salesqty, price_unit, netprice;
	//Clear Message
		$("#message").text("");

		//selrow = $(e.currentTarget)[0].parentElement;
		var selrow = that.parentElement;
		var readopen = selrow.parentElement.children[1].children[0].value; //Pumpopen reading of selected row
	//Convert to Number
		var num_ropen = parseFloat(readopen);
		var num_rclose = parseFloat(that.value);
	//Validation	
		if (readopen.trim() == ""){
			$("#message").text("Enter Open Pump Reading!!!");
			 document.getElementById("message").style.color = "#98052e";
			 //selrow.parentElement.children[1].children[0].style.borderColor = "red";
			 selrow.parentElement.children[1].children[0].focus();
		}
		else if (num_ropen > num_rclose){
			$("#message").text("Open Pump Reading greater than Close Pump reading!!!");
			 document.getElementById("message").style.color = "#98052e";
			 selrow.parentElement.children[1].children[0].focus();
		}else{
	//Sales Qty	
			salesqty = num_rclose - num_ropen;
		selrow.parentElement.children[4].textContent =  salesqty.toLocaleString('en-US', {minimumFractionDigits: 2});
	//price
		price_unit = parseFloat($("#salesprice_fuel option:selected").text());
		netprice = ( salesqty * price_unit );
	//Net Price Calculation
		selrow.parentElement.children[5].textContent = netprice.toLocaleString('en-US', {minimumFractionDigits: 2});
		}	
}
//*********************************************************************************************************
function getfuel_salesprice(){
	//Add a day & get sales price, if maintained
	var lsalesdate = document.getElementById("salesdate").value;
	lsalesdate.trim();
//Convert String to Date
	var ndate = new Date(lsalesdate);
//Add a day 
		ndate.setDate( ndate.getDate() + 1);
		var sdate = dateconvert(ndate);

		var params = {product: $("#product option:selected").text(),
	            date: $("#salesdate").val(),
	            nextdate: sdate};	
		
$.post("salesprice_fuel",  ($.param(params)), function(responseJson) {   
	var $select = null;
	$select = $("#salesprice_fuel");
	$select.find("option").remove();
	 $.each(responseJson, function(index, salesprice) {
		 if (salesprice != null){
         $("<option>").val(salesprice.product).text(salesprice.sell_price).appendTo($select);
		 }
     });            
});
//return $("#salesprice_fuel option:selected").text();
};
//*********************************************************************************************************
//Button Click
$(document).on("click", "#btntotalcheck", function(){
	$("#message").text("");
	var tabcontent = $("#pumpreadtable"), i, sumqty = 0, sumprice = 0;	
	var colcnt = tabcontent[0].children[0].children[2].children.length;
	var rowscnt = tabcontent[0].rows.length - 3;
	for (i=3; i<=rowscnt; i++){
if (i == rowscnt){
//Display aggregate Qty/Net price with Comma Notation
	tabcontent[0].rows[i].children[4].textContent = sumqty.toLocaleString('en-US', {minimumFractionDigits: 2});
	tabcontent[0].rows[i].children[5].textContent = sumprice.toLocaleString('en-US', {minimumFractionDigits: 2});
	tabcontent[0].rows[i].children[4].style.color = "#000000";
	tabcontent[0].rows[i].children[5].style.color = "#000000";
}else{		
		salesqty = parseFloat(tabcontent[0].rows[i].children[4].textContent.replaceAll(",",""));
		netprice = parseFloat(tabcontent[0].rows[i].children[5].textContent.replaceAll(",",""));
		sumqty = sumqty + salesqty;
		sumprice = sumprice + netprice;
}
	}
});
//*********************************************************************************************************
$(document).on("click", "#btnfuelsave", function(){
	$("#message").text("");
	var pumpreads = new Array();
	var tabcontent = $("#pumpreadtable"), i;
	//var colcnt = tabcontent[0].children[0].children[2].children.length;
	var rowscnt = tabcontent[0].rows.length - 4;
	var uname = document.getElementById("userfirstname"), error = false, error_tqty = false;;
	for (i=3; i<=rowscnt; i++){
		var pumpread = {};
		pumpread.product = $("#product option:selected").text();
		pumpread.date = document.getElementById("salesdate").value;
		pumpread.pump_id = tabcontent[0].rows[i].children[0].textContent;
		pumpread.labour_shift = document.getElementById("shift").value;
		pumpread.sales_priceunit =	$("#salesprice_fuel option:selected").text();
		pumpread.pump_reading_open = tabcontent[0].rows[i].children[1].children[0].value;
		pumpread.pump_reading_close = tabcontent[0].rows[i].children[2].children[0].value;
		if (pumpread.pump_reading_open.trim() == "" || pumpread.pump_reading_close.trim() == ""){
			tabcontent[0].rows[i].children[2].children[0].focus();
			error = true;
			break;
		}
//Test Qty
		pumpread.testqty = document.getElementById("testqty").value;
		if (pumpread.testqty == ''){error_tqty = true; break;}
		pumpread.testqty = parseFloat(pumpread.testqty);
		pumpread.sales_qty = tabcontent[0].rows[i].children[4].textContent.replaceAll(",","").trim();
		pumpread.changedby = uname.textContent;
		pumpread.changeddate = fngettoday();
		pumpread.changedtime = fngetcurrenttime();
		pumpreads.push(pumpread);
	}
	if (error == true){
		$("#message").text("Please Maintain Pump reading for all!!!");
		document.getElementById("message").style.color = "#98052e";		
	}else if (error_tqty == true){
		$("#message").text("Please Maintain Test Qty!!!");
		document.getElementById("message").style.color = "#98052e";		
	}else{
//Save fuel with pump-reading / Sales details		
	$.post("pumpread_fuelsave", { "pumpreads":JSON.stringify(pumpreads)}, function(responseText) {
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
$(document).on("click", "#btnfueledit", function(){
	$("#message").text("");
	var tabcontent = $("#pumpreadtable"), i;
	var rowscnt = tabcontent[0].rows.length - 4;
	for (i=3; i<=rowscnt; i++){
		tabcontent[0].rows[i].children[1].children[0].disabled = false;
		tabcontent[0].rows[i].children[2].children[0].disabled = false;
	}
//Enable Save Mode in Last row
	i = rowscnt + 3;
	tabcontent[0].rows[i].children[2].children[0].disabled = false;
});
