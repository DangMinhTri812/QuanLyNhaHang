package dao;

import connectDB.ConnectDB;
import entity.KhachHang;

import java.sql.*;
import java.util.ArrayList;

public class KhachHangDAO {
    //lấy danh sách khách hàng
    public ArrayList<KhachHang> layThongTin(){
        ArrayList<KhachHang> dsKH = new ArrayList<KhachHang>();
        try{
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String SQL = "SELECT * FROM KhachHang";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()){
                String maKH = rs.getString(1);
                String tenKH = rs.getString(2);
                String diaChi = rs.getString(5);
                String soDienThoai = rs.getString(4);
                String phai = rs.getString(3);
                KhachHang kh = new KhachHang(maKH,tenKH,phai,soDienThoai,diaChi);
                dsKH.add(kh);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dsKH;
    }
    public String layMaMoiNhat() {
	    String ma = null;
	    try {
	        Connection connection = ConnectDB.getInstance().getConnection();
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery("SELECT MAX(maKH) AS ma FROM KhachHang");
	        if (resultSet.next()) {
	            ma = resultSet.getString("ma");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return ma;
	}
    //thêm Khách hàng
    public boolean themKhachHang(KhachHang khachHang){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;

        String SQL = "INSERT INTO KhachHang VALUES (?,?,?,?,?)";
        int n = 0;
        try{
            statement = con.prepareStatement(SQL);
            statement.setString(1,khachHang.getMaKH());
            statement.setString(2,khachHang.getTenKH());
            statement.setString(3,khachHang.getPhai().trim());
            statement.setString(4,khachHang.getSdt());
            statement.setString(5,khachHang.getDiaChi());
            n = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return n>0;
    }
    // Xóa khach hàng
    public boolean xoaKhachHang(String maKH){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        int n=0;
        try{
            String SQL = "DELETE FROM KhachHang WHERE maKH = ?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,maKH);
            n = statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return n > 0;
    }
    //sửa khách hàng
    public boolean capNhatKhachHang(KhachHang khachHang){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        int n = 0;
        try{
        	String SQL = "UPDATE KhachHang SET tenKH = ?, phai = ?, sdt = ?, diaChi = ? WHERE maKH = ?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,khachHang.getTenKH());
            statement.setString(2,khachHang.getPhai().trim());
            statement.setString(3,khachHang.getSdt());
            statement.setString(4,khachHang.getDiaChi());
            statement.setString(5,khachHang.getMaKH());
            n = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return n>0;
    }
    public KhachHang TimKhachHang(String id) {
		KhachHang kh = new KhachHang();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {

			String sql = "select * from KhachHang where maKH=?";
			statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {

				String maKH = rs.getString(1);
                String tenKH = rs.getString(2);
                String diaChi = rs.getString(5);
                String soDienThoai = rs.getString(4);
                String phai = rs.getString(3);
                kh = new KhachHang(maKH,tenKH,phai,soDienThoai,diaChi);

			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();

		} 
		return kh;
	}




}

