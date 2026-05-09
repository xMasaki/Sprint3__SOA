package br.com.careplus.mev.application.port.out;

import br.com.careplus.mev.application.core.domain.model.CheckInSocial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CheckInSocialRepository extends JpaRepository<CheckInSocial, Long> {
    List<CheckInSocial> findTop7ByPacienteIdOrderByDataRegistroDesc(Long pacienteId);

    List<CheckInSocial> findByPacienteIdAndDataRegistroBetween(
            Long pacienteId,
            LocalDateTime inicio,
            LocalDateTime fim
    );
}
