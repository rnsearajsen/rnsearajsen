CREATE DEFINER=`root`@`localhost` PROCEDURE `dailytxn_crdcustpdue`(IN fpname varchar(45), fptxndate date, fppsdate date, fppedate date, OUT fpopdue float(21,2))
    MODIFIES SQL DATA
    SQL SECURITY INVOKER
    COMMENT 'Get consolidated Pastdue of Previous Month'
BEGIN
if EXISTS (select txndate from cust_credit where (txndate > fptxndate and name = fpname ) limit 1) then 
set fpopdue = 0;
else
select ((sum(crdamount) + sum(ch_crdamount)) - (sum(dbamount) + sum(ch_dbamount)) + coalesce(sum(pastdue),0)) as pastdue from cust_credit 
where name = fpname and txndate between fppsdate and fppedate into fpopdue;
end if;
END