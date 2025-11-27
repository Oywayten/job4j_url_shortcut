package shortcut.urlshortcut.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Oywayten 05.06.2023.
 */
@AllArgsConstructor
@Setter
@Getter
public class ResponseHostRegistration {
    private boolean registration;
    private String login;
    private String password;
}
