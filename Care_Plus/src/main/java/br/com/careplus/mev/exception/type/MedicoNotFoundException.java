package br.com.careplus.mev.exception.type;

public class MedicoNotFoundException extends RuntimeException {
    public MedicoNotFoundException(Long id) {
        super("Médico não encontrado com id: " + id);
    }
}
