package br.com.careplus.mev.application.core.validation.exercicio;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInExercicio;
import br.com.careplus.mev.application.core.domain.model.CheckInExercicio;
import br.com.careplus.mev.application.core.validation.interfaces.ValidacoesDeCheckInExercicio;
import br.com.careplus.mev.application.port.out.CheckInExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ValidadorInatividadeProlongada implements ValidacoesDeCheckInExercicio {

    @Autowired
    private CheckInExercicioRepository checkInExercicioRepository;

    @Override
    public Optional<String> validar(DadosCheckInExercicio dados) {
        List<CheckInExercicio> historico = checkInExercicioRepository
                .findTop7ByPacienteIdOrderByDataRegistroDesc(dados.idPaciente());

        Optional<CheckInExercicio> ultimoAtivo = historico.stream()
                .filter(c -> c.getSessoes() > 0)
                .findFirst();

        if (ultimoAtivo.isPresent()) {
            LocalDateTime dataUltimoAtivo = ultimoAtivo.get().getDataRegistro();
            if (dataUltimoAtivo.isBefore(LocalDateTime.now().minusDays(3))) {
                return Optional.of(
                        "Inatividade prolongada detectada. Recomenda-se retomar atividade física.");
            }
        } else if (!historico.isEmpty()) {
            return Optional.of(
                    "Inatividade prolongada detectada. Recomenda-se retomar atividade física.");
        }
        return Optional.empty();
    }
}
