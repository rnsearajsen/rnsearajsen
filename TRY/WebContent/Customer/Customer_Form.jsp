<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- For Icon -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<c:if test="${user != null}">
<!-- Column1 -->

        		<c:if test="${customer != null}">
        			<input type="hidden" id="customer_id"
        			value="<c:out value='${customer.id}'/>" />
        		</c:if>
				<tr>
<!-- First Column -->
					<td class="required">Customer Name:<i class="fa fa-asterisk"></i></td>
					<td><input type="text" id="name" required 
					 value="<c:out value='${customer.name}'/>" /></td>
<!-- Second Column -->	
<td class="required">ID Card Type1:<i class="fa fa-asterisk"></i></td>
<!-- With Drop-down(F4) help -->
<c:if test="${customer == null}">
<td><select id="idtype1">
            <c:forEach items="${listSub_Groupidtype}" var="subgroup_idtype">
                <option value="${subgroup_idtype.fsub_group}"> ${subgroup_idtype.fsub_group} </option> 
            </c:forEach>
    </select>        
</td></c:if>
<c:if test="${customer != null}">
<td><select id="idtype1">
            <c:forEach items="${listSub_Groupidtype}" var="subgroup_idtype">
                <option value="${subgroup_idtype.fsub_group}"
<c:if test="${subgroup_idtype.fsub_group eq customer.idtype1}">selected="selected"</c:if>    
                > ${subgroup_idtype.fsub_group} </option> 
            </c:forEach>
    </select>        
</td>
</c:if>
<!-- Third Column -->
<td>Last Changed By:</td>
<td><input type="text" id="changedby" value="<c:out value='${user.firstname}'/>" disabled /></td>
				</tr>
				<tr>
<!-- First Column -->
					<td>Group:<i class="fa fa-asterisk"></i></td>
					<td><input type="text" id="group_name" required
					 value="Customer" disabled /></td>
<!-- Second Column -->								 
			    <td class="required">ID Card number1:<i class="fa fa-asterisk"></i></td>
					<td><input type="text" id="idnum1" required 
					 value="<c:out value='${customer.idnum1}'/>" /></td>	
<!-- Third Column -->
<td>Last Changed Date: </td>
<td><input type="date" id="changeddate" value="<c:out value='${customer.changeddate}'/>" disabled /></td>
				</tr>
				<tr>
<!-- First Column -->				
					<td>Sub-Group:<i class="fa fa-asterisk"></i></td>
<!-- With Drop-down(F4) help -->
<c:if test="${customer == null}">
<td><select id="subgroup_name">
            <c:forEach items="${listSub_Group}" var="sub_group">
                <option value="${sub_group.fsub_group}"> ${sub_group.fsub_group} </option> 
            </c:forEach>
    </select>        
</td></c:if>
<c:if test="${customer != null}">
<td><select id="subgroup_name">
            <c:forEach items="${listSub_Group}" var="sub_group">
                <option value="${sub_group.fsub_group}"
<c:if test="${sub_group.fsub_group eq customer.subgroup_name}">selected="selected"</c:if>                
                > ${sub_group.fsub_group} </option> 
            </c:forEach>
    </select>        
</td>
</c:if>
<!-- Second Column -->					 
                <td class="required">ID Card Type2:</td>
<!-- With Drop-down(F4) help -->
<c:if test="${customer == null}">
<td><select id="idtype2">
            <c:forEach items="${listSub_Groupidtype}" var="subgroup_idtype">
                <option value="${subgroup_idtype.fsub_group}"> ${subgroup_idtype.fsub_group} </option> 
            </c:forEach>
    </select>        
</td></c:if>
<c:if test="${customer != null}">	 
<td><select id="idtype2">
            <c:forEach items="${listSub_Groupidtype}" var="subgroup_idtype">
                <option value="${subgroup_idtype.fsub_group}"
<c:if test="${subgroup_idtype.fsub_group eq customer.idtype2}">selected="selected"</c:if> 
                > ${subgroup_idtype.fsub_group} </option> 
            </c:forEach>
    </select>        
</td>               
</c:if>		
<!-- Third Column -->
<td>Last Changed Time: </td>
<td><input type="time" id="changedtime" value="<c:out value='${customer.changedtime}'/>" disabled /></td>		 
				</tr>
				<tr>
<!-- First Column -->	
					<td>Address:<i class="fa fa-asterisk"></i></td>
<td><textarea id="address" style="width: 300px; height: 100px;" required><c:out value='${customer.address}'/></textarea>
					&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;</td>
<!-- Second Column -->								 
			    <td class="required">ID Card number2:</td>
					<td><input type="text" id="idnum2" 
					 value="<c:out value='${customer.idnum2}'/>" /></td>
<!-- Third Column -->
<td>Created Date: </td>
<td><input type="date" id="createddate" value="<c:out value='${customer.createddate}'/>" disabled /></td>
				</tr>
				<tr>
<!-- First Column -->	
					<td>E-Mail:</td>
					<td><input type="text" id="mail" 
					value="<c:out value='${customer.mail}'/>" /></td>
<!-- Second Column -->								 
					<td>Comments:</td>
<td><textarea id="comments" style="width: 300px; height: 100px;"><c:out value='${customer.comments}'/> </textarea>
					&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;</td>			
<!-- Third Column -->
<td>Created Time: </td>
<td><input type="time" id="createdtime" value="<c:out value='${customer.createdtime}'/>" disabled /></td>		
				</tr>
				<tr>
<!-- First Column -->	
					<td>Contact No 1:<i class="fa fa-asterisk"></i></td>
					<td><input type="number" id="contact1" required 
					 value="<c:out value='${customer.contact1}'/>" /></td>
<!-- Second Column -->
<td>Start Date:<i class="fa fa-asterisk"></i></td>
<td><input type="date" id="joindate" required value="<c:out value='${customer.joindate}'/>" /></td>
				</tr>
				<tr>
<!-- First Column -->	
					<td>Contact No 2:</td>
					<td><input type="number" id="contact2"
					 value="<c:out value='${customer.contact2}'/>" /></td>
<!-- Second Column -->
<td>End Date:</td>
<td><input type="date" id="lastdate" value="<c:out value='${customer.lastdate}'/>" /></td>
				</tr>
				<tr><td><br></td></tr>
				<tr>
					<td colspan="2">(<i class="fa fa-asterisk"></i>Required field)</td>
				</tr>

</c:if>
<!-- Remove Attribute Value -->
<% session.removeAttribute("sesscustomerevent"); %>