CREATE DEFINER=`root`@`localhost` PROCEDURE `fuel_reversal`(IN fpproduct varchar(25), IN fpsalesdate date, IN fplabour_shift varchar(25), IN fpsales_priceunit float(21,2))
    MODIFIES SQL DATA
    SQL SECURITY INVOKER
    COMMENT 'Reversal: Add Sales Qty to Purchase Stock Qty and delete the sales details'
BEGIN
DECLARE v_done bool DEFAULT FALSE;
declare lv_product varchar(25);
declare lv_purchasedate date;
declare lv_sales_qty float(15,2);
declare lv_stkqty float(15,2);
declare c_sales cursor for select product, purchasedate, sales_qty from sales where (product = fpproduct and salesdate = fpsalesdate and labour_shift = fplabour_shift and sales_priceunit = fpsales_priceunit);
DECLARE CONTINUE HANDLER FOR NOT FOUND SET v_done = TRUE;  SET v_done = FALSE; 
open c_sales;
c_sales_loop: loop fetch c_sales into lv_product, lv_purchasedate, lv_sales_qty;
IF v_done THEN LEAVE c_sales_loop;end if;
if exists (SELECT product purchasedate from purchase where product = fpproduct and purchasedate = lv_purchasedate)
then 
SELECT stock_qty into lv_stkqty from purchase where product = fpproduct and purchasedate = lv_purchasedate;
set lv_stkqty = lv_stkqty + lv_sales_qty;
UPDATE  purchase set stock_qty = lv_stkqty where product = fpproduct and purchasedate = lv_purchasedate;
end if;
end loop;
close c_sales;
delete from sales where (product = fpproduct and salesdate = fpsalesdate and labour_shift = fplabour_shift and sales_priceunit = fpsales_priceunit);
END