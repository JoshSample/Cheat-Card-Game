drop table users;

create table users
	(username varchar(30),
	password binary(16)
	);
	

	
alter table users
 add constraint users_username_pk primary key (username);
 
 
alter table users
 modify username varchar(30) not null; 
alter table users
 modify password binary(16) not null; 

 
 

