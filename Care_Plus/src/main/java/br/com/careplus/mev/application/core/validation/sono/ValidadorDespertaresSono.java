package br.com.careplus.mev.application.core.validation.sono;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInSono;
import br.com.careplus.mev.application.core.validation.interfaces.ValidacoesDeCheckInSono;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidadorDespertaresSono implements ValidacoesDeCheckInSono {

    @Override
    public Optional<String> validar(DadosCheckInSono dados) {
        if (dados.despertares() >= 3) {
            return Optional.of("Múltiplos despertares detectados. Qualidade do sono comprometida.");
        }
        return Optional.empty();
    }
}
