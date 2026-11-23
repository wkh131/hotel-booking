create table partner
(
    partner_id     bigint primary key generated always as identity,
    name           text unique not null,
    title          varchar(100), -- official title
    address_line   varchar(100), -- street address
    locality       varchar(100), -- city or town
    state_province varchar(100), -- state or province
    country_code   char(2),      -- country code
    postal_code    varchar(20),  -- zip code
    phone_number   varchar(20),
    email_address  varchar(100)
);

create table hotel
(
    hotel_id       bigint primary key generated always as identity,
    name           text unique not null,
    partner_id     bigint      not null references partner (partner_id),
    title          varchar(100), -- official title
    address_line   varchar(100), -- street address
    locality       varchar(100), -- city or town
    state_province varchar(100), -- state or province
    country_code   char(2),      -- country code
    postal_code    varchar(20),  -- zip code
    phone_number   varchar(20),
    latitude       float,        -- corrected latitude
    longitude      float,        -- corrected longitude
    rating         int
);

create table room_type
(
    room_type_id bigint generated always as identity primary key,
    hotel_id           bigint             not null references hotel (hotel_id),
    type_name          varchar(64) unique not null,
    type_description   varchar(256)
)

create table room_type_override
(
    room_type_override_id bigint generated always as identity primary key,
    room_type_id           bigint             not null references room_type (room_type_id),
    type_name          varchar(64) unique not null,
    type_description   varchar(256)
)

-- TODO Wasim: Fix
create table visual_asset_type
(
    visual_asset_type_id bigint generated always as identity primary key,
    asset_type           varchar(20) not null,
    type_encoding        varchar(20) not null
);

create table visual_asset
(
    visual_asset_id      bigint generated always as identity primary key,
    hotel_id             bigint        not null references hotels (hotel_id),
    visual_asset_type_id bigint        not null references visual_asset_type (visual_asset_type_id),
    visual_asset_url     varchar(2048) not null
);

create table amenity
(
    amenity_id   bigint generated always as identity primary key,
    amenity_name varchar(64) unique not null
);

create table hotel_amenity
(
    hotel_amenity_id bigint generated always as identity primary key,
    hotel_id         bigint references hotel (hotel_id),
    amenity_id       bigint references amenity (amenity_id)
);

create table policy_type
(
    policy_type_id   bigint generated always as identity primary key,
    policy_type_name varchar(256) unique not null
);

create table policy_type_option
(
    policy_type_option_id bigint generated always as identity primary key,
    policy_type_id        bigint       not null references policy_type (policy_type_id),
    option_value          varchar(128) not null
);

create table hotel_policy
(
    hotel_policy_id   bigint generated always as identity primary key,
    hotel_id          bigint not null references hotel (hotel_id),
    policy_type_id    bigint not null references policy_type (policy_type_id),
    custom_desciption varchar(1024) -- TODO Wasim: What size?
);