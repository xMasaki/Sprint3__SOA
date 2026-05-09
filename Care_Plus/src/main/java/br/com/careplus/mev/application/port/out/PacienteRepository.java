package br.com.careplus.mev.application.port.out;

import br.com.careplus.mev.application.core.domain.model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByAtivoTrue(Pageable pageable);
    Page<Paciente> findAllByAtivoTrueAndMedicoId(Long medicoId, Pageable pageable);
    boolean existsByCpf(String cpf);
}
