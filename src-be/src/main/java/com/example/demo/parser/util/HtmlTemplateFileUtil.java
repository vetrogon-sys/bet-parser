package com.example.demo.parser.util;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;

public class HtmlTemplateFileUtil {

    public static final String PATH_TO_HTML_TEMPLATE = "src-be/src/main/resources/statistics/templates/html/template.html";

    private HtmlTemplateFileUtil() {
    }

    @SneakyThrows
    public static String getHtmlTemplate() {
        return Files.readString(Path.of(PATH_TO_HTML_TEMPLATE));
    }

}
