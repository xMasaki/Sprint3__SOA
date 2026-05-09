package br.com.careplus.mev.application.core.usecase;

import br.com.careplus.mev.adapter.in.controller.response.checkIn.*;
import br.com.careplus.mev.adapter.in.controller.response.relatorio.*;
import br.com.careplus.mev.application.core.domain.enums.NivelEstresse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    @Autowired
    private HistoricoService historicoService;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public br.com.careplus.mev.adapter.in.controller.response.relatorio.DadosRelatorioMEV gerarRelatorio(
            Long idPaciente, LocalDate dataInicio, LocalDate dataFim) {

        var historico = historicoService.buscarHistorico(idPaciente, dataInicio, dataFim);

        LocalDate inicio = dataInicio != null ? dataInicio : LocalDate.now().minusDays(30);
        LocalDate fim = dataFim != null ? dataFim : LocalDate.now();

        return new DadosRelatorioMEV(
                historico.idPaciente(),
                historico.nomePaciente(),
                inicio.format(FORMATTER),
                fim.format(FORMATTER),
                calcularRelatorioSono(historico.sono()),
                calcularRelatorioExercicio(historico.exercicio()),
                calcularRelatorioAlimentacao(historico.alimentacao()),
                calcularRelatorioEstresse(historico.estresse()),
                calcularRelatorioSocial(historico.social()),
                calcularRelatorioSubstancias(historico.substancias())
        );
    }

    private RelatorioSono calcularRelatorioSono(List<DadosDetalhamentoCheckInSono> sono) {
        if (sono.isEmpty()) {
            return new RelatorioSono(0.0, 0.0, 0.0, List.of());
        }
        double mediaHoras = sono.stream().mapToDouble(DadosDetalhamentoCheckInSono::horasDormidas).average().orElse(0.0);
        double mediaDespertares = sono.stream().mapToDouble(DadosDetalhamentoCheckInSono::despertares).average().orElse(0.0);
        long qualidadeBoa = sono.stream().filter(c -> c.qualidade() != null &&
                (c.qualidade().name().equals("BOA") || c.qualidade().name().equals("OTIMA"))).count();
        double percentualBoa = (double) qualidadeBoa / sono.size() * 100;
        List<String> alertas = sono.stream()
                .filter(c -> c.alertas() != null)
                .flatMap(c -> Arrays.stream(c.alertas().split(" \\| ")))
                .distinct().toList();
        return new RelatorioSono(mediaHoras, percentualBoa, mediaDespertares, alertas);
    }

    private RelatorioExercicio calcularRelatorioExercicio(List<DadosDetalhamentoCheckInExercicio> exercicio) {
        if (exercicio.isEmpty()) {
            return new RelatorioExercicio(0.0, 0.0, "N/A", List.of());
        }
        double totalSessoes = exercicio.stream().mapToDouble(DadosDetalhamentoCheckInExercicio::sessoes).sum();
        double duracaoTotal = exercicio.stream().mapToDouble(DadosDetalhamentoCheckInExercicio::duracaoTotal).sum();
        String intensidadePredominante = exercicio.stream()
                .filter(c -> c.intensidade() != null)
                .collect(Collectors.groupingBy(DadosDetalhamentoCheckInExercicio::intensidade, Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue())
                .map(e -> e.getKey().name()).orElse("N/A");
        List<String> alertas = exercicio.stream()
                .filter(c -> c.alertas() != null)
                .flatMap(c -> Arrays.stream(c.alertas().split(" \\| ")))
                .distinct().toList();
        return new RelatorioExercicio(totalSessoes, duracaoTotal, intensidadePredominante, alertas);
    }

    private RelatorioAlimentacao calcularRelatorioAlimentacao(List<DadosDetalhamentoCheckInAlimentacao> alimentacao) {
        if (alimentacao.isEmpty()) {
            return new RelatorioAlimentacao(0.0, 0.0, "N/A", "N/A", List.of());
        }
        double mediaRefeicoes = alimentacao.stream().mapToDouble(DadosDetalhamentoCheckInAlimentacao::refeicoes).average().orElse(0.0);
        double mediaHidratacao = alimentacao.stream().mapToDouble(DadosDetalhamentoCheckInAlimentacao::hidratacao).average().orElse(0.0);
        String saciedadePredominante = alimentacao.stream()
                .filter(c -> c.saciedade() != null)
                .collect(Collectors.groupingBy(DadosDetalhamentoCheckInAlimentacao::saciedade, Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue())
                .map(e -> e.getKey().name()).orElse("N/A");
        String acucarPredominante = alimentacao.stream()
                .filter(c -> c.nivelAcucar() != null)
                .collect(Collectors.groupingBy(DadosDetalhamentoCheckInAlimentacao::nivelAcucar, Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue())
                .map(e -> e.getKey().name()).orElse("N/A");
        List<String> alertas = alimentacao.stream()
                .filter(c -> c.alertas() != null)
                .flatMap(c -> Arrays.stream(c.alertas().split(" \\| ")))
                .distinct().toList();
        return new RelatorioAlimentacao(mediaRefeicoes, mediaHidratacao, saciedadePredominante, acucarPredominante, alertas);
    }

    private RelatorioEstresse calcularRelatorioEstresse(List<DadosDetalhamentoCheckInEstresse> estresse) {
        if (estresse.isEmpty()) {
            return new RelatorioEstresse("N/A", 0L, List.of());
        }
        String nivelPredominante = estresse.stream()
                .filter(c -> c.nivelEstresse() != null)
                .collect(Collectors.groupingBy(DadosDetalhamentoCheckInEstresse::nivelEstresse, Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue())
                .map(e -> e.getKey().name()).orElse("N/A");
        long diasCritico = estresse.stream()
                .filter(c -> c.nivelEstresse() == NivelEstresse.CRITICO).count();
        List<String> alertas = estresse.stream()
                .filter(c -> c.alertas() != null)
                .flatMap(c -> Arrays.stream(c.alertas().split(" \\| ")))
                .distinct().toList();
        return new RelatorioEstresse(nivelPredominante, diasCritico, alertas);
    }

    private RelatorioSocial calcularRelatorioSocial(List<DadosDetalhamentoCheckInSocial> social) {
        if (social.isEmpty()) {
            return new RelatorioSocial(0L, 0L, 0.0, List.of());
        }
        long positivas = social.stream().filter(c -> Boolean.TRUE.equals(c.interacaoPositiva())).count();
        long total = social.size();
        double percentual = (double) positivas / total * 100;
        List<String> alertas = social.stream()
                .filter(c -> c.alertas() != null)
                .flatMap(c -> Arrays.stream(c.alertas().split(" \\| ")))
                .distinct().toList();
        return new RelatorioSocial(positivas, total, percentual, alertas);
    }

    private RelatorioSubstancias calcularRelatorioSubstancias(List<DadosDetalhamentoCheckInSubstancias> substancias) {
        if (substancias.isEmpty()) {
            return new RelatorioSubstancias(0.0, 0L, 0L, List.of());
        }
        double totalDoses = substancias.stream().mapToDouble(DadosDetalhamentoCheckInSubstancias::doses).sum();
        long totalCigarros = (long) substancias.stream().mapToDouble(DadosDetalhamentoCheckInSubstancias::cigarros).sum();
        long diasComConsumo = substancias.stream()
                .filter(c -> c.doses() > 0 || c.cigarros() > 0).count();
        List<String> alertas = substancias.stream()
                .filter(c -> c.alertas() != null)
                .flatMap(c -> Arrays.stream(c.alertas().split(" \\| ")))
                .distinct().toList();
        return new RelatorioSubstancias(totalDoses, totalCigarros, diasComConsumo, alertas);
    }
}
