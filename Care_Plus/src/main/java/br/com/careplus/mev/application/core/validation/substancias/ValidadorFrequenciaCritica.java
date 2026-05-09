package br.com.careplus.mev.application.core.validation.substancias;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInSubstancias;
import br.com.careplus.mev.application.core.validation.interfaces.ValidacoesDeCheckInSubstancias;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ValidadorFrequenciaCritica implements ValidacoesDeCheckInSubstancias {

    @Override
    public Optional<String> validar(DadosCheckInSubstancias dados) {
        List<String> alertas = new ArrayList<>();

        if (dados.doses() > 4.0) {
            alertas.add("Consumo de álcool acima do limite seguro recomendado (4 doses/dia).");
        }
        if (dados.cigarros() > 10) {
            alertas.add("Consumo elevado de tabaco. Risco à saúde cardiovascular e respiratória.");
        }

        if (alertas.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(String.join(" | ", alertas));
    }
}
