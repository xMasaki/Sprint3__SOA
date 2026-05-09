package br.com.careplus.mev.adapter.in.controller.response.checkIn;

import br.com.careplus.mev.application.core.domain.enums.QualidadeSono;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DadosDetalhamentoCheckInSono(
        Long id,
        Long idPaciente,
        @JsonFormat(pattern = "dd/MM/yyyy - HH:mm") LocalDateTime dataRegistro,
        Double horasDormidas,
        QualidadeSono qualidade,
        Integer despertares,
        String alertas
) {
}
