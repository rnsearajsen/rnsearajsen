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
<script type="text/javascript" src="script/salepricescript.js"> </script>	
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
<% session.removeAttribute("sesssalepriceevent"); %>
	<!-- Set Attribute "Saleprice.jsp" -->
<% session.setAttribute("sesssalepriceevent", "change");  %>

<jsp:include page="Saleprice.jsp"></jsp:include>
<!--  -->
<br><div class="container" id="message"></div>
<div class="container" id="scrolltable">
<!-- Create Saleprice Form -->	
<!-- Edit Saleprice -->	
<c:if test="${saleprice != null}">
		<form action="salepriceupdate">  			
			<table id="salepricetable">
               <jsp:include page="Saleprice_Form.jsp"></jsp:include>
				<tr>
					<td colspan="4" align="center">
					<input type="button" id="btnsalepriceupdate" class="btn btn-primary" value="Update" /></td>
				</tr>
				<tr><td><br></td></tr>
			</table>
		</form>
</c:if>
<!-- Filter Saleprice Values-->
<c:if test="${saleprice == null}">
<br>
		<form action="salepricefilter" method="post">
			<table>
<!-- Saleprice Name -->
				<tr>
					<td>Saleprice Name:</td>
<td><select name="saleprice">
            <c:forEach items="${listSaleprice}" var="saleprice">
                <option value="${saleprice.id}"> ${saleprice.name} </option>                 
            </c:forEach>
    </select>        
</td>
				</tr>				
<!-- Line Space -->	
<tr><td><br></td></tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						class="btn btn-primary" value="Edit" /></td>
				</tr>
			</table>
		</form>
</c:if>		
</div>
<!-- Remove Attribute Value -->
<% session.removeAttribute("sesssalepriceevent"); %>	
</c:if>	
</body>
</html>