package med.voll.api.service;

import jakarta.transaction.Transactional;
import med.voll.api.dto.paciente.DadosAtualizacaoPacienteDTO;
import med.voll.api.dto.paciente.DadosCadastroPacienteDTO;
import med.voll.api.dto.paciente.DadosListagemPacienteDTO;
import med.voll.api.entity.Paciente;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    public Paciente cadastrarPaciente(DadosCadastroPacienteDTO dados) {
        return pacienteRepository.save(new Paciente(dados));
    }

    @Transactional
    public void deletarPaciente(Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.deletarPaciente();
    }

    @Transactional
    public Paciente buscarPacientePorId(Long id) {
        return pacienteRepository.getReferenceById(id);
    }

    @Transactional
    public void atualizarPaciente(DadosAtualizacaoPacienteDTO dadosAtualizacaoPacienteDTO) {
        Paciente paciente = pacienteRepository.getReferenceById(dadosAtualizacaoPacienteDTO.id());
        paciente.atualizarPaciente(dadosAtualizacaoPacienteDTO);
    }

    @Transactional
    public Page<DadosListagemPacienteDTO> listarPacientes(Pageable paginacao) {
        return pacienteRepository.findAllByStatusTrue(paginacao).map(DadosListagemPacienteDTO::new);
    }
}
