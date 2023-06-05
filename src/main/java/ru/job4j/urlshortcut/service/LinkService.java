package ru.job4j.urlshortcut.service;

import org.springframework.transaction.annotation.Transactional;
import ru.job4j.urlshortcut.domain.Link;
import ru.job4j.urlshortcut.dto.RequestUrl;
import ru.job4j.urlshortcut.dto.ResponseHostStatistic;

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
