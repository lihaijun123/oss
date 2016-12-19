DELIMITER $$

DROP PROCEDURE IF EXISTS `p_get_dept` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `p_get_dept`(IN startId varchar(4))
BEGIN
	drop table if exists tmp_dept_tree;
  call p_get_depttree(startId);
  select * from tmp_dept_tree t;
END $$

DELIMITER ;