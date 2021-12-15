create table "system"
(
    id   bigserial
        primary key,
    name varchar(255) not null
);

create table commodity_category
(
    id   bigserial
        primary key,
    name varchar(255) not null
        constraint uk_t0m8iwjna37uap3gbcke99xav
            unique
);

create table commodity
(
    id          bigserial
        primary key,
    eddn_name   varchar(255) not null
        constraint idx_commodity_eddn_name_unq
            unique,
    name        varchar(255) not null
        constraint idx_commodity_name_unq
            unique,
    category_id bigint       not null
        constraint fkjmsksq8oe8x12yjx13y3vfp66
            references commodity_category
);

create table economy
(
    id   bigserial
        primary key,
    name varchar(255) not null
);

create table station
(
    id     bigint       not null
        primary key,
    name   varchar(255) not null
        constraint idx_stationentity_name_unq
            unique,
    system bigint       not null
        constraint fkqbgjn42xst0i963od9k3keva8
            references system
);

create table prohibited_at_station
(
    id           bigserial
        primary key,
    commodity_id bigint not null
        constraint fkk19fsjiqis6vsy3ojvnlojdx5
            references commodity,
    station_id   bigint not null
        constraint fk49kouwaf56doopiiykr12welw
            references station
);

create table stations_economies
(
    id         bigserial
        primary key,
    proportion double precision,
    economy_id bigint not null
        constraint fkmnsf16hrykyh9w1gmfbpul799
            references economy,
    station_id bigint not null
        constraint fkgl8wro82cg89pg8m3m2hpq4x6
            references station
);

create table commodities_at_station
(
    id           bigserial
        primary key,
    buy_price    bigint,
    demand       bigint,
    sell_price   bigint,
    stock        bigint,
    commodity_id bigint not null
        constraint fkoo5000fb451xha1mg67v9qgmp
            references commodity,
    station_id   bigint not null
        constraint fk9kkhyqhld8wb5uuaqhkpt2x0c
            references station
);