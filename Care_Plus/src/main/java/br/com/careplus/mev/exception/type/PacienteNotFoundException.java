package br.com.careplus.mev.exception.type;

public class PacienteNotFoundException extends RuntimeException {
    public PacienteNotFoundException(Long id) {
        super("Paciente não encontrado com id: " + id);
    }
}
