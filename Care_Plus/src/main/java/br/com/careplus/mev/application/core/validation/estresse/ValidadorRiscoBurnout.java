package br.com.careplus.mev.application.core.validation.estresse;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInEstresse;
import br.com.careplus.mev.application.core.domain.enums.NivelEstresse;
import br.com.careplus.mev.application.core.domain.model.CheckInEstresse;
import br.com.careplus.mev.application.core.domain.model.CheckInExercicio;
import br.com.careplus.mev.application.core.domain.model.CheckInSono;
import br.com.careplus.mev.application.core.validation.interfaces.ValidacoesDeCheckInEstresse;
import br.com.careplus.mev.application.port.out.CheckInEstresseRepository;
import br.com.careplus.mev.application.port.out.CheckInExercicioRepository;
import br.com.careplus.mev.application.port.out.CheckInSonoRepository;
import br.com.careplus.mev.exception.type.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ValidadorRiscoBurnout implements ValidacoesDeCheckInEstresse {

    @Autowired
    private CheckInEstresseRepository checkInEstresseRepository;

    @Autowired
    private CheckInSonoRepository checkInSonoRepository;

    @Autowired
    private CheckInExercicioRepository checkInExercicioRepository;

    @Override
    public Optional<String> validar(DadosCheckInEstresse dados) {
        List<CheckInEstresse> ultimos7Estresse = checkInEstresseRepository
                .findTop7ByPacienteIdOrderByDataRegistroDesc(dados.idPaciente());
        List<CheckInSono> ultimos7Sono = checkInSonoRepository
                .findTop7ByPacienteIdOrderByDataRegistroDesc(dados.idPaciente());
        List<CheckInExercicio> ultimos7Exercicio = checkInExercicioRepository
                .findTop7ByPacienteIdOrderByDataRegistroDesc(dados.idPaciente());

        if (ultimos7Estresse.size() < 7 || ultimos7Sono.size() < 7 || ultimos7Exercicio.size() < 7) {
            return Optional.empty();
        }

        long diasCriticos = ultimos7Estresse.stream()
                .filter(c -> c.getNivelEstresse() == NivelEstresse.CRITICO)
                .count();

        double mediaSono = ultimos7Sono.stream()
                .mapToDouble(CheckInSono::getHorasDormidas)
                .average()
                .orElse(0.0);

        long diasSemExercicio = ultimos7Exercicio.stream()
                .filter(c -> c.getSessoes() == 0)
                .count();

        if (diasCriticos >= 3 && mediaSono < 6.0 && diasSemExercicio >= 5) {
            throw new ValidacaoException(
                    "Risco elevado de Burnout identificado. Intervenção médica urgente necessária.");
        }
        return Optional.empty();
    }
}
