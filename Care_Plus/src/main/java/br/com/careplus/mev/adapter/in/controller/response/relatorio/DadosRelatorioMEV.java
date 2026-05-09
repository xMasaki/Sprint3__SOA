package br.com.careplus.mev.adapter.in.controller.response.relatorio;

public record DadosRelatorioMEV(
        Long idPaciente,
        String nomePaciente,
        String periodoInicio,
        String periodoFim,
        RelatorioSono sono,
        RelatorioExercicio exercicio,
        RelatorioAlimentacao alimentacao,
        RelatorioEstresse estresse,
        RelatorioSocial social,
        RelatorioSubstancias substancias
) {
}
