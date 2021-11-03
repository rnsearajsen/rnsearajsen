CREATE DEFINER=`root`@`localhost` PROCEDURE `billpurpay_save`(IN fpbill_no int, fpbill_date date, fpbill_prdgrp varchar(45), fpbill_amt float(21,2),
fpdiscount float(21,2), fpinterest float(21,2), fppaymnt_amt float(21,2), fppaymnt_date date, fppaid varchar(1), fpchangedby varchar(25), fpchangeddate date, fpchangedtime time, OUT foupd_type varchar(8))
    MODIFIES SQL DATA
    SQL SECURITY INVOKER
    COMMENT 'Insert/Update "billpurpay" table'
BEGIN
if EXISTS (select bill_no from billpurpay where (bill_no = fpbill_no and bill_date = fpbill_date and bill_prdgrp = fpbill_prdgrp) limit 1) then 
update billpurpay SET bill_amt = fpbill_amt, discount = fpdiscount, interest = fpinterest, paymnt_amt = fppaymnt_amt, paymnt_date = fppaymnt_date,
                     paid = fppaid, changedby = fpchangedby, changeddate = fpchangeddate, changedtime = fpchangedtime
                     where bill_no = fpbill_no and bill_date = fpbill_date and bill_prdgrp = fpbill_prdgrp;
SET foupd_type = 'Update';
ELSE
insert into billpurpay (bill_no, bill_date, bill_prdgrp, bill_amt, discount, interest, paymnt_amt, paymnt_date, paid, changedby, changeddate, changedtime)
values (fpbill_no, fpbill_date, fpbill_prdgrp, fpbill_amt, fpdiscount, fpinterest, fppaymnt_amt, fppaymnt_date, fppaid, fpchangedby, fpchangeddate, fpchangedtime);
SET foupd_type = 'Insert';
END IF;
END