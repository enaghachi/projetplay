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

create table commentaire (
  id                        bigint not null,
  creation_date             timestamp,
  label                     varchar(255),
  Comm_username             varchar(255),
  Comm_tweetID              bigint,
  constraint pk_commentaire primary key (id))
;

create table image (
  idimage                   integer not null,
  file_path                 varchar(2000),
  constraint pk_image primary key (idimage))
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
  images                    integer,
  constraint uq_utilisateur_1 unique (email),
  constraint pk_utilisateur primary key (username))
;

create sequence abonnement_seq;

create sequence commentaire_seq;

create sequence image_seq;

create sequence tweet_seq;

create sequence utilisateur_seq;

alter table abonnement add constraint fk_abonnement_user_1 foreign key (Proprio_username) references utilisateur (username) on delete restrict on update restrict;
create index ix_abonnement_user_1 on abonnement (Proprio_username);
alter table commentaire add constraint fk_commentaire_user_2 foreign key (Comm_username) references utilisateur (username) on delete restrict on update restrict;
create index ix_commentaire_user_2 on commentaire (Comm_username);
alter table commentaire add constraint fk_commentaire_tweet_3 foreign key (Comm_tweetID) references tweet (id) on delete restrict on update restrict;
create index ix_commentaire_tweet_3 on commentaire (Comm_tweetID);
alter table tweet add constraint fk_tweet_user_4 foreign key (Tweet_userID) references utilisateur (username) on delete restrict on update restrict;
create index ix_tweet_user_4 on tweet (Tweet_userID);
alter table utilisateur add constraint fk_utilisateur_images_5 foreign key (images) references image (idimage) on delete restrict on update restrict;
create index ix_utilisateur_images_5 on utilisateur (images);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists abonnement;

drop table if exists commentaire;

drop table if exists image;

drop table if exists tweet;

drop table if exists utilisateur;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists abonnement_seq;

drop sequence if exists commentaire_seq;

drop sequence if exists image_seq;

drop sequence if exists tweet_seq;

drop sequence if exists utilisateur_seq;

