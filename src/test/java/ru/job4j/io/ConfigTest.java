package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Nikita");
    }

    @Test
    void whenPairWithComment() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.url")).isEqualTo("jdbc:postgresql://127.0.0.1:5432/trackstudio");
    }

    @Test
    void whenNoKeyValuePair() {
        String path = "./data/noKeyValuePair.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenNMultipleEqualsSings() {
        String path = "./data/multipleEquals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("son1")).isEqualTo("Wyatte=");
        assertThat(config.value("lastName")).isEqualTo("=Halliwell");
    }

}