package dao;
import entity.Ban;
import entity.DatMon;
import entity.KhuVuc;
import entity.LoaiMon;
import entity.MonAn;
import entity.PhieuDatBan;
import entity.Phong;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;

// Lấy danh sách món ăn
public class DatMonDAO {
	private static MonAnDAO monandao= new MonAnDAO();
	private static PhieuDatBanDAO phieudatbandao = new PhieuDatBanDAO();



	// Phương thức để lấy thông tin về đặt món từ cơ sở dữ liệu
	public static ArrayList<DatMon> layThongTin() {
	    ArrayList<DatMon> danhSachDatMon = new ArrayList<>();
	    try {
	        ConnectDB.getInstance().connect(); // Kết nối cơ sở dữ liệu
	        Connection con = ConnectDB.getConnection();
	        String SQL = "SELECT * FROM DatMon"; // Truy vấn SQL để lấy thông tin đặt món
	        Statement statement = con.createStatement();
	        ResultSet rs = statement.executeQuery(SQL);
	        while (rs.next()) {
	            String maMon = rs.getString("maMon");
	            String maPhieuDatBan = rs.getString("maPhieuDatBan");
	            String maKhuVuc = rs.getString("maKhuVuc");
	            String maBan = rs.getString("maBan");
	            String maLoaiMon = rs.getString("maLoaiMon");
	            String maPhong = rs.getString("maPhong");
	            int soLuong = rs.getInt("soLuong");
	            int donGia = rs.getInt("donGia");
	            PhieuDatBan phieuDatBan = new PhieuDatBan(maPhieuDatBan);
	            KhuVuc khuVuc = new KhuVuc(maKhuVuc);
	            Phong phong = new Phong(maPhong); // Truyền mã phòng vào constructor
	            Ban ban = new Ban(maBan);
	            MonAn monAn = new MonAn(maMon); // Truyền mã món vào constructor
	            LoaiMon loaiMon = new LoaiMon(maLoaiMon);
	            
	            DatMon datMon = new DatMon(phieuDatBan, khuVuc, phong, ban, monAn, loaiMon, soLuong, donGia);
	            danhSachDatMon.add(datMon);
	        }
	        con.close(); 
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return danhSachDatMon;
	}





	public static ArrayList<DatMon> layDanhSachDatMonTheoPhieu(String ma) {
	    ArrayList<DatMon> dsMon = new ArrayList<>();
	    try (Connection con = ConnectDB.getConnection();
	         PreparedStatement statement = con.prepareStatement("SELECT * FROM DatMon WHERE maPhieuDatBan = ?")) {
	        statement.setString(1, ma);
	        try (ResultSet rs = statement.executeQuery()) {
	            while (rs.next()) {
	                String maMon = rs.getString("maMon");
	                String maPhieuDatBan = rs.getString("maPhieuDatBan");
	                int soLuong = rs.getInt("soLuong");
	                
	                MonAn mon = monandao.getMonAntheoMaMon(maMon);
	                PhieuDatBan db = phieudatbandao.getPhieuDatBantheoMaPhieuDatBan(maPhieuDatBan);
	                
	                DatMon dm = new DatMon(soLuong, mon, db, null, null, null, null);
	                dsMon.add(dm);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsMon;
	}


	public boolean themDatMon(DatMon datMon) {
	    Connection connection = null;
	    PreparedStatement statement = null;
	    boolean success = false;
	    try {
	       
	        connection = ConnectDB.getConnection();
	        String sql = "INSERT INTO DatMon (maPhieuDatBan, maKhuVuc, maPhong, maBan, maMon, maLoaiMon, donGia, soLuong) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	        statement = connection.prepareStatement(sql);
	        statement.setString(1, datMon.getPhieudatban().getMaPhieu());
	        statement.setString(2, datMon.getKhuVuc().getMaKhuVuc());
	        statement.setString(3, datMon.getPhong().getMaPhong());
	        statement.setString(4, datMon.getBan().getMaBan());
	        statement.setString(5, datMon.getMonAn().getMaMon());
	        statement.setString(6, datMon.getLoaiMon().getMaLoaiMon());
	        statement.setInt(7, datMon.getMonAn().getDonGia());
	        statement.setInt(8, datMon.getSoLuong());
	        int rowsInserted = statement.executeUpdate();

	        if (rowsInserted > 0) {
	            success = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (statement != null) {
	                statement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return success;
	}

}



