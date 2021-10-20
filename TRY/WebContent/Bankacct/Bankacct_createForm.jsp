<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- For Icon -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<c:if test="${user != null}">

				<tr>
<!-- First Column -->
					<td>Group:<i class="fa fa-asterisk"></i></td>
					<td><select id="group_name">
					<option value="company">Company Accounts</option>
					<option value="owner">Owner Accounts</option>
					<option value="vendor">Vendor Accounts</option>
					<option value="employee">Employee Accounts</option>
					</select>&nbsp; &nbsp;&nbsp; &nbsp;</td>
<!-- ************************************************ -->

<!-- Second Column -->
<td>Last Changed By:</td>
<td><input type="text" id="changedby" value="<c:out value='${user.firstname}'/>" disabled /></td>	
<!-- Third Column -->
				</tr>
				<tr>
<!-- First Column -->
					<td>Account Holder Name:<i class="fa fa-asterisk"></i></td>
					<td><select id="name" required></select></td>
					 
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
<!-- ****************************** -->
<!-- Second Column -->
<td>Created Date: </td>
<td><input type="date" id="createddate" value="<c:out value='${bankacct.createddate}'/>" disabled /></td>
</tr>
				<tr>
<!-- First Column -->				
<td>Account Type: </td>
<td><select id="acctype">
            <c:forEach items="${listSub_Groupacctype}" var="subgroup_acctype">
                <option value="${subgroup_acctype.fsub_group}"> ${subgroup_acctype.fsub_group} </option> 
            </c:forEach>
    </select>        
</td>						
				</tr>
				<tr>
<!-- First Column -->									 
<td>IFSC Code:<i class="fa fa-asterisk"></i> </td>
<td><input type="text" id="ifsc" value="<c:out value='${bankacct.ifsc}'/>" required /></td>

				</tr>
				<tr>
<!-- First Column -->	
<td>Bank Account no:<i class="fa fa-asterisk"></i> </td>
<td><input type="text" id="accountno" value="<c:out value='${bankacct.accountno}'/>" required /></td> 
				</tr>

<tr>
<!-- First Column -->
					<td>Comments:</td>
<td><textarea id="comments" style="width: 300px; height: 100px;"><c:out value='${bankacct.comments}'/> </textarea></td>	
</tr>
				<tr><td><br></td></tr>
				<tr>
					<td colspan="2">(<i class="fa fa-asterisk"></i>Required field)</td>
				</tr>
</c:if>
<!-- Remove Attribute Value -->
<% session.removeAttribute("sessbankacctevent"); %>