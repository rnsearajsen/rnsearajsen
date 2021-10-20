<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container" class="row">
	<!-- Header -->	
	<div class="row">
<!-- Column1 - Logo -->
<div class=col>
<div class="imgcontainer">
    <img src="Images/bpcl_logo.png" alt="Avatar" class="avatar">
</div>    
</div>
<!-- Column2 Heading -->
<div class=col>	
<br>
<c:if test="${companyc.name == null}">
	<h3 class="text-center">Company Name</h3>
	<h4 class="text-center">Company Suffix</h4>
</c:if>
<c:if test="${companyc != null}">
	<!-- Header -->
	<h3 class="text-center"><c:out value="${companyc.name}" /></h3>
	<h4 class="text-center"><c:out value="${companyc.suffix}" /></h4>
</c:if>
</div>
<!-- Column3 User Detail -->
<div class=col>
<div class="imgcontainer">
    <img src="Images/bpcl_logo.png" align="left" alt="Avatar" class="avatar">
    <h6>Welcome!!! ${user.firstname}</h6>
    <p hidden="hidden" id="useraccess">${user.access}</p>
    <p hidden="hidden" id="userfirstname">${user.firstname}</p>
    <br>
    <a href="userlogout" class="btn btn-danger" style="font-size:14px" role="button">Sign Out <i class="fa fa-sign-out" style="font-size:20px;color:white"></i></a>
</div> 
</div>
</div>
</div>