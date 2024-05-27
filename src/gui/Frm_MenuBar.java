

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import timKiem.FrmTimKiemKhachHang;
import timKiem.Frm_TimKiemNhanVien;

public class Frm_MenuBar extends JFrame implements MouseListener {
	private Frm_DatMon datMon; 
    private Frm_DatBan datBan; 
    private Frm_CapNhatNhanVien nhanVien;
    private Frm_ThongKe thongKe;
    private Frm_CapNhapMon Mon ;
    private Frm_KhuVuc khuVuc;
    private Frm_Phong phong;
    private Frm_Ban ban;
    
    private Frm_CapNhatKhachHang capNhatKhachHang;
	private Frm_TimKiemNhanVien timKiemNhanVien;
	private FrmTimKiemKhachHang timKiemKhachHang;
	

    public Frm_MenuBar() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Quản lý nhà hàng");
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        JMenu thucDon = new JMenu("Thực đơn");
        JMenuItem mniCapNhatMon = new JMenuItem("Cập nhật món ");
        thucDon.add(mniCapNhatMon);

        JMenu ban = new JMenu("Bàn");
        JMenuItem mniCapNhatKV = new JMenuItem("Cập nhật khu vực");
        JMenuItem mniCapNhatP = new JMenuItem("Cập nhật phòng");
        JMenuItem mniCapNhatBan = new JMenuItem("Cập nhật bàn");
        ban.add(mniCapNhatKV);
        ban.add(mniCapNhatP);
        ban.add(mniCapNhatBan);
        
        JMenu nhanVienMenu = new JMenu("Nhân viên");
        JMenuItem mniDatMon = new JMenuItem("Đặt món");
        JMenuItem mniDatBan = new JMenuItem("Đặt bàn");
        JMenuItem mniCapNhatNhanVien = new JMenuItem("Cập nhật nhân viên");
        JMenuItem mniTimKiemNhanVien = new JMenuItem("Tìm kiếm nhân viên");
        JMenuItem mniThongKe = new JMenuItem("Thống kê doanh thu");
        nhanVienMenu.add(mniDatMon);
        nhanVienMenu.add(mniDatBan);
        nhanVienMenu.add(mniCapNhatNhanVien);
        nhanVienMenu.add(mniTimKiemNhanVien);
        nhanVienMenu.add(mniThongKe);


        JMenu khachHangMenu = new JMenu("Khách hàng");
        JMenuItem mniTimKiem = new JMenuItem("Tìm kiếm khách hàng");
        JMenuItem mnicapNhatKhachHang = new JMenuItem("Cập nhật khách hàng");
        khachHangMenu.add(mniTimKiem);
        khachHangMenu.add(mnicapNhatKhachHang);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(thucDon);
        menuBar.add(ban);
        menuBar.add(nhanVienMenu);
        menuBar.add(khachHangMenu);

