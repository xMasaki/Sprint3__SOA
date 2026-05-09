package br.com.careplus.mev.adapter.in.controller.response.historico;

import br.com.careplus.mev.adapter.in.controller.response.checkIn.*;

import java.util.List;

public record DadosHistoricoPaciente(
        Long idPaciente,
        String nomePaciente,
        List<DadosDetalhamentoCheckInSono> sono,
        List<DadosDetalhamentoCheckInExercicio> exercicio,
        List<DadosDetalhamentoCheckInAlimentacao> alimentacao,
        List<DadosDetalhamentoCheckInEstresse> estresse,
        List<DadosDetalhamentoCheckInSocial> social,
        List<DadosDetalhamentoCheckInSubstancias> substancias
) {
}
