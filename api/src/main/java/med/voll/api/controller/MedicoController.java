package med.voll.api.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.PaginatedResponse;
import med.voll.api.dto.medico.DadosAtualizacaoMedicoDTO;
import med.voll.api.dto.medico.DadosCadastroMedicoDTO;
import med.voll.api.dto.medico.DadosListagemMedicoDTO;
import med.voll.api.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    public void cadastrarMedico(@RequestBody @Valid DadosCadastroMedicoDTO dadosCadastroMedicoDTO) {
        medicoService.cadastrarMedico(dadosCadastroMedicoDTO);
    }

    @GetMapping
    public PaginatedResponse<DadosListagemMedicoDTO> listarMedicos(@PageableDefault(size = 1, sort = {"nome"}) Pageable paginacao) {
        Page<DadosListagemMedicoDTO> page = medicoService.listarMedicos(paginacao);
        return new PaginatedResponse<>(page);
    }

    @PutMapping
    public void atualizarMedico(@RequestBody @Valid DadosAtualizacaoMedicoDTO dadosAtualizacaoMedicoDTO) {
        medicoService.atualizarMedico(dadosAtualizacaoMedicoDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarMedico(@PathVariable @Valid @NotNull Long id) {
        medicoService.deletarMedico(id);
    }

    @GetMapping("/{id}")
    public DadosListagemMedicoDTO buscarMedicoPorId(@PathVariable @Valid @NotNull Long id) {
        return new DadosListagemMedicoDTO(medicoService.buscarMedicoPorId(id));
    }

}
