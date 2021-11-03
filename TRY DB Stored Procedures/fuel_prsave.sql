CREATE DEFINER=`root`@`localhost` PROCEDURE `fuel_prsave`(IN fpproduct VARCHAR(25),IN fpdate date, IN fppump_id VARCHAR(25), IN fplabour_shift varchar(25), IN fpsales_priceunit float(21,2), IN fppump_reading_open float, IN fppump_reading_close float, IN fpsales_qty float, IN fpchangedby varchar(20), IN fpchangeddate date, IN fpchangedtime time,
OUT foupd_type varchar(8))
    MODIFIES SQL DATA
    SQL SECURITY INVOKER
    COMMENT 'If Entry exists for existing primary key combination, then Update Data, else Insert Data'
BEGIN 
if exists (select * from try.pumpread where ( product = fpproduct and date = fpdate and
           pump_id = fppump_id and labour_shift = fplabour_shift and sales_priceunit = fpsales_priceunit ) limit 1)
then update pumpread SET pump_reading_open = fppump_reading_open, pump_reading_close = fppump_reading_close, sales_qty = fpsales_qty, 
changedby = fpchangedby, changeddate = fpchangeddate, changedtime = fpchangedtime WHERE product = fpproduct and date = fpdate and pump_id = fppump_id and labour_shift = fplabour_shift and sales_priceunit = fpsales_priceunit; 
SET foupd_type = 'Update'; 
else
insert into pumpread (product, date, pump_id, labour_shift, sales_priceunit, pump_reading_open, pump_reading_close, sales_qty,
changedby, changeddate, changedtime) values (fpproduct, fpdate, fppump_id, fplabour_shift, fpsales_priceunit, fppump_reading_open, fppump_reading_close, fpsales_qty, fpchangedby, fpchangeddate, fpchangedtime);         
SET foupd_type = "Insert";
end if;
END