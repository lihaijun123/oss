DELIMITER $$

DROP FUNCTION IF EXISTS `TO_CHAR` $$
CREATE DEFINER=`root`@`localhost` FUNCTION `TO_CHAR`(`d_date` datetime,`s_type` varchar(100)) RETURNS varchar(100) CHARSET utf8
BEGIN
	if length(s_type)>13 then
     return DATE_FORMAT(d_date,'%Y-%m-%d %T') ;
	else
		if lower(s_type)='yyyy-mm' THEN
			return DATE_FORMAT(d_date,'%Y-%m') ;
		elseif lower(s_type)='yyyymm' THEN
			return DATE_FORMAT(d_date,'%Y%m') ;
		elseif lower(s_type)='yyyymmdd' THEN
			return DATE_FORMAT(d_date,'%Y%m%d') ;
		else
			return DATE_FORMAT(d_date,'%Y-%m-%d') ;
		end if;
  end if;
END $$

DELIMITER ;