package entity;

public class DatMon {
	private PhieuDatBan phieudatban;
	private KhuVuc khuVuc;
	private Phong phong;
	private Ban ban;
	private MonAn monAn;
	private LoaiMon loaiMon;
	private int soLuong;
	private int donGia;
	public PhieuDatBan getPhieudatban() {
		return phieudatban;
	}
	public void setPhieudatban(PhieuDatBan phieudatban) {
		this.phieudatban = phieudatban;
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
	public Ban getBan() {
		return ban;
	}
	public void setBan(Ban ban) {
		this.ban = ban;
	}
	public MonAn getMonAn() {
		return monAn;
	}
	public void setMonAn(MonAn monAn) {
		this.monAn = monAn;
	}
	public LoaiMon getLoaiMon() {
		return loaiMon;
	}
	public void setLoaiMon(LoaiMon loaiMon) {
		this.loaiMon = loaiMon;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public int getDonGia() {
		return donGia;
	}
	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}
	
	
	public DatMon(PhieuDatBan phieudatban, KhuVuc khuVuc, Phong phong, Ban ban, MonAn monAn, LoaiMon loaiMon, int soLuong, int donGia) {
	    this.phieudatban = phieudatban;
	    this.khuVuc = khuVuc;
	    this.phong = phong;
	    this.ban = ban;
	    this.monAn = monAn;
	    this.loaiMon = loaiMon;
	    this.soLuong = soLuong;
	    this.donGia = donGia;
	}

    
}
