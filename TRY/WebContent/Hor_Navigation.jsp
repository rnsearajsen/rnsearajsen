<!-- Reference: https://www.w3schools.com/bootstrap4/tryit.asp?filename=trybs_navbar_dropdown&stacked=h -->
<script type="text/javascript" src="script/customscript.js"></script>
<body onload="accessfunc()">
<div class="container" class="row">
	<!-- Master Data -->
	<nav class="navbar navbar-expand-sm bg-light navbar-light">
		<ul class="navbar-nav">
			<li class="nav-item dropdown" id="Master"><a
				class="navbar-brand dropdown-toggle" href=# 
				data-toggle="dropdown"> Master data </a> 
				<!-- Dropdown List -->
<!-- Remove Session Attribute Value -->
<% session.removeAttribute("sesscompanyevent"); %>				
				<div class="dropdown-menu">
					<a class="dropdown-item" href="Company">Company Name</a>
					<a class="dropdown-item" href="Group">Group</a>
				    <a class="dropdown-item" href="SubGroup">Sub-Group</a> 
				    <a class="dropdown-item" href="Owner">Owner</a>
				    <a class="dropdown-item" href="Customer">Customer</a>
				    <a class="dropdown-item" href="Vendor">Vendor</a>
				    <a class="dropdown-item" href="Employee">Employee</a>
				    <a class="dropdown-item" href="Product">Product</a> 
				    <a class="dropdown-item" href="Bankacct">Bank Account</a> 
					
				</div></li>
			<!-- Transaction Data -->
			<li class="nav-item dropdown" id="Transaction"><a class="navbar-brand dropdown-toggle" 
			href=# data-toggle="dropdown">Transaction data</a>
			<!-- Dropdown List -->
			<div class="dropdown-menu">
				    <a class="dropdown-item" href="Saleprice">Price Update</a>	
				    <a class="dropdown-item" href="Sales">Daily Update</a>
					<a class="dropdown-item" href="Purchase">Purchase/Stock Update</a>
					<a class="dropdown-item" href="Billpurpay">Purchase Bills/Payments</a>
		    </div>
			</li>
			<!-- Report -->
			<li class="nav-item dropdown" id="Report"><a class="navbar-brand dropdown-toggle" 
			href=# data-toggle="dropdown">Report</a>
			<!-- Dropdown List -->
			<div class="dropdown-menu">
				    <a class="dropdown-item" href="Salesrpt">Sales Report</a>	
				    <a class="dropdown-item" href="Expenserpt">Expense Report</a>
					<a class="dropdown-item" href="Stockrpt">Stock Report</a>
					<a class="dropdown-item" href="Empwagesrpt">Employee Wages Report</a>
					<a class="dropdown-item" href="Custcrdrpt">Customer Credit Report</a>
					<a class="dropdown-item" href="Owncrdrpt">Owner Credit Report</a>
					<a class="dropdown-item" href="Purchaserpt">Purchase Report</a>
					<a class="dropdown-item" href="Profitrpt">Profit Report</a>
					<a class="dropdown-item" href="Cashtxnrpt">Cash Txn Report</a>
		    </div>
			</li>
		</ul>
	</nav>
</div>
</body>