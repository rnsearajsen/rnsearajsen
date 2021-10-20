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
<script type="text/javascript" src="script/subgroupscript.js"> </script>
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
<% session.removeAttribute("sesssubgroupevent"); %>
	<!-- Set Attribute "Sub_Group.jsp" -->
<% session.setAttribute("sesssubgroupevent", "create");  %>
<jsp:include page="Sub_Group.jsp"></jsp:include>
<!--  -->

<!-- Create Sub_Group Form -->	
	<div class="container">
		<br>
<!-- Create Sub_Group -->	
		<form action="subgrpinsert">
		<h6>${message}</h6>
		<div id="message"></div><br>
			<table id="subgrptable">
               <jsp:include page="Sub_Group_Form.jsp"></jsp:include>
				<tr>
					<td colspan="2" align="center"><input type="button" id="btnsubgrpinsert"
						class="btn btn-primary" value="Create" />&nbsp; &nbsp; 
						<button type="reset" id="btnsubgrpclear" class="btn btn-primary">Clear</button></td>
				</tr>
			</table>
		</form>
	</div>
</c:if>	
</body>
</html>