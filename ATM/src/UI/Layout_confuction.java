package UI;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
/*
 * 功能界面
 */

public class Layout_confuction implements ActionListener{

	private JFrame frmFreeLoveAtm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Layout_confuction window = new Layout_confuction();
					window.frmFreeLoveAtm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Layout_confuction() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFreeLoveAtm = new JFrame();
		frmFreeLoveAtm.setTitle("free love ATM\u81EA\u52A8\u53D6\u6B3E\u673A");
		frmFreeLoveAtm.setBounds(100, 100, 450, 300);
		frmFreeLoveAtm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFreeLoveAtm.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(112, 10, 152, 43);
		frmFreeLoveAtm.getContentPane().add(panel);
		
		JButton btnNewButton = new JButton("\u53D6\u6B3E");
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(112, 78, 152, 43);
		frmFreeLoveAtm.getContentPane().add(panel_1);
		
		JButton btnNewButton_2 = new JButton("\u5B58\u6B3E");
		panel_1.add(btnNewButton_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(112, 142, 152, 43);
		frmFreeLoveAtm.getContentPane().add(panel_2);
		
		JButton btnNewButton_1 = new JButton("\u8F6C\u8D26");
		panel_2.add(btnNewButton_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(112, 195, 152, 36);
		frmFreeLoveAtm.getContentPane().add(panel_3);
		
		JButton btnNewButton_3 = new JButton("\u6253\u5370\u6E05\u5355");
		panel_3.add(btnNewButton_3);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
