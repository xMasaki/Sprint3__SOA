package br.com.careplus.mev.adapter.in.controller;

import br.com.careplus.mev.adapter.in.controller.response.relatorio.DadosRelatorioMEV;
import br.com.careplus.mev.application.core.usecase.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/{idPaciente}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDICO')")
    public ResponseEntity<DadosRelatorioMEV> gerarRelatorio(
            @PathVariable Long idPaciente,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        DadosRelatorioMEV relatorio = relatorioService.gerarRelatorio(idPaciente, dataInicio, dataFim);
        return ResponseEntity.ok(relatorio);
    }
}
