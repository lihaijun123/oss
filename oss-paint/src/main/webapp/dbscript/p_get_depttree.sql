DELIMITER $$

DROP PROCEDURE IF EXISTS `p_get_depttree` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `p_get_depttree`(IN startId varchar(4))
BEGIN
 DECLARE _id varchar(4);
 DECLARE _pid varchar(4);
 DECLARE _name varchar(100);
declare cur_t  cursor for select department_id, department_name, department_parent_id
  from OSS_ADMIN_DEPARTMENT where department_parent_id = startId;

  declare continue handler for sqlstate '02000' set _id=null,_pid=null,_name=null;

  create temporary table IF NOT EXISTS
    tmp_dept_tree(
      department_id varchar(4),
      department_name varchar(100),
      department_parent_id varchar(4)) ;

  set @@max_sp_recursion_depth = 10;
  open cur_t;
  fetch cur_t into _id,_name,_pid;
  while (_id is not null) do
		insert into tmp_dept_tree(department_id, department_name, department_parent_id)
		select _id,_name,_pid;
     call p_get_depttree(_id);
     fetch cur_t into _id,_name,_pid;
  end while;
 END $$

DELIMITER ;