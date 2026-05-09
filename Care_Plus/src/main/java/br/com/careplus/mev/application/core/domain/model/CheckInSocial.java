package br.com.careplus.mev.application.core.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "checkins_social")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CheckInSocial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paciente_id")
    private Long pacienteId;

    @Column(name = "data_registro")
    private LocalDateTime dataRegistro;

    @Column(name = "interacao_positiva")
    private Boolean interacaoPositiva;

    public CheckInSocial(
            Long pacienteId,
            LocalDateTime dataRegistro,
            Boolean interacaoPositiva
    ) {
        this.pacienteId = pacienteId;
        this.dataRegistro = dataRegistro;
        this.interacaoPositiva = interacaoPositiva;
    }
}
