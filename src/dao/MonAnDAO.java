package dao;

import connectDB.ConnectDB;
import entity.Ban;
import entity.LoaiMon;
import entity.MonAn;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MonAnDAO {

    public static List<MonAn> layThongTin() {
        List<MonAn> dsMonAn = new ArrayList<>();
        try (Connection con = ConnectDB.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT maMon, tenMon, donGia, maLoaiMon FROM Mon");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String maMon = rs.getString("maMon");
                String tenMon = rs.getString("tenMon");
                int donGia = rs.getInt("donGia");
                String maLoaiMon = rs.getString("maLoaiMon");
                LoaiMon loaiMon = new LoaiMon(maLoaiMon); // Assuming LoaiMon constructor requires only maLoaiMon
                MonAn monAn = new MonAn(maMon, tenMon, donGia, loaiMon);
                dsMonAn.add(monAn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsMonAn;
    }

    public String layMaMonAnMoiNhat() {
        String ma = null;
        try (Connection connection = ConnectDB.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT MAX(maMon) AS ma FROM Mon")) {

            if (resultSet.next()) {
                ma = resultSet.getString("ma");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ma;
    }

    public boolean xoaMon(String maMon) {
        int n = 0;
        try (Connection con = ConnectDB.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement("DELETE FROM Mon WHERE maMon = ?")) {

            statement.setString(1, maMon);
            n = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
    public boolean themMonAn(MonAn monAn) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        String SQL;
        int n = 0;
        try {
            if (monAn.getLoaiMon() == null) {
                SQL = "INSERT INTO Mon (maMon, tenMon, donGia, maLoaiMon) VALUES (?, ?, ?, NULL)";
                statement = con.prepareStatement(SQL);
                statement.setString(1, monAn.getMaMon());
                statement.setString(2, monAn.getTenMon());
                statement.setInt(3, monAn.getDonGia());
            } else {
                SQL = "INSERT INTO Mon (maMon, tenMon, donGia, maLoaiMon) VALUES (?, ?, ?, ?)";
                statement = con.prepareStatement(SQL);
                statement.setString(1, monAn.getMaMon());
                statement.setString(2, monAn.getTenMon());
                statement.setInt(3, monAn.getDonGia());
                statement.setString(4, monAn.getLoaiMon().getMaLoaiMon());
            }

            n = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo đóng tài nguyên
            try {
                if (statement != null) statement.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return n > 0;
    }
    public void capNhatThongTinMon(String maMon, String tenMon, int donGia, String tenLoaiMon) {
        try (Connection con = ConnectDB.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement("UPDATE Mon SET tenMon = ?, donGia = ?, maLoaiMon = (SELECT maLoaiMon FROM LoaiMon WHERE tenLoaiMon = ?) WHERE maMon = ?")) {

            statement.setString(1, tenMon);
            statement.setInt(2, donGia);
            statement.setString(3, tenLoaiMon);
            statement.setString(4, maMon);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public MonAn getMonAntheoMaMon(String maMon) {
 
		PreparedStatement stmt = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT * FROM Mon m join LoaiMon lm on m.maLoaiMon =lm.maLoaiMon where maMon = ? ";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maMon);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String ma = rs.getString(1);
				String ten=  rs.getString(2);
				int donGia = rs.getInt(3);
				LoaiMon maloaimon = new LoaiMon(rs.getString(4),rs.getString(6));
				MonAn m = new MonAn(ma, ten, donGia, maloaimon);
				return m;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}


}

