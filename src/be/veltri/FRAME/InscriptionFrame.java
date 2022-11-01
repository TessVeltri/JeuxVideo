package be.veltri.FRAME;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import be.veltri.POJO.Player;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.util.Date;
import java.util.Locale;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.time.LocalDate;
import java.time.ZoneId;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InscriptionFrame extends JFrame {

	private static final long serialVersionUID = 8042066396338039324L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPseudo;
	private JPasswordField txtPassword;
	private JPasswordField txtConfirmPassword;

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
	public InscriptionFrame() {
		setResizable(false);
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
		txtUsername.setHorizontalAlignment(SwingConstants.LEFT);
		txtUsername.setForeground(Color.BLACK);
		txtUsername.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		txtUsername.setColumns(10);
		txtUsername.setBounds(343, 121, 244, 36);
		contentPane.add(txtUsername);

		txtPseudo = new JTextField();
		txtPseudo.setHorizontalAlignment(SwingConstants.LEFT);
		txtPseudo.setForeground(Color.BLACK);
		txtPseudo.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		txtPseudo.setColumns(10);
		txtPseudo.setBounds(343, 168, 244, 36);
		contentPane.add(txtPseudo);

		txtPassword = new JPasswordField();
		txtPassword.setHorizontalAlignment(SwingConstants.LEFT);
		txtPassword.setForeground(Color.BLACK);
		txtPassword.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		txtPassword.setColumns(10);
		txtPassword.setBounds(343, 262, 244, 36);
		contentPane.add(txtPassword);

		txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setHorizontalAlignment(SwingConstants.LEFT);
		txtConfirmPassword.setForeground(Color.BLACK);
		txtConfirmPassword.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		txtConfirmPassword.setColumns(10);
		txtConfirmPassword.setBounds(343, 309, 244, 36);
		contentPane.add(txtConfirmPassword);

		JDateChooser dateOfBirth = new JDateChooser();
		dateOfBirth.setLocale(Locale.FRANCE);
		dateOfBirth.setBounds(343, 215, 244, 36);
		contentPane.add(dateOfBirth);

		JLabel lblDateOfBirth = new JLabel("Date of birth : ");
		lblDateOfBirth.setHorizontalAlignment(SwingConstants.LEFT);
		lblDateOfBirth.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblDateOfBirth.setBounds(185, 224, 133, 27);
		contentPane.add(lblDateOfBirth);

		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean verif = false;
				String username = txtUsername.getText().trim();
				String pseudo = txtPseudo.getText().trim();
				String password = String.valueOf(txtPassword.getPassword());
				String confirmPassword = String.valueOf(txtConfirmPassword.getPassword());
				Date birth = dateOfBirth.getDate();
				Player player = new Player(username, password, pseudo,
						null, LocalDate.now(), 10, false);
				Player used = new Player ();
				used.setUsername(username);
				String dateString = ((JTextField) dateOfBirth.getDateEditor().getUiComponent()).getText();

				if (username.equals("") || pseudo.equals("") || password.equals("") || confirmPassword.equals("")
						|| dateString.equals("") || dateString == null || birth == null) {
					JOptionPane.showMessageDialog(null, "Please complete all fields");
				} else if (birth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(LocalDate.now())
						|| dateOfBirth.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
								.isEqual(LocalDate.now())) {
					JOptionPane.showMessageDialog(null, "The date is not correct");
				} else if (!password.equals(confirmPassword)) {
					JOptionPane.showMessageDialog(null, "Passwords are not correct");
				} else if (used.find() != null) {
					JOptionPane.showMessageDialog(null, "This username is already used");
				} else {
					verif = true;
				}

				if (verif) {
					player.setDateOfBirth(birth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
					player.addBirthdayBonus();
					if (player.create()) {
						JOptionPane.showMessageDialog(null, "Great ! Your sign up is done ");
						HomePlayerFrame frame = new HomePlayerFrame(player);
						frame.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "An error has occurred, try again");
					}
				}
			}
		});
		btnSignIn.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnSignIn.setBounds(530, 392, 175, 47);
		contentPane.add(btnSignIn);

		JButton btnBack = new JButton("");
		btnBack.setOpaque(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectionFrame conn = new ConnectionFrame();
				conn.setVisible(true);
				dispose();
			}
		});
		Image imgBack = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/back.png")).getImage();
		btnBack.setIcon(new ImageIcon(imgBack));
		btnBack.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnBack.setBounds(68, 392, 50, 47);
		contentPane.add(btnBack);

		JLabel lblUsername = new JLabel("Username : ");
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblUsername.setBounds(185, 129, 133, 27);
		contentPane.add(lblUsername);

		JLabel lblPseudo = new JLabel("Pseudo : ");
		lblPseudo.setHorizontalAlignment(SwingConstants.LEFT);
		lblPseudo.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblPseudo.setBounds(185, 176, 133, 27);
		contentPane.add(lblPseudo);

		JLabel lblPassword = new JLabel("Password : ");
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblPassword.setBounds(185, 270, 133, 27);
		contentPane.add(lblPassword);

		JLabel lblConfirmPass = new JLabel("Confirm password : ");
		lblConfirmPass.setHorizontalAlignment(SwingConstants.LEFT);
		lblConfirmPass.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblConfirmPass.setBounds(185, 318, 163, 27);
		contentPane.add(lblConfirmPass);

		JLabel image = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/background.jpg")).getImage();
		image.setIcon(new ImageIcon(img));
		image.setBounds(0, 0, 790, 470);
		getContentPane().add(image);
	}
}
