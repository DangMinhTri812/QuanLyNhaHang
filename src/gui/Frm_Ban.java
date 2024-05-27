package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
import dao.KhuVucDAO;
import dao.PhongDAO;
import dao.BanDAO;
import entity.KhuVuc;
import entity.PhieuDatBan;
import entity.Ban;
import entity.Phong;

public class Frm_Ban extends JDialog implements ActionListener,MouseListener{

    private static final long serialVersionUID = 1L;
    private JTable table;
	private DefaultTableModel modelBan;
	private JButton btnthem;
	private JButton btnxoa;
	private JLabel lbltim;
	private JTextField txttim;
	private JButton btntim;
	private JLabel lbltenBan;
	private JTextField txttenBan;
	private KhuVucDAO kvdao;
	private PhongDAO phongdao;
	private JComboBox<String> cmbkhuVuc;
	private BanDAO bandao;
	private JLabel lblsoGhe;
	private JTextField txtsoGhe;
	private JButton btnSua;
	private JComboBox<String> cmbPhong;
	private JLabel lblkhuVuc;
	private JLabel lblphong;
	private JButton btnxoaRong;
	private JButton btnlamMoi;

    public Frm_Ban() {
    	try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		kvdao = new KhuVucDAO();
		phongdao = new PhongDAO();
		bandao = new BanDAO();
		
        setTitle("Chi tiết bàn");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(255, 255, 255));
        titlePanel.setPreferredSize(new Dimension(100, 35)); 

        JLabel titleLabel = new JLabel("Chi tiết Bàn");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        

        String[] columnNames = {"Mã Bàn","Số Bàn","Số ghế", "Khu Vực","Phòng"};

