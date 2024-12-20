package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.agenda.DadosCadastroAgendamentoDTO;
import med.voll.api.dto.agenda.DadosDetalhamentoAgendamentoDTO;
import med.voll.api.entity.Agenda;
import med.voll.api.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("agendas")
public class AgendaController {

    @Autowired private AgendaService agendaService;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoAgendamentoDTO> cadastrarAgendamento(
            @RequestBody @Valid DadosCadastroAgendamentoDTO dados) {
        Agenda agenda = agendaService.cadastrarAgendamento(dados);

        var uri =
                UriComponentsBuilder.fromPath("/agendas/{id}")
                        .buildAndExpand(agenda.getId())
                        .toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoAgendamentoDTO(agenda));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agenda> buscarAgendamentoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(agendaService.buscarAgendamentoPorId(id));
    }

    @PutMapping
    public ResponseEntity<Agenda> atualizarAgendamento(@RequestBody Agenda agenda) {
        agendaService.atualizarAgendamento(agenda);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAgendamento(@PathVariable Long id) {
        agendaService.deletarAgendamento(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/detalhes")
    public ResponseEntity<Agenda> detalharAgendamento(@PathVariable Long id) {
        return ResponseEntity.ok(agendaService.detalharAgendamento(id));
    }
}
