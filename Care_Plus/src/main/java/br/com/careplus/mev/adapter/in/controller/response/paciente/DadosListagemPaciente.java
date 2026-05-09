package br.com.careplus.mev.adapter.in.controller.response.paciente;

import br.com.careplus.mev.application.core.domain.model.Paciente;

public record DadosListagemPaciente(
        Long id,
        String nome,
        String cpf,
        Long medicoId
) {
    public DadosListagemPaciente(Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getMedicoId()
        );
    }
}
