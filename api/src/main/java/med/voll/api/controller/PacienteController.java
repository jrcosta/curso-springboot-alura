package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.PaginatedResponse;
import med.voll.api.dto.paciente.DadosAtualizacaoPacienteDTO;
import med.voll.api.dto.paciente.DadosCadastroPacienteDTO;
import med.voll.api.dto.paciente.DadosDetalhamentoPacienteDTO;
import med.voll.api.dto.paciente.DadosListagemPacienteDTO;
import med.voll.api.entity.Paciente;
import med.voll.api.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired private PacienteService pacienteService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPacienteDTO> cadastrarPaciente(
            @RequestBody @Valid DadosCadastroPacienteDTO dados, UriComponentsBuilder uriBuilder) {
        Paciente paciente = pacienteService.cadastrarPaciente(dados);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPacienteDTO(paciente));
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<DadosListagemPacienteDTO>> listarPaciente(
            @PageableDefault(
                            page = 0,
                            size = 10,
                            sort = {"nome"})
                    Pageable paginacao) {
        Page<DadosListagemPacienteDTO> page = pacienteService.listarPacientes(paginacao);
        return ResponseEntity.ok(new PaginatedResponse<>(page));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosAtualizacaoPacienteDTO> atualizarPaciente(
            @RequestBody @Valid DadosAtualizacaoPacienteDTO dadosAtualizacaoPacienteDTO) {
        pacienteService.atualizarPaciente(dadosAtualizacaoPacienteDTO);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarPaciente(@PathVariable @Valid @NotNull Long id) {
        pacienteService.deletarPaciente(id);

        return ResponseEntity.ok().build();
    }
}
