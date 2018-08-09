drop table T_USER if exists;

create table T_USER (ID bigint identity primary key, email varchar(50) not null,
                        NAME varchar(50) not null, DESCRIPTION varchar(150));
