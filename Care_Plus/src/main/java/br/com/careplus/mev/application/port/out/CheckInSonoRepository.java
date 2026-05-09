package br.com.careplus.mev.application.port.out;

import br.com.careplus.mev.application.core.domain.model.CheckInSono;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CheckInSonoRepository extends JpaRepository<CheckInSono, Long> {
    List<CheckInSono> findTop7ByPacienteIdOrderByDataRegistroDesc(Long pacienteId);

    List<CheckInSono> findByPacienteIdAndDataRegistroBetween(
            Long pacienteId,
            LocalDateTime inicio,
            LocalDateTime fim
    );
}
