package br.com.careplus.mev.application.core.validation.alimentacao;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInAlimentacao;
import br.com.careplus.mev.application.core.validation.interfaces.ValidacoesDeCheckInAlimentacao;
import br.com.careplus.mev.application.core.validation.interfaces.ValidacoesDeCheckInEstresse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidadorHidratacao implements ValidacoesDeCheckInAlimentacao {

    @Override
    public Optional<String> validar(DadosCheckInAlimentacao dados) {
        if (dados.hidratacao() < 2.0) {
            return Optional.of(
                    "Hidratação insuficiente. Recomendado mínimo de 2 litros por dia.");
        }
        return Optional.empty();
    }
}
