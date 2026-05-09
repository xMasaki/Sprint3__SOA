package br.com.careplus.mev.application.core.usecase;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInEstresse;
import br.com.careplus.mev.adapter.in.controller.response.checkIn.DadosDetalhamentoCheckInEstresse;
import br.com.careplus.mev.application.core.domain.model.CheckInEstresse;
import br.com.careplus.mev.application.core.validation.interfaces.ValidacoesDeCheckInEstresse;
import br.com.careplus.mev.application.port.out.CheckInEstresseRepository;
import br.com.careplus.mev.application.port.out.PacienteRepository;
import br.com.careplus.mev.exception.type.PacienteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistroCheckInEstresseService {

    @Autowired
    private CheckInEstresseRepository checkInEstresseRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidacoesDeCheckInEstresse> validadores;

    public DadosDetalhamentoCheckInEstresse registrar(DadosCheckInEstresse dados) {
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

        var checkIn = new CheckInEstresse(
                dados.idPaciente(),
                dados.dataRegistro(),
                dados.nivelEstresse()
        );
        checkInEstresseRepository.save(checkIn);

        return new DadosDetalhamentoCheckInEstresse(
                checkIn.getId(),
                checkIn.getPacienteId(),
                checkIn.getDataRegistro(),
                checkIn.getNivelEstresse(),
                alertas
        );
    }
}
