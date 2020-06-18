package com.study.studyrestfulservice;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageSource messageSource;

    @GetMapping(path = "/hello-internationalized")
    public String internationalized(@RequestHeader(name="Accept-Language", required=false) Locale locale){
        return messageSource.getMessage("greeting.message",null,locale);
    }
}
