DROP  procedure IF exists update_machine_existing_code;
DELIMITER $$
CREATE PROCEDURE update_machine_existing_code()
BEGIN
DECLARE done INT DEFAULT FALSE;
DECLARE currentId INT;
DECLARE currentName VARCHAR(45);
DECLARE newCode VARCHAR(45);
DECLARE cur CURSOR FOR SELECT machine_id,name from t_machine;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
OPEN cur;
	read_loop: LOOP
		FETCH cur INTO currentId, currentName;
		IF done THEN
			LEAVE read_loop;
		END IF;
        SELECT name into newCode FROM t_machine where machine_id = currentId;
        SET newCode = substr(newCode, 1, 5);
        select newCode;
		UPDATE t_machine SET code = newCode WHERE machine_id = currentId;
    END LOOP;
CLOSE cur;
END$$
DELIMITER ;

CALL update_machine_existing_code;
