package br.com.careplus.mev.application.core.validation.social;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInSocial;
import br.com.careplus.mev.application.core.domain.model.CheckInSocial;
import br.com.careplus.mev.application.core.validation.interfaces.ValidacoesDeCheckInSocial;
import br.com.careplus.mev.application.port.out.CheckInSocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ValidadorFrequenciaInteracao implements ValidacoesDeCheckInSocial {

    @Autowired
    private CheckInSocialRepository checkInSocialRepository;

    @Override
    public Optional<String> validar(DadosCheckInSocial dados) {
        List<CheckInSocial> ultimos7 = checkInSocialRepository
                .findTop7ByPacienteIdOrderByDataRegistroDesc(dados.idPaciente());

        if (ultimos7.isEmpty()) {
            return Optional.empty();
        }

        long positivas = ultimos7.stream()
                .filter(CheckInSocial::getInteracaoPositiva)
                .count();

        double percentual = (double) positivas / ultimos7.size() * 100;

        if (percentual < 30.0) {
            return Optional.of(
                    "Frequência de interação social significativa abaixo do recomendado.");
        }
        return Optional.empty();
    }
}
