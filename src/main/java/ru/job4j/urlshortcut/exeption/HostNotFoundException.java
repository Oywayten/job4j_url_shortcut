package ru.job4j.urlshortcut.exeption;

/**
 * Oywayten 27.05.2023.
 */
public class HostNotFoundException extends RuntimeException {
    public HostNotFoundException(String message) {
        super(message);
    }
}
