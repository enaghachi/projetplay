# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table abonnement (
  id                        bigint not null,
  username_ajout            varchar(255),
  date_ajout                timestamp,
  Proprio_username          varchar(255),
  constraint pk_abonnement primary key (id))
;

create table tweet (
  id                        bigint not null,
  num                       integer,
  creation_date             timestamp,
  label                     varchar(255),
  sujet                     varchar(255),
  taguser                   varchar(255),
  Tweet_userID              varchar(255),
  constraint pk_tweet primary key (id))
;

create table utilisateur (
  username                  varchar(255) not null,
  email                     varchar(255),
  sexe                      varchar(255),
  date_inscription          timestamp,
  password                  varchar(255),
  description               varchar(255),
  constraint uq_utilisateur_1 unique (email),
  constraint pk_utilisateur primary key (username))
;

create sequence abonnement_seq;

create sequence tweet_seq;

create sequence utilisateur_seq;

alter table abonnement add constraint fk_abonnement_user_1 foreign key (Proprio_username) references utilisateur (username) on delete restrict on update restrict;
create index ix_abonnement_user_1 on abonnement (Proprio_username);
alter table tweet add constraint fk_tweet_user_2 foreign key (Tweet_userID) references utilisateur (username) on delete restrict on update restrict;
create index ix_tweet_user_2 on tweet (Tweet_userID);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists abonnement;

drop table if exists tweet;

drop table if exists utilisateur;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists abonnement_seq;

drop sequence if exists tweet_seq;

drop sequence if exists utilisateur_seq;

