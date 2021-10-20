<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- For Icon -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<c:if test="${user != null}">
<!-- Column1 -->

        		<c:if test="${bankacct != null}">
        			<input type="hidden" id="bankacct_id"
        			value="<c:out value='${bankacct.id}'/>" />
        		</c:if>
				<tr>
<!-- First Column -->
					<td >Account Holder Name:<i class="fa fa-asterisk"></i></td>
					<td><input type="text" id="name" required 
					 value="<c:out value='${bankacct.name}'/>" disabled/></td>
<!-- Second Column -->
					<td>Comments:</td>
<td><textarea id="comments" style="width: 300px; height: 100px;"><c:out value='${bankacct.comments}'/> </textarea>
					&nbsp; &nbsp;</td>	
<!-- Third Column -->

				</tr>
				<tr>
<!-- First Column -->
					<td>Group:<i class="fa fa-asterisk"></i></td>
					<td><input type="text" id="group_name" required
					 value="<c:out value='${bankacct.group_name}'/>" disabled /></td>
<!-- Second Column -->		
<td>Last Changed By:</td>
<td><input type="text" id="changedby" value="<c:out value='${user.firstname}'/>" disabled /></td>						 
<!-- Third Column -->

				</tr>
				<tr>
<!-- First Column -->
					<td>Sub-Group:<i class="fa fa-asterisk"></i></td>
					<td><input type="text" id="subgroup_name" required
					 value="<c:out value='${bankacct.subgroup_name}'/>" disabled /></td>
<!-- <c:if test="${bankacct != null}">
<td><select id="subgroup_name">
            <c:forEach items="${listSub_Group}" var="sub_group">
                <option value="${sub_group.fsub_group}"
<c:if test="${sub_group.fsub_group eq bankacct.subgroup_name}">selected="selected"</c:if>                
                > ${sub_group.fsub_group} </option> 
            </c:forEach>
    </select>        
</td> 
</c:if> -->

<!-- ****************************** -->
<!-- Second Column -->
<td>Last Changed Date: </td>
<td><input type="date" id="changeddate" value="<c:out value='${bankacct.changeddate}'/>" disabled />
    &nbsp; &nbsp;&nbsp; &nbsp;Time: <input type="time" id="changedtime" value="<c:out value='${bankacct.changedtime}'/>" disabled /></td>

<!-- Third Column -->
</tr>
				<tr>
<!-- First Column -->				
					<td>Bank:<i class="fa fa-asterisk"></i></td>
<td><input type="text" id="bank" required
					 value="<c:out value='${bankacct.bank}'/>" /></td>					
<!-- Second Column -->		
<td>Created Date: </td>
<td><input type="date" id="createddate" value="<c:out value='${bankacct.createddate}'/>" disabled /></td>
<!-- Third Column --> 
				</tr>
				<tr>
<!-- First Column -->	
<td>Account Type: </td>
<c:if test="${bankacct != null}">
<td><select id="acctype">
            <c:forEach items="${listSub_Groupacctype}" var="subgroup_acctype">
                <option value="${subgroup_acctype.fsub_group}"
<c:if test="${subgroup_acctype.fsub_group eq bankacct.acctype}">selected="selected"</c:if>                
                > ${subgroup_acctype.fsub_group} </option> 
            </c:forEach>
    </select>        
</td>
</c:if>
<!-- Second Column -->								 
<!-- Third Column -->
				</tr>
				<tr>
<!-- First Column -->									 
<td>IFSC Code:<i class="fa fa-asterisk"></i> </td>
<td><input type="text" id="ifsc" value="<c:out value='${bankacct.ifsc}'/>" required /></td>
<!-- Second Column -->
<!-- Third Column -->
				</tr>
				<tr>
<!-- First Column -->	
<td>Bank Account no:<i class="fa fa-asterisk"></i> </td>
<td><input type="text" id="accountno" value="<c:out value='${bankacct.accountno}'/>" required /></td>
<!-- Second Column 
<td> Last Changed Time </td> 
<td><input type="time" id="changedtime" value="<c:out value='${bankacct.changedtime}'/>" disabled /></td> -->
				</tr>

				<tr><td><br></td></tr>
				<tr>
					<td colspan="2">(<i class="fa fa-asterisk"></i>Required field)</td>
				</tr>

</c:if>
<!-- Remove Attribute Value -->
<% session.removeAttribute("sessbankacctevent"); %>