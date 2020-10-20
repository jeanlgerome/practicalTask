
create table IF NOT EXISTS citizenship
(
  citizenship_code bigint       not null, -- Код страны
  citizenship_name varchar(255) not null, -- Название страны
  primary key (citizenship_code)
);



create table IF NOT EXISTS doc_concrete
(
  doc_number varchar(255) not null, -- Номер документа. Однозначно задает документ. По номеру определяется тип и дата
  doc_date   date         not null, -- Дата документа
  doc_code   bigint       not null, -- Код документа
  primary key (doc_number)
);


create table IF NOT EXISTS doc_type
(
  doc_code bigint       not null, -- Код документа
  doc_name varchar(255) not null, -- Название докумена = тип документа
  primary key (doc_code)
);

create table IF NOT EXISTS office
(
  id              bigint  not null AUTO_INCREMENT, -- Уникальный идентификатор
  adress          varchar(255),                    -- Адрес
  is_active       boolean not null,                -- Статус активности
  name            varchar(255),                    -- Название
  phone           varchar(255),                    -- Номер
  version         bigint  not null,                -- Служебное поле hibernate
  organization_id bigint  not null,                -- Айди организации
  primary key (id)
);

create table IF NOT EXISTS organization
(
  id        bigint       not null AUTO_INCREMENT, -- Уникальный идентификатор
  adress    varchar(255) not null,                -- Адрес
  full_name varchar(255) not null,                -- Полное название
  inn       varchar(255) not null,                -- Инн
  is_active boolean      not null,                -- Статус активности
  kpp       varchar(255) not null,                -- Кпп
  name      varchar(255) not null,                -- Название
  phone     varchar(255),                         -- Телефон
  version   bigint       not null,                -- Служебное поле hibernate
  primary key (id)
);

create table IF NOT EXISTS usr
(
  id                      bigint       not null AUTO_INCREMENT, -- Уникальный идентификатор
  first_name              varchar(255) not null,                -- Имя
  is_identified           boolean      not null,                -- Статус идентификации
  middle_name             varchar(255),                         -- Второе имя
  phone                   varchar(255),                         -- Телефон
  position                varchar(255) not null,                -- Должность
  second_name             varchar(255),                         -- Фамилия
  version                 bigint       not null,                -- Служебное поле hibernate
  citizenship_code        bigint,                               -- Код гражданства. Однозначно задает название страны
  doc_number varchar(255),                         -- Номер документа. Однозначно задает документ. По номеру определяется тип и дата
  office_id               bigint       not null,                -- Айди офиса
  primary key (id)
);

create index IX_citizenship_citizenshipCode on citizenship (citizenship_code);
create index IX_doc_concrete_docType on doc_concrete (doc_code);
create index IX_doc_type_docCode on doc_type (doc_code);
create index IX_office_organization on office (organization_id);
create index IX_office_name on office (name);
create index IX_office_isActive on office (is_active);
alter table office
  add constraint UX_office_phone unique (phone);
alter table usr
  add constraint IX_usr_docConcrete unique (doc_number);
create index IX_organization_name on organization (name);
create index IX_organization_isActive on organization (is_active);
alter table organization
  add constraint UX_organization_inn unique (inn);
create index IX_usr_docConcrete on usr (doc_number);
create index IX_usr_office on usr (office_id);
create index IX_usr_firstName on usr (first_name);
create index IX_usr_secondName on usr (second_name);
create index IX_usr_middleName on usr (middle_name);
create index IX_usr_position on usr (position);
create index IX_usr_citizenship on usr (citizenship_code);
alter table doc_concrete
  add constraint FKr0cxbah1iu5lyhkntnquq1un0 foreign key (doc_code) references doc_type;
alter table office
  add constraint FKaa75do6pm855upok2c8g2rhbk foreign key (organization_id) references organization;
alter table usr
  add constraint FKfdceh039sg6imvqdv0eo5c5jn foreign key (citizenship_code) references citizenship;
alter table usr
  add constraint FK17kfclc8qt8kn3n4hg8hk58nm foreign key (office_id) references office;
alter table usr
  add constraint FKtotb11qqgqdtc8xja6ftetgss foreign key (doc_number) references doc_concrete;
