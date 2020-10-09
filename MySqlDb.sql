create table contracts
(
    id         int auto_increment
        primary key,
    number     varchar(15)   null,
    tariff     int           null,
    customer   int           null,
    is_blocked int           null,
    balance    decimal(8, 2) not null,
    constraint number_UNIQUE
        unique (number)
);

create index fk_Contracts__1_idx
    on contracts (tariff);

create index fk_Contracts__2_idx
    on contracts (customer);

create table customers
(
    id              int auto_increment,
    name            varchar(45)  not null,
    surname         varchar(45)  not null,
    date_of_birth   datetime     not null,
    passport_number varchar(45)  not null,
    password        varchar(255) not null,
    address         varchar(255) not null,
    email           varchar(45)  not null,
    passport_data   varchar(255) not null,
    is_blocked      tinyint      not null,
    constraint id_UNIQUE
        unique (id)
);

alter table customers
    add primary key (id);

create table options
(
    id           int auto_increment
        primary key,
    name         varchar(45)   null,
    cost         decimal(8, 2) null,
    connect_cost decimal(8, 2) null,
    description  varchar(255)  null
);

create table forbidden_option_relationships
(
    id_first  int not null,
    id_second int not null,
    constraint fk_Forbidden_option_relationships_1
        foreign key (id_first) references options (id),
    constraint fk_forbidden_option_relationships_2
        foreign key (id_second) references options (id)
);

create index fk_Forbidden_option_relationships_1_idx
    on forbidden_option_relationships (id_first);

create index fk_Forbidden_option_relationships_2_idx
    on forbidden_option_relationships (id_second);

create table required_option_relationships
(
    id_first  int not null,
    id_second int not null,
    constraint fk_Required_option_relationships_1
        foreign key (id_first) references options (id),
    constraint fk_Required_option_relationships_2
        foreign key (id_second) references options (id)
);

create index fk_Required_option_relationships_1_idx
    on required_option_relationships (id_first);

create index fk_Required_option_relationships_2_idx
    on required_option_relationships (id_second);

create table roles
(
    id        int auto_increment
        primary key,
    role_name varchar(20) null
);

create index roles_role_id_index
    on roles (id);

create table tariffs
(
    id          int auto_increment
        primary key,
    name        varchar(45)   null,
    cost        decimal(8, 2) null,
    description varchar(255)  null
);

create table possible_options_of_tariffs
(
    tariff_id int not null,
    option_id int not null,
    constraint fk_Possible_options_of_tariffs_1
        foreign key (tariff_id) references tariffs (id),
    constraint fk_Possible_options_of_tariffs_2
        foreign key (option_id) references options (id)
);

create table used_options_of_tariff
(
    contract_id int not null,
    option_id   int not null,
    constraint fk_Used_options_of_tariffs_1
        foreign key (option_id) references options (id),
    constraint fk_Used_options_of_tariffs_2
        foreign key (contract_id) references contracts (id)
);

create index fk_Used_options_of_tariffs_1_idx
    on used_options_of_tariff (option_id);

create index fk_Used_options_of_tariffs_2_idx
    on used_options_of_tariff (contract_id);

create table users_roles
(
    user_id int null,
    role_id int null,
    constraint users_roles_customers_id_fk
        foreign key (user_id) references customers (id),
    constraint users_roles_roles_id_fk
        foreign key (role_id) references roles (id)
);

create index users_roles_role_id_index
    on users_roles (role_id);

create index users_roles_user_id_index
    on users_roles (user_id);

create view information_schema.ADMINISTRABLE_ROLE_AUTHORIZATIONS as
-- missing source code
;

create view information_schema.APPLICABLE_ROLES as
-- missing source code
;

create view information_schema.CHARACTER_SETS as
-- missing source code
;

create view information_schema.CHECK_CONSTRAINTS as
-- missing source code
;

create view information_schema.COLLATIONS as
-- missing source code
;

create view information_schema.COLLATION_CHARACTER_SET_APPLICABILITY as
-- missing source code
;

create view information_schema.COLUMNS as
-- missing source code
;

create view information_schema.COLUMN_PRIVILEGES as
-- missing source code
;

create view information_schema.COLUMN_STATISTICS as
-- missing source code
;

create view information_schema.ENABLED_ROLES as
-- missing source code
;

create view information_schema.ENGINES as
-- missing source code
;

create view information_schema.EVENTS as
-- missing source code
;

create view information_schema.FILES as
-- missing source code
;

create view information_schema.INNODB_BUFFER_PAGE as
-- missing source code
;

