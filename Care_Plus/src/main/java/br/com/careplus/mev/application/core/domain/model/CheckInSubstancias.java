package br.com.careplus.mev.application.core.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "checkins_substancias")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CheckInSubstancias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paciente_id")
    private Long pacienteId;

    @Column(name = "data_registro")
    private LocalDateTime dataRegistro;

    private Double doses;

    private Integer cigarros;

    public CheckInSubstancias(
            Long pacienteId,
            LocalDateTime dataRegistro,
            Double doses,
            Integer cigarros
    ) {
        this.pacienteId = pacienteId;
        this.dataRegistro = dataRegistro;
        this.doses = doses;
        this.cigarros = cigarros;
    }
}
