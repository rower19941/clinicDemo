-- begin CLINIC_OWNER
create unique index IDX_CLINIC_OWNER_UNIQ_EMAIL on CLINIC_OWNER (EMAIL)^
-- end CLINIC_OWNER
-- begin CLINIC_VETERINARIAN
alter table CLINIC_VETERINARIAN add constraint FK_CLINIC_VETERINARIAN_ON_USER foreign key (USER_ID) references SEC_USER(ID)^
create index IDX_CLINIC_VETERINARIAN_ON_USER on CLINIC_VETERINARIAN (USER_ID)^
-- end CLINIC_VETERINARIAN
-- begin CLINIC_PET_TYPE
create unique index IDX_CLINIC_PET_TYPE_UNIQ_NAME on CLINIC_PET_TYPE (NAME)^
-- end CLINIC_PET_TYPE
-- begin CLINIC_PET
alter table CLINIC_PET add constraint FK_CLINIC_PET_ON_PET_TYPE foreign key (PET_TYPE_ID) references CLINIC_PET_TYPE(ID)^
alter table CLINIC_PET add constraint FK_CLINIC_PET_ON_OWNER foreign key (OWNER_ID) references CLINIC_OWNER(ID)^
create index IDX_CLINIC_PET_ON_PET_TYPE on CLINIC_PET (PET_TYPE_ID)^
create index IDX_CLINIC_PET_ON_OWNER on CLINIC_PET (OWNER_ID)^
-- end CLINIC_PET
-- begin CLINIC_VISIT
alter table CLINIC_VISIT add constraint FK_CLINIC_VISIT_ON_PET foreign key (PET_ID) references CLINIC_PET(ID)^
alter table CLINIC_VISIT add constraint FK_CLINIC_VISIT_ON_VETERINARIAN foreign key (VETERINARIAN_ID) references CLINIC_VETERINARIAN(ID)^
create index IDX_CLINIC_VISIT_ON_PET on CLINIC_VISIT (PET_ID)^
create index IDX_CLINIC_VISIT_ON_VETERINARIAN on CLINIC_VISIT (VETERINARIAN_ID)^
-- end CLINIC_VISIT
-- begin CLINIC_VISIT_CONSUMABLE_LINK
alter table CLINIC_VISIT_CONSUMABLE_LINK add constraint FK_VISCON_ON_VISIT foreign key (VISIT_ID) references CLINIC_VISIT(ID)^
alter table CLINIC_VISIT_CONSUMABLE_LINK add constraint FK_VISCON_ON_CONSUMABLE foreign key (CONSUMABLE_ID) references CLINIC_CONSUMABLE(ID)^
-- end CLINIC_VISIT_CONSUMABLE_LINK
