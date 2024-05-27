package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import connectDB.ConnectDB;
import dao.BanDAO;
import dao.KhuVucDAO;
import dao.PhongDAO;
import entity.Ban;
import entity.KhuVuc;

import entity.Phong;

public class Frm_Phong extends JDialog implements ActionListener, MouseListener {

    private static final long serialVersionUID = 1L;
    private JTable table;
	private DefaultTableModel modelPhong;
	private JButton btnthem;
	private JButton btnxoa;
	private JLabel lbltim;
	private JTextField txttim;
	private JButton btntim;
	private JLabel lbltenPhong;
	private JTextField txttenPhong;
	private KhuVucDAO kv_dao;
	private PhongDAO phong_dao;
	private JComboBox<String> cmbkhuVuc;
	private JButton btnSua;
	private JButton btnLamMoi;

    public Frm_Phong() {
    	try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		kv_dao = new KhuVucDAO();
		phong_dao = new PhongDAO();
		
        setTitle("Chi tiết phòng");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(255, 255, 255));
        titlePanel.setPreferredSize(new Dimension(100, 35)); 

        JLabel titleLabel = new JLabel("Chi tiết phòng");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        

        String[] columnNames = {"Mã Phòng","Tên Phòng", "Thuộc","Số ghế"};

        modelPhong = new DefaultTableModel(columnNames, 0);
		table = new JTable(modelPhong);
        table.setFont(new Font("Arial", Font.BOLD, 16));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setRowHeight(30);

        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));
        Color lightBlue = new Color(173, 216, 230); // Light blue color
        header.setBackground(lightBlue);
        header.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel pnlButton1 = new JPanel();
        pnlButton1.setLayout(new BoxLayout(pnlButton1, BoxLayout.X_AXIS));
        pnlButton1.setBackground(new Color(255, 255, 255));
        pnlButton1.setPreferredSize(new Dimension(100, 35));

        cmbkhuVuc = new JComboBox<String>();
        cmbkhuVuc.setEditable(false);	
		cmbkhuVuc.revalidate();
		cmbkhuVuc.repaint();
		ArrayList<KhuVuc> listKV = kv_dao.layThongTin() ;
		for (KhuVuc kv : listKV) {
			cmbkhuVuc.addItem(kv.getTenKhuVuc());
		}
		
		cmbkhuVuc.setFont(new Font("Tahoma", Font.BOLD, 15));
        pnlButton1.add(Box.createHorizontalStrut(6));
        lbltenPhong = new JLabel("Nhập tên phòng: ");
        lbltenPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
        
        txttenPhong = new JTextField();
        txttenPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnthem = new JButton("Thêm");
        btnthem.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnLamMoi = new JButton("Làm mới");
        btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 16));
        pnlButton1.add(lbltenPhong);
        pnlButton1.add(Box.createHorizontalStrut(3));
        pnlButton1.add(txttenPhong);
        pnlButton1.add(Box.createHorizontalStrut(6));
        pnlButton1.add(cmbkhuVuc);
        pnlButton1.add(Box.createHorizontalStrut(12));
        pnlButton1.add(btnthem);
        pnlButton1.add(Box.createHorizontalStrut(16));
        pnlButton1.add(btnLamMoi);
        
        JPanel pnlButton2 = new JPanel();
        pnlButton2.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
        pnlButton2.add(Box.createHorizontalStrut(30));
        lbltim = new JLabel("Nhập tên phòng cần tìm: ");
        lbltim.setFont(new Font("Tahoma", Font.BOLD, 14));
        txttim = new JTextField();
        txttim.setFont(new Font("Tahoma", Font.BOLD, 15));
        btntim = new JButton("Tìm");
        btntim.setFont(new Font("Tahoma", Font.BOLD, 15));
        pnlButton2.add(lbltim);
        pnlButton2.add(txttim);
        pnlButton2.add(Box.createHorizontalStrut(5));
        pnlButton2.add(btntim);
        pnlButton2.add(Box.createHorizontalStrut(12));
        
        pnlButton2.setLayout(new BoxLayout(pnlButton2, BoxLayout.X_AXIS));
        pnlButton2.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlButton2.setBackground(new Color(255, 255, 255));
        pnlButton2.setPreferredSize(new Dimension(100, 35));

        btnxoa = new JButton("Xóa phòng");
        btnxoa.setFont(new Font("Tahoma", Font.BOLD, 15));
        pnlButton2.add(btnxoa);
        pnlButton2.add(Box.createHorizontalStrut(8));
        btnSua = new JButton("Sửa");
        btnSua.setFont(new Font("Tahoma", Font.BOLD, 15));
        pnlButton2.add(btnSua);
        pnlButton2.add(Box.createHorizontalStrut(30));

        JPanel pnlButton = new JPanel();
        pnlButton.setBackground(new Color(255, 255, 255));
        pnlButton.setLayout(new BoxLayout(pnlButton, BoxLayout.Y_AXIS));
        pnlButton.add(pnlButton1);
        pnlButton.add(pnlButton2);
        
 
        JPanel pnlContainer = new JPanel();
        pnlContainer.setBackground(new Color(255, 255, 255));
        pnlContainer.setLayout(new BoxLayout(pnlContainer, BoxLayout.Y_AXIS));
        pnlContainer.add(titlePanel, BorderLayout.NORTH);
        pnlContainer.add(Box.createVerticalStrut(10));
        pnlContainer.add(new JScrollPane(table), BorderLayout.CENTER);
        pnlContainer.add(Box.createVerticalStrut(10));
        pnlContainer.add(pnlButton, BorderLayout.SOUTH);
        add(pnlContainer);
        pnlContainer.setBorder(new EmptyBorder(7, 15, 7, 15));
        setSize(790, 465);
        
        btnthem.addActionListener(this);
        btnxoa.addActionListener(this);
        btntim.addActionListener(this);
        btnSua.addActionListener(this);
        table.addMouseListener(this);
        btnLamMoi.addActionListener(this);
        docDuLieuDBVaoTable();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    	Object o = e.getSource();
    	  	
    	if (o.equals(btnthem)) {
    	    String maPhong = maTangDan();
    	    String tenPhong = txttenPhong.getText().trim();
    	    String khuVuc = String.valueOf(cmbkhuVuc.getSelectedItem());
    	    int soGhe = 0;
    	    KhuVucDAO khuVucDAO = new KhuVucDAO();
    	    ArrayList<KhuVuc> dsKV = khuVucDAO.layThongTin();

    	    for (KhuVuc kv : dsKV) {
    	        if (khuVuc.equalsIgnoreCase(kv.getTenKhuVuc())) {
    	            Phong p = new Phong(maPhong, tenPhong, kv,soGhe);
    	                if (phong_dao.themPhong(p)) {
    	                    modelPhong.addRow(new Object[]{p.getMaPhong(), p.getTenPhong(), kv.getTenKhuVuc(),p.getSoGhe()});
    	                    txttenPhong.setText("");
    	                    cmbkhuVuc.setSelectedIndex(0);
    	                } else {
    	                    JOptionPane.showMessageDialog(null, "Trùng mã", "Lỗi", JOptionPane.ERROR_MESSAGE);
    	                }
    	                return;
    	            } 
    	        }
    	   }


    	if (o.equals(btnxoa)) {
    	    int r = table.getSelectedRow();
    	    if (r != -1) {
    	        String maPhong = (String) modelPhong.getValueAt(r, 0);
    	        
    	        int rs = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa ?");
    	        if (rs == JOptionPane.YES_OPTION) {
    	            modelPhong.removeRow(r);
    	            phong_dao.xoaPhong(maPhong);
    	        }
    	    }
    	}
        
    	else if (o.equals(btntim)) {
    	    String tenPhong = txttim.getText(); 
    	    List<Phong> list = phong_dao.layThongTin(); 
    	    modelPhong.setRowCount(0); 

    	    for (Phong phong : list) {
    	        if (phong.getTenPhong().contains(tenPhong)) { 
    	            modelPhong.addRow(new Object[]{phong.getMaPhong(), phong.getTenPhong(),phong.getKhuVuc().getMaKhuVuc(), phong.getSoGhe()});
    	        }
    	    }

    	    if (modelPhong.getRowCount() == 0) {
    	        JOptionPane.showMessageDialog(this, "Không tìm thấy phòng"); 
    	    }
    	}


     
        if (e.getSource() == btnSua) {
            int r = table.getSelectedRow();
            if (r != -1) {
                String maPhong = String.valueOf(modelPhong.getValueAt(r, 0)); 
                String tenPhong = txttenPhong.getText().trim();
                String khuVuc = String.valueOf(cmbkhuVuc.getSelectedItem());
                
                String ph = (String) modelPhong.getValueAt(r, 1);
                String kv = (String) modelPhong.getValueAt(r, 2);

                if (!tenPhong.equals(ph) || !khuVuc.equals(kv)) {
                    try {
                        phong_dao.capNhatThongTinPhong(maPhong, tenPhong, khuVuc); 
                        modelPhong.setValueAt(tenPhong, r, 1); 
                        modelPhong.setValueAt(khuVuc, r, 2); 
                        
                        txttenPhong.setText("");
                        cmbkhuVuc.setSelectedIndex(0);
                        
                        JOptionPane.showMessageDialog(this, "Dữ liệu đã được cập nhật thành công");
                    } catch (Exception e2) {
                        e2.printStackTrace(); 
                        JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật dữ liệu vào cơ sở dữ liệu");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Không có thay đổi nào để lưu");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để cập nhật");
            }
        }

        if(o.equals(btnLamMoi)) {
        	modelPhong.setRowCount(0);
	    	docDuLieuDBVaoTable();
        }


}
    
    private int count = 0;

	private String maTangDan() {
	    String ma = phong_dao.layMaMoiNhat();
	 // Lấy phần số của mã bàn (bỏ đi ký tự "P") và tăng giá trị lên 1
	    count = Integer.parseInt(ma.substring(1)) + 1; 
	    return String.format("P%03d", count);
	}

	public void docDuLieuDBVaoTable() {
		
		List<Phong> listPhong = phong_dao.layThongTin();
		for (Phong p : listPhong ) {
			modelPhong.addRow(new Object [] {p.getMaPhong(), p.getTenPhong(),p.getKhuVuc().getMaKhuVuc(),p.getSoGhe()});
			
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		txttenPhong.setText(modelPhong.getValueAt(row, 1).toString());
		cmbkhuVuc.setSelectedItem(modelPhong.getValueAt(row, 2).toString());
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


