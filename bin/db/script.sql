USE [master]
GO

CREATE DATABASE [QuanLyNhaHang]
GO

USE [QuanLyNhaHang]
GO
CREATE TABLE Foods (
    MaMonAn VARCHAR(50),
    TenMonAn VARCHAR(100),
    LoaiMonAn VARCHAR(50)
);
CREATE TABLE dbo.KhuVuc(
    maKhuVuc nvarchar(10) NOT NULL PRIMARY KEY,
    tenKhuVuc nvarchar(30) NULL
)

INSERT INTO dbo.KhuVuc (maKhuVuc, tenKhuVuc)
VALUES ('KV01', N'Ngoài trời'),
       ('KV02', N'Tầng 1'),
       ('KV03', N'Tầng 2');

CREATE TABLE dbo.Phong(
    maPhong nvarchar(10) NOT NULL PRIMARY KEY,
    tenPhong nchar(30) NULL,
    maKhuVuc nvarchar(10) NULL,
    soGhe int NULL,
    FOREIGN KEY (maKhuVuc) REFERENCES dbo.KhuVuc(maKhuVuc)
)
INSERT INTO dbo.Phong (maPhong, tenPhong, maKhuVuc, soGhe)
VALUES (N'P001', N'none01', N'KV01', Null),
       (N'P002', N'none02', N'KV02', Null),
       (N'P003', N'none03', N'KV03', Null),
	   (N'P004', N'Phòng Vip 1', N'KV01', Null),
	   (N'P005', N'Phòng Vip 2', N'KV02', Null);

CREATE TABLE dbo.Ban(
    maBan nvarchar(30) NOT NULL,
    soBan nvarchar(30) NULL,
    soGhe int NULL,
    maKhuVuc nvarchar(10) NULL,
    maPhong nvarchar(10) NULL,
    PRIMARY KEY CLUSTERED (maBan),
    FOREIGN KEY (maKhuVuc) REFERENCES dbo.KhuVuc(maKhuVuc),
    FOREIGN KEY (maPhong) REFERENCES dbo.Phong(maPhong)
)

INSERT INTO dbo.Ban (maBan, soBan, soGhe, maKhuVuc, maPhong)
VALUES (N'B001', N'Bàn 1', 4, N'KV01', N'P001'),
       (N'B002', N'Bàn 2', 6, N'KV02', N'P002'),
       (N'B003', N'Bàn 3', 4, N'KV03', N'P003'),
	   (N'B004', N'Bàn 4', 4, N'KV01', N'P004'),
       (N'B005', N'Bàn 5', 6, N'KV02', N'P005'),
       (N'B006', N'Bàn 6', 4, N'KV03', N'P003');

CREATE TABLE dbo.KhachHang(
    maKH nvarchar(10) NOT NULL,
    tenKH nvarchar(50) NULL,
    phai nchar(10) NULL,
    SDT nvarchar(20) NULL,
    diaChi nvarchar(100) NULL,
    PRIMARY KEY CLUSTERED (maKH)
)

INSERT INTO dbo.KhachHang (maKH, tenKH, phai, SDT, diaChi)
VALUES  (N'KH001', N'Nguyễn A', N'Nam', N'0313579454', N'TPHCM'),
		(N'KH002', N'Trần A', N'Nữ', N'0278951333', N'TPHCM'),
		(N'KH003', N'Võ B', N'Nam', N'0916462564', N'TPHCM');

CREATE TABLE dbo.Mon(
    maMon nvarchar(10) NOT NULL PRIMARY KEY,
    tenMon nvarchar(50) NULL,
    donGia float NULL,
    loaiMon nvarchar(50) NULL,
    soLuong int NULL, 
    maBan nvarchar(30) NULL,
    maPhong nvarchar(10) NULL,
    maKhuVuc nvarchar(10) NULL,
    FOREIGN KEY (maBan) REFERENCES dbo.Ban(maBan),
    FOREIGN KEY (maPhong) REFERENCES dbo.Phong(maPhong),
    FOREIGN KEY (maKhuVuc) REFERENCES dbo.KhuVuc(maKhuVuc)
)

INSERT INTO dbo.Mon (maMon, tenMon, donGia, soLuong, loaiMon)
VALUES 
    ('M001', N'Cơm gà', 50000, 20, N'Món nướng'),
    ('M002', N'Phở bò', 45000, 15, N'Món lẩu'),
    ('M003', N'Bún bò', 40000, 18, N'Món lẩu'),
    ('M004', N'String', 20000, 25, N'Thức uống'),
    ('M005', N'Trà tắc', 30000, 30, N'Thức uống');


