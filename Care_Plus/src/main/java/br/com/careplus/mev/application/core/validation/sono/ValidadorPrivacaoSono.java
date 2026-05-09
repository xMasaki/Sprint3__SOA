package br.com.careplus.mev.application.core.validation.sono;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInSono;
import br.com.careplus.mev.application.core.domain.model.CheckInSono;
import br.com.careplus.mev.application.core.validation.interfaces.ValidacoesDeCheckInSono;
import br.com.careplus.mev.application.port.out.CheckInSonoRepository;
import br.com.careplus.mev.exception.type.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ValidadorPrivacaoSono implements ValidacoesDeCheckInSono {

    @Autowired
    private CheckInSonoRepository checkInSonoRepository;

    @Override
    public Optional<String> validar(DadosCheckInSono dados) {
        List<CheckInSono> ultimos7 = checkInSonoRepository
                .findTop7ByPacienteIdOrderByDataRegistroDesc(dados.idPaciente());

        if (ultimos7.size() < 7) {
            return Optional.empty();
        }

        double media = ultimos7.stream()
                .mapToDouble(CheckInSono::getHorasDormidas)
                .average()
                .orElse(0.0);

        if (media < 6.0) {
            throw new ValidacaoException(
                    "Privação crônica de sono detectada. Intervenção médica recomendada.");
        }
        return Optional.empty();
    }
}
