package br.com.careplus.mev.adapter.in.controller;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInSubstancias;
import br.com.careplus.mev.adapter.in.controller.response.checkIn.DadosDetalhamentoCheckInSubstancias;
import br.com.careplus.mev.application.core.usecase.RegistroCheckInSubstanciasService;
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
@RequestMapping("/checkins/substancias")
public class CheckInSubstanciasController {

    @Autowired
    private RegistroCheckInSubstanciasService service;

    @PostMapping
    @Transactional
    @PreAuthorize("hasAnyRole('MEDICO', 'PACIENTE')")
    public ResponseEntity<DadosDetalhamentoCheckInSubstancias> registrar(
            @RequestBody @Valid DadosCheckInSubstancias dados) {
        DadosDetalhamentoCheckInSubstancias resultado = service.registrar(dados);
        return ResponseEntity.ok(resultado);
    }
}
