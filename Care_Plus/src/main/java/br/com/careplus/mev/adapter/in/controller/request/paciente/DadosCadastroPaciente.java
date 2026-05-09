package br.com.careplus.mev.adapter.in.controller.request.paciente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record DadosCadastroPaciente(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank @Pattern(regexp = "\\d{9,11}") String cpf,
        @NotNull LocalDate dataNascimento,
        @NotBlank String telefone,
        @NotNull Long medicoId,
        @NotNull Long usuarioId
) {
}
