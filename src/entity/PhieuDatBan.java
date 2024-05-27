package entity;

import java.time.LocalDate;
import java.util.ArrayList;

public class PhieuDatBan {
    private String maPhieu, ngayDat, gioDat;
    private int soLuongNguoi;
    private LocalDate ngayLap;
    private KhuVuc khuVuc;
    private Phong phong;
    private Ban tenBan;
    private KhachHang khachHang;
    private NhanVien nhanVien;
	public PhieuDatBan(String maPhieu, KhuVuc khuVuc, Phong phong, Ban tenBan, int soLuongNguoi, String ngayDat,
			LocalDate ngayLap, String gioDat, KhachHang khachHang, NhanVien nhanVien) {
		super();
		this.maPhieu = maPhieu;
		this.khuVuc = khuVuc;
		this.phong = phong;
		this.tenBan = tenBan;
		this.soLuongNguoi = soLuongNguoi;
		this.ngayDat = ngayDat;
		this.ngayLap = ngayLap;
		this.gioDat = gioDat;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
	}
	public PhieuDatBan(String maPhieu) {
		// TODO Auto-generated constructor stub
		this.maPhieu = maPhieu;
	}
	public String getMaPhieu() {
		return maPhieu;
	}
	public void setMaPhieu(String maPhieu) {
		this.maPhieu = maPhieu;
	}
	public String getNgayDat() {
		return ngayDat;
	}
	public void setNgayDat(String ngayDat) {
		this.ngayDat = ngayDat;
	}
	public String getGioDat() {
		return gioDat;
	}
	public void setGioDat(String gioDat) {
		this.gioDat = gioDat;
	}
	public int getSoLuongNguoi() {
		return soLuongNguoi;
	}
	public void setSoLuongNguoi(int soLuongNguoi) {
		this.soLuongNguoi = soLuongNguoi;
	}
	public LocalDate getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(LocalDate ngayLap) {
		this.ngayLap = ngayLap;
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
	public Ban getTenBan() {
		return tenBan;
	}
	public void setTenBan(Ban tenBan) {
		this.tenBan = tenBan;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	@Override
	public String toString() {
		return "PhieuDatBan [maPhieu=" + maPhieu + ", ngayDat=" + ngayDat + ", gioDat=" + gioDat + ", soLuongNguoi="
				+ soLuongNguoi + ", ngayLap=" + ngayLap + ", khuVuc=" + khuVuc + ", phong=" + phong + ", tenBan="
				+ tenBan + ", khachHang=" + khachHang + ", nhanVien=" + nhanVien + "]";
	}
    
}