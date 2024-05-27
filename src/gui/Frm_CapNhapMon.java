package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import connectDB.ConnectDB;
import dao.LoaiMonDao;
import dao.MonAnDAO;
import entity.Ban;
import entity.LoaiMon;
import entity.MonAn;

public class Frm_CapNhapMon extends JDialog implements ActionListener, MouseListener {
    private static final long serialVersionUID = 1L;
    private MonAnDAO mon_dao;
    private LoaiMonDao loaimon_dao;
    private DefaultTableModel modelMon;
    private JTable table;
    private JTextField txttenMon, txtdonGia;
    private JButton btnthem, btnxoa, btnluu, btnxoaRong, btnSua, btnlamMoi;
    private JLabel lbltenMon, lblloaiMon, lbldonGia;
    private JComboBox<LoaiMon> cboLoaiMon;
    private List<LoaiMon> dsLoaiMon;
    private List<MonAn> dsMonAn;

    public Frm_CapNhapMon() {
        try {
            ConnectDB.getInstance().connect();
            mon_dao = new MonAnDAO();
            loaimon_dao = new LoaiMonDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setTitle("Chi tiết Món");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setPreferredSize(new Dimension(100, 35));

        JLabel titleLabel = new JLabel("Chi tiết Món");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titlePanel.add(titleLabel);

        String[] columnNames = {"Mã món", "Tên món", "Đơn giá", "Loại món"};
        modelMon = new DefaultTableModel(columnNames, 0);
        table = new JTable(modelMon);
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

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel pnlButton1 = new JPanel();
        pnlButton1.setLayout(new BoxLayout(pnlButton1, BoxLayout.X_AXIS));
        pnlButton1.setBackground(Color.WHITE);
        pnlButton1.setPreferredSize(new Dimension(800, 35));

        lbltenMon = new JLabel("Nhập tên món: ");
        lbltenMon.setFont(new Font("Tahoma", Font.BOLD, 14));
        txttenMon = new JTextField(10);
        txttenMon.setFont(new Font("Tahoma", Font.BOLD, 14));

        lblloaiMon = new JLabel("Chọn loại món: ");
        lblloaiMon.setFont(new Font("Tahoma", Font.BOLD, 14));

        lbldonGia = new JLabel("Nhập đơn giá: ");
        lbldonGia.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtdonGia = new JTextField(10);
        txtdonGia.setFont(new Font("Tahoma", Font.BOLD, 14));

        btnthem = new JButton("Thêm món");
        btnthem.setFont(new Font("Tahoma", Font.BOLD, 14));

        btnxoa = new JButton("Xóa món");
        btnxoa.setFont(new Font("Tahoma", Font.BOLD, 14));

        btnluu = new JButton("Lưu");
        btnluu.setFont(new Font("Tahoma", Font.BOLD, 14));

        pnlButton1.add(lbltenMon);
        pnlButton1.add(txttenMon);
        pnlButton1.add(Box.createHorizontalStrut(6));
        pnlButton1.add(lbldonGia);
        pnlButton1.add(txtdonGia);
        pnlButton1.add(Box.createHorizontalStrut(6));
        pnlButton1.add(lblloaiMon);
        pnlButton1.add(Box.createHorizontalStrut(6));

        cboLoaiMon = new JComboBox<>();
        cboLoaiMon.setFont(new Font("Tahoma", Font.BOLD, 14));
        cboLoaiMon.setPreferredSize(new Dimension(150, 25));

        // Đọc dữ liệu loại món từ cơ sở dữ liệu và thêm vào combobox
        dsLoaiMon = loaimon_dao.getAllDsLoaiMon();
        for (LoaiMon lm : dsLoaiMon) {
            cboLoaiMon.addItem(lm);
        }
        pnlButton1.add(cboLoaiMon);
        pnlButton1.add(Box.createHorizontalStrut(6));
        pnlButton1.add(btnthem);
        pnlButton1.add(Box.createHorizontalStrut(6));
        pnlButton1.add(btnxoa);
        pnlButton1.add(Box.createHorizontalStrut(6));
        pnlButton1.add(btnluu);
        pnlButton1.add(Box.createHorizontalStrut(6));

        JPanel pnlButton2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlButton2.setBorder(BorderFactory.createEmptyBorder(7, 0, 7, 0));

        btnxoaRong = new JButton("Xóa rỗng");
        btnxoaRong.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnSua = new JButton("Sửa");
        btnSua.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnlamMoi = new JButton("Làm mới");
        btnlamMoi.setFont(new Font("Tahoma", Font.BOLD, 14));

        pnlButton2.add(btnxoaRong);
        pnlButton2.add(btnSua);
        pnlButton2.add(btnlamMoi);

        pnlButton2.setBackground(Color.WHITE);
        pnlButton2.setPreferredSize(new Dimension(120, 55));

        JPanel pnlButton = new JPanel();
        pnlButton.setBackground(Color.WHITE);
        pnlButton.setLayout(new BoxLayout(pnlButton, BoxLayout.Y_AXIS));
        pnlButton.add(pnlButton1);
        pnlButton.add(pnlButton2);
        pnlButton.setPreferredSize(new Dimension(200, 75));

        JPanel pnlContainer = new JPanel();
        pnlContainer.setBackground(Color.WHITE);
        pnlContainer.setLayout(new BorderLayout());
        pnlContainer.add(titlePanel, BorderLayout.NORTH);
        pnlContainer.add(scrollPane, BorderLayout.CENTER);
        pnlContainer.add(pnlButton, BorderLayout.SOUTH);
        pnlContainer.setBorder(new EmptyBorder(7, 15, 7, 15));
        getContentPane().add(pnlContainer);

        setSize(1200, 600);
        setLocationRelativeTo(null);

        btnthem.addActionListener(this);
        btnxoa.addActionListener(this);
        btnluu.addActionListener(this);
        btnSua.addActionListener(this);
        btnxoaRong.addActionListener(this);
        btnlamMoi.addActionListener(this);
        btnluu.addActionListener(this);
        table.addMouseListener(this);

        docDuLieuDBVaoTable();
    }

    public void docDuLieuDBVaoTable() {
        List<MonAn> listMonAn = mon_dao.layThongTin();
        for (MonAn ma : listMonAn) {
            String maLoaiMon = "";
            if (ma.getLoaiMon() != null) {
                maLoaiMon = ma.getLoaiMon().getMaLoaiMon();
            }
            modelMon.addRow(new Object[]{ma.getMaMon(), ma.getTenMon(), ma.getDonGia(), maLoaiMon});
        }
    }

    private int count = 0;

    private String maTangDan() {
	    String ma = mon_dao.layMaMonAnMoiNhat();
	 // Lấy phần số của mã bàn (bỏ đi ký tự "B") và tăng giá trị lên 1
	    count = Integer.parseInt(ma.substring(1)) + 1; 
	    return String.format("M%03d", count);
	}
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnthem)) {
            // Xử lý sự kiện thêm món ăn
            String maMon = maTangDan();
            String tenMon = txttenMon.getText().trim();
            int donGia;
            try {
                donGia = Integer.parseInt(txtdonGia.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số cho đơn giá.");
                return;
            }

            LoaiMon loaiMon = (LoaiMon) cboLoaiMon.getSelectedItem();
            String tenLoaiMon = loaiMon.getTenLoaiMon();

            if (loaiMon != null) {
                MonAn monAn = new MonAn(maMon, tenMon, donGia, loaiMon);
                if (mon_dao.themMonAn(monAn)) {
                    modelMon.addRow(new Object[]{monAn.getMaMon(), monAn.getTenMon(), monAn.getDonGia(), loaiMon.getTenLoaiMon()});
                    JOptionPane.showMessageDialog(this, "Thêm món ăn thành công.");
                    xoaRong();
                } else {
                    JOptionPane.showMessageDialog(this, "Mã món ăn đã tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (o.equals(btnxoa)) {
            // Xử lý sự kiện xóa món ăn
            int r = table.getSelectedRow();
            if (r != -1) {
                String maMon = (String) modelMon.getValueAt(r, 0);
                int rs = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa món này?");
                if (rs == JOptionPane.YES_OPTION) {
                    modelMon.removeRow(r);
                    mon_dao.xoaMon(maMon);
                }
            }
        } else if (o.equals(btnluu)) {
            // Xử lý sự kiện lưu thay đổi
            // Hiện tại sự kiện này không có xử lý, có thể thêm xử lý sau nếu cần.
        } else if (o.equals(btnSua)) {
            // Xử lý sự kiện sửa món ăn
            int r = table.getSelectedRow();
            if (r != -1) { 
                String maMon = (String) modelMon.getValueAt(r, 0);
                String tenMon = txttenMon.getText().trim();
                int donGia;
                try {
                    donGia = Integer.parseInt(txtdonGia.getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số cho đơn giá.");
                    return;
                }
                String tenLoaiMon = String.valueOf(cboLoaiMon.getSelectedItem());
                String mon = (String) modelMon.getValueAt(r, 1);
                int gia = (int) modelMon.getValueAt(r, 2);
                String loaiMon = (String) modelMon.getValueAt(r, 3);

                if (!tenMon.equals(mon) || donGia != gia || !tenLoaiMon.equals(loaiMon)) {
                    try {
                        mon_dao.capNhatThongTinMon(maMon, tenMon, donGia, tenLoaiMon);
                        modelMon.setValueAt(tenMon, r, 1);
                        modelMon.setValueAt(donGia, r, 2);
                        modelMon.setValueAt(tenLoaiMon, r, 3);

                        // Xóa các trường văn bản để chuẩn bị cho một món ăn mới
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
        } else if (o.equals(btnxoaRong)) {
            // Xử lý sự kiện xóa rỗng
            xoaRong();
        } else if (o.equals(btnlamMoi)) {
            // Xử lý sự kiện làm mới
            modelMon.setRowCount(0); // Xóa dữ liệu trên bảng
            docDuLieuDBVaoTable(); // Đọc lại dữ liệu từ cơ sở dữ liệu
        }
    }

    // Phương thức này xóa các trường văn bản để chuẩn bị cho một món ăn mới
    private void xoaRong() {
        txttenMon.setText("");
        txtdonGia.setText("");
        cboLoaiMon.setSelectedIndex(0);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = table.getSelectedRow();
        txttenMon.setText(modelMon.getValueAt(row, 1).toString());
        txtdonGia.setText(modelMon.getValueAt(row, 2).toString());

        Object loaiMonObj = modelMon.getValueAt(row, 3);
        if (loaiMonObj != null) {
            cboLoaiMon.setSelectedItem(loaiMonObj.toString());
        } else {
            // Set default value for cboLoaiMon, if necessary
            // cboLoaiMon.setSelectedIndex(0);
        }
    }

    // Các phương thức dưới đây là các phương thức của giao diện MouseListener, không cần sửa đổi
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}