create view information_schema.INNODB_BUFFER_PAGE_LRU as
-- missing source code
;

create view information_schema.INNODB_BUFFER_POOL_STATS as
-- missing source code
;

create view information_schema.INNODB_CACHED_INDEXES as
-- missing source code
;

create view information_schema.INNODB_CMP as
-- missing source code
;

create view information_schema.INNODB_CMPMEM as
-- missing source code
;

create view information_schema.INNODB_CMPMEM_RESET as
-- missing source code
;

create view information_schema.INNODB_CMP_PER_INDEX as
-- missing source code
;

create view information_schema.INNODB_CMP_PER_INDEX_RESET as
-- missing source code
;

create view information_schema.INNODB_CMP_RESET as
-- missing source code
;

create view information_schema.INNODB_COLUMNS as
-- missing source code
;

create view information_schema.INNODB_DATAFILES as
-- missing source code
;

create view information_schema.INNODB_FIELDS as
-- missing source code
;

create view information_schema.INNODB_FOREIGN as
-- missing source code
;

create view information_schema.INNODB_FOREIGN_COLS as
-- missing source code
;

create view information_schema.INNODB_FT_BEING_DELETED as
-- missing source code
;

create view information_schema.INNODB_FT_CONFIG as
-- missing source code
;

create view information_schema.INNODB_FT_DEFAULT_STOPWORD as
-- missing source code
;

create view information_schema.INNODB_FT_DELETED as
-- missing source code
;

create view information_schema.INNODB_FT_INDEX_CACHE as
-- missing source code
;

create view information_schema.INNODB_FT_INDEX_TABLE as
-- missing source code
;

create view information_schema.INNODB_INDEXES as
-- missing source code
;

create view information_schema.INNODB_METRICS as
-- missing source code
;

create view information_schema.INNODB_SESSION_TEMP_TABLESPACES as
-- missing source code
;

create view information_schema.INNODB_TABLES as
-- missing source code
;

create view information_schema.INNODB_TABLESPACES as
-- missing source code
;

create view information_schema.INNODB_TABLESPACES_BRIEF as
-- missing source code
;

create view information_schema.INNODB_TABLESTATS as
-- missing source code
;

create view information_schema.INNODB_TEMP_TABLE_INFO as
-- missing source code
;

create view information_schema.INNODB_TRX as
-- missing source code
;

create view information_schema.INNODB_VIRTUAL as
-- missing source code
;

create view information_schema.KEYWORDS as
-- missing source code
;

create view information_schema.KEY_COLUMN_USAGE as
-- missing source code
;

create view information_schema.OPTIMIZER_TRACE as
-- missing source code
;

create view information_schema.PARAMETERS as
-- missing source code
;

create view information_schema.PARTITIONS as
-- missing source code
;

create view information_schema.PLUGINS as
-- missing source code
;

create view information_schema.PROCESSLIST as
-- missing source code
;

create view information_schema.PROFILING as
-- missing source code
;

create view information_schema.REFERENTIAL_CONSTRAINTS as
-- missing source code
;

create view information_schema.RESOURCE_GROUPS as
-- missing source code
;

create view information_schema.ROLE_COLUMN_GRANTS as
-- missing source code
;

create view information_schema.ROLE_ROUTINE_GRANTS as
-- missing source code
;

create view information_schema.ROLE_TABLE_GRANTS as
-- missing source code
;

create view information_schema.ROUTINES as
-- missing source code
;

create view information_schema.SCHEMATA as
-- missing source code
;

create view information_schema.SCHEMA_PRIVILEGES as
-- missing source code
;

create view information_schema.STATISTICS as
-- missing source code
;

create view information_schema.ST_GEOMETRY_COLUMNS as
-- missing source code
;

create view information_schema.ST_SPATIAL_REFERENCE_SYSTEMS as
-- missing source code
;

create view information_schema.ST_UNITS_OF_MEASURE as
-- missing source code
;

create view information_schema.TABLES as
-- missing source code
;

create view information_schema.TABLESPACES as
-- missing source code
;

create view information_schema.TABLE_CONSTRAINTS as
-- missing source code
;

create view information_schema.TABLE_PRIVILEGES as
-- missing source code
;

create view information_schema.TRIGGERS as
-- missing source code
;

create view information_schema.USER_PRIVILEGES as
-- missing source code
;

create view information_schema.VIEWS as
-- missing source code
;

create view information_schema.VIEW_ROUTINE_USAGE as
-- missing source code
;

create view information_schema.VIEW_TABLE_USAGE as
-- missing source code
;


