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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import connectDB.ConnectDB;
import dao.BanDAO;
import dao.KhachHangDAO;
import entity.Ban;
import entity.KhachHang;

public class Frm_CapNhatKhachHang extends JPanel implements ActionListener, MouseListener {
	private JLabel lbtitle;
	private JLabel lbhoTen;
	private JTextField txthoTen;
	private JLabel lbdiaChi;
	private JTextField txtdiaChi;
	private JLabel lbsdt;
	private JTextField txtsdt;
	private JRadioButton nu;
	private JPanel pnlS;
	private JPanel bTrai;
	private JPanel bPhai;
	private JLabel lbTim;
	private JTextField txtTim;
	private JButton btnTim;
	private JButton btnThem;
	private JButton btnXoaTrang;
	private JButton btnXoa;
	private JButton btnSua;
	private JPanel pnlW;
	private JLabel phai;
	private DefaultTableModel modelKH;
	private JTable tableKhachHang;
	private JRadioButton nam;
	private KhachHangDAO kh_dao;

	public Frm_CapNhatKhachHang() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		kh_dao = new KhachHangDAO();
		
		setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		setBackground(new Color(204, 235, 150)); 
		pnlW = new JPanel();
		pnlW.setLayout(new BoxLayout(pnlW, BoxLayout.Y_AXIS));
		pnlW.setPreferredSize(new Dimension(520, 602));
		pnlW.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        Color lightBlue = new Color(173, 216, 230); // Light blue colorS
		lbtitle = new JLabel("THÔNG TIN KHÁCH HÀNG");
		lbtitle.setFont(new Font("Arial", Font.BOLD, 35));
		lbtitle.setForeground(Color.black);

		lbhoTen = new JLabel("Họ tên: ");
		txthoTen = new JTextField();
		phai = new JLabel("Phái: ");
		nu = new JRadioButton("Nữ");
		nam = new JRadioButton("Nam");
		ButtonGroup gr = new ButtonGroup();
		gr.add(nam);
		gr.add(nu);
		lbsdt = new JLabel("Số điện thoại: ");
		txtsdt = new JTextField();
		lbdiaChi = new JLabel("Địa chỉ: ");
		txtdiaChi = new JTextField();
		

		JPanel jpFields = new JPanel(new GridLayout(0, 1));
		jpFields.add(lbhoTen);
		jpFields.add(txthoTen);
		jpFields.add(lbsdt);
		jpFields.add(txtsdt);
		jpFields.add(lbdiaChi);
		jpFields.add(txtdiaChi);

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
		JTextField[] textFields = {txthoTen, txtdiaChi, txtsdt};

		for (JTextField textField : textFields) {
		    textField.setPreferredSize(textFieldSize);
		    textField.setHorizontalAlignment(SwingConstants.CENTER);
		    textField.setFont(textFieldFont);
		}
		
		lbhoTen.setHorizontalAlignment(JLabel.CENTER);
		lbdiaChi.setHorizontalAlignment(JLabel.CENTER);
		lbsdt.setHorizontalAlignment(JLabel.CENTER);
		jpPhai.setAlignmentX(Component.CENTER_ALIGNMENT);

		lbtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

		pnlW.add(Box.createVerticalGlue()); 
		pnlW.add(lbtitle);
		pnlW.add(Box.createVerticalStrut(10)); 
		pnlW.add(jpFields);
		pnlW.add(Box.createVerticalStrut(10)); 
		pnlW.add(jpPhai);
		pnlW.add(Box.createVerticalGlue());

		add(pnlW, BorderLayout.WEST);
		JPanel emptyPanel = new JPanel();
		emptyPanel.setPreferredSize(new Dimension(15, 490));
		emptyPanel.setBackground(new Color(204, 235, 150)); 
		add(emptyPanel,BorderLayout.CENTER);

        lbhoTen.setFont(lbhoTen.getFont().deriveFont(Font.BOLD, 20));
        lbdiaChi.setFont(lbdiaChi.getFont().deriveFont(Font.BOLD, 20));
        lbsdt.setFont(lbsdt.getFont().deriveFont(Font.BOLD, 20));

