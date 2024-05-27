package dao;

import connectDB.ConnectDB;
import entity.KhuVuc;
import entity.Phong;

import java.sql.*;
import java.util.ArrayList;

public class PhongDAO {
	 public static ArrayList<Phong> layThongTin() {
	        ArrayList<Phong> dsPhong = new ArrayList<>();
	        try {
	            Connection con = ConnectDB.getInstance().getConnection();
	            String SQL = "SELECT p.maPhong, p.tenPhong, k.tenKhuVuc, " +
	                    "(SELECT SUM(b.soGhe) FROM Ban b WHERE b.maPhong = p.maPhong) AS soGhe " +
	                    "FROM Phong p " +
	                    "INNER JOIN KhuVuc k ON p.maKhuVuc = k.maKhuVuc";

	            Statement statement = con.createStatement();
	            ResultSet rs = statement.executeQuery(SQL);
	            while (rs.next()) {
	                String maPhong = rs.getString(1).trim();
	                String tenPhong = rs.getString(2).trim();
	                String tenKhuVuc = rs.getString(3).trim();
	                int soGhe = rs.getInt(4);
	                KhuVuc khuVuc = new KhuVuc(tenKhuVuc); 
	                Phong p = new Phong(maPhong, tenPhong, khuVuc, soGhe);
	                dsPhong.add(p);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return dsPhong;
	 }
	 public ArrayList<Phong> layThongTinPhongTheoKhuVuc(String tenKhuVuc) {
		    ArrayList<Phong> dsPhong = new ArrayList<>();
		    Connection con = null;
		    PreparedStatement statement = null;
		    try {
		        con = ConnectDB.getInstance().getConnection();
		        String sql = "SELECT p.maPhong, p.tenPhong, k.tenKhuVuc, p.soGhe " +
		                     "FROM Phong p " +
		                     "INNER JOIN KhuVuc k ON p.maKhuVuc = k.maKhuVuc " +
		                     "WHERE k.tenKhuVuc = ?";
		        statement = con.prepareStatement(sql);
		        statement.setString(1, tenKhuVuc);
		        ResultSet rs = statement.executeQuery();
		        while (rs.next()) {
		            String maPhong = rs.getString(1).trim();
		            String tenPhong = rs.getString(2).trim();
		            String tenKV = rs.getString(3).trim();
		            int soGhe = rs.getInt(4);
		            KhuVuc khuVuc = new KhuVuc(tenKV);
		            Phong p = new Phong(maPhong, tenPhong, khuVuc, soGhe);
		            dsPhong.add(p);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } 
		    return dsPhong;
		}

	 public Phong layThongTinBangMaPhong(String maPhong) {
		    Phong phong = null;
		    try {
		        Connection con = ConnectDB.getInstance().getConnection();
		        String SQL = "SELECT p.maPhong, p.tenPhong, k.tenKhuVuc, p.soGhe " +
		                "FROM Phong p " +
		                "INNER JOIN KhuVuc k ON p.maKhuVuc = k.maKhuVuc " +
		                "WHERE p.maPhong = ?";
		        PreparedStatement statement = con.prepareStatement(SQL);
		        statement.setString(1, maPhong);
		        ResultSet rs = statement.executeQuery();
		        if (rs.next()) {
		            String tenPhong = rs.getString(2).trim();
		            String tenKhuVuc = rs.getString(3).trim();
		            int soGhe = rs.getInt(4);
		            KhuVuc khuVuc = new KhuVuc(tenKhuVuc);
		            phong = new Phong(maPhong, tenPhong, khuVuc,soGhe);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return phong;
		}


	 public String layMaMoiNhat() {
		    String ma = null;
		    try {
		        Connection connection = ConnectDB.getInstance().getConnection();
		        Statement statement = connection.createStatement();
		        ResultSet resultSet = statement.executeQuery("SELECT MAX(maPhong) AS ma FROM Phong");
		        if (resultSet.next()) {
		            ma = resultSet.getString("ma");
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return ma;
		}
    public boolean themPhong(Phong phong) {
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String SQL = "INSERT INTO Phong (maPhong, tenPhong, maKhuVuc) VALUES (?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(SQL);
            statement.setString(1, phong.getMaPhong().trim());
            statement.setString(2, phong.getTenPhong().trim());
            statement.setString(3, phong.getKhuVuc().getMaKhuVuc());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaPhong(String maPhong) {
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String SQL = "DELETE FROM Phong WHERE maPhong = ?";
            PreparedStatement statement = con.prepareStatement(SQL);
            statement.setString(1, maPhong);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void capNhatThongTinPhong(String maPhong, String tenPhong, String tenKhuVuc) throws SQLException {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = ConnectDB.getInstance().getConnection();
            String SQL = "UPDATE Phong " +
                    "SET tenPhong = ?, maKhuVuc = (SELECT maKhuVuc FROM KhuVuc WHERE tenKhuVuc = ?) " +
                    "WHERE maPhong = ?";

            statement = con.prepareStatement(SQL);
            statement.setString(1, tenPhong);
            statement.setString(2, tenKhuVuc);
            statement.setString(3, maPhong);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
        } 
    }


}
