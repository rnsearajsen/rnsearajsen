<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- For Icon -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<c:if test="${user != null}">
<!-- Column1 -->

        		<c:if test="${product != null}">
        			<input type="hidden" id="product_id"
        			value="<c:out value='${product.id}'/>" />
        		</c:if>
				<tr>
<!-- First Column -->
					<td class="required">Product Name:<i class="fa fa-asterisk"></i></td>
					<td><input type="text" id="name" required 
					 value="<c:out value='${product.name}'/>" /></td>
<!-- Second Column -->
	
<!-- Third Column -->

				</tr>
				<tr>
<!-- First Column -->
					<td>Group:<i class="fa fa-asterisk"></i></td>
					<td><input type="text" id="group_name" required
					 value="Product" disabled /></td>
<!-- Second Column -->								 
<!-- Third Column -->

				</tr>
				<tr>
<!-- First Column -->
					<td>Sub-Group:<i class="fa fa-asterisk"></i></td>
<!-- With Drop-down(F4) help -->
<c:if test="${product == null}">
<td><select id="subgroup_name">
            <c:forEach items="${listSub_Group}" var="sub_group">
                <option value="${sub_group.fsub_group}"> ${sub_group.fsub_group} </option> 
            </c:forEach>
    </select>        
</td></c:if>
<c:if test="${product != null}">
<td><select id="subgroup_name">
            <c:forEach items="${listSub_Group}" var="sub_group">
                <option value="${sub_group.fsub_group}"
<c:if test="${sub_group.fsub_group eq product.subgroup_name}">selected="selected"</c:if>                
                > ${sub_group.fsub_group} </option> 
            </c:forEach>
    </select>        
</td>
</c:if>

<!-- ****************************** -->
<!-- Second Column -->
<!-- Third Column -->
</tr>
				<tr>
<!-- First Column -->				
					<td>UoM(Unit):<i class="fa fa-asterisk"></i></td>
<!-- With Drop-down(F4) help -->
<c:if test="${product == null}">
<td><select id="uom">
            <c:forEach items="${listSub_Groupuom}" var="subgroup_uom">
                <option value="${subgroup_uom.fsub_group}"> ${subgroup_uom.fsub_group} </option> 
            </c:forEach>
    </select>        
</td></c:if>
<c:if test="${product != null}">
<td><select id="uom">
            <c:forEach items="${listSub_Groupuom}" var="subgroup_uom">
                <option value="${subgroup_uom.fsub_group}"
<c:if test="${subgroup_uom.fsub_group eq product.uom}">selected="selected"</c:if>                
                > ${subgroup_uom.fsub_group} </option> 
            </c:forEach>
    </select>        
</td>
</c:if>
<!-- Second Column -->		
<!-- Third Column --> 
				</tr>
				<tr>
<!-- First Column -->	
					<td>Comments:</td>
<td><textarea id="comments" style="width: 300px; height: 100px;"><c:out value='${product.comments}'/> </textarea>
					&nbsp; &nbsp;</td>
<!-- Second Column -->								 
<!-- Third Column -->
				</tr>
				<tr>
<!-- First Column -->									 
<td>Last Changed By:</td>
<td><input type="text" id="changedby" value="<c:out value='${user.firstname}'/>" disabled /></td>			
<!-- Second Column -->
<!-- Third Column -->
				</tr>
				<tr>
<!-- First Column -->	
<td>Last Changed Date: </td>
<td><input type="date" id="changeddate" value="<c:out value='${product.changeddate}'/>" disabled />
    &nbsp; &nbsp;&nbsp; &nbsp;Time: <input type="time" id="changedtime" value="<c:out value='${product.changedtime}'/>" disabled /></td>
<!-- Second Column 
<td> Last Changed Time </td> 
<td><input type="time" id="changedtime" value="<c:out value='${product.changedtime}'/>" disabled /></td> -->
				</tr>
				<tr>
<!-- First Column -->	
<td>Created Date: </td>
<td><input type="date" id="createddate" value="<c:out value='${product.createddate}'/>" disabled /></td>
<!-- Second Column -->		
				</tr>
				<tr><td><br></td></tr>
				<tr>
					<td colspan="2">(<i class="fa fa-asterisk"></i>Required field)</td>
				</tr>

</c:if>
<!-- Remove Attribute Value -->
<% session.removeAttribute("sessproductevent"); %>