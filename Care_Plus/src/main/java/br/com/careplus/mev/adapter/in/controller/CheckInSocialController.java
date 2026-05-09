package br.com.careplus.mev.adapter.in.controller;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInSocial;
import br.com.careplus.mev.adapter.in.controller.response.checkIn.DadosDetalhamentoCheckInSocial;
import br.com.careplus.mev.application.core.usecase.RegistroCheckInSocialService;
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
@RequestMapping("/checkins/social")
public class CheckInSocialController {

    @Autowired
    private RegistroCheckInSocialService service;

    @PostMapping
    @Transactional
    @PreAuthorize("hasAnyRole('MEDICO', 'PACIENTE')")
    public ResponseEntity<DadosDetalhamentoCheckInSocial> registrar(
            @RequestBody @Valid DadosCheckInSocial dados) {
        DadosDetalhamentoCheckInSocial resultado = service.registrar(dados);
        return ResponseEntity.ok(resultado);
    }
}
