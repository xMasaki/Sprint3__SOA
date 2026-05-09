package br.com.careplus.mev.adapter.in.controller.request.medico;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedico(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank @Pattern(regexp = "\\d{5,7}") String crm,
        @NotBlank String telefone,
        @NotNull Long usuarioId
) {
}
