package entity;

public class LoaiMon {
	private String maLoaiMon;
	private String tenLoaiMon;

	public String getMaLoaiMon() {
		return maLoaiMon;
	}
	public void setMaLoaiMon(String maLoaiMon) {
		this.maLoaiMon = maLoaiMon;
	}
	public String getTenLoaiMon() {
		return tenLoaiMon;
	}
	public void setTenLoaiMon(String tenLoaiMon) {
		this.tenLoaiMon = tenLoaiMon;
	}
	
	
	
	public LoaiMon(String maLoaiMon, String tenLoaiMon) {

		this.maLoaiMon = maLoaiMon;
		this.tenLoaiMon = tenLoaiMon;
	}
	public LoaiMon(String maLoaiMon) {
		this.maLoaiMon = maLoaiMon;

	}
	 @Override
	    public String toString() {
	        return this.maLoaiMon;
	    }}


