package br.com.careplus.mev.application.core.domain.model;

import br.com.careplus.mev.application.core.domain.enums.NivelEstresse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "checkins_estresse")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CheckInEstresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paciente_id")
    private Long pacienteId;

    @Column(name = "data_registro")
    private LocalDateTime dataRegistro;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_estresse")
    private NivelEstresse nivelEstresse;

    public CheckInEstresse(
            Long pacienteId,
            LocalDateTime dataRegistro,
            NivelEstresse nivelEstresse
    ) {
        this.pacienteId = pacienteId;
        this.dataRegistro = dataRegistro;
        this.nivelEstresse = nivelEstresse;
    }
}
