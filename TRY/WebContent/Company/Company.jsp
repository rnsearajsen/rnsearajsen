<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="script/customscript.js"> </script>
<body onload="accessfunc()">
<!-- Get Attribute Value maintained in "Company_Display.jsp" & "Company_Change.jsp -->
<%String event=(String)session.getAttribute("sesscompanyevent"); %>

<div class="container" class="row">
<c:if test="${user != null}">
<!-- <h4 style="color: #b3003b;" class="text-center">Company details</h4>  -->
    <h4 class="text-center">Company details</h4>
	<!-- Row 3 (Content) -->
		<div class="row">
	<!-- Column 1 -->
			<div class="col">	
	<div class="btn-group">
<% if (event != "create") { %>
 <!-- <button type="button" onclick="location.href='./Company/Company_Create.jsp';" -->  
	 <!-- 	class="btn btn-primary">Create</button>   -->
	    <a id="btncreate" href="compcreate" class="btn btn-primary" role="button">Create</a>  
<% } %>
<% if (event != "change") { %>
		<a id="btnchange" href="compchange" class="btn btn-primary" role="button">Change</a>
<% } %>
<% if (event != "display") { %>
		<a id="btndisplay" href="complist" class="btn btn-primary" role="button">Display</a> 
		
<% } %>
</div>
	</div> 
			<!-- Column 2 -->
			<div class="col">
<% if (event == "create") { %>
				<h4>Create Company</h4>
<% } %>
<% if (event == "change") { %>
				<h4>Change Company</h4>
<% } %>
<% if (event == "display") { %>
				<h4>Display Company</h4>
<!-- Remove Attribute Value -->
<% session.removeAttribute("sesscompanyevent"); %>
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