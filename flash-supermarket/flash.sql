create table relationship
(
    user_name   varchar(255) not null,
    follow_name varchar(255) not null,
    primary key (user_name, follow_name)
);

create table repo_goods
(
    repo_id      int          not null
        primary key,
    commodity_id varchar(255) not null
);

create table star
(
    user_name     varchar(255) not null,
    repository_id int          not null,
    primary key (user_name, repository_id)
);

create table user
(
    user_name   varchar(255)  not null
        primary key,
    description varchar(255)  null,
    avatar      varchar(1024) null,
    pass_word   varchar(255)  not null
);

create table repository
(
    repository_id      int           not null
        primary key,
    repository_title   varchar(255)  null,
    repository_content varchar(1024) null,
    user_name          varchar(255)  not null,
    stars              int           not null,
    is_public          tinyint(1)    not null,
    constraint blog_user_user_name_fk
        foreign key (user_name) references user (user_name)
);

