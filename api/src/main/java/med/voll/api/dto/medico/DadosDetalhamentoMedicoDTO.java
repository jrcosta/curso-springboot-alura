package med.voll.api.dto.medico;

import med.voll.api.entity.Endereco;
import med.voll.api.entity.Especialidade;
import med.voll.api.entity.Medico;

public record DadosDetalhamentoMedicoDTO(Long id, String nome, String email, String telefone, String crm, Especialidade especialidade, Endereco endereco) {
    public DadosDetalhamentoMedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}
