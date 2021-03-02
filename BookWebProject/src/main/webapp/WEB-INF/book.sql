--------------------------------------------------------
--  ������ ������ - �ݿ���-2��-05-2021   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table BOOK
--------------------------------------------------------

  CREATE TABLE "BOOK" 
   (	"BNO" VARCHAR2(12 BYTE), 
	"TITLE" VARCHAR2(500 BYTE), 
	"WRITER" VARCHAR2(50 BYTE), 
	"PUBLISHER" VARCHAR2(50 BYTE), 
	"WDATE" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
REM INSERTING into BOOK
SET DEFINE OFF;
Insert into BOOK (BNO,TITLE,WRITER,PUBLISHER,WDATE) values ('E5576','�ڹ�','���ط�','����',to_date('02/01/17','RR/MM/DD'));
Insert into BOOK (BNO,TITLE,WRITER,PUBLISHER,WDATE) values ('Q9714','JSP/Servlet','�ڼͿ�','�Ѻ�',to_date('04/12/21','RR/MM/DD'));
Insert into BOOK (BNO,TITLE,WRITER,PUBLISHER,WDATE) values ('J6990','SpringFramework','�ں���','����',to_date('03/04/10','RR/MM/DD'));
Insert into BOOK (BNO,TITLE,WRITER,PUBLISHER,WDATE) values ('E2788','Node.js','������','���ۺ�',to_date('00/04/05','RR/MM/DD'));
Insert into BOOK (BNO,TITLE,WRITER,PUBLISHER,WDATE) values ('G2576','C���','��ÿ�','��Ȱ����',to_date('03/05/17','RR/MM/DD'));
Insert into BOOK (BNO,TITLE,WRITER,PUBLISHER,WDATE) values ('F6092','UI/UX','���ο�','��Ŭ',to_date('03/08/06','RR/MM/DD'));
Insert into BOOK (BNO,TITLE,WRITER,PUBLISHER,WDATE) values ('N7458','mariaDB','�̵���','����',to_date('02/10/17','RR/MM/DD'));
Insert into BOOK (BNO,TITLE,WRITER,PUBLISHER,WDATE) values ('W6375','�ȵ���̵�','�̿���','Ŭ��',to_date('04/09/29','RR/MM/DD'));
--------------------------------------------------------
--  DDL for Index SYS_C007531
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C007531" ON "BOOK" ("BNO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  Constraints for Table BOOK
--------------------------------------------------------

  ALTER TABLE "BOOK" ADD PRIMARY KEY ("BNO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
