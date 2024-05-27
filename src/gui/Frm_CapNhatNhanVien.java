package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JComponent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import connectDB.ConnectDB;
import dao.NhanVienDAO;
import entity.KhachHang;
import entity.KhuVuc;
import entity.NhanVien;

public class Frm_CapNhatNhanVien extends JPanel implements ActionListener,MouseListener{
	private JLabel lbltitle;
	private JLabel lblTen;
	private JTextField txtTen;
	private JLabel lbltuoi;
	private JTextField txtTuoi;
	private JLabel lblsdt;
	private JTextField txtsdt;
	private JRadioButton nu;
	private JPanel pnlS;
	private JPanel bTrai;
	private JPanel bPhai;
	private JLabel lblTim;
	private JTextField txtTim;
	private JButton btnTim;
	private JButton btnThem;
	private JButton btnXoaTrang;
	private JButton btnXoa;
	private JButton btnSua;
	private JPanel pnlW;
	private JLabel phai;
	private DefaultTableModel modelNV;
	private JTable tableNhanVien;
	private JRadioButton nam;
	private NhanVienDAO nv_dao;

	public Frm_CapNhatNhanVien() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nv_dao = new NhanVienDAO();
		
		setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		setBackground(new Color(173, 216, 230));
		pnlW = new JPanel();
		pnlW.setLayout(new BoxLayout(pnlW, BoxLayout.Y_AXIS));
		pnlW.setPreferredSize(new Dimension(520, 602));
		pnlW.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        Color lightBlue = new Color(173, 216, 230); // Light blue colorS
		lbltitle = new JLabel("THÔNG TIN NHÂN VIÊN");
		lbltitle.setFont(new Font("Arial", Font.BOLD, 35));
		lbltitle.setForeground(Color.black);

		lblTen = new JLabel("Họ tên: ");
		txtTen = new JTextField();
		lbltuoi = new JLabel("Tuổi: ");
		txtTuoi = new JTextField();
		lblsdt = new JLabel("Số điện thoại: ");
		txtsdt = new JTextField();
		phai = new JLabel("Phái: ");
		nu = new JRadioButton("Nữ");
		nam = new JRadioButton("Nam");
		ButtonGroup gr = new ButtonGroup();
		gr.add(nam);
		gr.add(nu);

		JPanel jpFields = new JPanel(new GridLayout(0, 1));
		jpFields.add(lblTen);
		jpFields.add(txtTen);
		jpFields.add(lbltuoi);
		jpFields.add(txtTuoi);
		jpFields.add(lblsdt);
		jpFields.add(txtsdt);

		JPanel jpPhai = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jpPhai.add(phai);
		jpPhai.add(nu);
		jpPhai.add(nam);

		for (Component component : jpFields.getComponents()) {
		    if (component instanceof JLabel || component instanceof JTextField) {
		        ((JComponent) component).setAlignmentX(Component.CENTER_ALIGNMENT);
		    }
		}
		
		Dimension textFieldSize = new Dimension(200, 30);
		Font textFieldFont = new Font("Arial", Font.BOLD, 18); 
		JTextField[] textFields = { txtTen, txtTuoi, txtsdt};

		for (JTextField textField : textFields) {
		    textField.setPreferredSize(textFieldSize);
		    textField.setHorizontalAlignment(SwingConstants.CENTER);
		    textField.setFont(textFieldFont);
		}
		
		lblTen.setHorizontalAlignment(JLabel.CENTER);
		lbltuoi.setHorizontalAlignment(JLabel.CENTER);
		lblsdt.setHorizontalAlignment(JLabel.CENTER);
		jpPhai.setAlignmentX(Component.CENTER_ALIGNMENT);

		lbltitle.setAlignmentX(Component.CENTER_ALIGNMENT);

		pnlW.add(Box.createVerticalGlue()); 
		pnlW.add(lbltitle);
		pnlW.add(Box.createVerticalStrut(10)); 
		pnlW.add(jpFields);
		pnlW.add(Box.createVerticalStrut(10)); 
		pnlW.add(jpPhai);
		pnlW.add(Box.createVerticalGlue());

		add(pnlW, BorderLayout.WEST);
		JPanel emptyPanel = new JPanel();
		emptyPanel.setPreferredSize(new Dimension(15, 390));
		emptyPanel.setBackground(new Color(173, 216, 230));
		add(emptyPanel,BorderLayout.CENTER);

        lblTen.setFont(lblTen.getFont().deriveFont(Font.BOLD, 20));
        lbltuoi.setFont(lbltuoi.getFont().deriveFont(Font.BOLD, 20));
        lblsdt.setFont(lblsdt.getFont().deriveFont(Font.BOLD, 20));

