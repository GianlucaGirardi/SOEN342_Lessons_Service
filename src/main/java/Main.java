import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties props = new Properties();
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Unable to find config.properties");
                return;
            }
            props.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        try {
            /*  load MySQL driver */
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                if (conn != null) {
                    System.out.println("Connected to the database!");

                    String createTableSQL = "CREATE TABLE TestTable (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255))";
                    try (Statement stmt = conn.createStatement()) {
                        stmt.execute(createTableSQL);
                        System.out.println("Table created successfully.");
                    }

                    String insertSQL = "INSERT INTO TestTable (name) VALUES (?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                        pstmt.setString(1, "Test Name 1");
                        pstmt.executeUpdate();

                        pstmt.setString(1, "Test Name 2");
                        pstmt.executeUpdate();
                        System.out.println("Data inserted successfully.");
                    }

                    String selectSQL = "SELECT * FROM TestTable";
                    try (Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery(selectSQL)) {

                        System.out.println("Data in TestTable:");
                        while (rs.next()) {
                            int id = rs.getInt("id");
                            String name = rs.getString("name");
                            System.out.println("ID: " + id + ", Name: " + name);
                        }
                    }

                    String dropTableSQL = "DROP TABLE TestTable";
                    try (Statement stmt = conn.createStatement()) {
                        stmt.execute(dropTableSQL);
                        System.out.println("Table dropped successfully.");
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

