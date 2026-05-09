package br.com.careplus.mev.application.core.usecase;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInSocial;
import br.com.careplus.mev.adapter.in.controller.response.checkIn.DadosDetalhamentoCheckInSocial;
import br.com.careplus.mev.application.core.domain.model.CheckInSocial;
import br.com.careplus.mev.application.core.validation.interfaces.ValidacoesDeCheckInSocial;
import br.com.careplus.mev.application.port.out.CheckInSocialRepository;
import br.com.careplus.mev.application.port.out.PacienteRepository;
import br.com.careplus.mev.exception.type.PacienteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistroCheckInSocialService {

    @Autowired
    private CheckInSocialRepository checkInSocialRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidacoesDeCheckInSocial> validadores;

    public DadosDetalhamentoCheckInSocial registrar(DadosCheckInSocial dados) {
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

        var checkIn = new CheckInSocial(
                dados.idPaciente(),
                dados.dataRegistro(),
                dados.interacaoPositiva()
        );
        checkInSocialRepository.save(checkIn);

        return new DadosDetalhamentoCheckInSocial(
                checkIn.getId(),
                checkIn.getPacienteId(),
                checkIn.getDataRegistro(),
                checkIn.getInteracaoPositiva(),
                alertas
        );
    }
}
