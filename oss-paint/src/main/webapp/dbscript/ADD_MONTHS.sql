DELIMITER $$

DROP FUNCTION IF EXISTS `ADD_MONTHS` $$
CREATE DEFINER=`root`@`localhost` FUNCTION `ADD_MONTHS`(`s_date` datetime,s_month int) RETURNS datetime
BEGIN
	return DATE_ADD(s_date,INTERVAL s_month month);
END $$

DELIMITER ;