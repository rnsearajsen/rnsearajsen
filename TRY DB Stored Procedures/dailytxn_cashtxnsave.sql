CREATE DEFINER=`root`@`localhost` PROCEDURE `dailytxn_cashtxnsave`(IN fptxndate date, fplabour_shift varchar(25), fpaggramt float(21,2), fpcollectamt float(21,2),
fpdifference float(21,2), fpchangedby varchar(20), fpchangeddate date, fpchangedtime time, OUT foupd_type varchar(8))
    MODIFIES SQL DATA
    SQL SECURITY INVOKER
    COMMENT 'Insert/Update "cashtxn" table'
BEGIN
declare lv_txndate date;
declare lv_labour_shift varchar(25);
declare lv_aggramt float(21,2) default "0.00";
set foupd_type = null;
SELECT txndate, labour_shift, aggramt into lv_txndate, lv_labour_shift, lv_aggramt FROM cashtxn 
where txndate = (select max(txndate) from cashtxn) order by labour_shift desc limit 1;
if (fptxndate < lv_txndate) or ( fptxndate = lv_txndate and fplabour_shift <= lv_labour_shift) then
if (fpcollectamt != "0.00") then
update cashtxn set collection_amt = fpcollectamt, difference = fpdifference, changedby = fpchangedby, changeddate = fpchangeddate, changedtime = fpchangedtime
where txndate = fptxndate and labour_shift = fplabour_shift;
end if;
update cashtxn set aggramt = fpaggramt, changedby = fpchangedby, changeddate = fpchangeddate, changedtime = fpchangedtime
where txndate = lv_txndate and labour_shift = lv_labour_shift;
SET foupd_type = 'Update';
else
insert into cashtxn (txndate, labour_shift, aggramt, collection_amt, difference, changedby, changeddate, changedtime)
values(fptxndate, fplabour_shift, fpaggramt, fpcollectamt, fpdifference, fpchangedby, fpchangeddate, fpchangedtime);
SET foupd_type = "Insert";
end if;
END