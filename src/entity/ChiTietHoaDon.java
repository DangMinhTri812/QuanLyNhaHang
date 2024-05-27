package entity;

public class ChiTietHoaDon {

    private HoaDon HoaDon;
    private Mon mon;
    private double thanhTien;


	public ChiTietHoaDon(entity.HoaDon hoaDon, Mon mon, double thanhTien) {
		super();
		HoaDon = hoaDon;
		this.mon = mon;
		this.thanhTien = thanhTien;
	}

	public HoaDon getHoaDon() {
		return HoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		HoaDon = hoaDon;
	}

	public Mon getMon() {
		return mon;
	}

	public void setMon(Mon mon) {
		this.mon = mon;
	}

	public double getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}

	@Override
	public String toString() {
		return "ChiTietHoaDon [HoaDon=" + HoaDon + ", mon=" + mon + ", thanhTien=" + thanhTien + "]";
	}



    
}

