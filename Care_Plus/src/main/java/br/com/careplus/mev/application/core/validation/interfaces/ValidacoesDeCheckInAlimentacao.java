package br.com.careplus.mev.application.core.validation.interfaces;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInAlimentacao;

import java.util.Optional;

public interface ValidacoesDeCheckInAlimentacao {
    Optional<String> validar(DadosCheckInAlimentacao dados);
}
