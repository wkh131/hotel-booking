create table partners
(
    partner_id     bigint primary key generated always as identity,
    resource_name  varchar(2048) not null unique,
    title          varchar(100), -- official title
    address_line   varchar(100), -- street address
    locality       varchar(100), -- city or town
    state_province varchar(100), -- state or province
    country_code   char(2),      -- country code
    postal_code    varchar(20),  -- zip code
    phone_number   varchar(20),
    email_address  varchar(100)
);

create table hotels
(
    hotel_id       bigint primary key generated always as identity,
    resource_name  varchar(2048) not null unique,
    partner_id     bigint        not null references partners (partner_id),
    title          varchar(100), -- official title
    address_line   varchar(100), -- street address
    locality       varchar(100), -- city or town
    state_province varchar(100), -- state or province
    country_code   char(2),      -- country code
    postal_code    varchar(20),  -- zip code
    phone_number   varchar(20),
    email_address  varchar(100),
    latitude       float,        -- corrected latitude
    longitude      float,        -- corrected longitude
    rating         int
);

create table room_types
(
    room_type_id           bigint primary key generated always as identity,
    resource_name          varchar(2048) not null unique,
    hotel_id               bigint        not null references hotels (hotel_id),
    display_name           varchar(100),
    default_price_per_room float,
    capacity_per_room      int,
    total_rooms            int
);

create table room_type_overrides
(
    room_type_override_id bigint primary key generated always as identity,
    resource_name         varchar(2048) not null unique,
    room_type_id          bigint        not null references room_types (room_type_id),
    start_date_inc        date          not null,
    end_date_inc          date          not null,
    price                 float,
    num_rooms             int
);

create table amenities
(
    amenity_id    bigint primary key generated always as identity,
    resource_name varchar(2048) not null unique,
    display_name  varchar(100)
);

create table hotel_amenities
(
    hotel_amenity_id bigint primary key generated always as identity,
    hotel_id         bigint references hotels (hotel_id),
    amenity_id       bigint references amenities (amenity_id)
);

create table visual_asset_types
(
    visual_asset_type_id bigint primary key generated always as identity,
    type                 varchar(10)  not null, -- short id
    encoding             varchar(100) not null
);

create table visual_assets
(
    visual_asset_id bigint primary key generated always as identity,
    resource_name   varchar(2048) not null unique,
    hotel_id        bigint        not null references hotels (hotel_id),
    type_id         bigint        not null references visual_asset_types (visual_asset_type_id),
    url             varchar(2048) not null -- where to locate the data
);

-- type of policy
create table policy_types
(
    policy_type_id bigint primary key generated always as identity,
    resource_name  varchar(2048) not null unique,
    display_name   varchar(100)
);

-- zero or more options for each policy type
create table policy_type_options
(
    policy_type_option_id bigint primary key generated always as identity,
    policy_type_id        bigint       not null references policy_types (policy_type_id),
    display_name          varchar(100) not null
);

create table hotel_policies
(
    hotel_policy_id bigint primary key generated always as identity,
    hotel_id        bigint not null references hotels (hotel_id),
    policy_type_id  bigint not null references policy_types (policy_type_id),
    description     text
);