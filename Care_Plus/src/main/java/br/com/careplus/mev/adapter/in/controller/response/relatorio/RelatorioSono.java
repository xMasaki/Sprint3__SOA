package br.com.careplus.mev.adapter.in.controller.response.relatorio;

import java.util.List;

public record RelatorioSono(
        Double mediaHorasDormidas,
        Double percentualQualidadeBoa,
        Double mediaDespertares,
        List<String> alertas
) {
}
