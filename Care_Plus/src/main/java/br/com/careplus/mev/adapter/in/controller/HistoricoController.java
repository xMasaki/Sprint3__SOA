package br.com.careplus.mev.adapter.in.controller;

import br.com.careplus.mev.adapter.in.controller.response.historico.DadosHistoricoPaciente;
import br.com.careplus.mev.application.core.usecase.HistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/historico")
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;

    @GetMapping("/{idPaciente}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDICO')")
    public ResponseEntity<DadosHistoricoPaciente> buscarHistorico(
            @PathVariable Long idPaciente,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        DadosHistoricoPaciente historico = historicoService.buscarHistorico(idPaciente, dataInicio, dataFim);
        return ResponseEntity.ok(historico);
    }
}
