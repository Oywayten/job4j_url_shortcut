package shortcut.urlshortcut.service;

import org.springframework.transaction.annotation.Transactional;
import shortcut.urlshortcut.domain.Link;
import shortcut.urlshortcut.dto.RequestUrl;
import shortcut.urlshortcut.dto.ResponseHostStatistic;

import java.util.List;

/**
 * Oywayten 05.06.2023.
 */
public interface LinkService {
    String convert(RequestUrl requestUrl);

    @Transactional
    Link getLinkByCode(String code);

    List<ResponseHostStatistic> getStatistics();
}
