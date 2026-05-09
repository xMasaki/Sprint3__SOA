package br.com.careplus.mev.application.core.usecase;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInSono;
import br.com.careplus.mev.adapter.in.controller.response.checkIn.DadosDetalhamentoCheckInSono;
import br.com.careplus.mev.application.core.domain.model.CheckInSono;
import br.com.careplus.mev.application.core.validation.interfaces.ValidacoesDeCheckInSono;
import br.com.careplus.mev.application.port.out.CheckInSonoRepository;
import br.com.careplus.mev.application.port.out.PacienteRepository;
import br.com.careplus.mev.exception.type.PacienteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistroCheckInSonoService {

    @Autowired
    private CheckInSonoRepository checkInSonoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidacoesDeCheckInSono> validadores;

    public DadosDetalhamentoCheckInSono registrar(DadosCheckInSono dados) {
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new PacienteNotFoundException(dados.idPaciente());
        }

        String alertas = validadores.stream()
                .map(v -> v.validar(dados))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.collectingAndThen(
                        Collectors.joining(" | "),
                        s -> s.isEmpty() ? null : s
                ));

        var checkIn = new CheckInSono(
                dados.idPaciente(),
                dados.dataRegistro(),
                dados.horasDormidas(),
                dados.qualidade(),
                dados.despertares()
        );
        checkInSonoRepository.save(checkIn);

        return new DadosDetalhamentoCheckInSono(
                checkIn.getId(),
                checkIn.getPacienteId(),
                checkIn.getDataRegistro(),
                checkIn.getHorasDormidas(),
                checkIn.getQualidade(),
                checkIn.getDespertares(),
                alertas
        );
    }
}
