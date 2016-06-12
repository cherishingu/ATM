package UI;
/*
 * 本程序实现了转账接界面
 * 
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JTextArea;

public class Layout_transfer2 implements ActionListener {

	private JFrame frmFreeLoveAtm;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Layout_transfer2 window = new Layout_transfer2();
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
	public Layout_transfer2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFreeLoveAtm = new JFrame();
		frmFreeLoveAtm.setTitle("free love ATM\u81EA\u52A8\u53D6\u6B3E\u673A-\u8F6C\u8D26");
		frmFreeLoveAtm.setBounds(100, 100, 450, 300);
		frmFreeLoveAtm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFreeLoveAtm.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setBounds(10, 10, 147, 28);
		frmFreeLoveAtm.getContentPane().add(panel);
		
		JLabel label = new JLabel("\u5BF9\u65B9\u8D26\u6237");
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_1.setBounds(179, 10, 245, 28);
		frmFreeLoveAtm.getContentPane().add(panel_1);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(30);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panel_2.setBounds(10, 48, 147, 28);
		frmFreeLoveAtm.getContentPane().add(panel_2);
		
		JLabel label_1 = new JLabel("\u91D1\u989D");
		panel_2.add(label_1);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_3.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_3.setBounds(179, 48, 245, 28);
		frmFreeLoveAtm.getContentPane().add(panel_3);
		
		textField_1 = new JTextField();
		textField_1.setColumns(30);
		panel_3.add(textField_1);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(57, 227, 100, 25);
		frmFreeLoveAtm.getContentPane().add(panel_6);
		
		JButton btnNewButton = new JButton("\u786E\u5B9A");
		panel_6.add(btnNewButton);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(204, 227, 119, 25);
		frmFreeLoveAtm.getContentPane().add(panel_7);
		
		JButton btnNewButton_1 = new JButton("\u53D6\u6D88");
		panel_7.add(btnNewButton_1);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_4.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_4.setBounds(10, 96, 414, 118);
		frmFreeLoveAtm.getContentPane().add(panel_4);
		
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
