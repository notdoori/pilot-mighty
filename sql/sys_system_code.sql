--------------------------------------------------------
--  파일이 생성됨 - 금요일-6월-18-2021   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table SYS_SYSTEM_CODE
--------------------------------------------------------

  CREATE TABLE "C##SCOTT"."SYS_SYSTEM_CODE" 
   (	"TABLE_NAME" VARCHAR2(30 BYTE), 
	"TABLE_DESC" VARCHAR2(200 BYTE), 
	"USE_YN" VARCHAR2(1 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into C##SCOTT.SYS_SYSTEM_CODE
SET DEFINE OFF;
Insert into C##SCOTT.SYS_SYSTEM_CODE (TABLE_NAME,TABLE_DESC,USE_YN) values ('MULTI_LANG_CD','다국어 코드','Y');
Insert into C##SCOTT.SYS_SYSTEM_CODE (TABLE_NAME,TABLE_DESC,USE_YN) values ('FLAG_YN','플래그','Y');
Insert into C##SCOTT.SYS_SYSTEM_CODE (TABLE_NAME,TABLE_DESC,USE_YN) values ('USE_YN','사용유무','Y');
Insert into C##SCOTT.SYS_SYSTEM_CODE (TABLE_NAME,TABLE_DESC,USE_YN) values ('DEPARTMENT','부서','Y');
--------------------------------------------------------
--  DDL for Index SYS_C0010410
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##SCOTT"."SYS_C0010410" ON "C##SCOTT"."SYS_SYSTEM_CODE" ("TABLE_NAME") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table SYS_SYSTEM_CODE
--------------------------------------------------------

  ALTER TABLE "C##SCOTT"."SYS_SYSTEM_CODE" MODIFY ("TABLE_NAME" NOT NULL ENABLE);
  ALTER TABLE "C##SCOTT"."SYS_SYSTEM_CODE" ADD PRIMARY KEY ("TABLE_NAME")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
