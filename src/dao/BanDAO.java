package dao;

import connectDB.ConnectDB;
import entity.Ban;
import entity.KhuVuc;
import entity.Phong;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BanDAO {
    //lấy danh sách Bàn
	public static ArrayList<Ban> layThongTin() {
	    ArrayList<Ban> dsBan = new ArrayList<Ban>();
	    try {
	        ConnectDB.getInstance().connect();
	        Connection con = ConnectDB.getConnection();
	        String SQL = "SELECT b.maBan, b.soBan, b.soGhe, k.tenKhuVuc, p.tenPhong " +
	                    "FROM Ban b " +
	                    "INNER JOIN KhuVuc k ON b.maKhuVuc = k.maKhuVuc " +
	                    "LEFT JOIN Phong p ON b.maPhong = p.maPhong";       
	        Statement statement = con.createStatement();
	        ResultSet rs = statement.executeQuery(SQL);
	        while (rs.next()) {
	            String maBan = rs.getString(1);
	            String soBan = rs.getString(2);
	            int soGhe = rs.getInt(3);
	            String tenKhuVuc = rs.getString(4);
	            String tenPhong = rs.getString(5);
	            KhuVuc khuVuc = new KhuVuc(tenKhuVuc);
	            Phong phong = new Phong(tenPhong);
	            Ban b = new Ban(maBan, soBan, soGhe, khuVuc, phong);
	            dsBan.add(b);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsBan;
	}

	public ArrayList<Ban> layThongTinTheoKhuVuc(String tenKhuVuc) {
	    ArrayList<Ban> dsBan = new ArrayList<>();
	    try {
	        Connection con = ConnectDB.getInstance().getConnection();
	        String SQL = "SELECT b.maBan, b.soBan, b.soGhe, p.maPhong " +
                    "FROM Ban b " +
                    "INNER JOIN Phong p ON b.maPhong = p.maPhong " +
                    "WHERE p.maKhuVuc = ?";
	        PreparedStatement statement = con.prepareStatement(SQL);
	        statement.setString(1, tenKhuVuc);
	        ResultSet rs = statement.executeQuery();
	        while (rs.next()) {
	            String maBan = rs.getString(1).trim();
	            String soBan = rs.getString(2);
	            int soGhe = rs.getInt(3);
	            String maPhong = rs.getString(4);
	            Phong phong = new PhongDAO().layThongTinBangMaPhong(maPhong.trim());
	            Ban ban = new Ban(maBan, soBan, soGhe, phong);
	            dsBan.add(ban);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsBan;
	}


	public ArrayList<Ban> layThongTinTheoPhong(String tenPhong, String tenKhuVuc) {
	    ArrayList<Ban> dsBan = new ArrayList<>();
	    try {
	        Connection con = ConnectDB.getInstance().getConnection();
	        String SQL = "SELECT b.maBan, b.soBan, b.soGhe, b.maKhuVuc, b.maPhong " +
	                     "FROM Ban b " +
	                     "LEFT JOIN Phong p ON b.maPhong = p.maPhong " +
	                     "LEFT JOIN KhuVuc k ON b.maKhuVuc = k.maKhuVuc " +
	                     "WHERE (p.tenPhong = ? OR p.tenPhong IS NULL) AND k.tenKhuVuc = ? ";
	        PreparedStatement statement = con.prepareStatement(SQL);
	        statement.setString(1, tenPhong);
	        statement.setString(2, tenKhuVuc);
	        ResultSet rs = statement.executeQuery();
	        while (rs.next()) {
	            String maBan = rs.getString(1).trim();
	            String soBan = rs.getString(2);
	            int soGhe = rs.getInt(3);
	            String maKhuVuc = rs.getString(4);
	            String maPhong = rs.getString(5);
	            Phong phong = new PhongDAO().layThongTinBangMaPhong(maPhong);
	            KhuVuc khuVuc = new KhuVucDAO().timKhuVuc(maKhuVuc);
	            Ban ban = new Ban(maBan, soBan, soGhe, khuVuc, phong);
	            dsBan.add(ban);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsBan;
	}

	// thêm Bàn
	public boolean themBan(Ban ban) {
	    ConnectDB.getInstance();
	    Connection con = ConnectDB.getConnection();
	    PreparedStatement statement = null;
	    
	    String SQL;
	    int n = 0;
	    try {
	        if (ban.getPhong() == null) {
	            SQL = "insert into Ban (maBan, soBan, soGhe, maKhuVuc, maPhong) values (?, ?, ?, ?, NULL)";
	            statement = con.prepareStatement(SQL);
	            statement.setString(1, ban.getMaBan());
	            statement.setString(2, ban.getSoBan());
	            statement.setInt(3, ban.getSoGhe());
	            statement.setString(4, ban.getKhuVuc().getMaKhuVuc());
	        } else {
	            SQL = "insert into Ban (maBan, soBan, soGhe, maKhuVuc, maPhong) values (?, ?, ?, ?, ?)";
	            statement = con.prepareStatement(SQL);
	            statement.setString(1, ban.getMaBan());
	            statement.setString(2, ban.getSoBan());
	            statement.setInt(3, ban.getSoGhe());
	            statement.setString(4, ban.getKhuVuc().getMaKhuVuc());
	            statement.setString(5, ban.getPhong().getMaPhong());
	        }

	        n = statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return n > 0;
	}


	public String layMaBanMoiNhat() {
	    String ma = null;
	    try {
	        Connection connection = ConnectDB.getInstance().getConnection();
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery("SELECT MAX(maBan) AS ma FROM Ban");
	        if (resultSet.next()) {
	            ma = resultSet.getString("ma");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return ma;
	}
	
    // Xóa khu vực
    public boolean xoaBan(String maBan){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        int n=0;
        try{
            String SQL = "DELETE FROM Ban WHERE maBan = ?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,maBan);
            n = statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return n > 0;
    }
    //sửa Bàn
    public void capNhatThongTinBan(String maBan, String soBan, int soGhe, String tenKhuVuc, String tenPhong) throws SQLException {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = ConnectDB.getInstance().getConnection();
            String SQL = "UPDATE Ban " +
                    "SET soBan = ?, soGhe = ?, maKhuVuc = (SELECT maKhuVuc FROM KhuVuc WHERE tenKhuVuc = ?), maPhong = (SELECT maPhong FROM Phong WHERE tenPhong = ?) " +
                    "WHERE maBan = ?";

            statement = con.prepareStatement(SQL);
            statement.setString(1, soBan); 
            statement.setInt(2, soGhe); 
            statement.setString(3, tenKhuVuc);
            statement.setString(4, tenPhong);
            statement.setString(5, maBan);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
        } 
    }


}



