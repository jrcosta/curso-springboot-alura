CREATE table agenda(
  id bigint not null auto_increment,
  data_agendamento datetime not null,
  data_atendimento datetime not null,
  observacao varchar(255),
  paciente_id bigint not null,
  medico_id bigint not null,
  status tinyint not null,
  especialidade_id bigint not null,
  primary key(id),
  foreign key(paciente_id) references pacientes(id),
  foreign key(medico_id) references medicos(id)
);