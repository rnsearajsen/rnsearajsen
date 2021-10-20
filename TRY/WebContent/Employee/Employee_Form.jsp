<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- For Icon -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<c:if test="${user != null}">
<!-- Column1 -->

        		<c:if test="${employee != null}">
        			<input type="hidden" id="employee_id"
        			value="<c:out value='${employee.id}'/>" />
        		</c:if>
				<tr>
<!-- First Column -->
					<td class="required">Employee Name:<i class="fa fa-asterisk"></i></td>
					<td><input type="text" id="name" required 
					 value="<c:out value='${employee.name}'/>" /></td>
<!-- Second Column -->	
<td class="required">ID Card Type1:<i class="fa fa-asterisk"></i></td>
<!-- With Drop-down(F4) help -->
<c:if test="${employee == null}">
<td><select id="idtype1">
            <c:forEach items="${listSub_Groupidtype}" var="subgroup_idtype">
                <option value="${subgroup_idtype.fsub_group}"> ${subgroup_idtype.fsub_group} </option> 
            </c:forEach>
    </select>        
</td></c:if>
<c:if test="${employee != null}">
<td><select id="idtype1">
            <c:forEach items="${listSub_Groupidtype}" var="subgroup_idtype">
                <option value="${subgroup_idtype.fsub_group}"
<c:if test="${subgroup_idtype.fsub_group eq employee.idtype1}">selected="selected"</c:if>    
                > ${subgroup_idtype.fsub_group} </option> 
            </c:forEach>
    </select>        
</td>
</c:if>
<!-- Third Column -->

				</tr>
				<tr>
<!-- First Column -->
					<td>Group:<i class="fa fa-asterisk"></i></td>
					<td><input type="text" id="group_name" required
					 value="Employee" disabled /></td>
<!-- Second Column -->								 
			    <td class="required">ID Card number1:<i class="fa fa-asterisk"></i></td>
					<td><input type="text" id="idnum1" required 
					 value="<c:out value='${employee.idnum1}'/>" /></td>	
<!-- Third Column -->

				</tr>
				<tr>
<!-- First Column -->
					<td>Gender:<i class="fa fa-asterisk"></i></td>
<!-- With Drop-down(F4) help -->
<c:if test="${employee == null}">
<td><select id="gender">
            <c:forEach items="${listSub_Groupgender}" var="subgroup_gender">
                <option value="${subgroup_gender.fsub_group}"> ${subgroup_gender.fsub_group} </option> 
            </c:forEach>
    </select>        
</td></c:if>
<c:if test="${employee != null}">
<td><select id="gender">
            <c:forEach items="${listSub_Groupgender}" var="subgroup_gender">
                <option value="${subgroup_gender.fsub_group}"
<c:if test="${subgroup_gender.fsub_group eq employee.gender}">selected="selected"</c:if>                
                > ${subgroup_gender.fsub_group} </option> 
            </c:forEach>
    </select>        
</td>
</c:if>
<!-- Second Column -->
					<td>Comments:</td>
<td><textarea id="comments" style="width: 300px; height: 100px;"><c:out value='${employee.comments}'/> </textarea>
					&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;</td>
<!-- Third Column -->
</tr>
				<tr>
<!-- First Column -->				
					<td>Job:<i class="fa fa-asterisk"></i></td>
<!-- With Drop-down(F4) help -->
<c:if test="${employee == null}">
<td><select id="subgroup_name">
            <c:forEach items="${listSub_Group}" var="sub_group">
                <option value="${sub_group.fsub_group}"> ${sub_group.fsub_group} </option> 
            </c:forEach>
    </select>        
</td></c:if>
<c:if test="${employee != null}">
<td><select id="subgroup_name">
            <c:forEach items="${listSub_Group}" var="sub_group">
                <option value="${sub_group.fsub_group}"
<c:if test="${sub_group.fsub_group eq employee.subgroup_name}">selected="selected"</c:if>                
                > ${sub_group.fsub_group} </option> 
            </c:forEach>
    </select>        
</td>
</c:if>
<!-- Second Column -->		
<td>Start Date:<i class="fa fa-asterisk"></i></td>
<td><input type="date" id="joindate" required value="<c:out value='${employee.joindate}'/>" /></td>			 
<!-- Third Column -->
 
				</tr>
				<tr>
<!-- First Column -->	
<td>Salary:<i class="fa fa-asterisk"></i></td>
<td><input type="number" id="salary" step="0.01" required 
					 value="<c:out value='${employee.salary}'/>" /></td>
<!-- Second Column -->								 
<td>End Date:</td>
<td><input type="date" id="lastdate" value="<c:out value='${employee.lastdate}'/>" /></td>
<!-- Third Column -->
				</tr>
				<tr>
<!-- First Column -->	
					<td>Address:<i class="fa fa-asterisk"></i></td>
<td><textarea id="address" style="width: 300px; height: 100px;" required><c:out value='${employee.address}'/></textarea>
					&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;</td>
<!-- Second Column -->								 
<td>Last Changed By:</td>
<td><input type="text" id="changedby" value="<c:out value='${user.firstname}'/>" disabled /></td>			
<!-- Third Column -->
				</tr>
				<tr>
<!-- First Column -->	
					<td>Contact No 1:<i class="fa fa-asterisk"></i></td>
					<td><input type="number" id="contact1" required 
					 value="<c:out value='${employee.contact1}'/>" /></td>
<!-- Second Column -->
<td>Last Changed Date: </td>
<td><input type="date" id="changeddate" value="<c:out value='${employee.changeddate}'/>" disabled /></td>
				</tr>
				<tr>
<!-- First Column -->	
					<td>Contact No 2:</td>
					<td><input type="number" id="contact2"
					 value="<c:out value='${employee.contact2}'/>" /></td>
<!-- Second Column -->
<td>Last Changed Time: </td>
<td><input type="time" id="changedtime" value="<c:out value='${employee.changedtime}'/>" disabled /></td>		
				</tr>
				<tr><td><br></td></tr>
				<tr>
					<td colspan="2">(<i class="fa fa-asterisk"></i>Required field)</td>
				</tr>

</c:if>
<!-- Remove Attribute Value -->
<% session.removeAttribute("sessemployeeevent"); %>