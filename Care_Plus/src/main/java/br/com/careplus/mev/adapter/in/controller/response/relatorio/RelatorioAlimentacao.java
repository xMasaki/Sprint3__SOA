package br.com.careplus.mev.adapter.in.controller.response.relatorio;

import java.util.List;

public record RelatorioAlimentacao(
        Double mediaRefeicoes,
        Double mediaHidratacao,
        String saciedadePredominante,
        String nivelAcucarPredominante,
        List<String> alertas
) {
}
