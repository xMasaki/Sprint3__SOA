package br.com.careplus.mev.application.core.usecase;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInSubstancias;
import br.com.careplus.mev.adapter.in.controller.response.checkIn.DadosDetalhamentoCheckInSubstancias;
import br.com.careplus.mev.application.core.domain.model.CheckInSubstancias;
import br.com.careplus.mev.application.core.validation.interfaces.ValidacoesDeCheckInSubstancias;
import br.com.careplus.mev.application.port.out.CheckInSubstanciasRepository;
import br.com.careplus.mev.application.port.out.PacienteRepository;
import br.com.careplus.mev.exception.type.PacienteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistroCheckInSubstanciasService {

    @Autowired
    private CheckInSubstanciasRepository checkInSubstanciasRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidacoesDeCheckInSubstancias> validadores;

    public DadosDetalhamentoCheckInSubstancias registrar(DadosCheckInSubstancias dados) {
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

        var checkIn = new CheckInSubstancias(
                dados.idPaciente(),
                dados.dataRegistro(),
                dados.doses(),
                dados.cigarros()
        );
        checkInSubstanciasRepository.save(checkIn);

        return new DadosDetalhamentoCheckInSubstancias(
                checkIn.getId(),
                checkIn.getPacienteId(),
                checkIn.getDataRegistro(),
                checkIn.getDoses(),
                checkIn.getCigarros(),
                alertas
        );
    }
}
