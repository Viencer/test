--------------------------------------------------------
--  DDL for Sequence LAB3_DEPARTMENT_SEQ
--------------------------------------------------------

CREATE SEQUENCE  "LAB3_DEPARTMENT_SEQ"  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE;

--------------------------------------------------------
--  DDL for Sequence LAB3_DIAGNOSIS_SEQ
--------------------------------------------------------

CREATE SEQUENCE  "LAB3_DEPARTMENT_SEQ"  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE;

--------------------------------------------------------
--  DDL for Sequence LAB3_JOB_SEQ
--------------------------------------------------------

CREATE SEQUENCE  "LAB3_JOB_SEQ"  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE;

--------------------------------------------------------
--  DDL for Sequence LAB3_MEDICINE_SEQ
--------------------------------------------------------

CREATE SEQUENCE  "LAB3_MEDICINE_SEQ"  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE;

--------------------------------------------------------
--  DDL for Sequence LAB3_MEDICINE_DIAGNOSIS_SEQ
--------------------------------------------------------

CREATE SEQUENCE  "LAB3_MEDICINE_DIAGNOSIS_SEQ"  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE;

--------------------------------------------------------
--  DDL for Sequence LAB3_PATIENTS_SEQ
--------------------------------------------------------

CREATE SEQUENCE  "LAB3_PATIENTS_SEQ"  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE;

--------------------------------------------------------
--  DDL for Sequence LAB3_PERSONAL_SEQ
--------------------------------------------------------

CREATE SEQUENCE  "LAB3_PERSONAL_SEQ"  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE;

--------------------------------------------------------
--  DDL for Sequence LAB3_TREATMENT_SEQ
--------------------------------------------------------

CREATE SEQUENCE  "LAB3_TREATMENT_SEQ"  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE;

--------------------------------------------------------
--  DDL for Sequence LAB3_ROLE_SEQ
--------------------------------------------------------

CREATE SEQUENCE  "LAB3_ROLE_SEQ"  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE;

--------------------------------------------------------
--  DDL for Table LAB3_DEPARTMENT
--------------------------------------------------------

CREATE TABLE "LAB3_DEPARTMENT"
(	"DEPARTMENT_ID" NUMBER NOT NULL ENABLE,
     "DEPARTMENT_NAME" VARCHAR2(50 BYTE) NOT NULL ENABLE,
     "DEPARTMENT_BOSS_ID" NUMBER NOT NULL ENABLE,
     CONSTRAINT "LAB3_DEPARTMENT_PK" PRIMARY KEY ("DEPARTMENT_ID")
);

--------------------------------------------------------
--  DDL for Table LAB3_PERSONAL
--------------------------------------------------------

CREATE TABLE "LAB3_PERSONAL"
(	"PERSONAL_ID" NUMBER NOT NULL ENABLE,
     "FIRST_NAME" VARCHAR2(50 BYTE) NOT NULL ENABLE,
     "LAST_NAME" VARCHAR2(50 BYTE) NOT NULL ENABLE,
     "BOSS_ID" NUMBER,
     "JOB_ID" NUMBER,
     "EXPERIENCE_YEARS" NUMBER NOT NULL ENABLE,
     "SALARY" NUMBER NOT NULL ENABLE,
     "DEPARTMENT_ID" NUMBER NOT NULL ENABLE,
     "PATIENT_ID" NUMBER,
     CONSTRAINT "LAB3_PERSONAL_PK" PRIMARY KEY ("PERSONAL_ID")
);

--------------------------------------------------------
--  DDL for Table ROLE
--------------------------------------------------------

CREATE TABLE "LAB3_ROLE"
(	"PERSONAL_ID" NUMBER NOT NULL ENABLE,
     "USER_NAME" VARCHAR2(50 BYTE) NOT NULL ENABLE,
     "PASSWORD" VARCHAR2(50 BYTE) NOT NULL ENABLE,
     "ENABLE" NUMBER NOT NULL ENABLE,
     CONSTRAINT "LAB3_ROLE_PK" PRIMARY KEY ("PERSONAL_ID")
);

--------------------------------------------------------
--  DDL for Table LAB3_JOBS
--------------------------------------------------------

