CREATE DEFINER=`root`@`localhost` PROCEDURE `fuel_salesave`(IN fpproduct varchar(25), fpsalesdate date, fplabour_shift varchar(25), fpsales_priceunit float(21,2),
fpsubgroup_name varchar(25), fpsales_qty float(15,2), fptestqty float(15,2), fptaxpcen float(21,2), fpcomments varchar(150),
fpchangedby varchar(20), fpchangeddate date, fpchangedtime time,
OUT foupd_type varchar(8))
    MODIFIES SQL DATA
    SQL SECURITY INVOKER
    COMMENT 'Testqty to be made updated only per combination of Primary Keys in "SALES" table '
begin
declare firemsqty float(15,2);
declare fipurdate date;
declare fiuom varchar(15);
declare fipur_unit float(21,2);
declare lv_totsprice float(21,2);
declare lv_taxtotal float(21,2);
declare lv_flgtstqty int;
set lv_flgtstqty = 0;
if EXISTS (select * from sales where (product = fpproduct and salesdate = fpsalesdate and labour_shift = fplabour_shift and 
sales_priceunit = fpsales_priceunit ) limit 1) then 
call fuel_reversal(fpproduct, fpsalesdate, fplabour_shift, fpsales_priceunit); end if;
loop_label :  loop
set firemsqty = 0;
call fuel_purchaseupdate(fpproduct, fpsales_qty, fipurdate, fiuom, fipur_unit,foupd_type,firemsqty);
if foupd_type = "Nostock" then 
leave loop_label; end if;
set lv_totsprice = firemsqty * fpsales_priceunit;
set lv_taxtotal = ((lv_totsprice * fptaxpcen) / 100);
if lv_flgtstqty > 0 then 
set fptestqty = 0.00;
end if;
set lv_flgtstqty = lv_flgtstqty + 1;
if EXISTS (select * from sales where (product = fpproduct and salesdate = fpsalesdate and labour_shift = fplabour_shift and sales_priceunit = fpsales_priceunit 
and purchase_priceunit = fipur_unit) limit 1) then 
update sales SET purchasedate = fipurdate,subgroup_name = fpsubgroup_name, sales_qty = firemsqty, testqty = fptestqty, total_sales_price = lv_totsprice ,uom = fiuom, taxpercent_total = fptaxpcen,
taxamt_total = lv_taxtotal, comments = fpcomments, changedby = fpchangedby, changeddate = fpchangeddate, changedtime = fpchangedtime 
WHERE product = fpproduct and salesdate = fpsalesdate and labour_shift = fplabour_shift and sales_priceunit = fpsales_priceunit
      and purchase_priceunit = fipur_unit;
SET foupd_type = 'Update';
ELSE
insert into sales (product,salesdate,labour_shift, sales_priceunit,purchase_priceunit,purchasedate,subgroup_name, sales_qty, testqty, total_sales_price,uom , taxpercent_total,
taxamt_total, comments, changedby, changeddate, changedtime) 
values (fpproduct,fpsalesdate,fplabour_shift,fpsales_priceunit,fipur_unit,fipurdate,fpsubgroup_name, firemsqty, fptestqty, lv_totsprice, fiuom, fptaxpcen,lv_taxtotal,fpcomments, fpchangedby, fpchangeddate,fpchangedtime);
SET foupd_type = "Insert";
 end if;
 if (fpsales_qty = firemsqty) then 
leave loop_label;else
set fpsales_qty = fpsales_qty - firemsqty;
 end if;
 end loop;
end