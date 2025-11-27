package shortcut.urlshortcut.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import shortcut.urlshortcut.domain.Host;
import shortcut.urlshortcut.domain.Link;

import java.util.List;

/**
 * Oywayten 25.05.2023.
 */
public interface LinkRepository extends CrudRepository<Link, Long> {
    @NonNull
    List<Link> findAll();

    List<Link> findLinkByHost(Host host);

    Link findByPathAndHost(String path, Host host);

    Link findByShortCode(String shortCode);

    boolean existsByShortCode(String shortCode);

    @Modifying
    @Query(value = "update link set request_count = (request_count + 1) WHERE id = :id", nativeQuery = true)
    void statisticIncrementByLinkId(long id);
}