		nam.setFont(lbsdt.getFont().deriveFont(Font.BOLD, 20));
		nu.setFont(lbsdt.getFont().deriveFont(Font.BOLD, 20));
		phai.setFont(lbsdt.getFont().deriveFont(Font.BOLD, 20));

		
		String[] columns = {
			"Mã KH", "Họ", "Phái", "SĐT" , "Địa chỉ"	
		};
		modelKH = new DefaultTableModel(columns, 0);
		tableKhachHang = new JTable(modelKH);
		tableKhachHang.setPreferredScrollableViewportSize(new Dimension(940, 570));
		tableKhachHang.setRowHeight(30);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		for (int i = 0; i < tableKhachHang.getColumnCount(); i++) {
		    tableKhachHang.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		tableKhachHang.setFont(new Font("Arial", Font.BOLD, 18));
		

        JTableHeader header = tableKhachHang.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 30));

        header.setBackground(lightBlue);
        header.setFont(new Font("Arial", Font.BOLD, 20));
		
		String[] luaChon = {"Nam", "Nữ"};
        TableColumn phaiColumn = tableKhachHang.getColumnModel().getColumn(2);
        phaiColumn.setCellEditor(new DefaultCellEditor(new JComboBox<>(luaChon)));
        
        JScrollPane scrollPane = new JScrollPane(tableKhachHang);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        add(scrollPane, BorderLayout.EAST);
		
				
				
		pnlS = new JPanel();
		pnlS.setBorder(new EmptyBorder(10, 0, 0, 0));
		pnlS.add(bTrai = new JPanel());
                
		bTrai.setLayout(new BoxLayout(bTrai, BoxLayout.X_AXIS));
		bTrai.setBackground(new Color(204, 235, 150));
			bTrai.add(lbTim = new JLabel("Nhập tên cần tìm: "));
			bTrai.add(txtTim = new JTextField(15));
			txtTim.setFont(new Font("Arial", Font.BOLD, 16));
			bTrai.add(btnTim = new JButton("Tìm"));
			lbTim.setFont(textFieldFont);
			txthoTen.setFont(textFieldFont);
			btnTim.setFont(textFieldFont);
			
				
		pnlS.add(bPhai = new JPanel());
		pnlS.setBackground(new Color(204, 235, 150));
		bPhai.setLayout(new BoxLayout(bPhai, BoxLayout.X_AXIS));
		bPhai.setBackground(new Color(204, 235, 150));
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
		tableKhachHang.addMouseListener(this);
		docDuLieuDBVaoTable();
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			String maKH = maTangDan();
			String tenKH = txthoTen.getText();
			String phai = nam.isSelected()?"Nam" : nu.isSelected()?"Nữ":"";
			String diaChi = txtdiaChi.getText();
			String soDienThoai = txtsdt.getText().trim();
		    if (!soDienThoai.matches("\\d{10,11}")) {
		        JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ");
		        return;
		    }
			KhachHang kh = new KhachHang(maKH,tenKH,phai,soDienThoai,diaChi);
			try {
			    kh_dao.themKhachHang(kh);

			    modelKH.addRow(new Object[] { kh.getMaKH(), kh.getTenKH(),kh.getPhai().trim(),
			    		 kh.getSdt(),kh.getDiaChi()
			    });
			    xoaRong();
			    JOptionPane.showMessageDialog(this, "Thêm thành công");		   
			} catch (Exception e2) {
			    e2.printStackTrace(); 
			    JOptionPane.showMessageDialog(this, "Trùng mã");
			    return;
			}
		}
		if (o.equals(btnSua)) {
		    int r = tableKhachHang.getSelectedRow();
		    if (r != -1) {
		        String maKH = (String) modelKH.getValueAt(r, 0);
		        String tenKH = txthoTen.getText().trim();
		        String phai = nam.isSelected() ? "Nam" : "Nữ";
		        String sdt = txtsdt.getText().trim();
			    if (!sdt.matches("\\d{10,11}")) {
			        JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ");
			        return;
			    }
		        String diaChi = txtdiaChi.getText().trim();
		        
		        if (!tenKH.equals(modelKH.getValueAt(r, 1)) || !phai.equals(modelKH.getValueAt(r, 2)) || !sdt.equals(modelKH.getValueAt(r, 3)) || !diaChi.equals(modelKH.getValueAt(r, 4))) {
		            KhachHang kh = new KhachHang(maKH, tenKH, phai, sdt, diaChi);
		            try {
		                kh_dao.capNhatKhachHang(kh);
		                
		                modelKH.setValueAt(kh.getTenKH(), r, 1); 
		                modelKH.setValueAt(kh.getPhai(), r, 2);    
		                modelKH.setValueAt(kh.getSdt(), r, 3);  
		                modelKH.setValueAt(kh.getDiaChi(), r, 4);
		                xoaRong();
		                
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
		    int r = tableKhachHang.getSelectedRow();
		    if (r != -1) {
		        String maKH = (String) modelKH.getValueAt(r, 0);
		        
		        int rs = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa khách hàng này?");
		        if (rs == JOptionPane.YES_OPTION) {
		            modelKH.removeRow(r);
		            kh_dao.xoaKhachHang(maKH);
		        }
		    }
		}
		
		if(o.equals(btnXoaTrang)) {
			xoaRong();
		}
		
		 else if (o.equals(btnTim)) {
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
	}
	private void xoaRong() {
	    txthoTen.setText("");
	    nam.setSelected(false);
	    nu.setSelected(false);
	    txtdiaChi.setText("");
	    txtsdt.setText("");
	    ButtonGroup gr = new ButtonGroup();
	    gr.add(nam);
	    gr.add(nu);
	    gr.clearSelection();
	}

	private int count = 0;

	private String maTangDan() {
	    String ma = kh_dao.layMaMoiNhat();
	 // Lấy phần số của mã bàn (bỏ đi ký tự "KH") và tăng giá trị lên 1
	    count = Integer.parseInt(ma.substring(2)) + 1; 
	    return String.format("KH%03d", count);
	}


	public void docDuLieuDBVaoTable() {

	    List<KhachHang> listKH = kh_dao.layThongTin();
	    for (KhachHang kh : listKH) {
	        modelKH.addRow(new Object[] { kh.getMaKH(), kh.getTenKH(), kh.getPhai().trim(),
	        		kh.getSdt(), kh.getDiaChi() });
	    }
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableKhachHang.getSelectedRow();
		txthoTen.setText(modelKH.getValueAt(row, 1).toString());
		String gioiTinh = modelKH.getValueAt(row, 2).toString();
	    if (gioiTinh.equals("Nam")) {
	        nam.setSelected(true);
	    } else {
	        nu.setSelected(true);
	    }
		txtsdt.setText(modelKH.getValueAt(row, 3).toString());
		txtdiaChi.setText(modelKH.getValueAt(row, 4).toString());
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