		nam.setFont(lblsdt.getFont().deriveFont(Font.BOLD, 20));
		nu.setFont(lblsdt.getFont().deriveFont(Font.BOLD, 20));
		phai.setFont(lblsdt.getFont().deriveFont(Font.BOLD, 20));

		
		String[] columns = {
			"Mã NV", "Họ tên", "Phái","Tuổi", "SĐT" 	
		};
		modelNV = new DefaultTableModel(columns, 0);
		tableNhanVien = new JTable(modelNV);
		tableNhanVien.setPreferredScrollableViewportSize(new Dimension(940, 570));
		tableNhanVien.setRowHeight(30);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		for (int i = 0; i < tableNhanVien.getColumnCount(); i++) {
			tableNhanVien.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		tableNhanVien.setFont(new Font("Arial", Font.BOLD, 18));
		

        JTableHeader header = tableNhanVien.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 30));

        header.setBackground(lightBlue);
        header.setFont(new Font("Arial", Font.BOLD, 20));
		
		String[] luaChon = {"Nam", "Nữ"};
        TableColumn phaiColumn = tableNhanVien.getColumnModel().getColumn(2);
        phaiColumn.setCellEditor(new DefaultCellEditor(new JComboBox<>(luaChon)));
        
        JScrollPane scrollPane = new JScrollPane(tableNhanVien);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        add(scrollPane, BorderLayout.EAST);
		
				
				
		pnlS = new JPanel();
		pnlS.setBorder(new EmptyBorder(10, 0, 0, 0));
		pnlS.add(bTrai = new JPanel());
                
		bTrai.setLayout(new BoxLayout(bTrai, BoxLayout.X_AXIS));
		bTrai.setBackground(new Color(173, 216, 230));
			bTrai.add(lblTim = new JLabel("Nhập tên cần tìm: "));
			bTrai.add(txtTim = new JTextField(15));
			txtTim.setFont(new Font("Arial", Font.BOLD, 16));
			bTrai.add(btnTim = new JButton("Tìm"));
			lblTim.setFont(textFieldFont);
			txtTen.setFont(textFieldFont);
			btnTim.setFont(textFieldFont);
			
				
		pnlS.add(bPhai = new JPanel());
		pnlS.setBackground(new Color(173, 216, 230));
		bPhai.setLayout(new BoxLayout(bPhai, BoxLayout.X_AXIS));
		bPhai.setBackground(new Color(173, 216, 230));
		bPhai.setLayout(new BoxLayout(bPhai, BoxLayout.X_AXIS));

		bPhai.add(Box.createHorizontalStrut(30));
		bPhai.add(btnThem = new JButton("Thêm"));
		bPhai.add(Box.createHorizontalStrut(10));
		bPhai.add(btnXoaTrang = new JButton("Xóa trắng"));
		bPhai.add(Box.createHorizontalStrut(10));
		bPhai.add(btnXoa = new JButton("Xóa"));
		bPhai.add(Box.createHorizontalStrut(10));
		bPhai.add(btnSua = new JButton("Sửa"));


		bPhai.add(Box.createHorizontalGlue());
			btnThem.setFont(textFieldFont);
			btnXoaTrang.setFont(textFieldFont);
			btnXoa.setFont(textFieldFont);
			btnSua.setFont(textFieldFont);
		add(pnlS,BorderLayout.SOUTH);
	
	
		btnXoaTrang.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnTim.addActionListener(this);
		btnSua.addActionListener(this);
		tableNhanVien.addMouseListener(this);
		docDuLieuDBVaoTable();
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
		    String maNV = maTangDan();
		    String tenNV = txtTen.getText().trim();
		    String phai = nam.isSelected() ? "Nam" : nu.isSelected() ? "Nữ" : "";		
		    int tuoi;
		    try {
		        tuoi = Integer.parseInt(txtTuoi.getText().trim());
		        if (tuoi < 18 || tuoi > 60) {
		            JOptionPane.showMessageDialog(this, "Tuổi phải nằm trong khoảng từ 18 đến 60");
		            return;
		        }
		    } catch (NumberFormatException ex) {
		        JOptionPane.showMessageDialog(this, "Tuổi phải là số");
		        return;
		    }

		    String soDienThoai = txtsdt.getText().trim();
		    if (!soDienThoai.matches("\\d{10,11}")) {
		        JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ");
		        return;
		    }
		    NhanVien nv = new NhanVien(maNV, tenNV, phai, tuoi, soDienThoai);

		    try {
		        nv_dao.themNhanVien(nv);

		        modelNV.addRow(new Object[] { nv.getMaNV(), nv.getHoTenNV(),nv.getPhai(),
		            nv.getTuoi(), nv.getSdt()
		        });
		    } catch (Exception e2) {
		        e2.printStackTrace(); 
		        JOptionPane.showMessageDialog(this, "Trùng mã");
		    }
		}
			if (o.equals(btnSua)) {
		    int r = tableNhanVien.getSelectedRow();
		    if (r != -1) {
		        String maNV = (String) modelNV.getValueAt(r, 0);
		        String tenNV = txtTen.getText().trim();
		        String phai = nam.isSelected() ? "Nam" : "Nữ";
		        int tuoi;
			    try {
			        tuoi = Integer.parseInt(txtTuoi.getText().trim());
			        if (tuoi < 18 || tuoi > 60) {
			            JOptionPane.showMessageDialog(this, "Tuổi phải nằm trong khoảng từ 18 đến 60");
			            return;
			        }
			    } catch (NumberFormatException ex) {
			        JOptionPane.showMessageDialog(this, "Tuổi phải là số");
			        return;
			    }
			    String soDienThoai = txtsdt.getText().trim();
			    if (!soDienThoai.matches("\\d{10,11}")) {
			        JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ");
			        return;
			    }
		        NhanVien nv = new NhanVien(maNV, tenNV, phai, tuoi, soDienThoai);
		        
		        String ten = (String) modelNV.getValueAt(r, 1);
		        String Phai = (String) modelNV.getValueAt(r, 2);
		        int Tuoi = (int) modelNV.getValueAt(r, 3);
		        String Sdt = (String) modelNV.getValueAt(r, 4);
		        
		        if (!tenNV.equals(ten) || !phai.equals(Phai) || tuoi != Tuoi || !soDienThoai.equals(Sdt)) {
		            try {
		                nv_dao.capNhatNhanVien(nv);
		                modelNV.setValueAt(nv.getHoTenNV(), r, 1); 
		                modelNV.setValueAt(nv.getPhai(), r, 2);    
		                modelNV.setValueAt(nv.getTuoi(), r, 3);  
		                modelNV.setValueAt(nv.getSdt(), r, 4);
		                txtTen.setText("");
		                nam.setSelected(false);
		                nu.setSelected(false);
		                txtTuoi.setText("");
		                txtsdt.setText("");
		                ButtonGroup gr = new ButtonGroup();
		                gr.add(nam);
		                gr.add(nu);
		                gr.clearSelection();
		                JOptionPane.showMessageDialog(this, "Dữ liệu đã được lưu thành công");
		            } catch (Exception e2) {
		                e2.printStackTrace(); 
		                JOptionPane.showMessageDialog(this, "Lỗi khi lưu dữ liệu vào cơ sở dữ liệu");
		            }
		        } else {
		            JOptionPane.showMessageDialog(this, "Không có thay đổi nào để lưu");
		        }
		    } else {
		        JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để cập nhật");
		    }
		}
		
			if (o.equals(btnXoa)) {
			    int r = tableNhanVien.getSelectedRow();
			    if (r != -1) {
			        String maNV = (String) modelNV.getValueAt(r, 0);
			        
			        int rs = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa nhân viên này?");
			        if (rs == JOptionPane.YES_OPTION) {
			            modelNV.removeRow(r);
			            nv_dao.xoaNhanVien(maNV);
			        }
			    }
			}
		if(o.equals(btnXoaTrang)) {
			txtTen.setText("");
			nam.setSelected(false);
			nu.setSelected(false);
			txtTuoi.setText("");
			txtsdt.setText("");
			//đặt lại phương thức xóa trắng
    		ButtonGroup gr = new ButtonGroup();
			gr.add(nam);
			gr.add(nu);
			gr.clearSelection();
		}
		
		if (o.equals(btnTim)) {
	        String tim = txtTim.getText();
	        List<NhanVien> list = nv_dao.layThongTin();
	        modelNV.setRowCount(0);

	        // Duyệt qua từng Bàn trong danh sách
	        for (NhanVien nv : list) {
	            if (nv.getHoTenNV().contains(tim)) { 
	                //Thêm dòng mới vào bảng với thông tin của Bàn đó
	                modelNV.addRow(new Object[]{nv.getMaNV(), nv.getHoTenNV(),nv.getPhai(),
	    		            nv.getTuoi(), nv.getSdt()});
	            }
	        }

	        if (modelNV.getRowCount() == 0) {
	            JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên");
	        }
	    }
	}
     
	private int count = 0;

	private String maTangDan() {
	    String ma = nv_dao.layMaMoiNhat();
	 // Lấy phần số của mã bàn (bỏ đi ký tự "NV") và tăng giá trị lên 1
	    count = Integer.parseInt(ma.substring(2)) + 1; 
	    return String.format("NV%03d", count);
	}


	public void docDuLieuDBVaoTable() {
		List<NhanVien> listNV = nv_dao.layThongTin();
		for (NhanVien nv : listNV ) {
			modelNV.addRow(new Object [] {nv.getMaNV(), nv.getHoTenNV(),nv.getPhai(),
			        nv.getTuoi(), nv.getSdt()});
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableNhanVien.getSelectedRow();
		txtTen.setText(modelNV.getValueAt(row, 1).toString());
		String gioiTinh = modelNV.getValueAt(row, 2).toString();
	    if (gioiTinh.equals("Nam")) {
	        nam.setSelected(true);
	    } else {
	        nu.setSelected(true);
	    }
		txtTuoi.setText(modelNV.getValueAt(row, 3).toString());
		txtsdt.setText(modelNV.getValueAt(row, 4).toString());
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
