//Insert an entry in table on button click
$(document).on("click", "#btnsalepriceinsert", function(){
//get Date/Time
getdatetime();
	var params = {date: $("#todaydate").val(),
		      product: {product : $("#product option:selected").text()}.product,
		      subgroup_name: $("#subgroup_name").val(),
		      sell_price: $("#sell_price").val().trim().replaceAll(",",""), //remove Commas
      uom: {product : $("#product option:selected").val()}.product,
      taxpercent_total: $("#taxpercent_total").val(),
      taxamt_total: (($("#taxpercent_total").val() * $("#sell_price").val().trim().replaceAll(",","")) / 100 ), 
      comments: $("#comments").val().trim(),
      changedby:$("#changedby").val(),
      changeddate:$("#changeddate").val(),
      changedtime:$("#changedtime").val()};

if (params.date > document.getElementById("todaydate").max) {
	$("#message").text("Error!!!Saleprice Date CANNOT be greater than Today");
	document.getElementById("message").style.color = "#98052e";
	return;
}
//postajax
that = this;
fnpostajax(that,params,"salepricetable");
	});

//******************************************************************************
//Update Saleprice
$(document).on("click", "#btnsalepriceupdate", function(){
//get Date/Time
	getdatetime();
//Parameters
	var params = {date: $("#date").val(),
		      product: $("#product").val(),
		      subgroup_name: $("#subgroup_name").val(),
		      sell_price: $("#sell_price").val().trim().replaceAll(",",""),
    uom: $("#uom").val(),
    taxpercent_total: $("#taxpercent_total").val(),
    taxamt_total: (($("#taxpercent_total").val() * $("#sell_price").val().trim().replaceAll(",","")) / 100 ), 
    comments: $("#comments").val().trim(),
    changedby: $("#username").val(),
    changeddate:$("#changeddate").val(),
    changedtime:$("#changedtime").val()};
//postajax
	that = this;
	fnpostajax(that,params,"salepricetable");	
	});
//*********************************************************
//Tax amount Calculation
function amt_calc(that){
	var sell_price, taxpercent_total, amt, saleprice_qty;
	if (that.id == "taxpercent_total"){
		amt = $("#sell_price").val().trim().replaceAll(",","");
		sell_price = parseFloat(amt);
		taxpercent_total = parseFloat(that.value);
		$("#taxamt_total")[0].value = (taxpercent_total * sell_price) / 100; 
	} 
	if (that.id == "sell_price"){
		amt = $("#taxpercent_total").val().trim().replaceAll(",","");
		taxpercent_total = parseFloat(amt);
		amt = that.value.trim().replaceAll(",","");
		sell_price = parseFloat(amt);
//Tax Amount Calculation
		$("#taxamt_total")[0].value = (sell_price * taxpercent_total) / 100;	
	}
}

//*********************************************************
//Clear 
$(document).on("click", "#btnsalepriceclear", function() {
// Clear Mandatory Border Color
	fntableclear("salepricetable");
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

function salepricecreate(){
	gettodaydate() ;
}
//*************************************************************************************
function salepricedisp(){
	gettodaydate() ;
	getmonthstart();
//Hide Display Content
	$("#scrolltable").hide();
}
//*************************************************************************************
$(document).on("click", "#btngodisplay", function(){
	//Clear Message
	$("#message").text(" ");
	//Display Saleprice table
	displaysalepricetable();
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
//Display Saleprice table
		displaysalepricetable();		
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
//Display Saleprice table
function displaysalepricetable(){
	var params = {subgroup_name: $("#subgroup_name").val(),
		      date_from: $("#monthstart").val(),
	          date_to:   $("#todaydate").val()};

	var tabcontent = $("#tableview");
	$.get("salepricelistajax", ($.param(params)), function(responseJson) {
		$("#tableview").find("tr:gt(0)").remove();
		$.each(responseJson, function(key, value) {
          var row = $("<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>" +
          		"<td><a>Edit</a>&nbsp;&nbsp;&nbsp;&nbsp;<input/></td></tr>");
          row.eq(0)[0].id = "tableview";
          row.children().eq(0).text(value['product']);
//Set Pop-up Click for First Column
          var modal = row.children().eq(0)[0];
          modal.id = "btnrowpopup";
          modal.setAttribute("type", "button");
//Remainingn Columns          
          row.children().eq(1).text(value['date']);
          row.children().eq(2).text(value['subgroup_name']);
          row.children().eq(3).text(value['sell_price']);
          row.children().eq(4).text(value['uom']);
          row.children().eq(5).text(value['taxpercent_total']);
          row.children().eq(6).text(value['taxamt_total']);
          row.children().eq(7).text(value['comments']);
          row.children().eq(8).text(value['changedby']);
          row.children().eq(9).text(value['changeddate']);
          row.children().eq(10).text(value['changedtime']); 
//Get/Set Element content "a"
          var a = row.children().eq(11)[0];
          var date = dateconvert(value['date']);
          var edithref = "salepriceedit?product=" + value['product'] + "&date=" + date;
          var deletehref = "salepricedeleteajax?product=" + value['product'] + "&date=" + date;
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
