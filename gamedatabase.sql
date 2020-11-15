drop table contact;
drop table users;

create table users
	(username varchar(30),
	password binary(16)
	);
	
create table contact
	(username varchar(30),
	contactUsername varchar(30)
	);

	
alter table users
 add constraint users_username_pk primary key (username);
 
alter table contact
 add constraint contact_contactUsername_pk primary key (contactUsername);
 
alter table contact
 add constraint	contact_username_fk foreign key(username)
 references users(username);
 
alter table users
 modify username varchar(30) not null; 
alter table users
 modify password binary(16) not null; 
alter table contact
 modify username varchar(30) not null; 
 

 
 

