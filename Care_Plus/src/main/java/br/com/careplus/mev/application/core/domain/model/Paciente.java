package br.com.careplus.mev.application.core.domain.model;

import br.com.careplus.mev.adapter.in.controller.request.paciente.DadosAtualizacaoPaciente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean ativo;
    private String nome;
    private String email;
    private String cpf;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    private String telefone;

    @Column(name = "medico_id")
    private Long medicoId;

    @Column(name = "usuario_id")
    private Long usuarioId;

    public Paciente(String nome, String email, String cpf, LocalDate dataNascimento,
                    String telefone, Long medicoId, Long usuarioId) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.medicoId = medicoId;
        this.usuarioId = usuarioId;
        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAtualizacaoPaciente dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.medicoId() != null) {
            this.medicoId = dados.medicoId();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
