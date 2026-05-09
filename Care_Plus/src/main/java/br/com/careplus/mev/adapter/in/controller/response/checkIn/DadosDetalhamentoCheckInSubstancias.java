package br.com.careplus.mev.adapter.in.controller.response.checkIn;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DadosDetalhamentoCheckInSubstancias(
        Long id,
        Long idPaciente,
        @JsonFormat(pattern = "dd/MM/yyyy - HH:mm") LocalDateTime dataRegistro,
        Double doses,
        Integer cigarros,
        String alertas
) {
}
