package gui;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dao.BanDAO;
import dao.DatMonDAO;
import dao.KhuVucDAO;
import dao.LoaiMonDao;
import dao.MonAnDAO;

import dao.PhieuDatBanDAO;
import dao.PhongDAO;
import entity.Ban;
import entity.DatMon;
import entity.KhuVuc;
import entity.LoaiMon;
import entity.MonAn;
import entity.PhieuDatBan;
import entity.Phong;


import javax.swing.border.LineBorder;

public class Frm_DatMon extends JPanel implements ActionListener,MouseListener {
	private static final long serialVersionUID = 1L;
	private JTextField  txtDonGia, txtsoLuong;
	private DefaultTableModel model;
	private JButton btnThem, btnXoa, btnLamMoi,btnTaoDon,btnDatDon;
	private JComboBox<String> cmbLoaiMon, cmbTenMon ,cmbKhuVuc, cmbBan, cmbPhong,cmbphieudatban;
	private JTable table;
	DatMonDAO dmdao = new DatMonDAO();
	PhieuDatBanDAO dbDao = new PhieuDatBanDAO();
	MonAnDAO mDao = new MonAnDAO();
	private  ArrayList<DatMon> danhSachDatMon;



	public Frm_DatMon() throws SQLException {

		setLayout(null);
		// Tiêu đề
		JPanel panelTitle = new JPanel();
		panelTitle.setBackground(new Color(0, 206, 209));
		panelTitle.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		JLabel lblTitLe = new JLabel("ĐẶT MÓN");
		lblTitLe.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitLe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitLe.setFont(new Font("Arial", Font.BOLD, 35));
		lblTitLe.setForeground(Color.RED);
		panelTitle.add(lblTitLe);
		panelTitle.setBounds(0, 42, 1540, 60);
		add(panelTitle);

		// Khung thông tin món ăn
		JPanel panelThongTin = new JPanel();
		panelThongTin.setBackground(new Color(0, 128, 128));
		panelThongTin.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panelThongTin.setBounds(10, 99, 1540, 235);
		add(panelThongTin);
		panelThongTin.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setBounds(0, 0, 180, 35);
		panelThongTin.add(panel);

		JLabel lbl_titlle_panel_1 = new JLabel("Thông tin món");
		panel.add(lbl_titlle_panel_1);
		lbl_titlle_panel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_titlle_panel_1.setFont(new Font("Arial", Font.BOLD, 16));
		JLabel lblTenMon = new JLabel("Tên Món:");
		lblTenMon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTenMon.setBounds(804, 75, 100, 40);
		panelThongTin.add(lblTenMon);

		// ComboBox cho tên món
		cmbTenMon = new JComboBox<>();
		cmbTenMon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbTenMon.setBounds(900, 75, 200, 40);
		cmbTenMon.setEditable(true);
		for(MonAn ma : MonAnDAO.layThongTin()) {
			cmbTenMon.addItem(ma.getTenMon());
		}
		panelThongTin.add(cmbTenMon);


		// Đơn giá
		JLabel lblDonGia = new JLabel("Đơn giá:");
		lblDonGia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDonGia.setBounds(807, 125, 100, 40);
		panelThongTin.add(lblDonGia);

		txtDonGia = new JTextField();
		txtDonGia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDonGia.setBounds(900, 125, 200, 40);
		panelThongTin.add(txtDonGia);
		txtDonGia.setColumns(10);

		// Loại Món
		JLabel lblLoaiMon = new JLabel("Loại Món:");
		lblLoaiMon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLoaiMon.setBounds(451, 125, 86, 40);
		panelThongTin.add(lblLoaiMon);

		cmbLoaiMon = new JComboBox<>();
		cmbLoaiMon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbLoaiMon.setEditable(false);
		cmbLoaiMon.setBounds(528, 125, 200, 40);
		cmbLoaiMon.setEditable(true);
		for(LoaiMon ma : LoaiMonDao.getAllDsLoaiMon()) {
			cmbLoaiMon.addItem(ma.getTenLoaiMon());
		}
		panelThongTin.add(cmbLoaiMon);

		// Khu Vực
		JLabel lblKhuVuc = new JLabel("Khu Vực:");
		lblKhuVuc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblKhuVuc.setBounds(90, 70, 86, 40);
		panelThongTin.add(lblKhuVuc);

		cmbKhuVuc = new JComboBox<>();
		cmbKhuVuc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbKhuVuc.setEditable(false);
		cmbKhuVuc.setBounds(200, 76, 200, 39);
		cmbKhuVuc.setEditable(true);
		for(KhuVuc kv: KhuVucDAO.layThongTin()) {
			cmbKhuVuc.addItem(kv.getTenKhuVuc());
		}
		panelThongTin.add(cmbKhuVuc);

		// Bàn
		JLabel lblBan = new JLabel("Bàn:");
		lblBan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBan.setBounds(462, 75, 86, 40);
		panelThongTin.add(lblBan);

		cmbBan = new JComboBox<>();
		cmbBan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbBan.setEditable(false);
		cmbBan.setBounds(528, 76, 200, 39);
		cmbBan.setEditable(true);
		for(Ban b : BanDAO.layThongTin()) {
			cmbBan.addItem(b.getMaBan());
		}
		panelThongTin.add(cmbBan);

		//Phòng
		JLabel lblPhong = new JLabel("Phòng:");
		lblPhong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPhong.setBounds(90, 125, 86, 40);
		panelThongTin.add(lblPhong);

		cmbPhong = new JComboBox<>();
		cmbPhong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbPhong.setEditable(false);
		cmbPhong.setBounds(200, 125, 200, 40);
		cmbPhong.setEditable(true);
		for(Phong ph: PhongDAO.layThongTin()) {
			cmbPhong.addItem(ph.getTenPhong());
		}
		panelThongTin.add(cmbPhong);

		// Số lượng
		JLabel lblSoLuong = new JLabel("Số lượng:");
		lblSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSoLuong.setBounds(1152, 75, 100, 40);
		panelThongTin.add(lblSoLuong);

		txtsoLuong = new JTextField();
		txtsoLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtsoLuong.setColumns(10);
		txtsoLuong.setBounds(1260, 75, 200, 40);
		panelThongTin.add(txtsoLuong);

		// Nút thêm
		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThem.setBounds(100, 175, 180, 50);
		panelThongTin.add(btnThem);

		// Nút xóa
		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXoa.setBounds(386, 175, 170, 50);
		panelThongTin.add(btnXoa);

		// Nút làm mới
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLamMoi.setBounds(708, 175, 152, 50);
		panelThongTin.add(btnLamMoi);
		//Phiếu dặt bàn
		JLabel lblPhieu = new JLabel("Phiếu đặt bàn:");
		lblPhieu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPhieu.setBounds(1152, 125, 110, 40);
		panelThongTin.add(lblPhieu);

		cmbphieudatban = new JComboBox<>();
		cmbphieudatban.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbphieudatban.setEditable(false);
		cmbphieudatban.setBounds(1260, 126, 200, 39);
		for(PhieuDatBan ph: PhieuDatBanDAO.layThongTin()) {
			cmbphieudatban.addItem(ph.getMaPhieu());
		}
		panelThongTin.add(cmbphieudatban);

		cmbphieudatban.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String maPhieu = (String) cmbphieudatban.getSelectedItem();
				System.out.println(maPhieu);
				if (maPhieu != null) {
					PhieuDatBan phieuDatBan = dbDao.getPhieuDatBantheoMaPhieuDatBan(maPhieu);

					if (phieuDatBan != null) {
						cmbKhuVuc.setSelectedItem(phieuDatBan.getKhuVuc().getTenKhuVuc());
						cmbPhong.setSelectedItem(phieuDatBan.getPhong().getTenPhong());
						cmbBan.setSelectedItem(phieuDatBan.getTenBan().getSoBan());
						ArrayList<DatMon> ds = dmdao.layDanhSachDatMonTheoPhieu(maPhieu);
						for (DatMon datMon : ds) {
							System.out.println(datMon.getPhieudatban().getKhuVuc().getTenKhuVuc());
							Object[] rowData  = {datMon.getPhieudatban().getKhuVuc().getTenKhuVuc(),datMon.getPhieudatban().getPhong().getTenPhong(),datMon.getPhieudatban().getTenBan().getSoBan(),
									datMon.getMonAn().getTenMon(),datMon.getMonAn().getLoaiMon().getTenLoaiMon(),Integer.toString(datMon.getMonAn().getDonGia()),Integer.toString(datMon.getSoLuong())};
							model.addRow(rowData);	
						}
					}
				}
			}




		});

		panelThongTin.add(cmbphieudatban);

		// Nút tạo đơn
		JButton btnTaoDon_1 = new JButton("Tạo Đơn");
		btnTaoDon_1.setBounds(1308, 175, 152, 50);
		panelThongTin.add(btnTaoDon_1);
		btnTaoDon_1.setForeground(new Color(0, 139, 139));
		btnTaoDon_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnLamMoi_1 = new JButton("Làm mới");
		btnLamMoi_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLamMoi_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLamMoi_1.setBounds(983, 175, 152, 50);
		panelThongTin.add(btnLamMoi_1);

		// Thêm sự kiện cho các nút
		btnTaoDon_1.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLamMoi.addActionListener(this);

		// Khung bảng dữ liệu
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(53, 359, 1426, 427);
		add(scrollPane_2);

		String[] Header = {"Mã Phiếu Đặt Bàn","Khu Vực", "Phòng", "Bàn", "Tên Món", "Loại Món","Đơn Giá", "Số Lượng"};
		model = new DefaultTableModel(Header, 0);
		table = new JTable(model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane_2.setViewportView(table);
		docDuLieuDBVaoTable();
		// Hiển thị giao diện
		setVisible(true);
	}
	// Xử lý sự kiện cho các nút
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		// Nút thêm
	}
	



	// Get the table model
	public DefaultTableModel getModel() {
		return model;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
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
	public void docDuLieuDBVaoTable() {
	    ArrayList<DatMon> list = dmdao.layThongTin();
	    for (DatMon dm : list) {
	        model.addRow(new Object[]{dm.getPhieudatban().getMaPhieu(), dm.getKhuVuc().getMaKhuVuc(), dm.getPhong().getMaPhong(), dm.getBan().getMaBan(),dm.getMonAn().getMaMon(),dm.getLoaiMon().getMaLoaiMon(),dm.getMonAn().getDonGia(),dm.getSoLuong()});
	    }
	}
}





