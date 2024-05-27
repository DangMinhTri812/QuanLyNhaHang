package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static Connection con = null;
    private static ConnectDB instance = new ConnectDB();

    private ConnectDB() {
        // Constructor riêng tư để ngăn việc khởi tạo từ bên ngoài
    }

    public static ConnectDB getInstance() {
        return instance;
    }

    public void connect() throws SQLException {
        if (con == null || con.isClosed()) {
            String url = "jdbc:sqlserver://localhost:1433;databasename=QuanLyNhaHang";
            String user = "sa";
            String password = "sapassword";
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Kết nối cơ sở dữ liệu thành công.");
        } else {
            System.out.println("Kết nối đã được mở.");
        }
    }

    public void disconnect() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Đã ngắt kết nối cơ sở dữ liệu thành công.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
        try {
            if (con == null || con.isClosed()) {
                getInstance().connect();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
