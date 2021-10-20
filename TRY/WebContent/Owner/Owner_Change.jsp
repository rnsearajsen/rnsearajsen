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
<script type="text/javascript" src="script/ownerscript.js"> </script>	
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
<% session.removeAttribute("sessownerevent"); %>
	<!-- Set Attribute "Owner.jsp" -->
<% session.setAttribute("sessownerevent", "change");  %>

<jsp:include page="Owner.jsp"></jsp:include>
<!--  -->

<!-- Create Owner Form -->	
	<div class="container">
		<br>
<!-- Edit Owner -->	
<c:if test="${owner != null}">
		<form action="ownerupdate">
			<div id="message"></div><br>
			<table id="ownertable">
               <jsp:include page="Owner_Form.jsp"></jsp:include>
				<tr>
					<td colspan="2" align="center">
					<input type="button" id="btnownerupdate" class="btn btn-primary" value="Update" />
					&nbsp;&nbsp;<a role="button" href="ownerchange" class="btn btn-primary">Next</a></td>
				</tr>
			</table>
		</form>
</c:if>
<!-- Filter Owner Values-->
<c:if test="${owner == null}">
		<form action="ownerfilter" method="post">
			<table>
<!-- Owner Name -->
				<tr>
					<td>Owner Name:</td>
<td><select name="owner">
            <c:forEach items="${listOwner}" var="owner">
                <option value="${owner.id}"> ${owner.name} </option>                 
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
<% session.removeAttribute("sessownerevent"); %>	
</c:if>	
</body>
</html>