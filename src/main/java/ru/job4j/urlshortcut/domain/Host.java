package ru.job4j.urlshortcut.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

/**
 * Oywayten 25.05.2023.
 */
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Host {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    @NotNull(message = "site must be not empty")
    @Length(min = 4, max = 255, message = "site must be min = 4, max = 255")
    @Pattern(regexp = "^[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "It must be real hostname")
    private String site;

    private String login;

    private String password;
}