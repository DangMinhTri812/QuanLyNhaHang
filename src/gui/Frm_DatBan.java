package gui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import connectDB.ConnectDB;
import dao.BanDAO;
import dao.KhachHangDAO;
import dao.KhuVucDAO;
import dao.NhanVienDAO;
import dao.PhieuDatBanDAO;
import dao.PhongDAO;
import entity.Ban;
import entity.KhachHang;
import entity.KhuVuc;
import entity.NhanVien;
import entity.PhieuDatBan;
import entity.Phong;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Frm_DatBan extends JPanel implements ActionListener,MouseListener{
    private JSpinner spnngayThang;
    private JComboBox<String> cmbgioDat;
    private JComboBox<Integer> cmbsoLuongNguoi;
    private JComboBox<String> cmbkhuVuc;
    private JComboBox<String> cmbban;
	private JButton btnThem,btnTim, btnXoa, btnxoaRong;
	private DefaultTableModel modelPhieu;
	private JTable table;
	private JTextField txtTim;
	private JLabel lblTim;
	private JComboBox <String> cmbnhanVien;
	private KhuVucDAO kv_dao;
	private PhongDAO phong_dao;
	private BanDAO ban_dao;
	private NhanVienDAO nv_dao;
	private PhieuDatBanDAO phieu_dao;
	private JComboBox<String> cmbkhachHang;
	private KhachHangDAO kh_dao;
	private JComboBox<String> cmbPhong;
	private JButton btnSua;
	private JButton btnLamMoi;
	private JLabel lblTitle;
	private JButton btnChonMon;

    public Frm_DatBan() {
    	try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		kv_dao = new KhuVucDAO();
		phong_dao = new PhongDAO();
		ban_dao = new BanDAO();
		nv_dao = new NhanVienDAO();
		phieu_dao = new PhieuDatBanDAO();
		kh_dao = new KhachHangDAO();
		
        setLayout(new BorderLayout());
        

        JPanel mainPanel = new JPanel(new BorderLayout(15, 5));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(160, 210, 180));
        JPanel pnlTitle = new JPanel();
        lblTitle = new JLabel("ĐẶT BÀN");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 35));
        lblTitle.setForeground(Color.DARK_GRAY);
        pnlTitle.add(lblTitle);
        pnlTitle.setBackground(new Color(160, 210, 180)); 
        pnlTitle.setPreferredSize(new Dimension(50, 50));

        mainPanel.add(pnlTitle, BorderLayout.NORTH);

        JPanel pnlN = new JPanel(new GridLayout(3, 4, 0, 8));
        pnlN.setBackground(new Color(160, 210, 180)); 
        pnlN.setBorder(new EmptyBorder(5, 0, 10, 0));

        // Ngày tháng năm
        JPanel pnlngayThang = new JPanel(new BorderLayout());
        pnlngayThang.setBackground(new Color(160, 210, 180));
        pnlngayThang.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.black, 2), "Ngày đặt", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));
        SpinnerDateModel dateModel = new SpinnerDateModel();
        spnngayThang = new JSpinner(dateModel);
        spnngayThang.setEditor(new JSpinner.DateEditor(spnngayThang, "dd/MM/yyyy"));
        spnngayThang.setFont(new Font("Arial", Font.BOLD, 20));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date selectedDate = (Date) spnngayThang.getValue();
        Date currentDate = new Date(); // Current date

        try {
            selectedDate = dateFormat.parse(dateFormat.format(selectedDate));
            currentDate = dateFormat.parse(dateFormat.format(currentDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        spnngayThang.setEnabled(!selectedDate.before(currentDate));
        pnlngayThang.add(spnngayThang, BorderLayout.CENTER);
        pnlN.add(pnlngayThang);

        // Giờ đặt
        JPanel pnlgioDat = new JPanel(new BorderLayout());
        pnlgioDat.setBackground(new Color(160, 210, 180)); 
        pnlgioDat.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "Giờ đặt", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));
        List<String> dsGio = new ArrayList<>();
        for (int hour = 8; hour <= 20; hour++) {
            for (int minutes = 0; minutes < 60; minutes += 30) {
                String time = String.format("%02d:%02d", hour, minutes);
                dsGio.add(time);
            }
        }
        String[] gioDat = dsGio.toArray(new String[0]);

        cmbgioDat = new JComboBox<>(gioDat);
        cmbgioDat.setFont(new Font("Arial", Font.BOLD, 20));
        pnlgioDat.add(cmbgioDat, BorderLayout.CENTER);
        pnlN.add(pnlgioDat);

        // Số lượng người
        JPanel pnlsoLuongNguoi = new JPanel(new BorderLayout());
        pnlsoLuongNguoi.setBackground(new Color(160, 210, 180)); 
        pnlsoLuongNguoi.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "Số lượng người", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));
        List<Integer> dsSoNguoi = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
        	dsSoNguoi.add(i);
        }
        Integer[] peopleOptions = dsSoNguoi.toArray(new Integer[0]);

        cmbsoLuongNguoi = new JComboBox<>(peopleOptions);
        cmbsoLuongNguoi.setFont(new Font("Arial", Font.BOLD, 20));
        pnlsoLuongNguoi.add(cmbsoLuongNguoi, BorderLayout.CENTER);
        pnlN.add(pnlsoLuongNguoi);
        // Nhân viên
        
        JPanel pnlnhanVien = new JPanel(new BorderLayout());
        pnlnhanVien.setBackground(new Color(160, 210, 180)); 
        pnlnhanVien.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "Nhân viên", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));
        cmbnhanVien = new JComboBox<String>();
        cmbnhanVien.setEditable(false);	
		
		ArrayList<NhanVien> listNV = nv_dao.layThongTin() ;
		for (NhanVien nv : listNV) {
			cmbnhanVien.addItem(nv.getHoTenNV());
		}
        cmbnhanVien.setFont(new Font("Arial", Font.BOLD, 20));
        pnlnhanVien.add(cmbnhanVien, BorderLayout.CENTER);
        pnlN.add(pnlnhanVien);
 
        // Khách hàng
        
        JPanel pnlkhachHang = new JPanel(new BorderLayout());
        pnlkhachHang.setBackground(new Color(160, 210, 180)); 
        pnlkhachHang.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "Khách hàng", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));
        cmbkhachHang = new JComboBox<String>();
        cmbkhachHang.setEditable(false);	
		
		ArrayList<KhachHang> listKH = kh_dao.layThongTin() ;
		for (KhachHang kh : listKH) {
			cmbkhachHang.addItem(kh.getTenKH());
		}
		cmbkhachHang.setFont(new Font("Arial", Font.BOLD, 20));
		pnlkhachHang.add(cmbkhachHang, BorderLayout.CENTER);
        pnlN.add(pnlkhachHang);

        // Khu vực
        
        JPanel pnlkhuVuc = new JPanel(new BorderLayout());
        pnlkhuVuc.setBackground(new Color(160, 210, 180)); 
        pnlkhuVuc.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "Khu vực", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));
        cmbkhuVuc = new JComboBox<String>();
        cmbkhuVuc.setEditable(false);	
        cmbkhuVuc.addItem("Chọn khu vực");
		ArrayList<KhuVuc> listKV = kv_dao.layThongTin() ;
		for (KhuVuc kv : listKV) {
			cmbkhuVuc.addItem(kv.getTenKhuVuc());
		}
        cmbkhuVuc.setFont(new Font("Arial", Font.BOLD, 20));
        pnlkhuVuc.add(cmbkhuVuc, BorderLayout.CENTER);
        pnlN.add(pnlkhuVuc);
        cmbkhuVuc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chonKV = (String) cmbkhuVuc.getSelectedItem();
                capNhatCmbTheoKhuVuc(chonKV);
            }
        });

     // Phòng
        JPanel pnlPhong = new JPanel(new BorderLayout());
        pnlPhong.setBackground(new Color(160, 210, 180));
        pnlPhong.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "Phòng", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));

        cmbPhong = new JComboBox<String>();
        cmbPhong.setEditable(false);
        cmbPhong.addItem("Chọn phòng");

        ArrayList<Phong> listPhong = phong_dao.layThongTin();
        for (Phong phong : listPhong) {
            cmbPhong.addItem(phong.getTenPhong());
        }

        cmbPhong.setFont(new Font("Arial", Font.BOLD, 20));
        cmbPhong.setForeground(Color.BLACK);
        pnlPhong.add(cmbPhong, BorderLayout.CENTER);
        pnlN.add(pnlPhong);

        cmbPhong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	cmbPhong.addItem("Chọn phòng");
                String chonPhong = (String) cmbPhong.getSelectedItem();
                String chonKhuVuc = (String) cmbkhuVuc.getSelectedItem();
                capNhatCmbBanTheoPhong(chonPhong, chonKhuVuc);
                
            }
        });


        
    // Bàn
        JPanel pnlBan = new JPanel(new BorderLayout());
        pnlBan.setBackground(new Color(160, 210, 180));
        pnlBan.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "Bàn", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));

        cmbban = new JComboBox<String>();
        cmbban.setEditable(false);	

        ArrayList<Ban> listBan = ban_dao.layThongTin();
        for (Ban b : listBan) { 
            cmbban.addItem(b.getSoBan());
        }
        
        cmbban.setFont(new Font("Arial", Font.BOLD, 20));
        cmbban.setForeground(Color.BLACK);
        pnlBan.add(cmbban, BorderLayout.CENTER);
        pnlN.add(pnlBan);  
        
        JPanel pnltim = new JPanel();
        pnltim.setBackground(new Color(160, 210, 180));
        pnltim.setLayout(new FlowLayout(FlowLayout.CENTER, 7, 5));
        lblTim = new JLabel("Nhập tên khách hàng cần tìm:");
        lblTim.setFont(new Font("Arial", Font.BOLD, 16));
        txtTim = new JTextField(12);
        txtTim.setFont(new Font("Arial", Font.BOLD, 16));
        btnTim = new JButton("Tìm kiếm");
        pnltim.add(lblTim);
        txtTim.setPreferredSize(new Dimension(200, 25)); 
        pnltim.add(txtTim);
        pnltim.add(btnTim);
        
        
      //Buttons
        JPanel pnlbtn = new JPanel(new GridLayout(1, 2,10,0));
        pnlbtn.setBackground(new Color(160, 210, 180));
        pnlbtn.setBorder(new EmptyBorder(30, 30, 0, 30));

        btnThem = new JButton("Thêm");
        btnXoa = new JButton("Xóa");
        btnxoaRong = new JButton("Xóa rỗng");

        btnLamMoi = new JButton("Làm mới");
        btnLamMoi.setFont(new Font("Arial", Font.BOLD, 16));
        pnlbtn.add(btnThem);
        pnlbtn.add(btnXoa);
        Font bFont = new Font("Arial", Font.BOLD, 16);
        btnThem.setFont(bFont);
        btnXoa.setFont(bFont);
        btnxoaRong.setFont(bFont);
        btnTim.setFont(bFont);


        JPanel pnlbtn1 = new JPanel(new GridLayout(1, 2,10,0));
        pnlbtn1.setBackground(new Color(160, 210, 180));
        pnlbtn1.setBorder(new EmptyBorder(30, 40, 0, 30));

        pnlbtn1.add(btnxoaRong);
        pnlbtn1.add(btnLamMoi);
        
        pnlN.add(pnltim);
        pnlN.add(pnlbtn);
        pnlN.add(pnlbtn1);
        
        JPanel pnlbtnSua = new JPanel(new GridLayout(1, 2,20,0));
        pnlbtnSua.setBackground(new Color(160, 210, 180));
        pnlbtnSua.setBorder(new EmptyBorder(30, 20, 0, 20));
        btnSua = new JButton("Sửa");
        Font btnFont = new Font("Arial", Font.BOLD, 17); 
        btnSua.setFont(btnFont);
        pnlbtnSua.add(btnSua);
        btnChonMon = new JButton("Chọn món");
        btnChonMon.setFont(new Font("Arial", Font.BOLD, 19));
        pnlbtnSua.add(btnChonMon);
        pnlN.add(pnlbtnSua);
        pnlN.add(pnlbtnSua);
        mainPanel.add(pnlN, BorderLayout.CENTER);
        add(mainPanel);

        JPanel pnlTable = new JPanel(new BorderLayout());

        // Table
        String[] columnNames = {"Mã", "Khu vực","Phòng", "Số bàn", "SL","Ngày đặt","Ngày lập","Giờ đặt","Khách hàng", "Nhân viên"};

        modelPhieu = new DefaultTableModel(columnNames, 0);
		table = new JTable(modelPhieu);
        table.setFont(new Font("Arial", Font.BOLD, 16));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
        table.setRowHeight(30);

        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));
        Color lightBlue = new Color(173, 216, 230); 
        header.setBackground(lightBlue);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        pnlTable.add(tableScrollPane, BorderLayout.CENTER);
        pnlTable.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        pnlTable.setPreferredSize(new Dimension(50, 430));
        mainPanel.add(pnlTable, BorderLayout.SOUTH);
            
                
        btnThem.addActionListener(this );
        btnXoa.addActionListener(this);
        btnxoaRong.addActionListener(this);
        btnTim.addActionListener(this);
        btnSua.addActionListener(this);
        btnLamMoi.addActionListener(this);
        
        table.addMouseListener(this);
        docDuLieuDBVaoTable();
        
    }

	@Override
	public void actionPerformed(ActionEvent e) {
	    Object o = e.getSource();
	    if (o.equals(btnThem)) {
	        Date selectedDate = (Date) spnngayThang.getValue();
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        String ngayDat = formatter.format(selectedDate);
	        String gio = String.valueOf(cmbgioDat.getSelectedItem());
	        int soLuong = (int) cmbsoLuongNguoi.getSelectedItem();
	        int soGhe = soLuongGheBanDaChon();
	        if (soLuong > soGhe) {
	            JOptionPane.showMessageDialog(null, "Vượt quá số lượng, vui lòng chọn bàn khác !!! ", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        } 
	        String khuVuc = String.valueOf(cmbkhuVuc.getSelectedItem());
	        String khachHang = String.valueOf(cmbkhachHang.getSelectedItem());
	        String ma = maTangDan();
	        String nhanVien = String.valueOf(cmbnhanVien.getSelectedItem());
	        String tenBan = String.valueOf(cmbban.getSelectedItem());
	        String tenPhong = String.valueOf(cmbPhong.getSelectedItem());
	        LocalDate ngayLap = LocalDate.now();

	        KhuVucDAO khuVucDAO = new KhuVucDAO();
    	    ArrayList<KhuVuc> dsKV = khuVucDAO.layThongTin();
    	    PhongDAO phongDao = new PhongDAO();
    	    ArrayList<Phong> dsPhong = phongDao.layThongTin();
    	    BanDAO banDao = new BanDAO();
    	    ArrayList<Ban> dsBan = banDao.layThongTin();
    	    NhanVienDAO nvDao = new NhanVienDAO();
    	    ArrayList<NhanVien> dsNV = nvDao.layThongTin();
    	    KhachHangDAO khDao = new KhachHangDAO();
    	    ArrayList<KhachHang> dsKH = khDao.layThongTin();

	        KhuVuc kv = timTenKhuVuc(dsKV, khuVuc);
	        Phong ph = timTenPhong(dsPhong, tenPhong);
	        Ban ban = timTenBan(dsBan, tenBan);
	        NhanVien nv = timTenNhanVien(dsNV, nhanVien);
	        KhachHang kh = timTenKhachHang(dsKH, khachHang);

	        if (kv != null && ban != null && nv != null && kh != null) {
	            PhieuDatBan p = new PhieuDatBan(ma, kv, ph, ban, soLuong, ngayDat, ngayLap, gio, kh, nv);
	            if (phieu_dao.themPhieu(p,ngayDat)) {
	                modelPhieu.addRow(new Object[]{p.getMaPhieu(), kv.getTenKhuVuc(), ph != null ? ph.getTenPhong() : null, ban.getSoBan(), p.getSoLuongNguoi(),
	                        p.getNgayDat(), p.getNgayLap(), p.getGioDat().trim(), kh.getTenKH(), nv.getHoTenNV()});
	                xoaRong();
	                JOptionPane.showMessageDialog(null, "Thêm thành công");
	            } else {
	                JOptionPane.showMessageDialog(null, "Trùng mã", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    }

	    if(o.equals(btnLamMoi)) {
	    	modelPhieu.setRowCount(0);
	    	docDuLieuDBVaoTable();
	    }
	    if (o.equals(btnXoa)) {
	        int r = table.getSelectedRow();
	        if (r != -1) {
	            String maPhieu = (String) modelPhieu.getValueAt(r, 0);
	            
	            int rs = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa phiếu này?");
	            if (rs == JOptionPane.YES_OPTION) {
	                modelPhieu.removeRow(r);
	                phieu_dao.xoaPhieu(maPhieu);
	            }
	        }
	    }
	    if (o.equals(btnTim)) {
	        String tim = txtTim.getText();
	        List<PhieuDatBan> list = phieu_dao.layThongTin();
	        modelPhieu.setRowCount(0);

	        // Duyệt qua từng Bàn trong danh sách
	        for (PhieuDatBan p : list) {
	            if (p.getKhachHang().getMaKH().contains(tim)) { 
	                //Thêm dòng mới vào bảng với thông tin của Bàn đó
	                modelPhieu.addRow(new Object[]{p.getMaPhieu(), p.getKhuVuc().getMaKhuVuc(), p.getPhong().getMaPhong(), p.getTenBan().getMaBan(), p.getSoLuongNguoi(),
	                        p.getNgayDat(), p.getNgayLap(), p.getGioDat().trim(),p.getKhachHang().getMaKH(), p.getNhanVien().getMaNV()});
	            }
	        }

	        if (modelPhieu.getRowCount() == 0) {
	            JOptionPane.showMessageDialog(this, "Không tìm thấy phiếu");
	        }
	    }

	    if (o.equals(btnxoaRong)) {
	    	xoaRong();
	    }
	    if (o.equals(btnSua)) {
	        int r = table.getSelectedRow(); 
	        if (r != -1) {
	            Date selectedDate = (Date) spnngayThang.getValue();
	            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	            String ngayDat = formatter.format(selectedDate);
	            String gio = String.valueOf(cmbgioDat.getSelectedItem());
	            int soLuong = (int) cmbsoLuongNguoi.getSelectedItem();
	            int soGhe = soLuongGheBanDaChon();
		        if (soLuong > soGhe) {
		            JOptionPane.showMessageDialog(null, "Vượt quá số lượng, vui lòng chọn bàn khác !!! ", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        } 
	            String ma = (String) modelPhieu.getValueAt(r, 0);
	            String khuVuc = String.valueOf(cmbkhuVuc.getSelectedItem());
	            String khachHang = String.valueOf(cmbkhachHang.getSelectedItem());
	            String nhanVien = String.valueOf(cmbnhanVien.getSelectedItem());
	            String tenBan = String.valueOf(cmbban.getSelectedItem());
	            String tenPhong = String.valueOf(cmbPhong.getSelectedItem());

	            String khuVucTbl = (String) modelPhieu.getValueAt(r, 1);
	            String tenPhongTbl = (String) modelPhieu.getValueAt(r, 2);
	            String tenBanTbl = (String) modelPhieu.getValueAt(r, 3);
	            int soLuongTbl = (int) modelPhieu.getValueAt(r, 4);
	            String ngayDatTbl = (String) modelPhieu.getValueAt(r, 5);
	            String gioTbl = (String) modelPhieu.getValueAt(r, 7);
	            String khachHangTbl = (String) modelPhieu.getValueAt(r, 8);
	            String nhanVienTbl = (String) modelPhieu.getValueAt(r, 9);

	            if (!khuVuc.equals(khuVucTbl) || !tenPhong.equals(tenPhongTbl) || !tenBan.equals(tenBanTbl)
	                    || soLuong != soLuongTbl || !ngayDat.equals(ngayDatTbl) || !gio.equals(gioTbl)
	                    || !khachHang.equals(khachHangTbl) || !nhanVien.equals(nhanVienTbl)) {
	                try {
	                    phieu_dao.capNhatPhieu(ma, khuVuc, tenPhong, tenBan, soLuong, ngayDat, gio, khachHang, nhanVien);

	                    modelPhieu.setValueAt(khuVuc, r, 1);
	                    modelPhieu.setValueAt(tenPhong, r, 2);
	                    modelPhieu.setValueAt(tenBan, r, 3);
	                    modelPhieu.setValueAt(soLuong, r, 4);
	                    modelPhieu.setValueAt(ngayDat, r, 5);
	                    modelPhieu.setValueAt(gio, r, 7);
	                    modelPhieu.setValueAt(khachHang, r, 8);
	                    modelPhieu.setValueAt(nhanVien, r, 9);

	                    xoaRong();

	                    JOptionPane.showMessageDialog(null, "Dữ liệu đã được lưu thành công");
	                } catch (Exception e2) {
	                    e2.printStackTrace();
	                    JOptionPane.showMessageDialog(null, "Lỗi khi lưu dữ liệu vào cơ sở dữ liệu");
	                }
	            } else {
	                JOptionPane.showMessageDialog(null, "Không có thay đổi nào để lưu");
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để cập nhật");
	        }
	    }


	}

	private void xoaRong () {
		spnngayThang.setValue(new Date());
		cmbgioDat.setSelectedIndex(0);
		cmbsoLuongNguoi.setSelectedIndex(0);
		if (cmbkhuVuc.getItemCount() > 0) {
		    cmbkhuVuc.setSelectedIndex(0);
		}
		if (cmbban.getItemCount() > 0) {
		    cmbban.setSelectedIndex(0);
		}
		if (cmbkhachHang.getItemCount() > 0) {
		    cmbkhachHang.setSelectedIndex(0);
		}
		if (cmbnhanVien.getItemCount() > 0) {
		    cmbnhanVien.setSelectedIndex(0);
		}
		if (cmbPhong.getItemCount() > 0) {
		    cmbPhong.setSelectedIndex(0);
		}

	}

	private KhuVuc timTenKhuVuc(ArrayList<KhuVuc> dsKV, String tenKhuVuc) {
	    for (KhuVuc kv : dsKV) {
	        if (tenKhuVuc.equalsIgnoreCase(kv.getTenKhuVuc())) {
	            return kv;
	        }
	    }
	    return null;
	}

	private Phong timTenPhong(ArrayList<Phong> dsPhong, String tenPhong) {
	    for (Phong ph : dsPhong) {
	        if (tenPhong.equalsIgnoreCase(ph.getTenPhong())) {
	            return ph;
	        }
	    }
	    return null;
	}

	private Ban timTenBan(ArrayList<Ban> dsBan, String soBan) {
	    for (Ban ban : dsBan) {
	        if (soBan.equalsIgnoreCase(ban.getSoBan())) {
	            return ban;
	        }
	    }
	    return null;
	}

	private NhanVien timTenNhanVien(ArrayList<NhanVien> dsNV, String tenNV) {
	    for (NhanVien nv : dsNV) {
	        if (tenNV.equalsIgnoreCase(nv.getHoTenNV())) {
	            return nv;
	        }
	    }
	    return null;
	}

	private KhachHang timTenKhachHang(ArrayList<KhachHang> dsKH, String tenKH) {
	    for (KhachHang kh : dsKH) {
	        if (tenKH.equalsIgnoreCase(kh.getTenKH())) {
	            return kh;
	        }
	    }
	    return null;
	}
	private int count = 0;

	private String maTangDan() {
	    String ma = phieu_dao.layMaMoiNhat();
	 // Lấy phần số của mã bàn (bỏ đi ký tự "PH") và tăng giá trị lên 1
	    count = Integer.parseInt(ma.substring(2)) + 1; 
	    return String.format("PH%03d", count);
	}


	private void capNhatCmbTheoKhuVuc(String chonKV) {
	    cmbPhong.removeAllItems();
	    cmbban.removeAllItems();
	    ArrayList<Phong> listPhong = phong_dao.layThongTinPhongTheoKhuVuc(chonKV);
	    for (Phong phong : listPhong) { 
	        cmbPhong.addItem(phong.getTenPhong());
	    }

	    ArrayList<Ban> listBan = ban_dao.layThongTinTheoKhuVuc(chonKV);
	    for (Ban ban : listBan) {
	        cmbban.addItem(ban.getSoBan());
	    }
	}

	private void capNhatCmbBanTheoPhong(String chonPhong, String chonKhuVuc) {
	    cmbban.removeAllItems();
	    if (chonPhong == null || chonPhong.equals("Chọn phòng")) {
	        ArrayList<Ban> listBan = ban_dao.layThongTinTheoPhong(chonPhong, chonKhuVuc);
	        for (Ban b : listBan) {
	            if (b.getPhong() == null) {
	                cmbban.addItem(b.getSoBan());
	            }
	        }
	    } 
	    if (chonPhong != null) {
	        ArrayList<Ban> listBan = ban_dao.layThongTinTheoPhong(chonPhong,chonKhuVuc);
	        for (Ban b : listBan) {
	            if (b.getPhong() != null) {
	                cmbban.addItem(b.getSoBan());
	            }
	        }
	    } 
	}

	private int soLuongGheBanDaChon() {
	    String chonBan = cmbban.getSelectedItem().toString();
	    int soGhe = 0;
	    ArrayList<Ban> dsB = ban_dao.layThongTin(); 
	    for (Ban ban : dsB) {
	        if (ban.getSoBan().equals(chonBan)) {
	            soGhe = ban.getSoGhe();
	            break;
	        }
	    }

	    return soGhe;
	}

	public void docDuLieuDBVaoTable() {
	    List<PhieuDatBan> listPhieu = phieu_dao.layThongTin();
	    for (PhieuDatBan p : listPhieu) {
	        modelPhieu.addRow(new Object[]{p.getMaPhieu(), p.getKhuVuc().getMaKhuVuc(), p.getPhong().getMaPhong(), p.getTenBan().getMaBan(), p.getSoLuongNguoi(),
	                p.getNgayDat(), p.getNgayLap(), p.getGioDat(), p.getKhachHang().getMaKH(), p.getNhanVien().getMaNV()});
	    }
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	    int row = table.getSelectedRow();
	    cmbkhuVuc.setSelectedItem(modelPhieu.getValueAt(row, 1).toString());
	    Object phongObj = modelPhieu.getValueAt(row, 2);
	    if (phongObj != null) {
	        cmbPhong.setSelectedItem(phongObj.toString());
	    } else {
	        cmbPhong.setSelectedIndex(0);
	    }
	    cmbban.setSelectedItem(modelPhieu.getValueAt(row, 3).toString());
	    cmbsoLuongNguoi.setSelectedItem(modelPhieu.getValueAt(table.getSelectedRow(), 4));
	    String dateString = modelPhieu.getValueAt(row, 5).toString();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
	    try {
	        Date date = dateFormat.parse(dateString);
	        spnngayThang.setValue(date);
	    } catch (ParseException ex) {
	        ex.printStackTrace(); 
	    }

	    cmbgioDat.setSelectedItem(modelPhieu.getValueAt(row, 7).toString());
	    cmbkhachHang.setSelectedItem(modelPhieu.getValueAt(row, 8).toString());
	    cmbnhanVien.setSelectedItem(modelPhieu.getValueAt(row, 9).toString());
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}