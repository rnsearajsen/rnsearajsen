<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="./CSS/Page_Main.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="script/billpurpay.js"> </script>
<script type="text/javascript" src="script/customscript.js"> </script>	
<script>
	    var listBillprds = [], listBillprd, g_amtinhand;
		<c:forEach var="billprd" items="${listBillprd}">
		    listBillprd = { billprd: '${billprd.fsub_group}' };
		    listBillprds.push(listBillprd);  
		    g_amtinhand = '${billprd.amtinhand}';
		</c:forEach>
		g_amtinhand = parseFloat(g_amtinhand);
</script>
</head>
<body class="bg" onload="fbillpurpay()">
	<c:if test="${user != null}">

		<!-- Background Image - flew from CSS(Page_Main.css) -->
		<!-- Row 1 (Header) -->
		<jsp:include page="/Header.jsp"></jsp:include>

		<!-- Row 2 (Navigation) -->
		<!-- Horizontal Navigation -->
		<jsp:include page="/Hor_Navigation.jsp"></jsp:include>

		<!-- Row 3 (Process) -->
		<div class="container" class="row">
			<h4 class="text-center">Purchase and Payment</h4>
		</div>
		<!-- Message -->
		<div class="container" id="message"></div>
		<br>
		<div id="scrolltable" class="container">
			<!-- Purchase Bill -->
			<table id="billpurpaytable" border="1">
				<thead>
					<tr>
						<td><button type="button" id="btnaddinc"
								class="btn btn-success">ADD NEW ROW</button></td>
						<td></td>
						<td>Amount in Hand:</td>
						<td><input type="number" id="bamtinhand" disabled="disabled"></input></td>
					</tr>
					<tr id="tableview">
						<th>Bill No <i class="fa fa-asterisk"></i></th>
						<th>Bill Date <i class="fa fa-asterisk"></i></th>
						<th>Bill Product <i class="fa fa-asterisk"></i></th>
						<th>Bill Amount <i class="fa fa-asterisk"></i></th>
						<th>Paid Amount</th>
						<th>Discount</th>
						<th>Latepay Interest</th>
						<th>Payment Amt</th>						
						<th>Comments</th>
						<th>Remove</th>
					</tr>
				</thead>
			</table>
		</div>
	</c:if>
</body>
</html>