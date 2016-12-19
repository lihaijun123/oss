DELIMITER $$

DROP FUNCTION IF EXISTS `TO_DATE` $$
CREATE DEFINER=`root`@`localhost` FUNCTION `TO_DATE`(`s_date` varchar(100),s_type varchar(100)) RETURNS varchar(100) CHARSET utf8
BEGIN
	if length(s_type)>13 then
		return DATE_FORMAT(s_date,'%Y-%m-%d %T') ;
  else
		if lower(s_type)='yyyy-mm' THEN
			return DATE_FORMAT(s_date,'%Y-%m') ;
		elseif lower(s_type)='yyyymm' THEN
			return DATE_FORMAT(s_date,'%Y%m') ;
		elseif lower(s_type)='yyyymmdd' THEN
			return DATE_FORMAT(s_date,'%Y%m%d') ;
		else
			return DATE_FORMAT(s_date,'%Y-%m-%d') ;
		end if;
  end if;
END $$

DELIMITER ;