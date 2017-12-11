
    alter table Book 
        drop constraint FK_2krfr0tkkuxiv7j794sgfv5j1

    alter table Book 
        drop constraint FK_oobhb38ja35ikb5isw4t4nghy

    drop table Book if exists

    drop table Category if exists

    drop table Writer if exists

    drop sequence hibernate_sequence

    create table Book (
        id bigint not null,
        cover varchar(255),
        isRented boolean,
        isbn varchar(255),
        pages integer,
        publisher varchar(255),
        publishing_date varchar(255),
        title varchar(255),
        author_id bigint,
        category_id bigint,
        primary key (id)
    )

    create table Category (
        id bigint not null,
        name varchar(255),
        primary key (id)
    )

    create table Writer (
        id bigint not null,
        address varchar(255),
        firstname varchar(255),
        lastname varchar(255),
        primary key (id)
    )

    alter table Book 
        add constraint FK_2krfr0tkkuxiv7j794sgfv5j1 
        foreign key (author_id) 
        references Writer

    alter table Book 
        add constraint FK_oobhb38ja35ikb5isw4t4nghy 
        foreign key (category_id) 
        references Category

    create sequence hibernate_sequence start with 1
