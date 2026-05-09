package br.com.careplus.mev.adapter.in.controller;

import br.com.careplus.mev.adapter.in.controller.request.paciente.DadosAtualizacaoPaciente;
import br.com.careplus.mev.adapter.in.controller.request.paciente.DadosCadastroPaciente;
import br.com.careplus.mev.adapter.in.controller.response.paciente.DadosDetalhamentoPaciente;
import br.com.careplus.mev.adapter.in.controller.response.paciente.DadosListagemPaciente;
import br.com.careplus.mev.application.core.domain.model.Paciente;
import br.com.careplus.mev.application.port.out.PacienteRepository;
import br.com.careplus.mev.exception.type.PacienteNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDICO')")
    public ResponseEntity<DadosDetalhamentoPaciente> cadastrar(
            @RequestBody @Valid DadosCadastroPaciente dados,
            UriComponentsBuilder uriBuilder) {
        Paciente paciente = new Paciente(
                dados.nome(), dados.email(), dados.cpf(),
                dados.dataNascimento(), dados.telefone(),
                dados.medicoId(), dados.usuarioId()
        );
        pacienteRepository.save(paciente);
        URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDICO')")
    public ResponseEntity<Page<DadosListagemPaciente>> listar(
            @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        Page<DadosListagemPaciente> page = pacienteRepository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDICO', 'PACIENTE')")
    public ResponseEntity<DadosDetalhamentoPaciente> detalhar(@PathVariable Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new PacienteNotFoundException(id));
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @PutMapping
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDICO')")
    public ResponseEntity<DadosDetalhamentoPaciente> atualizar(
            @RequestBody @Valid DadosAtualizacaoPaciente dados) {
        Paciente paciente = pacienteRepository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.excluir();
        return ResponseEntity.noContent().build();
    }
}
