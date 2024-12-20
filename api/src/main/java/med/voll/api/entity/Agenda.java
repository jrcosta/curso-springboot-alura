package med.voll.api.entity;

import jakarta.persistence.*;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.agenda.DadosCadastroAgendamentoDTO;

@Table(name = "agendas")
@Entity(name = "Agenda")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long medico_id;
    private Long paciente_id;
    private Date data_agendamento;
    private Date data_atendimento;
    private Long especialidade_id;
    private boolean status;
    private String observacao;

    public Agenda(DadosCadastroAgendamentoDTO dados) {
        this.medico_id = dados.medico_id();
        this.paciente_id = dados.paciente_id();
        this.data_agendamento = dados.data_agendamento();
        this.data_atendimento = dados.data_atendimento();
        this.especialidade_id = dados.especialidade_id();
        this.status = true;
        this.observacao = dados.observacao();
    }

    public void deletarAgendamento() {
        this.status = false;
    }
}
