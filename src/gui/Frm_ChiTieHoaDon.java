package gui;

import javax.swing.JFrame;

import dao.ChiTietHoaDonDAO;
import entity.ChiTietHoaDon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Frm_ChiTieHoaDon extends JFrame {
	private JTable table;
	public Frm_ChiTieHoaDon() {
		setTitle("Chi tiết hóa đơn");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 927, 327);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Chi tiết hóa đơn");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel.setBounds(335, 11, 219, 24);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 907, 270);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
	public static void main(String[] args) {
		new Frm_ChiTieHoaDon().setVisible(true);
	}
}
