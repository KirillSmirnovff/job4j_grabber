package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.HarbCareerDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerParse implements Parse {

    private static final String SOURCE_LINK = "https://career.habr.com";

    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer?page=", SOURCE_LINK);

    private final DateTimeParser dateTimeParser;

    public HabrCareerParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    private String retrieveDescription(String link) {
        String result = null;
        Connection connection = Jsoup.connect(link);
        try {
            Document document = connection.get();
            Element descriptionElement = document.selectFirst(".style-ugc");
            result = descriptionElement.text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void parsing(Element element, List<Post> list) {
        Element titleElement = element.select(".vacancy-card__title").first();
        Element linkElement = titleElement.child(0);
        Element timeElement = element.select(".vacancy-card__date").first().child(0);
        String vacancyName = titleElement.text();
        String vacancyLink = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
        LocalDateTime vacancyTime = dateTimeParser.parse(timeElement.attr("datetime"));
        String vacancyDesc = retrieveDescription(vacancyLink);
        list.add(new Post(vacancyName, vacancyLink, vacancyDesc, vacancyTime));
    }

    @Override
    public List<Post> list(String link) {
        List<Post> result = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            Connection connection = Jsoup.connect(link + i);
            try {
                Document document = connection.get();
                Elements rows = document.select(".vacancy-card__inner");
                rows.forEach(row -> parsing(row, result));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        HabrCareerParse parse = new HabrCareerParse(new HarbCareerDateTimeParser());
        List<Post> vacancyList = parse.list(PAGE_LINK);
        for (Post post : vacancyList) {
            System.out.println(post);
        }
    }
}