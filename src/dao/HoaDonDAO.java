package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.Ban;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuVuc;
import entity.NhanVien;
import entity.Phong;

public class HoaDonDAO {
    public List<HoaDon> layThongTin() {
        List<HoaDon> dsHoaDon = new ArrayList<>();
        try {
        	ConnectDB.getInstance().connect();
        	Connection con = ConnectDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                 "SELECT hd.maHoaDon, hd.tongTien, hd.khuVuc, hd.phong, hd.banAn, " +
                 "nv.tenNV AS tenNhanVien, hd.ngayLap, kh.tenKH AS tenKhachHang, " +
                 "pdb.ngayDat " +
                 "FROM HoaDon hd " +
                 "JOIN NhanVien nv ON hd.maNV = nv.maNV " +
                 "JOIN KhachHang kh ON hd.maKH = kh.maKH " +
                 "JOIN PhieuDatBan pdb ON hd.maPhieuDatBan = pdb.maPhieuDatBan"
             );

            while (rs.next()) {
                   String maHD = rs.getString("maHoaDon");
                   Double tongTien =  rs.getDouble("tongTien");
                   KhuVuc khuVuc = new KhuVuc(rs.getString("khuVuc"));
                   Phong phong = new Phong(rs.getString("phong"));
                   Ban banAn = new Ban(rs.getString("banAn"));
                   NhanVien tenNV = new NhanVien(rs.getString("tenNhanVien"));
                   Date ngayLap = rs.getDate("ngayLap"); 
                   KhachHang tenKH = new KhachHang(rs.getString("tenKhachHang"));
                   Date ngayDat = rs.getDate("ngayDat");
                   HoaDon  hd = new HoaDon(maHD, tongTien, khuVuc, phong, banAn, tenNV, ngayLap, tenKH, ngayDat);
                
                dsHoaDon.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsHoaDon;
    }

}
