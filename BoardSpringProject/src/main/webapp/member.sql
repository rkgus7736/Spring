drop table grade_list cascade constraint;

--아이디 - 25글자, 암호 - 50글자, 이름 - 10글자, 나이 - 숫자
create table member(
	id varchar2(25 byte) primary key,
	pass varchar2(50 byte) not null,
	name varchar2(30 byte),
	age number
);

alter table member add grade number default 1;

select * from nls_database_parameters where parameter like '%CHARACTERSET%';

select * from member;
delete from member;
insert into member values('A0001', '123456', '홍길동',33,1);

--회원 등급 나타내는 테이블 GRADE_LIST
-- BRONZE - 1, SILVER - 2, GOLD - 3, VIP - 4, VVIP - 5 MASTER -> 0  
create table grade_list(
	grade_no number primary key,
	grade_name varchar2(10 byte)
);
insert into GRADE_LIST values(1, 'BRONZE');
insert into GRADE_LIST values(2, 'SILVER');
insert into GRADE_LIST values(3, 'GOLD');
insert into GRADE_LIST values(4, 'VIP');
insert into GRADE_LIST values(5, 'VVIP');
insert into GRADE_LIST values(0, 'MASTER');

select * from grade_list;

select id, name, pass, age, grade_name from MEMBER, grade_list where grade_no = grade;

select * from qna where writer = 'A0001' order by qno desc;

select * from
(select ceil(rownum / 5) as page, qno, title, content, wdate, writer, status, response
from (select * from qna where writer = 'A0001' order by qno desc)) where page = 1;

select * from QNA;

select * from member;
(select ceil(rownum / 5) as page, qno, title, content, wdate, writer, status, response
from (select * from qna order by status asc, qno desc)) where page = 1;

select * from member;


