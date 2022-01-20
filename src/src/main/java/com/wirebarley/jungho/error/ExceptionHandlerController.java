package com.wirebarley.jungho.error;

import com.wirebarley.jungho.exception.WirebarleyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(annotations = Controller.class)
public class ExceptionHandlerController {

    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalArgument(final IllegalArgumentException e, Model model) {
        log.error("IllegalArgument Exception occur", e);
        model.addAttribute("errorMessage", e.getMessage());
        return "error/message";
    }

    @ExceptionHandler(WirebarleyException.class)
    public String wirebarley(final WirebarleyException e, Model model) {
        log.error("Wirebarley Exception occur", e);
        model.addAttribute("errorMessage", e.getMessage());
        return "error/message";
    }
}
