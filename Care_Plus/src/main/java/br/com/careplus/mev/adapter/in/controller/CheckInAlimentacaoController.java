package br.com.careplus.mev.adapter.in.controller;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInAlimentacao;
import br.com.careplus.mev.adapter.in.controller.response.checkIn.DadosDetalhamentoCheckInAlimentacao;
import br.com.careplus.mev.application.core.usecase.RegistroCheckInAlimentacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkins/alimentacao")
public class CheckInAlimentacaoController {

    @Autowired
    private RegistroCheckInAlimentacaoService service;

    @PostMapping
    @Transactional
    @PreAuthorize("hasAnyRole('MEDICO', 'PACIENTE')")
    public ResponseEntity<DadosDetalhamentoCheckInAlimentacao> registrar(
            @RequestBody @Valid DadosCheckInAlimentacao dados) {
        DadosDetalhamentoCheckInAlimentacao resultado = service.registrar(dados);
        return ResponseEntity.ok(resultado);
    }
}
