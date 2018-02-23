CREATE TABLE EMPLOYEE(
"USERNAME" VARCHAR2(100 BYTE), 
"PASSWORD" VARCHAR2(100 BYTE), 
"TYPE" NUMBER(6,0), 
"REPORTSTO" VARCHAR2(100 BYTE),
 
 CONSTRAINT "EMP_PKEY" PRIMARY KEY ("USERNAME")
 CONSTRAINT "NUM_RANGE" CHECK (type < 4 OR type >= 0),
 CONSTRAINT "REPORTS_FK" FOREIGN KEY ("REPORTSTO")
 REFERENCES "PROJ1"."EMPLOYEE" ("USERNAME") ON DELETE CASCADE ENABLE
);

CREATE OR REPLACE TRIGGER CREATERECORDTOYEAR
AFTER INSERT ON EMPLOYEE
FOR EACH ROW
DECLARE
    toyearusr EMPLOYEE.USERNAME%TYPE;
    emptype EMPLOYEE.TYPE%TYPE;
BEGIN
    IF :NEW.type = 0
    THEN
    toyearusr := :NEW.username;
    INSERT INTO TOYEAR (username)
    VALUES (toyearusr);
    END IF;
END;
