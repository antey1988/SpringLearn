CREATE TABLE SINGER_AUDIT (
    ID INT NOT NULL AUTO_INCREMENT,
    VERSION INT NOT NULL DEFAULT 0,
    FIRST_NAME VARCHAR(60) NOT NULL,
    LAST_NAME VARCHAR(40) NOT NULL,
    BIRTH_DATE DATE,
    CREATED_BY VARCHAR(20),
    CREATED_DATE TIMESTAMP,
    LAST_MODIFIED_BY VARCHAR(20),
    LAST_MODIFIED_DATE TIMESTAMP,
    UNIQUE UQ_SINGER_AUDIT (FIRST_NAME, LAST_NAME),
    PRIMARY KEY (ID)
);