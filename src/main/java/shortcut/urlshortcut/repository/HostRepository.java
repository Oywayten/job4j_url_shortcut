package shortcut.urlshortcut.repository;

import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import shortcut.urlshortcut.domain.Host;

import java.util.List;

/**
 * Oywayten 25.05.2023.
 */
public interface HostRepository extends CrudRepository<Host, Long> {
    @NonNull
    List<Host> findAll();

    Host findByLogin(String login);
    Host findBySite(String site);

}
