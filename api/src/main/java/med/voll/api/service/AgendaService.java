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
    public Agenda cadastrarAgendamento(DadosCadastroAgendamentoDTO dados) {
        return agendaRepository.save(new Agenda(dados));
    }

    @Transactional
    public void deletarAgendamento(Long id) {
        Agenda agenda = agendaRepository.getReferenceById(id);
        agenda.deletarAgendamento();
    }

    @Transactional
    public Agenda buscarAgendamentoPorId(Long id) {
        return agendaRepository.getReferenceById(id);
    }

    @Transactional
    public void atualizarAgendamento(Agenda agenda) {
        agendaRepository.save(agenda);
    }

    @Transactional
    public Agenda detalharAgendamento(Long id) {
        return agendaRepository.getReferenceById(id);
    }
}
