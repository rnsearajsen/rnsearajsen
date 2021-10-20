<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="script/customscript.js"> </script>
<body onload="accessfunc()">
<!-- Get Attribute Value maintained in "Employee_Display.jsp" & "Employee_Change.jsp -->
<%String event=(String)session.getAttribute("sessemployeeevent"); %>

<div class="container" class="row">
<c:if test="${user != null}">
<!-- <h4 style="color: #b3003b;" class="text-center">Employee details</h4>  -->
    <h4 class="text-center">Employee details</h4>
	<!-- Row 3 (Content) -->
		<div class="row">
	<!-- Column 1 -->
			<div class="col">	
	<div class="btn-group">
<% if (event != "create") { %>
 <!-- <button type="button" onclick="location.href='./Employee/Employee_Create.jsp';" -->  
	 <!-- 	class="btn btn-primary">Create</button>   -->
	    <a id="btncreate" href="employeecreate" class="btn btn-primary" role="button">Create</a>  
<% } %>
<% if (event != "change") { %>
		<a id="btnchange" href="employeechange" class="btn btn-primary" role="button">Change</a>
<% } %>
<% if (event != "display") { %>
		<a id="btndisplay" href="employeelist" class="btn btn-primary" role="button">Display</a> 
		
<% } %>
</div>
	</div> 
			<!-- Column 2 -->
			<div class="col">
<% if (event == "create") { %>
				<h4>Create Employee</h4>
<% } %>
<% if (event == "change") { %>
				<h4>Change Employee</h4>
<% } %>
<% if (event == "display") { %>
				<h4>Display Employee</h4>
<!-- Remove Attribute Value -->
<% session.removeAttribute("sessemployeeevent"); %>
<% } %>				
			</div>
			<!-- Column3 -->
			<div class="col">
				<br>
			</div>
	
	</div>
</c:if>
</div>
</body>