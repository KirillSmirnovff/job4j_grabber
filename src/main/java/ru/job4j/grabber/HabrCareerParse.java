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

    private String retrieveDescription(String link) throws IOException {
        Connection connection = Jsoup.connect(link);
        Document document = connection.get();
        Element descriptionElement = document.selectFirst(".style-ugc");
        return descriptionElement.text();
    }

    @Override
    public List<Post> list(String link) throws IOException {
        Connection connection = Jsoup.connect(link);
        Document document = connection.get();
        Elements rows = document.select(".vacancy-card__inner");
        List<Post> result = new ArrayList<>();
        rows.forEach(row -> {
            Element titleElement = row.select(".vacancy-card__title").first();
            Element linkElement = titleElement.child(0);
            Element timeElement = row.select(".vacancy-card__date").first().child(0);
            String vacancyName = titleElement.text();
            String vacancyLink = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
            LocalDateTime vacancyTime = dateTimeParser.parse(timeElement.attr("datetime"));
            String vacancyDesc = null;
            try {
                vacancyDesc = retrieveDescription(vacancyLink);
            } catch (IOException e) {
                e.printStackTrace();
            }
            result.add(new Post(vacancyName, vacancyLink, vacancyDesc, vacancyTime));
        });
        return result;
    }

    public static void main(String[] args) throws IOException {
        HabrCareerParse parse = new HabrCareerParse(new HarbCareerDateTimeParser());
        List<Post> vacancyList = new ArrayList<>();
        int page = 1;
        while (page != 6) {
            vacancyList.addAll(parse.list(PAGE_LINK + page));
            page++;
        }
        for (Post post : vacancyList) {
            System.out.println(post);
        }
    }
}