package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
/*
 * ´æ¿î×ªÕË
 */
public class Layout_deposit2 implements ActionListener {

	private JFrame frmFreeLoveAtm;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Layout_deposit2 window = new Layout_deposit2();
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
	public Layout_deposit2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFreeLoveAtm = new JFrame();
		frmFreeLoveAtm.setTitle("free love ATM\u81EA\u52A8\u53D6\u6B3E\u673A-\u5B58\u6B3E");
		frmFreeLoveAtm.setBounds(100, 100, 450, 300);
		frmFreeLoveAtm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFreeLoveAtm.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel.setBounds(10, 10, 111, 29);
		frmFreeLoveAtm.getContentPane().add(panel);
		
		JLabel label = new JLabel("\u91D1\u989D\uFF1A");
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_1.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_1.setBounds(149, 10, 275, 29);
		frmFreeLoveAtm.getContentPane().add(panel_1);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(30);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setVgap(10);
		panel_2.setBounds(10, 77, 414, 125);
		frmFreeLoveAtm.getContentPane().add(panel_2);
		
		JTextArea textArea = new JTextArea();
		textArea.setRows(5);
		textArea.setColumns(50);
		textArea.setTabSize(10);
		panel_2.add(textArea);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(79, 212, 111, 40);
		frmFreeLoveAtm.getContentPane().add(panel_3);
		
		JButton btnNewButton = new JButton("\u786E\u5B9A");
		panel_3.add(btnNewButton);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(253, 214, 137, 38);
		frmFreeLoveAtm.getContentPane().add(panel_4);
		
		JButton btnNewButton_1 = new JButton("\u53D6\u6D88");
		panel_4.add(btnNewButton_1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
