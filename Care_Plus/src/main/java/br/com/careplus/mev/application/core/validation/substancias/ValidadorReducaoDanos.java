package br.com.careplus.mev.application.core.validation.substancias;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInSubstancias;
import br.com.careplus.mev.application.core.domain.model.CheckInSubstancias;
import br.com.careplus.mev.application.core.validation.interfaces.ValidacoesDeCheckInSubstancias;
import br.com.careplus.mev.application.port.out.CheckInSubstanciasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ValidadorReducaoDanos implements ValidacoesDeCheckInSubstancias {

    @Autowired
    private CheckInSubstanciasRepository checkInSubstanciasRepository;

    @Override
    public Optional<String> validar(DadosCheckInSubstancias dados) {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime inicio14 = agora.minusDays(28);
        LocalDateTime fim14 = agora.minusDays(14);

        List<CheckInSubstancias> periodo14Anterior = checkInSubstanciasRepository
                .findByPacienteIdAndDataRegistroBetween(dados.idPaciente(), inicio14, fim14);
        List<CheckInSubstancias> periodo14Recente = checkInSubstanciasRepository
                .findByPacienteIdAndDataRegistroBetween(dados.idPaciente(), fim14, agora);

        if (periodo14Anterior.isEmpty() || periodo14Recente.isEmpty()) {
            return Optional.empty();
        }

        double mediasDosesAnterior = periodo14Anterior.stream()
                .mapToDouble(CheckInSubstancias::getDoses).average().orElse(0.0);
        double mediaDosesRecente = periodo14Recente.stream()
                .mapToDouble(CheckInSubstancias::getDoses).average().orElse(0.0);

        double mediaCigarrosAnterior = periodo14Anterior.stream()
                .mapToDouble(CheckInSubstancias::getCigarros).average().orElse(0.0);
        double mediaCigarrosRecente = periodo14Recente.stream()
                .mapToDouble(CheckInSubstancias::getCigarros).average().orElse(0.0);

        if (mediasDosesAnterior > 0 && mediaCigarrosAnterior > 0) {
            double reducaoDoses = (mediasDosesAnterior - mediaDosesRecente) / mediasDosesAnterior;
            double reducaoCigarros = (mediaCigarrosAnterior - mediaCigarrosRecente) / mediaCigarrosAnterior;

            if (reducaoDoses >= 0.30 && reducaoCigarros >= 0.30) {
                return Optional.of(
                        "Redução significativa no consumo de substâncias. Excelente progresso!");
            }
        }
        return Optional.empty();
    }
}
