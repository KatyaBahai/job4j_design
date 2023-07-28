package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String absPath = String.format("%s%s", cachingDir, key);
        Path path = Path.of(absPath);

        StringBuilder builder = new StringBuilder();
        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(builder::append);
        } catch (IOException e)  {
            e.printStackTrace();
        }
        String value = builder.toString();
       put(key, value);
        return value;
    }

}