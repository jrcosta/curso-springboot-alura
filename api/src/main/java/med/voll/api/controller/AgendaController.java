package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.agenda.DadosCadastroAgendamentoDTO;
import med.voll.api.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("agendas")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @PostMapping
    public void cadastrarAgendamento(@RequestBody @Valid DadosCadastroAgendamentoDTO dados) {
        agendaService.cadastrarAgendamento(dados);
    }
}