CREATE TABLE "LAB3_JOBS"
(	"JOB_ID" NUMBER NOT NULL ENABLE,
     "JOB_NAME" VARCHAR2(50 BYTE) NOT NULL ENABLE,
     "MIN_SALARY" NUMBER NOT NULL ENABLE,
     "MAX_SALARY" NUMBER NOT NULL ENABLE,
     CONSTRAINT "LAB3_JOBS_PK" PRIMARY KEY ("JOB_ID")
);

--------------------------------------------------------
--  DDL for Table LAB3_TREATMENT
--------------------------------------------------------

CREATE TABLE "LAB3_TREATMENT"
(	"TREATMENT_ID" NUMBER,
     "PERSONAL_ID" NUMBER NOT NULL ENABLE,
     "DIAGNOSIS_ID" NUMBER NOT NULL ENABLE,
     "NAME_OF_TREATMENT" VARCHAR2(50 BYTE) NOT NULL ENABLE,
     CONSTRAINT "LAB3_TREATMENT_PK" PRIMARY KEY ("TREATMENT_ID")
);

--------------------------------------------------------
--  DDL for Table LAB3_DIAGNOSIS
--------------------------------------------------------

CREATE TABLE "LAB3_DIAGNOSIS"
(	"DIAGNOSIS_ID" NUMBER NOT NULL ENABLE,
     "DAY_TO_RECOVER" NUMBER NOT NULL ENABLE,
     "DIAGNOSIS_NAME" VARCHAR2(50 BYTE) NOT NULL ENABLE,
     "TREATMENT_ID" NUMBER,
     CONSTRAINT "LAB3_DIAGNOSIS_PK" PRIMARY KEY ("DIAGNOSIS_ID")
);

--------------------------------------------------------
--  DDL for Table LAB3_PATIENT
--------------------------------------------------------

CREATE TABLE "LAB3_PATIENT"
(	"PATIENT_ID" NUMBER NOT NULL ENABLE,
     "FIRST_NAME" VARCHAR2(50 BYTE) NOT NULL ENABLE,
     "LAST_NAME" VARCHAR2(50 BYTE) NOT NULL ENABLE,
     "POSITION" VARCHAR2(50 BYTE) NOT NULL ENABLE,
     "PHONE" VARCHAR2(50 BYTE) NOT NULL ENABLE,
     "ADDRESS" VARCHAR2(50 BYTE) NOT NULL ENABLE,
     "DIAGNOSIS_ID" NUMBER,
     "MEDICINE_ID" NUMBER,
     CONSTRAINT "LAB3_PATIENT_PK" PRIMARY KEY ("PATIENT_ID")
);

--------------------------------------------------------
--  DDL for Table LAB3_MEDICINE
--------------------------------------------------------

CREATE TABLE "LAB3_MEDICINE"
(	"MEDICINE_ID" NUMBER NOT NULL ENABLE,
     "MEDICINE_NAME" VARCHAR2(50 BYTE) NOT NULL ENABLE,
     "ADMISSION_DAYS" NUMBER NOT NULL ENABLE,
     "PROVIDER_NAME" VARCHAR2(50 BYTE) NOT NULL ENABLE,
     CONSTRAINT "LAB3_MEDICINE_PK" PRIMARY KEY ("MEDICINE_ID")
);

--------------------------------------------------------
--  DDL for Index LAB3_DEPARTMENT
--------------------------------------------------------

CREATE UNIQUE INDEX "LAB3_DEPARTMENT_PK" ON "LAB3_DEPARTMENT" ("DEPARTMENT_ID");

--------------------------------------------------------
--  DDL for Index LAB3_PERSONAL
--------------------------------------------------------

CREATE UNIQUE INDEX "LAB3_PERSONAL_PK" ON "LAB3_PERSONAL" ("PERSONAL_ID");

--------------------------------------------------------
--  DDL for Index LAB3_ROLE
--------------------------------------------------------

CREATE UNIQUE INDEX "LAB3_ROLE_PK" ON "LAB3_ROLE" ("PERSONAL_ID");

--------------------------------------------------------
--  DDL for Index LAB3_JOBS
--------------------------------------------------------

CREATE UNIQUE INDEX "LAB3_JOBS_PK" ON "LAB3_JOBS" ("JOB_ID");

--------------------------------------------------------
--  DDL for Index LAB3_TREATMENT
--------------------------------------------------------

CREATE UNIQUE INDEX "LAB3_TREATMENT_PK" ON "LAB3_TREATMENT" ("TREATMENT_ID");

