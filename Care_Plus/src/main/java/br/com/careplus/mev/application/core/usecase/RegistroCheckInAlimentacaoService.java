package br.com.careplus.mev.application.core.usecase;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInAlimentacao;
import br.com.careplus.mev.adapter.in.controller.response.checkIn.DadosDetalhamentoCheckInAlimentacao;
import br.com.careplus.mev.application.core.domain.model.CheckInAlimentacao;
import br.com.careplus.mev.application.core.validation.interfaces.ValidacoesDeCheckInAlimentacao;
import br.com.careplus.mev.application.port.out.CheckInAlimentacaoRepository;
import br.com.careplus.mev.application.port.out.PacienteRepository;
import br.com.careplus.mev.exception.type.PacienteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistroCheckInAlimentacaoService {

    @Autowired
    private CheckInAlimentacaoRepository checkInAlimentacaoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidacoesDeCheckInAlimentacao> validadores;

    public DadosDetalhamentoCheckInAlimentacao registrar(DadosCheckInAlimentacao dados) {
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

        var checkIn = new CheckInAlimentacao(
                dados.idPaciente(),
                dados.dataRegistro(),
                dados.refeicoes(),
                dados.saciedade(),
                dados.hidratacao(),
                dados.nivelAcucar()
        );
        checkInAlimentacaoRepository.save(checkIn);

        return new DadosDetalhamentoCheckInAlimentacao(
                checkIn.getId(),
                checkIn.getPacienteId(),
                checkIn.getDataRegistro(),
                checkIn.getRefeicoes(),
                checkIn.getSaciedade(),
                checkIn.getHidratacao(),
                checkIn.getNivelAcucar(),
                alertas
        );
    }
}
