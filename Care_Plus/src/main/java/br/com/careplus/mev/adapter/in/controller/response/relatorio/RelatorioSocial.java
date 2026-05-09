package br.com.careplus.mev.adapter.in.controller.response.relatorio;

import java.util.List;

public record RelatorioSocial(
        Long totalInteracoesPositivas,
        Long totalRegistros,
        Double percentualInteracao,
        List<String> alertas
) {
}
