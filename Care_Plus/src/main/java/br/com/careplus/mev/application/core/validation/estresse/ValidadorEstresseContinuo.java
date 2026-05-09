package br.com.careplus.mev.application.core.validation.estresse;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInEstresse;
import br.com.careplus.mev.application.core.domain.enums.NivelEstresse;
import br.com.careplus.mev.application.core.domain.model.CheckInEstresse;
import br.com.careplus.mev.application.core.validation.interfaces.ValidacoesDeCheckInEstresse;
import br.com.careplus.mev.application.port.out.CheckInEstresseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ValidadorEstresseContinuo implements ValidacoesDeCheckInEstresse {

    @Autowired
    private CheckInEstresseRepository checkInEstresseRepository;

    @Override
    public Optional<String> validar(DadosCheckInEstresse dados) {
        List<CheckInEstresse> ultimos5 = checkInEstresseRepository
                .findTop7ByPacienteIdOrderByDataRegistroDesc(dados.idPaciente())
                .stream().limit(5).toList();

        if (ultimos5.size() < 5) {
            return Optional.empty();
        }

        long altoOuCritico = ultimos5.stream()
                .filter(c -> c.getNivelEstresse() == NivelEstresse.ALTO
                        || c.getNivelEstresse() == NivelEstresse.CRITICO)
                .count();

        if (altoOuCritico >= 4) {
            return Optional.of(
                    "Alto estresse contínuo detectado. Recomenda-se avaliação psicológica.");
        }
        return Optional.empty();
    }
}
