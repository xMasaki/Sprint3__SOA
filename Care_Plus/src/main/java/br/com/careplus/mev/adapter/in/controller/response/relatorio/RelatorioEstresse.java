package br.com.careplus.mev.adapter.in.controller.response.relatorio;

import java.util.List;

public record RelatorioEstresse(
        String nivelPredominante,
        Long diasComEstresseCritico,
        List<String> alertas
) {
}
