package br.com.careplus.mev.exception;

import br.com.careplus.mev.exception.type.MedicoNotFoundException;
import br.com.careplus.mev.exception.type.PacienteNotFoundException;
import br.com.careplus.mev.exception.type.ValidacaoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorGlobalDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> lidarComErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosBadRequest>> lidarComErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors()
                .stream()
                .map(DadosBadRequest::new)
                .toList();
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<DadosErroNegocio> lidarComValidacaoException(ValidacaoException ex) {
        return ResponseEntity.badRequest().body(new DadosErroNegocio(ex.getMessage()));
    }

    @ExceptionHandler(PacienteNotFoundException.class)
    public ResponseEntity<DadosErroNegocio> lidarComPacienteNotFoundException(PacienteNotFoundException ex) {
        return ResponseEntity.status(404).body(new DadosErroNegocio(ex.getMessage()));
    }

    @ExceptionHandler(MedicoNotFoundException.class)
    public ResponseEntity<DadosErroNegocio> lidarComMedicoNotFoundException(MedicoNotFoundException ex) {
        return ResponseEntity.status(404).body(new DadosErroNegocio(ex.getMessage()));
    }

    private record DadosBadRequest(String campo, String mensagem) {
        DadosBadRequest(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

    private record DadosErroNegocio(String mensagem) {}
}
