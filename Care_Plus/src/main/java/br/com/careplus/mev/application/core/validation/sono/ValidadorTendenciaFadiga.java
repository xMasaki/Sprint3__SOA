package br.com.careplus.mev.application.core.validation.sono;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInSono;
import br.com.careplus.mev.application.core.domain.enums.QualidadeSono;
import br.com.careplus.mev.application.core.domain.model.CheckInSono;
import br.com.careplus.mev.application.core.validation.interfaces.ValidacoesDeCheckInSono;
import br.com.careplus.mev.application.port.out.CheckInSonoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ValidadorTendenciaFadiga implements ValidacoesDeCheckInSono {

    @Autowired
    private CheckInSonoRepository checkInSonoRepository;

    @Override
    public Optional<String> validar(DadosCheckInSono dados) {
        List<CheckInSono> ultimos5 = checkInSonoRepository
                .findTop7ByPacienteIdOrderByDataRegistroDesc(dados.idPaciente())
                .stream().limit(5).toList();

        if (ultimos5.size() < 5) {
            return Optional.empty();
        }

        long ruinsOuPessimas = ultimos5.stream()
                .filter(c -> c.getQualidade() == QualidadeSono.RUIM
                        || c.getQualidade() == QualidadeSono.PESSIMO)
                .count();

        if (ruinsOuPessimas >= 4) {
            return Optional.of(
                    "Tendência de fadiga identificada. Avalie causas de baixa qualidade do sono.");
        }
        return Optional.empty();
    }
}
