package br.com.careplus.mev.application.core.usecase;

import br.com.careplus.mev.adapter.in.controller.response.checkIn.*;
import br.com.careplus.mev.adapter.in.controller.response.historico.DadosHistoricoPaciente;
import br.com.careplus.mev.application.port.out.*;
import br.com.careplus.mev.exception.type.PacienteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoricoService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private CheckInSonoRepository checkInSonoRepository;

    @Autowired
    private CheckInExercicioRepository checkInExercicioRepository;

    @Autowired
    private CheckInAlimentacaoRepository checkInAlimentacaoRepository;

    @Autowired
    private CheckInEstresseRepository checkInEstresseRepository;

    @Autowired
    private CheckInSocialRepository checkInSocialRepository;

    @Autowired
    private CheckInSubstanciasRepository checkInSubstanciasRepository;

    public DadosHistoricoPaciente buscarHistorico(Long idPaciente, LocalDate dataInicio, LocalDate dataFim) {
        var paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new PacienteNotFoundException(idPaciente));

        LocalDateTime inicio = (dataInicio != null ? dataInicio : LocalDate.now().minusDays(30))
                .atStartOfDay();
        LocalDateTime fim = (dataFim != null ? dataFim : LocalDate.now())
                .atTime(23, 59, 59);

        List<DadosDetalhamentoCheckInSono> sono = checkInSonoRepository
                .findByPacienteIdAndDataRegistroBetween(idPaciente, inicio, fim)
                .stream()
                .map(c -> new DadosDetalhamentoCheckInSono(
                        c.getId(), c.getPacienteId(), c.getDataRegistro(),
                        c.getHorasDormidas(), c.getQualidade(), c.getDespertares(), null))
                .toList();

        List<DadosDetalhamentoCheckInExercicio> exercicio = checkInExercicioRepository
                .findByPacienteIdAndDataRegistroBetween(idPaciente, inicio, fim)
                .stream()
                .map(c -> new DadosDetalhamentoCheckInExercicio(
                        c.getId(), c.getPacienteId(), c.getDataRegistro(),
                        c.getSessoes(), c.getIntensidade(), c.getDuracaoTotal(), null))
                .toList();

        List<DadosDetalhamentoCheckInAlimentacao> alimentacao = checkInAlimentacaoRepository
                .findByPacienteIdAndDataRegistroBetween(idPaciente, inicio, fim)
                .stream()
                .map(c -> new DadosDetalhamentoCheckInAlimentacao(
                        c.getId(), c.getPacienteId(), c.getDataRegistro(),
                        c.getRefeicoes(), c.getSaciedade(), c.getHidratacao(), c.getNivelAcucar(), null))
                .toList();

        List<DadosDetalhamentoCheckInEstresse> estresse = checkInEstresseRepository
                .findByPacienteIdAndDataRegistroBetween(idPaciente, inicio, fim)
                .stream()
                .map(c -> new DadosDetalhamentoCheckInEstresse(
                        c.getId(), c.getPacienteId(), c.getDataRegistro(),
                        c.getNivelEstresse(), null))
                .toList();

        List<DadosDetalhamentoCheckInSocial> social = checkInSocialRepository
                .findByPacienteIdAndDataRegistroBetween(idPaciente, inicio, fim)
                .stream()
                .map(c -> new DadosDetalhamentoCheckInSocial(
                        c.getId(), c.getPacienteId(), c.getDataRegistro(),
                        c.getInteracaoPositiva(), null))
                .toList();

        List<DadosDetalhamentoCheckInSubstancias> substancias = checkInSubstanciasRepository
                .findByPacienteIdAndDataRegistroBetween(idPaciente, inicio, fim)
                .stream()
                .map(c -> new DadosDetalhamentoCheckInSubstancias(
                        c.getId(), c.getPacienteId(), c.getDataRegistro(),
                        c.getDoses(), c.getCigarros(), null))
                .toList();

        return new DadosHistoricoPaciente(
                paciente.getId(),
                paciente.getNome(),
                sono, exercicio, alimentacao, estresse, social, substancias
        );
    }
}
