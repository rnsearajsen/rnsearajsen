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
<script type="text/javascript" src="script/customscript.js"></script>
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
	<!-- Set Attribute for Display in "Group.jsp" -->
<% session.removeAttribute("sessgroupevent"); %>
<% session.setAttribute("sessgroupevent", "display");  %>
<jsp:include page="Group.jsp"></jsp:include>
	
<!-- Group Display  -->
    <div align="center"> 
<!-- Fuzzy Search -->
<input type="text" id="myInput" onkeyup="filtersearch()" placeholder="Search for Group Name.." title="Type in a group">
<br><h6>${message}</h6><br>
</div>
    <div class="scrolltable" align="center"> 
<!-- Fuzzy Search -->     
        <table id="tableview" border="1" >
            <tr id="tableview" style="padding:10px">
<!-- Hide "ID Column" <th id="tableview" style="padding:10px">Group_ID</th> -->
                <th style="padding:10px; cursor:pointer" onclick="sortTable(0)">Group Name <i class='fas fa-sort'></i></th>
                <th>Comments</th>
                <th id="cmaintain">Maintain</th>
            </tr>
            <c:forEach var="group" items="${listGroup}">
                <tr id="tableview" style="padding:10px">
<!-- Hide "ID Column" <td id="tableview" style="padding:10px"><c:out value="${group.id}" /></td>  -->  
                    <td style="padding:10px"><c:out value="${group.name}" /></td>                    
                    <td ><c:out value="${group.comments}" /></td>
                    <td id="cmaintain">
<!-- id="linkedit" is style to text edit link: maintained in Page_Main.css -->                    
     	<a id="linkedit" href="grpedit?group_id=<c:out value='${group.id}' />">Edit</a>   
                     	&nbsp;&nbsp;&nbsp;&nbsp;             
 	<a id="linkedit" href="grpdelete?group_id=<c:out value='${group.id}' />">Delete</a>                     	
                    </td>
                </tr>                
            </c:forEach>        
        </table><br>
</div>    
</c:if>		
</body>
</html>