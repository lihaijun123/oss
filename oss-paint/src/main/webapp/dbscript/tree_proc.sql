DELIMITER $$

DROP PROCEDURE IF EXISTS `tree_proc` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `tree_proc`(iid bigint(20),lvl int)
BEGIN
  declare tid bigint(10) default -1;
  declare tfid bigint(10) default -1;

  declare cur_t  cursor for select id,pid from t_tree
     where pid = iid;

  declare continue handler for sqlstate '02000' set tid=null,tfid=null;

  prepare stmt_tmp from 'create temporary table IF NOT EXISTS tmp_tree(id int(10),fid int(10),lvl int(10))';
  execute stmt_tmp;

  set @@max_sp_recursion_depth = 10;
  set @lvl = lvl;
  open cur_t;
  fetch cur_t into tid,tfid;
  while (tid is not null) do
     set @tid = tid;
     set @fid = tfid;
     prepare stmt_tmp from 'insert into tmp_tree  values(?,?,?)';
     execute stmt_tmp using @tid,@fid, @lvl;

     call tree_proc(tid,lvl+1);
     fetch cur_t into tid,tfid;
  end while;
END $$

DELIMITER ;