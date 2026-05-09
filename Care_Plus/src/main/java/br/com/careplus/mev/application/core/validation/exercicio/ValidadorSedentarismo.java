package br.com.careplus.mev.application.core.validation.exercicio;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInExercicio;
import br.com.careplus.mev.application.core.domain.model.CheckInExercicio;
import br.com.careplus.mev.application.core.validation.interfaces.ValidacoesDeCheckInExercicio;
import br.com.careplus.mev.application.port.out.CheckInExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ValidadorSedentarismo implements ValidacoesDeCheckInExercicio {

    @Autowired
    private CheckInExercicioRepository checkInExercicioRepository;

    @Override
    public Optional<String> validar(DadosCheckInExercicio dados) {
        List<CheckInExercicio> ultimos7 = checkInExercicioRepository
                .findTop7ByPacienteIdOrderByDataRegistroDesc(dados.idPaciente());

        if (ultimos7.size() < 7) {
            return Optional.empty();
        }

        boolean todosSemExercicio = ultimos7.stream()
                .allMatch(c -> c.getDuracaoTotal() == 0.0);

        if (todosSemExercicio) {
            return Optional.of(
                    "Sedentarismo detectado. Inatividade por 7 dias consecutivos.");
        }
        return Optional.empty();
    }
}
