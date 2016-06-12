package UI;
/*
 * 本程序主要实现了ATM的取款界面
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Box;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

public class Layout_withdrawls2 implements ActionListener{

	private JFrame frmAtm;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Layout_withdrawls2 window = new Layout_withdrawls2();
					window.frmAtm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Layout_withdrawls2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAtm = new JFrame();
		frmAtm.setTitle("free love ATM\u81EA\u52A8\u53D6\u6B3E\u673A-\u53D6\u6B3E");
		frmAtm.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel.setBounds(0, 10, 149, 26);
		frmAtm.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("\u94F6\u884C\u5361\u53F7\uFF1A");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_1.setBounds(159, 10, 265, 26);
		frmAtm.getContentPane().add(panel_1);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(30);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panel_2.setBounds(0, 46, 149, 26);
		frmAtm.getContentPane().add(panel_2);
		
		JLabel label = new JLabel("\u91D1\u989D\uFF1A");
		panel_2.add(label);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_3.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_3.setBounds(159, 46, 265, 26);
		frmAtm.getContentPane().add(panel_3);
		
		textField_1 = new JTextField();
		textField_1.setColumns(30);
		panel_3.add(textField_1);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(82, 219, 89, 33);
		frmAtm.getContentPane().add(panel_6);
		
		JButton btnNewButton = new JButton("\u786E\u5B9A");
		panel_6.add(btnNewButton);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(216, 219, 106, 33);
		frmAtm.getContentPane().add(panel_7);
		
		JButton btnNewButton_1 = new JButton("\u53D6\u6D88");
		panel_7.add(btnNewButton_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 82, 373, 135);
		frmAtm.getContentPane().add(panel_4);
		
		JTextArea textArea = new JTextArea();
		textArea.setColumns(50);
		textArea.setRows(5);
		panel_4.add(textArea);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
