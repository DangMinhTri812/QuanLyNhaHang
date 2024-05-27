package entity;

import java.util.Date;

public class HoaDon {

    private String maHoaDon;
    private double tongTien;
    private KhuVuc khuVuc;
    private Phong phong;
    private Ban banAn;
    private NhanVien nhanVien;
    private Date ngayLap;
    private KhachHang khachHang;
    private Date ngayDat;


	public HoaDon(String maHoaDon, double tongTien, KhuVuc khuVuc, Phong phong, Ban banAn, NhanVien nhanVien,
			Date ngayLap, KhachHang khachHang, Date ngayDat) {
		super();
		this.maHoaDon = maHoaDon;
		this.tongTien = tongTien;
		this.khuVuc = khuVuc;
		this.phong = phong;
		this.banAn = banAn;
		this.nhanVien = nhanVien;
		this.ngayLap = ngayLap;
		this.khachHang = khachHang;
		this.ngayDat = ngayDat;
	}

	public HoaDon(String mahd) {
		this.maHoaDon=mahd;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}



	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}



	public double getTongTien() {
		return tongTien;
	}



	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}



	public KhuVuc getKhuVuc() {
		return khuVuc;
	}



	public void setKhuVuc(KhuVuc khuVuc) {
		this.khuVuc = khuVuc;
	}



	public Phong getPhong() {
		return phong;
	}



	public void setPhong(Phong phong) {
		this.phong = phong;
	}



	public Ban getBanAn() {
		return banAn;
	}



	public void setBanAn(Ban banAn) {
		this.banAn = banAn;
	}



	public NhanVien getNhanVien() {
		return nhanVien;
	}



	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}



	public Date getNgayLap() {
		return ngayLap;
	}



	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}



	public KhachHang getKhachHang() {
		return khachHang;
	}



	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}



	public Date getNgayDat() {
		return ngayDat;
	}



	public void setNgayDat(Date ngayDat) {
		this.ngayDat = ngayDat;
	}



	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", tongTien=" + tongTien + ", khuVuc=" + khuVuc + ", phong=" + phong
				+ ", banAn=" + banAn + ", nhanVien=" + nhanVien + ", ngayLap=" + ngayLap + ", khachHang=" + khachHang
				+ ", ngayDat=" + ngayDat + "]";
	}

    
}

