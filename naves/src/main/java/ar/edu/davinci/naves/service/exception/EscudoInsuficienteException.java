package ar.edu.davinci.naves.service.exception;


public class EscudoInsuficienteException extends RuntimeException {
    public EscudoInsuficienteException(String message) {
        super(message);
    }
}