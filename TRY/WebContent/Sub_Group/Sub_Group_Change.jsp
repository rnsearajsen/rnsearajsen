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
<% session.setAttribute("sesssubgroupevent", "change");  %>

<jsp:include page="Sub_Group.jsp"></jsp:include>
<!--  -->

<!-- Create Sub_Group Form -->	
	<div class="container">
<!-- Edit Sub_Group -->	
<c:if test="${sub_group != null}">
		<form action="subgrpupdate">
		<br><div id="message"></div><br>
			<table id="subgrptable">
               <jsp:include page="Sub_Group_Form.jsp"></jsp:include>
				<tr>
					<td colspan="2" align="center"><input type="button" id="btnsubgrpupdate"
						class="btn btn-primary" value="Update" />
						&nbsp;&nbsp;<a role="button" href="subgrpchange" class="btn btn-primary">Next</a></td>
				</tr>
			</table>
		</form>
</c:if>
<!-- Filter Group Values-->
<c:if test="${sub_group == null}">
		<form action="subgrpfilter" method="post">
			<table>

<!-- Sub_Group Name -->
				<tr>
					<td>Sub Group Name:</td>
<td><select name="sub_group">
            <c:forEach items="${listSub_Group}" var="sub_group">
                <option value="${sub_group.id}"> ${sub_group.fsub_group} </option>                 
            </c:forEach>
    </select>        
</td>
				</tr>				
<!-- Line Space -->
				<tr><td><br></td></tr>
<!-- Button -->									
				<tr>
					<td colspan="2" align="center"><input type="submit"
						class="btn btn-primary" value="Edit" /></td>
				</tr>
			</table>
		</form>
</c:if>		
	</div>
<!-- Remove Attribute Value -->
<% session.removeAttribute("sesssubgroupevent"); %>
</c:if>	
</body>
</html>