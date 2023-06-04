package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.domain.Host;
import ru.job4j.urlshortcut.dto.ResponseHostRegistration;
import ru.job4j.urlshortcut.repository.HostRepository;

/**
 * Oywayten 26.05.2023.
 */
@Service
@AllArgsConstructor
@Slf4j
public class HostService {

    private static final int STRING_LENGTH = 16;

    private static final String EMPTY_STRING = "";

    private final BCryptPasswordEncoder encoder;
    private final HostRepository hostRepository;

    public ResponseHostRegistration save(Host host) {
        String site = host.getSite();
        String password = EMPTY_STRING;
        String login = EMPTY_STRING;
        Host duplicateHost = hostRepository.findBySite(site);
        boolean registration = false;
        if (duplicateHost == null) {
            password = RandomStringUtils.randomAlphanumeric(STRING_LENGTH);
            login = RandomStringUtils.randomAlphanumeric(STRING_LENGTH);
            host.setId(0L);
            host.setPassword(encoder.encode(password));
            host.setLogin(login);
            hostRepository.save(host);
            registration = true;
        }
        return new ResponseHostRegistration(registration, login, password);
    }

    public Host findByLogin(String login) {
        return hostRepository.findByLogin(login);
    }
}
