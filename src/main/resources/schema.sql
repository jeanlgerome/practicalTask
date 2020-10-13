create table IF NOT EXISTS organization
(
  id        int8         not null,          -- Уникальный идентификатор
  version   int8         not null,          -- Служебное поле hibernate
  adress    varchar(255) not null,          -- Адрес
  full_name varchar(255) not null,          -- Полное название
  inn       varchar(255) not null,          -- Инн
  is_active boolean      not null,          -- Статус активности
  kpp       varchar(255) not null,          -- Кпп
  name      varchar(255) not null,          -- Название
  phone     varchar(255),                   -- Телефон
  primary key (id)
);

create table IF NOT EXISTS office
(
  id              int8         not null,    -- Уникальный идентификатор
  version         int8         not null,    -- Служебное поле hibernate
  adress          varchar(255) not null,    -- Адрес
  is_active       boolean      not null,    -- Статус активности
  name            varchar(255) not null,    -- Название
  phone           varchar(255),             -- Номер
  organization_id int8         not null,    -- Айди организации
  primary key (id)
);

create table IF NOT EXISTS usr
(
  id               int8         not null,   -- Уникальный идентификатор
  version          int8         not null,   -- Служебное поле hibernate
  citizenship_code int8,                    -- Код гражданства. Однозначно задает название страны
  doc_number       varchar(255),            -- Номер документа. Однозначно задает документ. По номеру определяется тип и дата
  is_identified    boolean      not null,   -- Статус идентификации
  first_name       varchar(255) not null,   -- Имя
  middle_name      varchar(255),            -- Второе имя
  second_name      varchar(255),            -- Фамилия
  phone            varchar(255),            -- Телефон
  position         varchar(255) not null,   -- Должность
  office_id        int8         not null,   -- Айди офиса
  primary key (id)
);

create table IF NOT EXISTS citizenship
(
  citizenship_code int8 not null,           -- Код гражданства
  citizenship_name varchar(255) not null,   -- Название страны
  primary key (citizenship_code)
);

create table IF NOT EXISTS doc_concrete
(
  doc_number varchar(255) not null,         -- Номер документа. Однозначно задает документ. По номеру определяется тип и дата
  doc_code   int8 not null,                 -- Код документа
  doc_date   date not null,                 -- Дата документа
  primary key (doc_number)
);

create table IF NOT EXISTS doc_type
(
  doc_code int8 not null,                   -- Код документа
  doc_name varchar(255) not null,           -- Название докумена = тип документа
  primary key (doc_code)
);

create index IX_office_organization on office (organization_id);

create index IX_office_name on office (name);

create index IX_office_isActive on office (is_active);

alter table if exists office
  add constraint UX_office_phone unique (phone);

create index IX_organization_name on organization (name);

create index IX_organization_isActive on organization (is_active);

alter table if exists organization
  add constraint UX_organization_inn unique (inn);

create index IX_usr_office on usr (office_id);

create index IX_usr_firstName on usr (first_name);

create index IX_usr_secondName on usr (second_name);

create index IX_usr_middleName on usr (middle_name);

create index IX_usr_position on usr (position);

create index IX_usr_citizenshipCode on usr (citizenship_code);

alter table if exists usr
  add constraint UX_usr_docNumber unique (doc_number);

alter table if exists office
  add constraint FKaa75do6pm855upok2c8g2rhbk foreign key (organization_id) references organization;

alter table if exists usr
  add constraint FK17kfclc8qt8kn3n4hg8hk58nm foreign key (office_id) references office;