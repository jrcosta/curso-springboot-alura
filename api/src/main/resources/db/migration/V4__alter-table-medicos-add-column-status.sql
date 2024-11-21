alter table medicos add column status tinyint;
update medicos set status = 1;
alter table medicos modify column status tinyint not null;