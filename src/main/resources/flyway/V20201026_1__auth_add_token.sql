alter table auth
	add token varchar(255);

create unique index auth_token_uindex
	on auth (token);