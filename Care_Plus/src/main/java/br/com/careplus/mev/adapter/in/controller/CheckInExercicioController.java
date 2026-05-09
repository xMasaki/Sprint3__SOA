package br.com.careplus.mev.adapter.in.controller;

import br.com.careplus.mev.adapter.in.controller.request.checkIn.DadosCheckInExercicio;
import br.com.careplus.mev.adapter.in.controller.response.checkIn.DadosDetalhamentoCheckInExercicio;
import br.com.careplus.mev.application.core.usecase.RegistroCheckInExercicioService;
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
@RequestMapping("/checkins/exercicio")
public class CheckInExercicioController {

    @Autowired
    private RegistroCheckInExercicioService service;

    @PostMapping
    @Transactional
    @PreAuthorize("hasAnyRole('MEDICO', 'PACIENTE')")
    public ResponseEntity<DadosDetalhamentoCheckInExercicio> registrar(
            @RequestBody @Valid DadosCheckInExercicio dados) {
        DadosDetalhamentoCheckInExercicio resultado = service.registrar(dados);
        return ResponseEntity.ok(resultado);
    }
}
