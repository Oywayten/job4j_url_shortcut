package shortcut.urlshortcut.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.*;
import shortcut.urlshortcut.domain.Host;
import shortcut.urlshortcut.domain.Link;
import shortcut.urlshortcut.dto.RequestUrl;
import shortcut.urlshortcut.dto.ResponseCode;
import shortcut.urlshortcut.dto.ResponseHostRegistration;
import shortcut.urlshortcut.dto.ResponseHostStatistic;
import shortcut.urlshortcut.service.HostService;
import shortcut.urlshortcut.service.LinkService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Oywayten 25.05.2023.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/shortcut")
@Tag(name = "Shortcut controller", description = "Operations about shortcut")
public class ShortcutController {

    private final HostService hostService;
    private final LinkService linkService;

    @PostMapping("/registration")
    @io.swagger.v3.oas.annotations.Operation(
            summary = "Host registration",
            description = "Allows you to register a host"
    )
    public ResponseEntity<ResponseHostRegistration> registration(
            @Valid @RequestBody @Parameter(description = "Host to registration") Host host) {
        return new ResponseEntity<>(
                    hostService.save(host),
                new MultiValueMapAdapter<>(Map.of("Title", List.of("registration"))),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/convert")
    @io.swagger.v3.oas.annotations.Operation(
            summary = "url conversion",
            description = "Convert url to unique short code"
    )
    public ResponseEntity<ResponseCode> convert(@RequestBody @Parameter(description = "URL") RequestUrl requestUrl) {
        String code = linkService.convert(requestUrl);
        return new ResponseEntity<>(
                new ResponseCode(code),
                new MultiValueMapAdapter<>(Map.of("Title", List.of("urlShortening"))),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/redirect/{code}")
    @io.swagger.v3.oas.annotations.Operation(
            summary = "get link",
            description = "get link by code"
    )
    public ResponseEntity<?> getLink(@PathVariable @Parameter(description = "URL") String code) {
        Link link = linkService.getLinkByCode(code);
        Host host = link.getHost();
        String url = "%s%s".formatted(host.getSite(), link.getPath());
        return new ResponseEntity<>(
                new MultiValueMapAdapter<>(Map.of("HTTP CODE", List.of(url))), HttpStatus.FOUND
        );
    }

    @GetMapping("/statistic")
    @io.swagger.v3.oas.annotations.Operation(
            summary = "url conversion",
            description = "Convert url to unique short code"
    )
    public ResponseEntity<List<ResponseHostStatistic>> getStatistics() {
        return new ResponseEntity<>(
                linkService.getStatistics(),
                new MultiValueMapAdapter<>(Map.of("Title", List.of("statistic"))),
                HttpStatus.OK
        );
    }
}