        modelBan = new DefaultTableModel(columnNames, 0);
		table = new JTable(modelBan);
        table.setFont(new Font("Arial", Font.BOLD, 16));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setRowHeight(30);

        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));
        Color lightBlue = new Color(173, 216, 230); 
        header.setBackground(lightBlue);
        header.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel pnlButton1 = new JPanel();
        pnlButton1.setLayout(new BoxLayout(pnlButton1, BoxLayout.X_AXIS));
        pnlButton1.setBackground(new Color(255, 255, 255));
        pnlButton1.setPreferredSize(new Dimension(800, 35));


        cmbkhuVuc = new JComboBox<String>();
        cmbkhuVuc.setEditable(false);	
        cmbkhuVuc.addItem("Chọn khu vực");
		ArrayList<KhuVuc> listKV = kvdao.layThongTin() ;
		for (KhuVuc kv : listKV) {
			cmbkhuVuc.addItem(kv.getTenKhuVuc());
		}
		cmbkhuVuc.revalidate();
		cmbkhuVuc.repaint();
        cmbPhong = new JComboBox<String>();
        cmbPhong.setEditable(false);
		ArrayList<Phong> listPhong = phongdao.layThongTin() ;
		for (Phong p : listPhong) {
			cmbPhong.addItem(p.getTenPhong());
		}
		cmbkhuVuc.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String chonKhuVuc = (String) cmbkhuVuc.getSelectedItem();
		        cmbPhong.removeAllItems();
		        cmbPhong.addItem("Chọn phòng");
		        ArrayList<Phong> chonPhongTuKV = phongdao.layThongTinPhongTheoKhuVuc(chonKhuVuc);
		        for (Phong p : chonPhongTuKV) {
		            cmbPhong.addItem(p.getTenPhong());
		        }
		    }
		});
		

		cmbkhuVuc.setFont(new Font("Tahoma", Font.BOLD, 15));
		cmbPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		cmbPhong.setPreferredSize(new Dimension(150, 15));
        pnlButton1.add(Box.createHorizontalStrut(6));
        lblkhuVuc = new JLabel("Chọn khu vực: ");
        lblkhuVuc.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblphong = new JLabel("Chọn phòng: ");
        lblphong.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbltenBan = new JLabel("Nhập tên bàn: ");
        lbltenBan.setFont(new Font("Tahoma", Font.BOLD, 14));
        txttenBan = new JTextField(10);
        txttenBan.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblsoGhe = new JLabel("Nhập số ghế: ");
        lblsoGhe.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtsoGhe = new JTextField(10);
        txtsoGhe.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnthem = new JButton("Thêm");
        btnthem.setFont(new Font("Tahoma", Font.BOLD, 16));
        pnlButton1.add(lbltenBan);
        pnlButton1.add(Box.createHorizontalStrut(3));
        pnlButton1.add(txttenBan);
        pnlButton1.add(Box.createHorizontalStrut(3));
        pnlButton1.add(lblsoGhe);
        pnlButton1.add(Box.createHorizontalStrut(3));
        pnlButton1.add(txtsoGhe);
        pnlButton1.add(Box.createHorizontalStrut(6));
        pnlButton1.add(lblkhuVuc);
        pnlButton1.add(cmbkhuVuc);
        pnlButton1.add(Box.createHorizontalStrut(6));
        pnlButton1.add(lblphong);
        pnlButton1.add(cmbPhong);
        pnlButton1.add(Box.createHorizontalStrut(12));
        pnlButton1.add(btnthem);
        pnlButton1.add(Box.createHorizontalStrut(5));

        JPanel pnlButton2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlButton2.setBorder(BorderFactory.createEmptyBorder(7, 0, 7, 0));

        lbltim = new JLabel("Nhập tên bàn cần tìm: ");
        lbltim.setFont(new Font("Tahoma", Font.BOLD, 14));
        txttim = new JTextField();
        txttim.setFont(new Font("Tahoma", Font.BOLD, 15));
        txttim.setPreferredSize(new Dimension(150, 25)); 
        btntim = new JButton("Tìm");
        btntim.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnxoa = new JButton("Xóa");
        btnxoa.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnxoaRong = new JButton("Xóa rỗng");
        btnxoaRong.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnSua = new JButton("Sửa");
        btnSua.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnlamMoi = new JButton("Làm mới");
        btnlamMoi.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        pnlButton2.add(lbltim);
        pnlButton2.add(txttim);
        pnlButton2.add(btntim);
        pnlButton2.add(Box.createHorizontalStrut(12));
        pnlButton2.add(btnxoa);
        pnlButton2.add(btnxoaRong);
        pnlButton2.add(btnSua);
        pnlButton2.add(Box.createHorizontalStrut(12));
        pnlButton2.add(btnlamMoi);


        pnlButton2.setBackground(new Color(255, 255, 255));
        pnlButton2.setPreferredSize(new Dimension(120, 55));
        
        pnlButton2.add(Box.createHorizontalStrut(30));

        JPanel pnlButton = new JPanel();
        pnlButton.setBackground(new Color(255, 255, 255));
        pnlButton.setLayout(new BoxLayout(pnlButton, BoxLayout.Y_AXIS));
        pnlButton.add(pnlButton1);
        pnlButton.add(pnlButton2);
        pnlButton.setPreferredSize(new Dimension(200, 75));
        
 
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
        setSize(1090, 635);
        
        btnthem.addActionListener(this);
        btnxoa.addActionListener(this);
        btntim.addActionListener(this);
        btnSua.addActionListener(this);
        btnxoaRong.addActionListener(this);
        btnlamMoi.addActionListener(this);
        table.addMouseListener(this);
        docDuLieuDBVaoTable();
    }
    
    	  		
    public void docDuLieuDBVaoTable() {
        List<Ban> listBan = bandao.layThongTin();
        for (Ban b : listBan) {
            String maPhong = "";
            if (b.getPhong() != null) {
                maPhong = b.getPhong().getMaPhong();
            }
            modelBan.addRow(new Object[]{b.getMaBan(), b.getSoBan(), b.getSoGhe(), b.getKhuVuc().getMaKhuVuc(), maPhong});
        }
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
	  			
		if (o.equals(btnthem)) {
		    String maBan = maTangDan();
		    String soBan = txttenBan.getText();
		    int soGhe;
		    try {
		        soGhe = Integer.parseInt(txtsoGhe.getText().trim());
		    } catch (NumberFormatException ex) {
		        JOptionPane.showMessageDialog(this, "Vui lòng nhập số");
		        return;
		    }

		    String khuVuc = String.valueOf(cmbkhuVuc.getSelectedItem());
		    String phong = String.valueOf(cmbPhong.getSelectedItem());

		    KhuVucDAO khuVucDAO = new KhuVucDAO();
		    ArrayList<KhuVuc> dsKV = khuVucDAO.layThongTin();
		    PhongDAO phongDao = new PhongDAO();
		    ArrayList<Phong> dsPhong = phongDao.layThongTin();
		    
		    KhuVuc kv = timTenKhuVuc(dsKV, khuVuc);
		    Phong ph = null;

		    if (!phong.equals("Chon phong")) {
		        Phong phongObject = timTenPhong(dsPhong, phong);
		        if (phongObject != null) {
		            ph = phongObject;
		        }
		    }
		    if (kv != null) {
		        Ban b = new Ban(maBan, soBan, soGhe, kv, ph);
		        if (bandao.themBan(b)) {
		            modelBan.addRow(new Object[]{b.getMaBan(), b.getSoBan(), b.getSoGhe(), kv.getTenKhuVuc(), ph != null ? ph.getTenPhong() : null});
		            JOptionPane.showMessageDialog(this, "Thêm thành công");
		            xoaRong();
		        } else {
		            JOptionPane.showMessageDialog(null, "Trùng mã", "Lỗi", JOptionPane.ERROR_MESSAGE);
		        }
		    } 
		}	

	    if(o.equals(btnlamMoi)) {
	    	modelBan.setRowCount(0);
	    	docDuLieuDBVaoTable();
	    }
	    if (o.equals(btnxoa)) {
	        int r = table.getSelectedRow();
	        if (r != -1) {
	            String maBan = (String) modelBan.getValueAt(r, 0);
	            
	            int rs = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa bàn này?");
	            if (rs == JOptionPane.YES_OPTION) {
	                modelBan.removeRow(r);
	                bandao.xoaBan(maBan);
	            }
	        }
	    }
        
	    else if (o.equals(btntim)) {
	        String tenBan = txttim.getText();
	        List<Ban> list = bandao.layThongTin();
	        modelBan.setRowCount(0);

	        // Duyệt qua từng Bàn trong danh sách
	        for (Ban ban : list) {
	            if (ban.getSoBan().contains(tenBan)) { 
	                //Thêm dòng mới vào bảng với thông tin của Bàn đó
	                modelBan.addRow(new Object[]{ban.getMaBan(), ban.getSoBan(), ban.getSoGhe(), ban.getKhuVuc().getMaKhuVuc(), ban.getPhong().getMaPhong()});
	            }
	        }

	        if (modelBan.getRowCount() == 0) {
	            JOptionPane.showMessageDialog(this, "Không tìm thấy bàn");
	        }
	    }


    
        if(o.equals(btnxoaRong)) {
        	xoaRong();
        }
        if (e.getSource() == btnSua) {
            int r = table.getSelectedRow();
            if (r != -1) { 
            	// Lấy dữ liệu sau khi thay đổi
                String maBan = (String) modelBan.getValueAt(r, 0);
                String tenBan = txttenBan.getText().trim();
                int soGhe;
    		    try {
    		        soGhe = Integer.parseInt(txtsoGhe.getText().trim());
    		    } catch (NumberFormatException ex) {
    		        JOptionPane.showMessageDialog(this, "Vui lòng nhập số");
    		        return;
    		    }
                String khuVuc = String.valueOf(cmbkhuVuc.getSelectedItem()).trim();
                String phong = String.valueOf(cmbPhong.getSelectedItem());

                // Lấy dữ liệu chưa bị thay đổi trên bảng
                String ban = (String) modelBan.getValueAt(r, 1);
                int ghe = (int) modelBan.getValueAt(r, 2);
                String kv = (String) modelBan.getValueAt(r, 3);
                String p = (String) modelBan.getValueAt(r, 4);

                
                if (!tenBan.equals(ban) || soGhe != ghe || !khuVuc.equals(kv) || phong != null && !phong.equals(p)) {
                    try {
                        bandao.capNhatThongTinBan(maBan, tenBan, soGhe, khuVuc, phong);
                        modelBan.setValueAt(tenBan, r, 1);
                        modelBan.setValueAt(soGhe, r, 2);
                        modelBan.setValueAt(khuVuc, r, 3);
                        modelBan.setValueAt(phong == null ? phong : "", r, 4);
                        xoaRong();

                        JOptionPane.showMessageDialog(this, "Dữ liệu đã được sửa thành công");
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

    }
	private void xoaRong () {
		txttenBan.setText("");
        txtsoGhe.setText("");
        cmbkhuVuc.setSelectedIndex(0);
        cmbPhong.setSelectedIndex(0);
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
	private int count = 0;

	private String maTangDan() {
	    String ma = bandao.layMaBanMoiNhat();
	 // Lấy phần số của mã bàn (bỏ đi ký tự "B") và tăng giá trị lên 1
	    count = Integer.parseInt(ma.substring(1)) + 1; 
	    return String.format("B%03d", count);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
	    int row = table.getSelectedRow();
	    txttenBan.setText(modelBan.getValueAt(row, 1).toString());
	    txtsoGhe.setText(modelBan.getValueAt(row, 2).toString());
	    cmbkhuVuc.setSelectedItem(modelBan.getValueAt(row, 3).toString());

	    Object phongObj = modelBan.getValueAt(row, 4);
	    if (phongObj != null) {
	        cmbPhong.setSelectedItem(phongObj.toString());
	    } else {
	        cmbPhong.setSelectedIndex(0);
	    }
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


