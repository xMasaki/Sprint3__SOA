package br.com.careplus.mev.adapter.in.controller.response.checkIn;

import br.com.careplus.mev.application.core.domain.enums.IntensidadeExercicio;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DadosDetalhamentoCheckInExercicio(
        Long id,
        Long idPaciente,
        @JsonFormat(pattern = "dd/MM/yyyy - HH:mm") LocalDateTime dataRegistro,
        Integer sessoes,
        IntensidadeExercicio intensidade,
        Double duracaoTotal,
        String alertas
) {
}
