//Based on reference: "https://www.w3schools.com/howto/howto_js_filter_table.asp"
//Filter Search on Table (Used in "_Display.jsp)
function filtersearch() {
	var input, filter, table, tr, td, i, txtValue;
	input = document.getElementById("myInput");
	filter = input.value.toUpperCase();
	table = document.getElementById("tableview");
	tr = table.getElementsByTagName("tr");
	for (i = 0; i < tr.length; i++) {
//		Change value of "tr[i].getElementsByTagName("td")[1]"
//		based on columns to be searched
		td = tr[i].getElementsByTagName("td")[0];
		if (td) {
			txtValue = td.textContent || td.innerText;
			if (txtValue.toUpperCase().indexOf(filter) > -1) {
				tr[i].style.display = "";
			} else {
				tr[i].style.display = "none";
			}
		}       
	}
}
//***************************************************************************************
//Sort Table Ascending & Descending (Used in "_Display.jsp)
//https://www.w3schools.com/howto/howto_js_sort_table.asp- Reference
function sortTable(n) {
	var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
	table = document.getElementById("tableview");
	switching = true;
	//Set the sorting direction to ascending:
	dir = "asc"; 
	/*Make a loop that will continue until
	  no switching has been done:*/
	while (switching) {
		//start by saying: no switching is done:
		switching = false;
		rows = table.rows;
		/*Loop through all table rows (except the
	    first, which contains table headers):*/
		for (i = 1; i < (rows.length - 1); i++) {
			//start by saying there should be no switching:
			shouldSwitch = false;
			/*Get the two elements you want to compare,
	      one from current row and one from the next:*/
			x = rows[i].getElementsByTagName("TD")[n];
			y = rows[i + 1].getElementsByTagName("TD")[n];
			/*check if the two rows should switch place,
	      based on the direction, asc or desc:*/
			if (dir == "asc") {
				if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
					//if so, mark as a switch and break the loop:
					shouldSwitch= true;
					break;
				}
			} else if (dir == "desc") {
				if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
					//if so, mark as a switch and break the loop:
					shouldSwitch = true;
					break;
				}
			}
		}
		if (shouldSwitch) {
			/*If a switch has been marked, make the switch
	      and mark that a switch has been done:*/
			rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
			switching = true;
			//Each time a switch is done, increase this count by 1:
			switchcount ++;      
		} else {
			/*If no switching has been done AND the direction is "asc",
	      set the direction to "desc" and run the while loop again.*/
			if (switchcount == 0 && dir == "asc") {
				dir = "desc";
				switching = true;
			}
		}
	}
}
//***************************************************************************************
//Access Function (it get Load on page under <body> tag: for Company.jsp, Group.jsp, etc(Similar)) 
function accessfunc() {
	var accessval = document.getElementById("useraccess")    ;
//	"useraccess" ID maintained in 'Header.jsp' file	
	if (accessval.innerText == "semi") {
		document.getElementById("btncreate").style.display = "none"
	}
	if (accessval.innerText == "display") {
		document.getElementById("btncreate").style.display = "none"
			document.getElementById("btnchange").style.display = "none"
				document.getElementById("cmaintain").style.display = "none"	    	 
//					Make column 'Maintain' hide
					var table, tr,td;
		table = document.getElementById("tableview");
		tr = table.getElementsByTagName("tr");
		for (i = 1; i < tr.length; i++) {
			tds = tr[i].getElementsByTagName("td");
			tds.cmaintain.style.display = "none"
		}

	}
	if (accessval.innerText == "end") {
//		End User - Master Data is completely hided 
		document.getElementById("Master").style.display = "none"
	}	 
//	Hide Display Content
	// $("#divcompdisplay").hide();
	// $("#divcompdisplay1").hide();
}
//*************************************************************************************
//Get Today
function fngettoday() {
	var date = new Date();
	//Date
	var day = date.getDate();
	var month = date.getMonth() + 1;
	var year = date.getFullYear();

	if (month < 10) month = "0" + month;
	if (day < 10) day = "0" + day;	

	var today = year + "-" + month + "-" + day;   
	return today;
}
//*************************************************************************************
function fnmonthstart(){
	var today = fngettoday();
	var monthstart = today.substring(0,8).concat("01");
	return monthstart;
}
//*************************************************************************************
function fngetcurrenttime(){
	var date = new Date();
	//Time
	var hh = date.getHours();
	var mm = date.getMinutes();
	var ss = date.getSeconds();	

	if (hh < 10) hh = "0" + hh;
	if (mm < 10) mm = "0" + mm;
	if (ss < 10) ss = "0" + ss;

	var currentTime = hh + ':' + mm + ':' + ss;
	return currentTime;
}
//*************************************************************************************
//Get Current Date
function gettodaydate() {
	var today = fngettoday();

//	Update Today's date
	document.getElementById("todaydate").value = today;
	document.getElementById("todaydate").setAttribute("max", today);
}
//*************************************************************************************
//Get Month Start date
function getmonthstart() {

	var monthstart = fnmonthstart();
//	Update Month start date
	document.getElementById("monthstart").value = monthstart;
}
//*************************************************************************************
//Add Commas
function fnaddseparator(that){

	if (that.value.trim() != "") {
		var remvcomma = that.value.replaceAll(",","");
		var number = parseFloat(remvcomma);	//Convert to Number
		that.value = number.toLocaleString('en-US', {minimumFractionDigits: 2});
	}
}
//*************************************************************************************
//Date Conversion
function dateconvert(fpdate){
	var date = new Date(fpdate);
	var retdate = null;

	var day = date.getDate();
	var month = date.getMonth() + 1;
	var year = date.getFullYear();	

	if (month < 10) month = "0" + month;
	if (day < 10) day = "0" + day;	

	retdate = year + "-" + month + "-" + day;
	return retdate;

}
//*********************************************************
//Get Previous Month Date Range(Start & End Date)
function fngetprevdtrange(fpdate){
	var date = new Date(fpdate);
	var retsdate = null, retedate = null;

	var lastMonth = date.getMonth() == 0 ? 12 : date.getMonth() - 0;
	var year = lastMonth == 12 ? date.getFullYear() - 1 : date.getFullYear();

	retedate = new Date(year, lastMonth, 0); //End Date
	retsdate = new Date(year, lastMonth - 1, 1); //Start Date
	retedate = dateconvert(retedate);
	retsdate = dateconvert(retsdate);

	return [retsdate, retedate];						
}
//*********************************************************
//*********************************************************
function fnpostajax(that,params,fptable){

//	Clear Message
	$("#message").text(" ");
//	Validation
	var i,n, td,tds, process;	
	var table = document.getElementById(fptable);
	var tr = table.getElementsByTagName("tr");    
//	Do not post entry, if required field is "Empty" 
	process = true; 
	for (i = 0; i < tr.length; i++) {
		//td = tr[i].getElementsByTagName("td")[1];
		tds = tr[i].getElementsByTagName("td");
		for (n = 1; n < tds.length; n++) {
			td = tds[n];
			n++;
			if (td){
				//if (td.childNodes[0].style.borderColor = "red"){
				td.childNodes[0].style.borderColor = "";
				//	}
				if (td.childNodes[0].required == true && 
						(td.childNodes[0].value.trim() == "" || td.childNodes[0].value.trim().length < 1)) {
					process = false;
					td.childNodes[0].style.borderColor = "red";
					td.childNodes[0].focus();
					break;	 
				}
			}
		}
		if (process == false){break;}
	}
	if (process == true) {
//		Get 'Action' maintained in 'Form'
		var actionpos = that.form.action.lastIndexOf("/");
		actionpos++;   
//		Get Method
		var method = that.form.method; 
//		Call Servlet    
		$.post(that.form.action.substr(actionpos),  ($.param(params)), function(responseText) {   
			$("#message").text(responseText);
			document.getElementById("message").style.color = "#00ff00";
		});           
	}else {
		$("#message").text("Enter Required field!!!");
		document.getElementById("message").style.color = "#98052e";
	};
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
//**************************************************************************************
//Clear / Reset
function fntableclear(fptable){
	// Clear Message
	$("#message").text(" ");
	var i, td;
	var table = document.getElementById(fptable);
	var tr = table.getElementsByTagName("tr"); 
	for (i = 0; i < tr.length; i++) {
		//td = tr[i].getElementsByTagName("td")[1];
		tds = tr[i].getElementsByTagName("td");
		for (n = 1; n < tds.length; n++) {
			td = tds[n];
			n++;
			if (td){
				if (td.childNodes[0].style.borderColor = "red"){
					td.childNodes[0].style.borderColor = "";
				} } }
	}

}
//**************************************************************************************
//Modal Pop-up View with Selected Row 
$(document).on("click", "#btnrowpopup", function(e){
//	Get Table Header
	var i;
	var maintable = document.getElementById("tableview");
	var mainrow = maintable.getElementsByTagName("tr");
	var maintabh = mainrow[0].getElementsByTagName("th");
	var main_i = maintabh.length - 1; 
//	Get Selected Table Row 
	that = this;
	var selrow = $(e.currentTarget)[0].parentElement;
//	Modal - Popup
	var modalcontent = $("#tblpopuprow");	
	$("#tblpopuprow").find("tr").remove();
	for (i = 0; i < main_i ; i++) {
		var row = $("<tr><td></td><td></td></tr>");
		row.eq(0)[0].id = "tblpopuprow";
		row.children().eq(0).text(maintabh[i].textContent);
		row.children().eq(1).text(selrow.cells[i].textContent)
		row.appendTo(modalcontent);
	}
//	Show Pop-up
	$('#myModal').modal('show');
});
//**************************************************************************************
//Get No. of Days in Month
function daysInMonth (fpdate) {
	var date = new Date(fpdate);

	//var day = date.getDate();
	var month = date.getMonth() + 1;
	var year = date.getFullYear();

	return new Date(year, month, 0).getDate();
}

//**************************************************************************************
//Get First & Last Date of Month in a Year
function fngetmthdtrange(fpmthyr){
	var date = new Date(fpmthyr);
	var month = date.getMonth() + 1;
	var year = date.getFullYear();

	retedate = new Date(year, month, 0); //End Date
	retsdate = new Date(year, month - 1, 1); //Start Date
	retedate = dateconvert(retedate);
	retsdate = dateconvert(retsdate);

	return [retsdate, retedate];
}
//**************************************************************************************
//Get Month & Year
function fngetmthyear(fpmthyr){
	var date = new Date(fpmthyr);
	var month = date.getMonth() + 1;
	var year = date.getFullYear();

	return [month, year];
}

//**************************************************************************************