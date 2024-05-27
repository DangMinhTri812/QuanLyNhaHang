package entity;

import java.util.Objects;

public class MonAn {
	
	private String maMon;
	private String tenMon;
	private int donGia;
	private LoaiMon loaiMon;
	
	public String getMaMon() {
		return maMon;
	}
	public void setMaMon(String maMon) {
		this.maMon = maMon;
	}
	public String getTenMon() {
		return tenMon;
	}
	public void setTenMon(String tenMon) {
		this.tenMon = tenMon;
	}
	public int getDonGia() {
		return donGia;
	}
	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}
	public LoaiMon getLoaiMon() {
		return loaiMon;
	}
	public void setLoaiMon(LoaiMon loaiMon) {
		this.loaiMon = loaiMon;
	}
	public MonAn(String maMon, String tenMon, int donGia, LoaiMon loaiMon) {
		this.maMon = maMon;
		this.tenMon = tenMon;
		this.donGia = donGia;
		this.loaiMon = loaiMon;
	}
	public MonAn(String maMon) {
		// TODO Auto-generated constructor stub
		this.maMon = maMon;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maMon);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MonAn other = (MonAn) obj;
		return Objects.equals(maMon, other.maMon);
	}
	
	
	
	
}