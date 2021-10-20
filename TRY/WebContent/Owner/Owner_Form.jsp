<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- For Icon -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<c:if test="${user != null}">
<!-- Column1 -->

        		<c:if test="${owner != null}">
        			<input type="hidden" id="owner_id"
        			value="<c:out value='${owner.id}'/>" />
        		</c:if>
				<tr>
<!-- First Column -->
					<td class="required">Owner Name:<i class="fa fa-asterisk"></i></td>
					<td><input type="text" id="name" required 
					 value="<c:out value='${owner.name}'/>" /></td>
<!-- Second Column -->	
<td class="required">ID Card Type1:<i class="fa fa-asterisk"></i></td>
<!-- With Drop-down(F4) help -->
<c:if test="${owner == null}">
<td><select id="idtype1">
            <c:forEach items="${listSub_Groupidtype}" var="subgroup_idtype">
                <option value="${subgroup_idtype.fsub_group}"> ${subgroup_idtype.fsub_group} </option> 
            </c:forEach>
    </select>        
</td></c:if>
<c:if test="${owner != null}">
<td><select id="idtype1">
            <c:forEach items="${listSub_Groupidtype}" var="subgroup_idtype">
                <option value="${subgroup_idtype.fsub_group}"
<c:if test="${subgroup_idtype.fsub_group eq owner.idtype1}">selected="selected"</c:if>    
                > ${subgroup_idtype.fsub_group} </option> 
            </c:forEach>
    </select>        
</td>
</c:if>
				</tr>
				<tr>
<!-- First Column -->
					<td>Group:<i class="fa fa-asterisk"></i></td>
					<td><input type="text" id="group_name" required
					 value="Owner" disabled /></td>
<!-- Second Column -->								 
			    <td class="required">ID Card number1:<i class="fa fa-asterisk"></i></td>
					<td><input type="text" id="idnum1" required 
					 value="<c:out value='${owner.idnum1}'/>" /></td>					 
				</tr>
				<tr>
<!-- First Column -->				
					<td>Sub-Group:<i class="fa fa-asterisk"></i></td>
<!-- With Drop-down(F4) help -->
<c:if test="${owner == null}">
<td><select id="subgroup_name">
            <c:forEach items="${listSub_Group}" var="sub_group">
                <option value="${sub_group.fsub_group}"> ${sub_group.fsub_group} </option> 
            </c:forEach>
    </select>        
</td></c:if>
<c:if test="${owner != null}">
<td><select id="subgroup_name">
            <c:forEach items="${listSub_Group}" var="sub_group">
                <option value="${sub_group.fsub_group}"
<c:if test="${sub_group.fsub_group eq owner.subgroup_name}">selected="selected"</c:if>                
                > ${sub_group.fsub_group} </option> 
            </c:forEach>
    </select>        
</td>
</c:if>
<!-- Second Column -->					 
                <td class="required">ID Card Type2:</td>
<!-- With Drop-down(F4) help -->
<c:if test="${owner == null}">
<td><select id="idtype2">
            <c:forEach items="${listSub_Groupidtype}" var="subgroup_idtype">
                <option value="${subgroup_idtype.fsub_group}"> ${subgroup_idtype.fsub_group} </option> 
            </c:forEach>
    </select>        
</td></c:if>
<c:if test="${owner != null}">	 
<td><select id="idtype2">
            <c:forEach items="${listSub_Groupidtype}" var="subgroup_idtype">
                <option value="${subgroup_idtype.fsub_group}"
<c:if test="${subgroup_idtype.fsub_group eq owner.idtype2}">selected="selected"</c:if> 
                > ${subgroup_idtype.fsub_group} </option> 
            </c:forEach>
    </select>        
</td>               
</c:if>				 
				</tr>
				<tr>
<!-- First Column -->	
					<td>Address:<i class="fa fa-asterisk"></i></td>
					<td><textarea id="address" style="width: 300px; height: 100px;" required>
					<c:out value='${owner.address}'/> </textarea>&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;</td>
<!-- Second Column -->								 
			    <td class="required">ID Card number2:</td>
					<td><input type="text" id="idnum2" 
					 value="<c:out value='${owner.idnum2}'/>" /></td>					
				</tr>
				<tr>
					<td>E-Mail:</td>
					<td><input type="text" id="mail" 
					value="<c:out value='${owner.mail}'/>" /></td>
				</tr>
				<tr>
					<td>Contact No 1:<i class="fa fa-asterisk"></i></td>
					<td><input type="number" id="contact1" required 
					 value="<c:out value='${owner.contact1}'/>" /></td>
				</tr>
				<tr>
					<td>Contact No 2:</td>
					<td><input type="number" id="contact2" 
					 value="<c:out value='${owner.contact2}'/>" /></td>
				</tr>
				<tr><td><br></td></tr>
				<tr>
					<td>(<i class="fa fa-asterisk"></i>  Required field)</td>
				</tr>

</c:if>
<!-- Remove Attribute Value -->
<% session.removeAttribute("sessownerevent"); %>