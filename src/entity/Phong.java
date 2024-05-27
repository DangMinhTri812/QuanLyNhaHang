package entity;

import java.util.List;

public class Phong {
    private String maPhong;
    private String tenPhong;
    private KhuVuc khuVuc;
    private int soGhe;


	public Phong(String maPhong, String tenPhong, KhuVuc khuVuc, int soGhe) {
        super();
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.khuVuc = khuVuc;
        this.soGhe = soGhe;
    }

    public Phong(String maPhong) {
        this.maPhong = maPhong;
    }
    public Phong(String maPhong,String tenPhong) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
    }

    public Phong() {
		// TODO Auto-generated constructor stub
	}

	public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public KhuVuc getKhuVuc() {
        return khuVuc;
    }

    public void setKhuVuc(KhuVuc khuVuc) {
        this.khuVuc = khuVuc;
    }

    public int getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }

    public void tongSoGheTuBan(List<Ban> banList) {
        int sum = 0;
        for (Ban ban : banList) {
            sum += ban.getSoGhe();
        }
        this.soGhe = sum;
    }

    @Override
    public String toString() {
        return "Phong [maPhong=" + maPhong + ", tenPhong=" + tenPhong + ", khuVuc=" + khuVuc + ", soGhe=" + soGhe + "]";
    }
}
