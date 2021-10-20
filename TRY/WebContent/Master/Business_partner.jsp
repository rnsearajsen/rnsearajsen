<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="script/customscript.js"> </script>
<script type="text/javascript" src="script/ownerscript.js"> </script>
<body onload="accessfunc()">
<!-- Get Attribute Value maintained in "Owner_Display.jsp" & "Owner_Change.jsp -->
<%String event=(String)session.getAttribute("sessownerevent"); %>

<div class="container" class="row">
<c:if test="${user != null}">
<!-- <h4 style="color: #b3003b;" class="text-center">Owner details</h4>  -->
    <h4 class="text-center">Owner details</h4>
	<!-- Row 3 (Content) -->
		<div class="row">
	<!-- Column 1 -->
			<div class="col">	
	<div class="btn-group">
<% if (event != "create") { %>
 <!-- <button type="button" onclick="location.href='./Owner/Owner_Create.jsp';" -->  
	 <!-- 	class="btn btn-primary">Create</button>   -->
	    <a id="btncreate" href="ownercreate" class="btn btn-primary" role="button">Create</a>  
<% } %>
<% if (event != "change") { %>
		<a id="btnchange" href="ownerchange" class="btn btn-primary" role="button">Change</a>
<% } %>
<% if (event != "display") { %>
		<a id="btndisplay" href="ownerlist" class="btn btn-primary" role="button">Display</a> 
		
<% } %>
</div>
	</div> 
			<!-- Column 2 -->
			<div class="col">
<% if (event == "create") { %>
				<h4>Create Owner</h4>
<% } %>
<% if (event == "change") { %>
				<h4>Change Owner</h4>
<% } %>
<% if (event == "display") { %>
				<h4>Display Owner</h4>
<!-- Remove Attribute Value -->
<% session.removeAttribute("sessownerevent"); %>
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