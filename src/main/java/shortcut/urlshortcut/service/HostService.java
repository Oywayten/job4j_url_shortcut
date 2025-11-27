package shortcut.urlshortcut.service;

import shortcut.urlshortcut.domain.Host;
import shortcut.urlshortcut.dto.ResponseHostRegistration;

/**
 * Oywayten 05.06.2023.
 */
public interface HostService {
    ResponseHostRegistration save(Host host);

    Host findByLogin(String login);
}
