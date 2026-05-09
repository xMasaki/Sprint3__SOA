package br.com.careplus.mev.application.core.validation.substancias;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInSubstancias;
import br.com.careplus.mev.application.core.domain.model.CheckInSubstancias;
import br.com.careplus.mev.application.core.validation.interfaces.ValidacoesDeCheckInSubstancias;
import br.com.careplus.mev.application.port.out.CheckInSubstanciasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ValidadorRecaida implements ValidacoesDeCheckInSubstancias {

    @Autowired
    private CheckInSubstanciasRepository checkInSubstanciasRepository;

    @Override
    public Optional<String> validar(DadosCheckInSubstancias dados) {
        boolean temConsumoAtual = dados.doses() > 0 || dados.cigarros() > 0;
        if (!temConsumoAtual) {
            return Optional.empty();
        }

        List<CheckInSubstancias> ultimos7 = checkInSubstanciasRepository
                .findTop7ByPacienteIdOrderByDataRegistroDesc(dados.idPaciente());

        if (ultimos7.size() < 3) {
            return Optional.empty();
        }

        int diasAbstinencia = 0;
        for (CheckInSubstancias c : ultimos7) {
            if (c.getDoses() == 0 && c.getCigarros() == 0) {
                diasAbstinencia++;
            } else {
                break;
            }
        }

        if (diasAbstinencia >= 3) {
            return Optional.of(
                    "Possível recaída detectada após período de abstinência.");
        }
        return Optional.empty();
    }
}
