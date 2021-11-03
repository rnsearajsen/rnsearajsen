CREATE DEFINER=`root`@`localhost` PROCEDURE `dailytxn_collectsave`(IN fptxndate date, fplabour_shift varchar(25), fpcollect_type varchar(25),fpcollect_amt float(21,2), 
 fpcomments varchar(250), fpchangedby varchar(20), fpchangeddate date, fpchangedtime time, 
 OUT foupd_type varchar(8))
    MODIFIES SQL DATA
    SQL SECURITY INVOKER
    COMMENT 'Update/Insert Collect table based on Primary Keys "Txndate, Labour shift & Collect Type"'
BEGIN
if EXISTS (select txndate from collect where (txndate = fptxndate and labour_shift = fplabour_shift and 
collect_type = fpcollect_type) limit 1) then 
update collect SET collect_amt = fpcollect_amt, comments = fpcomments, changedby = fpchangedby, changeddate = fpchangeddate,
changedtime = fpchangedtime where txndate = fptxndate and labour_shift = fplabour_shift and collect_type = fpcollect_type; 
SET foupd_type = 'Update';
ELSE 
insert into collect (txndate, labour_shift, collect_type, collect_amt, comments, changedby, changeddate, changedtime)
values(fptxndate, fplabour_shift, fpcollect_type, fpcollect_amt, fpcomments, fpchangedby, fpchangeddate, fpchangedtime);
SET foupd_type = "Insert";
end if;
END