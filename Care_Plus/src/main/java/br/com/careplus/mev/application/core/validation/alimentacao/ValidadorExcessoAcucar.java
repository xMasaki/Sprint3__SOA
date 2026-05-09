package br.com.careplus.mev.application.core.validation.alimentacao;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInAlimentacao;
import br.com.careplus.mev.application.core.domain.enums.NivelAcucar;
import br.com.careplus.mev.application.core.validation.interfaces.ValidacoesDeCheckInAlimentacao;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidadorExcessoAcucar implements ValidacoesDeCheckInAlimentacao {

    @Override
    public Optional<String> validar(DadosCheckInAlimentacao dados) {
        if (dados.nivelAcucar() == NivelAcucar.ALTO || dados.nivelAcucar() == NivelAcucar.MUITO_ALTO) {
            return Optional.of(
                    "Consumo elevado de açúcar detectado. Risco para saúde metabólica.");
        }
        return Optional.empty();
    }
}
