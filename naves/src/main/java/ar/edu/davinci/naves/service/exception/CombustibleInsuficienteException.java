package ar.edu.davinci.naves.service.exception;


public class CombustibleInsuficienteException extends RuntimeException {
    public CombustibleInsuficienteException(String message) {
        super(message);
    }
}