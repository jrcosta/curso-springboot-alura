package med.voll.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.medico.DadosAtualizacaoMedicoDTO;
import med.voll.api.dto.medico.DadosCadastroMedicoDTO;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded private Endereco endereco;

    private boolean status;

    public Medico(DadosCadastroMedicoDTO dadosCadastroMedicoDTO) {
        this.status = true;
        this.nome = dadosCadastroMedicoDTO.nome();
        this.email = dadosCadastroMedicoDTO.email();
        this.telefone = dadosCadastroMedicoDTO.telefone();
        this.crm = dadosCadastroMedicoDTO.crm();
        this.especialidade = dadosCadastroMedicoDTO.especialidade();
        this.endereco = new Endereco(dadosCadastroMedicoDTO.endereco());
    }

    public void atualizarMedico(DadosAtualizacaoMedicoDTO dadosAtualizacaoMedicoDTO) {
        if (dadosAtualizacaoMedicoDTO.nome() != null) {
            this.nome = dadosAtualizacaoMedicoDTO.nome();
        }
        if (dadosAtualizacaoMedicoDTO.telefone() != null) {
            this.telefone = dadosAtualizacaoMedicoDTO.telefone();
        }
        if (dadosAtualizacaoMedicoDTO.endereco() != null) {
            this.endereco.atualizarEndereco(dadosAtualizacaoMedicoDTO.endereco());
        }
    }

    public void deletarMedico() {
        this.status = false;
    }
}
