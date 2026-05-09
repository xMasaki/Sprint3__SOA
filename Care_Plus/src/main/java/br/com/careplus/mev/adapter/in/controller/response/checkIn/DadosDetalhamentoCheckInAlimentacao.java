package br.com.careplus.mev.adapter.in.controller.response.checkIn;

import br.com.careplus.mev.application.core.domain.enums.NivelAcucar;
import br.com.careplus.mev.application.core.domain.enums.NivelSaciedade;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DadosDetalhamentoCheckInAlimentacao(
        Long id,
        Long idPaciente,
        @JsonFormat(pattern = "dd/MM/yyyy - HH:mm") LocalDateTime dataRegistro,
        Integer refeicoes,
        NivelSaciedade saciedade,
        Double hidratacao,
        NivelAcucar nivelAcucar,
        String alertas
) {
}
