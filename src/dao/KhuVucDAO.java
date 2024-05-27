package dao;

import connectDB.ConnectDB;
import entity.KhuVuc;

import java.sql.*;
import java.util.ArrayList;

public class KhuVucDAO {

    //lấy danh sách khu vực
    public static ArrayList<KhuVuc> layThongTin(){
        ArrayList<KhuVuc> dsKhuVuc = new ArrayList<KhuVuc>();
        try{
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String SQL = "SELECT * FROM KhuVuc";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()){
                String maKV = rs.getString(1);
                String tenKV = rs.getString(2);
                KhuVuc kv = new KhuVuc(maKV,tenKV);
                dsKhuVuc.add(kv);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dsKhuVuc;
    }
    public String layMaMoiNhat() {
	    String ma = null;
	    try {
	        Connection connection = ConnectDB.getInstance().getConnection();
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery("SELECT MAX(maKhuVuc) AS ma FROM KhuVuc");
	        if (resultSet.next()) {
	            ma = resultSet.getString("ma");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return ma;
	}
    //thêm khu vực
    public boolean themKhuVuc(KhuVuc khuVuc){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;

        String SQL = "INSERT INTO KhuVuc VALUES (?,?)";
        int n = 0;
        try{
            statement = con.prepareStatement(SQL);
            statement.setString(1,khuVuc.getMaKhuVuc());
            statement.setString(2,khuVuc.getTenKhuVuc());

              n = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return n>0;
    }
    // Xóa khu vực
    public boolean xoaKhuVuc(String maKhuVuc){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        int n=0;
        try{
            String SQL = "DELETE FROM KhuVuc WHERE maKhuVuc = ?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,maKhuVuc);
            n = statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return n > 0;
    }
    //sửa khu vực
    public boolean capNhatKhuVuc(KhuVuc khuVuc) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;
        try {
            String SQL = "UPDATE KhuVuc SET tenKhuVuc = ? WHERE maKhuVuc = ?";
            statement = con.prepareStatement(SQL);
            statement.setString(1, khuVuc.getTenKhuVuc());
            statement.setString(2, khuVuc.getMaKhuVuc());
            n = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public KhuVuc timKhuVuc(String maKhuVuc) {
        KhuVuc khuVuc = null;
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String SQL = "SELECT * FROM KhuVuc WHERE maKhuVuc = ?";
            PreparedStatement statement = con.prepareStatement(SQL);
            statement.setString(1, maKhuVuc);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String tenKhuVuc = rs.getString("tenKhuVuc");
                khuVuc = new KhuVuc(maKhuVuc, tenKhuVuc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khuVuc;
    }

}


