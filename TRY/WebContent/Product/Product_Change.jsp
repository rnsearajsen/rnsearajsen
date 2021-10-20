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
<script type="text/javascript" src="script/productscript.js"> </script>	
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
<% session.removeAttribute("sessproductevent"); %>
	<!-- Set Attribute "Product.jsp" -->
<% session.setAttribute("sessproductevent", "change");  %>

<jsp:include page="Product.jsp"></jsp:include>
<!--  -->
<br><div class="container" id="message"></div>
<div class="container" id="scrolltable">
<!-- Create Product Form -->	
<!-- Edit Product -->	
<c:if test="${product != null}">
		<form action="productupdate">  
			
			<table id="producttable">
               <jsp:include page="Product_Form.jsp"></jsp:include>
				<tr>
					<td colspan="4" align="center">
					<input type="button" id="btnproductupdate" class="btn btn-primary" value="Update" />
					&nbsp;&nbsp;<a role="button" href="productchange" class="btn btn-primary">Next</a></td>
				</tr>
				<tr><td><br></td></tr>
			</table>
		</form>
</c:if>
<!-- Filter Product Values-->
<c:if test="${product == null}">
<br>
		<form action="productfilter" method="post">
			<table>
<!-- Product Name -->
				<tr>
					<td>Product Name:</td>
<td><select name="product">
            <c:forEach items="${listProduct}" var="product">
                <option value="${product.id}"> ${product.name} </option>                 
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
<% session.removeAttribute("sessproductevent"); %>	
</c:if>	
</body>
</html>