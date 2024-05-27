package timKiem;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import connectDB.ConnectDB;
import dao.KhachHangDAO;
import entity.KhachHang;

public class FrmTimKiemKhachHang extends JPanel implements ActionListener {
    private JPanel jpS;
    private JTextField txtTim;
    private JButton btnTim;
    private DefaultTableModel modelKH;
    private JTable tableKhachHang;
    private JLabel lbNhap;
    private JLabel lbTitle;
	private KhachHangDAO kh_dao;
	private JLabel lbNhap1;
	private JTextField txtTim1;
	private JButton btnTim1;

    public FrmTimKiemKhachHang() {
        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        kh_dao = new KhachHangDAO();
        
        setBorder(new EmptyBorder(30, 30, 30, 30));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(204, 235, 150));

        lbTitle = new JLabel("Tìm kiếm khách hàng");
        lbTitle.setFont(new Font("Arial", Font.BOLD, 40));
        lbTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        lbTitle.setForeground(Color.darkGray);
        lbTitle.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        add(lbTitle);

        String[] columns = {
                "Mã KH", "Họ tên", "Phái", "SĐT", "Địa chỉ"
        };
        modelKH = new DefaultTableModel(columns, 0);
        tableKhachHang = new JTable(modelKH);
        tableKhachHang.setPreferredScrollableViewportSize(new Dimension(940, 570));
        tableKhachHang.setRowHeight(30);
        tableKhachHang.setFont(new Font("Arial", Font.BOLD, 18));


        JTableHeader header = tableKhachHang.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 30));
        header.setFont(new Font("Arial", Font.BOLD, 20));

        String[] luaChon = {"Nam", "Nữ"};
        TableColumn phaiColumn = tableKhachHang.getColumnModel().getColumn(2);
        phaiColumn.setCellEditor(new DefaultCellEditor(new JComboBox<>(luaChon)));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		for (int i = 0; i < tableKhachHang.getColumnCount(); i++) {
			tableKhachHang.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
        JScrollPane scrollPane = new JScrollPane(tableKhachHang);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        add(scrollPane);

        jpS = new JPanel();
        jpS.setBorder(new EmptyBorder(30, 80, 30, 80));
        jpS.setLayout(new BoxLayout(jpS, BoxLayout.X_AXIS));
        jpS.setBackground(new Color(204, 235, 150));

        lbNhap = new JLabel("Nhập tên cần tìm: ");
        lbNhap.setFont(new Font("Arial", Font.BOLD, 20)); 
        txtTim = new JTextField(8);
        txtTim.setFont(new Font("Arial", Font.PLAIN, 20)); 
        btnTim = new JButton("Tìm");
        btnTim.setFont(new Font("Arial", Font.BOLD, 20)); 
        lbNhap1 = new JLabel("Nhập số điện thoại cần tìm: ");
        lbNhap1.setFont(new Font("Arial", Font.BOLD, 20)); 
        txtTim1 = new JTextField(8);
        txtTim1.setFont(new Font("Arial", Font.PLAIN, 20)); 
        btnTim1 = new JButton("Tìm");
        btnTim1.setFont(new Font("Arial", Font.BOLD, 20)); 

        jpS.add(lbNhap);
        jpS.add(txtTim);
        jpS.add(Box.createHorizontalStrut(10));
        jpS.add(btnTim);
        jpS.add(Box.createHorizontalStrut(35));
        jpS.add(lbNhap1);
        jpS.add(txtTim1);
        jpS.add(Box.createHorizontalStrut(10));
        jpS.add(btnTim1);

        add(jpS);

        btnTim.addActionListener(this);
        btnTim1.addActionListener(this);
        docDuLieuDBVaoTable();
        setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();	
		 if (o.equals(btnTim)) {
	        String tim = txtTim.getText();
	        List<KhachHang> list = kh_dao.layThongTin();
	        modelKH.setRowCount(0);

	        // Duyệt qua từng Bàn trong danh sách
	        for (KhachHang kh : list) {
	            if (kh.getTenKH().contains(tim)) { 
	                //Thêm dòng mới vào bảng với thông tin của Bàn đó
	                modelKH.addRow(new Object[]{kh.getMaKH(), kh.getTenKH(),kh.getPhai().trim(),kh.getSdt(),kh.getDiaChi()});
	            }
	        }

	        if (modelKH.getRowCount() == 0) {
	            JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng");
	        }
	    }
		 else if (o.equals(btnTim1)) {
		        String tim = txtTim1.getText();
		        List<KhachHang> list = kh_dao.layThongTin();
		        modelKH.setRowCount(0);

		        // Duyệt qua từng Bàn trong danh sách
		        for (KhachHang kh : list) {
		            if (kh.getSdt().contains(tim)) { 
		                //Thêm dòng mới vào bảng với thông tin của Bàn đó
		                modelKH.addRow(new Object[]{kh.getMaKH(), kh.getTenKH(),kh.getPhai().trim(),kh.getSdt(),kh.getDiaChi()});
		            }
		        }

		        if (modelKH.getRowCount() == 0) {
		            JOptionPane.showMessageDialog(this, "Không tìm thấy bàn");
		        }
		    }
	}

	public void docDuLieuDBVaoTable() {
		List<KhachHang> listKH = kh_dao.layThongTin();
		for (KhachHang kh : listKH ) {
			modelKH.addRow(new Object [] {kh.getMaKH(), kh.getTenKH(),kh.getPhai().trim(),
			         kh.getSdt(),kh.getDiaChi()});
		}
	}
}
