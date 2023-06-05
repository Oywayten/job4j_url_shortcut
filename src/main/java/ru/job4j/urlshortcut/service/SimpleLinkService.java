package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.urlshortcut.dto.RequestUrl;
import ru.job4j.urlshortcut.dto.ResponseHostStatistic;
import ru.job4j.urlshortcut.domain.Host;
import ru.job4j.urlshortcut.domain.Link;
import ru.job4j.urlshortcut.mapper.LinkMapper;
import ru.job4j.urlshortcut.repository.HostRepository;
import ru.job4j.urlshortcut.repository.LinkRepository;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomStringUtils.randomAscii;

/**
 * Oywayten 03.06.2023.
 */
@Service
@AllArgsConstructor
@Slf4j
public class SimpleLinkService implements LinkService {
    private static final int CODE_LENGTH = 8;
    private final LinkRepository linkRepository;
    private final HostRepository hostRepository;
    private final LinkMapper linkMapper;

    @Override
    public String convert(RequestUrl requestUrl) {
        String url = requestUrl.getUrl();
        Link link = linkMapper.convertToLink(url);
        String code = randomAlphanumeric(CODE_LENGTH);
        while (linkRepository.existsByShortCode(code)) {
            code = randomAscii(CODE_LENGTH);
        }
        link.setShortCode(code);
        linkRepository.save(link);
        return code;
    }

    @Override
    @Transactional
    public Link getLinkByCode(String code) {
        Link link = linkRepository.findByShortCode(code);
        linkRepository.statisticIncrementByLinkId(link.getId());
        return link;
    }

    @Override
    public List<ResponseHostStatistic> getStatistics() {
        String login = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Host host = hostRepository.findByLogin(login);
        List<Link> linkList = linkRepository.findLinkByHost(host);
        List<ResponseHostStatistic> result = new ArrayList<>();
        for (Link link : linkList) {
            String urlString = "%s%s".formatted(host.getSite(), link.getPath());
            long requestCount = link.getRequestCount();
            result.add(new ResponseHostStatistic(urlString, requestCount));
        }
        return result;
    }
}
