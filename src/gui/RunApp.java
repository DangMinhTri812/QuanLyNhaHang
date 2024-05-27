package gui;

import javax.swing.SwingUtilities;

public class RunApp {
    public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Frm_MenuBar frm = new Frm_MenuBar();
				frm.setVisible(true);
			}
		});
	}
}
