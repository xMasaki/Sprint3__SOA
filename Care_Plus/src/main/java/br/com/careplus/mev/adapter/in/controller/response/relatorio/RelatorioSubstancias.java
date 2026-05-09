package br.com.careplus.mev.adapter.in.controller.response.relatorio;

import java.util.List;

public record RelatorioSubstancias(
        Double totalDoses,
        Long totalCigarros,
        Long diasComConsumo,
        List<String> alertas
) {
}
