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
<script type="text/javascript" src="script/bankacctscript.js"> </script>	
</head>
<body class="bg">
<c:if test="${user != null}">
	<!-- Background Image - flew from CSS(Page_Main.css) -->
	<!-- Row 1 (Header) -->
	<jsp:include page="/Header.jsp"></jsp:include>

	<!-- Row 2 (Header) -->
	<!-- Horizontal Navigation -->
	<jsp:include page="/Hor_Navigation.jsp"></jsp:include>

<!--  -->
<% session.removeAttribute("sessbankacctevent"); %>
	<!-- Set Attribute "Bankacct.jsp" -->
<% session.setAttribute("sessbankacctevent", "create");  %>
<jsp:include page="Bankacct.jsp"></jsp:include>
<!--  -->

<!-- Create Bankacct Form -->	
	<div id="scrolltable" class="container">
		<br>	
<!-- Create Bankacct -->	
		<form action="bankacctinsert">
		<div id="message"></div><br>
		<div class="scrolltable">
		<table id="bankaccttable">
              <jsp:include page="Bankacct_createForm.jsp"></jsp:include>
				<tr>
<!-- Used AJAX to insert entry in DB via 'bankacctscript.js' On Button Click function -->
					<td colspan="2" align="center">
			<button type="button" id="btnbankacctinsert" class="btn btn-primary">Create</button>
			&nbsp; &nbsp; <button type="reset" id="btnbankacctclear" class="btn btn-primary">Clear</button></td>
				</tr>   				
			</table>
			</div>	
		</form> 
	</div>
</c:if>	
</body>
</html>