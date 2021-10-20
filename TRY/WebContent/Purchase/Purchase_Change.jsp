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
<script type="text/javascript" src="script/purchasescript.js"> </script>	
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
<% session.removeAttribute("sesspurchaseevent"); %>
	<!-- Set Attribute "Purchase.jsp" -->
<% session.setAttribute("sesspurchaseevent", "change");  %>

<jsp:include page="Purchase.jsp"></jsp:include>
<!--  -->
<br><div class="container" id="message"></div>
<div class="container" id="scrolltable">
<!-- Create Purchase Form -->	
<!-- Edit Purchase -->	
<c:if test="${purchase != null}">
		<form action="purchaseupdate">  			
			<table id="purchasetable">
               <jsp:include page="Purchase_Form.jsp"></jsp:include>
				<tr>
					<td colspan="4" align="center">
					<input type="button" id="btnpurchaseupdate" class="btn btn-primary" value="Update" /></td>
				</tr>
				<tr><td><br></td></tr>
			</table>
		</form>
</c:if>
<!-- Filter Purchase Values-->
<c:if test="${purchase == null}">
<br>
		<form action="purchasefilter" method="post">
			<table>
<!-- Purchase Name -->
				<tr>
					<td>Purchase Name:</td>
<td><select name="purchase">
            <c:forEach items="${listPurchase}" var="purchase">
                <option value="${purchase.id}"> ${purchase.name} </option>                 
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
<% session.removeAttribute("sesspurchaseevent"); %>	
</c:if>	
</body>
</html>