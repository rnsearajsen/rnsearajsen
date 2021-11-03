CREATE DEFINER=`root`@`localhost` PROCEDURE `dailytxn_expensesave`(IN fptxndate date, fplabour_shift varchar(25), fpexpense_type varchar(25),fpexpense_amt float(21,2), fpchexp_amt float(21,2),
 fpcomments varchar(250), fpchangedby varchar(20), fpchangeddate date, fpchangedtime time, 
 OUT foupd_type varchar(8))
    MODIFIES SQL DATA
    SQL SECURITY INVOKER
    COMMENT 'Update/Insert Expense table based on Primary Keys "Txndate, Labour shift & Expense Type"'
BEGIN
if EXISTS (select txndate from expense where (txndate = fptxndate and labour_shift = fplabour_shift and 
expense_type = fpexpense_type) limit 1) then 
update expense SET expense_amt = fpexpense_amt, chexp_amt = fpchexp_amt, comments = fpcomments, changedby = fpchangedby, changeddate = fpchangeddate,
changedtime = fpchangedtime where txndate = fptxndate and labour_shift = fplabour_shift and expense_type = fpexpense_type; 
SET foupd_type = 'Update';
ELSE 
insert into expense (txndate, labour_shift, expense_type, expense_amt, chexp_amt, comments, changedby, changeddate, changedtime)
values(fptxndate, fplabour_shift, fpexpense_type, fpexpense_amt, fpchexp_amt, fpcomments, fpchangedby, fpchangeddate, fpchangedtime);
SET foupd_type = "Insert";
end if;
END