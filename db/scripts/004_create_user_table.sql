CREATE TABLE USER (
    ID VARCHAR2(100) PRIMARY KEY
    , PASSWORD VARCHAR2(25) NOT NULL
    , FIRST_NAME VARCHAR2(35) NOT NULL
    , LAST_NAME VARCHAR2(35) NOT NULL
    , ROLE VARCHAR2(10) NOT NULL
    , CELL_PHONE VARCHAR2(10) NOT NULL
    , GENDER VARCHAR2(6) NULL
    , BIRTH_DAY DATE  NULL
    , BLOOD_GROUP VARCHAR2(7) NOT NULL
    , ANONYMOUS VARCHAR2(5) NOT NULL
    , CITY VARCHAR2(255) NOT NULL
    , STATE VARCHAR2(255) NOT NULL
    , ADDRESS VARCHAR2(1000) NOT NULL
    , DISTANCE VARCHAR2(2) NULL
    , LAST_DONATE_DATE DATE NOT NULL
);

--//@UNDO

DROP TABLE USER;