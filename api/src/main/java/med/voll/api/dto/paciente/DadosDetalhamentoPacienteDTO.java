package med.voll.api.dto.paciente;

import med.voll.api.entity.Endereco;
import med.voll.api.entity.Paciente;

public record DadosDetalhamentoPacienteDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        boolean status,
        Endereco endereco) {
    public DadosDetalhamentoPacienteDTO(Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getTelefone(),
                paciente.getCpf(),
                paciente.isStatus(),
                paciente.getEndereco());
    }
}
