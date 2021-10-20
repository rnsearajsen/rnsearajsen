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
	<!-- Set Attribute for Display in "Bankacct.jsp" -->
<% session.removeAttribute("sessbankacctevent"); %>
<% session.setAttribute("sessbankacctevent", "display");  %>
<jsp:include page="Bankacct.jsp"></jsp:include>
	
<!-- Bankacct Display  -->
<div class="container" align="center"> 
	<br>
<!-- Fuzzy Search -->
<input type="text" id="myInput" onkeyup="filtersearch()" placeholder="Search for Bankacct Name.." title="Type in a Bankacct">
<br><h6>${message}</h6><br>
<!-- Fuzzy Search -->  
 </div> 
    <div class="container" id="scrolltable" align="center"> 
<!-- id="tableview" is style to border the table: maintained in Page_Main.css -->
        <table id="tableview" border="1" >
            <tr id="tableview" style="padding:10px">
<!-- Hide "ID Column" <th id="tableview" style="padding:10px">Bankacct_ID</th> -->
                <th style="padding:10px; cursor:pointer" onclick="sortTable(0)">Account Holder<i class='fas fa-sort'></i></th>
                <th >Group</th>
                <th >Sub-Group</th>
                <th >Bank</th>
                <th >Acct Type</th>
                <th >IFSC</th>
                <th >Account No:</th>
                <th>Comments</th>
                <th>Changed By</th>
                <th>Changed Date</th>
                <th>Changed Time</th>
                <th>Created Date</th>
                <th id="cmaintain">Maintain</th>
            </tr>
            <c:forEach var="bankacct" items="${listBankacct}">
                <tr id="tableview" style="padding:10px">
<!-- Hide "ID Column"  <td id="tableview" style="padding:10px"><c:out value="${bankacct.id}" /></td> -->                      
                    <td  style="padding:10px"><c:out value="${bankacct.name}" /></td>
                    <td ><c:out value="${bankacct.group_name}" /></td>
                    <td ><c:out value="${bankacct.subgroup_name}" /></td>
                    <td ><c:out value="${bankacct.bank}" /></td>
                    <td ><c:out value="${bankacct.acctype}" /></td>
                    <td ><c:out value="${bankacct.ifsc}" /></td>
                    <td ><c:out value="${bankacct.accountno}" /></td>
                    <td ><c:out value="${bankacct.comments}" /></td>
                    <td ><c:out value="${bankacct.changedby}" /></td>
                    <td ><c:out value="${bankacct.changeddate}" /></td>
                    <td ><c:out value="${bankacct.changedtime}" /></td>
                    <td ><c:out value="${bankacct.createddate}" /></td>
                    <td id="cmaintain">
<!-- id="linkedit" is style to text edit link: maintained in Page_Main.css -->                
     	<a id="linkedit" href="bankacctedit?bankacct_id=<c:out value='${bankacct.id}' />">Edit</a>   
                     	&nbsp;&nbsp;&nbsp;&nbsp;             
 	<a id="linkedit" href="bankacctdelete?bankacct_id=<c:out value='${bankacct.id}' />">Delete</a>  	                     	
                    </td>
                </tr>
            </c:forEach>
        </table><br>        
    </div>
</c:if>		
</body>
</html>