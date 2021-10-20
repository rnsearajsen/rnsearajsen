<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- For Icon -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<c:if test="${user != null}">
        		<c:if test="${company != null}">
        			<input type="hidden" id="company_id"
        			value="<c:out value='${company.id}'/>" />
        		</c:if>
				<tr>
					<td class="required">Company Name:<i class="fa fa-asterisk"></i></td>
					<td><input type="text" id="name" required 
					 value="<c:out value='${company.name}'/>" /></td>
				</tr>
				<tr>
					<td>Company Suffix:<i class="fa fa-asterisk"></i></td>
					<td><input type="text" id="suffix" required
					 value="<c:out value='${company.suffix}'/>" /></td>
				</tr>
				<tr>
					<td>Address:<i class="fa fa-asterisk"></i></td>
					<td><textarea id="address" style="width: 300px; height: 100px;" required>
					<c:out value='${company.address}'/> </textarea></td>
				</tr>
				<tr>
					<td>E-Mail:</td>
					<td><input type="text" id="mail" 
					value="<c:out value='${company.mail}'/>" /></td>
				</tr>
				<tr>
					<td>Contact No 1:<i class="fa fa-asterisk"></i></td>
					<td><input type="number" id="contact1" required 
					 value="<c:out value='${company.contact1}'/>" /></td>
				</tr>
				<tr>
					<td>Contact No 2:</td>
					<td><input type="number" id="contact2" 
					 value="<c:out value='${company.contact2}'/>" /></td>
				</tr>
				<tr><td><br></td></tr>
				<tr>
					<td>(<i class="fa fa-asterisk"></i>  Required field)</td>
				</tr>
</c:if>
<!-- Remove Attribute Value -->
<% session.removeAttribute("sesscompanyevent"); %>