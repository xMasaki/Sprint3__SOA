package br.com.careplus.mev.adapter.in.controller.response.checkIn;

import br.com.careplus.mev.application.core.domain.enums.NivelEstresse;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DadosDetalhamentoCheckInEstresse(
        Long id,
        Long idPaciente,
        @JsonFormat(pattern = "dd/MM/yyyy - HH:mm") LocalDateTime dataRegistro,
        NivelEstresse nivelEstresse,
        String alertas
) {
}
