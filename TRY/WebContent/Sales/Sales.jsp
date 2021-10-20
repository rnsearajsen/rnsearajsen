<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="script/customscript.js"> </script>
<body onload="accessfunc()">
<!-- Get Attribute Value maintained in "Sales_Display.jsp" & "Sales_Change.jsp -->
<%String event=(String)session.getAttribute("sesssalesevent"); %>

<div class="container" class="row">
<c:if test="${user != null}">
    <h4 class="text-center">Daily Update</h4>
	<!-- Row 3 (Content) -->
		<div class="row">
	<!-- Column 1 -->
			<div class="col">	
	<div class="btn-group">
<% if (event != "create") { %>
 <!-- <button type="button" onclick="location.href='./Sales/Sales_Create.jsp';" -->  
	 <!-- 	class="btn btn-primary">Create</button>   -->
	    <a id="btncreate" href="salescreate" class="btn btn-primary" role="button">Sales Entry</a>  
<% } %>

<% if (event != "dlytxnupd") { %>  
	    <a id="btndlytxnupd" href="dlytxnupd" class="btn btn-primary" role="button">Daily Txn</a>  
<% } %>

<% if (event != "display") { %>
		<a id="btndisplay" href="saleslist" class="btn btn-primary" role="button">Display</a> 
		
<% } %>
</div>
	</div> 
			<!-- Column 2 -->
			<div class="col">
<% if (event == "create") { %>
				<h4>Sales Entry</h4>
<% } %>
<% if (event == "dlytxnupd") { %>
				<h4>Daily Txn Entry</h4>
<% } %>
<% if (event == "display") { %>
				<h4>Display Sales Entry</h4>
<!-- Remove Attribute Value -->
<% session.removeAttribute("sesssalesevent"); %>
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