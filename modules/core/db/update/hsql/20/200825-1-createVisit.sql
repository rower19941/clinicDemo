create table CLINIC_VISIT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    PET_ID varchar(36) not null,
    VETERINARIAN_ID varchar(36),
    DATE_ timestamp not null,
    DESCRIPTIONS longvarchar,
    HOURS_SPEND integer,
    AMOUNT decimal(19, 2),
    VISIT_TYPE varchar(50),
    --
    primary key (ID)
);