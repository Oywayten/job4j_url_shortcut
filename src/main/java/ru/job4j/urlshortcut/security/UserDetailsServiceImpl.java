package ru.job4j.urlshortcut.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.domain.Host;
import ru.job4j.urlshortcut.service.HostService;

import static java.util.Collections.emptyList;

/**
 * Oywayten 26.05.2023.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final HostService hostService;

    public UserDetailsServiceImpl(HostService hostService) {
        this.hostService = hostService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Host host = hostService.findByLogin(login);
        if (host == null) {
            throw new UsernameNotFoundException(login);
        }
        return new User(host.getLogin(), host.getPassword(), emptyList());
    }
}