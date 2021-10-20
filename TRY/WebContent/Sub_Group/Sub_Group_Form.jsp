<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<c:if test="${user != null}">
        		<c:if test="${sub_group != null}">
        			<input type="hidden" id="subgrp_id" 
        			value="<c:out value='${sub_group.id}'/>" />
        		</c:if>
<!-- Sub-Group -->
				<tr>
					<td>Sub Group Name:<i class="fa fa-asterisk"></i></td>
					<td><input type="text" id="fsub_group" required 
					value="<c:out value='${sub_group.fsub_group}'/>" /></td>
				</tr>
<!-- Group -->       
				<tr>
					<td>Group Name:<i class="fa fa-asterisk"></i></td>
<!-- With Drop-down(F4) help -->
<c:if test="${sub_group == null}">
<td><select id="group">
            <c:forEach items="${listGroup}" var="group">
                <option value="${group.name}"> ${group.name} </option> 
            </c:forEach>
    </select>        
</td>
</c:if>
<!-- Selected Drop-down(F4) Help -->
					<c:if test="${sub_group != null}">
<td><select id="group">
            <c:forEach items="${listGroup}" var="group">
                <option value="${group.name}"
<c:if test="${group.name eq sub_group.fgroup}">selected="selected"</c:if>
                > ${group.name} </option> 
            </c:forEach>
    </select>        
</td>
<!-- <td><input type="text" id="fgroup" required value="<c:out value='${sub_group.fgroup}'/>" /></td>  -->
					</c:if>
				</tr>

<!-- Comments -->				
				<tr>
					<td>Comments:</td>
					<td><textarea id="comments" style="width: 300px; height: 75px;">
							<c:out value='${sub_group.comments}'/> </textarea></td>
				</tr>
				<tr>
					<td><br></td>
				</tr>
</c:if>
<!-- Remove Attribute Value -->
<% session.removeAttribute("sesssubgroupevent"); %>