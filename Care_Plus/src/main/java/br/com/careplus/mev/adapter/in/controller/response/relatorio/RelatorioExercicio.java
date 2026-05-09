package br.com.careplus.mev.adapter.in.controller.response.relatorio;

import java.util.List;

public record RelatorioExercicio(
        Double totalSessoes,
        Double duracaoTotalHoras,
        String intensidadePredominante,
        List<String> alertas
) {
}
