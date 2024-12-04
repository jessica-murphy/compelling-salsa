create table if not exists channel (
    channel_id varchar(16) not null,
    updated_by varchar(128) not null,
    last_updated timestamp not null,
    primary key (channel_id)
);