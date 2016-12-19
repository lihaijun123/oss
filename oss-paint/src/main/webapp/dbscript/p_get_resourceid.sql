DELIMITER $$

DROP PROCEDURE IF EXISTS `p_get_resourceid` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `p_get_resourceid`(in i_resource_type varchar(1), in i_role_id int, in i_user_id varchar(30))
BEGIN

declare done int default 0;
declare v_index int default 0;
declare v_RESOURCE_ID varchar(30);

declare cur1 cursor for
SELECT RESOURCE_ID
FROM OSS_ADMIN_ROLE_RESOURCE
WHERE ROLE_ID = i_role_id
UNION
SELECT RESOURCE_ID
FROM OSS_ADMIN_USER_RESOURCE
WHERE USER_ID = i_user_id;

DECLARE CONTINUE HANDLER FOR NOT FOUND SET done=1;

create table IF NOT EXISTS
    tmp_menu_sum(
      resource_id int(10),
      resource_name varchar(100),
      resource_parent_id int(10),
      resource_type varchar(1),
    resource_interface varchar(2000),
    resource_display varchar(100),
    description varchar(4000),
    active varchar(1),
    resource_order int(10));

    delete from tmp_menu_sum;

OPEN cur1;
emp_loop: LOOP
FETCH cur1 INTO v_RESOURCE_ID;

IF done=1 THEN
	LEAVE emp_loop;
END IF;

call p_menutree_test(i_resource_type, v_RESOURCE_ID);

insert into tmp_menu_sum(resource_id, resource_name, resource_parent_id, resource_type, resource_interface, resource_display, description, active, resource_order)
select src.* from tmp_menu_tree, OSS_ADMIN_RESOURCE src where tmp_menu_tree.resource_id= src.resource_id;

delete from tmp_menu_tree;

END LOOP emp_loop;
CLOSE cur1;


SELECT A.*, if(B.RESOURCE_ID is null, '', A.RESOURCE_INTERFACE) FLAG
  FROM
  (select distinct * from tmp_menu_sum) A LEFT outer JOIN
  (SELECT RESOURCE_ID FROM OSS_ADMIN_ROLE_RESOURCE C WHERE ROLE_ID = i_role_id UNION SELECT RESOURCE_ID FROM OSS_ADMIN_USER_RESOURCE D WHERE USER_ID = i_user_id) B
  ON A.RESOURCE_ID = B.RESOURCE_ID;
END $$

DELIMITER ;