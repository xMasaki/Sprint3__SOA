package br.com.careplus.mev.application.port.out;

import br.com.careplus.mev.application.core.domain.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable pageable);
    boolean existsByCrm(String crm);
    boolean existsByEmail(String email);
}
