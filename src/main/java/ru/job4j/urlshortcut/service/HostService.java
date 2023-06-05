package ru.job4j.urlshortcut.service;

import ru.job4j.urlshortcut.domain.Host;
import ru.job4j.urlshortcut.dto.ResponseHostRegistration;

/**
 * Oywayten 05.06.2023.
 */
public interface HostService {
    ResponseHostRegistration save(Host host);

    Host findByLogin(String login);
}
