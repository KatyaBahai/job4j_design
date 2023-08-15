package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled
class GeneratorTest {
    @Test
    public void whenNoMistakesThenGeneratedString() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr");
        args.put("subject", "you");
        Generator generator = new TemplateGenerator();
        String expected = "I am a Petr Arsentev, Who are you? ";
        assertThat(expected).isEqualTo(generator.produce(template, args));
    }

    @Test
    public void whenNoKeyInMapThenException() {
        String template = "I am a ${name}, and this is ${pet}.";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr");
        Generator generator = new TemplateGenerator();
        assertThatThrownBy(() -> generator.produce(template, args)).
                isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenNoKeyInTemplateThenException() {
        String template = "I am a ${name}, and this is ${subject}.";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr");
        args.put("pet", "Sharik");
        Generator generator = new TemplateGenerator();
        assertThatThrownBy(() -> generator.produce(template, args)).
                isInstanceOf(IllegalArgumentException.class);
    }
}