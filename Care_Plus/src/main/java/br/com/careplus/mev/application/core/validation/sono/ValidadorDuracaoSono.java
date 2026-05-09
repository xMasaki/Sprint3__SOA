package br.com.careplus.mev.application.core.validation.sono;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInSono;
import br.com.careplus.mev.application.core.validation.interfaces.ValidacoesDeCheckInSono;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidadorDuracaoSono implements ValidacoesDeCheckInSono {

    @Override
    public Optional<String> validar(DadosCheckInSono dados) {
        if (dados.horasDormidas() < 7.0) {
            return Optional.of("Duração abaixo do ideal. Recomendado entre 7h e 9h.");
        }
        if (dados.horasDormidas() > 9.0) {
            return Optional.of("Excesso de sono. Recomendado entre 7h e 9h.");
        }
        return Optional.empty();
    }
}
