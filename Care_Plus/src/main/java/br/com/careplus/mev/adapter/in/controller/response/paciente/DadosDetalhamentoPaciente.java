package br.com.careplus.mev.adapter.in.controller.response.paciente;

import br.com.careplus.mev.application.core.domain.model.Paciente;

import java.time.LocalDate;

public record DadosDetalhamentoPaciente(
        Long id,
        String nome,
        String email,
        String cpf,
        LocalDate dataNascimento,
        String telefone,
        Long medicoId
) {
    public DadosDetalhamentoPaciente(Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getCpf(),
                paciente.getDataNascimento(),
                paciente.getTelefone(),
                paciente.getMedicoId()
        );
    }
}
