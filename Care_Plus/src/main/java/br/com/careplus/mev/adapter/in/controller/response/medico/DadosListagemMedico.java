package br.com.careplus.mev.adapter.in.controller.response.medico;

import br.com.careplus.mev.application.core.domain.model.Medico;

public record DadosListagemMedico(
        Long id,
        String nome,
        String crm
) {
    public DadosListagemMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getCrm());
    }
}
