create table user (
  id int not null primary key ,
  username varchar(64) not null ,
  password varchar(60) not null ,
  enabled boolean default true not null
);

create table role (
  id int not null primary key ,
  name varchar(64) not null
);

create table permission (
  id int not null primary key ,
  name varchar(64) not null
);

create table user_role (
  id int not null primary key ,
  user_id int not null ,
  role_id int not null
);

create table role_permission (
  id int not null primary key ,
  role_id int not null ,
  permission_id int not null
);
