create table general_entity
(
    id          bigint auto_increment
        primary key,
    description varchar(255) null,
    name        varchar(255) null
);

create table general_entity_type
(
    id          bigint auto_increment
        primary key,
    description varchar(255) null,
    name        varchar(255) null
);

create table general_entity_entity_type
(
    general_entity_id      bigint not null,
    general_entity_type_id bigint not null,
    constraint UK4mdl8qjtx9q7396giyec6t11q
        unique (general_entity_id, general_entity_type_id),
    constraint FKer8mgmqemm0d8mkyk4kg2p2t0
        foreign key (general_entity_type_id) references general_entity_type (id),
    constraint FKq1vktpsekspf8nhsdpvkrs5lw
        foreign key (general_entity_id) references general_entity (id)
);

create table phone_number
(
    id                  bigint auto_increment
        primary key,
    description         varchar(255) null,
    phone_number        varchar(255) null,
    phone_number_status tinyint      null,
    spam_report_count   int          not null,
    check (`phone_number_status` between 0 and 1)
);

create table report
(
    id          bigint auto_increment
        primary key,
    description varchar(255) null,
    report_date datetime(6)  null
);

create table roles
(
    id   bigint auto_increment
        primary key,
    name varchar(255) null
);

create table users
(
    id       bigint auto_increment
        primary key,
    deleted  bit          not null,
    email    varchar(255) not null,
    name     varchar(255) null,
    password varchar(255) not null,
    username varchar(255) not null,
    constraint UK6dotkott2kjsp8vw4d0m25fb7
        unique (email),
    constraint UKr43af9ap4edm43mmtq01oddj6
        unique (username)
);

create table users_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    constraint FK2o0jvgh89lemvvo17cbqvdxaa
        foreign key (user_id) references users (id),
    constraint FKj6m8fwv7oqv74fcehir1a9ffy
        foreign key (role_id) references roles (id)
);


