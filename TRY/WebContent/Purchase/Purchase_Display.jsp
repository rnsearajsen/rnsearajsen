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
<script src='https://kit.fontawesome.com/a076d05399.js'></script>	
<script type="text/javascript" src="script/customscript.js"> </script>		
<script type="text/javascript" src="script/purchasescript.js"> </script>
</head>
<body class="bg" onload="purchasedisp()">
<c:if test="${user != null}">
	<!-- Background Image - flew from CSS(Page_Main.css) -->
	<!-- Row 1 (Header) -->
	<jsp:include page="/Header.jsp"></jsp:include>

	<!-- Row 2 (Header) -->
	<!-- Horizontal Navigation -->
	<jsp:include page="/Hor_Navigation.jsp"></jsp:include>

	<!-- Row 3 (Content) -->
	<!-- Set Attribute for Display in "Purchase.jsp" -->
<% session.removeAttribute("sesspurchaseevent"); %>
<% session.setAttribute("sesspurchaseevent", "display");  %>
<jsp:include page="Purchase.jsp"></jsp:include>
	
<!-- Purchase Display  -->
<div class="container" align="center"> 
<table>
<tr>
<td>Sub-Group</td>
<td><select id="subgroup_name">
            <option value="all">All</option>
            <c:forEach items="${listSub_Group}" var="sub_group">
                <option value="${sub_group.fsub_group}"> ${sub_group.fsub_group} </option> 
            </c:forEach>
    </select> </td>
<!-- Date Range -->
<td>Date Range</td>
<td>From: <input type="date" id="monthstart" required/></td>
<td>To: <input type="date" id="todaydate" required/></td>
<td><input type="button" id="btngodisplay" class="btn btn-primary" value="Go(Refresh)" /></td>
</tr>
</table>    
	
<div id="message"></div><br>
<!-- Fuzzy Search -->
<input type="text" id="myInput" onkeyup="filtersearch()" placeholder="Search for Product Name.." title="Type in a Product">
<br><br>
<!-- Fuzzy Search -->  
 </div> 
    <div class="container" id="scrolltable" align="center"> 
<!-- id="tableview" is style to border the table: maintained in Page_Main.css -->
        <table id="tableview" border="1" >
            <tr id="tableview" style="padding:10px">
<!-- Hide "ID Column" <th id="tableview" style="padding:10px">Purchase_ID</th> -->
                <th style="padding:10px; cursor:pointer" onclick="sortTable(0)">Product <i class='fas fa-sort'></i></th>
                <th >Purchase Date</th>
                <th >Sub-Group</th>
                <th >Purchase Qty</th>
                <th >Stock Qty</th>
                <th >UoM</th>
                <th >Unit Price</th>
                <th >Total Price</th>
                <th >Tax %</th>
                <th >Total Tax Amt</th>
                <th>Comments</th>
                <th>Changed By</th>
                <th>Changed Date</th>
                <th>Changed Time</th>
                <th id="cmaintain">Maintain</th>
            </tr>            
        </table><br>        
    </div>
</c:if>		
<!-- Modal Pop -->
  <!-- Modal -->
  <jsp:include page="/Common/Rowviewpopup.jsp"></jsp:include> 
</body>
</html>