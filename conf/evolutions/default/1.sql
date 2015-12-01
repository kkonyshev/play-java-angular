# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table sell_point (
  id                        bigint not null,
  name                      varchar(255),
  seller_id                 bigint,
  constraint pk_sell_point primary key (id))
;

create table seller (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_seller primary key (id))
;

create table user_account (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_user_account primary key (email))
;


create table seller_sell_point (
  seller_id                      bigint not null,
  sell_point_id                  bigint not null,
  constraint pk_seller_sell_point primary key (seller_id, sell_point_id))
;
create sequence sell_point_seq;

create sequence seller_seq;

create sequence user_account_seq;

alter table sell_point add constraint fk_sell_point_seller_1 foreign key (seller_id) references seller (id) on delete restrict on update restrict;
create index ix_sell_point_seller_1 on sell_point (seller_id);



alter table seller_sell_point add constraint fk_seller_sell_point_seller_01 foreign key (seller_id) references seller (id) on delete restrict on update restrict;

alter table seller_sell_point add constraint fk_seller_sell_point_sell_poi_02 foreign key (sell_point_id) references sell_point (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists sell_point;

drop table if exists seller;

drop table if exists seller_sell_point;

drop table if exists user_account;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists sell_point_seq;

drop sequence if exists seller_seq;

drop sequence if exists user_account_seq;

