package ru.job4j.template;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import java.util.*;

public class GeneratorTest {

    @Test
    public void whenProduce() {
        Generator generator = new PhraseGeneranor();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = Map.of(
                "name", "Kirill Smirnov",
                "subject", "you"
        );
        String result = generator.produce(template, args);
        String expected = "I am a Kirill Smirnov, Who are you?";
        assertThat(result, is(expected));
    }

    @Test
    public void whenIllegalArgumentsInTemplate() throws IllegalArgumentException {
        Generator generator = new PhraseGeneranor();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = Map.of(
                "subject", "you"
        );
        generator.produce(template, args);
    }

    @Test
    public void whenIllegalArgumentsInArgs() throws IllegalArgumentException {
        Generator generator = new PhraseGeneranor();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = Map.of(
                "name", "Kirill Smirnov",
                "subject", "you",
                "action", "go"
        );
        generator.produce(template, args);
    }
}