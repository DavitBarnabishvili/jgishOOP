package Core.Infrastructure.Persistence;

import java.sql.*;

public class DbConnection {
        private static final String USERNAME = "root";
        private static final String PASSWORD = "Gareganateba258";
        private static final String URL = "jdbc:mysql://localhost:3306/QuizWebsite";
        private static final Connection con;

        static {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException | SQLException var1) {
                throw new RuntimeException(var1);
            }
        }

        public static Connection getConnection() {
            return con;
        }

        public static void close() {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    public static void close(AutoCloseable... closeables) {
        for (AutoCloseable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
