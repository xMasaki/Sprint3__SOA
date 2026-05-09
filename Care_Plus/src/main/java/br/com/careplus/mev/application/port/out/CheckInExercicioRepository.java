package br.com.careplus.mev.application.port.out;

import br.com.careplus.mev.application.core.domain.model.CheckInExercicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CheckInExercicioRepository extends JpaRepository<CheckInExercicio, Long> {
    List<CheckInExercicio> findTop7ByPacienteIdOrderByDataRegistroDesc(Long pacienteId);

    List<CheckInExercicio> findByPacienteIdAndDataRegistroBetween(
            Long pacienteId,
            LocalDateTime inicio,
            LocalDateTime fim
    );
}
