CREATE DEFINER=`root`@`localhost` PROCEDURE `dailytxn_empwagessave`(IN fpname varchar(45), fptxndate date, fplabour_shift varchar(25), fpattd varchar(3), fpcrdamount float(21,2),
 fpdbamount float(21,2), fpchcrdamount float(21,2), fpchdbamount float(21,2), fpactsal float(21,2), fpnentry varchar(3), fpsdate date, fpedate date, fpchangedby varchar(20), fpchangeddate date, fpchangedtime time, 
 OUT foupd_type varchar(8))
    MODIFIES SQL DATA
    SQL SECURITY INVOKER
    COMMENT 'Update/Insert "empwages" table based on Primary Keys "Txndate, Labour shift & name"'
BEGIN
declare fpopdue float(21,2);
set fpopdue = 0;
if (fpnentry = 'X') then
call dailytxn_empwagespdue(fpname, fptxndate, fpsdate, fpedate, fpopdue);
end if;
if EXISTS (select txndate from empwages where (txndate = fptxndate and labour_shift = fplabour_shift and 
name = fpname ) limit 1) then 
update empwages SET attendance = fpattd, crdamount = fpcrdamount, dbamount = fpdbamount, ch_crdamount = fpchcrdamount, ch_dbamount = fpchdbamount, changedby = fpchangedby, changeddate = fpchangeddate,
changedtime = fpchangedtime where txndate = fptxndate and labour_shift = fplabour_shift and name = fpname; 
SET foupd_type = 'Update';
ELSE 
insert into empwages (txndate, labour_shift, name, attendance, crdamount, dbamount, ch_crdamount, ch_dbamount, pastdue, act_salary, changedby, changeddate, changedtime)
values(fptxndate, fplabour_shift, fpname, fpattd, fpcrdamount, fpdbamount, fpchcrdamount, fpchdbamount, fpopdue, fpactsal, fpchangedby, fpchangeddate, fpchangedtime);
SET foupd_type = "Insert";
end if;
END