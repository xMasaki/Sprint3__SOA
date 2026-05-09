package br.com.careplus.mev.application.core.domain.model;

import br.com.careplus.mev.adapter.in.controller.request.medico.DadosAtualizacaoMedico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean ativo;
    private String nome;
    private String email;
    private String crm;
    private String telefone;

    @Column(name = "usuario_id")
    private Long usuarioId;

    public Medico(String nome, String email, String crm, String telefone, Long usuarioId) {
        this.nome = nome;
        this.email = email;
        this.crm = crm;
        this.telefone = telefone;
        this.usuarioId = usuarioId;
        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
