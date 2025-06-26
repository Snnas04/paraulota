package com.riu.practice.palabrota.domain.exception;

import lombok.Getter;

/**
 * Exception thrown to indicate an infrastructure-related error.
 */
@Getter
public class InfrastructureException extends RuntimeException {
    private final InfrastructureExceptionCode infrastructureExceptionCode;

    /**
     * Constructs a new InfrastructureException with the specified detail message and code.
     *
     * @param message the detail message
     * @param code the infrastructure exception code
     */
    public InfrastructureException(String message, InfrastructureExceptionCode code) {
        super(message);
        infrastructureExceptionCode = code;
    }
}
