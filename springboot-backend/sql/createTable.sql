set FOREIGN_KEY_CHECKS=0;
drop table if exists `user`;
drop table if exists `level_map`;
drop table if exists `user_level_map`;
set FOREIGN_KEY_CHECKS=1;

create table `user` (
  `id` int(11) not null AUTO_INCREMENT,
  `name` varchar(16) not null,
  `password` varchar(16) not null,
  primary key (`id`),
  unique key `name` (`name`)
) ENGINE=InnoDB default CHARSET=utf8;

create table `level_map` (
  `id` int(11) not null AUTO_INCREMENT,
  `name` varchar(16) not null,
  `map_string` varchar(20) not null,
  `map_xy_string` varchar(24) not null,
  `top_id` int(11),
  primary key (`id`),
  unique key `name` (`name`),
  unique key `map_string` (`map_string`),
  unique key `map_xy_string` (`map_xy_string`)
) ENGINE=InnoDB default CHARSET=utf8;

create table `user_level_map`(
   `id` int(11) not null AUTO_INCREMENT,
  `user_id` int(11) not null,
  `level_map_id` int(11) not null,
  `time_ms` bigint(31) not null,
  `step_count` bigint(31)not null,
  `score` bigint(31) not null,
  `star` int(2) not null,
  `date` timestamp not null default current_timestamp on update current_timestamp,
  `state` int(2) not null default 0,
  primary key (`id`),
  unique key `user_level_map` (`user_id`,`level_map_id`),
  foreign key (`user_id`) references `user` (`id`),
  foreign key (`level_map_id`) references `level_map` (`id`)
) ENGINE=InnoDB default CHARSET=utf8;

alter table `level_map` add constraint `level_map_top_id` foreign key (`top_id`) references `user_level_map` (`id`);

insert into `user` (`name`,`password`) values ("top_1","1");
insert into `user` (`name`,`password`) values ("top_2","1");
insert into `user` (`name`,`password`) values ("top_3","1");
insert into `level_map` (`name`,`map_string`,`map_xy_string`) values
("横刀立马","01120112344537856ab9","001030021232041323341424"),
("指挥若定","01120112644937853ab5","001030031233021323321424"),
("将拥曹营","a11b0112035263594478","011031120422032434330030"),
("齐头并进","01120112678934453ab5","001030031333021222321424"),
("兵分三路","61190112044237853ab5","011031031233001323301424"),
("雨声淅沥","01160117344532a582b9","001013021232303104342324"),
("左右步兵","6117811902350235a44b","021012221432003001310434"),
("桃花园中","6117011203528359a44b","011031121422003003330434"),
("一路进军","0116011723582359a44b","001002121422303132330434"),
("一路顺风","0116011734453825a92b","001023021232303113140434");
insert into `user_level_map` (`user_id`,`level_map_id`,`time_ms`,`step_count`,`score`,`star`) values (1,1,3000000,20,10,2);
insert into `user_level_map` (`user_id`,`level_map_id`,`time_ms`,`step_count`,`score`,`star`) values (1,2,30000,20,10,2);
insert into `user_level_map` (`user_id`,`level_map_id`,`time_ms`,`step_count`,`score`,`star`) values (1,3,30000,20,10,2);
insert into `user_level_map` (`user_id`,`level_map_id`,`time_ms`,`step_count`,`score`,`star`) values (1,4,30000,20,10,2);
insert into `user_level_map` (`user_id`,`level_map_id`,`time_ms`,`step_count`,`score`,`star`) values (1,5,30000,20,10,2);
insert into `user_level_map` (`user_id`,`level_map_id`,`time_ms`,`step_count`,`score`,`star`) values (2,5,30000,20,10,2);
insert into `user_level_map` (`user_id`,`level_map_id`,`time_ms`,`step_count`,`score`,`star`) values (3,5,30000,20,10,2);
insert into `user_level_map` (`user_id`,`level_map_id`,`time_ms`,`step_count`,`score`,`star`) values (2,4,30000,20,10,2);
update `level_map` set `top_id`=1 where `id`=1;

drop view if exists `user_info`;

create view `user_info` as select user.`id`,`name`,`password`,count(*) as `progress`,sum(`score`) as `scores`,sum(`star`) as `stars` from `user_level_map` left join `user` on user_id=user.`id` group by `user_id`;

drop view if exists `level_map_info`;

create view `level_map_info` as select level_map.`id`,level_map.`name`,`map_string`,`map_xy_string`,user.`name` as top_user_name,`score` as top_user_score from (`level_map` left join `user_level_map` on top_id=user_level_map.`id`) left join `user` on `user_id`=user.`id`;

select `level_map_id` from `user_level_map` where `user_id`=1;

drop view if exists `user_level_map_info`;

create view  `user_level_map_info` as select user_level_map.`id`,user.`name` as `user_name`,level_map.`name` as `level_map_name`,`time_ms`,`step_count`,`score`,`star`,`date` from (`user_level_map` left join `user` on user_id=user.id) left join `level_map` on `level_map_id`=`level_map`.id;