//Insert an entry in table on button click
$(document).on("click", "#btnsalesinsert", function(){
//get Date/Time
getdatetime();
	var params = {subgroup_name: $("#subgroup_name").val(),
		      salesdate: $("#todaydate").val(),
		      product: {product : $("#product option:selected").text()}.product,
		      sales_qty: $("#sales_qty").val().trim().replaceAll(",",""), //remove Commas
		      stock_qty: $("#sales_qty").val().trim().replaceAll(",",""), //remove Commas
      uom: {product : $("#product option:selected").val()}.product,
      price_unit: $("#price_total").val().trim().replaceAll(",","") /  $("#sales_qty").val().trim().replaceAll(",",""),
      price_total: $("#price_total").val().trim().replaceAll(",",""),
      taxpercent_total: $("#taxpercent_total").val(),
      taxamt_total: (($("#taxpercent_total").val() * $("#price_total").val().trim().replaceAll(",","")) / 100 ), 
      comments: $("#comments").val().trim(),
      changedby:$("#changedby").val(),
      changeddate:$("#changeddate").val(),
      changedtime:$("#changedtime").val()};

if (params.salesdate > document.getElementById("todaydate").max) {
	$("#message").text("Error!!!Sales Date CANNOT be greater than Today");
	document.getElementById("message").style.color = "#98052e";
	return;
}
//postajax
that = this;
fnpostajax(that,params,"salestable");
	});
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
$(document).on("click", "#btnsalesentry", function(){
	$("#message").text("");
//Fuel Pump reading	
	if ( $("#subgroup_name").val().toLocaleLowerCase() == "fuel" ) {
		var params = {subgroup_name: $("#subgroup_name").val(),
				               date: $("#salesdate").val()};
//Call Servlet to get Product list of group: "FUEL"
		$.get("product_list",  ($.param(params)), function(responseJson) {   
			var $select = $("#product");
			$select.find("option").remove();
			 $.each(responseJson, function(index, product) {
	             $("<option>").val(product.uom).text(product.product).appendTo($select);
	         });                   	
		});    				

//Call Servlet to get Sales price of fuel
//getfuel_salesprice(); 
		
//Show PumpREad Entry Table		
		$("#pumpreadtable").show();	
//Hide
		var tabcontent2 = $("#pumpreadtable");
		tabcontent2[0].tHead.children[1].hidden = true; //Hide Test Qty
		if (tabcontent2[0].tBodies[0] != undefined){
		tabcontent2[0].tBodies[0].hidden = true;} //Hide Table Body	
		
		$("#pktoiltable").hide();
		$("#engoiltable").hide();
		$("#salesprice_fuel").hide();
		$("#btnfuelgo").hide();
		$("#tableview").hide();
		//var tabcontent1 = $("#pumpreadtable");
		//tabcontent1[0].tBodies[0].hidden = true;
	};
//Packet Oil
	if ( $("#subgroup_name").val().toLocaleLowerCase() == "packet_oil" ) {
		var params = {subgroup_name: $("#subgroup_name").val(),
			    	shift: $("#shift option:selected").text().trim(),
	               date: $("#salesdate").val()};
//Call servlet to get Product of Packet oil & its stock details		
		pktoil_sales(params);	
		$("#pktoiltable").show();
//Hide
		$("#engoiltable").hide();
	};
//Engine Oil
	if ( $("#subgroup_name").val().toLocaleLowerCase() == "engine_oil" ) {
		var params = {subgroup_name: $("#subgroup_name").val(),
			    	shift: $("#shift option:selected").text().trim(),
	               date: $("#salesdate").val()};
//Call servlet to get Product of Packet oil & its stock details		
		engoil_sales(params);	
		$("#engoiltable").show();
//Hide
		$("#pktoiltable").hide();
	};	
});
//******************************************************************************
//Pumpread Table Entry change view based on 'Product' selected
$(document).on("change", "#product", function(){
	$("#message").text("");
//Call Servlet to get Sales price of fuel
	getfuel_salesprice(); 
	$("#salesprice_fuel").show();
	$("#btnfuelgo").show();
	$("#tableview").hide();
	var tabcontent1 = $("#pumpreadtable");
	tabcontent1[0].tHead.children[1].hidden = true; //Hide Test Qty
	if (tabcontent1[0].tBodies[0] != undefined){
	tabcontent1[0].tBodies[0].hidden = true;} //Hide Table Body	
});
//******************************************************************************
//Pumpread Table Entry change view based on 'Sales Price' selected
$(document).on("change", "#salesprice_fuel", function(){
	$("#message").text("");
	$("#tableview").hide();
	var tabcontent1 = $("#pumpreadtable");
	tabcontent1[0].tBodies[0].hidden = true;
});
//******************************************************************************
//Pumpread Table Entry change view based on 'Sales Price' selected
$(document).on("click", "#btnfuelgo", function(){
	$("#message").text("");
	//Parameters	
	var params1 = {product: $("#product option:selected").text(),
			shift: $("#shift option:selected").text().trim(),
	            date: $("#salesdate").val(),
	            salesprice: $("#salesprice_fuel option:selected").text().trim(),
	            sdate_flag : window.value};	
	//Read Nozzle Pump reading
	pumpreadentry(params1);
	$("#tableview").show();
	var tabcontent1 = $("#pumpreadtable");
	tabcontent1[0].tBodies[0].hidden = false;
});
//******************************************************************************
//Update Sales
$(document).on("click", "#btnsalesupdate", function(){
//get Date/Time
	getdatetime();
//Parameters
	var params = {subgroup_name: $("#subgroup_name").val(),
		      salesdate: $("#salesdate").val(),
		      product: $("#product").val(),
		      sales_qty: $("#sales_qty").val().trim().replaceAll(",",""),
		      stock_qty: $("#stock_qty").val().trim().replaceAll(",",""),
    uom: $("#uom").val(),
    price_unit: $("#price_total").val().trim().replaceAll(",","") /  $("#sales_qty").val().trim().replaceAll(",",""),
    price_total: $("#price_total").val().trim().replaceAll(",",""),
    taxpercent_total: $("#taxpercent_total").val(),
    taxamt_total: (($("#taxpercent_total").val() * $("#price_total").val().trim().replaceAll(",","")) / 100 ), 
    comments: $("#comments").val().trim(),
    changedby: $("#username").val(),
   // changedby:$("#changedby").val(),
    changeddate:$("#changeddate").val(),
    changedtime:$("#changedtime").val()};
//postajax
	that = this;
	fnpostajax(that,params,"salestable");	
	});
