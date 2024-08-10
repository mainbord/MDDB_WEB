package com.mddb.exception;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Log4j2(topic = "app")
public abstract class BaseExceptionHandler {
    private static final ExceptionMapping DEFAULT_ERROR = new ExceptionMapping(
            "SERVER_ERROR",
            "Internal server error",
            INTERNAL_SERVER_ERROR);

    private final Map<Class, ExceptionMapping> exceptionMappings = new HashMap<>();

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ErrorDto handleThrowable(final Throwable ex, final HttpServletResponse response, final HttpServletRequest request) {
        ExceptionMapping mapping = exceptionMappings.getOrDefault(ex.getClass(), DEFAULT_ERROR);
        response.setStatus(mapping.status.value());
        return logAndConstruct(mapping, ex);
    }

    protected void registerMapping(final Class<?> clazz, final String code, final String message, final HttpStatus status) {
        exceptionMappings.put(clazz, new ExceptionMapping(code, message, status));
    }


    protected ErrorDto construct(ExceptionMapping mapping, Throwable t) {
        return new ErrorDto(mapping.code, mapping.message + ": " + (t == null ? "" : t.getMessage() == null ? t : t.getMessage()));
    }

    private ErrorDto construct(ExceptionMapping mapping, Throwable t, String errMsg) {
        return new ErrorDto(mapping.code, mapping.message + ": " + errMsg);
    }

    protected ErrorDto logAndConstruct(ExceptionMapping mapping, Throwable t) {
        log.error("{} ({}): {}", mapping.message, mapping.code, t.getMessage(), t);
        return construct(mapping, t);
    }

    private static class ExceptionMapping {
        private final String message;
        private final String code;
        private final HttpStatus status;

        public ExceptionMapping(final String code, final String message, final HttpStatus status) {
            this.code = code;
            this.message = message;
            this.status = status;
        }
    }
}
