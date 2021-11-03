CREATE DEFINER=`root`@`localhost` PROCEDURE `dailytxn_crdownersave`(IN fptxndate date, fplabour_shift varchar(25), fpname varchar(45), fpcrdamount float(21,2),
 fpdbamount float(21,2), fpmop varchar(25), fpchcrdamount float(21,2), fpchdbamount float(21,2), fpcomments varchar(150), fpnentry varchar(3), fpsdate date, fpedate date, fpchangedby varchar(20), fpchangeddate date, fpchangedtime time, 
 OUT foupd_type varchar(8))
    MODIFIES SQL DATA
    SQL SECURITY INVOKER
    COMMENT 'Update/Insert "owner_credit" table based on Primary Keys "Txndate, Labour shift & name"'
BEGIN
declare fpopdue float(21,2);
set fpopdue = 0;
if (fpnentry = 'X') then
call dailytxn_crdownerpdue(fpname, fptxndate, fpsdate, fpedate, fpopdue);
end if;
if EXISTS (select txndate from owner_credit where (txndate = fptxndate and labour_shift = fplabour_shift and 
name = fpname ) limit 1) then 
update owner_credit SET crdamount = fpcrdamount, dbamount = fpdbamount, mop = fpmop, ch_crdamount = fpchcrdamount, ch_dbamount = fpchdbamount, comments = fpcomments, changedby = fpchangedby, changeddate = fpchangeddate,
changedtime = fpchangedtime where txndate = fptxndate and labour_shift = fplabour_shift and name = fpname; 
SET foupd_type = 'Update';
ELSE 
insert into owner_credit (txndate, labour_shift, name, crdamount, dbamount, mop, ch_crdamount, ch_dbamount, pastdue, comments, changedby, changeddate, changedtime)
values(fptxndate, fplabour_shift, fpname, fpcrdamount, fpdbamount, fpmop, fpchcrdamount, fpchdbamount, fpopdue, fpcomments, fpchangedby, fpchangeddate, fpchangedtime);
SET foupd_type = "Insert";
end if;
END