package br.com.careplus.mev.adapter.in.controller.response.checkIn;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DadosDetalhamentoCheckInSocial(
        Long id,
        Long idPaciente,
        @JsonFormat(pattern = "dd/MM/yyyy - HH:mm") LocalDateTime dataRegistro,
        Boolean interacaoPositiva,
        String alertas
) {
}
