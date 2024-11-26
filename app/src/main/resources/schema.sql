create table if not exists channel (
    channel varchar(32) not null,
    updated_by varchar(128) not null,
    last_updated timestamp not null,
    primary key (channel)
);