CREATE TABLE dbo.NhanVien(
    maNV nvarchar(10) NOT NULL PRIMARY KEY,
    tenNV nvarchar(20) NULL,
    phai nvarchar(10) NULL,
    tuoi int NULL,
    SDT nvarchar(20) NULL
)

INSERT INTO dbo.NhanVien (maNV, tenNV, phai, tuoi, SDT)
VALUES (N'NV001', N'Trần Bảo N', N'Nữ', 22, N'09137984666'),
       (N'NV002', N'Huỳnh K', N'Nam', 23, N'0923498462'),
       (N'NV003', N'Lê Văn C', N'Nam', 25, N'0789354359');

CREATE TABLE dbo.PhieuDatBan(
    maPhieuDatBan nvarchar(10) NOT NULL PRIMARY KEY,
    maKhuVuc nvarchar(10) NULL,
    maPhong nvarchar(10) NULL,
    maBan nvarchar(30) NULL,
    soNguoi int NULL,
    ngayDat date NULL,
    ngayLap date NULL,
    gioDat nchar(10) NULL,
    maKH nvarchar(10) NULL,
    maNV nvarchar(10) NULL,
    FOREIGN KEY (maKhuVuc) REFERENCES dbo.KhuVuc(maKhuVuc),
    FOREIGN KEY (maPhong) REFERENCES dbo.Phong(maPhong),
    FOREIGN KEY (maBan) REFERENCES dbo.Ban(maBan),
    FOREIGN KEY (maKH) REFERENCES dbo.KhachHang(maKH),
    FOREIGN KEY (maNV) REFERENCES dbo.NhanVien(maNV)
)
INSERT INTO dbo.PhieuDatBan (maPhieuDatBan, maKhuVuc, maPhong, maBan, soNguoi, ngayDat, ngayLap, gioDat, maKH, maNV)
VALUES ('PH001', 'KV01', 'P001', 'B001', 4, '2024-04-12', '2024-04-11', '10:00', 'KH001', 'NV001');

CREATE TABLE dbo.ChiTietPhieuDatBan(
    maPhieuDatBan nvarchar(10) NOT NULL,
    soLuongMon int NULL,
    donGia float NULL,
    maBan nvarchar(30) NOT NULL,
    PRIMARY KEY CLUSTERED (maPhieuDatBan, maBan),
    FOREIGN KEY (maPhieuDatBan) REFERENCES dbo.PhieuDatBan(maPhieuDatBan),
    FOREIGN KEY (maBan) REFERENCES dbo.Ban(maBan)
)
CREATE TABLE dbo.HoaDon(
    maHoaDon nvarchar(10) NOT NULL,
	tongTien float NULL,
	khuVuc nvarchar(50) NULL,
	phong nvarchar(50) NULL,
	banAn nvarchar(50) NULL,
	tenNhanVien nvarchar(50) NULL,
    ngayLap date NULL,
	ngayDat date NULL,
	tenKH nvarchar(50) NULL,
    maKH nvarchar(10) NULL,
    maPhieuDatBan nvarchar(10) NULL,
    maNV nvarchar(10) NULL,
    PRIMARY KEY CLUSTERED (maHoaDon),
    FOREIGN KEY (maPhieuDatBan) REFERENCES dbo.PhieuDatBan(maPhieuDatBan),
    FOREIGN KEY (maKH) REFERENCES dbo.KhachHang(maKH),
    FOREIGN KEY (maNV) REFERENCES dbo.NhanVien(maNV)
)

CREATE TABLE dbo.ChiTietHoaDon(
    maHoaDon nvarchar(10) NOT NULL,
    maMon nvarchar(10) NULL,
	tenMon nvarchar(50) NULL,
    soLuongMon int NULL,
    donGia float NULL,
    thanhTien float NULL,
    maPhieuDatBan nvarchar(10) NULL,
    PRIMARY KEY CLUSTERED (maHoaDon),
    FOREIGN KEY (maHoaDon) REFERENCES dbo.HoaDon(maHoaDon),
    FOREIGN KEY (maMon) REFERENCES dbo.Mon(maMon),
    FOREIGN KEY (maPhieuDatBan) REFERENCES dbo.PhieuDatBan(maPhieuDatBan)
)

