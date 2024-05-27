package entity;

public class KhachHang {
	private String maKH;
	private String tenKH;
	private String sdt;
	private String diaChi;
	private String phai;
	public KhachHang(String maKH, String tenKH, String phai, String sdt, String diaChi) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.sdt = sdt;
		this.diaChi = diaChi;
		this.phai = phai;
	}
	public KhachHang(String maKH) {
		// TODO Auto-generated constructor stub
		this.maKH = maKH;
	}
	public KhachHang() {
		// TODO Auto-generated constructor stub
		this.maKH = maKH;
	}
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getPhai() {
		return phai;
	}
	public void setPhai(String phai) {
		this.phai = phai;
	}
	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", phai=" + phai + ", sdt=" + sdt + ", diaChi=" + diaChi
				+ "]";
	}


}
