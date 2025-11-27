package shortcut.urlshortcut.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Oywayten 04.06.2023.
 */
@AllArgsConstructor
@Setter
@Getter
public class ResponseHostStatistic {
    private String url;
    private long total;
}
