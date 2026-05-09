package br.com.careplus.mev.application.port.out;

import br.com.careplus.mev.application.core.domain.model.CheckInAlimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CheckInAlimentacaoRepository extends JpaRepository<CheckInAlimentacao, Long> {
    List<CheckInAlimentacao> findTop7ByPacienteIdOrderByDataRegistroDesc(Long pacienteId);

    List<CheckInAlimentacao> findByPacienteIdAndDataRegistroBetween(
            Long pacienteId,
            LocalDateTime inicio,
            LocalDateTime fim
    );
}
