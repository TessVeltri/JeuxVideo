package be.veltri.FRAME;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import be.veltri.POJO.Admin;
import be.veltri.POJO.Player;
import be.veltri.POJO.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
	 * Create the frame.
	 */
	public ConnectionFrame() {
		setResizable(false);
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
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText().trim();
				String password = String.valueOf(txtPassword.getPassword());
				User user = new Player();
				user.setUsername(username);
				user.setPassword(password);
				if (username.equals("") || password.equals("")) {
					JOptionPane.showMessageDialog(null, "Please complete all fields");
				} else {
					user = user.login();
					if (user != null) {
						if (user instanceof Player) {
							Player player = (Player) user;
							player.addBirthdayBonus();
							boolean update = player.update();
							if (update) {
								HomePlayerFrame frame = new HomePlayerFrame(player);
								frame.setVisible(true);
								dispose();
							}
						} else if (user instanceof Admin) {
							Admin admin = (Admin) user;
							HomeAdminFrame frame = new HomeAdminFrame(admin);
							frame.setVisible(true);
							dispose();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Username or password incorrect");
					}
				}
			}
		});
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
