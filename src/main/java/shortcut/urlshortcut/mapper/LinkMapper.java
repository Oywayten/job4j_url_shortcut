package shortcut.urlshortcut.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import shortcut.urlshortcut.domain.Host;
import shortcut.urlshortcut.domain.Link;
import shortcut.urlshortcut.exeption.HostNotFoundException;
import shortcut.urlshortcut.repository.HostRepository;
import shortcut.urlshortcut.repository.LinkRepository;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Oywayten 03.06.2023.
 */
@Component
@Slf4j
public class LinkMapper {
    public static final String HOST_IS_NOT_FOUND_BY_NAME = "Host is not found by name = %s";
    public static final String THIS_LINK_ALREADY_EXIST_SHORT_CODE_IS = "This link already exist, short code is %s";
    public static final String A_MALFORMED_URL_HAS_OCCURRED = "A malformed URL has occurred";
    public static final String URL_HOST_DOES_NOT_BELONG_TO_THE_CURRENT_LOGIN
            = "Url host does not belong to the current login";
    public static final String URL_FETCH_ERROR = "URL fetch error.";
    public static final String LINK_ALREADY_EXIST = "Link already exist";
    private final HostRepository hostRepository;
    private final LinkRepository linkRepository;

    public LinkMapper(HostRepository hostRepository, LinkRepository linkRepository) {
        this.hostRepository = hostRepository;
        this.linkRepository = linkRepository;
    }

    public Link convertToLink(String stringUrl) {
        URL url = getUrl(stringUrl);
        String site = url.getHost();
        Host host = hostRepository.findBySite(site);
        checkHostPresence(site, host);
        String path = url.getPath();
        checkValidUser(host.getLogin());
        checkDuplicateLink(path, host);
        Link link = new Link();
        link.setHost(host);
        link.setPath(path);
        return link;
    }

    private static URL getUrl(String stringUrl) {
        URL url;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            log.error(URL_FETCH_ERROR, e);
            throw new IllegalArgumentException(A_MALFORMED_URL_HAS_OCCURRED);
        }
        return url;
    }

    private static void checkHostPresence(String hostName, Host host) {
        if (host == null) {
            throw new HostNotFoundException(HOST_IS_NOT_FOUND_BY_NAME.formatted(hostName));
        }
    }

    private void checkValidUser(String hostLogin) {
        String login = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        boolean isValidUser = hostLogin.equals(login);
        if (!isValidUser) {
            throw new IllegalArgumentException(URL_HOST_DOES_NOT_BELONG_TO_THE_CURRENT_LOGIN);
        }
    }

    private void checkDuplicateLink(String path, Host host) {
        Link duplicateLink = linkRepository.findByPathAndHost(path, host);
        if (duplicateLink != null) {
            log.error(LINK_ALREADY_EXIST);
            throw new IllegalArgumentException(
                    THIS_LINK_ALREADY_EXIST_SHORT_CODE_IS.formatted(duplicateLink.getShortCode()));
        }
    }
}
