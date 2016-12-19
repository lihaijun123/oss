DELIMITER $$

DROP PROCEDURE IF EXISTS `p_menutree_test` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `p_menutree_test`(in i_resource_type varchar(1), IN startId bigint(10))
BEGIN

 DECLARE _id bigint DEFAULT 0;
 DECLARE _path VARCHAR(255);
 DECLARE _last bigint DEFAULT 0;

   create table IF NOT EXISTS
    tmp_menu_tree(
      resource_id int(10),
      resource_name varchar(100),
      resource_parent_id int(10),
      resource_type varchar(1),
    resource_interface varchar(2000),
    resource_display varchar(100),
    description varchar(4000),
    active varchar(1),
    resource_order int(10)) ENGINE=InnoDB TYPE = HEAP;

 delete from tmp_menu_tree;

 insert into tmp_menu_tree(resource_id, resource_name, resource_parent_id, resource_type, resource_interface, resource_display, description, active, resource_order)
 select resource_id, resource_name, resource_parent_id, resource_type, resource_interface, resource_display, description, active, resource_order
  from OSS_ADMIN_RESOURCE where  RESOURCE_TYPE = i_resource_type AND ACTIVE = '1' and RESOURCE_ID = startId;

 set _id  = startId;

 WHILE _id <> 0 DO
       insert into tmp_menu_tree(resource_id, resource_name, resource_parent_id, resource_type, resource_interface, resource_display, description, active, resource_order)
              select resource_id, resource_name, resource_parent_id, resource_type, resource_interface, resource_display, description, active, resource_order
              from OSS_ADMIN_RESOURCE where  RESOURCE_TYPE = i_resource_type AND ACTIVE = '1' and RESOURCE_PARENT_ID = _id;
       set _last  = _id;
       set _id  = 0;
       select resource_id into _id from tmp_menu_tree where resource_id>_last limit 1;
 END WHILE;

 END $$

DELIMITER ;