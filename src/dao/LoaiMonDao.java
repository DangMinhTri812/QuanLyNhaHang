package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.LoaiMon;

public class LoaiMonDao {
	public static List<LoaiMon> getAllDsLoaiMon() {
        List<LoaiMon> dsLoaiMon = new ArrayList<>();
        String sql = "SELECT * FROM LoaiMon";

        try (Connection con = ConnectDB.getInstance().getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String maLoaiMon = rs.getString("maLoaiMon");
                String tenLoaiMon = rs.getString("tenLoaiMon");
                LoaiMon loaiMon = new LoaiMon(maLoaiMon, tenLoaiMon);
                dsLoaiMon.add(loaiMon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dsLoaiMon;
    }

	public LoaiMon timTenLoaiMon(String tenLoaiMon) {
        List<LoaiMon> dsLoaiMon = getAllDsLoaiMon(); // Lấy tất cả thông tin loại món
        for (LoaiMon lm : dsLoaiMon) {
            if (tenLoaiMon.equalsIgnoreCase(lm.getTenLoaiMon())) {
                return lm;
            }
        }
        return null;
    }



	
}
