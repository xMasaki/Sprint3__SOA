package br.com.careplus.mev.application.port.out;

import br.com.careplus.mev.application.core.domain.model.CheckInEstresse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CheckInEstresseRepository extends JpaRepository<CheckInEstresse, Long> {
    List<CheckInEstresse> findTop7ByPacienteIdOrderByDataRegistroDesc(Long pacienteId);

    List<CheckInEstresse> findByPacienteIdAndDataRegistroBetween(
            Long pacienteId,
            LocalDateTime inicio,
            LocalDateTime fim
    );
}
