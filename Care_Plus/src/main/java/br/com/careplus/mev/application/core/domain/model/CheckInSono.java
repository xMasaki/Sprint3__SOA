package br.com.careplus.mev.application.core.domain.model;

import br.com.careplus.mev.application.core.domain.enums.QualidadeSono;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "checkins_sono")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CheckInSono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paciente_id")
    private Long pacienteId;

    @Column(name = "data_registro")
    private LocalDateTime dataRegistro;

    @Column(name = "horas_dormidas")
    private Double horasDormidas;

    @Enumerated(EnumType.STRING)
    private QualidadeSono qualidade;

    private Integer despertares;

    public CheckInSono(
            Long pacienteId,
            LocalDateTime dataRegistro,
            Double horasDormidas,
            QualidadeSono qualidade,
            Integer despertares
    ) {
        this.pacienteId = pacienteId;
        this.dataRegistro = dataRegistro;
        this.horasDormidas = horasDormidas;
        this.qualidade = qualidade;
        this.despertares = despertares;
    }
}
