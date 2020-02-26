CREATE SEQUENCE hibernate_sequence START 10000;

create table users (
    id bigint NOT NULL,
    enabled boolean not null,
    password varchar(255),
    photo bytea,
    username varchar(255),
    primary key (id)
);

create table oauth_access_token (
  token_id VARCHAR(256),
  token bytea,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication bytea,
  refresh_token VARCHAR(256),
  primary key (token_id)
);