package br.com.careplus.mev.application.core.domain.model;

import br.com.careplus.mev.application.core.domain.enums.NivelAcucar;
import br.com.careplus.mev.application.core.domain.enums.NivelSaciedade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "checkins_alimentacao")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CheckInAlimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paciente_id")
    private Long pacienteId;

    @Column(name = "data_registro")
    private LocalDateTime dataRegistro;

    private Integer refeicoes;

    @Enumerated(EnumType.STRING)
    private NivelSaciedade saciedade;

    private Double hidratacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_acucar")
    private NivelAcucar nivelAcucar;

    public CheckInAlimentacao(
            Long pacienteId,
            LocalDateTime dataRegistro,
            Integer refeicoes,
            NivelSaciedade saciedade,
            Double hidratacao,
            NivelAcucar nivelAcucar
    ) {
        this.pacienteId = pacienteId;
        this.dataRegistro = dataRegistro;
        this.refeicoes = refeicoes;
        this.saciedade = saciedade;
        this.hidratacao = hidratacao;
        this.nivelAcucar = nivelAcucar;
    }
}
