package com.riu.practice.palabrota.domain.exception;

import lombok.Getter;

/**
 * Enum representing various infrastructure exception codes.
 */
@Getter
public enum InfrastructureExceptionCode {
    UNKNOWN_ERROR(1),
    BAD_REQUEST(400),
    INTERNAL_SERVER_ERROR(500),
    NO_CONTENT(204),
    NOT_FOUND(404);

    private final int errorCode;

    /**
     * Constructor for InfrastructureExceptionCode.
     *
     * @param errorCode the error code associated with the exception
     */
    InfrastructureExceptionCode(final int errorCode) {
        this.errorCode = errorCode;
    }
}
