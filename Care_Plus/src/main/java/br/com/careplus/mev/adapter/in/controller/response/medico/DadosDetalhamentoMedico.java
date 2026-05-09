package br.com.careplus.mev.adapter.in.controller.response.medico;

import br.com.careplus.mev.application.core.domain.model.Medico;

public record DadosDetalhamentoMedico(
        Long id,
        String nome,
        String email,
        String crm,
        String telefone
) {
    public DadosDetalhamentoMedico(Medico medico) {
        this(
                medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getTelefone()
        );
    }
}