//*********************************************************
//Compare Sales qty with Stock qty
function determine_stkqty(that){
//Validate Stock Qty
	var diff, qty;
	qty = $("#stock_qty").val().trim().replaceAll(",","");
	var stock_qty = parseFloat(qty);
	qty = that.defaultValue.trim().replaceAll(",","");
	var prevpur_qty = parseFloat(qty);
	qty = that.value.trim().replaceAll(",","");
	var curpur_qty = parseFloat(qty);
//Check whether Current Sales qty changed
	if (stock_qty != "" && prevpur_qty != curpur_qty ){
//If Stock qty less than previous maintained sales qty, then it
//means stock got consumed, so it need to be adjusted accordingly
		if (stock_qty < prevpur_qty){
//Case1: current.pur qty > prev.pur qty:
			if (curpur_qty > prevpur_qty){
				 diff = curpur_qty - prevpur_qty;
				$("#stock_qty")[0].value = diff + stock_qty;
//Case2: current.pur qty < prev.pur qty & current.pur qty > stock qty				
			}else if (curpur_qty < prevpur_qty && curpur_qty >= stock_qty) {
				diff = prevpur_qty - stock_qty; //Consumed
				if (diff < curpur_qty){
					$("#stock_qty")[0].value = curpur_qty - diff;
				}else {
//Pass '0', as Stock qty got consumed less than current Pur.qty
//Log table - needed to record the difference
				$("#stock_qty")[0].value = 0;
				}
//Case3: current.pur qty < prev.pur qty & current.pur qty < stock qty
			}else if (curpur_qty < prevpur_qty && curpur_qty < stock_qty) {
				diff = prevpur_qty - stock_qty; //Consumed qty
				$("#stock_qty")[0].value = curpur_qty - diff;
			}
		}else{
//If Stock Qty not been reduced from previous maintained Sales qty,
//then pass current changed Sales Qty to Stock Qty
		$("#stock_qty")[0].value = curpur_qty;
		}
	}
//Price Unit
	var amt = $("#price_total").val().trim().replaceAll(",","");
	var price_total = parseFloat(amt);
	$("#price_unit")[0].value = ( price_total / curpur_qty);
	fnaddseparator(that);
}
//*********************************************************
//Tax amount Calculation
function amt_calc(that){
	var price_total, taxpercent_total, amt, sales_qty;
	if (that.id == "taxpercent_total"){
		amt = $("#price_total").val().trim().replaceAll(",","");
		price_total = parseFloat(amt);
		taxpercent_total = parseFloat(that.value);
		$("#taxamt_total")[0].value = (taxpercent_total * price_total) / 100; 
	} 
	if (that.id == "price_total"){
		amt = $("#taxpercent_total").val().trim().replaceAll(",","");
		taxpercent_total = parseFloat(amt);
		qty = $("#sales_qty").val().trim().replaceAll(",","");
		sales_qty = parseFloat(qty);
		amt = that.value.trim().replaceAll(",","");
		price_total = parseFloat(amt);
//Tax Amount Calculation
		$("#taxamt_total")[0].value = (price_total * taxpercent_total) / 100;
//Price Unit Calculation
		$("#price_unit")[0].value = (price_total / sales_qty ) ;
		fnaddseparator(that);	
	}
}

//*********************************************************
//Clear 
$(document).on("click", "#btnsalesclear", function() {
// Clear Mandatory Border Color
	fntableclear("salestable");
});
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
//*************************************************************************************
//Successive Drop-down value update based on Predecessor Drop-down value change
$(document).on("change", "#subgroup_name", function(){
//Show PumpREad Entry Table		
	$("#pumpreadtable").hide();	
	var me = $(this);
	var params = {subgroup_name: $("#subgroup_name").val()};
	if ( me.data('requestRunning') == params.subgroup_name) {
	    return;
	}
mydropdown(params,me);
		});	
