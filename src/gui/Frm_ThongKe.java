package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.BanDAO;
import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.KhuVucDAO;
import dao.MonDAO;
import dao.NhanVienDAO;
import dao.PhieuDatBanDAO;
import dao.PhongDAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuVuc;
import entity.Mon;
import entity.NhanVien;

import javax.swing.SwingConstants;


public class Frm_ThongKe extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private DefaultTableModel model;
    private JTable table;
    private JButton btnNewButton;
    private Frm_DanhSachHoaDon inhoadon;
    private JTextField textField;
	private HoaDonDAO hd_dao;
	private DefaultTableModel tablemodel;
	private DefaultTableModel table_1_model;
	private ArrayList<HoaDon> HoaDon;
	private ChiTietHoaDonDAO cthd_dao;
	private KhuVucDAO khuvuc_dao;
	private ArrayList<KhuVuc> KhuVuc;
	private PhongDAO phong_dao;
	private NhanVienDAO nv_dao;
	private BanDAO ban_dao;
	private KhachHangDAO kh_dao;
	private MonDAO mon_dao;

    /**
     * Create the panel.
     */
    public Frm_ThongKe() {
    	setSize(1347,750);
    	setBackground(new Color(255, 128, 192));
        setBounds(100, 100, 1304, 750);
        setLayout(null);

        mon_dao = new MonDAO();
		phong_dao = new PhongDAO();
		ban_dao = new BanDAO();
		nv_dao = new NhanVienDAO();
		kh_dao = new KhachHangDAO();
        hd_dao = new HoaDonDAO();
        cthd_dao = new ChiTietHoaDonDAO();
        khuvuc_dao = new KhuVucDAO();
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 192, 1500, 388);
        add(scrollPane);

        Object[][] data = {};
        String[] hears ={"Mã hóa đơn", "Tổng tiền", "Khu vực", "Phòng", "Bàn ăn", "Nhân viên", "Ngày lập","Khách hàng ","Ngày đặt"};
        tablemodel = new DefaultTableModel(data, hears);
        table = new JTable(tablemodel);
        scrollPane.setViewportView(table);
        
        JLabel lblNewLabel = new JLabel("Danh sách hóa đơn");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(590, 11, 353, 38);
        add(lblNewLabel);
        
        Object[][] table02_Data =  {};
        String[] table02_Columns = {
    		"Mã hóa đơn","Tên món","Số lượng", "Đơn giá", "Thành tiền"};
        
        
        table_1_model = new DefaultTableModel(table02_Data, table02_Columns);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 49, 2050, 125);
        add(panel);
        panel.setLayout(null);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(192, 192, 192));
        panel_1.setBounds(0, 0, 181, 49);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("Tìm hóa đơn");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblNewLabel_1.setBounds(10, 11, 161, 27);
        panel_1.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Mã hóa đơn:");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblNewLabel_2.setBounds(432, 45, 157, 43);
        panel.add(lblNewLabel_2);
        
        textField = new JTextField();
        textField.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        textField.setBounds(609, 50, 307, 35);
        panel.add(textField);
        textField.setColumns(10);
        
        JButton btnNewButton_1 = new JButton("Tìm");
        btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 23));
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnNewButton_1.setBounds(956, 47, 106, 38);
        panel.add(btnNewButton_1);
        
        
                        
        btnNewButton = new JButton("In hóa đơn");
        btnNewButton.setBounds(1166, 685, 138, 43);
        add(btnNewButton);
        btnNewButton.setBackground(new Color(135, 149, 248));
        btnNewButton.setForeground(new Color(0, 0, 0));
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));   
      
        docDuLieuTuDatabase();
        btnNewButton.addActionListener(this);
        
        JButton btnXemChiTit = new JButton("Xem chi tiết hóa đơn");
        btnXemChiTit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnXemChiTit.setForeground(Color.BLACK);
        btnXemChiTit.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnXemChiTit.setBackground(new Color(135, 149, 248));
        btnXemChiTit.setBounds(909, 685, 218, 43);
        add(btnXemChiTit);
    }
    private void docDuLieuTuDatabase() {
        List<HoaDon> dsHoaDon = hd_dao.layThongTin();

        for (HoaDon hd : dsHoaDon) {
        	NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
			String tongTien = format.format(hd.getTongTien());
            Object[] rowData = {
                hd.getMaHoaDon(), 
                tongTien, 
                hd.getKhuVuc().getMaKhuVuc(), 
                hd.getPhong().getMaPhong(), 
                hd.getBanAn().getMaBan(),
                hd.getNhanVien().getMaNV(), 
                hd.getNgayLap(),
                hd.getKhachHang().getMaKH(),
                hd.getNgayDat()
            };
            tablemodel.addRow(rowData);
        }
    }
 

    @Override
    public void actionPerformed(ActionEvent e) {
    	Object o = e.getSource();
    	if(o.equals(btnNewButton)) {
    		new Frm_DanhSachHoaDon().setVisible(true);
    	}
}
}