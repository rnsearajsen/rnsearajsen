CREATE DEFINER=`root`@`localhost` PROCEDURE `procedure_cashtxn_save`(IN fptxndate date, fplabour_shift varchar(25), fpaggramt float(21,2), fpcollection_amt float(21,2), 
fpday_crd float(21,2), fpday_db float(21,2), fpchangedby varchar(20), fpchangeddate date, fpchangedtime time, 
 OUT foupd_type varchar(8))
    MODIFIES SQL DATA
    SQL SECURITY INVOKER
    COMMENT 'Update/Insert "cashtxn" table based on Primary Keys "Txndate & Labour shift "'
BEGIN
if EXISTS (select txndate from cashtxn where (txndate = fptxndate and labour_shift = fplabour_shift ) limit 1) then 
update cashtxn SET aggramt = fpaggramt, collection_amt = fpcollection_amt, day_crd = fpday_crd, day_db = fpday_db, changedby = fpchangedby, changeddate = fpchangeddate,
changedtime = fpchangedtime where txndate = fptxndate and labour_shift = fplabour_shift; 
SET foupd_type = 'Update';
ELSE 
insert into cashtxn (txndate, labour_shift, aggramt, collection_amt, day_crd, day_db, changedby, changeddate, changedtime)
values(fptxndate, fplabour_shift, fpaggramt, fpcollection_amt, fpday_crd, fpday_db, fpchangedby, fpchangeddate, fpchangedtime);
SET foupd_type = "Insert";
end if;
END