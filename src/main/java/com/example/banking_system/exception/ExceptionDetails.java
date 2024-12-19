package com.example.banking_system.exception;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record ExceptionDetails(LocalDateTime timestamp, String message, String details, String errorCode) {
}
