package org.example.dao.config.exeption;

public class PostgresConnectException extends RuntimeException {
    public PostgresConnectException(String message) {
        super(message);
    }
}