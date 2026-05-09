package br.com.careplus.mev.application.core.validation.alimentacao;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInAlimentacao;
import br.com.careplus.mev.application.core.validation.interfaces.ValidacoesDeCheckInAlimentacao;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidadorRefeicoesDiarias implements ValidacoesDeCheckInAlimentacao {

    @Override
    public Optional<String> validar(DadosCheckInAlimentacao dados) {
        if (dados.refeicoes() < 3) {
            return Optional.of(
                    "Número de refeições abaixo do recomendado. Ideal: entre 3 e 5 refeições.");
        }
        if (dados.refeicoes() > 6) {
            return Optional.of(
                    "Número elevado de refeições. Avalie padrão alimentar com nutricionista.");
        }
        return Optional.empty();
    }
}
