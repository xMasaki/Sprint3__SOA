package br.com.careplus.mev.application.core.validation.interfaces;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInSubstancias;

import java.util.Optional;

public interface ValidacoesDeCheckInSubstancias {
    Optional<String> validar(DadosCheckInSubstancias dados);
}
