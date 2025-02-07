package com.goulart.api_agendamento_notificacao.infrastructure.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) { super(message); }
}