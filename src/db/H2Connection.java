package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Connection {
        private static final String DB_JDBC_DRIVER = "jdbc:h2:~/db_be_odontologia"; // adjust this to your H2 URL
        private static final String USER = "sa"; // adjust this to your H2 username
        private static final String PASSWORD = "sa";

        public static Connection getConnection() throws ClassNotFoundException, SQLException {
            Class.forName(DB_JDBC_DRIVER);
            return DriverManager.getConnection(DB_JDBC_DRIVER, USER, PASSWORD);
        }
}
