package dao;

import connectDB.ConnectDB;
import entity.Ban;
import entity.KhachHang;
import entity.KhuVuc;
import entity.NhanVien;
import entity.PhieuDatBan;
import entity.Phong;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class PhieuDatBanDAO {
	//lấy danh sách phiếu
	public static ArrayList<PhieuDatBan> layThongTin(){
		ArrayList<PhieuDatBan> dsPhieuDB = new ArrayList<PhieuDatBan>();
		try{
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String SQL = "SELECT phieu.maPhieuDatBan, k.tenKhuVuc, p.tenPhong, b.soBan, phieu.soNguoi, phieu.ngayDat, phieu.ngayLap, phieu.gioDat, kh.tenKH, nv.tenNV " +
					"FROM PhieuDatBan phieu " +
					"INNER JOIN KhuVuc k ON phieu.maKhuVuc = k.maKhuVuc " +
					"INNER JOIN KhachHang kh ON phieu.maKH = kh.maKH " +
					"INNER JOIN NhanVien nv ON phieu.maNV = nv.maNV " + 
					"INNER JOIN Ban b ON phieu.maBan = b.maBan " +
					"INNER JOIN Phong p ON phieu.maPhong = p.maPhong";

			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(SQL);
			while (rs.next()){
				String maPhieu = rs.getString(1);
				String tenKhuVuc = rs.getString(2);
				String tenPhong = rs.getString(3).trim();
				String soBan = rs.getString(4).trim();
				int soLuongNguoi = rs.getInt(5);
				String ngayDat = rs.getString(6);
				java.sql.Date ngayLapDate = rs.getDate(7);
				LocalDate ngayLap = ngayLapDate.toLocalDate();
				String gioDat = rs.getString(8).trim();
				String tenKH = rs.getString(9);
				String tenNV = rs.getString(10);    
				KhuVuc khuVuc = new KhuVuc(tenKhuVuc);
				Ban ban = new Ban(soBan);
				NhanVien nv = new NhanVien(tenNV);
				KhachHang kh = new KhachHang(tenKH);
				Phong phong = new Phong(tenPhong);

				PhieuDatBan p = new PhieuDatBan(maPhieu, khuVuc, phong, ban, soLuongNguoi, ngayDat, ngayLap, gioDat, kh, nv);
				dsPhieuDB.add(p);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return dsPhieuDB;
	}

	public String layMaMoiNhat() {
		String ma = null;
		try {
			Connection connection = ConnectDB.getInstance().getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT MAX(maPhieuDatBan) AS ma FROM PhieuDatBan");
			if (resultSet.next()) {
				ma = resultSet.getString("ma");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ma;
	}
	//thêm phiếu
	public boolean themPhieu(PhieuDatBan phieu, String ngayDat) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;

		String SQL = "INSERT INTO PhieuDatBan (maPhieuDatBan, maKhuVuc, maPhong, maBan, soNguoi, ngayDat, ngayLap, gioDat, maKH, maNV) VALUES (?,?,?,?,?,?,?,?,?,?)";
		int n = 0;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = formatter.parse(ngayDat);
			statement = con.prepareStatement(SQL);
			statement.setString(1, phieu.getMaPhieu());
			statement.setString(2, phieu.getKhuVuc().getMaKhuVuc());
			if (phieu.getPhong() != null) {
				statement.setString(3, phieu.getPhong().getMaPhong());
			} else {
				statement.setNull(3, Types.VARCHAR);
			}
			statement.setString(4, phieu.getTenBan().getMaBan().trim());
			statement.setInt(5, phieu.getSoLuongNguoi());
			statement.setDate(6, new java.sql.Date(date.getTime()));
			statement.setDate(7, java.sql.Date.valueOf(phieu.getNgayLap()));
			statement.setString(8, phieu.getGioDat().trim());
			statement.setString(9, phieu.getKhachHang().getMaKH());
			statement.setString(10, phieu.getNhanVien().getMaNV());
			n = statement.executeUpdate();
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}  
		return n > 0;
	}

	public boolean xoaPhieu(String maPhieu){
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement =null;
		int n=0;
		try{
			String SQL = "DELETE FROM PhieuDatBan WHERE maPhieuDatBan = ?";
			statement = con.prepareStatement(SQL);
			statement.setString(1,maPhieu);
			n = statement.executeUpdate();

		}catch (SQLException e){
			e.printStackTrace();
		}
		return n > 0;
	}
	//sửa phiếu
	public boolean capNhatPhieu(String maPhieu, String khuVuc, String phong, String tenBan, int soLuong, String ngayDat, String gioDat, String khachHang, String nhanVien) throws ParseException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = formatter.parse(ngayDat);
			String SQL = "UPDATE PhieuDatBan " +
					"SET maKhuVuc = (SELECT maKhuVuc FROM KhuVuc WHERE tenKhuVuc = ?), " +
					"maPhong = (SELECT maPhong FROM Phong WHERE tenPhong = ?), " +
					"maBan = (SELECT maBan FROM Ban WHERE soBan = ?), " +
					"soNguoi = ?, ngayDat = ?, gioDat = ?, " +
					"maKH = (SELECT maKH FROM KhachHang WHERE tenKH = ?), " +
					"maNV = (SELECT maNV FROM NhanVien WHERE tenNV = ?) " +
					"WHERE maPhieuDatBan = ?";

			statement = con.prepareStatement(SQL);
			statement.setString(1, khuVuc);
			statement.setString(2, phong);
			statement.setString(3, tenBan);
			statement.setInt(4, soLuong);
			statement.setDate(5, new java.sql.Date(date.getTime()));
			statement.setString(6, gioDat);
			statement.setString(7, khachHang);
			statement.setString(8, nhanVien);
			statement.setString(9, maPhieu);

			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public PhieuDatBan getPhieuDatBantheoMaPhieuDatBan(String maPhieu) {
		PreparedStatement stmt = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT * FROM PhieuDatBan phieu "
					+ " join Ban b on phieu.maBan = b.maBan "
					+ " join Phong p on phieu.maPhong = p.maPhong "
					+ " join KhuVuc kv on phieu.maKhuVuc = kv.maKhuVuc "
					+ "  WHERE maPhieuDatBan = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maPhieu);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String ma = rs.getString("maPhieuDatBan");
				KhuVuc khuVuc = new KhuVuc(rs.getString("maKhuVuc"),rs.getString("tenKhuVuc"));
				Phong phong = new Phong(rs.getString("maPhong"),rs.getString("tenPhong"));
				Ban tenBan = new Ban(rs.getString("maBan"),rs.getString("soBan"));
				int soLuongNguoi = rs.getInt("soNguoi");
				String ngayDat = rs.getString("ngayDat");
				LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();
				String gioDat = rs.getString("gioDat");
				KhachHang khachHang = new KhachHang(rs.getString("maKH")); 
				NhanVien nhanVien = new NhanVien(rs.getString("maNV")); 

				PhieuDatBan phieuDatBan = new PhieuDatBan(ma, khuVuc, phong, tenBan, soLuongNguoi, ngayDat, ngayLap, gioDat, khachHang, nhanVien);
				return phieuDatBan;
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


