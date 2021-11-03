CREATE DEFINER=`root`@`localhost` PROCEDURE `fuel_purchaseupdate`(IN fpproduct varchar(25), IN fpsales_qty float(15,2),OUT fopurdate date, OUT fouom varchar(15), OUT fopur_unit float(21,2), OUT foupd_type varchar(8), OUT foremsqty float(15,2))
    MODIFIES SQL DATA
    SQL SECURITY INVOKER
    COMMENT 'Get Corresponding Purchase Price/Stock Qty /  Get Purchase Price whose Purchase Date is lesser'
begin
DECLARE lv_stkqty float(15,2) ;
declare lv_pqty float(15,2);
declare lv_remqty float(15,2);
declare lv_ppur_unit float(21,2);
set lv_remqty = fpsales_qty, fopur_unit = "0.00";
loop_label :  loop
 if (lv_remqty = 0) then 
leave loop_label; end if;
if exists (select product purchasedate from purchase where product = fpproduct and stock_qty > 0 limit 1) then 
select purchasedate, purchase_qty, stock_qty, uom, price_unit from purchase
where product = fpproduct and stock_qty > 0 order by purchasedate asc limit 1 into fopurdate, lv_pqty, lv_stkqty, fouom, lv_ppur_unit;
if (fopur_unit != "0.00" && lv_ppur_unit != fopur_unit) then 
leave loop_label; end if;
 if (lv_stkqty >= lv_remqty) then 
set lv_stkqty = lv_stkqty - lv_remqty;
set lv_remqty = 0;
else
set lv_remqty = fpsales_qty - lv_stkqty;
set lv_stkqty = 0;
end if;
SET fopur_unit = lv_ppur_unit;
set foremsqty = fpsales_qty - lv_remqty;
update purchase set stock_qty = lv_stkqty where product = fpproduct and purchasedate = fopurdate;
else
set foupd_type = "Nostock";
leave loop_label; end if;
end loop;
end