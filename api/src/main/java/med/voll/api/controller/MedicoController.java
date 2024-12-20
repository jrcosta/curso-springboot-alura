package med.voll.api.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.PaginatedResponse;
import med.voll.api.dto.medico.DadosAtualizacaoMedicoDTO;
import med.voll.api.dto.medico.DadosCadastroMedicoDTO;
import med.voll.api.dto.medico.DadosDetalhamentoMedicoDTO;
import med.voll.api.dto.medico.DadosListagemMedicoDTO;
import med.voll.api.entity.Medico;
import med.voll.api.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired private MedicoService medicoService;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoMedicoDTO> cadastrarMedico(
            @RequestBody @Valid DadosCadastroMedicoDTO dadosCadastroMedicoDTO,
            UriComponentsBuilder uriBuilder) {
        Medico medicoCadastrado = medicoService.cadastrarMedico(dadosCadastroMedicoDTO);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medicoCadastrado.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedicoDTO(medicoCadastrado));
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<DadosListagemMedicoDTO>> listarMedicos(
            @PageableDefault(
                            size = 1,
                            sort = {"nome"})
                    Pageable paginacao) {
        Page<DadosListagemMedicoDTO> page = medicoService.listarMedicos(paginacao);
        return ResponseEntity.ok(new PaginatedResponse<>(page));
    }

    @PutMapping
    public ResponseEntity<DadosDetalhamentoMedicoDTO> atualizarMedico(
            @RequestBody @Valid DadosAtualizacaoMedicoDTO dadosAtualizacaoMedicoDTO) {
        Medico medicoAtualizado = medicoService.atualizarMedico(dadosAtualizacaoMedicoDTO);

        return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medicoAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMedico(@PathVariable @Valid @NotNull Long id) {
        medicoService.deletarMedico(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public DadosListagemMedicoDTO buscarMedicoPorId(@PathVariable @Valid @NotNull Long id) {
        return new DadosListagemMedicoDTO(medicoService.buscarMedicoPorId(id));
    }

    @GetMapping("/{id}/detalhes")
    public ResponseEntity<DadosDetalhamentoMedicoDTO> detalharMedico(
            @PathVariable @Valid @NotNull Long id) {
        return ResponseEntity.ok(
                new DadosDetalhamentoMedicoDTO(medicoService.buscarMedicoPorId(id)));
    }
}
