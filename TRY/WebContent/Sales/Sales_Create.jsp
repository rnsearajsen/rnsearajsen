<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
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
<script type="text/javascript" src="script/customscript.js"> </script>	
<script type="text/javascript" src="script/salesscript.js"> </script>	
<script type="text/javascript" src="script/pumpreadscript.js"> </script>	
</head>
<body class="bg" onload="salescreate()">
<c:if test="${user != null}">
	<!-- Background Image - flew from CSS(Page_Main.css) -->
	<!-- Row 1 (Header) -->
	<jsp:include page="/Header.jsp"></jsp:include>

	<!-- Row 2 (Header) -->
	<!-- Horizontal Navigation -->
	<jsp:include page="/Hor_Navigation.jsp"></jsp:include>

<!--  -->
<% session.removeAttribute("sesssalesevent"); %>
	<!-- Set Attribute "Sales.jsp" -->
<% session.setAttribute("sesssalesevent", "create");  %>
<jsp:include page="Sales.jsp"></jsp:include>
<!--  -->

<!-- Create Sales Form -->	
		<br>
<!-- Choose option/Sales Date -->
<div class="container" align="center">
<table>
<tr>
<!-- Sub Group -->
<td>Sub-Group</td>
<td><select id="subgroup_name">
<!--             <option value="all">All</option>  -->
            <c:forEach items="${listSub_Group}" var="sub_group">
                <option value="${sub_group.fsub_group}"> ${sub_group.fsub_group} </option> 
            </c:forEach>
    </select> </td>
<!-- Shift Change-->
<td>Shift: <select id="shift">
            <c:forEach items="${listShift}" var="shift">
                <option value="${shift.fsub_group}"> ${shift.fsub_group} </option> 
            </c:forEach>
</select></td>    
<!-- Sales Date  -->
<td>Sales Date: <input type="date" id="salesdate" value="${lastsalesdate}" onchange="salesdatechg(this)" required/></td>

<td><input type="button" id="btnsalesentry" class="btn btn-primary" value="Sales Entry" /></td>
</tr>
</table> 
</div>
<!-- Message -->		
		<div class="container" id="message"></div><br>
	<div id="scrolltable" class="container">
<!-- Create Sales -->	
<!-- FUEL Sales -->
<!-- <form action="pumpreadinsert">  -->
		<table id="pumpreadtable" border="1">
<!-- Product Choose   -->
<thead>
		<tr>
<!-- First Column -->
					<td >Product:<i class="fa fa-asterisk"></i></td>	
					<td><select id="product" required></select></td>	
<!--Second Column Sales Price -->
                   <td >Sales Price:<i class="fa fa-asterisk"></i></td>	
				   <td><select id="salesprice_fuel" required></select></td>
<!-- Third Column - Button -->
<td><button type="button" id=btnfuelgo class="btn btn-primary">Go</button></td>				   	
		</tr>
		<tr><td>Test Qty</td>
		<td><input type="number" id="testqty"></td></tr>
		<tr id="tableview" ><th>Pump</th>
		    <th>Open Reading</th>
		    <th>Close Reading</th>
		    <th>Sales Price</th>
		    <th>Sales Qty</th>
		    <th>Net Price</th>
		    </tr>	
</thead>		    	
</table>    
<!-- Remove Attribute Value -->
<% session.removeAttribute("sesssalesevent"); %>		
<!-- </form>  -->		
<!-- Packet Oil Sales  -->
		<table id="pktoiltable" border="1">
<thead>		
		<tr><td><br></td></tr>
		<tr id="tableview" ><th>Packet Oil</th>
		    <th>Open Stock</th>
		    <th>Sales Qty</th>
		    <th>Close Stock</th>		    
		    <th>Sales Price</th>
		    <th>Net Price</th></tr>
</thead>
			</table>
<!-- Engine Oil Sales  -->
		<table id="engoiltable" border="1">
<thead>		
		<tr><td><button type="button" id="btnaddengoil" class="btn btn-success">ADD NEW ROW</button></td></tr>
		<tr id="tableview" ><th>Engine Oil</th>
		    <th>Open Stock</th>
		    <th>Sales Qty</th>
		    <th>Close Stock</th>		    
		    <th>Sales Price</th>
		    <th>Net Price</th>
		    <th>Remove</th></tr>
</thead>
			</table>			
	</div>
</c:if>	
</body>
</html>