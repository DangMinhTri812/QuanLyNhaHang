package entity;

public class Ban {
	private String maBan;
	private String soBan;
	private int soGhe;
	private KhuVuc khuVuc;
	private Phong phong;
	public Ban(String maBan, String soBan, int soGhe, KhuVuc khuVuc, Phong phong) {
		this.maBan = maBan;
		this.soBan = soBan;
		this.soGhe = soGhe;
		this.khuVuc = khuVuc;
		this.phong = phong;
	}
	 public Ban(String maBan, String soBan, int soGhe, Phong phong) {
	        this.maBan = maBan;
	        this.soBan = String.valueOf(soBan);
	        this.soGhe = soGhe;
	        this.phong = phong;
	    }
	public Ban(String maBan) {
		this.maBan = maBan;
	}
	public Ban(String maBan, String soBan) {
		this.maBan = maBan;
		this.soBan = soBan;
	}
	public Ban(String maBan, String soBan, int soGhe) {
		// TODO Auto-generated constructor stub
		this.maBan = maBan;
        this.soBan = soBan;
        this.soGhe = soGhe;
	}
	public String getMaBan() {
		return maBan;
	}
	public void setMaBan(String maBan) {
		this.maBan = maBan;
	}
	public String getSoBan() {
		return soBan;
	}
	public void setSoBan(String soBan) {
		this.soBan = soBan;
	}
	public int getSoGhe() {
		return soGhe;
	}
	public void setSoGhe(int soGhe) {
		this.soGhe = soGhe;
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
	@Override
	public String toString() {
		return "Ban [maBan=" + maBan + ", soBan=" + soBan + ", soGhe=" + soGhe + ", khuVuc=" + khuVuc + ", phong="
				+ phong + "]";
	}
	
}
