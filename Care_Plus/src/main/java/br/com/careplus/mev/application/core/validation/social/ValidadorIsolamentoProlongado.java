package br.com.careplus.mev.application.core.validation.social;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInSocial;
import br.com.careplus.mev.application.core.domain.model.CheckInSocial;
import br.com.careplus.mev.application.core.validation.interfaces.ValidacoesDeCheckInSocial;
import br.com.careplus.mev.application.port.out.CheckInSocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ValidadorIsolamentoProlongado implements ValidacoesDeCheckInSocial {

    @Autowired
    private CheckInSocialRepository checkInSocialRepository;

    @Override
    public Optional<String> validar(DadosCheckInSocial dados) {
        List<CheckInSocial> ultimos7 = checkInSocialRepository
                .findTop7ByPacienteIdOrderByDataRegistroDesc(dados.idPaciente());

        if (ultimos7.size() < 7) {
            return Optional.empty();
        }

        boolean todosNegativos = ultimos7.stream()
                .allMatch(c -> !c.getInteracaoPositiva());

        if (todosNegativos) {
            return Optional.of(
                    "Isolamento prolongado detectado. Incentive vínculos sociais e suporte comunitário.");
        }
        return Optional.empty();
    }
}
