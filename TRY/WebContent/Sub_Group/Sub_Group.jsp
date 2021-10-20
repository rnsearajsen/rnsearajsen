<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="script/customscript.js"> </script>
<body onload="accessfunc()">
<!-- Get Attribute Value maintained in "Sub_Group_Display.jsp" & "Sub_Group_Change.jsp -->
<%String event=(String)session.getAttribute("sesssubgroupevent"); %>

<div class="container" class="row">
<c:if test="${user != null}">
	<h4 class="text-center">Sub-Group Details</h4>
	<!-- Row 3 (Content) -->
		<div class="row">
	<!-- Column 1 -->
			<div class="col">	
	<div class="btn-group">
<% if (event != "create") { %>
	    <a id="btncreate" href="subgrpcreate" class="btn btn-primary" role="button">Create</a>  			
<% } %>
<% if (event != "change") { %>
		<a id="btnchange" href="subgrpchange" class="btn btn-primary" role="button">Change</a>
<% } %>
<% if (event != "display") { %>
		<a id="btndisplay" href="subgrplist" class="btn btn-primary" role="button">Display</a>
<% } %>
</div>
	</div> 
			<!-- Column 2 -->
			<div class="col">
<% if (event == "create") { %>
				<h4>Create Sub-Group</h4>
<% } %>
<% if (event == "change") { %>
				<h4>Change Sub-Group</h4>
<% } %>
<% if (event == "display") { %>
				<h4>Display Sub-Group</h4>
<!-- Remove Attribute Value -->
<% session.removeAttribute("sesssubgroupevent"); %>
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