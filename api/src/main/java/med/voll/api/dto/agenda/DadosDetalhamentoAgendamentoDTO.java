package med.voll.api.dto.agenda;

import med.voll.api.entity.Agenda;

public record DadosDetalhamentoAgendamentoDTO(
        Long id,
        Long medico_id,
        Long paciente_id,
        String data_agendamento,
        String data_atendimento,
        Long especialidade_id,
        boolean status,
        String observacao) {
    public DadosDetalhamentoAgendamentoDTO(Agenda agenda) {
        this(
                agenda.getId(),
                agenda.getMedico_id(),
                agenda.getPaciente_id(),
                agenda.getData_agendamento() != null ? agenda.getData_agendamento().toString() : "",
                agenda.getData_atendimento() != null ? agenda.getData_atendimento().toString() : "",
                agenda.getEspecialidade_id(),
                agenda.isStatus(),
                agenda.getObservacao());
    }
}
