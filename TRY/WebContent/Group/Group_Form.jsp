<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<!-- For Icon -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<c:if test="${user != null}">
        		<c:if test="${group != null}">
        			<input type="hidden" id="group_id" 
        			value="<c:out value='${group.id}'/>" />
        		</c:if>
				<tr>
					<td>Group Name:<i class="fa fa-asterisk"></i></td>
					<td><input type="text" id="name" required 
					value="<c:out value='${group.name}'/>" /></td>
				</tr>
				<tr>
					<td>Comments:</td>
<!-- <c:set var="text" value='${group.comments}' />
					<c:set var="trimmedText" value="${fn:trim(text)}" /> -->
					<td><textarea id="comments" style="width: 300px; height: 75px;">
							<c:out value='${group.comments}'/> </textarea></td>
				</tr>
				<tr>
					<td>(<i class="fa fa-asterisk"></i>  Required field)</td>
				</tr>
				<tr><td><br></td></tr>
</c:if>
<!-- Remove Attribute Value -->
<% session.removeAttribute("sessgroupevent"); %>