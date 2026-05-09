package br.com.careplus.mev.application.core.validation.exercicio;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInExercicio;
import br.com.careplus.mev.application.core.domain.enums.IntensidadeExercicio;
import br.com.careplus.mev.application.core.domain.model.CheckInExercicio;
import br.com.careplus.mev.application.core.validation.interfaces.ValidacoesDeCheckInExercicio;
import br.com.careplus.mev.application.port.out.CheckInExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ValidadorConsistenciaExercicio implements ValidacoesDeCheckInExercicio {

    @Autowired
    private CheckInExercicioRepository checkInExercicioRepository;

    @Override
    public Optional<String> validar(DadosCheckInExercicio dados) {
        List<CheckInExercicio> ultimos7 = checkInExercicioRepository
                .findTop7ByPacienteIdOrderByDataRegistroDesc(dados.idPaciente());

        if (ultimos7.size() < 7) {
            return Optional.empty();
        }

        long diasConsistentes = ultimos7.stream()
                .filter(c -> c.getSessoes() >= 1 && c.getIntensidade() != IntensidadeExercicio.LEVE)
                .count();

        if (diasConsistentes >= 5) {
            return Optional.of(
                    "Ótima consistência! Continue mantendo a frequência de exercícios.");
        }
        return Optional.empty();
    }
}
