--------------------------------------------------------
-- Archivo creado  - sábado-marzo-05-2022   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence ARCHIVO_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PORTAFOLIO"."ARCHIVO_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence TBL_PERFIL_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PORTAFOLIO"."TBL_PERFIL_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence TBL_PORTAFOLIO_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PORTAFOLIO"."TBL_PORTAFOLIO_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence TBL_SKILLSET_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PORTAFOLIO"."TBL_SKILLSET_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence TBL_UBIGEO_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PORTAFOLIO"."TBL_UBIGEO_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence VW_CURRICULUM_VITAE_AUX_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PORTAFOLIO"."VW_CURRICULUM_VITAE_AUX_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table TBL_SKILLSET
--------------------------------------------------------

  CREATE TABLE "PORTAFOLIO"."TBL_SKILLSET" 
   (	"ID_SKILLSET" NUMBER(19,0), 
	"ES_REGISTRO" VARCHAR2(1 CHAR), 
	"FE_ACTUALIZACION" TIMESTAMP (6), 
	"FE_CREACION" TIMESTAMP (6), 
	"NO_IMAGEN_ORIGINAL" VARCHAR2(50 CHAR), 
	"TIPO_IMAGEN" VARCHAR2(15 CHAR), 
	"FOTO_SKILLSET" VARCHAR2(255 CHAR), 
	"IP_ACTUALIZACION" VARCHAR2(9 CHAR), 
	"IP_CREACION" VARCHAR2(9 CHAR), 
	"NO_SKILLSET" VARCHAR2(50 CHAR), 
	"US_ACTUALIZACION" VARCHAR2(10 CHAR), 
	"US_CREACION" VARCHAR2(10 CHAR), 
	"NO_IMAGEN" LONG RAW, 
	"ID_PERFIL" NUMBER(19,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table TBL_PORTAFOLIO
--------------------------------------------------------

  CREATE TABLE "PORTAFOLIO"."TBL_PORTAFOLIO" 
   (	"ID_PORTAFOLIO" NUMBER(19,0), 
	"DE_PORTAFOLIO" VARCHAR2(1024 CHAR), 
	"ES_REGISTRO" VARCHAR2(1 CHAR), 
	"FE_ACTUALIZACION" TIMESTAMP (6), 
	"FE_CREACION" TIMESTAMP (6), 
	"IMG_PORTAFOLIO" VARCHAR2(500 CHAR), 
	"IP_ACTUALIZACION" VARCHAR2(9 CHAR), 
	"IP_CREACION" VARCHAR2(9 CHAR), 
	"NO_PORTAFOLIO" VARCHAR2(100 CHAR), 
	"US_ACTUALIZACION" VARCHAR2(10 CHAR), 
	"US_CREACION" VARCHAR2(10 CHAR), 
	"ID_PERFIL" NUMBER(19,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for View VW_CURRICULUM_VITAE_AUX
--------------------------------------------------------

  CREATE OR REPLACE FORCE VIEW "PORTAFOLIO"."VW_CURRICULUM_VITAE_AUX" ("ID_PERFIL", "NO_PERSONA", "NU_DNI_PERFIL", "TEL_PERFIL", "EMAIL_PERFIL", "DIR_PERFIL", "NO_PORTAFOLIO", "NO_SKILLSET", "TURNO", "SOBRE_MI", "RESUMEN") AS 
  SELECT 
pef.ID_PERFIL, pef.NO_PERFIL || ' ' || pef.AP_PERFIL AS NO_PERSONA
,pef.NU_DNI_PERFIL
,pef.TEL_PERFIL
,pef.EMAIL_PERFIL
,pef.DIR_PERFIL
,FUN_LISTA_NO_PORTAFOLIO(pef.ID_PERFIL) AS NO_PORTAFOLIO
,FUN_LISTA_NO_SKILLSET(pef.ID_PERFIL) AS NO_SKILLSET
,CASE 
    WHEN TO_CHAR(SYSDATE,'HH24:MI') BETWEEN '07:00:00' AND '11:59:59' THEN 'Buenos dias, '
    WHEN TO_CHAR(SYSDATE,'HH24:MI') BETWEEN '12:00:00' AND '18:59:59' THEN 'Buenas tardes, '
    ELSE 'Buenas noches, '
END AS TURNO
,pef.SOBRE_MI
,pef.RESUMEN
--, LISTAGG(por.NO_PORTAFOLIO, '; ') WITHIN GROUP (ORDER BY pef.ID_PERFIL) AS NO_PORTAFOLIO
--, LISTAGG(skl.NO_SKILLSET, '; ') WITHIN GROUP (ORDER BY skl.NO_SKILLSET) AS NO_SKILLSET
FROM TBL_PERFIL pef
--INNER JOIN TBL_PORTAFOLIO por ON (por.ID_PERFIL = pef.ID_PERFIL AND por.ES_REGISTRO = '1')
--INNER JOIN TBL_SKILLSET skl ON (skl.ID_PERFIL = pef.ID_PERFIL AND skl.ES_REGISTRO = '1')
;
REM INSERTING into PORTAFOLIO.TBL_SKILLSET
SET DEFINE OFF;
Insert into PORTAFOLIO.TBL_SKILLSET (ID_SKILLSET,ES_REGISTRO,FE_ACTUALIZACION,FE_CREACION,NO_IMAGEN_ORIGINAL,TIPO_IMAGEN,FOTO_SKILLSET,IP_ACTUALIZACION,IP_CREACION,NO_SKILLSET,US_ACTUALIZACION,US_CREACION,NO_IMAGEN,ID_PERFIL) values ('1','1',null,to_timestamp('05/03/22 07:23:56,220000000 PM','DD/MM/RR HH12:MI:SSXFF AM'),null,null,null,null,'127.0.0.0','SAASASSA',null,'jvalerio',null,'1');
REM INSERTING into PORTAFOLIO.TBL_PORTAFOLIO
SET DEFINE OFF;
Insert into PORTAFOLIO.TBL_PORTAFOLIO (ID_PORTAFOLIO,DE_PORTAFOLIO,ES_REGISTRO,FE_ACTUALIZACION,FE_CREACION,IMG_PORTAFOLIO,IP_ACTUALIZACION,IP_CREACION,NO_PORTAFOLIO,US_ACTUALIZACION,US_CREACION,ID_PERFIL) values ('1','SADSADSAD','1',null,to_timestamp('05/03/22 07:22:43,925000000 PM','DD/MM/RR HH12:MI:SSXFF AM'),null,null,'127.0.0.0','SADSADSAD',null,'jvalerio','1');
Insert into PORTAFOLIO.TBL_PORTAFOLIO (ID_PORTAFOLIO,DE_PORTAFOLIO,ES_REGISTRO,FE_ACTUALIZACION,FE_CREACION,IMG_PORTAFOLIO,IP_ACTUALIZACION,IP_CREACION,NO_PORTAFOLIO,US_ACTUALIZACION,US_CREACION,ID_PERFIL) values ('2','SASASASAS','1',null,to_timestamp('05/03/22 07:26:30,584000000 PM','DD/MM/RR HH12:MI:SSXFF AM'),null,null,'127.0.0.0','ASASASAS',null,'jvalerio','1');
--------------------------------------------------------
--  DDL for Index SYS_C007375
--------------------------------------------------------

  CREATE UNIQUE INDEX "PORTAFOLIO"."SYS_C007375" ON "PORTAFOLIO"."TBL_SKILLSET" ("ID_SKILLSET") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C007371
--------------------------------------------------------

  CREATE UNIQUE INDEX "PORTAFOLIO"."SYS_C007371" ON "PORTAFOLIO"."TBL_PORTAFOLIO" ("ID_PORTAFOLIO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Function CUSTOM_AUTH
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "PORTAFOLIO"."CUSTOM_AUTH" (p_username in VARCHAR2, p_password in VARCHAR2)
return BOOLEAN
is
  l_password varchar2(4000);
  l_stored_password varchar2(4000);
  l_expires_on date;
  l_count number;
begin
-- First, check to see if the user is in the user table
select count(*) into l_count from demo_users where user_name = p_username;
if l_count > 0 then
  -- First, we fetch the stored hashed password & expire date
  select password, expires_on into l_stored_password, l_expires_on
   from demo_users where user_name = p_username;

  -- Next, we check to see if the user's account is expired
  -- If it is, return FALSE
  if l_expires_on > sysdate or l_expires_on is null then

    -- If the account is not expired, we have to apply the custom hash
    -- function to the password
    l_password := custom_hash(p_username, p_password);

    -- Finally, we compare them to see if they are the same and return
    -- either TRUE or FALSE
    if l_password = l_stored_password then
      return true;
    else
      return false;
    end if;
  else
    return false;
  end if;
else
  -- The username provided is not in the DEMO_USERS table
  return false;
end if;
end;

/
--------------------------------------------------------
--  DDL for Function CUSTOM_HASH
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "PORTAFOLIO"."CUSTOM_HASH" (p_username in varchar2, p_password in varchar2)
return varchar2
is
  l_password varchar2(4000);
  l_salt varchar2(4000) := 'MGEETWAAHZVA7FSKXK85ADDTJ0NVMN';
begin

-- This function should be wrapped, as the hash algorhythm is exposed here.
-- You can change the value of l_salt or the method of which to call the
-- DBMS_OBFUSCATOIN toolkit, but you much reset all of your passwords
-- if you choose to do this.

l_password := utl_raw.cast_to_raw(dbms_obfuscation_toolkit.md5
  (input_string => p_password || substr(l_salt,10,13) || p_username ||
    substr(l_salt, 4,10)));
return l_password;
end;

/
--------------------------------------------------------
--  DDL for Function FUN_LISTA_NO_PORTAFOLIO
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "PORTAFOLIO"."FUN_LISTA_NO_PORTAFOLIO" (VL_ID_PERFIL NUMBER)
RETURN VARCHAR2 IS
VL_NO_PORTAFOLIO VARCHAR2(1024);
VL_DSC_NO_PORTAFOLIO VARCHAR2(1024);

CURSOR C1 IS
    SELECT TRIM(por.NO_PORTAFOLIO) AS NO_PORTAFOLIO
    FROM TBL_PORTAFOLIO por 
    WHERE por.ES_REGISTRO='1' 
    AND por.ID_PERFIL = VL_ID_PERFIL
    ;
BEGIN
  OPEN C1;
  LOOP
    FETCH C1
      INTO VL_NO_PORTAFOLIO;
    EXIT WHEN C1%NOTFOUND;

    IF (VL_DSC_NO_PORTAFOLIO IS NULL) THEN
        VL_DSC_NO_PORTAFOLIO := VL_NO_PORTAFOLIO;
    ELSE 
         VL_DSC_NO_PORTAFOLIO := VL_DSC_NO_PORTAFOLIO || ', ' || VL_NO_PORTAFOLIO;
    END IF;
  END LOOP;
  CLOSE C1;
  RETURN VL_DSC_NO_PORTAFOLIO;  
END FUN_LISTA_NO_PORTAFOLIO;

/
--------------------------------------------------------
--  DDL for Function FUN_LISTA_NO_SKILLSET
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "PORTAFOLIO"."FUN_LISTA_NO_SKILLSET" (VL_ID_PERFIL NUMBER)
RETURN VARCHAR2 IS
VL_NO_SKILLSET VARCHAR2(1024);
VL_DSC_NO_SKILLSET VARCHAR2(1024);

CURSOR C1 IS
    SELECT TRIM(skl.NO_SKILLSET) AS NO_SKILLSET
    FROM TBL_SKILLSET skl 
    WHERE skl.ES_REGISTRO='1' 
    AND skl.ID_PERFIL = VL_ID_PERFIL
    ;
BEGIN
  OPEN C1;
  LOOP
    FETCH C1
      INTO VL_NO_SKILLSET;
    EXIT WHEN C1%NOTFOUND;

    IF (VL_DSC_NO_SKILLSET IS NULL) THEN
        VL_DSC_NO_SKILLSET := VL_NO_SKILLSET;
    ELSE 
         VL_DSC_NO_SKILLSET := VL_DSC_NO_SKILLSET || ', ' || VL_NO_SKILLSET;
    END IF;
  END LOOP;
  CLOSE C1;
  RETURN VL_DSC_NO_SKILLSET;  
END FUN_LISTA_NO_SKILLSET;

/
--------------------------------------------------------
--  Constraints for Table TBL_SKILLSET
--------------------------------------------------------

  ALTER TABLE "PORTAFOLIO"."TBL_SKILLSET" ADD PRIMARY KEY ("ID_SKILLSET")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "PORTAFOLIO"."TBL_SKILLSET" MODIFY ("NO_SKILLSET" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."TBL_SKILLSET" MODIFY ("ES_REGISTRO" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."TBL_SKILLSET" MODIFY ("ID_SKILLSET" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table TBL_PORTAFOLIO
--------------------------------------------------------

  ALTER TABLE "PORTAFOLIO"."TBL_PORTAFOLIO" ADD PRIMARY KEY ("ID_PORTAFOLIO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "PORTAFOLIO"."TBL_PORTAFOLIO" MODIFY ("NO_PORTAFOLIO" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."TBL_PORTAFOLIO" MODIFY ("ES_REGISTRO" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."TBL_PORTAFOLIO" MODIFY ("ID_PORTAFOLIO" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table TBL_SKILLSET
--------------------------------------------------------

  ALTER TABLE "PORTAFOLIO"."TBL_SKILLSET" ADD CONSTRAINT "FK989XTH63VTKIEBBBBFA10ONDV" FOREIGN KEY ("ID_PERFIL")
	  REFERENCES "PORTAFOLIO"."TBL_PERFIL" ("ID_PERFIL") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table TBL_PORTAFOLIO
--------------------------------------------------------

  ALTER TABLE "PORTAFOLIO"."TBL_PORTAFOLIO" ADD CONSTRAINT "FKRABE10HMJU45KS9AYMJDA6W5M" FOREIGN KEY ("ID_PERFIL")
	  REFERENCES "PORTAFOLIO"."TBL_PERFIL" ("ID_PERFIL") ENABLE;
