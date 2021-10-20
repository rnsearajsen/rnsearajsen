<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<!-- <meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="./CSS/Page_Main.css">  -->
<script src='https://kit.fontawesome.com/a076d05399.js'></script>	
<script type="text/javascript" src="script/customscript.js"> </script>	
<script type="text/javascript" src="script/companyscript.js"> </script>	

<c:if test="${user != null}">
	
<!-- Company Display  -->
<div class="container" align="center"> 
	<br>
<!-- Fuzzy Search -->
<input type="text" id="myInput" onkeyup="filtersearch()" placeholder="Search for Company Name.." title="Type in a Company">
<br><br>
<h6>${compmessage}</h6>
<!-- Fuzzy Search -->  
 </div> 
    <div id="divcomptableview" class="scrolltable" align="center"> 
<!-- id="tableview" is style to border the table: maintained in Page_Main.css -->
        <table class="scrolltable" id="comptableview" border="1" >
            <tr id="tableview" style="padding:10px">
<!-- Hide "ID Column" <th id="tableview" style="padding:10px">Company_ID</th> -->
                <th style="padding:10px; cursor:pointer" onclick="sortTable(0)">Company Name <i class='fas fa-sort'></i></th>
                <th >Suffix</th>
                <th >Address</th>
                <th >Mail</th>
                <th >Contact1</th>
                <th >Contact2</th>
                <th >Maintain</th>
            </tr>
        </table>
<br>        
    </div>
</c:if>		
