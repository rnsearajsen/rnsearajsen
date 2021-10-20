<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- For Icon -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<c:if test="${user != null}">

				<tr>
<!-- First Column -->
					<td>Sub-Group:<i class="fa fa-asterisk"></i></td>
<!-- With Drop-down(F4) help -->
<c:if test="${purchase == null}">
<td><select id="subgroup_name">
            <c:forEach items="${listSub_Group}" var="sub_group">
                <option value="${sub_group.fsub_group}"> ${sub_group.fsub_group} </option> 
            </c:forEach>
    </select> </td></c:if>
<c:if test="${purchase != null}">
<td><select id="subgroup_name" disabled>
            <c:forEach items="${listSub_Group}" var="sub_group">
                <option value="${sub_group.fsub_group}"
<c:if test="${sub_group.fsub_group eq purchase.subgroup_name}">selected="selected"</c:if>                
                > ${sub_group.fsub_group} </option> 
            </c:forEach>
    </select> </td>
</c:if>
<!-- Second Column -->
<td>Purchase Date:<i class="fa fa-asterisk"></i></td>
<c:if test="${purchase == null}">
<td><input type="date" id="todaydate" required/></td>
</c:if>
<c:if test="${purchase != null}">
<td><input type="date" id="purchasedate" value="<c:out value='${purchase.purchasedate}'/>" disabled/></td>
</c:if>
<!-- Third Column -->
</tr>
<!-- Row of Gap -->
<tr><td><br></td></tr>
				<tr>
<!-- First Column -->
					<td >Product:<i class="fa fa-asterisk"></i></td>
<c:if test="${purchase == null}">			
					<td><select id="product" required></select></td>
</c:if>
<c:if test="${purchase != null}">
<td><input type="text" id="product" value="<c:out value='${purchase.product}'/>" disabled /></td>
</c:if>
<!-- Second Column -->
<c:if test="${purchase != null}">
<td>&nbsp;&nbsp;&nbsp;&nbsp;Unit Price:</td>
<td><input type="number" id="price_unit" step="0.01" value="<c:out value='${purchase.price_unit}'/>" disabled /></td>
</c:if>
<!-- Third Column -->

				</tr>
				<tr>
<!-- First Column -->
<td>Purchase Qty:<i class="fa fa-asterisk"></i></td>
<td><input type="text" id="purchase_qty" onchange="determine_stkqty(this)" required value="<c:out value='${purchase.purchase_qty}'/>" /></td>
<!-- Second Column -->		
<c:if test="${purchase != null}">
<td>Stock Qty:</td>
<td><input type="number" id="stock_qty" step="0.01" value="<c:out value='${purchase.stock_qty}'/>" disabled /></td>
</c:if>
<c:if test="${purchase == null}">
<td>&nbsp;&nbsp;&nbsp;&nbsp;Last Changed By:</td>
<td><input type="text" id="changedby" value="<c:out value='${user.firstname}'/>" disabled /></td>		
</c:if>	
<!-- Third Column -->
				</tr>
<c:if test="${purchase != null}">
				<tr>
<!-- First Column -->				
					<td>UoM(Unit):<i class="fa fa-asterisk"></i></td>
					<td><input type="text" id="uom" value="<c:out value='${purchase.uom}'/>" disabled /></td>
<!-- With Drop-down(F4) help 
<td><select id="uom">
            <c:forEach items="${listSub_Groupuom}" var="subgroup_uom">
                <option value="${subgroup_uom.fsub_group}"
<c:if test="${subgroup_uom.fsub_group eq purchase.uom}">selected="selected"</c:if>                
                > ${subgroup_uom.fsub_group} </option> 
            </c:forEach>
    </select>        
</td> -->
<!-- Second Column -->
		
<!-- Third Column --> 
				</tr></c:if>
				<tr>
<!-- First Column -->	
<td>Tax % Total:<i class="fa fa-asterisk"></i></td>
<td><input type="number" id="taxpercent_total" step="0.01" onchange="amt_calc(this)" required   value="<c:out value='${purchase.taxpercent_total}'/>" /></td>
<!-- Second Column -->
<c:if test="${purchase != null}">
<td>Tax Amount(Total)</td>
<td><input type="number" id="taxamt_total" step="0.01" value="<c:out value='${purchase.taxamt_total}'/>" disabled /></td>
</c:if>	
<c:if test="${purchase == null}">
<td>&nbsp;&nbsp;&nbsp;&nbsp;Last Changed Date: </td>
<td><input type="date" id="changeddate" value="<c:out value='${purchase.changeddate}'/>" disabled />
    &nbsp; &nbsp;&nbsp; &nbsp;Time: <input type="time" id="changedtime" value="<c:out value='${purchase.changedtime}'/>" disabled /></td>
</c:if>							 
<!-- Third Column -->
				</tr>
				<tr>
<!-- First Column -->		
<td>Price Total:<i class="fa fa-asterisk"></i></td>
<td><input type="text" id="price_total" required onchange="amt_calc(this)" value="<c:out value='${purchase.price_total}'/>"/></td>
			 
<!-- Second Column -->
<c:if test="${purchase != null}">
<td>&nbsp;&nbsp;&nbsp;&nbsp;Last Changed By:</td>
<td><input type="text" id="changedby" value="<c:out value='${purchase.changedby}'/>" disabled /></td>		
<input type="hidden" id="username" value="<c:out value='${user.firstname}'/>" />
</c:if>	
<!-- Third Column -->
				</tr>
				<tr>
<!-- First Column -->	
					<td>Comments:</td>
<td><textarea id="comments" style="width: 300px; height: 100px;"><c:out value='${purchase.comments}'/> </textarea></td>	
<!-- Second Column --> 
<c:if test="${purchase != null}">
<td>&nbsp;&nbsp;&nbsp;&nbsp;Last Changed Date: </td>
<td><input type="date" id="changeddate" value="<c:out value='${purchase.changeddate}'/>" disabled />
    &nbsp; &nbsp;&nbsp; &nbsp;Time: <input type="time" id="changedtime" value="<c:out value='${purchase.changedtime}'/>" disabled /></td>
</c:if>
				</tr>
				<tr><td><br></td></tr>
				<tr>
					<td colspan="2">(<i class="fa fa-asterisk"></i>Required field)</td>
				</tr>

</c:if>
<!-- Remove Attribute Value -->
<% session.removeAttribute("sesspurchaseevent"); %>