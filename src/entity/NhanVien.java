package entity;

public class NhanVien {
    private String maNV, hoTenNV, phai;
    private int tuoi;
    private String sdt;

 
    public NhanVien(String maNV, String hoTenNV, String phai, int tuoi, String sdt) {
		super();
		this.maNV = maNV;
		this.hoTenNV = hoTenNV;
		this.phai = phai;
		this.tuoi = tuoi;
		this.sdt = sdt;
	}

	public NhanVien(String maNV) {
		this.maNV = maNV;
	}

	public NhanVien() {
		// TODO Auto-generated constructor stub
		this.maNV = maNV;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getHoTenNV() {
		return hoTenNV;
	}

	public void setHoTenNV(String hoTenNV) {
		this.hoTenNV = hoTenNV;
	}

	public String getPhai() {
		return phai;
	}

	public void setPhai(String phai) {
		this.phai = phai;
	}

	public int getTuoi() {
		return tuoi;
	}

	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

    @Override
    public String toString() {
        return "NhanVien [maNV=" + maNV + ", hoTenNV=" + hoTenNV + ", phai=" + phai + ", tuoi=" + tuoi + ", sdt=" + sdt + "]";
    }

}