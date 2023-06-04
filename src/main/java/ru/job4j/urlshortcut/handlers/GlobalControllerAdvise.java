package ru.job4j.urlshortcut.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.job4j.urlshortcut.exeption.HostNotFoundException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Oywayten 27.05.2023.
 */
@ControllerAdvice
public class GlobalControllerAdvise {


    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerAdvise.class.getSimpleName());

    private final ObjectMapper objectMapper;

    public GlobalControllerAdvise(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler(value = {HostNotFoundException.class, IllegalArgumentException.class})
    public void handleException(Exception e, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() { {
            put("message", "Something is wrong");
            put("details", e.getMessage());
        }}));
        LOGGER.error(e.getMessage());
    }


}