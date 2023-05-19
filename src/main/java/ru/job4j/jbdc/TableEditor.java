package ru.job4j.jbdc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (TableEditor editor = new TableEditor(properties)) {
            editor.createTable("Silverware");
            System.out.println(editor.getTableScheme("Silverware"));
            editor.addColumn("Silverware", "name", "text");
            editor.addColumn("Silverware", "count", "integer");
            editor.addColumn("Silverware", "metal", "text");
            editor.addColumn("Silverware", "brand", "text");
            System.out.println(editor.getTableScheme("Silverware"));
            editor.dropColumn("Silverware", "brand");
            System.out.println(editor.getTableScheme("Silverware"));
            editor.renameColumn("Silverware", "name", "item");
            System.out.println(editor.getTableScheme("Silverware"));
            editor.dropTable("Silverware");
            try {
                editor.getTableScheme("Silverware");
            } catch (Exception e) {
                System.out.println("The table doesn't exist.");
            }
        }
    }

    public TableEditor(Properties properties) throws ClassNotFoundException, SQLException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        ClassLoader loader = TableEditor.class.getClassLoader();
        try (InputStream in = loader.getResourceAsStream("app.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Class.forName(properties.getProperty("driver"));
        this.connection = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("username"),
                properties.getProperty("password"));
    }

    public void execStatement(String query) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
            String create = String.format("CREATE TABLE %s();", tableName);
            execStatement(create);
    }

    public void dropTable(String tableName) {
            String drop = String.format("DROP TABLE %s", tableName);
            execStatement(drop);
    }

    public void addColumn(String tableName, String columnName,
                          String type)  {
        String addCol = String.format("ALTER TABLE %s ADD COLUMN %s %s",
                tableName, columnName, type);
        execStatement(addCol);
    }

    public void dropColumn(String tableName, String columnName)  {
        String dropCol = String.format("ALTER TABLE %s DROP COLUMN %s",
                tableName, columnName);
        execStatement(dropCol);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String renameCol = String.format("ALTER TABLE %s RENAME COLUMN %s to %s",
                tableName, columnName, newColumnName);
        execStatement(renameCol);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (Statement statement = connection.createStatement()) {
            ResultSet selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}