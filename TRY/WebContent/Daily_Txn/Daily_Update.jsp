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
<script type="text/javascript" src="script/dailytxn.js"> </script>
<script type="text/javascript" src="script/creditcust.js"> </script>
<script type="text/javascript" src="script/creditowner.js"> </script>
<script type="text/javascript" src="script/empwages.js"> </script>
<script type="text/javascript" src="script/othincome.js"> </script>		
<script type="text/javascript" src="script/collection.js"> </script>
<script type="text/javascript" src="script/ovrallupd.js"> </script>
<script>
	    var listMops = [], listMop;
		<c:forEach var="mop" items="${listMop}">
		    listMop = { mop: '${mop.fsub_group}' };
		    listMops.push(listMop);                                  
		</c:forEach>
</script>
</head>
<body class="bg" onload="dailytxn()">
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
<% session.setAttribute("sesssalesevent", "dlytxnupd");  %>
<jsp:include page="/Sales/Sales.jsp"></jsp:include>
<!--  -->		
<!-- Create Daily Txn update Form -->	
		<br>
<!-- Choose option/Sales Date -->
<div class="container" align="center">
<table>
<tr>
<!-- Sub Group -->
<td>Txn Update</td>
<td><select id="subgroup_name">
             <option value="expense">Expense</option>
             <option value="customer">Customer</option>
             <option value="owner">Owner</option>
             <option value="wages">Wages</option>   
             <option value="debit">Other Income</option>
             <option value="collect">Collection Amt</option>
             <option value="overall">Overall Update</option>              
    </select> </td>
<!-- Shift Change-->
<td>Shift: <select id="shift">
            <c:forEach items="${listShift}" var="shift">
                <option value="${shift.fsub_group}"> ${shift.fsub_group} </option> 
            </c:forEach>
            <option value="general">General</option>
</select></td>    
<!-- Sales Date  -->
<td>Sales Date: <input type="date" id="txndate" value="${lastsalesdate}" onchange="salesdatechg(this)" required/></td>

<td><input type="button" id="btndtxnentry" class="btn btn-primary" value="Txn Entry" /></td>
</tr>
</table> 
</div>
<!-- Message -->		
		<div class="container" id="message"></div><br>
	<div id="scrolltable" class="container">
<!-- Daliy Txn Update -->	
<!-- Expense Update -->
		<table id="expensetable" border="1">
<!-- Expense Type Choose   -->
<thead>
		<tr><td><button type="button" id="btnaddexpense" class="btn btn-success">ADD NEW ROW</button></td>
		<td></td><td>Amount in Hand:</td><td><input type="number" id="amtinhand" disabled="disabled"></input></td></tr>
		<tr id="tableview" ><th>Expense Type</th>
		    <th>Expense Amt</th>
		    <th>Cash Handle Expense</th>		    
		    <th>Comments</th>
		    <th>Remove</th>
		    </tr>	
</thead>		    	
</table>    
<!-- Remove Attribute Value -->
<% session.removeAttribute("sesssalesevent"); %>		
<!-- </form>  -->		
<!-- Customer Credit/Debit Update  -->
		<table id="customertable" border="1">
<thead>		
		<tr><td><button type="button" id="btnaddcustomer" class="btn btn-success">ADD NEW ROW</button></td>
		<td></td><td>Amount in Hand:</td><td><input type="number" id="camtinhand" disabled="disabled"></input></td></tr>
		<tr id="tableview" ><th>Customer</th>
		    <th>Current Credit</th>
		    <th>Credit Amount</th>
		    <th>Debit Amount</th>
		    <th>Mode of Pay</th>
		    <th>Cash Handle Credit</th>
		    <th>Cash Handle Debit</th>		    
		    <th>Aggr.Credit</th>
		    <th>Comments</th>
		    <th>Remove</th></tr>		    
</thead>
			</table>
<!-- Owner Txn  -->
		<table id="ownertable" border="1">
<thead>		
		<tr><td><button type="button" id="btnaddowner" class="btn btn-success">ADD NEW ROW</button></td>
		<td></td><td>Amount in Hand:</td><td><input type="number" id="wamtinhand" disabled="disabled"></input></td></tr>
		<tr id="tableview" ><th>Owner</th>
		    <th>Current Credit</th>
		    <th>Credit Amount</th>
		    <th>Debit Amount</th>
		    <th>Mode of Pay</th>
		    <th>Cash Handle Credit</th>
		    <th>Cash Handle Debit</th>
		    <th>Aggr.Credit</th>
		    <th>Comments</th>
		    <th>Remove</th></tr>
</thead>
			</table>	
<!-- Employee Txn  -->
		<table id="emptable" border="1">
<thead>		
		<tr><td><button type="button" id="btnaddemp" class="btn btn-success">ADD NEW ROW</button></td>
		<td></td><td>Amount in Hand:</td><td><input type="number" id="eamtinhand" disabled="disabled"></input></td></tr>
		<tr id="tableview" ><th>Employee</th>
		    <th>Position</th>
		    <th>Monthly Salary</th>
		    <th>Attendance</th>
		    <th>Credit Amount</th>
		    <th>Debit Amount</th>
		    <th>Cash Handle Credit</th>
		    <th>Cash Handle Debit</th>
		    <th>Actual Salary</th>		    
		    <th>Remove</th></tr>
</thead>
			</table>
<!-- Other Debit Incomes -->			
		<table id="inctable" border="1">
<thead>		
		<tr><td><button type="button" id="btnaddinc" class="btn btn-success">ADD NEW ROW</button></td>
		<td></td><td>Amount in Hand:</td><td><input type="number" id="damtinhand" disabled="disabled"></input></td></tr>
		<tr id="tableview" ><th>Income Type</th>
		    <th>Amount</th>
		    <th>Mode of Pay</th>
		    <th>Cash Handle Amount</th>
		    <th>Comments</th>
		    <th>Remove</th></tr>
</thead>
			</table>
<!-- Collection Update -->
		<table id="collecttable" border="1">
<!-- Collection Type Choose   -->
<thead>
		<tr><td><button type="button" id="btnaddcollect" class="btn btn-success">ADD NEW ROW</button></td>
		<td></td><td>Amount in Hand:</td><td><input type="number" id="ctamtinhand" disabled="disabled"></input></td></tr>
		<tr id="tableview" ><th>Collection Type</th>
		    <th>Collected Amt</th>
		    <th>Collection Amt</th>		    
		    <th>Comments</th>
		    <th>Remove</th>
		    </tr>	
</thead>		    	
</table>				
<!-- Overall Update -->			
		<table id="ovratable" border="1">    
<!--     <tr><td><br></td></tr>  -->		
<thead>	
<tr><td>Actual Collection Amount</td><td><input type="number" id="actualamt" onchange="ovrallrowupd(this)"/></td>
    <td><button type="button" id="btnamtupd" class="btn btn-success">Update</button></td><td></td><td>Amt in Hand</td>
    <td><input type="number" id="csamtinhand" disabled="disabled"></input></td></tr>
	<tr id="tableview"><th>Total Sales</th><th>Total Qty</th><th>Total Amount</th><th></th>
	                   <th>Miscellaneous</th><th>Total Amount</th></tr>
</thead>
			</table>																							
	</div>
</c:if>	
</body>
</html>