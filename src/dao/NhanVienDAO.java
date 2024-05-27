package dao;

import connectDB.ConnectDB;
import entity.NhanVien;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class NhanVienDAO {
    //lấy danh sách nhân viên
    public ArrayList<NhanVien> layThongTin(){
        ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
        try{
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String SQL = "SELECT * FROM NhanVien";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()){
                String maNV = rs.getString(1).trim();
                String tenNV = rs.getString(2).trim();
                String phai = rs.getString(3).trim();
                int tuoi = rs.getInt(4);
                String soDienThoai = rs.getString(5).trim();
                NhanVien nv = new NhanVien(maNV,tenNV,phai,tuoi,soDienThoai);
                dsNhanVien.add(nv);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dsNhanVien;
    }
    public String layMaMoiNhat() {
	    String ma = null;
	    try {
	        Connection connection = ConnectDB.getInstance().getConnection();
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery("SELECT MAX(maNV) AS ma FROM NhanVien");
	        if (resultSet.next()) {
	            ma = resultSet.getString("ma");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return ma;
	}
    //thêm nhân viên
    public boolean themNhanVien(NhanVien nhanVien){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;

        String SQL = "INSERT INTO NhanVien VALUES (?,?,?,?,?)";
        int n = 0;
        try{
        	statement = con.prepareStatement(SQL);
        	statement.setString(1, nhanVien.getMaNV().trim());
        	statement.setString(2, nhanVien.getHoTenNV().trim());
        	statement.setString(3, nhanVien.getPhai().trim());
        	statement.setInt(4, nhanVien.getTuoi());
        	statement.setString(5, nhanVien.getSdt().trim());

  
            n = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return n>0;
    }
    // Xóa nhân viên
    public boolean xoaNhanVien(String maNV){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        int n=0;
        try{
            String SQL = "DELETE FROM NhanVien WHERE maNV = ?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,maNV);
            n = statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return n > 0;
    }
    //sửa nhân viên
    public boolean capNhatNhanVien(NhanVien nhanVien){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        int n = 0;
        try{
        	String SQL = "UPDATE NhanVien SET tenNV = ?, phai = ?, tuoi = ?, sdt = ? WHERE maNV = ?";
            statement = con.prepareStatement(SQL);
            statement.setString(1, nhanVien.getHoTenNV().trim());
            statement.setString(2, nhanVien.getPhai().trim());
            statement.setInt(3, nhanVien.getTuoi());
            statement.setString(4, nhanVien.getSdt().trim());
            statement.setString(5, nhanVien.getMaNV().trim());


            n = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return n>0;
    }
    public NhanVien TimNhanVien(String id) {
		NhanVien nv = new NhanVien();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {

			String sql = "select * from NhanVien where maNV=?";
			statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maNV = rs.getString(1).trim();
                String tenNV = rs.getString(2).trim();
                String phai = rs.getString(3).trim();
                int tuoi = rs.getInt(4);
                String soDienThoai = rs.getString(5).trim();
                nv = new NhanVien(maNV,tenNV,phai,tuoi,soDienThoai);
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();

		} 
		return nv;
	}
}

