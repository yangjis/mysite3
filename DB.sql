CREATE TABLE user(	
    no          NUMBER(10),
    id          varchar2(20),
	name        VARCHAR2(80), 
	password    VARCHAR2(20), 
	gender     	VARCHAR2(10), 
	PRIMARY KEY (no)
);

create table guestbook(
    no         number(10),
    name       varchar2(80),
    password   varchar2(20),
    content    varchar2(2000),
    reg_date   date,
   primary key (no)
);

create table replyboard(
    parentsNo     number(30),
    no          number,
    user_no     number not null,
    title       varchar2(500),
    content     varchar2(4000),
    hit         number,
    reg_date    date,
    group_no    number,
    order_no    number,
    depth       number,
    del         varchar2(3),
    CONSTRAINT c_replyboard_fk foreign key(user_no)
    REFERENCES users(no)
);
create table gallery(
    no          number,
    user_no     number,
    comments    varchar2(1000),
    filePath    varchar2(500),
    orgName     varchar2(200),
    saveName    varchar2(500),
    fileSize    number,
    PRIMARY key (no),
    CONSTRAINT c_user_fk foreign key(user_no)
    REFERENCES users(no)
);

CREATE SEQUENCE seq_gallery_no increment by 1 start with 1;
create SEQUENCE SEQ_GUEST_NO increment by 1 start with 1;
create sequence seq_rboard_id increment by 1 start with 1;
create sequence seq_group_no increment by 1 start with 1;

--drop table replyboard
