//Insert an entry in table on button click
$(document).on("click", "#btnpurchaseinsert", function(){
//get Date/Time
getdatetime();
	var params = {subgroup_name: $("#subgroup_name").val(),
		      purchasedate: $("#todaydate").val(),
		      product: {product : $("#product option:selected").text()}.product,
		      purchase_qty: $("#purchase_qty").val().trim().replaceAll(",",""), //remove Commas
		      stock_qty: $("#purchase_qty").val().trim().replaceAll(",",""), //remove Commas
      uom: {product : $("#product option:selected").val()}.product,
      price_unit: $("#price_total").val().trim().replaceAll(",","") /  $("#purchase_qty").val().trim().replaceAll(",",""),
      price_total: $("#price_total").val().trim().replaceAll(",",""),
      taxpercent_total: $("#taxpercent_total").val(),
      taxamt_total: (($("#taxpercent_total").val() * $("#price_total").val().trim().replaceAll(",","")) / 100 ), 
      comments: $("#comments").val().trim(),
      changedby:$("#changedby").val(),
      changeddate:$("#changeddate").val(),
      changedtime:$("#changedtime").val()};

if (params.purchasedate > document.getElementById("todaydate").max) {
	$("#message").text("Error!!!Purchase Date CANNOT be greater than Today");
	document.getElementById("message").style.color = "#98052e";
	return;
}
//postajax
that = this;
fnpostajax(that,params,"purchasetable");
	});

//******************************************************************************
//Update Purchase
$(document).on("click", "#btnpurchaseupdate", function(){
//get Date/Time
	getdatetime();
//Parameters
	var params = {subgroup_name: $("#subgroup_name").val(),
		      purchasedate: $("#purchasedate").val(),
		      product: $("#product").val(),
		      purchase_qty: $("#purchase_qty").val().trim().replaceAll(",",""),
		      stock_qty: $("#stock_qty").val().trim().replaceAll(",",""),
    uom: $("#uom").val(),
    price_unit: $("#price_total").val().trim().replaceAll(",","") /  $("#purchase_qty").val().trim().replaceAll(",",""),
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
	fnpostajax(that,params,"purchasetable");	
	});
//*********************************************************
//Compare Purchase qty with Stock qty
function determine_stkqty(that){
//Validate Stock Qty
	var diff, qty;
	qty = $("#stock_qty").val().trim().replaceAll(",","");
	var stock_qty = parseFloat(qty);
	qty = that.defaultValue.trim().replaceAll(",","");
	var prevpur_qty = parseFloat(qty);
	qty = that.value.trim().replaceAll(",","");
	var curpur_qty = parseFloat(qty);
//Check whether Current Purchase qty changed
	if (stock_qty != "" && prevpur_qty != curpur_qty ){
//If Stock qty less than previous maintained purchase qty, then it
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
//If Stock Qty not been reduced from previous maintained Purchase qty,
//then pass current changed Purchase Qty to Stock Qty
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
	var price_total, taxpercent_total, amt, purchase_qty;
	if (that.id == "taxpercent_total"){
		amt = $("#price_total").val().trim().replaceAll(",","");
		price_total = parseFloat(amt);
		taxpercent_total = parseFloat(that.value);
		$("#taxamt_total")[0].value = (taxpercent_total * price_total) / 100; 
	} 
	if (that.id == "price_total"){
		amt = $("#taxpercent_total").val().trim().replaceAll(",","");
		taxpercent_total = parseFloat(amt);
		qty = $("#purchase_qty").val().trim().replaceAll(",","");
		purchase_qty = parseFloat(qty);
		amt = that.value.trim().replaceAll(",","");
		price_total = parseFloat(amt);
		fnaddseparator(that);
//Tax Amount Calculation
		$("#taxamt_total")[0].value = (price_total * taxpercent_total) / 100;
//Price Unit Calculation
		$("#price_unit")[0].value = (price_total / purchase_qty ) ;	
	}
}

//*********************************************************
//Clear 
$(document).on("click", "#btnpurchaseclear", function() {
// Clear Mandatory Border Color
	fntableclear("purchasetable");
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
	var me = $(this);
	var params = {subgroup_name: $("#subgroup_name").val()};
	if ( me.data('requestRunning') == params.subgroup_name) {
	    return;
	}
mydropdown(params,me);
		});	

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

function purchasecreate(){
	gettodaydate() ;
}
//*************************************************************************************
function purchasedisp(){
	gettodaydate() ;
	getmonthstart();
//Hide Display Content
	$("#scrolltable").hide();
}
//*************************************************************************************
$(document).on("click", "#btngodisplay", function(){
	//Clear Message
	$("#message").text(" ");
	//Display Purchase table
	displaypurchasetable();
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
//Display Purchase table
		displaypurchasetable();		
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
//Display Purchase table
function displaypurchasetable(){
	var params = {subgroup_name: $("#subgroup_name").val(),
		      purchasedate_from: $("#monthstart").val(),
	          purchasedate_to:   $("#todaydate").val()};

	var tabcontent = $("#tableview");
	$.get("purchaselistajax", ($.param(params)), function(responseJson) {
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
//Remaining Columns          
          row.children().eq(1).text(value['purchasedate']);
          row.children().eq(2).text(value['subgroup_name']);
          row.children().eq(3).text(value['purchase_qty']);
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
//Get/Set Element content "a"
          var a = row.children().eq(14)[0];
          var date = dateconvert(value['purchasedate']);
          var edithref = "purchaseedit?product=" + value['product'] + "&purchasedate=" + date;
          var deletehref = "purchasedeleteajax?product=" + value['product'] + "&purchasedate=" + date;
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
