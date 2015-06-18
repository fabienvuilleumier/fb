CREATE DEFINER = CURRENT_USER TRIGGER `fablab`.`t_mvt_stock_BEFORE_INSERT` BEFORE INSERT ON `t_mvt_stock` FOR EACH ROW
BEGIN
	INSERT INTO t_mvt_stock (mvt_date, supply_id, user_id, quantity, io) 
		VALUES (new.purchasse_date, new.supply_id, new.user_id, new.quantity, "output");
END
