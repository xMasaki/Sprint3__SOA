package br.com.careplus.mev.adapter.in.controller;

import br.com.careplus.mev.adapter.in.controller.request.medico.DadosAtualizacaoMedico;
import br.com.careplus.mev.adapter.in.controller.request.medico.DadosCadastroMedico;
import br.com.careplus.mev.adapter.in.controller.response.medico.DadosDetalhamentoMedico;
import br.com.careplus.mev.adapter.in.controller.response.medico.DadosListagemMedico;
import br.com.careplus.mev.application.core.domain.model.Medico;
import br.com.careplus.mev.application.port.out.MedicoRepository;
import br.com.careplus.mev.exception.type.MedicoNotFoundException;
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
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DadosDetalhamentoMedico> cadastrar(
            @RequestBody @Valid DadosCadastroMedico dados,
            UriComponentsBuilder uriBuilder) {
        Medico medico = new Medico(dados.nome(), dados.email(), dados.crm(), dados.telefone(), dados.usuarioId());
        medicoRepository.save(medico);
        URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDICO')")
    public ResponseEntity<Page<DadosListagemMedico>> listar(
            @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        Page<DadosListagemMedico> page = medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDICO')")
    public ResponseEntity<DadosDetalhamentoMedico> detalhar(@PathVariable Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new MedicoNotFoundException(id));
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @PutMapping
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDICO')")
    public ResponseEntity<DadosDetalhamentoMedico> atualizar(
            @RequestBody @Valid DadosAtualizacaoMedico dados) {
        Medico medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }
}
