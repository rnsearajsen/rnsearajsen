<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="script/customscript.js"> </script>
<body onload="accessfunc()">
<!-- Get Attribute Value maintained in "Group_Display.jsp" & "Group_Change.jsp -->
<%String event=(String)session.getAttribute("sessgroupevent"); %>
<div class="container" class="row">
<c:if test="${user != null}">
	<h4 class="text-center">Group Details</h4>
	<!-- Row 3 (Content) -->
		<div class="row">
	<!-- Column 1 -->
			<div class="col">	
	<div class="btn-group">
<% if (event != "create") { %>
	    <a id="btncreate" href="grpcreate" class="btn btn-primary" role="button">Create</a>  			
<% } %>
<% if (event != "change") { %>
		<a id="btnchange" href="grpchange" class="btn btn-primary" role="button">Change</a>
<% } %>
<% if (event != "display") { %>
		<a id="btndisplay" href="grplist" class="btn btn-primary" role="button">Display</a>
<% } %>
</div>
	</div> 
			<!-- Column 2 -->
			<div class="col">
<% if (event == "create") { %>
				<h4>Create Group</h4>
<% } %>
<% if (event == "change") { %>
				<h4>Change Group</h4>
<% } %>
<% if (event == "display") { %>
				<h4>Display Group</h4>
<!-- Remove Attribute Value -->
<% session.removeAttribute("sessgroupevent"); %>
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