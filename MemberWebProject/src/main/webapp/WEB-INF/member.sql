

  CREATE TABLE "MEMBER" ("ID" VARCHAR2(25 BYTE), "PASS" VARCHAR2(50 BYTE), "NAME" VARCHAR2(30 BYTE), "AGE" NUMBER, "GRADE" NUMBER DEFAULT 1)
REM INSERTING into MEMBER
SET DEFINE OFF;
Insert into MEMBER (ID,PASS,NAME,AGE,GRADE) values ('admin','1234','홍길동',2,0);
Insert into MEMBER (ID,PASS,NAME,AGE,GRADE) values ('A0001','1234','김길동1',251,1);
Insert into MEMBER (ID,PASS,NAME,AGE,GRADE) values ('A0002','1234','홍길동',33,1);
Insert into MEMBER (ID,PASS,NAME,AGE,GRADE) values ('Z1773','123456','강병헌',49,1);
Insert into MEMBER (ID,PASS,NAME,AGE,GRADE) values ('Z6298','123456','김동수',55,1);
Insert into MEMBER (ID,PASS,NAME,AGE,GRADE) values ('Q3297','123456','김시우',62,3);
Insert into MEMBER (ID,PASS,NAME,AGE,GRADE) values ('T4369','123456','김준래',47,1);
Insert into MEMBER (ID,PASS,NAME,AGE,GRADE) values ('Q8675','123456','박셩우',34,3);
Insert into MEMBER (ID,PASS,NAME,AGE,GRADE) values ('A6252','123456','박병상',55,5);
Insert into MEMBER (ID,PASS,NAME,AGE,GRADE) values ('M2454','123456','박정현',27,1);
Insert into MEMBER (ID,PASS,NAME,AGE,GRADE) values ('C3101','123456','서민우',56,1);
Insert into MEMBER (ID,PASS,NAME,AGE,GRADE) values ('P3478','123456','이원구',43,4);
Insert into MEMBER (ID,PASS,NAME,AGE,GRADE) values ('W4354','123456','정성수',50,3);
Insert into MEMBER (ID,PASS,NAME,AGE,GRADE) values ('L4082','123456','정희우',46,4);
Insert into MEMBER (ID,PASS,NAME,AGE,GRADE) values ('B7249','123456','조성수',26,3);
Insert into MEMBER (ID,PASS,NAME,AGE,GRADE) values ('S3150','123456','주지우',31,1);
Insert into MEMBER (ID,PASS,NAME,AGE,GRADE) values ('T6388','123456','최승수',51,2);
Insert into MEMBER (ID,PASS,NAME,AGE,GRADE) values ('S6278','123456','한상오',31,2);
Insert into MEMBER (ID,PASS,NAME,AGE,GRADE) values ('W4392','123456','이지원',39,4);
Insert into MEMBER (ID,PASS,NAME,AGE,GRADE) values ('S2298','123456','김희연',65,3);
Insert into MEMBER (ID,PASS,NAME,AGE,GRADE) values ('F1193','123456','노소정',33,3);
Insert into MEMBER (ID,PASS,NAME,AGE,GRADE) values ('E6882','123456','손지해',28,3);
Insert into MEMBER (ID,PASS,NAME,AGE,GRADE) values ('I5201','123456','정다혜',41,2);
Insert into MEMBER (ID,PASS,NAME,AGE,GRADE) values ('I9870','123456','장세이',23,5);
Insert into MEMBER (ID,PASS,NAME,AGE,GRADE) values ('L3517','123456','차영연',51,2);
Insert into MEMBER (ID,PASS,NAME,AGE,GRADE) values ('O1546','123456','장희은',35,5);
Insert into MEMBER (ID,PASS,NAME,AGE,GRADE) values ('T6396','123456','남혜미',52,2);
Insert into MEMBER (ID,PASS,NAME,AGE,GRADE) values ('K6985','123456','유민주',49,1);
Insert into MEMBER (ID,PASS,NAME,AGE,GRADE) values ('N1462','123456','원소은',51,3);
--------------------------------------------------------
--  DDL for Index SYS_C006988
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C006988" ON "MEMBER" ("ID")
--------------------------------------------------------
--  Constraints for Table MEMBER
--------------------------------------------------------

  ALTER TABLE "MEMBER" ADD PRIMARY KEY ("ID") ENABLE
  ALTER TABLE "MEMBER" MODIFY ("PASS" NOT NULL ENABLE)
