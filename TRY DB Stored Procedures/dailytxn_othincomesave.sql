CREATE DEFINER=`root`@`localhost` PROCEDURE `dailytxn_othincomesave`(IN fptxndate date, fplabour_shift varchar(25), fpinctype varchar(45),
 fpdbamount float(21,2), fpmop varchar(25), fpch_dbamount float(21,2), fpcomments varchar(150), fpchangedby varchar(20), fpchangeddate date, fpchangedtime time, 
 OUT foupd_type varchar(8))
    MODIFIES SQL DATA
    SQL SECURITY INVOKER
    COMMENT 'Update/Insert "oth_income" table based on Primary Keys "Txndate, Labour shift & Inctype"'
BEGIN
if EXISTS (select txndate from oth_income where (txndate = fptxndate and labour_shift = fplabour_shift and 
inctype = fpinctype ) limit 1) then 
update oth_income SET dbamount = fpdbamount, mop = fpmop, ch_dbamount = fpch_dbamount, comments = fpcomments, changedby = fpchangedby, changeddate = fpchangeddate,
changedtime = fpchangedtime where txndate = fptxndate and labour_shift = fplabour_shift and inctype = fpinctype; 
SET foupd_type = 'Update';
ELSE 
insert into oth_income (txndate, labour_shift, inctype, dbamount, mop, ch_dbamount, comments, changedby, changeddate, changedtime)
values(fptxndate, fplabour_shift, fpinctype, fpdbamount, fpmop, fpch_dbamount, fpcomments, fpchangedby, fpchangeddate, fpchangedtime);
SET foupd_type = "Insert";
end if;
END