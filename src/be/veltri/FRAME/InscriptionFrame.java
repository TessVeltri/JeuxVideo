package be.veltri.FRAME;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;

public class InscriptionFrame extends JFrame {

	private static final long serialVersionUID = 8042066396338039324L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPseudo;
	private JTextField txtPassword;
	private JTextField txtConfirmPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectionFrame frame = new ConnectionFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public InscriptionFrame() {
		initialize();
		setFocusable(true);
	}
	/**
	 * Create the frame.
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Inscription");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblTitle.setBounds(253, 11, 279, 79);
		contentPane.add(lblTitle);
		
		txtUsername = new JTextField();
		txtUsername.setText("Username");
		txtUsername.setHorizontalAlignment(SwingConstants.LEFT);
		txtUsername.setForeground(Color.GRAY);
		txtUsername.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		txtUsername.setColumns(10);
		txtUsername.setBounds(68, 122, 244, 36);
		contentPane.add(txtUsername);
		
		txtPseudo = new JTextField();
		txtPseudo.setText("Pseudo");
		txtPseudo.setHorizontalAlignment(SwingConstants.LEFT);
		txtPseudo.setForeground(Color.GRAY);
		txtPseudo.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		txtPseudo.setColumns(10);
		txtPseudo.setBounds(68, 169, 244, 36);
		contentPane.add(txtPseudo);
		
		txtPassword = new JTextField();
		txtPassword.setText("Password");
		txtPassword.setHorizontalAlignment(SwingConstants.LEFT);
		txtPassword.setForeground(Color.GRAY);
		txtPassword.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		txtPassword.setColumns(10);
		txtPassword.setBounds(68, 216, 244, 36);
		contentPane.add(txtPassword);
		
		txtConfirmPassword = new JTextField();
		txtConfirmPassword.setText("Confirm password");
		txtConfirmPassword.setHorizontalAlignment(SwingConstants.LEFT);
		txtConfirmPassword.setForeground(Color.GRAY);
		txtConfirmPassword.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		txtConfirmPassword.setColumns(10);
		txtConfirmPassword.setBounds(68, 263, 244, 36);
		contentPane.add(txtConfirmPassword);
		
		
	}
}
