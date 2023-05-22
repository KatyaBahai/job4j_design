package ru.job4j.spammer;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users;
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            users = rd.lines()
                    .map(p -> {
                        String[] split = p.split(";");
                       if (split.length != 2) {
                           throw new IllegalArgumentException();
                        }
                        return new User(split[0], split[1]);
                    })
                    .collect(Collectors.toList());
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            createUsersTable("users", "name", "email", cnt);
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement(
                        "INSERT INTO users(name, email) VALUES(?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
            try (PreparedStatement statement = cnt.prepareStatement("SELECT * FROM users")) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        System.out.println(resultSet.getString("name"));
                        System.out.println(resultSet.getString("email"));
                    }
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public void createUsersTable(String tableName, String col1, String col2, Connection cnt) {
        String create = String.format("CREATE TABLE IF NOT EXISTS %s("
                + "id serial primary key, "
                + "%s text,"
                + "%s text);", tableName, col1, col2);
        try (Statement statement = cnt.createStatement()) {
            statement.executeUpdate(create);
            ResultSet selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            ResultSetMetaData metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                System.out.printf("%-15s|%-15s%n%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "data/dump.txt");
        db.save(db.load());
    }
}