        ActionListener menuActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    if (e.getSource() == mniDatBan) {
			        datBan = new Frm_DatBan();
			        setContentPane(datBan);
			    } else if (e.getSource() == mniCapNhatKV) {
			        showKhuVucDialog();
			    } else if (e.getSource() == mniCapNhatP) {
			        showPhongDialog();
			    } else if (e.getSource() == mniCapNhatBan) {
			        showBanDialog();
			    } else if (e.getSource() == mniDatMon) {
			        try {
						datMon = new Frm_DatMon();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
			        setContentPane(datMon);
			    } else if (e.getSource() == mniCapNhatNhanVien) {
			        nhanVien = new Frm_CapNhatNhanVien();
			        setContentPane(nhanVien);
			    } else if (e.getSource() == mniTimKiemNhanVien) {
			        timKiemNhanVien = new Frm_TimKiemNhanVien();
			        setContentPane(timKiemNhanVien);
			    } else if (e.getSource() == mniCapNhatMon) {
			        showMonDialog();
			    } else if (e.getSource() == mniThongKe) {
			        thongKe = new Frm_ThongKe();
			        setContentPane(thongKe);
			    } else if (e.getSource() == mnicapNhatKhachHang) {
			        capNhatKhachHang = new Frm_CapNhatKhachHang();
			        setContentPane(capNhatKhachHang);
			    } else if (e.getSource() == mniTimKiem) {
			        timKiemKhachHang = new FrmTimKiemKhachHang();
			        setContentPane(timKiemKhachHang);
			    }
			    revalidate();
			}
        };

        mniDatMon.addActionListener(menuActionListener);
        mniDatBan.addActionListener(menuActionListener);
        mniCapNhatNhanVien.addActionListener(menuActionListener);
        mniTimKiemNhanVien.addActionListener(menuActionListener);
        mniCapNhatMon.addActionListener(menuActionListener);
        mniThongKe.addActionListener(menuActionListener);
     
        mniCapNhatKV.addActionListener(menuActionListener);
        mniCapNhatP.addActionListener(menuActionListener);
        mniCapNhatBan.addActionListener(menuActionListener);
        mnicapNhatKhachHang.addActionListener(menuActionListener);
        mniTimKiem.addActionListener(menuActionListener);
        
        menuBar.setLayout(new FlowLayout(FlowLayout.CENTER)); 
        menuBar.setBorder(new EmptyBorder(7, 0, 0, 0));
     for (Component c : menuBar.getComponents()) {
         if (c instanceof JMenu) {
             JMenu menu = (JMenu) c;
             menu.setFont(new Font("Arial", Font.BOLD, 24));
             menu.addMouseListener(this);

             for (Component menuItem : menu.getMenuComponents()) {
                 if (menuItem instanceof JMenuItem) {
                     JMenuItem item = (JMenuItem) menuItem;
                     item.setFont(new Font("Arial", Font.BOLD, 20));
                 }
             }
         }
     }

        Dimension preferredSize = menuBar.getPreferredSize();
        preferredSize.height = 55;
        menuBar.setPreferredSize(preferredSize);
        menuBar.setBackground(Color.white);
     
        JPanel panel = new JPanel(new BorderLayout()) {
            // Phương thức paintComponent sẽ được ghi đè để vẽ hình ảnh lên panel
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon(getClass().getResource("/img/mainScreen.jpg")).getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };


        panel.add(menuBar, BorderLayout.NORTH);
        setContentPane(panel);
        setJMenuBar(menuBar);
        setVisible(true);
    }
    private void showKhuVucDialog() {
        if (khuVuc == null) {
            khuVuc = new Frm_KhuVuc();
        }

        khuVuc.setTitle("Cập nhật khu vực");
        khuVuc.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        khuVuc.setLocationRelativeTo(this);
        khuVuc.setModal(true);  
        khuVuc.setVisible(true);
    }
    
    private void showPhongDialog() {
        if (phong== null) {
        	phong = new Frm_Phong();
        }

        phong.setTitle("Cập nhật phòng");
        phong.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        phong.setLocationRelativeTo(this);
        phong.setModal(true);  
        phong.setVisible(true);
    }
    private void showMonDialog() {
        if (Mon== null) {
        	Mon = new Frm_CapNhapMon();
        }

        Mon.setTitle("Cập nhật Món");
        Mon.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        Mon.setLocationRelativeTo(this);
        Mon.setModal(true);  
        Mon.setVisible(true);
    }
    private void showBanDialog() {
        if (ban== null) {
        	ban = new Frm_Ban();
        }

        ban.setTitle("Cập nhật bàn");
        ban.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        ban.setLocationRelativeTo(this);
        ban.setModal(true);  
        ban.setVisible(true);
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
        if (e.getSource() instanceof JMenu) {
        	JMenu menu  = (JMenu) e.getSource();
        	menu.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.black));
        }
    }

	@Override
	public void mouseExited(MouseEvent e) {
	    if (e.getSource() instanceof JMenu) {
	        JMenu menu = (JMenu) e.getSource();
	        menu.setBorder(null); 
	    }
	}

}
