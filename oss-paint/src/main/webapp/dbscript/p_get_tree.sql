DELIMITER $$

DROP PROCEDURE IF EXISTS `p_get_tree` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `p_get_tree`(iid int(20), lvl int)
BEGIN
  drop table if exists tmp_tree;
  call tree_proc(iid, lvl);
  select * from tmp_tree t where t.fid = fid;
END $$

DELIMITER ;