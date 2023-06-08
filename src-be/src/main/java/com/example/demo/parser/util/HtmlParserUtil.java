package com.example.demo.parser.util;

public class HtmlParserUtil {

    private HtmlParserUtil() {
    }
    public static String getAsCellWithHref(String href) {
        String htmlLink = "<a href=\"" + href + "\">Go to site</a>";
        return getAsCell(htmlLink);
    }

    public static String getAsCell(String content) {
        return "<th>" + content + "</th>";
    }

    public static String getAsRow(String content) {
        return "<tr>" + content + "</tr>";
    }

}
