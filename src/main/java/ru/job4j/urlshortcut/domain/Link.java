package ru.job4j.urlshortcut.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

/**
 * Oywayten 03.06.2023.
 */
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Link {

    public static final String PATH_AFTER_SITE_MUST_BE_MIN = "path after site must be min = 2";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    @Length(min = 4, message = PATH_AFTER_SITE_MUST_BE_MIN)
    private String path;

    @ManyToOne
    private Host host;

    private String shortCode;

    private long requestCount;
}
