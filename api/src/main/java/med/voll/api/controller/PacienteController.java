package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.PaginatedResponse;
import med.voll.api.dto.paciente.DadosAtualizacaoPacienteDTO;
import med.voll.api.dto.paciente.DadosCadastroPacienteDTO;
import med.voll.api.dto.paciente.DadosListagemPacienteDTO;
import med.voll.api.entity.Paciente;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public void cadastrarPaciente(@RequestBody @Valid DadosCadastroPacienteDTO dados) {
        pacienteRepository.save(new Paciente(dados));
    }

    @GetMapping
    public PaginatedResponse<DadosListagemPacienteDTO> listarPaciente(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable paginacao) {
        Page<DadosListagemPacienteDTO> page = pacienteRepository.findAllByStatusTrue(paginacao).map(DadosListagemPacienteDTO::new);
        return new PaginatedResponse<>(page);
    }

    @PutMapping
    @Transactional
    public void atualizarPaciente(@RequestBody @Valid DadosAtualizacaoPacienteDTO dadosAtualizacaoPacienteDTO) {
        Paciente paciente = pacienteRepository.getReferenceById(dadosAtualizacaoPacienteDTO.id());
        paciente.atualizarPaciente(dadosAtualizacaoPacienteDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletarPaciente(@PathVariable @Valid @NotNull Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.deletarPaciente();
    }
}