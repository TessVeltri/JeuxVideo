package be.veltri.FRAME;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.veltri.POJO.Admin;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeAdminFrame extends JFrame {

	private static final long serialVersionUID = -1378108003333212031L;
	private JPanel contentPane;

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
	public HomeAdminFrame(Admin admin) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomeAdmin = new JLabel("Welcome admin");
		lblWelcomeAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeAdmin.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblWelcomeAdmin.setBounds(253, 11, 279, 79);
		contentPane.add(lblWelcomeAdmin);
		
		JButton btnDisconnect = new JButton("");
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectionFrame conn = new ConnectionFrame();
				conn.setVisible(true);
				dispose();
			}
		});
		Image imgDisconnect = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/logOut.png")).getImage();
		btnDisconnect.setIcon(new ImageIcon(imgDisconnect));
		btnDisconnect.setOpaque(false);
		btnDisconnect.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnDisconnect.setContentAreaFilled(false);
		btnDisconnect.setBorderPainted(false);
		btnDisconnect.setBounds(709, 27, 50, 50);
		contentPane.add(btnDisconnect);
		
		JButton btnMessages = new JButton("Messages");
		btnMessages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminMessagesFrame frame = new AdminMessagesFrame(admin);
				frame.setVisible(true);
				dispose();
			}
		});
		btnMessages.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnMessages.setBounds(214, 114, 357, 67);
		contentPane.add(btnMessages);
		
		JButton btnAddGameName = new JButton("Add new game category");
		btnAddGameName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminAddGameName frame = new AdminAddGameName(admin);
				frame.setVisible(true);
				dispose();
			}
		});
		btnAddGameName.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnAddGameName.setBounds(214, 192, 357, 67);
		contentPane.add(btnAddGameName);
		
		JButton btnAddConsoleversion = new JButton("Add new console/version");
		btnAddConsoleversion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminAddConsoleVersion frame = new AdminAddConsoleVersion(admin);
				frame.setVisible(true);
				dispose();
			}
		});
		btnAddConsoleversion.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnAddConsoleversion.setBounds(214, 270, 357, 67);
		contentPane.add(btnAddConsoleversion);
		
		JButton btnManageUnits = new JButton("Manage units");
		btnManageUnits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminManageUnits frame = new AdminManageUnits(admin);
				frame.setVisible(true);
				dispose();
			}
		});
		btnManageUnits.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnManageUnits.setBounds(214, 347, 357, 67);
		contentPane.add(btnManageUnits);
		
		JLabel image = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/background.jpg")).getImage();
		image.setIcon(new ImageIcon(img));
		image.setBounds(0, 0, 790, 470);
		getContentPane().add(image);
	}

}