//***************************
function mydropdown(params,me){
//Set repeat process indicator false
	me.data('requestRunning', params.group_name)
	//Call Servlet    
	$.get("product_list",  ($.param(params)), function(responseJson) {   
		var $select = $("#product");
		$select.find("option").remove();
		 $.each(responseJson, function(index, product) {
             $("<option>").val(product.uom).text(product.product).appendTo($select);
         });                   	
	});           
	
}
//***********************************************************************************
//'Labour Shift' Change
$(document).on("change", "#shift", function(){
	//Show PumpREad Entry Table		
	$("#pumpreadtable").hide();	
});
//**********************************************************
function salescreate(){
	//Hide Display Content
	$("#pktoiltable").hide();
	$("#pumpreadtable").hide();	
	$("#engoiltable").hide();
	
	var today = fngettoday();
//Get last maintained date	
	var lastsalesdate = document.getElementById("salesdate").value;
	lastsalesdate.trim();
//Convert String to Date
	var date = new Date(lastsalesdate);
	if (lastsalesdate != ""){
//Add a day 
		date.setDate( date.getDate() + 1);
		var sdate = dateconvert(date);
		document.getElementById("salesdate").value = sdate;
	}else{
		document.getElementById("salesdate").value = today;
	}
	document.getElementById("salesdate").setAttribute("max", today);	

}
//*************************************************************************************
function salesdisp(){
	gettodaydate() ;
	getmonthstart();
//Hide Display Content
	$("#scrolltable").hide();
}
//*************************************************************************************
$(document).on("click", "#btngodisplay", function(){
	//Clear Message
	$("#message").text(" ");
	//Display Sales table
	displaysalestable();
});
//***********************************************************************
$(document).on("click", "#btnlinkdelete", function(){
	//Clear Message
	$("#message").text(" ");
	//Get "Action"
	that = this;
var href = that.name;

	$.post(href, function(responseText) {
		$("#message").text(responseText);		
		document.getElementById("message").style.color = "#00ff00";
//Display Sales table
		displaysalestable();		
	});
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
//Set Pop-up Click for First Column
          var modal = row.children().eq(0)[0];
          modal.id = "btnrowpopup";
          modal.setAttribute("type", "button");
//Remainingn Columns          
          row.children().eq(1).text(value['salesdate']);
          row.children().eq(2).text(value['subgroup_name']);
          row.children().eq(3).text(value['sales_qty']);
          row.children().eq(4).text(value['stock_qty']);
          row.children().eq(5).text(value['uom']);
          row.children().eq(6).text(value['sales_priceunit']);
          row.children().eq(7).text(value['total_sales_price']);
          row.children().eq(8).text(value['taxpercent_total']);
          row.children().eq(9).text(value['taxamt_total']);
          row.children().eq(10).text(value['comments']);
          row.children().eq(11).text(value['changedby']);
          row.children().eq(12).text(value['changeddate']);
          row.children().eq(13).text(value['changedtime']); 
//Get/Set Element content "a"
          var a = row.children().eq(14)[0];
          var date = dateconvert(value['salesdate']);
          var edithref = "salesedit?product=" + value['product'] + "&salesdate=" + date;
          var deletehref = "salesdeleteajax?product=" + value['product'] + "&salesdate=" + date;
          a.children[0].id = "linkedit"; a.children[1].id = "btnlinkdelete";            
//Set Attribute for "href"
          a.children[0].setAttribute("href", edithref);
          a.children[1].setAttribute("name", deletehref);
          a.children[1].setAttribute("value", "Delete");
          a.children[1].setAttribute("type", "button");
          
          row.appendTo(tabcontent);
		});		
      });		
	//Show Display Content
	$("#scrolltable").show();
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
	
}
//***********************************************************************
//Call servlet to get Product of Packet oil & its stock details
function pktoil_sales(params){
	var accessval = document.getElementById("useraccess");	
	var tabcontent = $("#pktoiltable"), hide_flag = false, 
	    sprice_init = true, save_disable = false, netprice;
    var tbodys = $("<tbody>"), tbodye = $("</tbody>");   
    tbodys.appendTo(tabcontent);
//Call Servlet to get Packet Oil Sales Data
		$.get("sales_pktoilajax", ($.param(params)), function(responseJson) {  
			$("#pktoiltable").find("tr:gt(1)").remove();
			$.each(responseJson, function(key, value) {
				var row = $("<tr><td></td><td></td><td><input/></td><td></td><td></td><td></td><td></td></tr>");
//If Stock(Purchase Price unit holds Stock qty) value equals 'exists', then it means 
//Sales on entered date is already been stored in 'sales' table. So Disable 'Save' option
			if (typeof value['purchase_priceunit'] == "undefined"){
     			save_disable = true;}
			//}else{
				row.eq(0)[0].id = "tableview";
//Column 1 - Product				
				row.children().eq(0).text(value['product']);	
//Column 2 - Stock Qty

				if (typeof value['purchase_priceunit'] != "undefined"){
//Purchase Price Unit holds Stock Qty					
					row.children().eq(1).text(value['purchase_priceunit']);
//Column Header - View					
					tabcontent[0].children[0].children[1].children[1].style.display = "";
				}else{
//Hide Column - Open Stock			
					var col2 = row.children().eq(1);
					col2[0].style.display = "none";
		            hide_flag = true;}
//Column 3 - Sales Qty				
				(row.children().eq(2)[0]).children[0].setAttribute("type", "number");
				(row.children().eq(2)[0]).children[0].setAttribute("id", "poilsalesqty");
				if (typeof value['purchase_priceunit'] == "undefined"){
					(row.children().eq(2)[0]).children[0].value = value['sales_qty'];	
					(row.children().eq(2)[0]).children[0].disabled = true;
				}				
//Column 4 - Close Stock
				if (typeof value['purchase_priceunit'] == "undefined"){
					var col4 = row.children().eq(3);
					col4[0].style.display = "none";					
				}else{
//Column Header - View					
					tabcontent[0].children[0].children[1].children[3].style.display = "";
				}
//Column 5 - Sales Price
				row.children().eq(4).text(value['sales_priceunit']);
//Column 6 - Net Price	
				if (typeof value['purchase_priceunit'] == "undefined"){
					row.children().eq(5).text(value['total_sales_price']);}
//Column 7 - By Default Hide the column 'Taxpercent_total', as it is used for Background Save
				row.children().eq(6).text(value['taxpercent_total']);
				var col7 = row.children().eq(6);
				col7[0].style.display = "none";
				row.appendTo(tabcontent);
			});
//Footer			
			var footer = $("<tr><td></td><td></td><td><input/></td><td></td><td></td><td></td></tr>");	
			  var skip = $("<tr><td><br></td><td></td><td></td><td></td><td></td><td></td></tr>");
			  var savebutton = $("<tr><td></td><td><input/></td><td><input/></td><td></td><td></td><td></td></tr>");
			  footer.eq(0)[0].id = "tableview";
//Button - Total Check
				(footer.children().eq(2)[0]).children[0].setAttribute("type", "button");
				(footer.children().eq(2)[0]).children[0].setAttribute("value", "Total Check");
				(footer.children().eq(2)[0]).children[0].setAttribute("id", "btnpktoiltotalcheck");	
//Hide Column
				if (hide_flag == true){
					var footer1 = footer.children().eq(1);
					var footer3 = footer.children().eq(3);
					footer1[0].style.display = "none";
					footer3[0].style.display = "none";
//Hide Header Column					
				var th = tabcontent.get()[0].getElementsByTagName("th");
					th[1].style.display = "none";
					th[3].style.display = "none";
				};		
				footer.appendTo(tabcontent);	//Append Footer
				skip[0].setAttribute("class", "noBorder");
				skip.appendTo(tabcontent);   
//Save Button Footer
				savebutton[0].setAttribute("class", "noBorder");
				(savebutton.children().eq(2)[0]).children[0].setAttribute("type", "button");
				(savebutton.children().eq(2)[0]).children[0].setAttribute("value", "SAVE");	
				(savebutton.children().eq(2)[0]).children[0].setAttribute("id", "btnpktoilsave");
				(savebutton.children().eq(2)[0]).children[0].setAttribute("class", "btn btn-primary");		
				
				if (hide_flag == true){
					(savebutton.children().eq(2)[0]).children[0].setAttribute("disabled", "true");
	//Edit Button				
	// Show Edit option only for 'Super' access user
					 if (accessval.innerText == "full") {			
				//(savebutton.children().eq(2)[0]).children[0].setAttribute("disabled", "false");	
				(savebutton.children().eq(1)[0]).children[0].setAttribute("type", "button");
				(savebutton.children().eq(1)[0]).children[0].setAttribute("value", "EDIT");	
				(savebutton.children().eq(1)[0]).children[0].setAttribute("id", "btnpktoiledit");
				(savebutton.children().eq(1)[0]).children[0].setAttribute("class", "btn btn-primary");
					 }				 			
				}
				else{(savebutton.children().eq(1)[0]).children[0].setAttribute("hidden", "true");}
				savebutton.appendTo(tabcontent);
				
				tbodye.appendTo(tabcontent);				
		});
};
//*********************************************************************************************************
//***********************************************************************************************************************************
//On value Change in 'Sales Qty', calculate Closing Stock / Net Price
$(document).on("change","#poilsalesqty", function(e){
	that = this;	
	calc_poil_sales_price_qty(that);
});
//*********************************************************************************************************
//Calculate Closing Stock / Net price based on cell value change
function calc_poil_sales_price_qty(that){
	var close_stk, netprice;
	//Clear Message
		$("#message").text("");

		//selrow = $(e.currentTarget)[0].parentElement;
		var selrow = that.parentElement;
		var openstk = selrow.parentElement.children[1].textContent; //Open Stock reading of selected row
	//Convert to Number
		var num_ropenstk = parseFloat(openstk);
		var num_rsalesqty = parseFloat(that.value);
	//Validation	
		if (that.value.trim() == ""){
			$("#message").text("Enter Sales Quantity!!!");
			 document.getElementById("message").style.color = "#98052e";
			 //selrow.parentElement.children[1].children[0].style.borderColor = "red";
			 selrow.parentElement.children[2].focus();
		}						
		else{
	//Closing Stock	
			close_stk = num_ropenstk - num_rsalesqty;
		selrow.parentElement.children[3].textContent =  close_stk.toLocaleString('en-US', {minimumFractionDigits: 2});
	//price
		price_unit = selrow.parentElement.children[4].textContent;
		netprice = ( num_rsalesqty * price_unit );
	//Net Price Calculation
		selrow.parentElement.children[5].textContent = netprice.toLocaleString('en-US', {minimumFractionDigits: 2});
		}	
}
//*********************************************************************************************************
//Button Click
$(document).on("click", "#btnpktoiltotalcheck", function(){
	$("#message").text("");
	var tabcontent = $("#pktoiltable"), i, sumprice = 0;	
	var colcnt = tabcontent[0].children[0].children[1].children.length;
	var rowscnt = tabcontent[0].rows.length - 3;
	var sales_qty, closestk, error = false, error_stk = false;
	for (i=2; i<=rowscnt; i++){
if (i == rowscnt){
//Display aggregate Net price with Comma Notation
	tabcontent[0].rows[i].children[5].textContent = sumprice.toLocaleString('en-US', {minimumFractionDigits: 2});
	tabcontent[0].rows[i].children[4].style.color = "#000000";
	tabcontent[0].rows[i].children[5].style.color = "#000000";
}else{		
	sales_qty = tabcontent[0].rows[i].children[2].children[0].value;
	closestk = tabcontent[0].rows[i].children[3].textContent;
	if (sales_qty.trim() == ""){
		tabcontent[0].rows[i].children[2].focus();
		error = true;
		break;		}
	if (closestk < 0){
		tabcontent[0].rows[i].children[2].focus();
		error_stk = true;
		break;
	}
		netprice = parseFloat(tabcontent[0].rows[i].children[5].textContent.replaceAll(",",""));
		sumprice = sumprice + netprice;
}
	}
	if (error == true){
		$("#message").text("Please Maintain Sales Qty for all!!!");
		document.getElementById("message").style.color = "#98052e";		
	}else if(error_stk == true){
		$("#message").text("Please Maintain Stock!!!");
		document.getElementById("message").style.color = "#98052e";
	}	
});
//*********************************************************************************************************
//Save Packet Oil Entry
$(document).on("click", "#btnpktoilsave", function(){
	$("#message").text("");
	var pktoils = new Array();
	var tabcontent = $("#pktoiltable"), i;
	//var colcnt = tabcontent[0].children[0].children[2].children.length;
	var rowscnt = tabcontent[0].rows.length - 4;
	var uname = document.getElementById("userfirstname"), error = false, error_stk = false;
	for (i=2; i<=rowscnt; i++){
		var pktoil = {};
		pktoil.product = tabcontent[0].rows[i].children[0].textContent;
		pktoil.salesdate = document.getElementById("salesdate").value;
		//pktoil.pump_id = tabcontent[0].rows[i].children[0].textContent;
		pktoil.labour_shift = document.getElementById("shift").value;
		pktoil.sales_priceunit = tabcontent[0].rows[i].children[4].textContent.trim().replaceAll(",","");	
		pktoil.subgroup_name = document.getElementById("subgroup_name").value;
		pktoil.sales_qty = tabcontent[0].rows[i].children[2].children[0].value.trim().replaceAll(",","");
		pktoil.testqty = parseFloat(0);
		pktoil.total_sales_price = tabcontent[0].rows[i].children[5].textContent.trim().replaceAll(",","");
		var closestk = tabcontent[0].rows[i].children[3].textContent;
		if (pktoil.sales_qty.trim() == ""){
			tabcontent[0].rows[i].children[2].focus();
			error = true;
			break;		}
		if (closestk < 0){
			tabcontent[0].rows[i].children[2].focus();
			error_stk = true;
			break;
		}
		pktoil.taxpercent_total = tabcontent[0].rows[i].children[6].textContent;
		if (pktoil.taxpercent_total.trim() == ""){ 
		pktoil.taxpercent_total = 0;}
		pktoil.comments = null;
		pktoil.changedby = uname.textContent;
		pktoil.changeddate = fngettoday();
		pktoil.changedtime = fngetcurrenttime();
		pktoils.push(pktoil);
	}
	if (error == true){
		$("#message").text("Please Maintain Sales Qty for all!!!");
		document.getElementById("message").style.color = "#98052e";		
	}else if(error_stk == true){
		$("#message").text("Please Maintain Stock!!!");
		document.getElementById("message").style.color = "#98052e";
	}
	else{
//Save fuel with pump-reading / Sales details		
	$.post("sales_pktoilsave", { "pktoils":JSON.stringify(pktoils)}, function(responseText) {
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
$(document).on("click", "#btnpktoiledit", function(){
	$("#message").text("");
	var tabcontent = $("#pktoiltable"), i;
	var rowscnt = tabcontent[0].rows.length - 4;
	for (i=2; i<=rowscnt; i++){
		//tabcontent[0].rows[i].children[1].children[0].disabled = false;
		tabcontent[0].rows[i].children[2].children[0].disabled = false;
	}
//Enable Save Mode in Last row
	i = rowscnt + 3;
	tabcontent[0].rows[i].children[2].children[0].disabled = false;
});
//***********************************************************************
//Call servlet to get Product of Engine oil & its stock details
function engoil_sales(params){
	var accessval = document.getElementById("useraccess");
	var uname = document.getElementById("userfirstname")
	var tabcontent = $("#engoiltable"), hide_flag = false, 
	    sprice_init = true, save_disable = false, netprice;
//Enable 'ADD NEW ROW' Button
	tabcontent[0].children[0].children[0].children[0].children[0].disabled = false;	
//Declared 'engoils' as global	
	engoils = new Array();
  var tbodys = $("<tbody>"), tbodye = $("</tbody>");    
  tbodys.appendTo(tabcontent);
//Call Servlet to get Engine Oil Sales Data
		$.get("sales_engoilajax", ($.param(params)), function(responseJson) {  
			$("#engoiltable").find("tr:gt(1)").remove();
			$.each(responseJson, function(key, value) {
				var row = $("<tr><td></td><td></td><td type='number'><input/></td><td></td><td></td><td></td><td></td></tr>");
				row.eq(0)[0].id = "tableview";
//If Stock(Purchase Price unit holds Stock qty) value equals 'exists', then it means 
//Sales on entered date is already been stored in 'sales' table. So Disable 'Save' option
			if (typeof value['purchase_priceunit'] == "undefined"){
   			save_disable = true;}
			else{
//For New Entry collect all the Engile Oil's Product				
				var engoil = {};
				engoil.product = value['product'];
				engoil.salesdate = document.getElementById("salesdate").value;
				engoil.labour_shift = document.getElementById("shift").value;
				engoil.sales_priceunit = value['sales_priceunit'];	
				engoil.subgroup_name = document.getElementById("subgroup_name").value;
//Purchase Price Unit holds Stock Qty & passing that to 'sales_qty' field				
				engoil.sales_qty = value['purchase_priceunit'];
				engoil.total_sales_price = value['total_sales_price'];
				engoil.taxpercent_total = value['taxpercent_total'];
				if (engoil.taxpercent_total == ""){ 
				engoil.taxpercent_total = 0;}				
				engoil.comments = null;
				engoil.changedby = uname.textContent;
				engoil.changeddate = fngettoday();
				engoil.changedtime = fngetcurrenttime();
				engoils.push(engoil);				

				return ;}
//Column 1 - Product
				row.children().eq(0).text(value['product']);
//Column 2 - Stock Qty
				if (typeof value['purchase_priceunit'] != "undefined"){
//Purchase Price Unit holds Stock Qty					
					row.children().eq(1).text(value['purchase_priceunit']);
				}else{
//Hide Column - Open Stock					
					var col2 = row.children().eq(1);
					col2[0].style.display = "none";
		            hide_flag = true;}
//Column 3 - Sales Qty				
				(row.children().eq(2)[0]).children[0].setAttribute("type", "number");
				(row.children().eq(2)[0]).children[0].setAttribute("id", "engoilsalesqty");
				if (typeof value['purchase_priceunit'] == "undefined"){
					(row.children().eq(2)[0]).children[0].value = value['sales_qty'];	
					(row.children().eq(2)[0]).children[0].disabled = true;
				}				
//Column 4 - Close Stock
				if (typeof value['purchase_priceunit'] == "undefined"){
					var col4 = row.children().eq(3);
					col4[0].style.display = "none";	
				}
//Column 5 - Sales Price
				row.children().eq(4).text(value['sales_priceunit']);
//Column 6 - Net Price	
				if (typeof value['purchase_priceunit'] == "undefined"){
					row.children().eq(5).text(value['total_sales_price']);}
//Column 7 - By Default Hide the column 'Taxpercent_total', as it is used for Background Save
				row.children().eq(6).text(value['taxpercent_total']);
				var col7 = row.children().eq(6);
				col7[0].style.display = "none";

				if (typeof value['purchase_priceunit'] == "undefined"){
//Hide 'ADD NEW ROW' Button
					tabcontent[0].children[0].children[0].children[0].children[0].hidden = true;					
				}
				row.appendTo(tabcontent);
			});
			if (save_disable == false){			
//Populate all Engine Oil Products as F4 help for 'Product' Input in new Dynamic row
			var dynrow = $("<tr><td><select><select/></td><td></td><td><input/></td><td></td><td></td><td></td><td></td><td>" +
					"<button type='button' id='btndelengoil' class='btn btn-danger'>DELETE</button></td></tr>");
			dynrow.eq(0)[0].id = "tableview";
			dynrow[0].children[0].children[0].setAttribute("id", "engproduct1");
			dynrow[0].children[2].children[0].setAttribute("id", "engoilsalesqty");
			dynrow[0].children[2].children[0].setAttribute("type", "number");
			dynrow[0].children[0].children[0].setAttribute("onchange", "engrowupd(this)");
			dynrow[0].children[6].style.display = "none"
			dynrow.appendTo(tabcontent);	
//Drop-Down for Engine Oil Product			
			var rowscnt = 0;
			engprd_dropdown(engoils, rowscnt);	
			}
//Footer			
			var footer = $("<tr><td></td><td></td><td><input/></td><td></td><td></td><td></td><td></td></tr>");	
			  var skip = $("<tr><td><br></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
			  var savebutton = $("<tr><td></td><td><input/></td><td><input/></td><td></td><td></td><td></td><td></td></tr>");
			  footer.eq(0)[0].id = "tableview";
			  //skip.eq(0)[0].id = "tableview";
			  //savebutton.eq(0)[0].id = "tableview";
//Button - Total Check
				(footer.children().eq(2)[0]).children[0].setAttribute("type", "button");
				(footer.children().eq(2)[0]).children[0].setAttribute("value", "Total Check");
				(footer.children().eq(2)[0]).children[0].setAttribute("id", "btnengoiltotalcheck");	
//Hide Column
				var footer1 = footer.children().eq(1);
				var footer3 = footer.children().eq(3);
				var footer6 = footer.children().eq(6);					

				var th = tabcontent.get()[0].getElementsByTagName("th");				
				if (hide_flag == true){
					footer1[0].style.display = "none";
					footer3[0].style.display = "none";
					footer6[0].style.display = "none";
//Hide Header Column					
					th[1].style.display = "none";
					th[3].style.display = "none";
					th[6].style.display = "none";
				}else{
//Display header/Footer Column 					
					footer1[0].style.display = footer3[0].style.display = footer6[0].style.display = "";
					th[1].style.display = th[3].style.display = th[6].style.display = "";
//Display 'ADD NEW ROW' Button
					tabcontent[0].children[0].children[0].children[0].children[0].hidden = false;					
				};		
				footer.appendTo(tabcontent);	//Append Footer
				skip[0].setAttribute("class", "noBorder");
				skip.appendTo(tabcontent);   
//Save Button Footer
				savebutton[0].setAttribute("class", "noBorder");
				(savebutton.children().eq(2)[0]).children[0].setAttribute("type", "button");
				(savebutton.children().eq(2)[0]).children[0].setAttribute("value", "SAVE");	
				(savebutton.children().eq(2)[0]).children[0].setAttribute("id", "btnengoilsave");
				(savebutton.children().eq(2)[0]).children[0].setAttribute("class", "btn btn-primary");		
				
				if (hide_flag == true){
					(savebutton.children().eq(2)[0]).children[0].setAttribute("disabled", "true");
	//Edit Button				
	// Show Edit option only for 'Super' access user
					 if (accessval.innerText == "full") {			
				//(savebutton.children().eq(2)[0]).children[0].setAttribute("disabled", "false");	
				(savebutton.children().eq(1)[0]).children[0].setAttribute("type", "button");
				(savebutton.children().eq(1)[0]).children[0].setAttribute("value", "EDIT");	
				(savebutton.children().eq(1)[0]).children[0].setAttribute("id", "btnengoiledit");
				(savebutton.children().eq(1)[0]).children[0].setAttribute("class", "btn btn-primary");
					 }				 			
				}
				else{(savebutton.children().eq(1)[0]).children[0].setAttribute("hidden", "true");}
				savebutton.appendTo(tabcontent);
				
				tbodye.appendTo(tabcontent);				
		});
};
//*********************************************************************************************************
function engprd_dropdown(engoils, rowscnt){ 
	//var $engselect = $("#engproduct");
	var idval = "#engproduct"+(rowscnt+1)
	var $engselect = $(idval);
	$engselect.find("option").remove();
	$("<option>").val(" ").text(" ").appendTo($engselect);
	engoils.forEach(function(engoil, index){
		if (engoil.comments != "X"){
		$("<option>").val(engoil.product).text(engoil.product).appendTo($engselect);
		}
	});
	};
//*********************************************************************************************************
	$(document).on("click", "#btnaddengoil", function(){
		//Clear Message
		$("#message").text("");
		var tabcontent = $("#engoiltable"), idnum;
		var uname = document.getElementById("userfirstname");
		var rowscnt = tabcontent[0].children[1].rows.length - 4;		
		if (rowscnt >= 0){
		var row = tabcontent[0].children[1].children[rowscnt];
//Validation - Product
		if (row.children[0].children[0] != undefined){
			if (row.children[0].children[0].value.trim() == ""){	
			$("#message").text("Maintain Engine Oil Product before Adding New Row!!!");
			document.getElementById("message").style.color = "#98052e";
			return;	
		}}
//Validation - Sales Qty
		if (row.children[2].children[0].value.trim() == ""){
			$("#message").text("Enter Sales Qty before Adding New Row!!!");
			document.getElementById("message").style.color = "#98052e";
			return;
		}		
//Check if all Product been added
		if (engoils.length > 0){
		var index_avbl = engoils.findIndex(engoil => (engoil.comments == null || engoil.comments.trim() == ""));
		if (index_avbl < 0){
			$("#message").text("All Engine Oil Product Added!!!");
			document.getElementById("message").style.color = "#98052e";
			return;
		}}
		}
		//rowscnt = rowscnt + 1;
		var dynrow = $("<tr><td><select><select/></td><td></td><td><input/></td><td></td><td></td><td></td><td></td><td>" +
		"<button type='button' id='btndelengoil' class='btn btn-danger'>DELETE</button></td></tr>");
		dynrow.eq(0)[0].id = "tableview";
//Get Last rows maintained 'ID' of Engine Product & the number series, to maintain with Consecutive Series of Next row
		if (rowscnt >= 0 && engoils.length > 0 && row.children[0].children[0] != undefined){
		idnum = row.children[0].children[0].id.substr(10,10);
		}else{	idnum = 0;
		}
	    var idnum1 = new Number(idnum);
		var idval = "engproduct" + (idnum1+1);
		dynrow[0].children[0].children[0].setAttribute("id", idval);
		dynrow[0].children[2].children[0].setAttribute("id", "engoilsalesqty");
		dynrow[0].children[2].children[0].setAttribute("type", "number");
		dynrow[0].children[0].children[0].setAttribute("onchange", "engrowupd(this)");
		dynrow[0].children[6].style.display = "none"
				
//Check if 'ADD NEW ROW' Clicked after clicking 'EDIT' Button by referring last row Column 'open Stk' Hide
		if (rowscnt >= 0) {
		if (row.children[1].style.display == "none"){
			dynrow[0].children[1].style.display = "none"; //Open Stk hide
			dynrow[0].children[3].style.display = "none"; //Close Stk hide
//if so then get list of Products which are of not Saved earlier in given Date & Shift
			if (engoils.length <= 0){
			var params = {subgroup_name: $("#subgroup_name").val(),
			    	shift: $("#shift option:selected").text().trim(),
		               date: $("#salesdate").val()};
			$.post("sales_engprdpendajax", ($.param(params)), function(responseJson) {
//Collect all the Engine Oil's Product			
				$.each(responseJson, function(key, value) {
				var engoil = {};
				engoil.product = value['product'];
				engoil.salesdate = document.getElementById("salesdate").value;
				engoil.labour_shift = document.getElementById("shift").value;
				engoil.sales_priceunit = value['sales_priceunit'];	
				engoil.subgroup_name = document.getElementById("subgroup_name").value;
//Purchase Price Unit holds Stock Qty & passing that to 'sales_qty' field				
				engoil.sales_qty = value['purchase_priceunit'];
				engoil.total_sales_price = value['total_sales_price'];
				engoil.taxpercent_total = value['taxpercent_total'];
				if (engoil.taxpercent_total.trim() == ""){ 
				engoil.taxpercent_total = 0;}				
				engoil.comments = null;
				engoil.changedby = uname.textContent;
				engoil.changeddate = fngettoday();
				engoil.changedtime = fngetcurrenttime();
				engoils.push(engoil);	
				});
//Drop-Down for Engine Oil Product		
				if (engoils.length > 0){
				engprd_dropdown(engoils, idnum1);
				}else{
					$("#message").text("All Engine Oil Product Added!!!");
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
//New First Entry	
			var row = tabcontent[0].children[1].children[0];
			dynrow.insertBefore(row);};				
//Drop-Down for Engine Oil Product	
		if (engoils.length > 0){
		engprd_dropdown(engoils, idnum1);}				
	});
//*********************************************************************************************************
function engrowupd(that){
	var tabcontent = $("#engoiltable");
	var selrow = that.parentElement;
//Update Row Value based on selected Product in Engine Oil
//Global Array 'ENGOILS' holds all required details
//Product- From Selected Drop-down
	var product = that.options[that.options.selectedIndex].text;
//Open Stock - 'sales_qty' field holds Stock qty 
	selrow.parentElement.children[1].textContent = engoils.find(engoil => engoil.product == product).sales_qty;
//Sales Qty - Make it null, as Product got changed
	selrow.parentElement.children[2].children[0].value = '';
//Close Stk - Make it null, as Product got changed
	selrow.parentElement.children[3].textContent = "";
//Net Price - Make it null, as Product got changed
	selrow.parentElement.children[5].textContent = "";
//Sales Price		
	selrow.parentElement.children[4].textContent = engoils.find(engoil => engoil.product == product).sales_priceunit;
//Taxpercent Total
	selrow.parentElement.children[6].textContent = engoils.find(engoil => engoil.product == product).taxpercent_total;
}

//***********************************************************************************************************************************
//On value Change in 'Sales Qty', calculate Closing Stock / Net Price
$(document).on("change","#engoilsalesqty", function(e){
	that = this;	
	calc_engoil_sales_price_qty(that);
});
//*********************************************************************************************************
//Calculate Closing Stock / Net price based on cell value change
function calc_engoil_sales_price_qty(that){
	var close_stk, netprice;
	//Clear Message
		$("#message").text("");

		//selrow = $(e.currentTarget)[0].parentElement;
		var selrow = that.parentElement;
		var openstk = selrow.parentElement.children[1].textContent; //Open Stock reading of selected row
	//Convert to Number
		var num_ropenstk = parseFloat(openstk);
		var num_rsalesqty = parseFloat(that.value);
	//Validation	
		if (that.value.trim() == ""){
			$("#message").text("Enter Sales Quantity!!!");
			 document.getElementById("message").style.color = "#98052e";
			 //selrow.parentElement.children[1].children[0].style.borderColor = "red";
			 selrow.parentElement.children[2].focus();
		}
		else if(selrow.parentElement.children[0].children[0].value.trim() == ""){
			$("#message").text("Select Engine Oil Product!!!");
			 document.getElementById("message").style.color = "#98052e";
			 selrow.parentElement.children[2].focus();
		}else{
	//Closing Stock	
			close_stk = num_ropenstk - num_rsalesqty;
		selrow.parentElement.children[3].textContent =  close_stk.toLocaleString('en-US', {minimumFractionDigits: 2});
	//price
		price_unit = selrow.parentElement.children[4].textContent;
		netprice = ( num_rsalesqty * price_unit );
	//Net Price Calculation
		selrow.parentElement.children[5].textContent = netprice.toLocaleString('en-US', {minimumFractionDigits: 2});
//Disable Dropdown of Product
		selrow.parentElement.children[0].children[0].disabled = true;
//Chosen Product should not list in other Drop-Down Product So setting with Flag in 'Comments' 
		var engselprd = "#" + selrow.parentElement.children[0].children[0].id ;
		engoils.find(engoil => engoil.product == $(engselprd).val()).comments = "X";		
		}	
}
//*********************************************************************************************************
//Button Click
$(document).on("click", "#btnengoiltotalcheck", function(){
	$("#message").text("");
	var tabcontent = $("#engoiltable"), i, sumprice = 0;	
	var colcnt = tabcontent[0].children[0].children[1].children.length;
	var rowscnt = tabcontent[0].rows.length - 3;
	var sales_qty, closestk, product, error = false, error_stk = false, error_prd = false;
	for (i=2; i<=rowscnt; i++){
if (i == rowscnt){
//Display aggregate Net price with Comma Notation
	tabcontent[0].rows[i].children[5].textContent = sumprice.toLocaleString('en-US', {minimumFractionDigits: 2});
	tabcontent[0].rows[i].children[4].style.color = "#000000";
	tabcontent[0].rows[i].children[5].style.color = "#000000";
}else{		
	if (tabcontent[0].rows[i].children[0].children[0] == undefined){
		product = tabcontent[0].rows[i].children[0].textContent;
	}else{
	    product = tabcontent[0].rows[i].children[0].children[0].value;}	
	sales_qty = tabcontent[0].rows[i].children[2].children[0].value;
	closestk = tabcontent[0].rows[i].children[3].textContent;
	if (product.trim() == ""){		
		tabcontent[0].rows[i].children[0].focus();
		error_prd = true;
		break;		}	
	if (sales_qty.trim() == ""){
		tabcontent[0].rows[i].children[2].focus();
		error = true;
		break;		}
	if (closestk < 0){
		tabcontent[0].rows[i].children[2].focus();
		error_stk = true;
		break;
	}
		netprice = parseFloat(tabcontent[0].rows[i].children[5].textContent.replaceAll(",",""));
		sumprice = sumprice + netprice;
}
	}
	if (error_prd == true){
		$("#message").text("Please Maintain Engine Oil Product for all!!!");
		document.getElementById("message").style.color = "#98052e";		
	}else if (error == true){
		$("#message").text("Please Maintain Sales Qty for all!!!");
		document.getElementById("message").style.color = "#98052e";		
	}else if(error_stk == true){
		$("#message").text("Please Maintain Stock!!!");
		document.getElementById("message").style.color = "#98052e";
	}	
});
//*********************************************************************************************************
//Save Packet Oil Entry
$(document).on("click", "#btnengoilsave", function(){
	$("#message").text("");
	var engupdoils = new Array();
	var tabcontent = $("#engoiltable"), i;
	//var colcnt = tabcontent[0].children[0].children[2].children.length;
	var rowscnt = tabcontent[0].rows.length - 4;
	var uname = document.getElementById("userfirstname"), error = false, 
	error_prd = false, error_stk = false;
	for (i=2; i<=rowscnt; i++){
		var engupdoil = {};
		if (tabcontent[0].rows[i].children[0].children[0] == undefined){
			engupdoil.product = tabcontent[0].rows[i].children[0].textContent;
		}else{
		engupdoil.product = tabcontent[0].rows[i].children[0].children[0].value;}
		if (engupdoil.product.trim() == ""){
			error_prd = true;
			tabcontent[0].rows[i].children[0].focus();
			break;		}	
		engupdoil.salesdate = document.getElementById("salesdate").value;
		engupdoil.labour_shift = document.getElementById("shift").value;
		engupdoil.sales_priceunit = tabcontent[0].rows[i].children[4].textContent.trim().replaceAll(",","");	
		engupdoil.subgroup_name = document.getElementById("subgroup_name").value;
		engupdoil.sales_qty = tabcontent[0].rows[i].children[2].children[0].value.trim().replaceAll(",","");
		engupdoil.testqty = parseFloat(0);
		engupdoil.total_sales_price = tabcontent[0].rows[i].children[5].textContent.trim().replaceAll(",","");
		var closestk = tabcontent[0].rows[i].children[3].textContent;
		if (engupdoil.sales_qty.trim() == ""){
			tabcontent[0].rows[i].children[2].focus();
			error = true;
			break;		}
		if (closestk < 0){
			tabcontent[0].rows[i].children[2].focus();
			error_stk = true;
			break;
		}
//Disable 'EDIT' Mode 
		tabcontent[0].rows[i].children[2].children[0].disabled = true;		
		engupdoil.taxpercent_total = tabcontent[0].rows[i].children[6].textContent;
		if (engupdoil.taxpercent_total.trim() == ""){ 
			engupdoil.taxpercent_total = 0;}
		engupdoil.comments = null;
		engupdoil.changedby = uname.textContent;
		engupdoil.changeddate = fngettoday();
		engupdoil.changedtime = fngetcurrenttime();
		engupdoils.push(engupdoil);
	}
	if (error == true){
		$("#message").text("Please Maintain Sales Qty for all!!!");
		document.getElementById("message").style.color = "#98052e";		
	}else if(error_stk == true){
		$("#message").text("Please Maintain Stock!!!");
		document.getElementById("message").style.color = "#98052e";
	}else if(error_prd == true){
		$("#message").text("Please Maintain Engine Oil Product for all!!!");
		document.getElementById("message").style.color = "#98052e";
	}else{
		//Disable 'ADD NEW ROW' Button
		tabcontent[0].children[0].children[0].children[0].children[0].disabled = true;
//Save fuel with Engine Oil Sales details		
	$.post("sales_engoilsave", { "engupdoils":JSON.stringify(engupdoils)}, function(responseText) {
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
$(document).on("click", "#btndelengoil", function(){
	that = this;
	var i = that.parentElement.parentElement.rowIndex - 2;
	var selrow = that.parentElement;
//Remove 'X' Flag in 'Comments' when the row is deleted	
	var engselprd = "#" + selrow.parentElement.children[0].children[0].id ;
	if ($(engselprd).val() != " "){
	engoils.find(engoil => engoil.product == $(engselprd).val()).comments = "";}
	that.parentElement.parentElement.parentElement.deleteRow(i);	
});
//*********************************************************************************************************
$(document).on("click", "#btnengoiledit", function(){
	$("#message").text("");
	var tabcontent = $("#engoiltable"), i;
	var rowscnt = tabcontent[0].rows.length - 4;
	for (i=2; i<=rowscnt; i++){
		//tabcontent[0].rows[i].children[1].children[0].disabled = false;
		tabcontent[0].rows[i].children[2].children[0].disabled = false;
	}
//Enable Save Mode in Last row
	i = rowscnt + 3;
	tabcontent[0].rows[i].children[2].children[0].disabled = false;
//Show 'ADD NEW ROW' Button
	tabcontent[0].children[0].children[0].children[0].children[0].hidden = false;
	tabcontent[0].children[0].children[0].children[0].children[0].disabled = false;
});
