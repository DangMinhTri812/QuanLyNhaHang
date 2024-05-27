package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frm_DangNhap extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;

    /**
     * Create the frame.
     */
    public Frm_DangNhap() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 150, 718, 497);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Đăng nhập hệ thống");
        lblNewLabel.setForeground(new Color(255, 0, 0));
        lblNewLabel.setBackground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(185, 27, 386, 123);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(getClass().getResource("/img/login.png")));
        lblNewLabel_1.setBounds(36, 145, 224, 215);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Tài khoản:");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblNewLabel_2.setBounds(270, 177, 105, 39);
        contentPane.add(lblNewLabel_2);
        
        JLabel lblNewLabel_2_1 = new JLabel("Mật khẩu:");
        lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblNewLabel_2_1.setBounds(270, 254, 145, 39);
        contentPane.add(lblNewLabel_2_1);
        
        textField = new JTextField();
        textField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        textField.setBounds(385, 181, 224, 28);
        contentPane.add(textField);
        textField.setColumns(10);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(385, 260, 224, 28);
        contentPane.add(passwordField);
        
        JCheckBox chckbxNewCheckBox = new JCheckBox("Hiển thị thông tin đăng nhập");
        chckbxNewCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        chckbxNewCheckBox.setBounds(365, 311, 386, 23);
        contentPane.add(chckbxNewCheckBox);
        chckbxNewCheckBox.addActionListener(e -> {
            if (chckbxNewCheckBox.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
            	passwordField.setEchoChar('*');
            }
        });

        
        JButton btnNewButton = new JButton("Đăng nhập");
        btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        btnNewButton.setBounds(335, 362, 118, 39);
        btnNewButton.addActionListener(this);
        contentPane.add(btnNewButton);
        
        JButton btnThoat = new JButton("Thoát");
        btnThoat.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        btnThoat.setBounds(478, 362, 89, 39);
        btnThoat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
            }
        });
        contentPane.add(btnThoat);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = textField.getText();
        String password = new String(passwordField.getPassword());
        if (isValidLogin(username, password)) {
            JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Frm_MenuBar frm = new Frm_MenuBar();
                    frm.setVisible(true);
                }
            });
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Tên đăng nhập hoặc mật khẩu không chính xác. Vui lòng thử lại !");
        }
    }

    private boolean isValidLogin(String username, String password) {
        return username.equals("admin") && password.equals("123");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Frm_DangNhap frm = new Frm_DangNhap();
                frm.setVisible(true);
            }
        });
    }
}
