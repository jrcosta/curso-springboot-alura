package med.voll.api.service;

import jakarta.transaction.Transactional;
import med.voll.api.dto.agenda.DadosCadastroAgendamentoDTO;
import med.voll.api.entity.Agenda;
import med.voll.api.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    @Transactional
    public void cadastrarAgendamento(DadosCadastroAgendamentoDTO dados) {
        agendaRepository.save(new Agenda(dados));
    }
}
