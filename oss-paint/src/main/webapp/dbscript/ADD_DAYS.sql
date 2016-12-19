DELIMITER $$

DROP FUNCTION IF EXISTS `ADD_DAYS` $$
CREATE DEFINER=`root`@`localhost` FUNCTION `ADD_DAYS`(`s_date` datetime,s_day int) RETURNS datetime
BEGIN
	return DATE_ADD(s_date,INTERVAL s_day day);
END $$

DELIMITER ;