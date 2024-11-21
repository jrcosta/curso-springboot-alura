package med.voll.api.service;

import jakarta.transaction.Transactional;
import med.voll.api.dto.medico.DadosAtualizacaoMedicoDTO;
import med.voll.api.dto.medico.DadosCadastroMedicoDTO;
import med.voll.api.dto.medico.DadosListagemMedicoDTO;
import med.voll.api.entity.Medico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Transactional
    public void cadastrarMedico(DadosCadastroMedicoDTO dadosCadastroMedicoDTO) {
        medicoRepository.save(new Medico(dadosCadastroMedicoDTO));
    }

    @Transactional
    public void atualizarMedico(DadosAtualizacaoMedicoDTO dadosAtualizacaoMedicoDTO) {
        Medico medico = medicoRepository.getReferenceById(dadosAtualizacaoMedicoDTO.id());
        medico.atualizarMedico(dadosAtualizacaoMedicoDTO);
    }

    @Transactional
    public Page<DadosListagemMedicoDTO> listarMedicos(@PageableDefault Pageable paginacao) {
        return medicoRepository.findAllByStatusTrue(paginacao).map(DadosListagemMedicoDTO::new);
    }

    @Transactional
    public void deletarMedico(Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.deletarMedico();
    }

    @Transactional
    public Medico buscarMedicoPorId(Long id) {
        return medicoRepository.getReferenceById(id);
    }

}
