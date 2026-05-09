package br.com.careplus.mev.adapter.in.controller.request.checkIn;

import br.com.careplus.mev.application.core.domain.enums.NivelAcucar;
import br.com.careplus.mev.application.core.domain.enums.NivelSaciedade;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCheckInAlimentacao(
        @NotNull Long idPaciente,
        @NotNull LocalDateTime dataRegistro,
        @NotNull @Min(0) Integer refeicoes,
        @NotNull NivelSaciedade saciedade,
        @NotNull @DecimalMin("0.0") Double hidratacao,
        @NotNull NivelAcucar nivelAcucar
        ) {
}
