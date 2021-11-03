CREATE DEFINER=`root`@`localhost` PROCEDURE `pktoil_sales`(IN fpsubgroup_name varchar(25), IN fpshift varchar(25), IN fpsdate date, 
out foproduct varchar(25), OUT fosales_qty float(15,2),  OUT fosell_price float (21,2))
    MODIFIES SQL DATA
    SQL SECURITY INVOKER
    COMMENT 'Get Sales of Packet Oil based on shift & Date'
BEGIN
DECLARE v_done bool DEFAULT FALSE;
declare lv_product varchar(25);
declare c_product cursor for select name from product where (subgroup_name = fpsubgroup_name);
DECLARE CONTINUE HANDLER FOR NOT FOUND SET v_done = TRUE;  SET v_done = FALSE; 
open c_product;
c_product_loop: loop fetch c_product into lv_product;
IF v_done THEN LEAVE c_product_loop;end if;
set foproduct = lv_product;
select sales_priceunit, sales_qty into fosell_price, fosales_qty from sales where product = lv_product and 
salesdate = fpsdate and labour_shift = fpshift ;
if ( fosell_price = "0.00" )
then 
SELECT sell_price into fosell_price from saleprice where product = lv_product and subgroup_name = fpsubgroup_name 
and date = (select max(date) from saleprice where product = lv_product) ;
end if;
end loop;
close c_product;
END