--------------------------------------------------------
--  DDL for Index LAB3_DIAGNOSIS
--------------------------------------------------------

CREATE UNIQUE INDEX "LAB3_DIAGNOSIS_PK" ON "LAB3_DIAGNOSIS" ("DIAGNOSIS_ID");

--------------------------------------------------------
--  DDL for Index LAB3_PATIENT
--------------------------------------------------------

CREATE UNIQUE INDEX "LAB3_PATIENT_PK" ON "LAB3_PATIENT" ("PATIENT_ID");

--------------------------------------------------------
--  DDL for Index LAB3_MEDICINE
--------------------------------------------------------

CREATE UNIQUE INDEX "LAB3_MEDICINE_PK" ON "LAB3_MEDICINE" ("MEDICINE_ID");

--------------------------------------------------------
--  DDL for Index LAB3_DEPARTMENT_PK
--------------------------------------------------------

ALTER TABLE "LAB3_DEPARTMENT" ADD CONSTRAINT "LAB3_DEPARTMENT_FK" FOREIGN KEY ("DEPARTMENT_ID")
    REFERENCES "LAB3_PERSONAL" ("DEPARTMENT_ID") ON DELETE CASCADE ENABLE;

--------------------------------------------------------
--  Ref Constraints for Table LAB3_PERSONAL
--------------------------------------------------------

ALTER TABLE "LAB3_PERSONAL" ADD CONSTRAINT "LAB3_PERSONAL_FK" FOREIGN KEY ("PERSONAL_ID")
    REFERENCES "LAB3_DEPARTMENT" ("DEPARTMENT_BOSS_ID") ON DELETE CASCADE ENABLE;

ALTER TABLE "LAB3_PERSONAL" ADD CONSTRAINT "LAB3_PERSONAL_FK" FOREIGN KEY ("PERSONAL_ID")
    REFERENCES "LAB3_ROLE" ("PERSONAL_ID") ON DELETE CASCADE ENABLE;

ALTER TABLE "LAB3_PERSONAL" ADD CONSTRAINT "LAB3_PERSONAL_FK" FOREIGN KEY ("PERSONAL_ID")
    REFERENCES "LAB3_TREATMENT" ("PERSONAL_ID") ON DELETE CASCADE ENABLE;

--------------------------------------------------------
--  Ref Constraints for Table LAB3_TREATMENT
--------------------------------------------------------

ALTER TABLE "LAB3_TREATMENT" ADD CONSTRAINT "LAB3_TREATMENT_FK" FOREIGN KEY ("TREATMENT_ID")
    REFERENCES "LAB3_DIAGNOSIS" ("TREATMENT_ID") ON DELETE SET NULL ENABLE;

--------------------------------------------------------
--  Ref Constraints for Table LAB3_JOBS
--------------------------------------------------------

ALTER TABLE "LAB3_JOBS" ADD CONSTRAINT "LAB3_JOBS_FK" FOREIGN KEY ("JOB_ID")
    REFERENCES "LAB3_PERSONAL" ("JOB_ID") ON DELETE SET NULL ENABLE;

--------------------------------------------------------
--  Ref Constraints for Table LAB3_DIAGNOSIS
--------------------------------------------------------

ALTER TABLE "LAB3_DIAGNOSIS" ADD CONSTRAINT "LAB3_DIAGNOSIS_FK" FOREIGN KEY ("DIAGNOSIS_ID")
    REFERENCES "LAB3_PATIENT" ("DIAGNOSIS_ID") ON DELETE SET NULL ENABLE;


--------------------------------------------------------
--  Ref Constraints for Table LAB3_PATIENT
--------------------------------------------------------

ALTER TABLE "LAB3_PATIENT" ADD CONSTRAINT "LAB3_PATIENT_FK" FOREIGN KEY ("PATIENT_ID")
    REFERENCES "LAB3_PERSONAL" ("PATIENT_ID") ON DELETE SET NULL ENABLE;

--------------------------------------------------------
--  Ref Constraints for Table LAB3_MEDICINE
--------------------------------------------------------

ALTER TABLE "LAB3_MEDICINE" ADD CONSTRAINT "LAB3_MEDICINE_FK" FOREIGN KEY ("MEDICINE_ID")
    REFERENCES "LAB3_PATIENT" ("MEDICINE_ID") ON DELETE SET NULL ENABLE;

    COMMIT;