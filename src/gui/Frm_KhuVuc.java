package gui;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import connectDB.ConnectDB;
import dao.KhuVucDAO;
import entity.KhuVuc;
import entity.NhanVien;

import javax.swing.table.DefaultTableCellRenderer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

public class Frm_KhuVuc extends JDialog implements ActionListener,MouseListener{

	    private static final long serialVersionUID = 1L;
	    private JTable table;
		private DefaultTableModel modelKV;
		private JButton btnthem;
		private JButton btnxoa;
		private JLabel lbltenKV;
		private JTextField txttenKV;
		private KhuVucDAO kv_dao;
		private JButton btnluu;

	    public Frm_KhuVuc() {
	    	
	    	try {
				ConnectDB.getInstance().connect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			kv_dao = new KhuVucDAO();
			
	        setTitle("Chi tiết khu vực");
	        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	        JPanel titlePanel = new JPanel();
	        titlePanel.setBackground(new Color(255, 255, 255));
	        titlePanel.setPreferredSize(new Dimension(100, 35)); 

	        JLabel titleLabel = new JLabel("Chi tiết khu vực");
	        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
	        titlePanel.add(titleLabel);
	        

	        String[] columnNames = {"Mã khu vực", "Tên khu vực"};

	        modelKV = new DefaultTableModel(columnNames, 0);
			table = new JTable(modelKV);
	        table.setFont(new Font("Arial", Font.BOLD, 16));

	        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
	        table.setRowHeight(30);

	        JTableHeader header = table.getTableHeader();
	        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));
	        Color lightBlue = new Color(173, 216, 230); // Light blue color
	        header.setBackground(lightBlue);
	        header.setFont(new Font("Arial", Font.BOLD, 18));

	        JPanel pnlButton = new JPanel();
	        pnlButton.setBackground(new Color(255, 255, 255));
	        pnlButton.setPreferredSize(new Dimension(100, 35));
	        pnlButton.setLayout(new BoxLayout(pnlButton, BoxLayout.X_AXIS));

	        lbltenKV = new JLabel("Nhập tên khu vực: ");
	        lbltenKV.setFont(new Font("Tahoma", Font.BOLD, 15));
	        txttenKV = new JTextField();
	        txttenKV.setFont(new Font("Tahoma", Font.BOLD, 15));
	        btnthem = new JButton("Thêm KV");
	        btnthem.setFont(new Font("Tahoma", Font.BOLD, 15));
	        pnlButton.add(lbltenKV);
	        pnlButton.add(txttenKV);
	        pnlButton.add(Box.createHorizontalStrut(6));
	        pnlButton.add(btnthem);
	        pnlButton.add(Box.createHorizontalStrut(15));
	        btnxoa = new JButton("Xóa KV");
	        btnxoa.setFont(new Font("Tahoma", Font.BOLD, 15));
	        pnlButton.add(btnxoa);
	        pnlButton.add(Box.createHorizontalStrut(6));
	        btnluu = new JButton("Lưu");
	        btnluu.setFont(new Font("Tahoma", Font.BOLD, 15));
	        pnlButton.add(btnluu);
	        pnlButton.add(Box.createHorizontalStrut(6));


	        
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
	        setSize(820, 465);
	        
	        btnthem.addActionListener(this);
	        btnxoa.addActionListener(this);
	        btnluu.addActionListener(this);
	        table.addMouseListener(this);
	        docDuLieuDBVaoTable();
	    }

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o = e.getSource();
			if (o.equals(btnthem)){
				String maKV = maTangDan();
				String tenKV = txttenKV.getText();
				KhuVuc kv = new KhuVuc(maKV, tenKV);
				try {
				    ;
				    kv_dao.themKhuVuc(kv);
				    modelKV.addRow(new Object[] { kv.getMaKhuVuc(), kv.getTenKhuVuc()
				    });
				    txttenKV.setText("");
				} catch (Exception e2) {
				    e2.printStackTrace(); 
				    JOptionPane.showMessageDialog(this, "Trùng mã");
				}
			}
			if (o.equals(btnxoa)) {
			    int r = table.getSelectedRow();
			    if (r != -1) {
			        String maKV = (String) modelKV.getValueAt(r, 0);
			        
			        int rs = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa khu vực này?");
			        if (rs == JOptionPane.YES_OPTION) {
			            modelKV.removeRow(r);
			            kv_dao.xoaKhuVuc(maKV);
			        }
			    }
			}
		
			if (o.equals(btnluu)) {
			    int selectedRow = table.getSelectedRow();
			    if (selectedRow != -1) {
			        String maKV = (String) modelKV.getValueAt(selectedRow, 0);
			        String tenKV = txttenKV.getText().trim();
			        try {
			            KhuVuc kv = new KhuVuc(maKV, tenKV);
			            kv_dao.capNhatKhuVuc(kv);
			            modelKV.setValueAt(tenKV, selectedRow, 1);
			            JOptionPane.showMessageDialog(this, "Dữ liệu đã được lưu thành công");
			        } catch (Exception e2) {
			            e2.printStackTrace(); 
			            JOptionPane.showMessageDialog(this, "Lỗi khi lưu dữ liệu vào cơ sở dữ liệu");
			        }
			        txttenKV.setText("");
			    }
			}
		}
		public void docDuLieuDBVaoTable() {
			List<KhuVuc> listKV = kv_dao.layThongTin();
			for (KhuVuc kv : listKV ) {
				modelKV.addRow(new Object [] {kv.getMaKhuVuc(),kv.getTenKhuVuc()});
			}
		}
		
		private int count = 0;

		private String maTangDan() {
		    String ma = kv_dao.layMaMoiNhat();
		 // Lấy phần số của mã bàn (bỏ đi ký tự "KV") và tăng giá trị lên 1
		    count = Integer.parseInt(ma.substring(2)) + 1; 
		    return String.format("KV%02d", count);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int row = table.getSelectedRow();
			txttenKV.setText(modelKV.getValueAt(row, 1).toString());
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
