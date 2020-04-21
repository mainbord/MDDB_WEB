package com.mddb.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@Log4j2(topic = "app")
public class ErrorHandler extends BaseExceptionHandler {

}
