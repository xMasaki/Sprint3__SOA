package br.com.careplus.mev.application.core.domain.model;

import br.com.careplus.mev.application.core.domain.enums.IntensidadeExercicio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "checkins_exercicio")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CheckInExercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paciente_id")
    private Long pacienteId;

    @Column(name = "data_registro")
    private LocalDateTime dataRegistro;

    private Integer sessoes;

    @Enumerated(EnumType.STRING)
    private IntensidadeExercicio intensidade;

    @Column(name = "duracao_total")
    private Double duracaoTotal;

    public CheckInExercicio(
            Long pacienteId,
            LocalDateTime dataRegistro,
            Integer sessoes,
            IntensidadeExercicio intensidade,
            Double duracaoTotal
    ) {
        this.pacienteId = pacienteId;
        this.dataRegistro = dataRegistro;
        this.sessoes = sessoes;
        this.intensidade = intensidade;
        this.duracaoTotal = duracaoTotal;
    }
}
