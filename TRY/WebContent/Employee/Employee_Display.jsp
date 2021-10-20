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
	<!-- Set Attribute for Display in "Employee.jsp" -->
<% session.removeAttribute("sessemployeeevent"); %>
<% session.setAttribute("sessemployeeevent", "display");  %>
<jsp:include page="Employee.jsp"></jsp:include>
	
<!-- Employee Display  -->
<div class="container" align="center"> 
	<br>
<!-- Fuzzy Search -->
<input type="text" id="myInput" onkeyup="filtersearch()" placeholder="Search for Employee Name.." title="Type in a Employee">
<br><h6>${message}</h6><br>
<!-- Fuzzy Search -->  
 </div> 
    <div class="container" id="scrolltable" align="center"> 
<!-- id="tableview" is style to border the table: maintained in Page_Main.css -->
        <table id="tableview" border="1" >
            <tr id="tableview" style="padding:10px">
<!-- Hide "ID Column" <th id="tableview" style="padding:10px">Employee_ID</th> -->
                <th style="padding:10px; cursor:pointer" onclick="sortTable(0)">Employee Name <i class='fas fa-sort'></i></th>
                <th >Group</th>
                <th >Gender</th>
                <th >Job</th>
                <th >Salary</th>
                <th >Address</th>
                <th >Contact1</th>
                <th >Contact2</th>
                <th >ID Card Type</th>
                <th >ID Card Number</th>
                <th>Comments</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Changed By</th>
                <th>Changed Date</th>
                <th>Changed Time</th>
                <th id="cmaintain">Maintain</th>
            </tr>
            <c:forEach var="employee" items="${listEmployee}">
                <tr id="tableview" style="padding:10px">
<!-- Hide "ID Column"  <td id="tableview" style="padding:10px"><c:out value="${employee.id}" /></td> -->                      
                    <td  style="padding:10px"><c:out value="${employee.name}" /></td>
                    <td ><c:out value="${employee.group_name}" /></td>
                    <td ><c:out value="${employee.gender}" /></td>
                    <td ><c:out value="${employee.subgroup_name}" /></td>
                    <td ><c:out value="${employee.salary}" /></td>
                    <td ><c:out value="${employee.address}" /></td>
                    <td ><c:out value="${employee.contact1}" /></td>
                    <td ><c:out value="${employee.contact2}" /></td>
                    <td ><c:out value="${employee.idtype1}" /></td>
                    <td ><c:out value="${employee.idnum1}" /></td>
                    <td ><c:out value="${employee.comments}" /></td>
                    <td ><c:out value="${employee.joindate}" /></td>
                    <td ><c:out value="${employee.lastdate}" /></td>
                    <td ><c:out value="${employee.changedby}" /></td>
                    <td ><c:out value="${employee.changeddate}" /></td>
                    <td ><c:out value="${employee.changedtime}" /></td>
                    <td id="cmaintain">
<!-- id="linkedit" is style to text edit link: maintained in Page_Main.css -->                
     	<a id="linkedit" href="employeeedit?employee_id=<c:out value='${employee.id}' />">Edit</a>   
                     	&nbsp;&nbsp;&nbsp;&nbsp;             
 	<a id="linkedit" href="employeedelete?employee_id=<c:out value='${employee.id}' />">Delete</a>  	                     	
                    </td>
                </tr>
            </c:forEach>
        </table><br>        
    </div>
</c:if>		
</body>
</html>