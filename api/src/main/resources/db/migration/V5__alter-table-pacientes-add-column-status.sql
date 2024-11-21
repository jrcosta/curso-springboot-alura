alter table pacientes add column status tinyint;
update pacientes set status = 1;
alter table pacientes modify column status tinyint not null;