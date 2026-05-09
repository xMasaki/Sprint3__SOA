package br.com.careplus.mev.application.port.out;

import br.com.careplus.mev.application.core.domain.model.CheckInSubstancias;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CheckInSubstanciasRepository extends JpaRepository<CheckInSubstancias, Long> {
    List<CheckInSubstancias> findTop7ByPacienteIdOrderByDataRegistroDesc(Long pacienteId);

    List<CheckInSubstancias> findByPacienteIdAndDataRegistroBetween(
            Long pacienteId,
            LocalDateTime inicio,
            LocalDateTime fim
    );
}
