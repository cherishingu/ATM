package UI;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
/*
 * Ö÷½çÃæ
 */

public class Layout_main2 implements ActionListener{

	private JFrame frmFreeLoveAtm;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField txtfreeLoveAtm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Layout_main2 window = new Layout_main2();
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
	public Layout_main2() {
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
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setBounds(10, 10, 134, 25);
		frmFreeLoveAtm.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u60A8\u7684\u5361\u53F7");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_1.setBounds(168, 10, 256, 25);
		frmFreeLoveAtm.getContentPane().add(panel_1);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(30);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panel_2.setBounds(10, 45, 134, 25);
		frmFreeLoveAtm.getContentPane().add(panel_2);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u60A8\u7684\u5BC6\u7801");
		panel_2.add(label);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_3.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_3.setBounds(168, 45, 256, 25);
		frmFreeLoveAtm.getContentPane().add(panel_3);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(30);
		panel_3.add(passwordField);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_4.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_4.setBounds(10, 113, 414, 108);
		frmFreeLoveAtm.getContentPane().add(panel_4);
		
		txtfreeLoveAtm = new JTextField();
		txtfreeLoveAtm.setText("\u6B22\u8FCE\u60A8\u4F7F\u7528free love ATM\u53D6\u6B3E\u673A");
		panel_4.add(txtfreeLoveAtm);
		txtfreeLoveAtm.setColumns(65);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
