package br.com.careplus.mev.application.core.validation.interfaces;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInExercicio;

import java.util.Optional;

public interface ValidacoesDeCheckInExercicio {
    Optional<String> validar(DadosCheckInExercicio dados);
}
