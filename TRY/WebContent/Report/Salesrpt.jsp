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
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<script type="text/javascript" src="script/customscript.js"></script>
<script type="text/javascript" src="script/salesreport.js"></script>
</head>
<body class="bg" onload="rptinitial()">
	<c:if test="${user != null}">
		<!-- Background Image - flew from CSS(Page_Main.css) -->
		<!-- Row 1 (Header) -->
		<jsp:include page="/Header.jsp"></jsp:include>

		<!-- Row 2 (Header) -->
		<!-- Horizontal Navigation -->
		<jsp:include page="/Hor_Navigation.jsp"></jsp:include>
		<!--Header Text-->
		<h4 class="text-center">Sales Report</h4>
		<!-- Row 3 (Header) -->
		<div class="container" class="row" align="center">
			<table>
				<tr>
					<!-- Column1 -->
					<td>Report type</td>
					<!-- Column2 -->
					<td><select id="report_type" onchange="rptypechg(this)">
							<option value="daily">Daily</option>
							<option value="monthly">Monthly</option>
							<option value="yearly">Yearly</option>
					</select></td>
					<!-- Column3 - Month Dropdown-->
					<td><input type="month" id="ddmonth" required /></td>
					<!-- Column4 - Year Dropdown-->
					<td><select id="ddyear">
							<!-- Getting "syear" from 'ReportControllerServlet' -->
							<c:forEach var="year" begin="2010" end="${syear}" step="1"
								varStatus="Yearinc">
								<option value="$year">${year}</option>
							</c:forEach>
					</select></td>
					<!-- Sub Group -->
					<td>Sub-Group</td>
					<td><select id="subgroup_name">
							<!--             <option value="all">All</option>  -->
							<c:forEach items="${listSub_Group}" var="sub_group">
								<option value="${sub_group.fsub_group}">
									${sub_group.fsub_group}</option>
							</c:forEach>
					</select></td>
					<!-- Column 5 - Button-->
					<!-- Getting "btnrptgo" from 'ReportControllerServlet' -->
					<td><input type="button" id="${btnrptgo}"
						class="btn btn-primary" value="Go" /></td>
				</tr>
			</table>
			<table id="salesrptable" border="1">
				<thead>
					<tr>
						<td><br></td>
					</tr>
					<tr id="tableview">
						<th id="salesprd" style="background-color: #98052e; color: white"></th>
						<th>Sales Qty</th>
						<th>Sales Price</th>
						<th hidden=true>Test Qty</th>
						<th id="salesprd1" style="background-color: #98052e; color: white"
							hidden=true></th>
						<th hidden=true>Sales Qty</th>
						<th hidden=true>Sales Price</th>
						<th hidden=true>Test Qty</th>
						<th id="salesprd2" style="background-color: #98052e; color: white"
							hidden=true></th>
						<th hidden=true>Sales Qty</th>
						<th hidden=true>Sales Price</th>
						<th hidden=true>Test Qty</th>
					</tr>
				</thead>
			</table>
		</div>
	</c:if>
</body>
</html>