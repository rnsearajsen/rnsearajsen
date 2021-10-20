<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="script/customscript.js"> </script>
<body onload="accessfunc()">
<!-- Get Attribute Value maintained in "Saleprice_Display.jsp" & "Saleprice_Change.jsp -->
<%String event=(String)session.getAttribute("sesssalepriceevent"); %>

<div class="container" class="row">
<c:if test="${user != null}">
<!-- <h4 style="color: #b3003b;" class="text-center">Saleprice details</h4>  -->
    <h4 class="text-center">Saleprice details</h4>
	<!-- Row 3 (Content) -->
		<div class="row">
	<!-- Column 1 -->
			<div class="col">	
	<div class="btn-group">
<% if (event != "create") { %>
 <!-- <button type="button" onclick="location.href='./Saleprice/Saleprice_Create.jsp';" -->  
	 <!-- 	class="btn btn-primary">Create</button>   -->
	    <a id="btncreate" href="salepricecreate" class="btn btn-primary" role="button">New Entry</a>  
<% } %>

<% if (event != "display") { %>
		<a id="btndisplay" href="salepricelist" class="btn btn-primary" role="button">Display</a> 
		
<% } %>
</div>
	</div> 
			<!-- Column 2 -->
			<div class="col">
<% if (event == "create") { %>
				<h4>Saleprice Entry</h4>
<% } %>
<% if (event == "change") { %>
				<h4>Change Saleprice Entry</h4>
<% } %>
<% if (event == "display") { %>
				<h4>Display Saleprice Entry</h4>
<!-- Remove Attribute Value -->
<% session.removeAttribute("sesssalepriceevent"); %>
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