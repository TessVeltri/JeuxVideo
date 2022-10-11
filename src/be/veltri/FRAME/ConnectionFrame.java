package be.veltri.FRAME;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConnectionFrame extends JFrame {

	private static final long serialVersionUID = 7622267582865266682L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JLabel lblUsername;
	private JLabel lblPassword;

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
	public ConnectionFrame() {
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
		
		JLabel lblTitle = new JLabel("Jeux Vidéo");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblTitle.setBounds(253, 62, 279, 79);
		contentPane.add(lblTitle);
		
		txtUsername = new JTextField();
		txtUsername.setForeground(Color.BLACK);
		txtUsername.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsername.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		txtUsername.setBounds(271, 192, 244, 36);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setForeground(Color.BLACK);
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		txtPassword.setColumns(10);
		txtPassword.setBounds(271, 256, 244, 36);
		contentPane.add(txtPassword);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnLogIn.setBounds(305, 316, 175, 47);
		contentPane.add(btnLogIn);
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InscriptionFrame frame = new InscriptionFrame();
				frame.setVisible(true);
				dispose();
			}
		});
		btnSignIn.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnSignIn.setBounds(305, 374, 175, 47);
		contentPane.add(btnSignIn);
		
		lblUsername = new JLabel("Enter username : ");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblUsername.setBounds(272, 166, 242, 27);
		contentPane.add(lblUsername);
		
		lblPassword = new JLabel("Enter password : ");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblPassword.setBounds(271, 229, 244, 27);
		contentPane.add(lblPassword);
		
		JLabel image = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/background.jpg")).getImage();
		image.setIcon(new ImageIcon(img));
		image.setBounds(0, 0, 790, 470);
		getContentPane().add(image);
	}
}
