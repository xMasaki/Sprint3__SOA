package br.com.careplus.mev.application.core.validation.interfaces;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInSocial;

import java.util.Optional;

public interface ValidacoesDeCheckInSocial {
    Optional<String> validar(DadosCheckInSocial dados);
}
