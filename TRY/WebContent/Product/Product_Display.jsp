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
</head>
<body class="bg">
<c:if test="${user != null}">
	<!-- Background Image - flew from CSS(Page_Main.css) -->
	<!-- Row 1 (Header) -->
	<jsp:include page="/Header.jsp"></jsp:include>

	<!-- Row 2 (Header) -->
	<!-- Horizontal Navigation -->
	<jsp:include page="/Hor_Navigation.jsp"></jsp:include>

	<!-- Row 3 (Content) -->
	<!-- Set Attribute for Display in "Product.jsp" -->
<% session.removeAttribute("sessproductevent"); %>
<% session.setAttribute("sessproductevent", "display");  %>
<jsp:include page="Product.jsp"></jsp:include>
	
<!-- Product Display  -->
<div class="container" align="center"> 
	<br>
<!-- Fuzzy Search -->
<input type="text" id="myInput" onkeyup="filtersearch()" placeholder="Search for Product Name.." title="Type in a Product">
<br><h6>${message}</h6><br>
<!-- Fuzzy Search -->  
 </div> 
    <div class="container" id="scrolltable" align="center"> 
<!-- id="tableview" is style to border the table: maintained in Page_Main.css -->
        <table id="tableview" border="1" >
            <tr id="tableview" style="padding:10px">
<!-- Hide "ID Column" <th id="tableview" style="padding:10px">Product_ID</th> -->
                <th style="padding:10px; cursor:pointer" onclick="sortTable(0)">Product Name <i class='fas fa-sort'></i></th>
                <th >Group</th>
                <th >Sub-Group</th>
                <th >UoM</th>
                <th>Comments</th>
                <th>Changed By</th>
                <th>Changed Date</th>
                <th>Changed Time</th>
                <th>Created Date</th>
                <th id="cmaintain">Maintain</th>
            </tr>
            <c:forEach var="product" items="${listProduct}">
                <tr id="tableview" style="padding:10px">
<!-- Hide "ID Column"  <td id="tableview" style="padding:10px"><c:out value="${product.id}" /></td> -->                      
                    <td  style="padding:10px"><c:out value="${product.name}" /></td>
                    <td ><c:out value="${product.group_name}" /></td>
                    <td ><c:out value="${product.subgroup_name}" /></td>
                    <td ><c:out value="${product.uom}" /></td>
                    <td ><c:out value="${product.comments}" /></td>
                    <td ><c:out value="${product.changedby}" /></td>
                    <td ><c:out value="${product.changeddate}" /></td>
                    <td ><c:out value="${product.changedtime}" /></td>
                    <td ><c:out value="${product.createddate}" /></td>
                    <td id="cmaintain">
<!-- id="linkedit" is style to text edit link: maintained in Page_Main.css -->                
     	<a id="linkedit" href="productedit?product_id=<c:out value='${product.id}' />">Edit</a>   
                     	&nbsp;&nbsp;&nbsp;&nbsp;             
 	<a id="linkedit" href="productdelete?product_id=<c:out value='${product.id}' />">Delete</a>  	                     	
                    </td>
                </tr>
            </c:forEach>
        </table><br>        
    </div>
</c:if>		
</body>
</html>