package dev.hieplp.spring.scheduler.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PageEnum {
    INDEX("index", "/"),
    CREATE("create", "/create"),
    UPDATE("update", "/update"),
    DELETE("delete", "/delete"),
    NOT_FOUND("404", "/404");

    private final String name;
    private final String path;

    public static String redirect(PageEnum page) {
        return page.redirect();
    }

    public String redirect() {
        return "redirect:" + path;
    }

    public String forward() {
        return "forward:" + path;
    }

    public String path() {
        return path;
    }
}
