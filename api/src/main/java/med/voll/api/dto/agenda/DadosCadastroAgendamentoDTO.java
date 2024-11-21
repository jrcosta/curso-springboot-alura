package med.voll.api.dto.agenda;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DadosCadastroAgendamentoDTO(
        @NotNull
        Long medico_id,
        @NotNull
        Long paciente_id,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "ddMMyyyyHHmm")
        Date data_agendamento,
        Date data_atendimento,
        Long especialidade_id,
        String observacao
) {
}
